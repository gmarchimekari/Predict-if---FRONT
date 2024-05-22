$(document).ready(function () {
  // desactivé le click sur le bouton prendre rendez-vous
  $("#btn-prendre-rendez-vous").hide();
  $("#container-presentation-medium").hide();

  $(".btn-se-deconnecter").on("click", function () {
    $.ajax({
      url: "../ActionServlet",
      method: "GET",
      data: {
        todo: "seDeconnecter",
      },
    })
      .done(function (response) {
        window.location.href = "../html/connexion.html";
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
  });

  $.ajax({
    url: "../ActionServlet",
    method: "GET",
    data: {
      todo: "menuClient",
    },
    dataType: "json",
  })
    .done(function (response) {
      // Remplissage des données du client dans le menu
      if (response.client) {
        // Remplissant les champs du formulaire avec les informations du client
        const nonRenseigne = "Non renseigné";
        response.client.nom !== ""
          ? $("#nom-client").val(response.client.nom)
          : $("#nom-client").val(nonRenseigne);

        response.client.prenom !== ""
          ? $("#prenom-client").val(response.client.prenom)
          : $("#prenom-client").val(nonRenseigne);

        response.client.profilAstral.couleur !== ""
          ? $("#couleur-client").val(response.client.profilAstral.couleur)
          : $("#couleur-client").val(nonRenseigne);

        response.client.profilAstral.animalTotem !== ""
          ? $("#animal-totem-client").val(
              response.client.profilAstral.animalTotem
            )
          : $("#animal-totem-client").val(nonRenseigne);

        response.client.profilAstral.signeAstrologique !== ""
          ? $("#signe-astrologique-client").val(
              response.client.profilAstral.signeAstrologique
            )
          : $("#signe-astrologique-client").val(nonRenseigne);

        response.client.profilAstral.signeChinois !== ""
          ? $("#signe-chinois-client").val(
              response.client.profilAstral.signeChinois
            )
          : $("#signe-chinois-client").val(nonRenseigne);

        response.client.dateNaissance !== ""
          ? $("#date-naissance-client").val(response.client.dateNaissance)
          : $("#date-naissance-client").val(nonRenseigne);

        response.client.mail !== ""
          ? $("#mail-client").val(response.client.mail)
          : $("#mail-client").val(nonRenseigne);
      } else {
        // Si l'utilisateur n'est pas connecté, redirection vers la page connexion.html
        window.location.href = "connexion.html";
      }

      //Remplissage de la listes des mediums de la base de données
      if (response.mediums) {
        const listeMediums = $("#liste-mediums");

        response.mediums.forEach((medium) => {
          const li = $("<li>");
          // La popularité du medium est un pourcentage aléatoire entre 95 et 100 %
          const popularite = Math.floor(Math.random() * 6) + 95;
          const contenuLi = `Nom : ${medium.denomination} - Type : ${medium.type}, Popularité : ${popularite}%`;
          li.text(contenuLi);

          // Lorsque l'utilisateur clique sur un medium, on récupère les informations du medium
          li.on("click", function () {
            console.log("click");
            $("#container-presentation-medium").show();
            $("#btn-prendre-rendez-vous").show();
            getInformationsMedium(medium);
          });

          listeMediums.append(li);
        });
      } else {
        // Si aucun medium n'est trouvé, on affiche un message d'erreur
        const listeMediums = $("#liste-mediums");
        const li = $("<li>");
        li.text("Aucun medium n'a été trouvé dans la base de données.");
        listeMediums.append(li);
      }
    })
    .fail(function (error) {
      // Fonction appelée en cas d'erreur lors de l'appel AJAX
      alert("Erreur lors de l'appel AJAX");
    });
});

function getInformationsMedium(medium) {
  console.log(medium);
  $("#btn-prendre-rendez-vous").prop("disabled", false);
  const nomPrenomMedium = $("#nom-prenom-medium");
  const genreMedium = $("#genre-medium");

  const presentationMedium = $("#presentation-medium");

  nomPrenomMedium.text(`${medium.denomination}`);
  genreMedium.text(`${medium.genre}`);

  if (medium.type == "Spirite") {
    const supportSpirite = $("#support-spirite");

    supportSpirite.text(`Support : ${medium.support}`);
  } else if (medium.type == "Astrologue") {
    const formationAstrologue = $("#formation-astrologue");
    const promotionAstrologue = $("#promotion-astrologue");

    formationAstrologue.text(`${medium.formation}`);
    promotionAstrologue.text(`${medium.promotion}`);
  } else if (medium.type == "Cartomancien") {
    const supportCartomancien = $("#support-cartomancien");

    supportCartomancien.text("Support : Cartes"); // pas d'informations, on ecrit "Cartes" en dur
  }

  presentationMedium.text(`${medium.presentation}`);

  // quand on clique sur le boutton
  const bouttonPrendreRendezVous = $("#btn-prendre-rendez-vous");
  bouttonPrendreRendezVous.on("click", function () {
    prendreRdv(medium); // On prend un rendez-vous avec le medium sélectionné
  });
}

function prendreRdv(medium) {
  $("#btn-prendre-rendez-vous").prop("disabled", true);
  //var lesMediums = $("#liste-mediums").children()
  //console.log(lesMediums[0])
  //for (var i = 0; i < lesMediums.length; i++) {
  //    lesMediums[i].off("click");
  //}

  $.ajax({
    url: "../ActionServlet",
    method: "GET",
    data: {
      todo: "prendreRDV",
      denominationMedium: medium.denomination, // On envoie uniquement la denomination du medium
    },
    dataType: "json",
  })
    .done(function (response) {
      console.log(response.rdvOk);
      if (response.rdvOk) {
        alert("attente prise de contact avec le medium");
      } else {
        alert(
          "Aucun medium n'est disponible pour le moment, veuillez réessayer plus tard"
        );
      }
    })
    .fail(function (error) {
      // Fonction appelée en cas d'erreur lors de l'appel AJAX
      alert("Erreur lors de l'appel AJAX");
    });
}
