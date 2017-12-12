package edson.web.erp.orderItem.action;

import java.util.*;

import edson.web.erp.orderItem.service.OrderItemServiceInter;
import edson.web.erp.utils.base.*;

public class OrderItemAction extends BaseAction{
	
	private OrderItemServiceInter service;

	public final void setService(OrderItemServiceInter service) {
		this.service = service;
	}
	

}