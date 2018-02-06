<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

function goPage(pageNo){
	$("#page").val(pageNo);
	submitFlag = true;
	$("#ticketForm").submit();
}

</script>
<form action="<%=request.getContextPath() %>/schedule/ticketlist.do" method="post" onsubmit="checkVal();" id="ticketForm">
	<table border="1">
    	  <tr>
    	    <th>티켓 번호</th>
    	    <th>스케줄 번호</th>
    	    <th>좌석번호</th>
    	    <th>상영관 이름</th>
    	  

	  <tbody>
	  	<c:forEach items="${tlist}" var="ticket">
		  	<tr>
		  		<td>${ticket.ticNo }</td>
		  		<td>${ticket.schNo }</td>
		  		<td>${ticket.ticseatno }</td>
		  		<td>${ticket.scname }</td>
		  	 
		  	</tr>
	  	</c:forEach>
	  </tbody>
	  </table>
	  </form>
	 