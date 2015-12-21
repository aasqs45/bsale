package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Titem;
import light.mvc.pageModel.basic.Item;
import light.mvc.service.basic.ItemServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemServiceI {
	

	@Autowired
	private BaseDaoI<Titem> itemDao;


	@Override
	public void add(Item ic) {
		Titem t = new Titem();
		BeanUtils.copyProperties(ic, t);
		itemDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Titem t = itemDao.get(Titem.class, id);
		itemDao.delete(t);
	}


	@Override
	public void edit(Item r) {
		Titem t = itemDao.get(Titem.class, r.getId());
		t.setName(r.getName());
		itemDao.update(t);
	}

	@Override
	public Item get(Long id) {
		Titem t = itemDao.get(Titem.class, id);
		Item r = new Item();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Item> dataGrid(Item bc, PageFilter ph) {
		List<Item> list = new ArrayList<Item>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titem t ";
		List<Titem> l = itemDao.find(hql + whereHql(bc, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Titem t : l) {
			Item b = new Item();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Item bc, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Titem t ";
		return itemDao.count("select count(*) " + hql + whereHql(bc, params), params);
	}

	private String whereHql(Item bc, Map<String, Object> params) {
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
