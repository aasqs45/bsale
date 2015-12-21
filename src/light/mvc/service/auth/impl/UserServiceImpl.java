package light.mvc.service.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.constant.GlobalConstant;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.SessionInfo;
import light.mvc.model.auth.Taccess;
import light.mvc.model.auth.Trole;
import light.mvc.model.auth.Tuser;
import light.mvc.model.basic.Tbranch;
import light.mvc.pageModel.auth.User;
import light.mvc.service.auth.UserServiceI;
import light.mvc.utils.MD5Util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private BaseDaoI<Tuser> userDao;
	
	@Autowired
	private BaseDaoI<Trole> roleDao;
	
	@Autowired
	private BaseDaoI<Tbranch> branchDao;

	@Override
	public void add(User u) {
		Tuser t = new Tuser();
		BeanUtils.copyProperties(u, t);
		t.setIsdefault(1);
		t.setBranch(branchDao.get(Tbranch.class, u.getBranchId()));
		
		List<Trole> roles = new ArrayList<Trole>();
		if (u.getRoleIds() != null) {
			for (String roleId : u.getRoleIds().split(",")) {
				roles.add(roleDao.get(Trole.class, Long.valueOf(roleId)));
			}
		}
		t.setRoles(new HashSet<Trole>(roles));
		
		t.setPassword(MD5Util.md5(u.getPassword()));
		t.setState(GlobalConstant.ENABLE);
		t.setCreatedatetime(new Date());
		userDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tuser t = userDao.get(Tuser.class, id);
		del(t);
	}

	private void del(Tuser t) {
		userDao.delete(t);
	}

	@Override
	public void edit(User user) {
		Tuser t = userDao.get(Tuser.class,user.getId());
		t.setAge(user.getAge());
		t.setLoginname(user.getLoginname());
		t.setName(user.getName());
		t.setBranch(branchDao.get(Tbranch.class,user.getBranchId()));
		List<Trole> roles = new ArrayList<Trole>();
		if (user.getRoleIds() != null) {
			for (String roleId : user.getRoleIds().split(",")) {
				roles.add(roleDao.get(Trole.class, Long.valueOf(roleId)));
			}
		}
		t.setRoles(new HashSet<Trole>(roles));
		t.setSex(user.getSex());
		t.setUsertype(user.getUsertype());
		if(user.getPassword()!=null&&!"".equals(user.getPassword())){
			t.setPassword(MD5Util.md5(user.getPassword()));
		}
		userDao.update(t);
	}

	@Override
	public User get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tuser t = userDao.get("from Tuser t  left join fetch t.roles role where t.id = :id", params);
		User u = new User();
		BeanUtils.copyProperties(t, u);
		
		if(t.getBranch()!=null){
			u.setBranchId(t.getBranch().getId());
			u.setBranchName(t.getBranch().getName());
		}
		
		if (t.getRoles() != null && !t.getRoles().isEmpty()) {
			String roleIds = "";
			String roleNames = "";
			boolean b = false;
			for (Trole role : t.getRoles()) {
				if (b) {
					roleIds += ",";
					roleNames += ",";
				} else {
					b = true;
				}
				roleIds += role.getId();
				roleNames += role.getName();
			}
			u.setRoleIds(roleIds);
			u.setRoleNames(roleNames);
		}
		return u;
	}

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginname", user.getLoginname());
		params.put("password", MD5Util.md5(user.getPassword()));
		Tuser t = userDao.get("from Tuser t where t.loginname = :loginname and t.password = :password", params);
		if (t != null) {
			User u = new User();
			BeanUtils.copyProperties(t, u);
			return u;
		}
		return null;
	}

	@Override
	public List<String> accessList(Long id) {
		List<String> resourceList = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tuser t = userDao.get(
				"from Tuser t join fetch t.roles role join fetch role.accesss resource where t.id = :id", params);
		if (t != null) {
			Set<Trole> roles = t.getRoles();
			if ((roles != null) && !roles.isEmpty()) {
				for (Trole role : roles) {
					Set<Taccess> accesss = role.getAccesss();
					if ((accesss != null) && !accesss.isEmpty()) {
						for (Taccess resource : accesss) {
							if ((resource != null) && (resource.getUrl() != null)) {
								resourceList.add(resource.getUrl());
							}
						}
					}
				}
			}
		}
		return resourceList;
	}

	@Override
	public List<User> dataGrid(User user, PageFilter ph) {
		List<User> ul = new ArrayList<User>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tuser t ";
		List<Tuser> l = userDao.find(hql + whereHql(user, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tuser t : l) {
			User u = new User();
			BeanUtils.copyProperties(t, u);
			Set<Trole> roles = t.getRoles();
			if ((roles != null) && !roles.isEmpty()) {
				String roleIds = "";
				String roleNames = "";
				boolean b = false;
				for (Trole tr : roles) {
					if (b) {
						roleIds += ",";
						roleNames += ",";
					} else {
						b = true;
					}
					roleIds += tr.getId();
					roleNames += tr.getName();
				}
				u.setRoleIds(roleIds);
				u.setRoleNames(roleNames);
			}
			if (t.getBranch() != null) {
				u.setBranchId(t.getBranch().getId());
				u.setBranchName(t.getBranch().getName());
			}
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(User user, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tuser t ";
		return userDao.count("select count(*) " + hql + whereHql(user, params), params);
	}

	private String whereHql(User user, Map<String, Object> params) {
		String hql = "";
		if (user != null) {
			hql += " where 1=1 ";
			if (user.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + user.getName() + "%%");
			}
			if (user.getCreatedatetimeStart() != null) {
				hql += " and t.createdatetime >= :createdatetimeStart";
				params.put("createdatetimeStart", user.getCreatedatetimeStart());
			}
			if (user.getCreatedatetimeEnd() != null) {
				hql += " and t.createdatetime <= :createdatetimeEnd";
				params.put("createdatetimeEnd", user.getCreatedatetimeEnd());
			}
			if(user.getBranchId()!=null){
				hql += " and t.branch.id ="+user.getBranchId();
			}
		}
		return hql;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd) {
		Tuser u = userDao.get(Tuser.class, sessionInfo.getId());
		if (u.getPassword().equalsIgnoreCase(MD5Util.md5(oldPwd))) {// 说明原密码输入正确
			u.setPassword(MD5Util.md5(pwd));
			return true;
		}
		return false;
	}

	@Override
	public User getByLoginName(User user) {
		Tuser t = userDao.get("from Tuser t  where t.loginname = '"+user.getLoginname()+"'");
		User u = new User();
		if(t!=null){
			BeanUtils.copyProperties(t, u);
		}else{
			return null;
		}
		return u;
	}
}
