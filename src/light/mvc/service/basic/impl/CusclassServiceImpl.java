package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Tcusclass;
import light.mvc.pageModel.basic.Cusclass;
import light.mvc.service.basic.CusclassServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusclassServiceImpl implements CusclassServiceI {
	

	@Autowired
	private BaseDaoI<Tcusclass> cusclassDao;


	@Override
	public void add(Cusclass cusclass) {
		Tcusclass t = new Tcusclass();
		BeanUtils.copyProperties(cusclass, t);
		cusclassDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tcusclass t = cusclassDao.get(Tcusclass.class, id);
		cusclassDao.delete(t);
	}


	@Override
	public void edit(Cusclass cusclass) {
		Tcusclass t = cusclassDao.get(Tcusclass.class, cusclass.getId());
		t.setName(cusclass.getName());
		t.setCode(cusclass.getCode());
		cusclassDao.update(t);
	}

	@Override
	public Cusclass get(Long id) {
		Tcusclass t = cusclassDao.get(Tcusclass.class, id);
		Cusclass r = new Cusclass();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Cusclass> dataGrid(Cusclass cusclass, PageFilter ph) {
		List<Cusclass> list = new ArrayList<Cusclass>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tcusclass t ";
		List<Tcusclass> l = cusclassDao.find(hql + whereHql(cusclass, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tcusclass t : l) {
			Cusclass b = new Cusclass();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Cusclass cusclass, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tcusclass t ";
		return cusclassDao.count("select count(*) " + hql + whereHql(cusclass, params), params);
	}

	private String whereHql(Cusclass bc, Map<String, Object> params) {
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


}
