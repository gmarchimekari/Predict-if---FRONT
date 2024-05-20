$(document).ready(function () {
  console.log("login.js chargé");
  $("#bouton-connexion").on("click", function () {
    // Fonction appelée lors du clic sur le bouton

    // Récupération de la valeur des champs du formulaire
    var champLogin = $("#champ-login").val();
    var champPassword = $("#champ-password").val();

    // Appel AJAX
    $.ajax({
      url: "./ActionServlet",
      method: "POST",
      data: {
        todo: "connecter",
        login: champLogin,
        password: champPassword,
      },
      dataType: "json",
    })
      .done(function (response) {
        // Fonction appelée en cas d'appel AJAX réussi
        console.log("response ==> " + response);
        if (response.operationOk) {
          // si la connexion est réussie
          $id = response.utilisateur.id;
          if (response.typeUtilisateur == "employe") {
            // Redirection vers la page d'accueil employé en incluant l'ID de l'employé dans l'URL
            window.location.href = "menu-employe.html?id=" + id;
          } else if (response.typeUtilisateur == "client") {
            // Redirection vers la page d'accueil client en incluant l'ID du client dans l'URL
            window.location.href = "menu-client.html?id=" + id;
          } else {
            // Dans le cas où le type d'utilisateur n'est pas reconnu
            alert("Erreur lors de la connexion, veuillez réessayer.");
          }
        } else {
          // si la connexion est échouée
          alert("Identifiant ou mot de passe incorrect, veuillez réessayer.");
        }
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
  });
});
