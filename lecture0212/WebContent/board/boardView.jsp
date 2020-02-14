<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<fmt:requestEncoding value = "UTF-8" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"></meta>
	<title>글 상세보기</title>
	
	<style>
		* {font-size: 9pt;}
		.btn_align {width: 800px; text-align: right;}
		<!-- 이 style을 사용하고자 하는 tag 내부에 class = "btn_align" 입력하여 사용 -->
		table tbody tr th {background-color: gray;}
	</style>

</head>

<body>
	<table border="1" summary="게시판 상세조회">
		<caption>게시판 상세조회</caption>
		<colgroup>
			<col width="100" />
			<col width="300" />
			<col width="100" />
			<col width="300" />
		</colgroup>
		<tbody>
			<tr>
				<th align="center" colspan = "1">제목</th>
				<td colspan = "3"><c:out value="${m.get_subject() }" /></td>
			</tr>
			<tr>
				<th align="center">작성자</th>
				<td><c:out value="${m.get_writer() }" /></td>
				<th align="center">조회수</th>
				<td><c:out value="${m.get_hit() }" /></td>
			</tr>
			<tr>
				<th align="center" colspan = "1">등록 일시</th>
				<td colspan = "3"><c:out value="${m.get_reg_date() }" /></td>
			</tr>
			<tr>
				<td colspan="4" style="padding: 10pt"><c:out value="${m.get_contents() }" escapeXml = "false" /></td>
			</tr>
		</tbody>
		
		
	</table>
	
	<p class="btn_align">
		<input type="button" value="목록" onclick="location.href='list?idx=${m.get_idx() }&pageNum=${m.get_pageNum() }&searchType=${m.get_searchType() }&searchText=${m.get_searchText() }'"/>
		<input type="button" value="수정" onclick="location.href='modify?idx=${m.get_idx() }&pageNum=${m.get_pageNum() }&searchType=${m.get_searchType() }&searchText=${m.get_searchText() }'" />
		<input type="button" value="삭제" onclick="location.href='delete?idx=${m.get_idx() }&pageNum=${m.get_pageNum() }&searchType=${m.get_searchType() }&searchText=${m.get_searchText() }'" />
	</p>
	
</body>
</html>