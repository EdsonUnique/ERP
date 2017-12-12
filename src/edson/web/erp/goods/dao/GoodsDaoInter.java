package edson.web.erp.goods.dao;

import edson.web.erp.goods.domain.*;
import java.util.*;

import edson.web.erp.supplier.domain.Supplier;
import edson.web.erp.utils.base.*;

public interface GoodsDaoInter extends BaseDaoInter<Goods>{

	List<Goods> findListByMyCriteriaMap(Map<String, Object> criteriaMap);

}