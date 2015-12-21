package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Supinfo;

public interface SupinfoServiceI {

	public void add(Supinfo supinfo);

	public void delete(Long id);

	public void edit(Supinfo supinfo);

	public Supinfo get(Long id);

	public List<Supinfo> dataGrid(Supinfo supinfo, PageFilter ph);
	
	public Long count(Supinfo supinfo, PageFilter ph);
}
