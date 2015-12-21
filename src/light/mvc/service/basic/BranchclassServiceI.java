package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.framework.tool.Tree;
import light.mvc.pageModel.basic.Branchclass;

public interface BranchclassServiceI {

	public void add(Branchclass branchclass);

	public void delete(Long id);

	public void edit(Branchclass branchclass);

	public Branchclass get(Long id);

	public List<Branchclass> dataGrid(Branchclass bc, PageFilter ph);
	
	public Long count(Branchclass bc, PageFilter ph);

	public List<Tree> tree();
}
