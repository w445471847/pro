<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layui</title>

<script src="dist/js/jquery-3.3.1.js"></script>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="layui/css/layui.css"  media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<br>
   <div class="demoTable">
 	 搜索条件：
  <div class="layui-inline">
    <input class="layui-input" name="id" id="demoReload" autocomplete="off">
  </div>
  <button class="layui-btn" data-type="reload">搜索</button>
</div>


<table class="layui-hide" id="test" lay-filter="test"></table>
 

 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
              
          
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 --> 
 
<script>
layui.use(['table','form'], function(){
  var table = layui.table;
  
  table.render({
    elem: '#test'
    ,url:'as'
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
      title: '提示'
      ,layEvent: 'LAYTABLE_TIPS'
      ,icon: 'layui-icon-tips'
    }]
    ,title: '用户数据表'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
      ,{field:'username', title:'用户名', width:120, edit: 'text'}
      ,{field:'email', title:'邮箱', width:150, edit: 'text', templet: function(res){
        return '<em>'+ res.email +'</em>'
      }}
      ,{field:'sex', title:'性别', width:80, edit: 'text', sort: true}
      ,{field:'city', title:'城市', width:100}
      ,{field:'sign', title:'签名'}
      ,{field:'experience', title:'积分', width:80, sort: true}
      ,{field:'ip', title:'IP', width:120}
      ,{field:'logins', title:'登入次数', width:100, sort: true}
      ,{field:'joinTime', title:'加入时间', width:120}
      ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:150}
    ]]
    ,page: true
    ,id: 'testReload'
  });
  
  
  var $ = layui.$, active = {
		    reload: function(){
		      var demoReload = $('#demoReload');
		      
		      //执行重载
		      table.reload('testReload', {
		        page: {
		          curr: 1 //重新从第 1 页开始
		        }
		        ,where: {
		         	//获取文本框
		            "strLike": demoReload.val()
		          
		        }
		      }, 'data');
		    }
		  };
		  
		  $('.demoTable .layui-btn').on('click', function(){
		    var type = $(this).data('type');
		    active[type] ? active[type].call(this) : '';
		  });
  
  
		  //监听提交
		 layui.form.on('submit(demo1)', function(data){
		   $.ajax({
			   url: "as?op=update",
			   type: "post",
			   contentType: "application/json",
			   data: JSON.stringify(data.field),
			   success:function(data)
			   {
				   layer.msg(data?"操作成功":"操作失败", {icon: 1},function(){
					   //刷新父页面
					   parent.location.reload();
				   });	   	
			   }
		   });
		   layui.form.render();
		   return false;
		  });	  
		  
		  
		  
  //监听行工具事件
  table.on('tool(test)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
      layer.confirm('真的删除'+data.username+"?", function(index){
    	  
    	 $.post("as","op=del&id="+data.id,function(data){
    		 
    		 layer.msg(data?"操作成功":"操作失败", {icon: 1});
    	     	obj.del();
    	        layer.close(index);
    	 })
      });
    } else if(obj.event === 'edit'){
      $("#id").attr("value",data.id);
      $("#username").attr("value",data.username);
      $("#email").attr("value",data.email);
      $("#city").attr("value",data.city);
      $("#sign").html(data.sign);
      if (data.sex =='女') {
		$("input[name='sex'][value='女']").prop("checked",true);
	}else{
		$("input[name='sex'][value='男']").prop("checked",true);
	}
      layui.form.render();
      
      layer.open({
    	  type: 1,
    	  content:$('#div1'),//可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）。
    	  area: ['690px', '560px']
      
      })
    }
  });
});	
</script>


<!-- 弹出层开始 -->
<div id="div1" style="display: none" >
<form class="layui-form" action="" lay-filter="example">
<br>
  <div class="layui-form-item">
    <label class="layui-form-label">编号</label>
    <div class="layui-input-block">
      <input id="id" type="text" name="id" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" readonly="readonly" >
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-block">
      <input id="username" type="text" name="username" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input" required="required" >
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">邮箱</label>
    <div class="layui-input-block">
      <input id="email" type="email" name="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input" required="required">
    </div>
  </div>
  
<!--    <div class="layui-form-item">
    <label class="layui-form-label">城市</label>
    <div class="layui-input-block">
      <select name="interest" lay-filter="aihao">
        <option value=""></option>
        <option value="0">写作</option>
        <option value="1">阅读</option>
        <option value="2">游戏</option>
        <option value="3">音乐</option>
        <option value="4">旅行</option>
      </select>
    </div>
  </div> 
  
  <div class="layui-form-item">
    <label class="layui-form-label">复选框</label>
    <div class="layui-input-block">
      <input type="checkbox" name="like[write]" title="写作">
      <input type="checkbox" name="like[read]" title="阅读">
      <input type="checkbox" name="like[daze]" title="发呆">
    </div>
  </div> 
  
  <div class="layui-form-item">
    <label class="layui-form-label">开关</label>
    <div class="layui-input-block">
      <input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">
    </div>
  </div>-->
  
  <div class="layui-form-item">
    <label class="layui-form-label">性别</label>
    <div class="layui-input-block">
      <input type="radio" name="sex" value="男" title="男" checked="">
      <input type="radio" name="sex" value="女" title="女">
    </div>
  </div>
  
    <div class="layui-form-item">
    <label class="layui-form-label">城市</label>
    <div class="layui-input-block">
      <input id="city" type="text" name="city" lay-verify="title" autocomplete="off" placeholder="请输入城市" class="layui-input" required="required" >
    </div>
  </div>
  
  <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">签名</label>
    <div class="layui-input-block">
      <textarea id="sign" placeholder="请输入内容" class="layui-textarea" name="sign"></textarea>
    </div>
  </div>
 
  <div class="layui-form-item">
    <div class="layui-input-block">
     <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
    </div>
  </div>
</form>
</div>
<!-- 弹出层结束 -->
</body>
</html>