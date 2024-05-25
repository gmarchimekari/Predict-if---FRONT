$(document).ready(function () {
  $.ajax({
    url: "../ActionServlet",
    method: "POST",
    data: {
      todo: "consultationEnCours",
    },
    dataType: "json",
  })
    .done(function (response) {
      $("#denomination-medium").val(response.medium.denomination);
      $("#genre-medium").val(response.medium.genre);
      $("#support-medium").val(response.medium.support);
      $("#signe-zodiac").val(response.client.profilAstral.signeZodiaque);
      $("#signe-chinois").val(response.client.profilAstral.signeChinois);
      $("#animal-totem").val(response.client.profilAstral.animalTotem);
      $("#couleur").val(response.client.profilAstral.couleur);
      $("#nom-prenom-client").val(response.client.nomPrenom);
    })
    .fail(function (error) {
      // Fonction appelée en cas d'erreur lors de l'appel AJAX
      alert("Erreur lors de l'appel AJAX");
    });

  // Quand on clique sur le bouton "Terminer la consultation"
  $("#btn-achever-consult").on("click", function () {
    $.ajax({
      url: "../ActionServlet",
      method: "GET",
      data: {
        todo: "terminerConsultation",
      },
    })
      .done(function (response) {
        window.location.href = "./commentaire.html";
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
  });

  // Quand on clique sur le bouton "Generer prediction"
  $("#generer-predictions").on("click", function () {
    var amour = $("#amour-input").val();
    var sante = $("#sante-input").val();
    var travail = $("#travail-input").val();
    $.ajax({
      url: "../ActionServlet",
      method: "GET",
      data: {
        todo: "genererPrediction",
        amour: amour,
        sante: sante,
        travail: travail,
      },
      dataType: "json",
    })
      .done(function (response) {
        console.log(response);
        $("#prediction-amour").text(response.amour);
        $("#prediction-sante").text(response.sante);
        $("#prediction-travail").text(response.travail);
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
  });
});
