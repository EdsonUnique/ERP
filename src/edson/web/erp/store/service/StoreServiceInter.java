package edson.web.erp.store.service;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.store.domain.*;
import java.util.*;
import edson.web.erp.utils.base.*;
import edson.web.erp.utils.exception.MessageException;

public interface StoreServiceInter extends BaseServiceInter<Store>{
	/**
	 * 商品入库
	 * @param orderItem_id
	 * @param store_id
	 * @param num
	 * @param login 入库者
	 * @throws MessageException 
	 */
	void storeGoods(String orderItem_id, String store_id, String num,
			Employee login) throws MessageException;

}