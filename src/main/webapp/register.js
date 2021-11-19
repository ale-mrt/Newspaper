$(document).ready(function(){
    $("#registerButton").click(function(){
        $.get("http://localhost:8080/auth/register")
    })
});