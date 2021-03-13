<html>  

<head>
    <link rel="stylesheet" type="text/css" href="styleCSS/body.css" />
    <link rel="stylesheet" type="text/css" href="styleCSS/index.css" />
</head>

<body>
    <form action="./ServiceConnexion" method="post">
        <div id=login>
            <label for="name">Login :</label>
            <input type="text" id="loginInput" name="login" required minlength="1">
        </div>
        <div id="pass">
            <label for="pass">Mot de passe :</label>
            <input type="password" id="passInput" name="mdp" required minlength="1">
        </div>
        <div class="button">
            <button type="submit">Connexion</button>
        </div>
        <div id=text>
        <%
            String error = (String) request.getAttribute("error");
            if (error!=null){
                out.println(error);
            }
        %>
        </div>
    </form>
    
</body>

<footer>
</footer>

</html>