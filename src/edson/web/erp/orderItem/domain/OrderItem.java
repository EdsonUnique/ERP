package edson.web.erp.orderItem.domain;

import edson.web.erp.goods.domain.Goods;
import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.order.domain.Order;

public class OrderItem {
	
	private Integer orderItem_id;
	private double price;
	private int num;
	private double sum;
	private Integer surplus;//入库剩余量
	
	private Goods goods;
	private Order order;
	private GoodsType goodsType;
	
	
	public final Integer getSurplus() {
		return surplus;
	}
	public final void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}
	public final GoodsType getGoodsType() {
		return goodsType;
	}
	public final void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	public final Integer getOrderItem_id() {
		return orderItem_id;
	}
	public final void setOrderItem_id(Integer orderItem_id) {
		this.orderItem_id = orderItem_id;
	}
	public final double getPrice() {
		return price;
	}
	public final void setPrice(double price) {
		this.price = price;
	}
	public final int getNum() {
		return num;
	}
	public final void setNum(int num) {
		this.num = num;
	}
	public final double getSum() {
		return sum;
	}
	public final void setSum(double sum) {
		this.sum = sum;
	}
	public final Goods getGoods() {
		return goods;
	}
	public final void setGoods(Goods goods) {
		this.goods = goods;
	}
	public final Order getOrder() {
		return order;
	}
	public final void setOrder(Order order) {
		this.order = order;
	}
	
	 
	
	
}
