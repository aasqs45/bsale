package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Tsupinfo;
import light.mvc.pageModel.basic.Supinfo;
import light.mvc.service.basic.SupinfoServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupinfoServiceImpl implements SupinfoServiceI {
	

	@Autowired
	private BaseDaoI<Tsupinfo> supinfoDao;


	@Override
	public void add(Supinfo supinfo) {
		Tsupinfo t = new Tsupinfo();
		BeanUtils.copyProperties(supinfo, t);
		supinfoDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tsupinfo t = supinfoDao.get(Tsupinfo.class, id);
		supinfoDao.delete(t);
	}


	@Override
	public void edit(Supinfo supinfo) {
		Tsupinfo t = supinfoDao.get(Tsupinfo.class, supinfo.getId());
		t.setName(supinfo.getName());
		t.setCode(supinfo.getCode());
		supinfoDao.update(t);
	}

	@Override
	public Supinfo get(Long id) {
		Tsupinfo t = supinfoDao.get(Tsupinfo.class, id);
		Supinfo r = new Supinfo();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Supinfo> dataGrid(Supinfo supinfo, PageFilter ph) {
		List<Supinfo> list = new ArrayList<Supinfo>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tsupinfo t ";
		List<Tsupinfo> l = supinfoDao.find(hql + whereHql(supinfo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tsupinfo t : l) {
			Supinfo b = new Supinfo();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Supinfo supinfo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tsupinfo t ";
		return supinfoDao.count("select count(*) " + hql + whereHql(supinfo, params), params);
	}

	private String whereHql(Supinfo supinfo, Map<String, Object> params) {
		String hql = "";
		if (supinfo != null) {
			hql += " where 1=1 ";
			if (supinfo.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + supinfo.getName() + "%%");
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
