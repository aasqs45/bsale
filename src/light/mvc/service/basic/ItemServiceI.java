package light.mvc.service.basic;

import java.util.List;

import light.mvc.framework.tool.PageFilter;
import light.mvc.pageModel.basic.Item;

public interface ItemServiceI {

	public void add(Item item);

	public void delete(Long id);

	public void edit(Item item);

	public Item get(Long id);

	public List<Item> dataGrid(Item it, PageFilter ph);
	
	public Long count(Item it, PageFilter ph);
}
