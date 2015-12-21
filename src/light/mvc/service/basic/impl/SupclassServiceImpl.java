package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Tsupclass;
import light.mvc.pageModel.basic.Supclass;
import light.mvc.service.basic.SupclassServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupclassServiceImpl implements SupclassServiceI {
	

	@Autowired
	private BaseDaoI<Tsupclass> supclassDao;


	@Override
	public void add(Supclass supclass) {
		Tsupclass t = new Tsupclass();
		BeanUtils.copyProperties(supclass, t);
		supclassDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tsupclass t = supclassDao.get(Tsupclass.class, id);
		supclassDao.delete(t);
	}


	@Override
	public void edit(Supclass supclass) {
		Tsupclass t = supclassDao.get(Tsupclass.class, supclass.getId());
		t.setName(supclass.getName());
		t.setCode(supclass.getCode());
		supclassDao.update(t);
	}

	@Override
	public Supclass get(Long id) {
		Tsupclass t = supclassDao.get(Tsupclass.class, id);
		Supclass r = new Supclass();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Supclass> dataGrid(Supclass supclass, PageFilter ph) {
		List<Supclass> list = new ArrayList<Supclass>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tsupclass t ";
		List<Tsupclass> l = supclassDao.find(hql + whereHql(supclass, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tsupclass t : l) {
			Supclass b = new Supclass();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Supclass supclass, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tsupclass t ";
		return supclassDao.count("select count(*) " + hql + whereHql(supclass, params), params);
	}

	private String whereHql(Supclass supclass, Map<String, Object> params) {
		String hql = "";
		if (supclass != null) {
			hql += " where 1=1 ";
			if (supclass.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + supclass.getName() + "%%");
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


}
