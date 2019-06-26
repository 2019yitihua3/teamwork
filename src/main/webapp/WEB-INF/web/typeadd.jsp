<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>栏目添加</title>

    <meta charset="UTF-8">

    <link rel="shortcut icon" href="<%=basePath%>include/img/ico.jpg">
	<link rel="stylesheet" href="<%=basePath%>include/css/themes/icon.css">
	<link rel="stylesheet" href="<%=basePath%>include/css/themes/default/easyui.css">

	<script src="<%=basePath%>include/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>include/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>include/js/easyui-lang-zh_CN.js"></script>
  	<script>var base="<%=basePath%>";</script>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>include/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>include/ueditor/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8"	src="<%=basePath%>include/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>
<body>
	<div class="easyui-panel" title="栏目添加" >
		<div >

	    	<table cellpadding="5" style="width:90%;">
	    		<tr>
	    			<td style="text-align:left;">栏目名称:</td>
	    			<td><input class="easyui-textbox" type="text" id="typename" data-options="required:true" style="width:100%;"></input></td>
	    		</tr>

	    	</table>

	    <div style="text-align:center;padding:5px">
	    	<a id="savetype" style="width:132px;height:32px" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >保存</a>

	    </div>
	    </div>
	</div>



	<script>
		var ue;
		$(function(){
		ue=UE.getEditor('content');
			$("#savetype").click(function(){
			    var typename=$("#typename").textbox("getValue");

				if(typename==""){
					parent.$.messager.alert('系统提示','有信息没有填完','warning');
					return;
				}

			$.ajax({
            url : "<%=basePath%>saveAddType",
            //只封装和传输指定的数据
            data :{"ntype.typename":typename},
            type:"POST",
            success : function (res) {
                  if (res.ok) {
                        parent.$.messager.alert('系统提示','添加成功','info');
                  }else {
                  parent.$.messager.alert('系统提示',res.msg,'warning');              }
                  return false;
            },

            error : function(res) {
            parent.$.messager.alert('系统错误','请核实错误','warning');return false;        }
      });
		});
		})

	</script>

</body>

</html>
