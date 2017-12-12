<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar.js"></script>
<script type="text/javascript">
	$(function(){
		//select  供应商与商品类别之间的联动
		//在供应商select增加onchange事件  
		//发送Ajax请求给struts  
		//后台以json方式返回商品类别信息
		$("#supplier").change(function(){
			//获取被选中的供应商id
			var supp_id=$(this).attr("value");
			//将id通过ajax传给struts  查询所拥有的商品类别
			$.ajax({
				type:"POST",
				url:"goods_ajaxGetGoodsType.action",
				data:"id="+supp_id,
				success: function(d){
					var arr=d.goodsTypesAjax;
					$("#goodsType").empty();//添加另一选项时先清空
     				for(var i=0;i<arr.length;i++){
     					var $op=$("<option value='"+arr[i].goodsType_id+"'>"+arr[i].name+"</option>");
     					$("#goodsType").append($op);

     				}
   				}



			});



		});




	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">商品管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="${pageContext.request.contextPath}${model.goods_id==null?'/goods/goods_add':'/goods/goods_update'}" method="post">
  			<div style="border:1px solid #cecece;">
  			<input type="hidden" name="goods_id" value="${model.goods_id }">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">供&nbsp;应&nbsp;商</td>
				      <td width="32%">
				      		<select id="supplier" style="width:190px" name="supplier.supplier_id">
								<option value="-1">----请-选-择----</option>
								<s:iterator value="suppliersAll"  var="supp">
									<option value="${supp.supplier_id }" ${supp.supplier_id==model.supplier.supplier_id?'selected':''}>${supp.name }</option>
								</s:iterator>
							</select>
				      </td>
				      <td width="18%"align="center">商品类别</td>
				      <td width="32%">
				      		<select id="goodsType" style="width:190px" name="goodsType.goodsType_id">
								<option value="-1">----请-选-择----</option>
								<s:iterator value="goodsTypesAll"  var="gta">
									<option value="${gta.goodsType_id }" ${gta.goodsType_id==model.goodsType.goodsType_id?'selected':''}>${gta.name }</option>
								</s:iterator>
							</select>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">商品名称</td>
				      <td>
				      	<input type="text" size="25" name="name" value="${model.name }"/>
				      </td>
				      <td  align="center">产&nbsp;&nbsp;&nbsp;&nbsp;地</td>
				      <td >
				      	<input type="text" size="25" name="origin" value="${model.origin }"/>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">生产厂家</td>
				      <td>
				      	<input type="text" size="25" name="manufacturer" value="${model.manufacturer }"/>
				      <td align="center">单&nbsp;&nbsp;&nbsp;&nbsp;位</td>
				      <td>
				      	<input type="text" size="25" name="unit" value="${model.unit }"/>
					  </td>
				     </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">进货单价</td>
				      <td>
				      	<input type="text" size="25" name="origin_price" value="${model.origin_price }"/>
					  </td>
				      <td align="center">销售单价</td>
				      <td>
				      	<input type="text" size="25" name="sale_price" value="${model.sale_price }"/>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">体&nbsp;&nbsp;&nbsp;&nbsp;积</td>
				      <td>
				      	<input type="text" size="25" name="volume" value="${model.volume }"/>
					  </td>
				      <td align="center">&nbsp;</td>
				      <td>&nbsp;</td>
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
