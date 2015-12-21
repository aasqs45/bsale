package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Titemproperty;
import light.mvc.pageModel.basic.Itemproperty;
import light.mvc.service.basic.ItempropertyServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItempropertyServiceImpl implements ItempropertyServiceI {
	

	@Autowired
	private BaseDaoI<Titemproperty> itempropertyDao;


	@Override
	public void add(Itemproperty ic) {
		Titemproperty t = new Titemproperty();
		BeanUtils.copyProperties(ic, t);
		itempropertyDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Titemproperty t = itempropertyDao.get(Titemproperty.class, id);
		itempropertyDao.delete(t);
	}


	@Override
	public void edit(Itemproperty r) {
		Titemproperty t = itempropertyDao.get(Titemproperty.class, r.getId());
		t.setName(r.getName());
		t.setCode(r.getCode());
		itempropertyDao.update(t);
	}

	@Override
	public Itemproperty get(Long id) {
		Titemproperty t = itempropertyDao.get(Titemproperty.class, id);
		Itemproperty r = new Itemproperty();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Itemproperty> dataGrid(Itemproperty bc, PageFilter ph) {
		List<Itemproperty> list = new ArrayList<Itemproperty>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titemproperty t ";
		List<Titemproperty> l = itempropertyDao.find(hql + whereHql(bc, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Titemproperty t : l) {
			Itemproperty b = new Itemproperty();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Itemproperty bc, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titemproperty t ";
		return itempropertyDao.count("select count(*) " + hql + whereHql(bc, params), params);
	}

	private String whereHql(Itemproperty bc, Map<String, Object> params) {
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
