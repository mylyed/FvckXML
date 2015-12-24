Ext.onReady(function() {
	var form = Ext.create('Ext.form.Panel', {
		bodyStyle : 'padding:8px',
		labelAlign : 'right',
		items : [ {
			xtype : 'textfield',
			id : 'username',
			tabIndex : 1,
			name : 'username',
			fieldLabel : '用户名',
			blankText : '请输入用户名',
			allowBlank : false,

		}, {
			xtype : 'textfield',
			id : 'password',
			tabIndex : 2,
			name : 'password',
			inputType : 'password',
			fieldLabel : '密码',
			blankText : '请输入密码',
			allowBlank : false
		}, {
			xtype : 'checkcode',
			name : 'checkcode',
			fieldLabel : '验证码',
			blankText : '请输入验证码',
			allowBlank : false,
			codeUrl : 'verify_code'
		} ],
		buttons : [ {
			text : '登陆',
			handler : function() {
				if (form.getForm().isValid()) {
					Ext.Msg.alert("提示", "登陆成功!");
				}
			}
		}, {
			text : '重置',
			handler : function() {
				form.getForm().reset();
			}
		} ]
	})

	var win = new Ext.Window({
		title : '登陆系统',
		width : 290,
		height : 155,
		resizable : false,
		modal : true,
		closable : false,
		maximizable : false,
		minimizable : false,
		plain : false,
		items : form,
		constrain : true,
		layout : 'fit', // 布局设置为hbox
		collapsible : true
	// 折叠
	});
	win.show();
});