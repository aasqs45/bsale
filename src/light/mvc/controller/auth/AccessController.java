package light.mvc.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import light.mvc.framework.constant.GlobalConstant;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.SessionInfo;
import light.mvc.framework.tool.Tree;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.auth.Access;
import light.mvc.service.auth.AccessServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/access")
public class AccessController extends BaseController {

	@Autowired
	private AccessServiceI accessService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/admin/access";
	}

	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(GlobalConstant.SESSION_INFO);
		return accessService.tree(sessionInfo);
	}

	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree(boolean flag) {//true获取全部资源,false只获取菜单资源
		return accessService.allTree(flag);
	}

	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Access> treeGrid() {
		return accessService.treeGrid();
	}

	@RequestMapping("/get")
	@ResponseBody
	public Access get(Long id) {
		return accessService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Access r = accessService.get(id);
		request.setAttribute("access", r);
		return "/admin/accessEdit";
	}


	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Access access) throws InterruptedException {
		Json j = new Json();
		try {
			accessService.edit(access);
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
			accessService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/admin/accessAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Access access) {
		Json j = new Json();
		try {
			accessService.add(access);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
