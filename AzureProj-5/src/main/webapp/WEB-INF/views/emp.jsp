

<%@page import="com.clayfin.model.Employee"%>
<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="java.util.List"%>
<%@page import="com.clayfin.model.Registration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<style>@import url('https://fonts.googleapis.com/css2?family=Bree+Serif&family=Caveat:wght@400;700&family=Lobster&family=Monoton&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&family=Playfair+Display+SC:ital,wght@0,400;0,700;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,700&family=Roboto:ital,wght@0,400;0,700;1,400;1,700&family=Source+Sans+Pro:ital,wght@0,400;0,700;1,700&family=Work+Sans:ital,wght@0,400;0,700;1,700&display=swap');

.container {}

.sub-container {
    background-color: #494341;
    height: 100vh;
}

.box {
    background-color: #DEF5F2;
    height: cover;
    width: cover;
    padding: 20px;
    border-radius:10px;
    
}</style>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>
     <div>   
	        	<% List<Registration> trc =(List<Registration>) request.getAttribute("employees"); 
				if(trc.size()!=0){%>
                	<caption><h2>List of Employees registered</h2></caption>
			        <table border="1" cellpadding="5">
			        <tr>
			            <th>Register ID</th>
			            
			            <th>Email</th> 
			       
			            <th>Role</th> 
			            <th>Regsitered Time</th> 
			            
			        </tr>
	<% 
		for(Registration i :trc){
			//out.println(i);
			%>
			
	            <tr>
	            	<td><%=i.getUser().getId() %></td>
	                <td><%=i.getUser().getEmail()() %></td>
	                <td><%=i.getRole() %></td>
	                <td><%=i.getRegisterTime()%></td>
	                
	                
	                
	                
	            </tr>
			<%
			
		}
	}
		else{
			%>
			
			<h5>No Registrations Found<h5>
			<% 
		}
		
		 
%> 		</table>

	<form action="/authorizeEmp" method="POST">	
	ID:<input type="number" class=" m-3" name="id" placeholder="enter the registration id"/>
	ROLE:<input type="text" placeholder="enter the role" name="role"/><br>
	<input type="submit" value="submit" />
</form>

               
        </div>
</body>
</html>