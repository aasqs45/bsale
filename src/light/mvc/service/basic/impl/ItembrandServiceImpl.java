package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Titembrand;
import light.mvc.pageModel.basic.Itembrand;
import light.mvc.service.basic.ItembrandServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItembrandServiceImpl implements ItembrandServiceI {
	

	@Autowired
	private BaseDaoI<Titembrand> itembrandDao;


	@Override
	public void add(Itembrand ic) {
		Titembrand t = new Titembrand();
		BeanUtils.copyProperties(ic, t);
		itembrandDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Titembrand t = itembrandDao.get(Titembrand.class, id);
		itembrandDao.delete(t);
	}


	@Override
	public void edit(Itembrand r) {
		Titembrand t = itembrandDao.get(Titembrand.class, r.getId());
		t.setName(r.getName());
		t.setCode(r.getCode());
		itembrandDao.update(t);
	}

	@Override
	public Itembrand get(Long id) {
		Titembrand t = itembrandDao.get(Titembrand.class, id);
		Itembrand r = new Itembrand();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Itembrand> dataGrid(Itembrand bc, PageFilter ph) {
		List<Itembrand> list = new ArrayList<Itembrand>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titembrand t ";
		List<Titembrand> l = itembrandDao.find(hql + whereHql(bc, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Titembrand t : l) {
			Itembrand b = new Itembrand();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Itembrand bc, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titembrand t ";
		return itembrandDao.count("select count(*) " + hql + whereHql(bc, params), params);
	}

	private String whereHql(Itembrand bc, Map<String, Object> params) {
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
