package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Supclass;

public interface SupclassServiceI {

	public void add(Supclass supclass);

	public void delete(Long id);

	public void edit(Supclass supclass);

	public Supclass get(Long id);

	public List<Supclass> dataGrid(Supclass sc, PageFilter ph);
	
	public Long count(Supclass sc, PageFilter ph);
}
