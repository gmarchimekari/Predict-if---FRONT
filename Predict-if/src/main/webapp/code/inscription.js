function verifierFormulaire() {
  // Récupération des valeurs des champs
  var dateNaissance = $("#champ-date-naissance").val();
  var numeroTel = $("#champ-numero-tel").val();
  var mail = $("#champ-mail").val();
  var motDePasse = $("#champ-mot-de-passe").val();
  var confirmationMotDePasse = $("#champ-confirmation-mot-de-passe").val();

  // Vérification de la date de naissance
  var selectedDate = new Date(dateNaissance);
  var today = new Date();
  if (selectedDate > today) {
    alert("La date de naissance ne peut pas être ultérieure à aujourd'hui.");
    return false;
  }

  // Vérification de l'adresse mail
  var mailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
  if (!mailRegex.test(mail)) {
    alert("L'adresse mail n'est pas valide.");
    return false;
  }

  // vérification du numéro de téléphone
  var phoneRegex = /^[0-9]{10}$/;
  if (!phoneRegex.test(numeroTel)) {
    alert("Le numéro de téléphone n'est pas valide.");
    return false;
  }


  // Vérification que les mots de passe sont identiques
  if (motDePasse !== confirmationMotDePasse) {
    alert("Les mots de passe ne correspondent pas.");
    return false;
  }

  // Si toutes les vérifications passent, retourner true
  return true;
}

$(document).ready(function () {
    console.log("wesh la zoneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
    console.log(response)
  $("#bouton-inscription").on("click", function () {
    // Fonction appelée lors du clic sur le bouton

    // Vérification du formulaire
    if (!verifierFormulaire()) {
      return;
    }

    // Récupération des valeurs des champs
    var nom = $("#champ-nom").val();
    var prenom = $("#champ-prenom").val();
    var dateNaissance = $("#champ-date-naissance").val();
    var rue = $("#champ-rue").val();
    var ville = $("#champ-ville").val();
    var numeroTel = $("#champ-numero-tel").val();
    var mail = $("#champ-mail").val();
    var motDePasse = $("#champ-mot-de-passe").val();
    console.log(dateNaissance);

    // Appel AJAX
    $.ajax({
      url: "../ActionServlet",
      method: "POST",
      data: {
        todo: "inscrire",
        nom: nom,
        prenom: prenom,
        dateNaissance: dateNaissance,
        rue: rue,
        ville: ville,
        numeroTel: numeroTel,
        mail: mail,
        motDePasse: motDePasse,
      },
      dataType: "json",
    })
      .done(function (response) {
        // Fonction appelée en cas d'appel AJAX réussi
        if (response.operationOk) {
          if (response.typeUtilisateur === "client") {
            $id = response.utilisateur.id;
            // Redirection vers la page menuClient.html en incluant l'ID du client dans l'URL
            window.location.href = "connexion.html";
          }
        } else {
            if(response.coords === "non valide") {
                alert("Veuillez saisir une addresse valide");
            } else {
                // si l'inscription a échouée
                alert("Erreur lors de l'inscription, veuillez réessayer.");
                // remettre les valeurs par défaut des champs
                $("#formulaire-inscription :input").each(function () {
                  $(this).val("");
                });
            }
        }
      })
      .fail(function (error) {
        // Fonction appelée en cas d'erreur lors de l'appel AJAX
        alert("Erreur lors de l'appel AJAX");
      });
      return false; // evite le rechargement de la page (comportement par defaut du form)
  });
});
