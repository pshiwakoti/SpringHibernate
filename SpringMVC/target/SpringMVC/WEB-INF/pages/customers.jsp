<html>

<head>
<title>Customer Details</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>


	<c:forEach items="${customerList}" var="cust">
		<div id="customerRow">
			<p>ID: ${cust.id}</p>
			<p>first Name: ${cust.firstName}</p>
			<p>Last Name: ${cust.lastName}</p>
			<p>Email: ${cust.email}</p>
			<p>Address: ${cust.address}</p>
			<p>Age: ${cust.age}</p>
		</div>
	</c:forEach>
</body>

</html>