$(document).ready(function () {
  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "getStatistiques",
    },
    dataType: "json",
  })
    .done(function (response) {})
    .fail(function (error) {
      alert("Erreur lors de l'appel AJAX");
    });
});
