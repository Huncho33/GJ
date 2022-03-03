<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
html, body {
	margin: 0;
	padding: 0;
	height: 100%;
}

#layout_container {
	position: relative;
	margin: 0px auto;
	width: 100%;
}

#layout_header {
	position : relative;
	height: 215px;
	padding: 5px;
	margin-bottom: 15px;
}

#layout_content {
	position : relative;
	width: 100%;
	padding: 20px;
	margin: 30px 0px 30px 0px;
	

}

#layout_footer {
	position :relative;
	bottom : 0;
	margin-top: 150px;
	padding: 5px;
}
</style>

<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="layout_container">
		<div id="layout_header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="layout_content">
			<tiles:insertAttribute name="body" />
		</div>

		<div id="layout_footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>