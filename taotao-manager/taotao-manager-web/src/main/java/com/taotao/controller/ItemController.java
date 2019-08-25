package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.Page;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	/*
	 * 	测试
	 */
	@RequestMapping("/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	
	/*
	 * 	list页面
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page list(Integer page, Integer rows) { 
		Page list = itemService.getPage(page, rows);
		return list;
	}
	
	/*
	 * 	插入功能
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult insertItem(TbItem item, String desc, String itemParams) throws Exception { // desc跟jsp中name一致
		TaotaoResult result = itemService.insertItem(item, desc, itemParams);
		return result;
	}
	
}
