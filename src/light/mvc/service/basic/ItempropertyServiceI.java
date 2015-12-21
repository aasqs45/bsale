package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Itemproperty;

public interface ItempropertyServiceI {

	public void add(Itemproperty itemproperty);

	public void delete(Long id);

	public void edit(Itemproperty itemproperty);

	public Itemproperty get(Long id);

	public List<Itemproperty> dataGrid(Itemproperty ib, PageFilter ph);
	
	public Long count(Itemproperty ib, PageFilter ph);
}
