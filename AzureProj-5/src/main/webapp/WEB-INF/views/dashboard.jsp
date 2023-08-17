<%@page import="com.clayfin.model.User"%>

<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="java.util.List"%>
<%@page import="com.clayfin.model.Registration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<button id="myButton" onclick="handleButtonClick()">CHECK IN</button>
	
	<button id="myButton" onclick="leaveRequests()">Leave Requests</button>
	<!-- <button id="leaveId" onclick="leave()">Apply For Leave</button>
	<button id="emgLeave" onclick="emgLeave()">Emergency Leave</button> -->
	<button  onclick="addEmp()">Add Employee</button>
	
	<div>
		<%
		List<User> trc = (List<User>) request.getAttribute("emps");
		if (trc.size() != 0) {
		%>
		<caption>
			<h2>Employees</h2>
		</caption>
		<table border="1" cellpadding="5">
			<tr>
				<th>employee name</th>
				<th>Assign task</th>
			</tr>
			<%
			for (User i : trc) {
				//out.println(i);
			%>

			<tr>
				<td><%=i.getUsername()%></td>

				<td>
					<form action="/manager/asignTask/" method="post">
						<input type="textarea" name="task" placeholder="enter a task "/>
						<input type="hidden" name="id" value=<%= i.getUserId() %>/>
						<input type="submit" onclick="alertName()" />
					</form>
				</td>
			</tr>


			<%
			}
			} else {
			%>

			<h5>
				No Employees Found
				<h5>
					<%
					}
					%>
				
		</table>



	</div>


	<script>
	
	function alertName() {
		alert("Task assigned successfully");
	}
		var defaultButtonText = "CHECk IN";
		var isButtonClicked = false;
		var leaveButton = document.getElementById("leaveId");
		function leaveRequests() {
			window.open('http://localhost:8080/manager/leaveRequests',
					name = self);
		}

		function leave() {

			window.open('http://localhost:8080/manager/leave', name = self);
		}

		function emgLeave() {

			window.open('http://localhost:8080/manager/emgLeave', name = self);
		}
		function addEmp() {

			window.open('http://localhost:8080/manager/addEmp', name = self);
		}

		function handleButtonClick() {
			var button = document.getElementById("myButton");
			var currentdate = new Date();
			console.log(currentdate);
			if (isButtonClicked) {
				button.innerHTML = defaultButtonText;
				isButtonClicked = false;
			} else {
				button.innerHTML = "CHCEK OUT";
				isButtonClicked = true;
			}

			if (button.innerHTML === "CHCEK OUT") {
				var checkInReq = new XMLHttpRequest();
				checkInReq.open("POST", "/manager/dashboard", true);
				checkInReq.setRequestHeader("Content-Type", "application/json");
				checkInReq.onreadystatechange = function() {
					console.log("ckeck In");
					if (checkInReq.readyState === XMLHttpRequest.DONE
							&& checkInReq.status === 200) {
						console.log("Data saved successfully!");
					}
				};
				var data = JSON.stringify({
					date : currentdate,
					status : "CHECKIN"
				});
				checkInReq.send(data);
			} else {
				var checkInReq = new XMLHttpRequest();
				checkInReq.open("POST", "/manager/dashboard", true);
				checkInReq.setRequestHeader("Content-Type", "application/json");
				checkInReq.onreadystatechange = function() {
					console.log("ckeck out");
					if (checkInReq.readyState === XMLHttpRequest.DONE
							&& checkInReq.status === 200) {
						console.log("Data saved successfully!");
					}
				};
				var data = JSON.stringify({
					date : currentdate,
					status : "CHECKOUT"
				});
				checkInReq.send(data);
			}
		}
	</script>
</body>
</html>