package com.taotao.service;

import com.taotao.common.pojo.Page;
import com.taotao.pojo.TbItem;

public interface ItemService {

	TbItem getItemById(Long itemId);
	
	public Page getPage(int page, int rows);
}
