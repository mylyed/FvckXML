Ext.onReady(function() {

	var window = new Ext.Window({
		title : '注册',
		// height : 500,

		items : {
			xtype : 'form',
			title : '请填写注册信息',
			items : [ {
				xtype : 'hiddenfield',
				name : 'id',
				value : '0'
			}, {
				xtype : 'numberfield',
				anchor : '100%',
				name : 'hight',
				fieldLabel : '身高',
				value : 178,
				maxValue : 300,
				minValue : 0

			}, {
				xtype : 'combobox',
				fieldLabel : '城市',
				queryMode : 'local',
				store : Ext.create('Ext.data.Store', {
			        autoLoad: true, // 必须自动加载, 否则无在编辑的时候load
			        proxy: {
			            type: 'ajax',
			            url: 'test/area/0'
			        },
			        fields: [
			            { name: 'shortname' },
			            { name: 'id' }
			        ]
			    }),
				displayField : 'shortname',
				valueField : 'id'
			}, {
				xtype : 'datefield',
				fieldLabel : '出生日期',
				format : 'Y年m月d日',
				editable : false,// 不可编辑
				allowBlank : true,// 不允许空
				blankText : '请选择日期'
			} ],

			buttons : [ {
				text : '注册',
				handler : function() {

				}
			},

			{
				text : '重置',
				handler : function() {
					console.log(this);
					console.log(window.items.items[0].getForm());
					window.items.items[0].getForm().reset();
				}
			} ]

		}
	});
	window.show();
})