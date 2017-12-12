package edson.web.erp.menu.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import edson.web.erp.menu.domain.Menu;
import edson.web.erp.utils.base.BaseDao;

public class MenuDao extends BaseDao<Menu>implements MenuDaoInter {

	@Override
	public List<Menu> findListByNotCriteriaMap(
			Map<String, Object> criteriaMap) {
		DetachedCriteria dc=DetachedCriteria.forClass(Menu.class);
		
		if(criteriaMap!=null){
			for(Entry entry:criteriaMap.entrySet()){
				dc.add(Restrictions.not(Restrictions.eq(entry.getKey().toString(), entry.getValue())));
			}
		}
		List<Menu> temp=this.getHibernateTemplate().findByCriteria(dc);
		return temp.size()>0?temp:null;
	}

	@Override//查询所有一级菜单和根菜单
	public List<Menu> findMyCriteriaMap(Map<String, Object> criteriaMap) {
		DetachedCriteria dc=DetachedCriteria.forClass(Menu.class);
		
		if(criteriaMap!=null){
			Criterion c1=Restrictions.eq("menu_id",criteriaMap.get("menu_id"));
			Criterion c2=Restrictions.eq("parent_menu.menu_id",criteriaMap.get("parent_menu.menu_id"));
			dc.add(Restrictions.or(c1, c2));
		}
		List<Menu> temp=this.getHibernateTemplate().findByCriteria(dc);
		return temp.size()>0?temp:null;

	}

	@Override
	public List<Menu> findOneLevelMenuMenu() {
		String hql="from Menu m where m.menu_id!=? and m.parent_menu.menu_id=?";
		List<Menu>temp=this.getHibernateTemplate().find(hql,Menu.SYSTEM_MENU_ID,Menu.SYSTEM_MENU_ID);
		return temp.size()>0?temp:null;
	}

	
}