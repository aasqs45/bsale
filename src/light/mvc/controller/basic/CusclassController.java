package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Cusclass;
import light.mvc.service.basic.CusclassServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cusclass")
public class CusclassController extends BaseController {

	@Autowired
	private CusclassServiceI cusclassService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/cusclass";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Cusclass cusclass, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(cusclassService.dataGrid(cusclass, ph));
		grid.setTotal(cusclassService.count(cusclass, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/cusclassAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Cusclass cusclass) {
		Json j = new Json();
		try {
			cusclassService.add(cusclass);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Cusclass get(Long id) {
		return cusclassService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Cusclass o = cusclassService.get(id);
		request.setAttribute("cusclass", o);
		return "/basic/cusclassEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Cusclass cusclass) throws InterruptedException {
		Json j = new Json();
		try {
			cusclassService.edit(cusclass);
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
			cusclassService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
