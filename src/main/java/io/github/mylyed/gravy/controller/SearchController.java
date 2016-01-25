package io.github.mylyed.gravy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.mylyed.gravy.service.LuceneService;
import io.github.mylyed.gravy.util.IKAnalyzerHelper;

@Controller
@RequestMapping("search")
public class SearchController {

	@RequestMapping({ "/{key}", "/" })
	@ResponseBody
	public Map<String, Object> search(@PathVariable("key") String key) {
		Map<String, Object> map = new HashMap<>();
		map.put("key", key);
		map.put("analyzer", IKAnalyzerHelper.getWords(key));
		map.put("data", luceneService.search(key));
		return map;
	}

	@RequestMapping("")
	public String searchPlus4Page(String k, Model model) {
		model.addAttribute("key", k);
		long s = System.currentTimeMillis();
		model.addAttribute("data", luceneService.search(k));
		long e = System.currentTimeMillis();
		model.addAttribute("time", e - s);
		model.addAttribute("analyzer", IKAnalyzerHelper.getWords(k));
		return "search";
	}

	@Autowired
	LuceneService luceneService;

	@RequestMapping("/init")
	@ResponseBody
	public String initIndex() {
		luceneService.createIndex();
		return "\"success\":true";
	}
}
