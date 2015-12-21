package light.mvc.service.auth.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.Tree;
import light.mvc.model.auth.Taccess;
import light.mvc.model.auth.Trole;
import light.mvc.pageModel.auth.Role;
import light.mvc.service.auth.RoleServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleServiceI {

	@Autowired
	private BaseDaoI<Trole> roleDao;

	@Autowired
	private BaseDaoI<Taccess> accessDao;

	@Override
	public void add(Role r) {
		Trole t = new Trole();
		t.setIsdefault(1);
		t.setName(r.getName());
		t.setSeq(r.getSeq());
		t.setDescription(r.getDescription());
		roleDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Trole t = roleDao.get(Trole.class, id);
		if(t.getUsers().size()>0){
			throw new ServiceException("该角色已被使用");
		}
		roleDao.delete(t);
	}

	@Override
	public void edit(Role r) {
		Trole t = roleDao.get(Trole.class, r.getId());
		t.setDescription(r.getDescription());
		t.setName(r.getName());
		t.setSeq(r.getSeq());
		roleDao.update(t);
	}

	@Override
	public Role get(Long id) {
		Trole t = roleDao.get(Trole.class, id);
		Role r = new Role();
		r.setDescription(t.getDescription());
		r.setId(t.getId());
		r.setIsdefault(t.getIsdefault());
		r.setName(t.getName());
		r.setSeq(t.getSeq());
		Set<Taccess> s = t.getAccesss();
		if ((s != null) && !s.isEmpty()) {
			boolean b = false;
			String ids = "";
			String names = "";
			for (Taccess tr : s) {
				if (b) {
					ids += ",";
					names += ",";
				} else {
					b = true;
				}
				ids += tr.getId();
				names += tr.getName();
			}
			r.setAccessIds(ids);
			r.setAccessNames(names);
		}
		return r;
	}

	@Override
	public List<Role> dataGrid(Role role, PageFilter ph) {
		List<Role> ul = new ArrayList<Role>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Trole t ";
		List<Trole> l = roleDao.find(hql + whereHql(role, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Trole t : l) {
			Role u = new Role();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(Role role, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Trole t ";
		return roleDao.count("select count(*) " + hql + whereHql(role, params), params);
	}

	private String whereHql(Role role, Map<String, Object> params) {
		String hql = "";
		if (role != null) {
			hql += " where 1=1 ";
			if (role.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + role.getName() + "%%");
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
	public void grant(Role role) {
		Trole t = roleDao.get(Trole.class, role.getId());
		if ((role.getAccessIds() != null) && !role.getAccessIds().equalsIgnoreCase("")) {
			String ids = "";
			boolean b = false;
			for (String id : role.getAccessIds().split(",")) {
				if (b) {
					ids += ",";
				} else {
					b = true;
				}
				ids += id;
			}
			t.setAccesss(new HashSet<Taccess>(accessDao.find("select distinct t from Taccess t where t.id in ("
					+ ids + ")")));
		} else {
			t.setAccesss(null);
		}
	}

	@Override
	public List<Tree> tree() {
		List<Trole> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = roleDao.find("select distinct t from Trole t order by t.seq");

		if ((l != null) && (l.size() > 0)) {
			for (Trole r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}
}
