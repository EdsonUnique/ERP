package edson.web.erp.utils.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import edson.web.erp.utils.publicBean.PageBean;
@Transactional//在父类上增加事务
public interface BaseServiceInter<T> extends HqlAndParameters{

	public void save(T t);

	public T findById(Integer id);

	public void update(T t);

	public void delete(T t);
	/**
	 * 分页查询所有数据对象（无条件查询所有）
	 * @param currentPage 当前页
	 * @return 分页对象
	 */
	public PageBean<T> findListByPageBean(Integer currentPage);
	/**
	 * 带条件分页查询所有数据对象
	 * @param criteriaMap 判断条件Map  key为属性名，value为属性值，没有值则为null
	 * @param currentPage 当前页
	 * @return 分页对象
	 */
	public PageBean<T> queryByCriteriaObjects(Map<String, Object> criteriaMap,
			int currentPage);
	
	/**
	 * 根据条件查询数据对象
	 * @param criteriaMap 存放条件的map
	 * @return 一个数据对象
	 */
	public T findByCriteriaMap(Map<String,Object> criteriaMap);
	/**
	 * 根据条件查询数据对象
	 * @param criteriaMap 存放条件的map
	 * @return 数据对象的List集合，没有值则返回Null
	 */
	public List<T> findListByCriteriaMap(Map<String,Object> criteriaMap);
	

	
	
}