package edson.web.erp.storeItem.action;

import java.util.*;

import edson.web.erp.storeItem.service.StoreItemServiceInter;
import edson.web.erp.utils.base.*;

public class StoreItemAction extends BaseAction{

	private StoreItemServiceInter service;

	public final void setService(StoreItemServiceInter service) {
		this.service = service;
	}
	
}