package edson.web.erp.order.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edson.web.erp.order.domain.Order;
import edson.web.erp.utils.base.BaseDao;

public class OrderDao extends BaseDao<Order>implements OrderDaoInter {

	@Override
	public List<Order> findByQuery(Map<String, Object> criteriaMap) {
		
		DetachedCriteria dc=DetachedCriteria.forClass(Order.class);
		
		for(Entry entry:criteriaMap.entrySet()){
			
			if(entry.getKey().equals("createTime")){
				dc.add(Restrictions.ge(entry.getKey().toString(), entry.getValue()));
				continue;
			}
			if(entry.getKey().equals("createTime1")){
				dc.add(Restrictions.le("createTime", entry.getValue()));
				continue;
			}
			
			if(entry.getKey().equals("totalNum")){
				dc.add(Restrictions.ge(entry.getKey().toString(), entry.getValue()));
				continue;
			}
			if(entry.getKey().equals("totalNum1")){
				dc.add(Restrictions.le("totalNum", entry.getValue()));
				continue;
			}
			
			if(entry.getKey().equals("totalPrice")){
				dc.add(Restrictions.ge(entry.getKey().toString(), entry.getValue()));
				continue;
			}
			if(entry.getKey().equals("totalPrice1")){
				dc.add(Restrictions.le("totalPrice", entry.getValue()));
				continue;
			}
			
			dc.add(Restrictions.eq(entry.getKey().toString(), entry.getValue()));
			
		}
		
		List<Order>temp=this.getHibernateTemplate().findByCriteria(dc);
		
		return temp.size()>0?temp:null;
	}

	//离线查询 in的使用
	private Integer[] orderStates=new Integer[]{
			Order.ORDER_STATE_OF_BUYING,
			Order.ORDER_STATE_OF_END,
			Order.ORDER_STATE_OF_IN_WAREHOUSE,
			Order.ORDER_STATE_OF_PASS
		};
	public List<Order> findAllCheck() {
		DetachedCriteria dc=DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.in("state", orderStates));
		
		List<Order> temp=this.getHibernateTemplate().findByCriteria(dc);
		
		return temp.size()>0?temp:null;
	}
	
	//离线查询 in的使用
		private Integer[] orderStates2=new Integer[]{
				Order.ORDER_STATE_OF_BUYING,
				Order.ORDER_STATE_OF_END,
				Order.ORDER_STATE_OF_IN_WAREHOUSE,
			};
	public List<Order> findAssigned() {
		DetachedCriteria dc=DetachedCriteria.forClass(Order.class);
		dc.add(Restrictions.in("state", orderStates2));
		
		List<Order> temp=this.getHibernateTemplate().findByCriteria(dc);
		
		return temp.size()>0?temp:null;
	}

}