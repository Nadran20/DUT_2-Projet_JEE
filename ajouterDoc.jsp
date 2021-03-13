<html>  

<head>
    <link rel="stylesheet" type="text/css" href="styleCSS/body.css"/>
    <link rel="stylesheet" type="text/css" href="styleCSS/ajouter.css"/>
</head>

<body>
    <form action="./ServiceAjout" method="post">
        <div id="choix">
            <div id="type">
                <label for="name">Type du document :</label>
                <select name="type" id="type-select">
                    <option value="0">Livre</option>
                    <option value="1">CD</option>
                    <option value="2">DVD</option>
                </select>
            </div>
            <div id="titreD">
                <label for="titre">Titre du document :</label>
                <input type="text" id="titre" name="titre" required minlength="1">
            </div>
            <div id="auteurD">
                <label for="name">Auteur du document :</label>
                <input type="text" id="auteur" name="auteur" required minlength="1">
            </div>
        </div>
        <div class="buttonD">
            <button type="submit">Ajouter le document</button>
        </div>
    </form>
</body>

<footer>
</footer>

</html>