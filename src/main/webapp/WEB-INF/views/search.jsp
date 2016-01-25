<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>西港搜索</title>

<!-- Bootstrap -->
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
	<br>
	<form id="form" action="${ctx}/search" method="get">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-md-offset-4">
					<input type="text" id=query class="text-left" name="k"
						value="${key}">
					<button id=search class="btn btn-default">搜索</button>
					${key}
				</div>
			</div>
		</div>
	</form>
	<br>
	<p>耗时：${time}毫秒</p>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">列表</div>
			<table class="table">
				<thead>
					<tr>
						<th>商品名</th>
						<th>重复数量</th>
						<th>备案号</th>
						<th>权重</th>
						<th>商品编号</th>
						<th>商品价格</th>
						<th>商品简介</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${data}" var="goods">
						<tr>
							<th><a
								href='http://www.xgqqg.com/goods-${goods.goods_id}.html'
								target='_blank'>${goods.goods_name }</a></th>
							<th>1</th>
							<th>${goods.goods_sn }</th>
							<th>1</th>
							<th>${goods.goods_id }</th>
							<th>${goods.shop_price }</th>
							<th>${goods.goods_brief }</th>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script>
		$(function() {
			$('#search').click(function(e) {
				$('form').submit();
			});
		});
	</script>
</body>
</html>
