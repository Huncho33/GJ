<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  isELIgnored="false"
 %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 
<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />     


<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title><tiles:insertAttribute name="title" /></title>
  </head>
    <body>
    
  </body>
</html>