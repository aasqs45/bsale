package light.mvc.framework.constant;

import java.util.HashMap;
import java.util.Map;

public class GlobalConstant {

	public static final String SESSION_INFO = "sessionInfo";

	public static final Integer ENABLE = 0; // 启用
	public static final Integer DISABLE = 1; // 禁用
	
	
	public static final Integer DEFAULT = 0; // 默认
	public static final Integer NOT_DEFAULT = 1; // 非默认
	
	public static final Map sexlist = new HashMap(){{ put("0", "男");  put("1", "女");} };
	public static final Map statelist = new HashMap(){{ put("0", "启用");  put("1", "停用");} };
	
}
