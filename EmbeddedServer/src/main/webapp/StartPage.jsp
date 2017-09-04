<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EmbeddedServer Start Page</title>
</head>
<body onload="document.frm.submit();">

	<form name="frm" method="post" action="Admin_Servlet_Start">
		<fieldSet>
			<table>
				<tr>
					<td>Start Database Server:</td>			
					<td colspan="2"><div id="infostartdatabase"	style="color: blue;"></div></td>
				</tr>
			</table>
		</fieldSet>

	</form>

</body>
</html>