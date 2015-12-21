package light.mvc.controller.auth;


import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.constant.GlobalConstant;
import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.SessionInfo;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.auth.User;
import light.mvc.service.auth.UserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceI userService;
	
	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		request.setAttribute("usertypeJson",JSON.toJSONString(""));
		return "/admin/user";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(User user, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(userService.dataGrid(user, ph));
		grid.setTotal(userService.count(user, ph));
		return grid;
	}
	
	
	@RequestMapping("/editPwdPage")
	public String editPwdPage(HttpServletRequest request) {
		return "/admin/userEditPwd";
	}
	
	@RequestMapping("/editUserPwd")
	@ResponseBody
	public Json editUserPwd(HttpServletRequest request,String oldPwd, String pwd) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		Json j = new Json();
		try {
			userService.editUserPwd(sessionInfo, oldPwd, pwd);
			j.setSuccess(true);
			j.setMsg("密码修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("sexList", GlobalConstant.sexlist);
		return "/admin/userAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(User user) {
		Json j = new Json();
		User u = userService.getByLoginName(user);
		if(u!=null){
			j.setMsg("用户名已存在!");
		}else{
			try {
				userService.add(user);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			
		}
		return j;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public User get(Long id) {
		return userService.get(id);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id) {
		Json j = new Json();
		try {
			userService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		User u = userService.get(id);
		request.setAttribute("user", u);
		request.setAttribute("sexList", GlobalConstant.sexlist);
		return "/admin/userEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(User user) {
		Json j = new Json();
		try {
			userService.edit(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
