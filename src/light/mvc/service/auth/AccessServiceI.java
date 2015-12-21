package light.mvc.service.auth;

import java.util.List;

import light.mvc.framework.tool.SessionInfo;
import light.mvc.framework.tool.Tree;
import light.mvc.pageModel.auth.Access;

public interface AccessServiceI {

	public List<Access> treeGrid();

	public void add(Access resource);

	public void delete(Long id);

	public void edit(Access resource);

	public Access get(Long id);

	public List<Tree> tree(SessionInfo sessionInfo);

	public List<Tree> allTree(boolean flag);

	public List<String> accessAllList();

}
