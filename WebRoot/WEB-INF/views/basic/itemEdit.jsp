<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	$(function() {
		
		$('#itemEditForm').form({
			url : '${ctx}/item/edit',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="itemEditForm" method="post">
			<table class="grid">
				<tr>
					<td>名称</td>
					<td><input name="id" type="hidden"  value="${item.id}">
					<input name="name" type="text" placeholder="请输入角色名称" class="easyui-validatebox" data-options="required:true" value="${item.name}"></td>
				</tr>
				<tr>
					<td>编码</td>
					<td ><input type="text" value="${item.id}" /></td>
				</tr>
			</table>
		</form>
	</div>
</div>