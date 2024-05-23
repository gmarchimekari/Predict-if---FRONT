$(document).ready(function () {
  console.log("consultation.js charge");
  
  
  $("#btn-terminer-consultation").on("click", function () {
      var commentaire = $("#champ-commentaire").val();
      $.ajax({
        url: "../ActionServlet",
        method: "GET",
        data: {
          todo: "terminerConsultation",
          commentaire: commentaire,
        }
      })
        .done(function (response) {
            window.location.href = "../html/menuEmploye.html";
        })
        .fail(function (error) {
          // Fonction appelée en cas d'erreur lors de l'appel AJAX
          alert("Erreur lors de l'appel AJAX");
        });
  });
});


