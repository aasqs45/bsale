package light.mvc.controller.basic;


import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.constant.GlobalConstant;
import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Employee;
import light.mvc.service.basic.EmployeeServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeServiceI employeeService;
	
	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		request.setAttribute("employeetypeJson",JSON.toJSONString(""));
		return "/basic/employee";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Employee employee, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(employeeService.dataGrid(employee, ph));
		grid.setTotal(employeeService.count(employee, ph));
		return grid;
	}
	
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("sexList", GlobalConstant.sexlist);
		return "/basic/employeeAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Employee employee) {
		Json j = new Json();
		try {
			employeeService.add(employee);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Employee get(Long id) {
		return employeeService.get(id);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id) {
		Json j = new Json();
		try {
			employeeService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Employee u = employeeService.get(id);
		request.setAttribute("employee", u);
		request.setAttribute("sexList", GlobalConstant.sexlist);
		return "/basic/employeeEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Employee employee) {
		Json j = new Json();
		try {
			employeeService.edit(employee);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
