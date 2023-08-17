<%@page import="com.clayfin.model.Task"%>
<%@page import="java.util.List"%>
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
	<button id="leaveId" onclick="leave()">Apply For Leave</button>
	<button id="emgLeave" onclick="emgLeave()">Emergency Leave</button>

	<div>
		<%
		List<Task> trc = (List<Task>) request.getAttribute("tasks");
		if (trc.size() != 0) {
		%>
		<caption>
			<h2>My Pending Tasks</h2>
		</caption>
		<table border="1" cellpadding="5">
			<tr>
				<th>Task</th>
				<th>Assigned at</th>
			</tr>
			<%
			for (Task i : trc) {
				//out.println(i);
			%>

			<tr>
				<td><%=i.getTaskName()%></td>
				<td><%=i.getAssignedTime()%></td>


			</tr>


			<%
			}
			} else {
			%>

			<h5>
				No Tasks Found
				<h5>
					<%
					}
					%>
				
		</table>

	</div>


	<script>
		var defaultButtonText = "CHECk IN";
		var isButtonClicked = false;
		var leaveButton = document.getElementById("leaveId");
		function leave() {

			window.open('http://localhost:8080/employee/leave', name = self);
		}

		function emgLeave() {

			window.open('http://localhost:8080/employee/emgLeave', name = self);
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
				checkInReq.open("POST", "/employee/existedEmp", true);
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
				checkInReq.open("POST", "/employee/existedEmp", true);
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