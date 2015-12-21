package light.mvc.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.Tree;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.auth.Role;
import light.mvc.service.auth.RoleServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleServiceI roleService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/role";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Role role, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(roleService.dataGrid(role, ph));
		grid.setTotal(roleService.count(role, ph));
		return grid;
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree() {
		return roleService.tree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/admin/roleAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Role role) {
		Json j = new Json();
		try {
			roleService.add(role);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id) {
		Json j = new Json();
		try {
			roleService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Role get(Long id)  {
		return roleService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		Role r = roleService.get(id);
		request.setAttribute("role", r);
		return "/admin/roleEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Role role) {
		Json j = new Json();
		try {
			roleService.edit(role);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/grantPage")
	public String grantPage(HttpServletRequest request, Long id) {
		Role r = roleService.get(id);
		request.setAttribute("role", r);
		return "/admin/roleGrant";
	}

	@RequestMapping("/grant")
	@ResponseBody
	public Json grant(Role role) {
		Json j = new Json();
		try {
			roleService.grant(role);
			j.setMsg("授权成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
