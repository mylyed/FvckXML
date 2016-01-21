package io.github.mylyed.gravy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.mylyed.gravy.dao.AreaDAO;
import io.github.mylyed.gravy.dao.GoodsDAO;
import io.github.mylyed.gravy.entitis.Area;
import io.github.mylyed.gravy.entitis.Goods;
import io.github.mylyed.gravy.entitis.Page;

@Controller
@RequestMapping("test")
public class TestController {
	@RequestMapping("/t1")
	public String test1(Model model) {
		model.addAttribute("str", "世界");
		return "index";
	}

	@Autowired
	AreaDAO areaDAO;

	@ResponseBody
	@RequestMapping("/json")
	public List<Area> json() {
		return areaDAO.getAll();
	}

	@ResponseBody
	@RequestMapping("/area/{id}")
	public List<Area> area(@PathVariable("id") String id) {
		return areaDAO.getAreasById(id);
	}

	@Autowired
	GoodsDAO goodsDAO;

	@ResponseBody
	@RequestMapping("/goods")
	public List<Goods> getGoods(Page page) {
		if (page.getPageNum() <= 0 || page.getPageSize() <= 0) {
			page = new Page(1, 10);
		}
		return goodsDAO.getGoods(page);
	}
}
