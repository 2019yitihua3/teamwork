<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>newslist.html</title>

<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta name="content-type" content="text/html; charset=UTF-8">

<link rel="shortcut icon" href="<%=basePath%>include/img/ico.jpg">
<link rel="stylesheet" href="<%=basePath%>include/css/themes/icon.css">
<link rel="stylesheet" href="<%=basePath%>include/css/themes/default/easyui.css">

</head>
<body>
<table id="dg" cellpadding="2"></table>
<div id="tb" style="padding:5px;">
    <input id="xm" class="easyui-textbox" data-options="prompt:'姓名...'" style="width:200px;height:32px">
    <a id="s_news" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:60px;height:32px">查询</a>
</div>
</body>

<script src="<%=basePath%>include/js/jquery.min.js"></script>
<script src="<%=basePath%>include/js/jquery.easyui.min.js"></script>R
<script src="<%=basePath%>include/js/easyui-lang-zh_CN.js"></script>

<script>
var role1="超级管理员"
var role2="普通管理员"
var xm = "",uid="",title="";
function loadGrid(){
xm=$("#xm").val();
$("#dg").datagrid({
					width : 800,
					height : 500,
					nowrap : false,
					striped : true,
					border : true,
					collapsible : false,
					url : "<%=basePath%>listUser",
					queryParams : {
						"xm" : xm
					},
					pagination : true,
					rownumbers : true,
					fitColumns : true,
					pageSize : 20,
					loadMsg : '数据加载中...',
					columns : [ [
							//标题过长时，显示部分文字和省略号。已给easyui中datagrid‐cell样式添加了属性 text‐overflow:ellipsis;
							//鼠标停留时，显示全部文字
							{
								title : '用户id',
								field : 'uid',
								width : 200,
								formatter : function(value, row, index) {
									return '<span title='+value+'>'
											+ (value ? value : '') + '</span>';
								}
							},
							{
								title:'用户名',
								field:'xm',
								width:150,
                                formatter : function(value, row, index) {
                                    return '<span title='+value+'>'
                                        + (value ? value : '') + '</span>';
                                }
                       		},
                            {
                             title:'所属',
                             field:'bj',
                             width:150,
                             formatter : function(value, row, index) {
                                return '<span title='+value+'>'
                                    + (value ? value : '') + '</span>';
                               }
                            },
                            {
                              title:'角色',
                              field:'role',
                              width:150,
                              formatter : function(value, row, index) {
                                  if (value == "1") {
                                      return '<span title=' + value + '>'
                                          + (value ? role1 : '') + '</span>';
                                  } else {
                                      return '<span title=' + value + '>'
                                          + (value ? role2 : '') + '</span>';
                                  }
                              }


                            },
							{
								title : '操作',
								field : 'hitnum',
								width : 100,
								formatter : function(value, row, index) {
									var p = "<a href=\"javascript:editUser('"
											+ row.uid + "')\">修改</a>";
									p += " | <a href=\"javascript:delUser('"
											+ row.uid+ "','" + row.xm
											+ "')\">删除</a>";
									return p;
								}
							} ] ],
					toolbar : '#tb'
				});
	}
	function editUser(uid) {
		parent.swNewTab("修改用户信息", "<%=basePath%>goEditUser?uid=" + uid);
	}
	function delUser(userid, uername) {
		uid = userid;
		xm = uername;
		parent.$.messager.confirm("系统提示", "您确认要删除“" + xm + "”吗？ ", function(r) {if (r){
				$.ajax({
					url : "<%=basePath%>doDelUser",
					data : {
						"uid" : uid
					},
					type : "post",
					success : function(res) {
						if (res.delflag) {
							parent.$.messager.alert("系统提示", "您已删除该用户： " + xm,	"info");
							uid = "";
							xm = "";
							loadGrid();
						} else {
							parent.$.messager.alert("系统提示", res, "error");
						}
						return false;
					},
					error : function(res) {
						parent.$.messager.alert("系统提示", "系统错误", "error");
					}
				})
			}
		});
	}
	$(function() {
		loadGrid();
		$("#s_news").click(function() {
			xm = $("#xm").val();
			loadGrid();
		});
		$("#tb").bind("keydown", function(e) {
			var theEvent = e || window.event; 
			var code = theEvent.keyCode || theEvent.which|| theEvent.charCode;
			if (code == 13) {
				$("#s_news").click();
			}
		});
	})
</script>
</html>
