package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;

@Controller
@RequestMapping("/itemParam")
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/{itemId}")
	public String showItemParamItem(@PathVariable Long itemId, Model model) {
		String html = itemParamItemService.getItemParamItemByItemId(itemId);
		model.addAttribute("html", html);
		return "itemParamItem";
	}
}
