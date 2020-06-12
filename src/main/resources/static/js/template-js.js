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

    }
    
);
















});