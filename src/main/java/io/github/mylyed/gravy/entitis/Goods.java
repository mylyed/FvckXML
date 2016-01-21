package io.github.mylyed.gravy.entitis;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

/**
 * 商品信息
 * 
 * @author lilei
 *
 */
@Entity
@Table(name = "ecs_goods")
public class Goods {
	private String goods_id;
	private String goods_sn;
	private String goods_name;
	private String goods_brief;
	private String goods_keywords;
	private String specification;
	private String goods_spec;
	private String brand_country;
	private Integer last_update;
	private String market_price;
	private String shop_price;
	private String origin_price;
	private String promote_price;
	private String is_new;
	private String goods_delete;
	private String sort_order;
	private String is_on_sale;
	private String sales_volume;
	private String goods_number;
	// -------------------
	private String cat_id;// b.cat_id;
	private String cat_name;// b.cat_name
	private String category_keywords;// b.keywords
	private String category_alias;// b.alias
	private String cat_delete;// b.is_delete
	private String brand_name;// c.brand_name;
	private String brand_alias;// c.alias
	private String brand_country1;// c.country
	private String brand_id;// c.brand_id;
	private String goods_country;// e.region_name

	private Integer promote_flag;
	private Integer goods_num_flag;
	private Integer volume_flag;

	@Id
	@Column(name = "goods_id")
	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	@Column(name = "goods_sn")
	public String getGoods_sn() {
		return goods_sn;
	}

	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}

	@Column(name = "goods_name")
	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	@Column(name = "goods_brief")
	public String getGoods_brief() {
		return goods_brief;
	}

	public void setGoods_brief(String goods_brief) {
		this.goods_brief = goods_brief;
	}

	@Column(name = "keywords")
	public String getGoods_keywords() {
		return goods_keywords;
	}

	public void setGoods_keywords(String goods_keywords) {
		this.goods_keywords = goods_keywords;
	}

	@Column(name = "specification")
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	@Column(name = "goods_spec")
	public String getGoods_spec() {
		return goods_spec;
	}

	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}

	@Column(name = "brand_country")
	public String getBrand_country() {
		return brand_country;
	}

	public void setBrand_country(String brand_country) {
		this.brand_country = brand_country;
	}

	@Column(name = "last_update")
	public Integer getLast_update() {
		return last_update;
	}

	public void setLast_update(Integer last_update) {
		this.last_update = last_update;
	}

	@Column(name = "market_price")
	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	@Column(name = "shop_price")
	public String getShop_price() {
		return shop_price;
	}

	public void setShop_price(String shop_price) {
		this.shop_price = shop_price;
	}

	@Column(name = "origin_price")
	public String getOrigin_price() {
		return origin_price;
	}

	public void setOrigin_price(String origin_price) {
		this.origin_price = origin_price;
	}

	@Column(name = "promote_price", insertable = false, updatable = false)
	public String getPromote_price() {
		return promote_price;
	}

	public void setPromote_price(String promote_price) {
		this.promote_price = promote_price;
	}

	@Column(name = "is_new")
	public String getIs_new() {
		return is_new;
	}

	public void setIs_new(String is_new) {
		this.is_new = is_new;
	}

	@Column(name = "is_delete")
	public String getGoods_delete() {
		return goods_delete;
	}

	public void setGoods_delete(String goods_delete) {
		this.goods_delete = goods_delete;
	}

	@Column(name = "sort_order")
	public String getSort_order() {
		return sort_order;
	}

	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}

	@Column(name = "is_on_sale")
	public String getIs_on_sale() {
		return is_on_sale;
	}

	public void setIs_on_sale(String is_on_sale) {
		this.is_on_sale = is_on_sale;
	}

	@Column(name = "sales_volume")
	public String getSales_volume() {
		return sales_volume;
	}

	public void setSales_volume(String sales_volume) {
		this.sales_volume = sales_volume;
	}

	@Column(name = "goods_number")
	public String getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}

	// -------------------------------------------------------
	@Formula("(SELECT b.cat_id FROM ecs_category b WHERE b.cat_id = cat_id)")
	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	@Formula("(SELECT b.cat_name FROM ecs_category b WHERE b.cat_id = cat_id)")
	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	@Formula("(SELECT b.keywords FROM ecs_category b WHERE b.cat_id = cat_id)")
	public String getCategory_keywords() {
		return category_keywords;
	}

	public void setCategory_keywords(String category_keywords) {
		this.category_keywords = category_keywords;
	}

	@Formula("(SELECT b.is_delete FROM ecs_category b WHERE b.cat_id = cat_id)")
	public String getCat_delete() {
		return cat_delete;
	}

	public void setCat_delete(String cat_delete) {
		this.cat_delete = cat_delete;
	}

	@Formula("(SELECT b.alias FROM ecs_category b WHERE b.cat_id = cat_id)")
	public String getCategory_alias() {
		return category_alias;
	}

	public void setCategory_alias(String category_alias) {
		this.category_alias = category_alias;
	}

	@Formula("(SELECT c.brand_name FROM ecs_brand c WHERE c.brand_id = brand_id)")
	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	@Formula("(SELECT c.alias FROM ecs_brand c WHERE c.brand_id = brand_id)")
	public String getBrand_alias() {
		return brand_alias;
	}

	public void setBrand_alias(String brand_alias) {
		this.brand_alias = brand_alias;
	}

	@Formula("(SELECT c.country FROM ecs_brand c WHERE c.brand_id = brand_id)")
	public String getBrand_country1() {
		return brand_country1;
	}

	public void setBrand_country1(String brand_country1) {
		this.brand_country1 = brand_country1;
	}

	@Formula("(SELECT c.brand_id FROM ecs_brand c WHERE c.brand_id = brand_id)")
	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	@Formula("(SELECT e.region_name FROM ecs_region e WHERE e.region_id = goods_country)")
	public String getGoods_country() {
		return goods_country;
	}

	public void setGoods_country(String goods_country) {
		this.goods_country = goods_country;
	}

	@Column(name = "promote_price", insertable = false, updatable = false)
	public Integer getPromote_flag() {
		return promote_flag;
	}

	public void setPromote_flag(Integer promote_flag) {
		this.promote_flag = promote_flag > 0 ? 1 : 0;
	}

	@Column(name = "goods_number", insertable = false, updatable = false)
	public Integer getGoods_num_flag() {
		return goods_num_flag;
	}

	public void setGoods_num_flag(Integer goods_num_flag) {
		this.goods_num_flag = goods_num_flag > 0 ? 1 : 0;
	}

	@Column(name = "sales_volume", insertable = false, updatable = false)
	public Integer getVolume_flag() {
		return volume_flag;
	}

	public void setVolume_flag(Integer volume_flag) {
		this.volume_flag = volume_flag > 0 ? 1 : 0;
	}

}
