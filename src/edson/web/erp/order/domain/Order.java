package edson.web.erp.order.domain;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.orderItem.domain.OrderItem;
import edson.web.erp.supplier.domain.Supplier;

/**
 * 订单模块
 * @author Edson
 *
 */
public class Order {
	
	private Integer order_id;
	private String orderNum;
	private String createTime;
	private String checkTime;
	private String endTime;
	private Integer orderType;
	private Integer state;
	private Integer totalNum;
	private Double totalPrice;
	
	private Employee creater;
	private Employee checker;
	private Employee completer;
	private Supplier supplier;
	
	private Set<OrderItem>orderItems=new HashSet<OrderItem>();
	
	//类型常量设计
	public static final int ORDER_TYPE_OF_BUY=11;//采购订单
	public static final int ORDER_TYPE_OF_BUY_RETURN=10;//采购订单退货
	public static final int ORDER_TYPE_OF_SALE=21;
	public static final int ORDER_TYPE_OF_SALE_RETURN=20;
	
	public static final int ORDER_STATE_OF_NO_CHECK=0;//未审核
	public static final int ORDER_STATE_OF_PASS=11;//审核通过
	public static final int ORDER_STATE_OF_NO_PASS=10;
	public static final int ORDER_STATE_OF_BUYING=111;//采购中
	public static final int ORDER_STATE_OF_IN_WAREHOUSE=112;//入库中
	public static final int ORDER_STATE_OF_END=199;//结单
	
	public static final Map<Integer,String>orderTypes=new TreeMap<Integer,String>();
	public static final Map<Integer,String>orderStates=new TreeMap<Integer,String>();
	
	static{
		orderTypes.put(ORDER_TYPE_OF_BUY, "采购订单");
		orderTypes.put(ORDER_TYPE_OF_BUY_RETURN, "采购退货订单");
		orderTypes.put(ORDER_TYPE_OF_SALE, "销售订单");
		orderTypes.put(ORDER_TYPE_OF_SALE_RETURN, "销售退货订单");
		
		orderStates.put(ORDER_STATE_OF_NO_CHECK, "未审核");
		orderStates.put(ORDER_STATE_OF_PASS, "通过");
		orderStates.put(ORDER_STATE_OF_NO_PASS, "驳回");
		orderStates.put(ORDER_STATE_OF_BUYING, "采购中");
		orderStates.put(ORDER_STATE_OF_IN_WAREHOUSE, "入库中");
		orderStates.put(ORDER_STATE_OF_END, "结单");
	}
	//视图值设计
	private String orderType_view;
	private String state_view;
	
	
	
	public final Integer getOrder_id() {
		return order_id;
	}
	public final void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public final String getOrderNum() {
		return orderNum;
	}
	public final void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public final String getCreateTime() {
		return createTime;
	}
	public final void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public final String getCheckTime() {
		return checkTime;
	}
	public final void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public final String getEndTime() {
		return endTime;
	}
	public final void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public final Integer getOrderType() {
		return orderType;
	}
	public final void setOrderType(Integer orderType) {
		this.orderType = orderType;
		if(orderType!=null)
		orderType_view=orderTypes.get(orderType);
	}
	public final Integer getState() {
		return state;
	}
	public final void setState(Integer state) {
		this.state = state;
		if(state!=null)
		state_view=orderStates.get(state);
	}
	public final Integer getTotalNum() {
		return totalNum;
	}
	public final void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public final Double getTotalPrice() {
		return totalPrice;
	}
	public final void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public final Employee getCreater() {
		return creater;
	}
	public final void setCreater(Employee creater) {
		this.creater = creater;
	}
	public final Employee getChecker() {
		return checker;
	}
	public final void setChecker(Employee checker) {
		this.checker = checker;
	}
	public final Employee getCompleter() {
		return completer;
	}
	public final void setCompleter(Employee completer) {
		this.completer = completer;
	}
	public final Supplier getSupplier() {
		return supplier;
	}
	public final void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public final Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public final void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public final String getOrderType_view() {
		return orderType_view;
	}
	public final String getState_view() {
		return state_view;
	}
	
	
	
	

}
