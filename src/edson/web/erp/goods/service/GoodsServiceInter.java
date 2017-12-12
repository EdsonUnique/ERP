package edson.web.erp.goods.service;

import edson.web.erp.goods.domain.*;
import java.util.*;

import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.utils.base.*;

public interface GoodsServiceInter extends BaseServiceInter<Goods>{
	/**
	 * 自定义查询条件
	 * @param criteriaMap 查询条件
	 * @return list集合
	 */
	public List<Goods> findListByMyCriteriaMap(Map<String,Object> criteriaMap);


}