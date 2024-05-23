$(document).ready(function () {
  // Quand on clique sur le bouton "Terminer la consultation"
  $("#btn-valider").on("click", function () {
    var commentaire = $("#comentaire-area").val();
    $.ajax({
      url: "../ActionServlet",
      method: "POST",
      data: {
        todo: "terminerConsultation",
        commentaire: commentaire,
      },
    })
      .done(function (response) {
        if (response.consultationTerminee) {
          window.location.href = "../html/menuEmploye.html";
        } else {
          alert("Erreur lors de la terminaison de la consultation");
        }
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
  });
});
