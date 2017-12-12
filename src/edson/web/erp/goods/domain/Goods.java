package edson.web.erp.goods.domain;

import edson.web.erp.goodsType.domain.GoodsType;
import edson.web.erp.store.domain.Store;
import edson.web.erp.supplier.domain.Supplier;

/**
 * 货物模块
 * @author Edson
 *
 */
public class Goods {

	private Integer goods_id;
	private String name;
	private String origin;
	private String manufacturer;
	private String unit;
	private Double origin_price;
	private Double sale_price;
	private Double volume;
	
	private GoodsType goodsType;
	private Supplier supplier;
	public final Integer getGoods_id() {
		return goods_id;
	}
	public final void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getOrigin() {
		return origin;
	}
	public final void setOrigin(String origin) {
		this.origin = origin;
	}
	public final String getManufacturer() {
		return manufacturer;
	}
	public final void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public final String getUnit() {
		return unit;
	}
	public final void setUnit(String unit) {
		this.unit = unit;
	}
	public final Double getOrigin_price() {
		return origin_price;
	}
	public final void setOrigin_price(Double origin_price) {
		this.origin_price = origin_price;
	}
	public final Double getSale_price() {
		return sale_price;
	}
	public final void setSale_price(Double sale_price) {
		this.sale_price = sale_price;
	}
	public final Double getVolume() {
		return volume;
	}
	public final void setVolume(Double volume) {
		this.volume = volume;
	}
	public final GoodsType getGoodsType() {
		return goodsType;
	}
	public final void setGoodsType(GoodsType goodsType) {
		this.goodsType = goodsType;
	}
	public final Supplier getSupplier() {
		return supplier;
	}
	public final void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
	
	
}
