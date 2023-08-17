<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
</head>

<body>
	<div>
		<h3>Registration Form</h3>



		<spring:form action="/storeData" method="POST" modelAttribute="user">
              Name:  <spring:input path="username" type="text"
				required="required" class="m-2" placeholder="enter your name" />
			<br />
               Email: <spring:input path="mail" type="email"
				required="required" class="m-2" placeholder="enter your email" />
			<br />
               
	Password: <spring:input class="m-2" path="password" required="required"
				placeholder="enter your password" />
			<br />
			<input class="rounded border border-white text-white bg-primary"
				type="submit" value="Submit" />
		</spring:form>


		<p style="display: none;" id="errMsg">Error occured</p>

	</div>



</body>

</html>