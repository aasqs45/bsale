package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Cusinfo;

public interface CusinfoServiceI {

	public void add(Cusinfo cusinfo);

	public void delete(Long id);

	public void edit(Cusinfo cusinfo);

	public Cusinfo get(Long id);

	public List<Cusinfo> dataGrid(Cusinfo cusinfo, PageFilter ph);
	
	public Long count(Cusinfo cusinfo, PageFilter ph);
}
