package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.Page;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public TbItem getItemById(Long itemId) {
		/*根据ID*/
		// TbItem selectByPrimaryKey = itemMapper.selectByPrimaryKey(itemId);
		
		/*根据查询条件*/
		// 1.创建example对象
		TbItemExample example = new TbItemExample();
		// 2.添加查询条件【Criteria：条件】
		Criteria criteria = example.createCriteria();
		// 3.根据ID查，将id放入条件中
		criteria.andIdEqualTo(itemId);
		// 4.根据ID肯定只返回一条数据，取List的第一条就行，前提是List不能为空
		List<TbItem> itemList = itemMapper.selectByExample(example);
		// 如果list不等于空并且list的长度大于0，取第一个并返回，如果为空 返回空
		if (itemList != null && itemList.size() > 0) {
			TbItem item = itemList.get(0);
			return item;
		}
		return null;
	}

	@Override
	public Page getPage(int page, int rows) {
		// 查询列表
		TbItemExample example = new TbItemExample();
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		// 创建返回值对象Page
		Page pageList = new Page();
		pageList.setRows(list);
		// 获取总记录数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		pageList.setTotal(total);;
		return pageList;
	}

	@Override
	public TaotaoResult insertItem(TbItem item, String desc, String itemParam) throws Exception {
		long id;
		try {
			// 补全POJO
			id = IDUtils.genItemId();
			item.setId(id);
			item.setStatus((byte)1);
			item.setCreated(new Date());
			item.setUpdated(new Date());
			// 插入数据表
			@SuppressWarnings("unused")
			int result = itemMapper.insert(item);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		// 插入商品描述信息
		TaotaoResult result = insertItemDesc(id, desc);
		if (result.getStatus() != 200) {
			// Spring管理实务，如果有异常，Spring会自己回滚！
			throw new Exception();
		}
		// 插入商品规格参数信息
		result = insertItemParam(id, itemParam);
		if (result.getStatus() != 200) {
			// Spring管理实务，如果有异常，Spring会自己回滚！
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	
	/*
	 * 	添加商品描述信息
	 */
	private TaotaoResult insertItemDesc(long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	/*
	 * 	添加商品规格参数信息到商品规格 参数表 tb_item_param_item
	 */
	private TaotaoResult insertItemParam(long itemId, String itemParam) {
		// 创建并补全POJO
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(new Date());
		itemParamItem.setUpdated(new Date());
		// 插入数据
		itemParamItemMapper.insert(itemParamItem);
		return TaotaoResult.ok();
	}

}
