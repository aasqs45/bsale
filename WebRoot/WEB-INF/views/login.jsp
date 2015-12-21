<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<script>

	var sessionInfo_userId = '${sessionInfo.id}';
	if (sessionInfo_userId) {//如果登录,直接跳转到index页面
		window.location.href='${ctx}/admin/index';
	}
		
	$(function() {
		
		$('#loginform').form({
		    url:'${ctx}/admin/login',
		    onSubmit : function() {
		    	progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid){
					progressClose();
				}
				return isValid;
			},
		    success:function(result){
		    	result = $.parseJSON(result);
		    	progressClose();
		    	if (result.success) {
		    		window.location.href='${ctx}/admin/index';
		    	}else{
		    		$.messager.show({
		    			title:'提示',
		    			msg:'<div class="light-info"><div class="light-tip icon-tip"></div><div>用户名或密码错误</div></div>',
		    			showType:'show'
		    		});
		    	}
		    }
		});
	});
	function submitForm(){
		$('#loginform').submit();
	}
	
	function clearForm(){
		$('#loginform').form('clear');
	}
	
	function enterlogin(){
		if (event.keyCode == 13){
			document.getElementById('login').focus(); 
        	event.returnValue=false;
        	event.cancel = true;
        	$('#loginform').submit();
    	}
	}
	
</script>
</head>
<body onkeydown="enterlogin();" style="background-color: #00aff0">
	<div align="center" style="padding:60px 0 0 0">
	<div   style="width:320px;height:320px; " >
		<div  align="center" ><img style="width:320px;height:200px;" src="${ctx}/static/style/images/index/logo.png" /></div>
		<div style="padding:10px 10px 10px 10px" align="center" >
	    <form id="loginform"  method="post">
	    	<div style="margin-bottom:10px">
    	    	<input class="easyui-textbox" name="loginname" style="width:100%;height:40px;padding:12px" data-options="prompt:'输入用户名',iconCls:'icon_login',iconWidth:38">
	       	</div>
            <div style="margin-bottom:20px">
            	<input class="easyui-textbox" type="password" name="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入密码',iconCls:'',iconWidth:38">
	    	</div>
	    	<div>
	    		<a id="login" onclick="submitForm()" href="javascript:void(0)" class="easyui-linkbutton"  style="padding:5px 0px;width:100%;border-color: #0b7daf;background-color: #0b7daf"><span style="font-size:14px;">登 录</span></a>
			</div>
	    </form>
	    </div>
	</div>
	</div>
	
	<!--[if lte IE 7]>
	<div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
	<a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
	<![endif]-->
	
	<style>
	/*ie6提示*/
	#ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
	#ie6-warning p{width:960px;margin:0 auto;}
	</style>
	<script>
	/* jQuery(function ($) {
	 if ( jQuery.browser.msie && ( jQuery.browser.version == "6.0" )&& ( jQuery.browser.version == "7.0" ) && !jQuery.support.style ){
	  jQuery('#ie6-warning').css({'top':jQuery(this).scrollTop()+'px'});
	 }
	}); */
	</script>
	</body>
</html>