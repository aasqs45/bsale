<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="${ctx}/static/style/images/index/favicon.png" />
<!-- 引入my97日期时间控件 -->
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<!-- 引入jQuery -->
<script src="${ctx}/static/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>

<!-- 引入EasyUI -->
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/static/easyui1.4/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css" type="text/css">
<script type="text/javascript" src="${ctx}/static/easyui1.4/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/static/easyui1.4/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<!-- 扩展EasyUI -->
<script type="text/javascript" src="${ctx}/static/extEasyUI.js" charset="utf-8"></script>
<!-- 扩展Jquery -->
<script type="text/javascript" src="${ctx}/static/extJquery.js" charset="utf-8"></script>

<!-- 自定义工具类 -->
<script type="text/javascript" src="${ctx}/static/lightmvc.js" charset="utf-8"></script>

<!-- 扩展EasyUI图标 -->
<link rel="stylesheet" href="${ctx}/static/style/lightmvc.css" type="text/css">


<script type="text/javascript">
$(window).load(function(){
	$("#loading").fadeOut();
});
var _ROOT_PATH = "${pageContext.request.contextPath}";
</script>
