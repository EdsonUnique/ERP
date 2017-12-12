package edson.web.erp.supplier.service;

import edson.web.erp.supplier.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;

public interface SupplierServiceInter extends BaseServiceInter<Supplier>{

	List<Supplier> findListByNotCriteriaMap(Map<String, Object> criteriaMap);
	/**
	 * 查找没有商品类型的供应商集合
	 * @return
	 */
	List<Supplier> findListWithoutType();
	/**
	 * 查询含有商品类别且商品类别含有商品的供应商集合
	 * @return
	 */
	List<Supplier> findHasAllSuppliers();
	
}