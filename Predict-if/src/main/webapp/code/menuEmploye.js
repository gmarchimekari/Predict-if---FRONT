$(document).ready(function () {
  const nonRenseigne = "Non renseigné";

  $.ajax({
    url: "../ActionServlet",
    method: "GET",
    data: {
      todo: "getInformationsConsultation",
    },
    dataType: "json",
  })
    .done(function (response) {
        console.log(response);
      response.employe.nom !== ""
        ? $("#nom-employe").val(response.employe.nom)
        : $("#nom-employe").val(nonRenseigne);

      response.employe.prenom !== ""
        ? $("#prenom-employe").val(response.employe.prenom)
        : $("#prenom-employe").val(nonRenseigne);

      if (!response.consultationEnCours) {
        // si l'employé n'a pas de consultation en cours
        $("#titre-pc").val("Aucune consultation en cours");
        $("#infos-clients-medium").hide(); // On cache la partie informations client et medium
        $("#btn-pret").hide(); // On cache le bouton prêt

        // Desactivé le click sur le bouton prêt
        $("#btn-pret").prop("disabled", true);
      } else {
        // si l'employé a une consultation en cours
        if (!response.client) {
          // si le client n'est pas renseigné
          $("#infos-clients").hide(); // On cache la partie informations client
        } else {
          // si le client est renseigné
          response.client.nom !== ""
            ? $("#nom-client").val(response.client.nom)
            : $("#nom-client").val(nonRenseigne);

          response.client.prenom !== ""
            ? $("#prenom-client").val(response.client.prenom)
            : $("#prenom-client").val(nonRenseigne);

          response.client.couleur !== ""
            ? $("#couleur").val(response.client.couleur)
            : $("#couleur").val(nonRenseigne);

          response.client.signeZodiac !== ""
            ? $("#signe-zodiac").text(response.client.signeZodiac)
            : $("#signe-zodiac").text(nonRenseigne);

          response.client.signeChinois !== ""
            ? $("#signe-chinois").text(response.client.signeChinois)
            : $("#signe-chinois").text(nonRenseigne);

          response.client.animalTotem !== ""
            ? $("#animal-totem").text(response.client.animalTotem)
            : $("#animal-totem").text(nonRenseigne);
        }

        if (!response.medium) {
          // si le medium n'est pas renseigné
          $("#infos-medium").hide(); // On cache la partie informations medium
        } else {
          // si le medium est renseigné
          response.medium.denomination !== ""
            ? $("#denomination-medium").text(response.medium.denomination)
            : $("#denomination-medium").text(nonRenseigne);

          response.medium.genre !== ""
            ? $("#genre-medium").text(response.medium.genre)
            : $("#genre-medium").text(nonRenseigne);

          const supportMedium = $("#support-medium");

          // On renseigne le support du medium en fonction de son type
          if (response.medium.type == "Spirite") {
            supportMedium.text(`Support : ${response.medium.support}`);
          } else if (response.medium.type == "Astrologue") {
            supportMedium.text(
              `Formation : ${response.medium.formation} <br> Promotion : ${response.medium.promotion}`
            );
          } else if (response.medium.type == "Tarologue") {
            supportMedium.text(`Support : Cartes`);
          } else {
            supportMedium.text(`Support : Non renseigné`);
          }
        }
      }

      if (!response.listeConsultations) {
        // si le clientÒ n'a pas de consultation en cours
        $("#titre-hc").val("Le client n'a jamais consulté de médiums");
      } else {
        response.listeConsultations.forEach((consultation) => {
          const li = $("<li>");
          const lesConsultations = $("#liste-consultations");
          li.text(
            `Date : ${consultation.date} - Medium : ${consultation.denomination} - Commentaire : ${consultation.commentaire}`
          );

          lesConsultations.append(li);
        });
      }

      $("#btn-pret").on("click", function () {
        commencerConsultation();
      });
    })
    .fail(function (error) {
      // Fonction appelée en cas d'erreur lors de l'appel AJAX
      alert("Erreur lors de l'appel AJAX");
    });
});

// permet à l'employé de commencer la consultation lié au client
function commencerConsultation() {
  $.ajax({
    url: "./ActionServlet",
    method: "GET",
    data: {
      todo: "commencerConsultation",
    },
    dataType: "json",
  })
    .done(function (response) {
      if (response.consultationDebutee) {
        window.location.href = "consultation.html";
      } else {
        alert("Erreur lors de la consultation, veuillez réessayer.");
      }
    })
    .fail(function (error) {
      alert("Erreur lors de l'appel AJAX");
    });
}
