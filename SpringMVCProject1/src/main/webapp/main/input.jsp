<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="output2.do">
	이름:<input type="text" name="name" size="15"><br/>
	성별:<input type="radio" name="sex" value="남자 " checked="checked">남자
		<input type="radio" name="sex" value="여자 ">여자<br/>
	주소:<input type="text" name="addr" size="25"><br/>
	전화:<input type="text" name="tel" size="25"><br/>
	소개:<textarea rows="2" cols="30" name="content"></textarea><br/>
		<input type="submit" value="전송">
	</form>
</body>
</html>