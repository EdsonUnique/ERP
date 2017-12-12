package edson.web.erp.goodsType.domain;

import java.util.HashSet;
import java.util.Set;

import edson.web.erp.goods.domain.Goods;
import edson.web.erp.supplier.domain.Supplier;

/**
 * 商品类型
 * @author Edson
 *
 */
public class GoodsType {

	private Integer goodsType_id;
	private String name;
	
	private Supplier supplier;
	private Set<Goods> the_goods=new HashSet<Goods>();
	
	
	
	public final Set<Goods> getThe_goods() {
		return the_goods;
	}
	public final void setThe_goods(Set<Goods> the_goods) {
		this.the_goods = the_goods;
	}
	public final Integer getGoodsType_id() {
		return goodsType_id;
	}
	public final void setGoodsType_id(Integer goodsType_id) {
		this.goodsType_id = goodsType_id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final Supplier getSupplier() {
		return supplier;
	}
	public final void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
}
