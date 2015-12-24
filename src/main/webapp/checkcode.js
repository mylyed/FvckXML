// 验证码控件
Ext.define('checkcode', {
	extend : 'Ext.container.Container',
	alias : 'widget.checkcode',
	codeUrl : Ext.BLANK_IMAGE_URL,
	fieldLabel : '',
	tabIndex : 0,
	border : 0,
	blankText : '',
	allowBlank : true,
	layout : {
		type : 'hbox'
	},
	initComponent : function() {
		var me = this;
		me.image = Ext.create(Ext.Img, {
			id : 'verifycode_img',
			style : "cursor:pointer ",
			src : me.codeUrl,
			margin : '0 0 0 5',
			shadow : true,
			listeners : {
				click : me.loadCodeImg,
				element : "el",
				scope : me
			}
		});
		me.items = [ {
			xtype : 'textfield',
			id : 'verifycode_input',
			fieldLabel : me.fieldLabel,
			border : 0,
			width : 180,
			tabIndex : me.tabIndex,
			blankText : me.blankText,
			allowBlank : me.allowBlank,
		}, me.image ]
		me.callParent();
	},
	loadCodeImg : function() {
		this.image.setSrc(this.codeUrl + '?time=' + new Date().getTime());
	}
});