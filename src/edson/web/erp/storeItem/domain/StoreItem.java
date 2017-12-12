package edson.web.erp.storeItem.domain;

import edson.web.erp.goods.domain.Goods;
import edson.web.erp.store.domain.Store;

/**
 * 仓库明细
 * @author Edson
 *
 */
public class StoreItem {
	//**仓库存储**商品**库存
	private Integer storeItem_id;
	private Goods goods;
	private Store store;
	private Integer num;
	
	
	
	public final Integer getStoreItem_id() {
		return storeItem_id;
	}
	public final void setStoreItem_id(Integer storeItem_id) {
		this.storeItem_id = storeItem_id;
	}
	public final Goods getGoods() {
		return goods;
	}
	public final void setGoods(Goods goods) {
		this.goods = goods;
	}
	public final Store getStore() {
		return store;
	}
	public final void setStore(Store store) {
		this.store = store;
	}
	public final Integer getNum() {
		return num;
	}
	public final void setNum(Integer num) {
		this.num = num;
	}
	
	
	
}
