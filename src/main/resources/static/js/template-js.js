$(document).ready(function () {

    // Side Navbar toggle button
    $('#toggle-btn').on('click', function (e) {
        // blocks default event handling
        e.preventDefault();

        if ($(window).outerWidth() > 1194) {
            // becomes a little smaller on larger screens
            $('nav.side-navbar').toggleClass('shrink');
            $('.content').toggleClass('active');
        } else {
            $('nav.side-navbar').toggleClass('show-sm');
            $('.content').toggleClass('active-sm');
        }

        // Enable tooltips
        $('[data-toggle="tooltip"]').tooltip();

    });
    
    $('#select_entries').on('change', function (e){
        e.preventDefault();
        var key = "";
        if(getQueryVariable("key")!=""){
        	key = getQueryVariable("key");
            window.location = $(this).find('option:selected').val()+"&key="+key;
        }
        else{
            window.location = $(this).find('option:selected').val();
        }
    });
    
    $('#search').on('click', function (e){
        e.preventDefault();
        var url = document.location.toString();
    　　　var arrUrl = url.split("?");
    	window.location = arrUrl[0]+"?page=1&size=5&key="+document.getElementById("search-field").value;
    });

    
    function getQueryVariable(variable)
    {
           var query = window.location.search.substring(1);
           var vars = query.split("&");
           for (var i=0;i<vars.length;i++) {
                   var pair = vars[i].split("=");
                   if(pair[0] == variable){return pair[1];}
           }
           return("");
    }
    
    function entryNumberInitial(){
    	var s = getQueryVariable("size");
    	if(document.getElementById("select_entries")!=null){
        	if(s==5 || s==false){
        		document.getElementById("select_entries").options[0].selected=true;
        	}
        	else if(s==10){
        		document.getElementById("select_entries").options[1].selected=true;
        	}
        	else if(s==20){
        		document.getElementById("select_entries").options[2].selected=true;
        	}
    	}
    }
    
    function searchKeyInitial(){
    	var key = getQueryVariable("key");
    	var s = document.getElementById("search-field");
    	if(s!="" && s!=null){
        	s.value = key;
    	}
    }
    
    function getYear(){
    	$('#year').text(new Date().getFullYear());
    }
    
    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){
             $("#search").click();
        }
    };

    function initial(){
    	entryNumberInitial();
    	searchKeyInitial();
    	getYear();
    }
    
    window.onload = initial();
    
});