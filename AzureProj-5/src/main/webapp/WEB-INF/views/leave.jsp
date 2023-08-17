<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="applyLeave" method="post">
		<input type="text" name="reason" placeholder="reason" /> <input
			type="number" name="days" placeholder="how many days" />
		<button id="id" type="submit" onclick="alertName()">Apply</button>

	</form>

	<script type="text/javascript">
		function alertName() {
			alert("Applied Leave successfully");
		}
	</script>

</body>
</html>