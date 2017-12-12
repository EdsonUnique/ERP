package edson.web.erp.storeOper.domain;

import edson.web.erp.emp.domain.Employee;
import edson.web.erp.goods.domain.Goods;
import edson.web.erp.store.domain.Store;

/**
 * 仓库操作明细表
 * @author Edson
 *
 */
public class StoreOper {

	private Integer storeOper_id;
	private Employee operator;
	private Store store;
	private Goods goods;
	private Integer num;
	private Integer type;//操作类型：1入库 0出库
	
	public static final Integer IN_STORE=1;
	public static final Integer OUT_STORE=0;
	
	private String operTime;//操作时间
	
	
	
	public final String getOperTime() {
		return operTime;
	}
	public final void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	public final Integer getStoreOper_id() {
		return storeOper_id;
	}
	public final void setStoreOper_id(Integer storeOper_id) {
		this.storeOper_id = storeOper_id;
	}
	public final Employee getOperator() {
		return operator;
	}
	public final void setOperator(Employee operator) {
		this.operator = operator;
	}
	public final Store getStore() {
		return store;
	}
	public final void setStore(Store store) {
		this.store = store;
	}
	public final Goods getGoods() {
		return goods;
	}
	public final void setGoods(Goods goods) {
		this.goods = goods;
	}
	public final Integer getNum() {
		return num;
	}
	public final void setNum(Integer num) {
		this.num = num;
	}
	public final Integer getType() {
		return type;
	}
	public final void setType(Integer type) {
		this.type = type;
	}
	
	
}
