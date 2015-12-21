package light.mvc.controller.basic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.Tree;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Branch;
import light.mvc.service.basic.BranchServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/branch")
public class BranchController extends BaseController {

	@Autowired
	private BranchServiceI branchService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/branch";
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Branch> treeGrid() {
		return branchService.treeGrid();
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		return branchService.tree();
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/branchAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Branch branch) {
		Json j = new Json();
		try {
			branchService.add(branch);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Branch get(Long id) {
		return branchService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Branch o = branchService.get(id);
		request.setAttribute("branch", o);
		return "/basic/branchEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Branch org) throws InterruptedException {
		Json j = new Json();
		try {
			branchService.edit(org);
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
			branchService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
