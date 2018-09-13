$(document).ready(function(){
Version_Text();
A02_Status();
A02_isOnline();
A02_lastValues();
dataDataBaseInterval();
dataDay_Temp();
dataDay_Humidity();
dataDay_Pressure();


function dataDay_Pressure(){
	$.ajax({
		url: "/A02/dataDay",
		method: "GET",
		success: function(data) {
		    var time = [];
			var pressure = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    pressure[i] = obj.pressure;
            time[i] = obj.timestamp.substring(10,16);
		}
	    var trace1 = {
          x: time,
          y: pressure,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Pressure').empty();
        Plotly.newPlot('A02Graph_Pressure', data);
        document.getElementById("btnDataDay_Pressure").style.backgroundColor = '#90EE90';    //green
        document.getElementById("btnDataWeek_Pressure").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Pressure").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Pressure").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataWeek_Pressure(){
	$.ajax({
		url: "/A02/dataWeek",
		method: "GET",
		success: function(data) {
		    var time = [];
			var pressure = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    pressure[i] = obj.pressure;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: pressure,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Pressure').empty();
        Plotly.newPlot('A02Graph_Pressure', data);
        document.getElementById("btnDataDay_Pressure").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Pressure").style.backgroundColor = '#90EE90';   //green
        document.getElementById("btnDataMonth_Pressure").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Pressure").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataMonth_Pressure(){
	$.ajax({
		url: "/A02/dataMonth",
		method: "GET",
		success: function(data) {
		    var time = [];
			var pressure = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    pressure[i] = obj.pressure;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: pressure,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Pressure').empty();
        Plotly.newPlot('A02Graph_Pressure', data);
        document.getElementById("btnDataDay_Pressure").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Pressure").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Pressure").style.backgroundColor = '#90EE90'; //green
        document.getElementById("btnDataYear_Pressure").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataYear_Pressure(){
	$.ajax({
		url: "/A02/dataYear",
		method: "GET",
		success: function(data) {

		    var time = [];
			var pressure = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    pressure[i] = obj.pressure;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: pressure,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Pressure').empty();
        Plotly.newPlot('A02Graph_Pressure', data);
        document.getElementById("btnDataDay_Pressure").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Pressure").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Pressure").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Pressure").style.backgroundColor = '#90EE90';   //green
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataDay_Humidity(){
	$.ajax({
		url: "/A02/dataDay",
		method: "GET",
		success: function(data) {
		    var time = [];
			var humidity = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    humidity[i] = obj.humidity;
            time[i] = obj.timestamp.substring(10,16);
		}
	    var trace1 = {
          x: time,
          y: humidity,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Humidity').empty();
        Plotly.newPlot('A02Graph_Humidity', data);
        document.getElementById("btnDataDay_Humidity").style.backgroundColor = '#90EE90';    //green
        document.getElementById("btnDataWeek_Humidity").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Humidity").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Humidity").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataWeek_Humidity(){
	$.ajax({
		url: "/A02/dataWeek",
		method: "GET",
		success: function(data) {
		    var time = [];
			var humidity = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    humidity[i] = obj.humidity;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: humidity,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Humidity').empty();
        Plotly.newPlot('A02Graph_Humidity', data);
        document.getElementById("btnDataDay_Humidity").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Humidity").style.backgroundColor = '#90EE90';   //green
        document.getElementById("btnDataMonth_Humidity").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Humidity").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataMonth_Humidity(){
	$.ajax({
		url: "/A02/dataMonth",
		method: "GET",
		success: function(data) {
		    var time = [];
			var humidity = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    humidity[i] = obj.humidity;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: humidity,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Humidity').empty();
        Plotly.newPlot('A02Graph_Humidity', data);
        document.getElementById("btnDataDay_Humidity").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Humidity").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Humidity").style.backgroundColor = '#90EE90'; //green
        document.getElementById("btnDataYear_Humidity").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataYear_Humidity(){
	$.ajax({
		url: "/A02/dataYear",
		method: "GET",
		success: function(data) {

		    var time = [];
			var humidity = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    humidity[i] = obj.humidity;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: humidity,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Humidity').empty();
        Plotly.newPlot('A02Graph_Humidity', data);
        document.getElementById("btnDataDay_Humidity").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Humidity").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Humidity").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Humidity").style.backgroundColor = '#90EE90';   //green
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataDay_Temp(){
	$.ajax({
		url: "/A02/dataDay",
		method: "GET",
		success: function(data) {
		    var time = [];
			var temperature = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    temperature[i] = obj.temp;
            time[i] = obj.timestamp.substring(10,16);
		}
	     var trace1 = {
          x: time,
          y: temperature,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Temp').empty();
        Plotly.newPlot('A02Graph_Temp', data);
        document.getElementById("btnDataDay_Temp").style.backgroundColor = '#90EE90';    //green
        document.getElementById("btnDataWeek_Temp").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Temp").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Temp").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataWeek_Temp(){
	$.ajax({
		url: "/A02/dataWeek",
		method: "GET",
		success: function(data) {
		    var time = [];
			var temperature = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    temperature[i] = obj.temp;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: temperature,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Temp').empty();
        Plotly.newPlot('A02Graph_Temp', data);
        document.getElementById("btnDataDay_Temp").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Temp").style.backgroundColor = '#90EE90';   //green
        document.getElementById("btnDataMonth_Temp").style.backgroundColor = '#D3D3D3'; //gray
        document.getElementById("btnDataYear_Temp").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataMonth_Temp(){
	$.ajax({
		url: "/A02/dataMonth",
		method: "GET",
		success: function(data) {
		    var time = [];
			var temperature = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    temperature[i] = obj.temp;
            time[i] = obj.timestamp.substring(0,16);
		}
	    var trace1 = {
          x: time,
          y: temperature,
          type: 'scatter'
        };
        var data = [trace1];
        $('#A02Graph_Temp').empty();
        Plotly.newPlot('A02Graph_Temp', data);
        document.getElementById("btnDataDay_Temp").style.backgroundColor = '#D3D3D3';    //gray
        document.getElementById("btnDataWeek_Temp").style.backgroundColor = '#D3D3D3';   //gray
        document.getElementById("btnDataMonth_Temp").style.backgroundColor = '#90EE90'; //green
        document.getElementById("btnDataYear_Temp").style.backgroundColor = '#D3D3D3';   //gray
		},
		error: function(data) {
			console.log(data);
		}
	});
};

function dataYear_Temp(){
	$.ajax({
		url: "/A02/dataYear",
		method: "GET",
		success: function(data) {

		    var time = [];
			var temperature = [];

		for(var i = 0; i < data.length; i++){
		    var obj = JSON.parse(data[i]);
		    temperature[i] = obj.temp;
            time[i] = obj.timestamp.substring(0,16);
		}
        var trace1 = {
              x: time,
              y: temperature,
              type: 'scatter'
            };
            var data = [trace1];
            $('#A02Graph_Temp').empty();
            Plotly.newPlot('A02Graph_Temp', data);
            document.getElementById("btnDataDay_Temp").style.backgroundColor = '#D3D3D3';    //gray
            document.getElementById("btnDataWeek_Temp").style.backgroundColor = '#D3D3D3';   //gray
            document.getElementById("btnDataMonth_Temp").style.backgroundColor = '#D3D3D3'; //gray
            document.getElementById("btnDataYear_Temp").style.backgroundColor = '#90EE90';   //green
            },
            error: function(data) {
                console.log(data);
            }
        });
};

function dataDataBaseInterval(){
	$.ajax({
		url: "/A02/database_interval",
		method: "GET",
		success: function(data) {
		    var database_interval_value = [];


		for(var i = 0; i < data.length; i++){
		    database_interval_value[i] = data[i];
		}

		    $('#database_interval_text').empty();
		    $('#database_interval_text').html('<h4 class="text-center"> Database interval: ' + database_interval_value[0]
		    + ' minutes</h4>');
		},
		error: function(data) {
			console.log(data);
		}
	});
};

$("#database_interval_update").submit(function(event) {
		// Prevent the form from submitting via the browser.
		event.preventDefault();
		databaseIntervalPost();
});

function databaseIntervalPost(){
    	var formData = {
    		value : $("#database_interval_update_value").val(),
    	}
    	$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/A02/database_interval_update",
			data : JSON.stringify(formData),
			success : function(result) {
				$("#database_interval_update_text").html(result);
				dataDataBaseInterval();
			},
			error : function(e) {
			console.log(e);
	            $("#database_interval_update_text").html(e.responseText);
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
			console.log(data);
		    $('#A02_isOnline').html(data);
		}
	});
};

function A02_isOnline(){
	$.ajax({
		url: "/A02/isonline",
		method: "GET",
		success: function(data) {
		    $('#A02_isOnline').empty();
		    if(data == "false"){
		    $('#A02_isOnline').html("<p class=\"text-center text-danger\"> Device is offline! </p>");
		    }
		},
		error: function(data) {
			console.log(data);
			$('#A02_isOnline').empty();
		    $('#A02_isOnline').html(data.responseText);
		}
	});
};

function A02_Status(){
	$.ajax({
		url: "/A02/status",
		method: "GET",
		success: function(data) {
		    $('#A02_status_text').empty();
		    $('#A02_status_text').html(data);
		},
		error: function(data) {
			console.log(data);
			$('#A02_status_text').empty();
		    $('#A02_status_text').html(data.responseText);
		}
	});
};

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






























































});