package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Titemclass;
import light.mvc.pageModel.basic.Itemclass;
import light.mvc.service.basic.ItemclassServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemclassServiceImpl implements ItemclassServiceI {
	

	@Autowired
	private BaseDaoI<Titemclass> itemclassDao;


	@Override
	public void add(Itemclass ic) {
		Titemclass t = new Titemclass();
		BeanUtils.copyProperties(ic, t);
		itemclassDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Titemclass t = itemclassDao.get(Titemclass.class, id);
		itemclassDao.delete(t);
	}


	@Override
	public void edit(Itemclass r) {
		Titemclass t = itemclassDao.get(Titemclass.class, r.getId());
		t.setName(r.getName());
		t.setCode(r.getCode());
		itemclassDao.update(t);
	}

	@Override
	public Itemclass get(Long id) {
		Titemclass t = itemclassDao.get(Titemclass.class, id);
		Itemclass r = new Itemclass();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Itemclass> dataGrid(Itemclass bc, PageFilter ph) {
		List<Itemclass> list = new ArrayList<Itemclass>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titemclass t ";
		List<Titemclass> l = itemclassDao.find(hql + whereHql(bc, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Titemclass t : l) {
			Itemclass b = new Itemclass();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Itemclass bc, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titemclass t ";
		return itemclassDao.count("select count(*) " + hql + whereHql(bc, params), params);
	}

	private String whereHql(Itemclass bc, Map<String, Object> params) {
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
