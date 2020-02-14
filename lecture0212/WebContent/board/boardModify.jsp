<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"></meta>
	<title>MODIFY</title>
	<!-- HTML5 방식 : STYLE FILE 참조 -->
	<script type = "text/javascript" src="<c:url value ="/ckeditor/ckeditor.js" />" ></script>
	
	<!-- HTML4 방식 : STYLE 직접 지정-->
	<style type="text/css">
		* {font-size: 9pt;}
		<!-- * {} : <body> </body> 사이 모든 태그에 지정 -->
		
		p {width: 600px; text-align: right;}
		<!-- p {} : p 태그 내부에 지정 -->
		
		table tbody tr th {background-color: gray;}
		<!-- table tbody tr th { } : table tbody tr th 태그 내부 지정 -->
		 
	</style>
	
</head>
<body>

	<form action="<c:out value = "modify" />" method="POST" >
		<input type="hidden" name="idx" value="<c:out value = "${m.get_idx() }" />" />
		<input type="hidden" name="pageNum" value="<c:out value = "${m.get_pageNum() }" />" />
		<input type="hidden" name="searchType" value="<c:out value = "${m.get_searchType() }" />" />
		<input type="hidden" name="searchText" value="<c:out value = "${m.get_searchText() }" />" />
		
		<table border="1" summary="게시판 수정 폼">
			<caption>게시판 수정 폼</caption>
			
			<colgroup>
				<col width="100" />
				<col width="500" />
			</colgroup>
			
			<tbody>
				<tr>
					<th align="center">제목</th>
					<td>
					<input type="text" name="subject" size="80" maxlength="100" value="<c:out value = "[${m.get_idx() }번글 ] ${m.get_subject() }" />"  /></td>
				</tr>
				
				<tr>
					<th align="center">작성자</th>
					<td><input type="text" name="writer" maxlength="20" value="<c:out value = "${m.get_writer() }" />" /></td>
				</tr>
				
				<tr>
					<td colspan="2">
						<textarea name="contents" cols="80" rows="10" >
						<c:out value = "${m.get_contents }" escapeXml = "false" />
						</textarea>
						<script>CKEDITOR.replace('contents');</script>
					</td>
				</tr>
			</tbody>
		</table>
		
		<p>
			<input type="button" value="목록" onclick="location.href='list'" />
			<input type="submit" value="글수정" />
		</p>
	
	</form>
	
</body>
</html>