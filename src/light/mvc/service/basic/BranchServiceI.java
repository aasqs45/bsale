package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.Tree;
import light.mvc.pageModel.basic.Branch;


public interface BranchServiceI {

	public List<Branch> treeGrid();

	public void add(Branch branch);

	public void delete(Long id);

	public void edit(Branch branch);

	public Branch get(Long id);

	public List<Tree> tree();
}
