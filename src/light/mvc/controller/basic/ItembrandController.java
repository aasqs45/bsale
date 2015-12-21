package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Itembrand;
import light.mvc.service.basic.ItembrandServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/itembrand")
public class ItembrandController extends BaseController {

	@Autowired
	private ItembrandServiceI itembrandService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/itembrand";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Itembrand bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(itembrandService.dataGrid(bc, ph));
		grid.setTotal(itembrandService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/itembrandAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Itembrand itembrand) {
		Json j = new Json();
		try {
			itembrandService.add(itembrand);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Itembrand get(Long id) {
		return itembrandService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Itembrand o = itembrandService.get(id);
		request.setAttribute("itembrand", o);
		return "/basic/itembrandEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Itembrand itembrand) throws InterruptedException {
		Json j = new Json();
		try {
			itembrandService.edit(itembrand);
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
			itembrandService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
