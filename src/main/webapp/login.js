$(document).ready(function(){
    $.get("http://localhost:8080/auth/getUser", function(userJSON){
        user = JSON.parse(userJSON);

        if(user != null){
            $("#loginForm").hide();
            $("#logoutForm").show();
        }else{
            $("#loginForm").show();
            $("#logoutForm").hide();
        }

        $("#loginButton").click(function(){
            console.log("clicked on login button");
            username = $("#usernameInput").val();
            password = $("#passwordInput").val();

            $.post("http://localhost:8080/auth/userLogin", {username: username, password: password}, function(userJSON){
                user = JSON.parse(userJSON);
    
                if(user != null){
                    $(window.location).attr("href", "/profile.html");
                }else{
                    alert("Wrong username or password");
                }
            });
        });

        $("#logoutButton").click(function(){
            $.get("http://localhost:8080/auth/logout", function(){
            });
        });
    });
});