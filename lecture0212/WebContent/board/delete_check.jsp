<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DELETE CHECK</title>
<style>
	.ck {
		width : 120px;
		height : 75px;
		border-radius : 20pt;
		border : 2px solid black;
		transition: all 0.5s;
  		cursor: pointer;
		
	}
	#delete {
		color :  #f44336;
		font-size: 20px;
		border: 2px solid #f44336;
	}
</style>

</head>
<body>
idx = ${m.get_idx() }
<H1><FONT COLOR = RED > 정말 지우시겠습니까?</FONT></H1>
<FORM ACTION = "delete" METHOD = "POST" >
	<INPUT TYPE = "HIDDEN" VALUE = "${m.get_idx() }" name = "idx" />
	<INPUT CLASS = "ck" ID = "delete" TYPE = "SUBMIT" VALUE = "삭제하기" />
	<INPUT CLASS = "ck" TYPE = "BUTTON" VALUE = "목록으로 돌아가기" ONCLICK = "location.href = 'list'" />
</FORM>
</body>
</html>