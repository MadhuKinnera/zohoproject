<%@page import="com.clayfin.model.Leavetable"%>


<%-- 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 --%>
<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="java.util.List"%>
<%@page import="com.clayfin.model.Registration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
   
    
<!DOCTYPE html>
<html>


<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous" />
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</head>
<body>
<div class="d-flex ">
	 <div>
	<% List<Leavetable> trc =(List<Leavetable>) request.getAttribute("leavereq"); 
	if(trc.size()!=0){
		
		
		
		%>
		<caption><h2>List of Leave request</h2></caption>
        <table border="1" cellpadding="5">
        <tr>
            <th>employee name</th>
            <th>Leave Id</th>
            <th>reason</th>
            <th>applied Time</th>
            <th>Days</th>
            <th></th>
        </tr>
	<% 
		for(Leavetable i :trc){
			//out.println(i);
			%>
			
	            <tr>
	                <td><%=i.getUser().getUsername() %></td>
	                <td><%=i.getId() %></td>
	                <td><%=i.getReason() %></td>
	                <td><%=i.getTimestamp() %></td>
	                <td><%=i.getDays() %></td>
	            	<td>
	            		<button onclick="approve(<%=i.getId()%>)">Approve</button>
	            		<button onclick="reject(<%=i.getId()%>)">Reject</button>
	            	</td>
	            </tr>
	        
	    
			<%
			
		}
	}
		else{
			%>
			
			<h5>No Leaves recorded<h5>
			<% 
		}
		
		 
%>
                
      </table>
      
      
      
      
      	<% List<Leavetable> trcs =(List<Leavetable>) request.getAttribute("emgReq"); 
	if(trcs.size()!=0){
		
		
		
		%>
		<caption><h2>List of Emergency Leaves</h2></caption>
        <table border="1" cellpadding="5">
        <tr>
            <th>employee name</th>
            <th>Leave Id</th>
            <th>reason</th>
            <th>applied Time</th>
            <th>Days</th>
            <th></th>
        </tr>
	<% 
		for(Leavetable i :trcs){
			//out.println(i);
			%>
			
	            <tr>
	                <td><%=i.getUser().getUsername() %></td>
	                <td><%=i.getId() %></td>
	                <td><%=i.getReason() %></td>
	                <td><%=i.getTimestamp() %></td>
	                <td><%=i.getDays() %></td>
	            	<td>
	            		<button onclick="approve(<%=i.getId()%>)">Approve</button>
	            		<button onclick="reject(<%=i.getId()%>)">Reject</button>
	            	</td>
	            </tr>
	        
	    
			<%
			
		}
	}
		else{
			%>
			
			<h5>No Emergency Leaves recorded<h5>
			<% 
		}
		
		 
%>
                
      </table>
      
      <script type="text/javascript">
      
      function approve(id){
    	  window.open('http://localhost:8080/manager/leaveApprove/'+id, name = self); 
      }
      
      function reject(id){
    	  window.open('http://localhost:8080/manager/leaveReject/'+id, name = self);   
      }
      
      </script>
      
      
      
                	
</div>
</div>
            
       
</body>
</html>