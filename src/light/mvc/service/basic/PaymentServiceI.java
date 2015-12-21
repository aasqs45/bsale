package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Payment;

public interface PaymentServiceI {

	public void add(Payment payment);

	public void delete(Long id);

	public void edit(Payment payment);

	public Payment get(Long id);

	public List<Payment> dataGrid(Payment bc, PageFilter ph);
	
	public Long count(Payment bc, PageFilter ph);
}
