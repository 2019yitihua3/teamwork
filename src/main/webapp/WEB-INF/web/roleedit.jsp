<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>修改类别信息</title>

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
	<div class="easyui-panel" title="修改栏目" >
		<div >


	    	<table cellpadding="5" style="width:90%;">
				<tr>
					<td style="text-align:left;width:10%;">角色:</td>
					<td><input class="easyui-textbox" type="text" id="rstr" data-options="required:true" style="width:100%;" readonly="readonly"></input></td>
				</tr>
				<tr>
					<td style="text-align:left;">可访问栏目:</td>
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
<%--$(function(){

		$("#uid").textbox("setValue", "${user.uid}");
        $("#xm").textbox("setValue", "${user.xm}");
        $("#bj").textbox("setValue", "${user.bj}");
        $("#role").textbox("setValue", "${user.role}");
		$('#baocunuser').click(
				//function() {//发布新闻前，要校验
					var a = $("#uid").textbox("uid");
                    var b = $("#xm").textbox("xm");
                    var c = $("#bj").textbox("bj");
                    var d = $("#role").textbox("role");
                    var e = $("#pwd").textbox("pwd");
                    console.log(e);
					if (a.length <= 0||b.length <= 0||c.length <= 0||d.length <= 0||e.length <= 0) {
						$.messager.alert("系统提示", "有信息没有填写", "warning");
						return;
					}
					$.ajax({
						type : 'POST',
						url : "<%=basePath%>saveEditType",
						data : {
							"user.xm" : b,
							"user.pwd" : c,
                            "user.bj" :d,
                            "user.role" : e,
							"user.uid" : ${user.uid}
						},
						success : function(res) {
							if (res.ok) {
								parent.$.messager.alert("系统提示", "你已修改信息:"+ $("#xm").val(), "info");
							} else {
								parent.$.messager.alert("系统提示", "修改失败！ ","error");
							}
							return false;
						},
						error : function(res) {
							parent.$.messager.alert("系统提示", "系统错误！", "error");
						}
					});
				}
				);
	});
</script>--%>
        $(function(){
            $("#id").textbox("setValue", "${ntype.id}");
            $("#typename").textbox("setValue", "${ntype.typename}");
            $('#savetype').click(
                function() {//要校验
                    var a = $("#id").textbox("getValue");
                    var b = $("#typename").textbox("getValue");
                    if (a.length <= 0||b.length <= 0) {
                        $.messager.alert("系统提示", "有信息没有填写", "warning");
                        return;
                    }
                    $.ajax({
                        type : 'POST',
                        url : "<%=basePath%>saveEditType",
                        data : {
                            "ntype.id" : a,
                            "ntype.typename" : b,
                        },
                        success : function(res) {
                            if (res.ok) {
                                parent.$.messager.alert("系统提示", "你已修改类别:"+ $("#id").val(), "info");
                            } else {
                                parent.$.messager.alert("系统提示", "修改失败！ ","error");
                            }
                            return false;
                        },
                        error : function(res) {
                            parent.$.messager.alert("系统提示", "系统错误！", "error");
                        }
                    });
                });
        });
	</script>
</body>
</html>
