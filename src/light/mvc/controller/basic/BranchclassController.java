package light.mvc.controller.basic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.Tree;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Branchclass;
import light.mvc.service.basic.BranchclassServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/branchclass")
public class BranchclassController extends BaseController {

	@Autowired
	private BranchclassServiceI branchclassService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/branchclass";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Branchclass bc, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(branchclassService.dataGrid(bc, ph));
		grid.setTotal(branchclassService.count(bc, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/branchclassAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Branchclass branchclass) {
		Json j = new Json();
		try {
			branchclassService.add(branchclass);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Branchclass get(Long id) {
		return branchclassService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Branchclass o = branchclassService.get(id);
		request.setAttribute("branchclass", o);
		return "/basic/branchclassEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Branchclass branchclass) throws InterruptedException {
		Json j = new Json();
		try {
			branchclassService.edit(branchclass);
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
			branchclassService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		return branchclassService.tree();
	}
}
