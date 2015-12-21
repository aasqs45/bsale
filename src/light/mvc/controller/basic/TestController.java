package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Supinfo;
import light.mvc.service.basic.SupinfoServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired
	private SupinfoServiceI supinfoService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/supinfo";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Supinfo bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(supinfoService.dataGrid(bc, ph));
		grid.setTotal(supinfoService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/supinfoAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Supinfo supinfo) {
		Json j = new Json();
		try {
			supinfoService.add(supinfo);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Supinfo get(Long id) {
		return supinfoService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Supinfo o = supinfoService.get(id);
		request.setAttribute("supinfo", o);
		return "/basic/supinfoEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Supinfo supinfo) throws InterruptedException {
		Json j = new Json();
		try {
			supinfoService.edit(supinfo);
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
			supinfoService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
