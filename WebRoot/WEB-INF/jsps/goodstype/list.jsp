<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#query").click(function() {
			$("[name='pageNum']").val(1);
			$("form:first").submit();
		});
	});
	function showMsg(msg,uuid){
		top.$('context-msg').style.display = "block";
		top.$('context-msg-text').innerHTML=msg;
		top.$('hid-action').value="${pageContext.request.contextPath}/goodsType/goodsType_delete?goodsType_id="+uuid;
		top.lock.show();
	}
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品类别管理</span>
		</div>
	</div>
	<div class="content-text">
		<form action="${pageContext.request.contextPath}/goodsType/goodsType_query" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="68" height="30">&nbsp;</td>
						<td width="123">&nbsp;</td>
						<td width="62">供应商:</td>
						<td width="142">
							<select class="kuan" name="supplier.supplier_id">
								<option value="">----请-选-择----</option>
								<s:iterator value="suppliers" var="supp">
									<option value="${supp.supplier_id }">${supp.name }</option>
								</s:iterator>
							</select>
						</td>
						<td width="60">类别名称:</td>
						<td width="149"><input type="text" size="18" name="name"/></td>
						<td width="70"><a id="query"> <img
								src="${pageContext.request.contextPath}/images/can_b_01.gif" border="0" /> </a></td>
						<td width="70"><a href="${pageContext.request.contextPath}/goodsType/goodsType_addOrUpdateUI"><img
								src="${pageContext.request.contextPath}/images/can_b_02.gif" border="0" /> </a></td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(${pageContext.request.contextPath}/images/table_bg.gif) repeat-x;">
						<td width="30%" height="30">供应商</td>
						<td width="30%">类别名称</td>
						<td width="40%">操作</td>
					</tr>
					<s:iterator value="goodsTypes" var="the_gt">
					<tr align="center" bgcolor="#FFFFFF">
						<td width="30%" height="30">${the_gt.name }</td>
						<td>${the_gt.supplier.name }</td>
						<td>
							<img src="${pageContext.request.contextPath}/images/icon_3.gif" /> 
							<span style="line-height:12px; text-align:center;"> 
								<a href="${pageContext.request.contextPath}/goodsType/goodsType_addOrUpdateUI?goodsType_id=${the_gt.goodsType_id}" class="xiu">修改</a> 
							</span> 
							<img src="${pageContext.request.contextPath}/images/icon_04.gif" /> 
							<span style="line-height:12px;text-align:center;"> 
								<a href="javascript:void(0)" class="xiu" onclick="showMsg('是否删除该项数据？','${the_gt.goodsType_id}')">删除</a>
							</span>
						</td>
					</tr>
					</s:iterator>
				</table>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
