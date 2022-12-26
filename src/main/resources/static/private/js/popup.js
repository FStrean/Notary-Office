$(document).ready(function(){
    //active start button
    $('.add-button').click(function(){
        $('.modal-box').toggleClass("show-modal");
        $('.add-button').toggleClass("show-modal");
    });
    // active cancel button
    $('.fa-times').click(function(){
        $('.modal-box').toggleClass("show-modal");
        $('.add-button').toggleClass("show-modal");
    });
});