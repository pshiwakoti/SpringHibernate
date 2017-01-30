function validateAndSubmit() {

	if ($('[name="firstName"]').val() == "") {
		alert("Please provide your first name!");
		document.signInForm.firstName.focus();
		return false;
	}

	if ($('[name="lastName"]').val() == "") {
		alert("Please provide your last name!");
		document.signInForm.signInForm.focus();
		return false;
	}

	//document.signInForm.address.value
	if ($('[name="address"]').val() == "") {
		alert("Please provide a address");
		document.signInForm.address.focus();
		return false;
	}

	if (isNaN($('[name="age"]').val())) {
		alert("Please provide your age in number format");
		return false;
	}

	validateEmail();
	return (true);

}

function validateEmail()
{
   var emailID = document.signInForm.email.value;
   atpos = emailID.indexOf("@");
   dotpos = emailID.lastIndexOf(".");
   
   if (atpos < 1 || ( dotpos - atpos < 2 )) 
   {
      alert("Please enter correct email ID")
      document.signInForm.email.focus() ;
      return false;
   }
   return( true );
}

function addUser(){
	
	if(validateAndSubmit()){
	  
		var jsonObj = {};
		jsonObj.firstName = $('[name="firstName"]').val();
		jsonObj.lastName = $('[name="lastName"]').val();
		jsonObj.address = $('[name="address"]').val();
		var age = $('[name="age"]').val();
		jsonObj.age = parseInt(age);
		jsonObj.email = $('[name="email"]').val();
		jsonObj.password=$('[name="password"]').val();
		jsonObj = JSON.stringify(jsonObj);
		
		var serviceUrl = "/SpringMVC/customer";
		var resutl = getServiceResponse(serviceUrl, jsonObj, "POST");
		if (resutl) {
			alert("The user is added successfully!!");
		}
		
		getAllUsers(resutl);
		
	}
	
}


function getAllUsers(jsonResults) {
    $("#data tbody").empty();
		if (jsonResults != null) {
		var content = '<tbody>';
		for (var i = 0; i < jsonResults.length; i++) {

			content += '<tr><td>' + jsonResults[i].id + '</td><td>'
					+ jsonResults[i].firstName + '</td><td>'
					+ jsonResults[i].lastName + '</td><td>'
					+ jsonResults[i].address + '</td><td>'
					+ jsonResults[i].email + '</td><td>' + jsonResults[i].age
					+ '</td></tr>';
		}

	}
	content += '</tbody>';
	$('#data').append(content);
}



function getServiceResponse(url, jsonObj, reqType) {
	{

		var strReturn = null;
		if (url != "" && reqType != "") {
			if (reqType == "POST") {
				$.ajax({
					url : url,
					type : "POST",
					data : (jsonObj),
					cache : false,
					global : false,
					dataType : "json",
					async : false,
					contentType : "application/json; charset=UTF-8",
					crossDomain : true,
					headers : {
						"Accept" : "application/json; charset=UTF-8"
					},
					// error: function(jqXHR, textStatus, errorThrown)
					// {alert(errorThrown);},
					success : function(data) {
						strReturn = data;

					}
				});
			} else if (reqType == "GET") {
				$.ajax({
					url : url,
					type : "GET",
					dataType : "json",
					cache : false,
					async : false,
					contentType : "application/json; charset=UTF-8",
					crossDomain : true,
					headers : {
						"Accept" : "application/json; charset=UTF-8"
					},
					// error: function(jqXHR, textStatus, errorThrown)
					// {alert(errorThrown);},
					success : function(data) {
						strReturn = data;
					}
				});
			} else {
				alert("getServiceResponse :: Invalid Request Type.");
			}
			if (strReturn) {

				return strReturn;
			}

		} else
			alert("Please enter a URL AND JSON string to post.");
	}
}






