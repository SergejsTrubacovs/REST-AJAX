var $ = function(id) {
	return document.getElementById(id);
}

function getHTTPObject() {
	var xhr = false;
	if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			xhr = new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e) {
				xhr = false;
			}
		}
	}
	return xhr;
}

function getEmployee() {
	
	//Construct an XMLHttpRequest object	
	var request = getHTTPObject();
	URL="http://localhost:8080/A00212878_ClientSide42/rest/employees/";
	//If object creation was successful, set up and make request
	if (request) {
		request.onreadystatechange = function() {
			parseResponse(request);
		};
		request.open("GET", URL, true);
		request.setRequestHeader('Accept','application/json');
		request.send(null);
	}
}

var data;
function parseResponse(request) {
	if (request.readyState == 4) {
		if (request.status == 200 || request.status == 304) {
			data = JSON.parse(request.responseText);
			console.log(data);
			var dataArray = data.employee;

			for(var i = 0; i < dataArray.length; i++) {
												
			var name = dataArray[i].name;
			var surname = dataArray[i].surname;
			
			var ulEmp = document.createElement("ul");		
			var liEmp = document.createElement("li");
			
			var li= document.createElement("a");
			li.setAttribute("href","#body")
			li.setAttribute("onClick","getListinfo("+i+")");
			li.innerHTML =  name+" "+surname+"<br>";			
			main.appendChild(li);
			console.log(li);
			
			ulEmp.appendChild(liEmp);
			main.appendChild(li);					
			}
		}
	}	
}

function getListinfo(i) {
	var dataE= data.employee[i];	
	var details = document.getElementById("details");
	
	var id = dataE.id;
	var name = dataE.name;
	var surname = dataE.surname;
	var department = dataE.department;
	var position = dataE.position;
	var rate = dataE.rate;
	var hours = dataE.hours;
	
	
	var header = document.createElement("h2");
	var text = document.createTextNode(name+" "+surname+" Details");
	
	var list = document.createElement("ul");
	var li1 = document.createElement("li");
	var li2 = document.createElement("li");
	var li3 = document.createElement("li");
	var li4 = document.createElement("li");	
	var li5 = document.createElement("li");	
	var li6 = document.createElement("li");	
	var li7 = document.createElement("li");	
	var li8 = document.createElement("li");	

	var text1 = document.createTextNode("ID: "+id);
	var text2 = document.createTextNode("Name: "+name);
	var text3 = document.createTextNode("Surname: "+surname);
	var text4 = document.createTextNode("Department: "+department);
	var text5 = document.createTextNode("Position: "+position);
	var text6 = document.createTextNode("Rate: "+rate);
	var text7 = document.createTextNode("Hours: "+hours);
	var text8 = document.createTextNode("Gross Pay: \u20ac"+rate * hours);
	
	li1.appendChild(text1);
	li2.appendChild(text2);
	li3.appendChild(text3);
	li4.appendChild(text4);
	li5.appendChild(text5);
	li6.appendChild(text6);
	li7.appendChild(text7);
	li8.appendChild(text8);
	
	header.appendChild(text);

	list.appendChild(li1);
	list.appendChild(li2);
	list.appendChild(li3);
	list.appendChild(li4);
	list.appendChild(li5);
	list.appendChild(li6);
	list.appendChild(li7);
	list.appendChild(li8);

	while(details.firstChild) {
		details.removeChild(details.firstChild);
	}	
	details.appendChild(header);
	details.appendChild(list);
}

function getEmployeeDelete(filename) {
	
	//Construct an XMLHttpRequest object	
	var request = getHTTPObject();
	
	//If object creation was successful, set up and make request
	if (request) {
		
		request.open("DELETE", filename, true);
		request.send(null);
	}	
}

function deleteEmp() {
	var id = document.getElementById("id").value;
	getEmployeeDelete("http://localhost:8080/A00212878_ClientSide42/rest/employees/"+id);
	console.log(id);
	alert("Employee with Id "+id+" deleted from DB");
	return false;
}
