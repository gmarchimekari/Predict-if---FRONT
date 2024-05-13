$(document).ready(function () {
  $("#start-aventure, #profil-astral-input").on("click", function () {
    console.log("click");
    // Appel AJAX
    $.ajax({
      url: "./ActionServlet",
      method: "POST",
      data: {
        todo: "estConnecte",
      },
      dataType: "json",
    })
      .done(function (response) {
        if (response.estConnecte) {
          // Si l'utilisateur est connecté, on vérifie son type
          if (response.typeUtilisateur == "client") {
            window.location.href = "menuClient.html";
          } else if (response.typeUtilisateur == "employe") {
            window.location.href = "menuEmploye.html";
          }
          // else ne devrait pas arriver
        } else {
          // Si l'utilisateur n'est pas connecté, redirection vers la page connexion.html
          window.location.href = "connexion.html";
        }
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
  });
});
