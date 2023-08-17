<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="addingEmp" method="post">
		Name: <input type="text" name="name"/><br>
		Email: <input type="text" name="email"/><br>
		password: <input type="text" name="password"/><br>
		<button onclick="alertName()" type="submit">ADD</button>
	</form>
	<script type="text/javascript">
		function alertName() {
			alert("Employee added successfully");
		}
	</script>
</body>
</html>