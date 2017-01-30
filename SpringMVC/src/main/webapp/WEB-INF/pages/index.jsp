<html>
<head>
<title>SignUp Page</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jsLibrary/commonLibrary.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/jsLibrary/jquery-1.4.4.min.js"></script>
</head>

<body>

	<form name="signInForm" action="#" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName"></td>
			</tr>

			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName"></td>

			</tr>
			<tr>

				<td>Address</td>
				<td><input type="text" name="address"></td>
			</tr>

			<tr>
				<td>Age</td>
				<td><input type="text" name="age"></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td><input type="reset" value="clear"></td>
				<td><input type="button" onclick="javascript:addUser();"
					value="signup"></td>


			</tr>
		</table>
	</form>

	<div id="content">
		<table id="data">
			<thead>
				<tr>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Email</th>
					<th>age</th>
				</tr>
			</thead>
			
		</table>
	</div>


</body>

</html>