<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function() {
		$("#all").click(function() {
			$("[name=resources]:checkbox").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function() {
			$("[name=resources]:checkbox").each(function () {
                $(this).attr("checked", !$(this).attr("checked"));
            });

		});
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">角色管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath}${model.role_id==null?'/role/role_add':'/role/role_update'}" method="post">
  			<div style="border:1px solid #cecece;">
  			<input type="hidden" name="role_id" value="${model.role_id }">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="32%">
				      	<input type="text"size="25" name="name" value="${model.name }"/>
				      </td>
				      <td width="18%" align="center">角色编码</td>
				      <td width="32%">
				      	<input type="text" size="25" name="short_name" value="${model.short_name }"/>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      <s:iterator value="resourcesAll" var="res">
				      	<input name="reserved_id" ${reserved_ids.contains(res.resource_id)?'checked':'' } value="${res.resource_id }" type="checkbox"/>${res.name }
				      </s:iterator>
				      </td>
				    </tr>
				     <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">菜单名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3">
				      	 <s:iterator value="menuAll" var="menu">
				      		<input name="menu_ids" ${menu_ids.contains(menu.menu_id)?'checked':'' } value="${menu.menu_id }" type="checkbox"/>${menu.name }
				      	</s:iterator>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${pageContext.request.contextPath}/images/content_bbg.jpg" /></div>
</div>
