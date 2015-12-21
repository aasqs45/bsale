<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	

	$(function() {
	
		$('#branchclassId').combotree({
			url : '${ctx}/branchclass/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto'
		});
		$('#branchclassId').combotree('setValue', 1);
		
		$('#pid').combotree({
			url : '${ctx}/branch/tree',
			parentField : 'pid',
			lines : true,
			panelHeight : 'auto'
		});
		$('#pid').combotree('setValue', 1);
		
		$('#branchAddForm').form({
			url : '${ctx}/branch/add',
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
					parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为branchclass.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
		
	});
</script>
<div style="padding: 3px;">
	<form id="branchAddForm" method="post">
		<table class="grid">
			<tr>
				<td>编号</td>
				<td><input name="code" type="text" placeholder="请输入部门编号" class="easyui-validatebox" data-options="required:true" ></td>
				<td>部门名称</td>
				<td><input name="name" type="text" placeholder="请输入部门名称" class="easyui-validatebox" data-options="required:true" ></td>
				
			</tr>
			<tr>
				<td>排序</td>
				<td><input name="seq"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false" value="0"></td>
				<td>菜单图标</td>
				<td><input  name="icon" value="icon_folder"/></td>
			</tr>
			<tr>
				<td>地址</td>
				<td><input  name="address" style="width: 150px;"/></td>
				<td>类型</td>
				<td><select  name="branchtype" style="width:200px;height: 29px;">
					<option  value="0" selected="selected">机构</option>
					<option value="1">仓库</option>
					<option value="2">部门</option>
				</select></td>
			</tr>
			<tr>
				<td>上级部门</td>
				<td><select id="pid" name="pid" style="width:200px;height: 29px;"></select>
				<!-- <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a> --></td>
				<td>店组</td>
				<td><select id="branchclassId" name="branchclassId" style="width:200px;height: 29px;"></select>
			</tr>
		</table>
	</form>
</div>