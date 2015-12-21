package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Supclass;
import light.mvc.service.basic.SupclassServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/supclass")
public class SupclassController extends BaseController {

	@Autowired
	private SupclassServiceI supclassService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/supclass";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Supclass bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(supclassService.dataGrid(bc, ph));
		grid.setTotal(supclassService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/supclassAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Supclass supclass) {
		Json j = new Json();
		try {
			supclassService.add(supclass);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Supclass get(Long id) {
		return supclassService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Supclass o = supclassService.get(id);
		request.setAttribute("supclass", o);
		return "/basic/supclassEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Supclass supclass) throws InterruptedException {
		Json j = new Json();
		try {
			supclassService.edit(supclass);
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
			supclassService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
