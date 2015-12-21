package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Item;
import light.mvc.service.basic.ItemServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

	@Autowired
	private ItemServiceI itemService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/item";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Item bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(itemService.dataGrid(bc, ph));
		grid.setTotal(itemService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/itemAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Item item) {
		Json j = new Json();
		try {
			itemService.add(item);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Item get(Long id) {
		return itemService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Item o = itemService.get(id);
		request.setAttribute("item", o);
		return "/basic/itemEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Item item) throws InterruptedException {
		Json j = new Json();
		try {
			itemService.edit(item);
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
			itemService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
