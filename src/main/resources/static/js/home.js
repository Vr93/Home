$(document).ready(function(){
Version_Text();
A01_isOnline();
A01_lastValues();
A02_isOnline();
A02_lastValues();


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

function A01_lastValues(){
	$.ajax({
		url: "/A01/lastvalues",
		method: "GET",
		success: function(data) {
		    var time;
			var temperature;
			var humidity;
			var pressure;
            var obj = JSON.parse(data);
            temperature = obj.temp;
            humidity = obj.humidity;
            pressure = obj.pressure;
            time = obj.timestamp;

        $('#A01_lastValues').empty();
        var data = "<h3 class=\"text-center\">" + temperature + " °C </p>";
        data = data + "<h3 class=\"text-center\">" + humidity + " % </p>";
        data = data + "<h3 class=\"text-center\">" + pressure + " hPa </p>";
        data = data + "<h3 class=\"text-center\">" + time + "</p>";
        $('#A01_lastValues').html(data);
		},
		error: function(data) {
			$('#A01_isOnline').empty();
		    $('#A01_isOnline').html(data.responseText);
		}
	});
};

function A01_isOnline(){
	$.ajax({
		url: "/A01/isonline",
		method: "GET",
		success: function(data) {
		    $('#A01_isOnline').empty();
		    if(data == "false"){
		    $('#A01_isOnline').html("<p class=\"text-center text-danger\"> Device is offline! </p>");
		    }
		},
		error: function(data) {
			console.log(data);
			$('#A01_isOnline').empty();
		    $('#A01_isOnline').html(data.responseText);
		}
	});
};

function A02_lastValues(){
	$.ajax({
		url: "/A02/lastvalues",
		method: "GET",
		success: function(data) {
		    var time;
			var temperature;
			var humidity;
			var pressure;
            var obj = JSON.parse(data);
            temperature = obj.temp;
            humidity = obj.humidity;
            pressure = obj.pressure;
            time = obj.timestamp;

        $('#A02_lastValues').empty();
        var data = "<h3 class=\"text-center\">" + temperature + " °C </p>";
        data = data + "<h3 class=\"text-center\">" + humidity + " % </p>";
        data = data + "<h3 class=\"text-center\">" + pressure + " hPa </p>";
        data = data + "<h3 class=\"text-center\">" + time + "</p>";
        $('#A02_lastValues').html(data);
		},
		error: function(data) {
			$('#A02_isOnline').empty();
		    $('#A02_isOnline').html(data.responseText);
		}
	});
};

function A02_isOnline(){
	$.ajax({
		url: "/A02/isonline",
		method: "GET",
		success: function(data) {
		    $('#A01_isOnline').empty();
		    if(data == "false"){
		    $('#A01_isOnline').html("<p class=\"text-center text-danger\"> Device is offline! </p>");
		    }
		},
		error: function(data) {
			console.log(data);
			$('#A02_isOnline').empty();
		    $('#A02_isOnline').html(data.responseText);
		}
	});
};




});