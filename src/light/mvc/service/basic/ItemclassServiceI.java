package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Itemclass;

public interface ItemclassServiceI {

	public void add(Itemclass itemclass);

	public void delete(Long id);

	public void edit(Itemclass itemclass);

	public Itemclass get(Long id);

	public List<Itemclass> dataGrid(Itemclass bc, PageFilter ph);
	
	public Long count(Itemclass bc, PageFilter ph);
}
