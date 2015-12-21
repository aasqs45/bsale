package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Itemclass;
import light.mvc.service.basic.ItemclassServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/itemclass")
public class ItemclassController extends BaseController {

	@Autowired
	private ItemclassServiceI itemclassService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/itemclass";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Itemclass bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(itemclassService.dataGrid(bc, ph));
		grid.setTotal(itemclassService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/itemclassAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Itemclass itemclass) {
		Json j = new Json();
		try {
			itemclassService.add(itemclass);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Itemclass get(Long id) {
		return itemclassService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Itemclass o = itemclassService.get(id);
		request.setAttribute("itemclass", o);
		return "/basic/itemclassEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Itemclass itemclass) throws InterruptedException {
		Json j = new Json();
		try {
			itemclassService.edit(itemclass);
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
			itemclassService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
