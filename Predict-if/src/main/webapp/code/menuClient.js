$(document).ready(function () {
  $.ajax({
    url: "./ActionServlet",
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
          ? $("#couleur-client").val(response.client.profilAstral)
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

      //Remplissage de la listes des voyants de la base de données
      if (response.voyants) {
        const listeVoyants = $("#liste-voyants");

        response.voyants.forEach((voyant) => {
          const li = $("<li>");
          // La popularité du voyant est un pourcentage aléatoire entre 95 et 100 %
          const popularite = Math.floor(Math.random() * 6) + 95;
          const contenuLi = `Nom : ${voyant.denomination} - Type: ${voyant.type}, Popularité: ${popularite}%`;
          li.text(contenuLi);

          // Lorsque l'utilisateur clique sur un voyant, on récupère les informations du voyant
          li.on("click", function () {
            getInformationsVoyant(voyant);
          });

          listeVoyants.append(li);
        });
      } else {
        // Si aucun voyant n'est trouvé, on affiche un message d'erreur
        const listeVoyants = $("#liste-voyants");
        const li = $("<li>");
        li.text("Aucun voyant n'est retrouvé dans la base de données.");
        listeVoyants.append(li);
      }
    })
    .fail(function (error) {
      // Fonction appelée en cas d'erreur lors de l'appel AJAX
      alert("Erreur lors de l'appel AJAX");
    });
});

function getInformationVoyant(voyant) {}
