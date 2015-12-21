package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Cusinfo;
import light.mvc.service.basic.CusinfoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cusinfo")
public class CusinfoController extends BaseController {

	@Autowired
	private CusinfoServiceI cusinfoService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/cusinfo";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Cusinfo bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(cusinfoService.dataGrid(bc, ph));
		grid.setTotal(cusinfoService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/cusinfoAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Cusinfo cusinfo) {
		Json j = new Json();
		try {
			cusinfoService.add(cusinfo);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Cusinfo get(Long id) {
		return cusinfoService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Cusinfo o = cusinfoService.get(id);
		request.setAttribute("cusinfo", o);
		return "/basic/cusinfoEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Cusinfo cusinfo) throws InterruptedException {
		Json j = new Json();
		try {
			cusinfoService.edit(cusinfo);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
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
			cusinfoService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
