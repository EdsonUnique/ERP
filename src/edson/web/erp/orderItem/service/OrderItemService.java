package edson.web.erp.orderItem.service;

import edson.web.erp.orderItem.dao.OrderItemDaoInter;
import edson.web.erp.orderItem.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;
import edson.web.erp.utils.publicBean.PageBean;

public class OrderItemService extends BaseService<OrderItem>implements OrderItemServiceInter {

	private OrderItemDaoInter dao;
	
	
	
	public final void setDao(OrderItemDaoInter dao) {
		this.dao = dao;
	}

	@Override
	public void save(OrderItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OrderItem findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public void update(OrderItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(OrderItem t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PageBean<OrderItem> findListByPageBean(Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<OrderItem> queryByCriteriaObjects(
			Map<String, Object> criteriaMap, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem findByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderItem> findListByCriteriaMap(Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		// TODO Auto-generated method stub
		return null;
	}

}