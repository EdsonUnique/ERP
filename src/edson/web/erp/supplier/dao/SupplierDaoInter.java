package edson.web.erp.supplier.dao;

import edson.web.erp.supplier.domain.*;

import java.util.*;

import edson.web.erp.utils.base.*;

public interface SupplierDaoInter extends BaseDaoInter<Supplier>{

	List<Supplier> findListWithoutType();

	List<Supplier> findHasAllSuppliers();

}