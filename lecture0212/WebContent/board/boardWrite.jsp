<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>

<!-- ckeditor 사용하기  -->
<script type="text/javascript" src="<c:url value = "/ckeditor/ckeditor.js" />" ></script>
<!-- ckeditor source file load, 객체 생성 -->

<style type="text/css">
* {
	font-size: 9pt;
}

p {
	width: 600px;
	text-align: right;
}

table tbody tr th {
	background-color: gray;
}
</style>

</head>

<body>
	<!-- c:out 은 안써도 무방 -->
	<!-- <form action="<c:out value="write" />" method="POST"> -->
	<form action="write" method="POST">

		<table border="1" summary="게시판 등록 폼">
			<caption>게시판 등록 폼</caption>

			<colgroup>
				<col width="100" />
				<col width="500" />
			</colgroup>

			<tbody>

				<tr>
					<th align="center">제목</th>
					<td><input type="text" name="subject" size="80"	maxlength="100" required /></td>
				</tr>

				<tr>
					<th align="center">작성자</th>
					<td><input type="text" name="writer" maxlength="20" required /></td>
				</tr>

				<tr>
					<td colspan="2">
					<textarea name="contents" cols="80" rows="10" ></textarea>
					<!-- TEXTAREA tag 사이에 아무것도 없어야 함  --> 
						<script>
						CKEDITOR.replace('contents');
						</script>
					</td>
				</tr>
			</tbody>
		</table>
		
		<p>
			<input type="BUTTON" value="목록" onclick="location.href='<c:out value = "list" />'" />
			<input type="SUBMIT" value="글쓰기" />
		</p>
		
	</form>
	
</body>
</html>