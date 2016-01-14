package io.github.mylyed.shh.fvckxml.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("task")
public class TaskController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping("/quartz")
	@ResponseBody
	public Object quartzTask(String express) {
		logger.info(express);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		if ("0".equals(express)) {
			jsonObject.put("success", false);
		}
		jsonObject.put("msg", "表达式是-->" + express);
		return jsonObject;
	}
}
