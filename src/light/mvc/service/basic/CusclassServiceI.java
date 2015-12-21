package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Cusclass;

public interface CusclassServiceI {

	public void add(Cusclass cusclass);

	public void delete(Long id);

	public void edit(Cusclass cusclass);

	public Cusclass get(Long id);

	public List<Cusclass> dataGrid(Cusclass ib, PageFilter ph);
	
	public Long count(Cusclass ib, PageFilter ph);
}
