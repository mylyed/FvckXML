Ext.onReady(function() {
			var mytree = Ext.create('Ext.tree.Panel', {
				el : "tree-div",// 应用到的html元素id
				animate : true,// 以动画形式伸展,收缩子节点
				title : "Extjs静态树",
				collapsible : true,
				rootVisible : true,// 是否显示根节点
				autoScroll : true,
				autoHeight : true,
				width : 150,
				lines : true
					// 节点之间连接的横竖线
				});
			mytree.render();// 不要忘记render()下,不然不显示哦
		});
