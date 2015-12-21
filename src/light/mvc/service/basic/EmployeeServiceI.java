package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Employee;

public interface EmployeeServiceI {

	public List<Employee> dataGrid(Employee employee, PageFilter ph);

	public Long count(Employee employee, PageFilter ph);

	public void add(Employee employee);

	public void delete(Long id);

	public void edit(Employee employee);

	public Employee get(Long id);


}
