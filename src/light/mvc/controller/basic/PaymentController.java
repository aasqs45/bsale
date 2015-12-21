package light.mvc.controller.basic;

import javax.servlet.http.HttpServletRequest;

import light.mvc.framework.exception.ServiceException;
import light.mvc.framework.tool.Grid;
import light.mvc.framework.tool.Json;
import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.web.BaseController;
import light.mvc.pageModel.basic.Payment;
import light.mvc.service.basic.PaymentServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/payment")
public class PaymentController extends BaseController {

	@Autowired
	private PaymentServiceI paymentService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/basic/payment";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(Payment payment, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(paymentService.dataGrid(payment, ph));
		grid.setTotal(paymentService.count(payment, ph));
		return grid;
	}

	
	@RequestMapping("/addPage")
	public String addPage() {
		return "/basic/paymentAdd";
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Payment payment) {
		Json j = new Json();
		try {
			paymentService.add(payment);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Payment get(Long id) {
		return paymentService.get(id);
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Long id) {
		Payment o = paymentService.get(id);
		request.setAttribute("payment", o);
		return "/basic/paymentEdit";
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Payment payment) throws InterruptedException {
		Json j = new Json();
		try {
			paymentService.edit(payment);
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
			paymentService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
}
