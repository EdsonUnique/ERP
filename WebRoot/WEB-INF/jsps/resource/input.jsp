<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">资源管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath}${model.resource_id==null?'/resource/resource_add':'/resource/resource_update'}" method="post">
  			<input type="hidden" name="resource_id" value="${model.resource_id }">
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源名称</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="77" name="name" value="${model.name }"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">资源类别</td>
				      <td>
				      		<select style="width:190px" name="is_action">
								<option value="-1">----请-选-择----</option>
								<option value="0" ${model.is_operation==0?'selected':'' }>URL访问</option>
								<option value="1" ${model.is_operation==1?'selected':'' }>action调用</option>
							</select>
					  </td>
				      <td align="center">操作类别</td>
				      <td>
				      		<select style="width:190px" name="is_operation">
								<option value="-1">----请-选-择----</option>
								<option value="1" ${model.is_operation==1?'selected':'' }>可访问</option>
								<option value="0" ${model.is_operation==0?'selected':'' }>可视</option>
							</select>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">资源值</td>
				      <td width="82%" colspan="3">
				      	<input type="text" size="77" name="path" value="${model.path }"/>
				      </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
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
					    <td><a href="javascript:history.go(-1)"><img src="${pageContext.request.contextPath}/images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="${pageContext.request.contextPath}/images/content_bbg.jpg" /></div>
</div>
