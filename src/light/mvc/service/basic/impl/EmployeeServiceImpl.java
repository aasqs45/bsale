package light.mvc.service.basic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.framework.tool.PageFilter;
import light.mvc.model.basic.Temployee;
import light.mvc.pageModel.basic.Employee;
import light.mvc.service.basic.EmployeeServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeServiceI {

	@Autowired
	private BaseDaoI<Temployee> employeeDao;

	@Override
	public void add(Employee u) {
		Temployee t = new Temployee();
		BeanUtils.copyProperties(u, t);
		employeeDao.save(t);
	}

	@Override
	public void delete(Long id) {
		Temployee t = employeeDao.get(Temployee.class, id);
		del(t);
	}

	private void del(Temployee t) {
		employeeDao.delete(t);
	}

	@Override
	public void edit(Employee employee) {
		Temployee t = employeeDao.get(Temployee.class,employee.getId());
		t.setAge(employee.getAge());
		t.setName(employee.getName());
		t.setSex(employee.getSex());
		employeeDao.update(t);
	}

	@Override
	public Employee get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Temployee t = employeeDao.get("from Temployee t where t.id = :id", params);
		Employee u = new Employee();
		BeanUtils.copyProperties(t, u);
		
		return u;
	}



	@Override
	public List<Employee> dataGrid(Employee employee, PageFilter ph) {
		List<Employee> ul = new ArrayList<Employee>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Temployee t ";
		List<Temployee> l = employeeDao.find(hql + whereHql(employee, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (Temployee t : l) {
			Employee u = new Employee();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	@Override
	public Long count(Employee employee, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from Temployee t ";
		return employeeDao.count("select count(*) " + hql + whereHql(employee, params), params);
	}

	private String whereHql(Employee employee, Map<String, Object> params) {
		String hql = "";
		if (employee != null) {
			hql += " where 1=1 ";
			if (employee.getName() != null) {
				hql += " and t.name like :name";
				params.put("name", "%%" + employee.getName() + "%%");
			}
//			if(employee.getBranchId()!=null){
//				hql += " and t.branch.id ="+employee.getBranchId();
//			}
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
