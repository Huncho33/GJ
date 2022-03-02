<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
#layout_container {
	width: 100%;
	margin: 0px auto;
	text-align: center;
	border: 0px solid #bcbcbc;
}

#layout_header {
	padding: 5px;
	margin-bottom: 15px;
	border: 0px solid #bcbcbc;
}

#layout_content {
	width: 100%;
	height: 100%;
	padding: 5px;
    transform: translateX(24%);
}

#layout_footer {
	clear: both;
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