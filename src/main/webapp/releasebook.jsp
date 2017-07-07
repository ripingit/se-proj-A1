<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>阅享 - 发布图书</title>
<%
	String path = request.getContextPath();
%>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script
	src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.min.js"></script>
<script src="<%=path%>/js/releasebook.js"></script>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/css/login_style.css" rel="stylesheet">
<link href="<%=path%>/css/yxdefault.css" rel="stylesheet">
<link href="<%=path%>/css/releasebook.css" rel="stylesheet">
<style type="text/css">
.error {
	color: red;
	font-weight: normal
}
</style>
</head>
<script>
	var logined = "<s:property value='#session.logined'/>";
	var path = "<%=path%>";
	$(document).ready(function() {
		if (logined == "") {
			bootbox.alert("您尚未登录，请先登录！", function() {
				location.href = "index";
			})
		}
	});
</script>
<body>
	<div
		class="yx-nav navbar navbar-default navbar-left navbar-static-top yx-nav" style="width:100%; margin-bottom:0">
		<a class="yx-brand navbar-brand" href="index" style="color: cadetblue">阅享图书交流平台</a>
		<div class="yx-toolbar">
			<a class="btn btn-default btn-sm" href="<%=path%>/ReleaseBook">发布图书</a>
			<form class="yx-search-bar">
				<input type="text" name="searchString" placeholder="查询图书" />
				<button class="btn btn-sm btn-primary btn-block" type="submit">Search</button>
			</form>
			<span>Hi, <s:property value='#session.userName' />!
			</span> <a href="profile" class="btn btn-default btn-sm" role="button">个人信息</a>
			<a href="logout" class="btn btn-default btn-sm" role="button">登出</a>
		</div>
	</div>

	<div class="yx-container container">
		<div class="page-header">
			<h2 class="text-primary">
				书籍信息填写<br> <small>您只需填写书籍的ISBN码，我们会获取书籍的标题、作者等详细信息:)</small>
			</h2>
		</div>

		<div class="row">
			<div class="col-md-12">
				<form id="releaseForm" class="form-horizontal"
					style="text-align: center" action="releaseBook" method="post"
					enctype="multipart/form-data">
					<!-- 这里的onsubmit属性原本是通过submit后禁用提交按钮来禁止重复提交表单，但这样也会在输入不合法时禁止提交，故暂先注释掉，后期再做修改 -->
					<!-- onsubmit="document.getElementById('submit').disabled=true;" -->
					<div class="form-group">
						<div class="col-md-8">
							<div class="form-group">
								<label for="isbn" class="col-md-2 control-label">ISBN(*)</label>
								<div class="col-md-8">
									<input type="number" class="form-control" id="isbn" name="isbn"
										placeholder="请输入13位ISBN码" required="true" maxlength="13">
								</div>
								<div class="col-md-2">
									<button id="previewInfo" data-toggle="modal"
										class="btn btn-info btn-sm" data-target="#previewModal">预览图书信息</button>
								</div>
							</div>
							<div class="form-group">
								<label for="publishDate" class="col-md-2 control-label">出版日期(*)</label>
								<div class="col-md-8">
									<input type="date" class="form-control" id="publishDate" name="publishDate"
										placeholder="请输入出版日期" required="true">
								</div>
							</div>
							<div class="form-group">
								<label for="discription" class="col-md-2 control-label">书籍描述(*)</label>
								<div class="col-md-8">
									<textarea maxlength="140" rows="10" required="true"
										class="form-control" id="discription" name="discription"
										placeholder="请输入书籍描述（该书新旧程度、注意事项等等，不超过140字符）"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label for="price" class="col-md-2 control-label">积分定价(*)</label>
								<div class="col-md-8">
									<input type="number" class="form-control" id="price" name="price"
										placeholder="请输入积分定价" required="true">
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group" style="text-align: center">
								<img id="preview" style="max-width: 70%; height: auto;"
									src="<%=path%>/images/picture-0.png" />
							</div>
							<div class="form-group" style="text-align: center">
								<label for="image">上传图片</label> <input type="file" id="image"
									name="image" style="margin: 0 auto">
								<p class="help-block">暂时仅支持上传1张图片，大小不得超过4MB</p>
							</div>
						</div>
					</div>
					<button id="submit" type="submit" class="btn btn-info"
						style="width: 20%">提交</button>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="previewModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">书籍信息</h4>
				</div>
				<div class="modal-body">
					<div class="container" style="max-width:100%">
						<div class="row">
							<div class="col-md-8">
								<dl>
									<dt>ISBN</dt>
									<dd id="infoIsbn">加载中...</dd>
									<dt>题名</dt>
									<dd id="infoTitle">加载中...</dd>
									<dt>作者</dt>
									<dd id="infoAuthor">加载中...</dd>
									<dt>出版商</dt>
									<dd id="infoPublisher">加载中...</dd>
									<dt>译者</dt>
									<dd id="infoTranslator">加载中...</dd>
								</dl>
								<p class="help-block">数据源自豆瓣</p>
							</div>
							<div class="col-md-4">
								<img id="cover" class="img-responsive" src="<%=path%>/images/picture-0.png" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

</body>
</html>