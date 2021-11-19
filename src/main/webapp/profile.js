$(document).ready(function(){
    $.get("http://localhost:8080/auth/getUser", function(userJSON){
        user = JSON.parse(userJSON);

        if(user != null){
            $("#name").html(user.name);
            $("#username").html(user.username);
            $("#password").html(user.password);
            $("#email").html(user.email);
            $("#imgURL").html(user.imgURL);
            $("#tagline").html(user.tagline);
            $("#isAuthor").html("author: " + user.author);

            if(user.author == true){
                $.post("http://localhost:8080/profile/getArticles", function(articlesJSON){
                    articles = JSON.parse(articlesJSON);
                    if(articles.length > 0){
                        htmlString = "<div><h1>Articles</h1><table><tr><th>ID</th><th>Title</th><th>Date</th><th>Likes</th><th>Tags</th></tr>";
                        for(var i = 0; i < articles.length; i++){
                            htmlString += "<tr><td>"+articles[i].id+"</td><td>"+articles[i].title+"</td><td>"+articles[i].date+"</td><td>"+articles[i].likes+"</td><td>"+articles[i].tags+"</td></tr>";
                        }
                        htmlString += "</table></div>";
                    }else{
                        htmlString = "<div>No articles</div>";
                    }
    
                    $.post("http://localhost:8080/profile/getComments", function(commentsJSON){
                        comments = JSON.parse(commentsJSON);
    
                        if(comments.length > 0){
                            htmlString += "</table><h1>Comments</h1></div><div><table><tr><th>ID</th><th>Likes</th><th>Date</th><th>Article ID</th></tr>";
                            for(var i = 0; i < comments.length; i++){
                                htmlString += "<tr><td>"+comments[i].id+"</td><td>"+comments[i].likes+"</td><td>"+comments[i].date+"</td><td>"+comments[i].idArticle+"</td></tr>";
                            }
                            htmlString += "</div>";
                        }else{
                            htmlString += "<div>No comments</div>";
                        }
    
                        $("#userContributions").html(htmlString);
                    });
                });
            }else{
                $.post("http://localhost:8080/profile/getComments", function(commentsJSON){
                    comments = JSON.parse(commentsJSON);
    
                    if(comments.length > 0){
                        htmlString = "</table></div><div><h1>Comments</h1><table><tr><th>ID</th><th>Likes</th><th>Date</th><th>Article ID</th></tr>";
                        for(var i = 0; i < comments.length; i++){
                            htmlString += "<tr><td>"+comments[i].id+"</td><td>"+comments[i].likes+"</td><td>"+comments[i].date+"</td><td>"+comments[i].idArticle+"</td></tr>";
                        }
                        htmlString += "</div>";
                    }else{
                        htmlString = "<div>No comments</div>";
                    }
    
                    $("#userContributions").html(htmlString);
                });
            }
        }else{
            $("#name").html("Not logged");
            $("#username").html("Not logged");
            $("#password").html("Not logged");
            $("#email").html("Not logged");
            $("#imgURL").html("Not logged");
            $("#tagline").html("Not logged");
            $("#isAuthor").html("Not logged");
        }
    });
});