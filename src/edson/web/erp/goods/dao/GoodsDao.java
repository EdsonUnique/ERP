package edson.web.erp.goods.dao;

import edson.web.erp.goods.domain.*;
import java.util.*;
import java.util.Map.Entry;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.utils.base.*;

public class GoodsDao extends BaseDao<Goods>implements GoodsDaoInter {

	@Override
	public List<Goods> findListByMyCriteriaMap(
			Map<String, Object> criteriaMap) {
		
		DetachedCriteria dc=DetachedCriteria.forClass(Goods.class);
		
		for(Entry entry:criteriaMap.entrySet()){
			if(entry.getKey().equals("sale_price")){
				dc.add(Restrictions.ge(entry.getKey().toString(), entry.getValue()));
				continue;
			}
			
			if(entry.getKey().equals("sale_price1")){
				dc.add(Restrictions.le("sale_price", entry.getValue()));
				continue;
			}
			
			if(entry.getKey().equals("origin_price")){
				dc.add(Restrictions.ge(entry.getKey().toString(), entry.getValue()));
				continue;
			}
			
			if(entry.getKey().equals("origin_price1")){
				dc.add(Restrictions.le("origin_price", entry.getValue()));
				continue;
			}
			
			dc.add(Restrictions.eq(entry.getKey().toString(), entry.getValue()));
				
		}
		
		List<Goods> temp=this.getHibernateTemplate().findByCriteria(dc);
		
		return temp.size()>0?temp:null;
	}

}