<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="extjs_include.jsp" />
<script type="text/javascript">
	Ext.onReady(function() {

		var window = new Ext.Window({
			title : 'quartz',
			closable : false,
			items : {
				xtype : 'form',
				url : '${ctx}/task/quartz',
				items : [ {
					xtype : 'textfield',
					name : 'express',
					fieldLabel : '表达式',
				} ],
				buttons : [ {
					text : '发送',
					handler : function() {
						var form = window.items.items[0].getForm();
						form.submit({
							success : function(form, action) {
								Ext.Msg.alert('成功', action.result.msg);
							},
							failure : function(form, action) {
								console.log(action);
								Ext.Msg.alert('失败', action.result.msg);
							}
						});
					}
				} ]

			}
		});
		window.show();
	})
</script>

<title>调度 -->quartz</title>
</head>
<body>

</body>
</html>