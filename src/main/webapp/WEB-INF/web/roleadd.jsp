<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>角色权限添加</title>

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
<div class="easyui-panel" title="角色权限添加" >
	<div >

		<table cellpadding="5" style="width:90%;">
			<tr>
				<td style="text-align:left;width:10%;">角色:</td>
				<td> 
					<select id="rid" >
						<option value="1">超级管理员</option>
						<option value="2">普通管理员</option>
				</select></td>
			</tr>
			<tr>
				<td style="text-align:left;width:10%;">可访问的资源:</td>
				<td> <select id="power" >
				</select></td>
			</tr>

		</table>

		<div style="text-align:center;padding:5px">
			<a id="saverole" style="width:132px;height:32px" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" >保存</a>

		</div>
	</div>
</div>



<script>
    var ue;
    $(function(){
        ue=UE.getEditor('content');
        $("#saverole").click(function(){
            var rid = document.getElementById("rid").value;
            var power = document.getElementById("power").value;
            if(rid==""||power==""){
                parent.$.messager.alert('系统提示','有信息没有填完','warning');
                return;
            }

            $.ajax({
                url : "<%=basePath%>saveAddRole",
                //只封装和传输指定的数据
                data :{"role.rid":rid,"role.power":power},
                type:"POST",
                success : function (res) {
                    console.log(res)
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
<script>
    $(document).ready(function () {
        $.ajax({
            timeout: 3000,
            async: false,
            type: "GET",
            url: "<%=basePath%>findListType",
            dataType: "json",
            success: function (data) {
                console.log(data);
                for (var i = 0; i < data.length; i++) {
                    $("#power").append("<option value=\"" + data[i].id + "\" >" +data[i].typename+" " + "</option>");
                }
            }
        });
    });
</script>
</body>

</html>
