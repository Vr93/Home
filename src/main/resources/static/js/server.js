

	$(document).ready(function(){
       	    Version_Text();
       	    //getCPUInformation();
       	    getStorage();
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

function getStorage(){
	$.ajax({
		url: "/server/storage",
		method: "GET",
		success: function(data) {
		    $('#server_memoryInfo').empty();

		    var filePlace = [];
		    var oneMBlocks = [];
		    var used = [];
		    var available = [];
		    var usePercentage = [];
		    var mountedOn = [];
		    if(data.length > 0){
		    var headers = data[0].split(",");
		    filePlace.push(headers[0]);
		    oneMBlocks.push(headers[1]);
		    used.push(headers[2]);
		    available.push(headers[3]);
		    usePercentage.push(headers[4]);
		    mountedOn.push(headers[5]);
		    }
		    for(var i = 1; i < data.length; i++){
		    var dataSplit = data[i].split(",");
		     filePlace.push(dataSplit[0]);
             oneMBlocks.push(dataSplit[1]);
             used.push(dataSplit[2]);
             available.push(dataSplit[3]);
             usePercentage.push(dataSplit[4]);
             mountedOn.push(dataSplit[5]);
		    }
            var tableValues = [
                          filePlace,
                          oneMBlocks,
                          used,
                          available,
                          usePercentage,
                          mountedOn]

                    var tableData = [{
                      type: 'table',
                      header: {
                        values: [
                        ["Filesystem"],
                        ["1M-blocks"],
                        ["Used"],
                        ["Available"],
                        ["Use(%)"],
                        ["Monted On"]
                        ],
                        align: "center",
                        line: {width: 1, color: 'black'},
                        fill: {color: "grey"},
                        font: {family: "Arial", size: 12, color: "white"}
                      },
                      cells: {
                        values: tableValues,
                        align: "center",
                        line: {color: "black", width: 1},
                        font: {family: "Arial", size: 11, color: ["black"]}
                      }
                    }]
                    $('#server_memoryInfo').empty();
                    Plotly.plot('server_memoryInfo', tableData);

                //$('#server_memoryInfo').append("<p>" + data[i] + "</p>");


		},
		error: function(data) {
			$('#server_memoryInfo').empty();
		    var sData;
            for(var i = 0; i < data.length; i++){
                 if(i > 1){
                 sData = sData + "<p class=\"text-center text-danger\">" + data[i] + "</p>";
                 }
                 else{
                  sData = "<p class=\"text-center text-danger\">" + data[i] + "</p>";
                 }
            }
             $('#server_memoryInfo').html(sData);
		}
	});
};




	$(document).ready(function(){
	   //getServerFanRunning();
	   //setInterval(getServerFanRunning, 1000);
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
	   //getServerCPUTemp();
	   //setInterval(getServerCPUTemp, 1000);
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
	  // getServerFanSetpoint();
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




