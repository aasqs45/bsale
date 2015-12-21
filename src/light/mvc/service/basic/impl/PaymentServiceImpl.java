package light.mvc.service.basic.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Tpayment;
import light.mvc.pageModel.basic.Payment;
import light.mvc.service.basic.PaymentServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentServiceI {
	

	@Autowired
	private BaseDaoI<Tpayment> paymentDao;


	@Override
	public void add(Payment org) {
		Tpayment t = new Tpayment();
		BeanUtils.copyProperties(org, t);
		paymentDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Tpayment t = paymentDao.get(Tpayment.class, id);
		paymentDao.delete(t);
	}


	@Override
	public void edit(Payment payment) {
		Tpayment t = paymentDao.get(Tpayment.class, payment.getId());
		t.setName(payment.getName());
		t.setDescription(payment.getDescription());
		paymentDao.update(t);
	}

	@Override
	public Payment get(Long id) {
		Tpayment t = paymentDao.get(Tpayment.class, id);
		Payment r = new Payment();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<Payment> dataGrid(Payment bc, PageFilter ph) {
		List<Payment> list = new ArrayList<Payment>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tpayment t ";
		List<Tpayment> l = paymentDao.find(hql + whereHql(bc, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Tpayment t : l) {
			Payment b = new Payment();
			BeanUtils.copyProperties(t, b);
			list.add(b);
		}     
		return list;
	}
	
	@Override
	public Long count(Payment payment, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Tpayment t ";
		return paymentDao.count("select count(*) " + hql + whereHql(payment, params), params);
	}

	private String whereHql(Payment bc, Map<String, Object> params) {
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
