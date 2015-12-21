package light.mvc.controller.auth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import light.mvc.framework.constant.GlobalConstant;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.SessionInfo;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.auth.User;
import light.mvc.service.auth.AccessServiceI;
import light.mvc.service.auth.UserServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private AccessServiceI accessService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "/index";
		}
		return "/login";
	}

	@ResponseBody
	@RequestMapping("/login")
	public Json login(User user, HttpSession session) {
		Json j = new Json();
		User sysuser = userService.login(user);
		if (sysuser != null) {
			j.setSuccess(true);
			j.setMsg("登陆成功！");

			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo.setId(sysuser.getId());
			sessionInfo.setLoginname(sysuser.getLoginname());
			sessionInfo.setName(sysuser.getName());
			sessionInfo.setAccessList(userService.accessList(sysuser.getId()));
			sessionInfo.setAccessAllList(accessService.accessAllList());
			session.setAttribute(GlobalConstant.SESSION_INFO, sessionInfo);
		}else{
			j.setMsg("用户名或密码错误!");
		}
		return j;
	}

	@ResponseBody
	@RequestMapping("/logout")
	public Json logout(HttpSession session) {
		Json j = new Json();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		j.setMsg("注销成功！");
		return j;
	}
	
	@RequestMapping("/basic")
	public String basic(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "menu/basic";
		}
		return "/login";
	}
	@RequestMapping("/purchase")
	public String purchase(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "menu/purchase";
		}
		return "/login";
	}
	@RequestMapping("/sale")
	public String sale(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "menu/sale";
		}
		return "/login";
	}
	@RequestMapping("/wholesale")
	public String wholesale(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "menu/wholesale";
		}
		return "/login";
	}
	@RequestMapping("/stock")
	public String stock(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "menu/stock";
		}
		return "/login";
	}
	@RequestMapping("/vip")
	public String vip(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			System.out.println("================");
			return "menu/vip";
		}
		return "/login";
	}
	@RequestMapping("/report")
	public String report(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			System.out.println("================");
			return "menu/report";
		}
		return "/login";
	}
	@RequestMapping("/setting")
	public String setting(HttpServletRequest request) {
		System.out.println("================");
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		if ((sessionInfo != null) && (sessionInfo.getId() != null)) {
			return "menu/setting";
		}
		return "/login";
	}

}
