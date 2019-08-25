<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 也是片段 -->
<table cellpadding="5" style="margin-left: 30px" id="itemParamAddTable" class="itemParam">
	<tr>
		<td>商品类目:</td>
		<!-- selectItemCat：在common.js中已经实现了  -->
		<td><a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a> 
			<input type="hidden" name="cid" style="width: 280px;"></input>
		</td>
	</tr>
	<tr class="hide addGroupTr">
		<td>规格参数:</td>
		<td>
			<ul>
				<li><a href="javascript:void(0)" class="easyui-linkbutton addGroup">添加分组</a></li>
			</ul>
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<a href="javascript:void(0)" class="easyui-linkbutton submit">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton close">关闭</a>
		</td>
	</tr>
</table>
<div  class="itemParamAddTemplate" style="display: none;">
	<li class="param">
		<ul>
			<li>
				<input class="easyui-textbox" style="width: 150px;" name="group"/>&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton addParam"  title="添加参数" data-options="plain:true,iconCls:'icon-add'"></a>
			</li>
			<li>
				<span>|-------</span><input  style="width: 150px;" class="easyui-textbox" name="param"/>&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton delParam" title="删除" data-options="plain:true,iconCls:'icon-cancel'"></a>						
			</li>
		</ul>
	</li>
</div>
<script style="text/javascript">
	$(function(){ // 页面初始化
		TAOTAO.initItemCat({ // 括号里面全是方法的一个参数
			/*
				参数的作用是参数回调，选择商品类目以后调用了fun这个方法。
				调用fun这个方法，来判断：判断的选择的商品规格是否已添加。
			*/
			/*
				这是一个方法			K  ， V
				TAOTAO.initItemCat({fun：Value})
			*/
			fun:function(node){
			$(".addGroupTr").hide().find(".param").remove();
				// ajax请求 根据返回的data 判断选择的目录是否已经添加过规格
			  $.getJSON("/item/param/query/itemcatid/" + node.id,function(data){
				  if(data.status == 200 && data.data){
					  $.messager.alert("提示", "该类目已经添加，请选择其他类目。", undefined, function(){
						 $("#itemParamAddTable .selectItemCat").click();
					  });
					  return ;
				  }
				  $(".addGroupTr").show();
			  });
			}
		});
		
		// 添加模板
		$(".addGroup").click(function(){
			  //               jQuery class选择器
			  var temple = $(".itemParamAddTemplate li").eq(0).clone();
			  $(this).parent().parent().append(temple);
			  temple.find(".addParam").click(function(){
				  var li = $(".itemParamAddTemplate li").eq(2).clone();
				  li.find(".delParam").click(function(){
					  $(this).parent().remove();
				  });
				  li.appendTo($(this).parentsUntil("ul").parent());
			  });
			  temple.find(".delParam").click(function(){
				  $(this).parent().remove();
			  });
		 });
		
		// 关闭按钮
		$("#itemParamAddTable .close").click(function(){
			$(".panel-tool-close").click();
		});
		
		// 提交按钮
		$("#itemParamAddTable .submit").click(function(){
			// 生成json字符串的过程：生成的json都放进params变量中
			var params = [];
			var groups = $("#itemParamAddTable [name=group]"); // 1、name=group的组选择出来
			// i：当前循环的下标； e:当前循环的对象
			groups.each(function(i,e){ // 2、做循环
				var p = $(e).parentsUntil("ul").parent().find("[name=param]"); // 3、把name=param取出来，一个一个放进p中
				var _ps = [];
				p.each(function(_i,_e){
					// siblings：兄弟
					var _val = $(_e).siblings("input").val(); // 4、遍历变量p 选择兄弟的值放入val变量中
					if($.trim(_val).length>0){
						_ps.push(_val);	// 5、把val变量的值放入数组中
					}
				});
				var _val = $(e).siblings("input").val();
				if($.trim(_val).length>0 && _ps.length > 0){
					params.push({	// 6、把组对应的值 和 项对应的值放进变量params中（这是一次循环）
						"group":_val,
						"params":_ps
					});					
				}
			});
			
			// 提交：添加数据库
			var url = "/item/param/save/"+$("#itemParamAddTable [name=cid]").val();
			// JSON.stringify(params)：将js对象转换为字符串（JSON内置的 方法）
			$.post(url,{"paramData":JSON.stringify(params)},function(data){
				if(data.status == 200){
					$.messager.alert('提示','新增商品规格成功!',undefined,function(){
						$(".panel-tool-close").click();
    					$("#itemParamList").datagrid("reload");
    				});
				}
			});
		});
	});
</script>