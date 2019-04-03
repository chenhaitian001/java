<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>育智云</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<link href="<%=request.getContextPath()%>/static/logo1.png" rel="shortcut icon" />
	<!-- Animate.css -->
	<link rel="stylesheet" href="static/hinova/login/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="static/hinova/login/css/icomoon.css">
	<!-- Themify Icons-->
	<link rel="stylesheet" href="static/hinova/login/css/themify-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="static/hinova/login/css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="static/hinova/login/css/magnific-popup.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="static/hinova/login/css/owl.carousel.min.css">
	<link rel="stylesheet" href="static/hinova/login/css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="static/hinova/login/css/style.css">

	<!-- Modernizr JS -->
	<script src="static/hinova/login/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet"	href="<%=request.getContextPath()%>/static/layui-v2.2.5/layui/css/layui.css">
	<script	src="<%=request.getContextPath()%>/static/layui-v2.2.5/layui/layui.js"></script>
	
	<script>
function go_login2(){
	layer.closeAll();
	layer.alert('敬请期待  暂未开通此功能!');
	return;
	
}	
	
function go_login() {
	var name = $("#name").val();
	var password = $("#password").val();
	if (name == null || name == "") {
		layer.closeAll();
		layer.alert('请输入帐号!');
		return;
	}
	
	if (password == null || password == "") {
		layer.closeAll();
		layer.alert('请输入密码!');
		return;
	}
	
	$.post('<%=basePath%>'+'/user/admin', {
		name : name,
		password : password
	}, function(result) {
		if (result.success) {
			layer.closeAll();
			//document.location.href = "/admin/main";
			//window.open("/admin/main");
			//window.location.href = "/admin/main";
			$("#name").val("");
			$("#password").val("");
			window.location.href = '<%=basePath%>user/login';
		} else {
			layer.closeAll();
			layer.alert(result.msg);
			$("#error").html(result.msg);
		}
	}, 'json');

}

</script>
	</head>
	<body>
		
	<div class="gtco-loader"></div>
	
	<div >

	
	<div class="page-inner">
	
	
	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image: url(static/hinova/login/images/332.jpg)">
	<div class="loge"><img src="static/hinova/images/loge.png"></div>
		<div class="overlay"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
					

					<div class="row row-mt-15em">
						<div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
							<span class="intro-text-small">welcome to visit our website</span>
							<h1>育智云后台管理系统</h1>	
						</div>
						<div class="col-md-4 col-md-push-1 animate-box" data-animate-effect="fadeInRight">
							<div class="form-wrap">
								<div class="tab">
									<ul class="tab-menu">
										<li class="active gtco-first"><a  data-tab="signup">登 陆</a></li>
										<!-- <li class="active gtco-second"><a  data-tab="login">陆</a></li> -->
									</ul>
									<div class="tab-content">
										<div class="tab-content-inner active" data-content="signup">
											
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">用户名：</label>
														<input type="text" class="form-control" id="name">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">密码：</label>
														<input type="password" class="form-control" id="password">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12" style="text-align:center; ">
														<button class="btn btn-primary" onclick="go_login()">登 陆</button>
													</div>
												</div>
											
										</div>

										<!-- <div class="tab-content-inner" data-content="login">
											
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">Username or Email</label>
														<input type="text" class="form-control" id="username">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">real name</label>
														<input type="text" class="form-control" id="username">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">password</label>
														<input type="password" class="form-control" id="passwordd">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">confirm password</label>
														<input type="password" class="form-control" id="passwordd">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<button class="btn btn-primary" onclick="go_login2()">注册</button>
													</div>
												</div>
											
										</div> -->
							</div>
						</div>
					</div>
							
					
				</div>
			</div>
		</div>
	</header>
	<div class="gtco-section border-bottom">
		<div class="gtco-container">
			
			<div class="row">
				<div class="col-md-8 text-left gtco-heading">
					<h2>手机定位打卡</h2>
					<p>Mobile phone online remote location punch.</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<img src="static/hinova/login/images/phoneCard.png" class="img-responsive" alt="Free HTML5 Bootstrap Template">
				</div>
				<div class="col-md-6 mt-sm">
					<div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-layers-alt"></i>
						</span>
						<div class="feature-copy">
							<h3>实时性管理</h3>
							<p>Real-time management.</p>
						</div>
					</div>

					<div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-key"></i>
						</span>
						<div class="feature-copy">
							<h3>移动考勤 支持多种考勤报告，支持全国范围的异地查询.</h3>
							<p>Mobile attendance support multiple attendance reports, support nationwide remote query.</p>
						</div>
					</div>

					<div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-image"></i>
						</span>
						<div class="feature-copy">
							<h3>多机构统一管理、跨地域考勤实施</h3>
							<p>Unified management of multiple institutions, cross-regional implementation of attendance.</p>
						</div>
					</div>

					<div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-heart"></i>
						</span>
						<div class="feature-copy">
							<h3>用户需求自动升级</h3>
							<p>Automatic upgrade of user requirements.</p>
						</div>
					</div>

					<div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-infinite"></i>
						</span>
						<div class="feature-copy">
							<h3>效率大师</h3>
							<p>The efficiency of the master.</p>
						</div>
					</div>

					<div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-credit-card"></i>
						</span>
						<div class="feature-copy">
							<h3>管理更加规范化</h3>
							<p>More standardized management.</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- <div class="gtco-section border-bottom">
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center gtco-heading">
					<h2>最懂中国幼儿园的管理系统</h2>
					<p>欢迎加入育智云平台.</p>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<a href="static/hinova/login/images/7.jpg" class="fh5co-project-item image-popup">
						<figure>
							<div class="overlay"><i class="ti-plus"></i></div>
							<img src="static/hinova/login/images/7.jpg" alt="Image" class="img-responsive">
						</figure>
						<div class="fh5co-text">
							<h2>基础园务</h2>
							<p>班级管理 年级管理 考勤设备管理..</p>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<a href="static/hinova/login/images/8.jpg" class="fh5co-project-item image-popup">
						<figure>
							<div class="overlay"><i class="ti-plus"></i></div>
							<img src="static/hinova/login/images/8.jpg" alt="Image" class="img-responsive">
						</figure>
						<div class="fh5co-text">
							<h2>幼儿管理</h2>
							<p>入园幼儿管理 离园幼儿管理 幼儿档案信息归档</p>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<a href="static/hinova/login/images/9.jpg" class="fh5co-project-item image-popup">
						<figure>
							<div class="overlay"><i class="ti-plus"></i></div>
							<img src="static/hinova/login/images/9.jpg" alt="Image" class="img-responsive">
						</figure>
						<div class="fh5co-text">
							<h2>职工管理</h2>
							<p>教职工管理 教职工考勤 文章管理 财务管理 工资管理..</p>
						</div>
					</a>
				</div>

				<div class="col-lg-4 col-md-4 col-sm-6">
					<a href="static/hinova/login/images/2.jpg" class="fh5co-project-item image-popup">
						<figure>
							<div class="overlay"><i class="ti-plus"></i></div>
							<img src="static/hinova/login/images/2.jpg" alt="Image" class="img-responsive">
						</figure>
						<div class="fh5co-text">
							<h2>成长档案</h2>
							<p>膳食营养  体格测评  保健管理和卫生安全 ..</p>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<a href="static/hinova/login/images/3.jpg" class="fh5co-project-item image-popup">
						<figure>
							<div class="overlay"><i class="ti-plus"></i></div>
							<img src="static/hinova/login/images/3.jpg" alt="Image" class="img-responsive">
						</figure>
						<div class="fh5co-text">
							<h2>移动端</h2>
							<p>共育互动 成长相册 即时通信 食谱 作业..</p>
						</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6">
					<a href="static/hinova/login/images/4.jpg" class="fh5co-project-item image-popup">
						<figure>
							<div class="overlay"><i class="ti-plus"></i></div>
							<img src="static/hinova/login/images/4.jpg" alt="Image" class="img-responsive">
						</figure>
						<div class="fh5co-text">
							<h2>集团管理</h2>
							<p>报表分析..</p>
						</div>
					</a>
				</div>

			</div>
		</div>
	</div>
 -->
	<footer id="gtco-footer" role="contentinfo">
		<div class="gtco-container">
			<!-- <div class="row row-p	b-md">

				<div class="col-md-4">
					<div class="gtco-widget">
						<h3>About <span class="footer-logo">Hinova<span>.<span></span></h3>
						<p>鹤诺科技（北京）有限公司（中文简称：“鹤诺科技”），是位于北京中关村高新技术科技园区的高新技术企业。以服务中国信息化建设为己任，主要从事企业信息化建设和互联网应用领域的软件开发、系统集成和技术服务。目前已通过ISO9001质量管理体系认证、中关村高新技术企业认证和软件企业认证。 凭借高素质的专业技术队伍和对企业信息化市场需求和发展方向的深入了解，开发出了一系列适应行业发展并具有自主知识产权的软件产品, 并获得了多项软件产品的软件著作权、软件产品登记证书、实用新型专利。</p>
					</div>
				</div>

				<div class="col-md-4 col-md-push-1">
					<div class="gtco-widget">
						<h3>Hinova</h3>
						<ul class="gtco-quick-contact">
							<li><a>Knowledge</a></li>
							<li><a>Security</a></li>
							<li><a>Professional</a></li>
							<li><a>Efficient</a></li>
							<li><a>Preferential</a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-4">
					<div class="gtco-widget">
						<h3>Get In Touch</h3>
						<ul class="gtco-quick-contact">
							<li><a ><i class="icon-phone"></i>   010-52693639</a></li>
							<li><a ><i class="icon-mail2"></i> hr@hinova.com.cn</a></li>
							<li><a ><i class="icon-chat"></i> service@hinova.com.cn</a></li>
						</ul>
					</div>
				</div>

			</div> -->

			<div class="row copyright">
				<div class="col-md-12">
					<p class="pull-left">
						<small class="block">© 2018 鹤诺科技（北京）有限公司  京ICP备16015145号-2 <a target="_blank"></a><a target="_blank">鹤诺科技(北京)有限公司  版权所有</a></small> 
					</p>
					
				</div>
			</div>

		</div>
	</footer>
	</div>

	</div>

	<div class="gototop js-top">
		<a  class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>
	
	<!-- jQuery -->
	<script src="static/hinova/login/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="static/hinova/login/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="static/hinova/login/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="static/hinova/login/js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="static/hinova/login/js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="static/hinova/login/js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="static/hinova/login/js/jquery.magnific-popup.min.js"></script>
	<script src="static/hinova/login/js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="static/hinova/login/js/main.js"></script>
	<script>
	layui.use([ 'laydate', 'laypage', 'layer', 'table', 'carousel',
				'upload', 'element' ], function() {
			var laydate = layui.laydate //日期
			, laypage = layui.laypage //分页
			layer = layui.layer //弹层
			, table = layui.table //表格
			, carousel = layui.carousel //轮播
			, upload = layui.upload //上传
			, element = layui.element; //元素操作
	});
</script>
	</body>
</html>

