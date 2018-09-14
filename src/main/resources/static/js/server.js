

	$(document).ready(function(){
       	    Version_Text();
       	    getCPUInformation();
       	});

	function Version_Text(){
	$.ajax({
		url: "/version",
		method: "GET",
		success: function(data) {
		    $('#version').empty();
		    $('#version').html(data);
		},
		error: function(data) {
			console.log(data);
			$('#version').empty();
		    $('#version').html(data);
		}
	});
};

function getCPUInformation(){
	$.ajax({
		url: "/server/cpuinformation",
		method: "GET",
		success: function(data) {
		    $('#server_platformName').empty();
		    var sData;
		    for(var i = 0; i < data.length; i++){
                if(i > 1){
                sData = sData + "<p>" + data[i] + "</p>";
                }
                else{
                sData = "<p>" + data[i] + "</p>";
                }
		    }
		    $('#server_platformName').html(sData);
		},
		error: function(data) {
			$('#server_platformName').empty();
		    var sData;
            for(var i = 0; i < data.length; i++){
                 if(i > 1){
                 sData = sData + "<p class=\"text-center text-danger\">" + data[i] + "</p>";
                 }
                 else{
                  sData = "<p class=\"text-center text-danger\">" + data[i] + "</p>";
                 }
            }
             $('#server_platformName').html(sData);
		}
	});
};




	$(document).ready(function(){
	   getServerFanRunning();
	   setInterval(getServerFanRunning, 1000);
	});

	function getServerFanRunning(){
	$.ajax({
		url: "/serverFan/output",
		method: "GET",
		success: function(data) {
		    $('#serverFan_running').empty();
		    if(data == "true"){
		    $('#serverFan_running').empty();
		    $('#serverFan_running').html("<img src=\"/image/fan_icon_on.png\" class=\"img-responsive center-block\">");
		    }
		    else{
		    $('#serverFan_running').empty();
		    $('#serverFan_running').html("<img src=\"/image/fan_icon_off.png\" class=\"img-responsive center-block\">");
		    }

		},
		error: function(e) {
			$('#serverFan_running').empty();
		    $('#serverFan_running').html(e.responseText);
		}
	});
};



	$(document).ready(function(){
	   getServerCPUTemp();
	   setInterval(getServerCPUTemp, 1000);
	});
	function getServerCPUTemp(){
     $.ajax({
     url: "/server/cputemperature",
     method: "GET",
     success: function(data) {
     $('#serverFan_cpuTemp').empty();
     $('#serverFan_cpuTemp').html("<h4 class=\"text-center\"> CPU temperature: " + data + " °C</h4>");
     $('#serverFan_cpuTempInfo').empty();
     $('#serverFan_cpuTempInfo').html("<p class=\"text-center\"> CPU temperature: " + data + " °C</p>");
     },
     error: function(e) {
     $('#serverFan_cpuTemp').empty();
     $('#serverFan_cpuTemp').html(e.responseText);
     }
     });
     };



	$(document).ready(function(){
	   getServerFanSetpoint();
	});

	function getServerFanSetpoint(){
	$.ajax({
		url: "/serverFan/setpoint",
		method: "GET",
		success: function(data) {
		    $('#serverFan_setpoint').empty();
		    $('#serverFan_setpoint').html("<h4 class=\"text-center\"> Setpoint: " + data + " °C</h4>");
		},
		error: function(data) {
			console.log(data);
			$('#serverFan_setpoint').empty();
		    $('#serverFan_setpoint').html(data);
		}
	});
};



$( document ).ready(function() {

	// SUBMIT FORM
    $("#serverFan_setpoint_update").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		setServerFanSetpoint();
	});


    function setServerFanSetpoint(){
    	var formData = {
    		setpoint : $("#serverFan_setpoint_update_value").val(),
    	}
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/serverfan/updateSetpoint",
			data : JSON.stringify(formData),
			success : function(result) {
				$("#serverFan_setpoint_update_text").html(result);
				getServerFanSetpoint();
			},
			error : function(e) {
			console.log(e);
	            $("#serverFan_setpoint_update_text").html(e.responseText);
			}
		});
    }
})




