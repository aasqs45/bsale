package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.Tree;
import light.mvc.model.basic.Tbranch;
import light.mvc.model.basic.Tbranchclass;
import light.mvc.pageModel.basic.Branchclass;
import light.mvc.service.basic.BranchclassServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchclassServiceImpl implements BranchclassServiceI {
	

	@Autowired
	private BaseDaoI<Tbranchclass> branchclassDao;


	@Override
	public void add(Branchclass org) {
		Tbranchclass t = new Tbranchclass();
		BeanUtils.copyProperties(org, t);
		branchclassDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tbranchclass t = branchclassDao.get(Tbranchclass.class, id);
		branchclassDao.delete(t);
	}


	@Override
	public void edit(Branchclass r) {
		Tbranchclass t = branchclassDao.get(Tbranchclass.class, r.getId());
		t.setName(r.getName());
		t.setCode(r.getCode());
		branchclassDao.update(t);
	}

	@Override
	public Branchclass get(Long id) {
		Tbranchclass t = branchclassDao.get(Tbranchclass.class, id);
		Branchclass r = new Branchclass();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Branchclass> dataGrid(Branchclass bc, PageFilter ph) {
		List<Branchclass> list = new ArrayList<Branchclass>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tbranchclass t ";
		List<Tbranchclass> l = branchclassDao.find(hql + whereHql(bc, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tbranchclass t : l) {
			Branchclass b = new Branchclass();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Branchclass bc, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tbranchclass t ";
		return branchclassDao.count("select count(*) " + hql + whereHql(bc, params), params);
	}

	private String whereHql(Branchclass bc, Map<String, Object> params) {
		String hql = "";
		if (bc != null) {
			hql += " where 1=1 ";
			if (bc.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + bc.getName() + "%%");
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
	public List<Tree> tree() {
		List<Tbranchclass> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = branchclassDao.find("from Tbranchclass t");

		if ((l != null) && (l.size() > 0)) {
			for (Tbranchclass r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId().toString());
				tree.setText(r.getName());
				lt.add(tree);
			}
		}
		return lt;
	}


}
