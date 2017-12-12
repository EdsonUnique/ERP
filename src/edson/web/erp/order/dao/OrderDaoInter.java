package edson.web.erp.order.dao;

import edson.web.erp.order.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;

public interface OrderDaoInter extends BaseDaoInter<Order>{

	List<Order> findByQuery(Map<String, Object> criteriaMap);

	List<Order> findAllCheck();

	List<Order> findAssigned();

}