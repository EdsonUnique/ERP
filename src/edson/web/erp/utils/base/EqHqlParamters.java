package edson.web.erp.utils.base;

import java.util.Iterator;
import java.util.Map;
/**
 * 条件查询时设置条件
 * 此条件均为相等条件如：name=? classId=? 而不是像这样的条件：score>?
 * @author Acer
 *
 */
public class EqHqlParamters implements HqlAndParameters{

	@Override
	public StringBuffer setHqlAndPramaters(String hql,
			Map<String, Object> criteriaMap) {
		StringBuffer sb=new StringBuffer(hql);
		
		//通过迭代器在遍历Map的同时删除其中元素
		Iterator<Map.Entry<String, Object>> iterator=criteriaMap.entrySet().iterator();
		while(iterator.hasNext()){
			//从Map中去除无值的参数，map中剩余部分则为设为参数部分
			Map.Entry<String, Object> entry=iterator.next();
			if(entry.getValue()==null || entry.getValue().toString().trim().equals("")){
				iterator.remove();//使用迭代器删除
			}else{
				//不为空则设为参数
				sb.append("and ").append(entry.getKey()).append("=?");
			}
		}
		
		return sb;
	}

	
}
