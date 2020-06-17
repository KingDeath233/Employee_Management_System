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
        if(getQueryVariable("key")!=false){
        	key = getQueryVariable("key");
        }
        window.location = $(this).find('option:selected').val()+"&key="+key;
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
           return(false);
    }
    
    function my(){
    	var s = getQueryVariable("size");
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
    
    window.onload=my;
    
});