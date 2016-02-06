package io.github.mylyed.gravy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.mylyed.gravy.dao.GoodsDAO;
import io.github.mylyed.gravy.entitis.Page;

@RequestMapping("goods/")
@Controller
public class GoodsController {
	@Autowired
	GoodsDAO goodsDAO;

	@ResponseBody
	@RequestMapping("/get")
	public Map<String, Object> getGoods(Page page) {
		if (page.getPageNum() <= 0 || page.getPageSize() <= 0) {
			page = new Page(1, 10);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("data", goodsDAO.getGoods(page));
		map.put("count", goodsDAO.getGoodsCount());
		return map;
	}
}
