package edson.web.erp.utils.DBSql;

import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
/**
 * HQL分页查询
 * @author Acer
 *
 * @param <T> 查询的对象
 */
public class PageHibernateCallback<T> implements HibernateCallback{
	
	private String hql;
	private Object[] params;
	private Integer firstResult;  
	private Integer maxResults;
	
	@Override
	public T doInHibernate(Session session) throws HibernateException,
			SQLException {
		Query qr=session.createQuery(this.getHql());
		
		if(params!=null && params.length>=0){
			//hql参数则为其设置参数
			for(int i=0;i<params.length;i++){
				qr.setParameter(i, params[i]);
			}
		}
		return (T) qr.setFirstResult(this.getFirstResult())
		.setMaxResults(this.getMaxResults()) 
		.list();
	}
	


	/**
	 * 
	 * @param hql hql语句
	 * @param params 参数，如果有的话
	 * @param firstResult 分页起始
	 * @param maxResults	每次查询的最大记录数
	 */
	public PageHibernateCallback(String hql, Object[] params,
			Integer firstResult, Integer maxResults) {
		super();
		this.hql = hql;
		this.params = params;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}


	public final String getHql() {
		return hql;
	}


	public final void setHql(String hql) {
		this.hql = hql;
	}


	public final Object[] getParams() {
		return params;
	}


	public final void setParams(Object[] params) {
		this.params = params;
	}


	public final Integer getFirstResult() {
		return firstResult;
	}


	public final void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}


	public final Integer getMaxResults() {
		return maxResults;
	}


	public final void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	
	
	

}
