package edson.web.erp.utils.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import edson.web.erp.utils.publicBean.PageBean;
/**
 * 基本数据层接口
 * @author Edson
 *
 * @param <T>
 */
public interface BaseDaoInter<T> {

	public void save(T t);
	/**
	 * 通过id查找对象
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);

	public void update(T t);

	public void delete(T t);
	/**
	 * 根据hql查找数据对象所在表的所有记录
	 * @param hql hql语句
	 * @param params  hql语句的参数
	 * @return 总记录数
	 */
	public long findTotalRecord(String hql, Object[] params);
	/**
	 * 分页查询
	 * @param pageBean 封装分页数据的对象
	 * @param currentPage 当前页
	 * @param totalRecord 总记录数
	 * @param one_page_record 一页内显示的记录数，即一次分页查询的最大记录数
	 * @param path 每次分页查询后页面访问的路径
	 * @param hql 分页查询hql语句
	 * @param params 分页查询hql语句的参数
	 * @return 封装分页数据的对象
	 */
	public PageBean<T> findListByPageBean(PageBean<T> pageBean,
			int currentPage, int totalRecord, int one_page_record, String path,
			String hql, Object[] params);
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
	/**
	 * 根据条件查询数据对象  否定条件  即key=name  value="张三"  条件则为name!="张三"
	 * @param criteriaMap 条件map集合
	 * @return LIst集合
	 */
	public List<T> findListByNotCriteriaMap(Map<String,Object> criteriaMap);

}