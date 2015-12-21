package light.mvc.service.auth.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.SessionInfo;
import light.mvc.framework.tool.Tree;
import light.mvc.model.auth.Taccess;
import light.mvc.pageModel.auth.Access;
import light.mvc.service.auth.AccessServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessServiceI {

	@Autowired
	private BaseDaoI<Taccess> accessDao;

	@Override
	public List<Access> treeGrid() {
		List<Access> lr = new ArrayList<Access>();
		List<Taccess> l = accessDao
				.find("select distinct t from Taccess t left join fetch t.access  order by t.seq");
		if ((l != null) && (l.size() > 0)) {
			for (Taccess t : l) {
				Access r = new Access();
				BeanUtils.copyProperties(t, r);
				r.setState(t.getState());
				if (t.getAccess() != null) {
					r.setPid(t.getAccess().getId());
				}
				r.setIconCls(t.getIcon());
				lr.add(r);
			}
		}
		return lr;
	}

	@Override
	public void add(Access r) {
		Taccess t = new Taccess();
		t.setCreatedatetime(new Date());
		t.setDescription(r.getDescription());
		t.setIcon(r.getIcon());
		t.setName(r.getName());
		if ((r.getPid() != null) && !"".equals(r.getPid())) {
			t.setAccess(accessDao.get(Taccess.class, r.getPid()));
		}
		t.setAccesstype(r.getAccesstype());
		t.setSeq(r.getSeq());
		t.setState(r.getState());
		t.setUrl(r.getUrl());
		accessDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Taccess t = accessDao.get(Taccess.class, id);
		del(t);
	}

	private void del(Taccess t) {
		if ((t.getAccesss() != null) && (t.getAccesss().size() > 0)) {
			for (Taccess r : t.getAccesss()) {
				del(r);
			}
		}
		accessDao.delete(t);
	}

	@Override
	public void edit(Access r) {
		Taccess t = accessDao.get(Taccess.class, r.getId());
		t.setDescription(r.getDescription());
		t.setIcon(r.getIcon());
		t.setName(r.getName());
		if ((r.getPid() != null) && !"".equals(r.getPid())) {
			t.setAccess(accessDao.get(Taccess.class, r.getPid()));
		}
		t.setAccesstype(r.getAccesstype());
		t.setSeq(r.getSeq());
		t.setState(r.getState());
		t.setUrl(r.getUrl());
		accessDao.update(t);
	}

	@Override
	public Access get(Long id) {
		Taccess t = accessDao.get(Taccess.class, id);
		Access r = new Access();
		BeanUtils.copyProperties(t, r);
		r.setState(t.getState());
		if (t.getAccess() != null) {
			r.setPid(t.getAccess().getId());
			r.setPname(t.getAccess().getName());
		}
		return r;
	}

	@Override
	public List<Tree> tree(SessionInfo sessionInfo) {
		List<Taccess> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accesstype", 0);// 菜单类型的资源

		if (sessionInfo != null) {
			if("admin".equals(sessionInfo.getLoginname())){
				l = accessDao
						.find("select distinct t from Taccess t  where t.accesstype = :accesstype  order by t.seq",
								params);
			}else{
				params.put("userId", Long.valueOf(sessionInfo.getId()));// 自查自己有权限的资源
				l = accessDao
					.find("select distinct t from Taccess t join fetch t.roles role join role.users user where t.accesstype = :accesstype and user.id = :userId order by t.seq",
							params);
			}
		} else {
			return null;
		}

		if ((l != null) && (l.size() > 0)) {
			for (Taccess r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				if (r.getAccess() != null) {
					tree.setPid(r.getAccess().getId().toString());
				}else{
					tree.setState("closed");
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<Tree> allTree(boolean flag) {
		List<Taccess> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		if(flag){
			l = accessDao.find("select distinct t from Taccess t left join fetch t.access  order by t.seq");
		}else{
			l = accessDao.find("select distinct t from Taccess t left join fetch t.access where t.accesstype =0 order by t.seq");
		}
		if ((l != null) && (l.size() > 0)) {
			for (Taccess r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				if (r.getAccess() != null) {
					tree.setPid(r.getAccess().getId().toString());
				}
				tree.setText(r.getName());
				tree.setIconCls(r.getIcon());
				Map<String, Object> attr = new HashMap<String, Object>();
				attr.put("url", r.getUrl());
				tree.setAttributes(attr);
				lt.add(tree);
			}
		}
		return lt;
	}

	@Override
	public List<String> accessAllList() {
		List<String> accessList = new ArrayList<String>();
		List<Taccess> l = accessDao.find("select distinct t from Taccess t left join fetch t.access  order by t.seq");
		for (int i = 0; i < l.size(); i++) {
			accessList.add(l.get(i).getUrl());
		}
		return accessList;
	}
}
