<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="com.wjh.model.Model" %>
    
    <%
    
    /*
    Model m = (Model) request.getAttribute("m");
    
    String pageNum = m.get_pageNum();
    String searchType = m.get_searchType();
	String searchText = m.get_searchText();
    */
	
	/*
	System.out.println("pageNum : " + pageNum);
	System.out.println("searchText:" + searchText + "___");
	System.out.println("searchType:" + searchType + "___");
	System.out.println("totalCount : " + totalCount);
	*/
	
	%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jstl/fmt" %>

<fmt:requestEncoding value = "UTF-8" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LIST</title>
<style type = "text/css">
	* { font-size : 9pt; }
	p { width : 600px; text-align : right; }
	table thead tr th {background-color: gray;}
</style>
</head>

<body>

	<!-- 게시글 검색 -->
	<form action = "list"  method="GET"  >
	<p>
		<select name="searchType">
			<option value="ALL" selected = "selected" >전체검색</option>
			<option value="SUBJECT"  
				<c:if test = "${m.get_searchType() eq 'SUBJECT' }"> selected = "selected" </c:if>>
				제목</option>
			<option value="WRITER" 
				<c:if test = "${m.get_searchType() eq 'WRITER' }"> selected = "selected" </c:if>>
				작성자</option>
			<option value="CONTENTS" 
				<c:if test = "${m.get_searchType() eq 'CONTENTS' }"> selected = "selected" </c:if>>
				내용</option>
		</select>
		
		<input type="text" name="searchText"  VALUE = "<c:out value = "${m.get_searchText() }" />" />
		<input type="submit" value="검색" />
	</p>
	</form>
	
	<table border="1" summary="게시판 목록">
		<caption>게시판 목록</caption>
		<!-- CAPTION : TABLE의 제목, 가장 먼저 써줘야 함 -->
		
		<colgroup>
			<col width="50" />
			<col width="300" />
			<col width="80" />
			<col width="100" />
			<col width="70" />
		</colgroup>
		<!-- COLGROUP : COLOMN GROUP, TD 속성 일괄 지정 -->
		
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록 일시</th>
				<th>조회수</th>
			</tr>
		</thead>
		<!-- THEAD : 제목 영역 -->

		<tbody>
		<c:choose>
		<c:when test = "${count == 0 }" >
			<tr>
				<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
			</tr>
		</c:when>
		
		<c:otherwise>
			<c:forEach var = "board" items = "${list}" varStatus = "sts" >
				<tr>
				
				<!-- 글번호 -->
				<td align="center">
					<c:out value = "${count-(sts.index+1)+1-(m.get_pageNum()-1)*m.get_list_count()}" />
				</td>
				
				<!-- 글제목 -->
				<td>
				<a href="<c:url value = "/board/view?idx=${board.get_idx() }&pageNum=${m.get_pageNum() }&searchType=${m.get_searchType() }&searchText=${m.get_searchText() }" />" >
					<c:out value = "${board.get_subject()}" />
				</a>
				
				</td>
				
				<!-- 작성자 -->
				<td align="center"><c:out value = "${board.get_writer() }" /></td>
				
				<!-- 작성일자 -->
				<td align="center"><c:out value = "${fn:substring(board.get_reg_date(),0,10) }"/></td>
				
				<!-- 조회수 -->
				<td align="center"><c:out value = "${board.get_hit() }" /></td>
				
				
			</tr>
			</c:forEach>
		</c:otherwise>
		</c:choose>
			

		</tbody>
		<!-- TBODY : 몸통 영역, 데이터 -->

		<tfoot>
			<tr>
				<td align="center" colspan="5">
					<c:out value = "${p_navi }" escapeXml = "false" />
				</td>
			</tr>
		</tfoot>
		<!-- TFOOT : 마지막 영역 -->
		
	</table>
	
	<p>
		<input type="button" value="목록"  onclick="location.href='<c:url value = "/board/list" />'"/>
		<input type="button" value="글쓰기"  onclick="location.href='<c:url value = "/board/write" />'" />
	</p>
 <hr>
 	<!-- 
 	c:out  value__ <br>
 	count = <c:out value = "${count }" /> <br />
 	p_navi = <c:out value = "${p_navi}" /> <br />
 	searchType =	<c:out value = "${m.get_searchType() }" /> <br />
	searchText = <c:out value = "${m.get_searchText() }" /> <br />
 	pageNum = <c:out value = "${m.get_pageNum() }" /> <br />
 	get_list_count = <c:out value = "${m.get_list_count()}" /> <br /> 
 	-->
 		
 
</body>

</html>