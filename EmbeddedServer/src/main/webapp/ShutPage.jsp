<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EmbeddedServer ShutDown Page</title>
</head>
<body onload="document.form.submit();">

	<form name="form" method="post" action="Admin_Servlet_ShutDown">
		<fieldSet>
			<table>
				<tr>
					<td>Shutdown Database Server:</td>				
					<td colspan="2"><div id="infoshutdowndatabase" style="color: blue;"></div></td>
				</tr>
			</table>
		</fieldSet>
	</form>

</body>
</html>