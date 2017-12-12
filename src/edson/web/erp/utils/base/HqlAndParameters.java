package edson.web.erp.utils.base;

import java.util.Map;

public interface HqlAndParameters {
	
	/**
	 * 设置查询的条件
	 * @param hql 拼接hql语句：在where 1=1 后拼接条件
	 * @param criteriaMap 判断条件
	 * @return 拼接后的hql语句
	 */
	public StringBuffer setHqlAndPramaters(String hql,Map<String, Object> criteriaMap);


}
