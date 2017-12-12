package edson.web.erp.utils.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edson.web.erp.utils.DBSql.PageHibernateCallback;
import edson.web.erp.utils.publicBean.PageBean;

public abstract class BaseDao<T> extends HibernateDaoSupport implements BaseDaoInter<T>{
	
	protected Class entityClass;
	
	{//求泛型T的Class  固定写法
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types=pt.getActualTypeArguments();
		entityClass=(Class) types[0];
	}

	public void save(T t) {
		this.getHibernateTemplate().save(t);		
	}

	public T findById(Serializable id) {
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		dc.add(Restrictions.eq("id", id));
		return (T) this.getHibernateTemplate().findByCriteria(dc).get(0);
	}
	
	public T findByCriteriaMap(Map<String,Object> criteriaMap){
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		if(criteriaMap!=null && criteriaMap.size()>0){
			for(Entry<String,Object> entry:criteriaMap.entrySet()){
				dc.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		List<T> temp=this.getHibernateTemplate().findByCriteria(dc);
		return  temp.size()>0?(T)temp.get(0):null;
	}
	
	public List<T> findListByNotCriteriaMap(Map<String,Object> criteriaMap){
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		if(criteriaMap!=null && criteriaMap.size()>0){
			for(Entry<String,Object> entry:criteriaMap.entrySet()){
				dc.add(Restrictions.not(Restrictions.eq(entry.getKey(), entry.getValue())));
			}
		}
		List<T> temp=this.getHibernateTemplate().findByCriteria(dc);
		return  temp.size()>0?temp:null;
	}
	
	public List<T> findListByCriteriaMap(Map<String,Object> criteriaMap){
		DetachedCriteria dc=DetachedCriteria.forClass(entityClass);
		if(criteriaMap!=null && criteriaMap.size()>0){
			for(Entry<String,Object> entry:criteriaMap.entrySet()){
				dc.add(Restrictions.eq(entry.getKey(), entry.getValue()));
			}
		}
		List<T> temp=this.getHibernateTemplate().findByCriteria(dc);
		return  temp.size()>0?temp:null;
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	public long findTotalRecord(String hql,Object[] params) {
		if(params!=null && params.length>0){
			return (long)this.getHibernateTemplate().find(hql, params).get(0);
		}
		
		return (long) this.getHibernateTemplate().find(hql).get(0);
	}
	
	public PageBean<T> findListByPageBean(
			PageBean<T> pageBean,int currentPage, int totalRecord,int one_page_record,String path,String hql,Object[] params) {
		List<T> temp=this.getHibernateTemplate().executeFind(new PageHibernateCallback<T>(hql, params, (currentPage-1)*one_page_record,one_page_record));
		
		pageBean=new PageBean<T>(totalRecord, currentPage,one_page_record, temp);
		pageBean.setPath(path);
		return pageBean;
	}

	
}
