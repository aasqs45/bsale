package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Itemproperty;
import light.mvc.service.basic.ItempropertyServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/itemproperty")
public class ItempropertyController extends BaseController {

	@Autowired
	private ItempropertyServiceI itempropertyService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/itemproperty";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Itemproperty bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(itempropertyService.dataGrid(bc, ph));
		grid.setTotal(itempropertyService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/itempropertyAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Itemproperty itemproperty) {
		Json j = new Json();
		try {
			itempropertyService.add(itemproperty);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Itemproperty get(Long id) {
		return itempropertyService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Itemproperty o = itempropertyService.get(id);
		request.setAttribute("itemproperty", o);
		return "/basic/itempropertyEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Itemproperty itemproperty) throws InterruptedException {
		Json j = new Json();
		try {
			itempropertyService.edit(itemproperty);
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
			itempropertyService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
