package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Tcusinfo;
import light.mvc.pageModel.basic.Cusinfo;
import light.mvc.service.basic.CusinfoServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CusinfoServiceImpl implements CusinfoServiceI {
	

	@Autowired
	private BaseDaoI<Tcusinfo> cusinfoDao;


	@Override
	public void add(Cusinfo cusinfo) {
		Tcusinfo t = new Tcusinfo();
		BeanUtils.copyProperties(cusinfo, t);
		cusinfoDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tcusinfo t = cusinfoDao.get(Tcusinfo.class, id);
		cusinfoDao.delete(t);
	}


	@Override
	public void edit(Cusinfo r) {
		Tcusinfo t = cusinfoDao.get(Tcusinfo.class, r.getId());
		t.setName(r.getName());
		t.setCode(r.getCode());
		cusinfoDao.update(t);
	}

	@Override
	public Cusinfo get(Long id) {
		Tcusinfo t = cusinfoDao.get(Tcusinfo.class, id);
		Cusinfo r = new Cusinfo();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Cusinfo> dataGrid(Cusinfo cusinfo, PageFilter ph) {
		List<Cusinfo> list = new ArrayList<Cusinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tcusinfo t ";
		List<Tcusinfo> l = cusinfoDao.find(hql + whereHql(cusinfo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tcusinfo t : l) {
			Cusinfo b = new Cusinfo();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Cusinfo cusinfo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tcusinfo t ";
		return cusinfoDao.count("select count(*) " + hql + whereHql(cusinfo, params), params);
	}

	private String whereHql(Cusinfo cusinfo, Map<String, Object> params) {
		String hql = "";
		if (cusinfo != null) {
			hql += " where 1=1 ";
			if (cusinfo.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + cusinfo.getName() + "%%");
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
