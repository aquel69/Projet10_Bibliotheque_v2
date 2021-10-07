<!DOCTYPE HTML>
<head>
    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="/css/styleEmail.css">
</head>
<body>

<table>
    <tr>
        <td>
            <a href="https://localhost:8443"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-cPHM9g72sUuXwYWDfIOTyqNKMfOQFfT3Dg&usqp=CAU" alt="Logo Bibliothèque"/></a>
        </td>
    </tr>
    <tr>
        <td>
            <p>Chèr(e) abonné(e),</p>
            <p><br>L'ouvrage : '${ouvrage.getLivre().getTitre()}' est disponible dans votre <strong> ${bibliotheque.getNom()}</strong>.</p>
            <p>Vous disposez de 48h pour venir le récupérer. Une fois le délai dépassé votre réservation sera supprimée.</p>
            <p><br>Merci et à bientôt.</p>
        </td>
    </tr>
    <tr>
        <#assign siret = "${ouvrage.getSiretBibliotheque()}">
        <td>
            <#switch siret>
                <#case "18004625200177"><p><br>Bibliothèque Bruce Wayne<br>25 rue du manoir<br>NJ 12345 Gotham</p>
                    <#break>
                <#case "18004625200568"><p><br>Bibliothèque Alfred<br>78 route du GCPD<br>NJ 12345 Gotham</p>
                    <#break>
                <#case "18004625200356"><p><br>Bibliothèque Robin<br>36 rue d'Arkham<br>NJ 12345 Gotham</p>
                    <#break>
            </#switch>
        </td>
    </tr>
</table>