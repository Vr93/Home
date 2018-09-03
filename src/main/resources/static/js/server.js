
    function openDocument(link){
    $("#doc").load(link);
    }


	$(document).ready(function(){
	    Version_Text();
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


	$(document).ready(function(){
	   getServerPlatformName();
	});

	function getServerPlatformName(){
	$.ajax({
		url: "/server/platformname",
		method: "GET",
		success: function(data) {
		    $('#server_platformName').empty();
		    $('#server_platformName').html("<p class=\"text-center\"> Platform name: " + data + "</p>");
		},
		error: function(e) {
			$('#server_platformName').empty();
		    $('#server_platformName').html(e.responseText);
		}
	});
};



	$(document).ready(function(){
	   getServerSerialNumber();
	});

	function getServerSerialNumber(){
	$.ajax({
		url: "/server/serialnumber",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Serial number: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};




	$(document).ready(function(){
	   getServerCPURevision();
	});

	function getServerCPURevision(){
	$.ajax({
		url: "/server/cpurevision",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> CPU Revision: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};



	$(document).ready(function(){
	   getServerCPUArchitecture();
	});

	function getServerCPUArchitecture(){
	$.ajax({
		url: "/server/cpuarchitecture",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> CPU Architecture: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};



	$(document).ready(function(){
	   getServerCPUPart();
	});

	function getServerCPUPart(){
	$.ajax({
		url: "/server/cpupart",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> CPU Part: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};



	$(document).ready(function(){
	   getServerCPUVoltage();
	});

	function getServerCPUVoltage(){
	$.ajax({
		url: "/server/cpuvoltage",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> CPU Voltage: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};



	$(document).ready(function(){
	   getModelName();
	});

	function getModelName(){
	$.ajax({
		url: "/server/modelname",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Model Name: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getProcessor();
	});

	function getProcessor(){
	$.ajax({
		url: "/server/processor",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Processor: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getHardware();
	});

	function getHardware(){
	$.ajax({
		url: "/server/hardware",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Hardware: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getHardwareRevision();
	});

	function getHardwareRevision(){
	$.ajax({
		url: "/server/hardwarerevision",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Hardware Revision: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   isHardFloatABI();
	});

	function isHardFloatABI(){
	$.ajax({
		url: "/server/hardfloatabi",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Hard Float ABI: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getBoardType();
	});

	function getBoardType(){
	$.ajax({
		url: "/server/boardtype",
		method: "GET",
		success: function(data) {
		    $('#server_hardwareInfo').append("<p class=\"text-center\"> Board Type: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_hardwareInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getTotalMemory();
	});

	function getTotalMemory(){
	$.ajax({
		url: "/server/totalmemory",
		method: "GET",
		success: function(data) {
		    $('#server_memoryInfo').append("<p class=\"text-center\"> Total Memory: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_memoryInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getUsedMemory();
	});

	function getUsedMemory(){
	$.ajax({
		url: "/server/usedmemory",
		method: "GET",
		success: function(data) {
		    $('#server_memoryInfo').append("<p class=\"text-center\"> Used Memory: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_memoryInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getFreeMemory();
	});

	function getFreeMemory(){
	$.ajax({
		url: "/server/freememory",
		method: "GET",
		success: function(data) {
		    $('#server_memoryInfo').append("<p class=\"text-center\"> Free Memory: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_memoryInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getSharedMemory();
	});

	function getSharedMemory(){
	$.ajax({
		url: "/server/sharedmemory",
		method: "GET",
		success: function(data) {
		    $('#server_memoryInfo').append("<p class=\"text-center\"> Shared Memory: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_memoryInfo').html(e.responseText);
		}
	});
};

$(document).ready(function(){
	   getMemoryBuffers();
	});

	function getMemoryBuffers(){
	$.ajax({
		url: "/server/memorybuffers",
		method: "GET",
		success: function(data) {
		    $('#server_memoryInfo').append("<p class=\"text-center\"> Memory Buffers: " + data + "</p>");
		},
		error: function(e) {
		    $('#server_memoryInfo').html(e.responseText);
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




