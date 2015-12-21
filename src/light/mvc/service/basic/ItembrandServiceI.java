package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Itembrand;

public interface ItembrandServiceI {

	public void add(Itembrand itembrand);

	public void delete(Long id);

	public void edit(Itembrand itembrand);

	public Itembrand get(Long id);

	public List<Itembrand> dataGrid(Itembrand ib, PageFilter ph);
	
	public Long count(Itembrand ib, PageFilter ph);
}
