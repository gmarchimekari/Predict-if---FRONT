$(document).ready(function () {
  $.ajax({
    url: "../ActionServlet",
    method: "GET",
    data: {
      todo: "getStatistiques",
    },
    dataType: "json",
  })
    .done(function (response) {
        console.log(response)
      if (response.nbConsultatations) {
        // affichage du nombre de consultations par médium
        let ChartData = {
          denomination: [],
          nbConsultations: [],
        };

        response.nbConsultatations.forEach((consultation) => {
          ChartData.denomination.push(consultation.denomination);
          ChartData.nbConsultations.push(consultation.nbConsultations);
        });

        const container = $("#nbs-consultations-mediums");
        buildConsultationChart(container, ChartData);
      }

      if (response.repartitionClients) {
        // affichage de la répartition des clients par employé
        let ChartData = {
          nomClient: [],
          repartitionClient: [],
        };

        response.repartitionClients.forEach((repartition) => {
          ChartData.nomClient.push(repartition.nomClient);
          ChartData.repartitionClient.push(repartition.repartitionClient);
        });

        const container = $("#repartition-client-par-employe");
        buildRepartitionChart(container, ChartData);
      }

      if (response.top5) {
        // affichage des 5 médiums les plus consultés
        let top5 = response.top5;
        let top5Container = $("#top5-mediums");
        top5.forEach((medium) => {
          let mediumElement = $("<li></li>");
          mediumElement.text(`${medium.denomination}`);
          top5Container.append(mediumElement);
        });
      }

      if (response.clients) {
        // affichage de la répartition géographique des clients
        initMap(response.clients);
      }
    })
    .fail(function (error) {
      alert("Erreur lors de l'appel AJAX");
    });
});

function buildConsultationChart(container, graphData) {
  Highcharts.chart(container, {
    chart: {
      type: "column",
    },
    title: {
      text: "Nombre de consultations par médium",
    },
    xAxis: {
      title: {
        text: "Médiums",
      },
      categories: graphData.denomination,
    },
    yAxis: {
      title: {
        text: "Nb de consultations",
      },
    },
    legend: {
      enabled: false,
    },
    credits: {
      enabled: false,
    },
    series: [
      { name: "Nombre de consultations : ", data: graphData.nbConsultatations },
    ],
  });
}

function buildRepartitionChart(container, graphData) {
  Highcharts.chart(container, {
    chart: {
      type: "column",
    },
    title: {
      text: "Nombre d'appels par client",
    },
    xAxis: {
      title: {
        text: "Clients",
      },
      categories: graphData.nomClient,
    },
    yAxis: {
      title: {
        text: "Nb d'appels",
      },
    },
    legend: {
      enabled: false,
    },
    credits: {
      enabled: false,
    },
    series: [
      { name: "Répartition du client : ", data: graphData.repartitionClient },
    ],
  });
}

function initMap(clients) {
  googleMapInstance = new google.maps.Map(
    document.getElementById("repartition-geo-clients"),
    {
      center: { lat: 45.7601424, lng: 4.8961779 },
      zoom: 10,
    }
  );

  generateMarkers(clients);
}

function generateMarkers(clients) {
  clients.forEach((client) => {
    let marker = new google.maps.Marker({
      position: { lat: client.latitude, lng: client.longitude },
      map: googleMapInstance,
      title: `${client.nom} ${client.prenom}`,
    });

    marker.addListener("click", function () {
      let infowindow = new google.maps.InfoWindow();
      let htmlDescription = `<div><strong>Nom :</strong> ${client.nom} ${client.prenom}</div>`;
      attachInfoWindow(marker, infowindow, htmlDescription);
    });
  });
}

function attachInfoWindow(marker, infowindow, htmlDescription) {
  marker.addListener("click", function () {
    infowindow.setContent(htmlDescription);
    infowindow.open(googleMapInstance, this);
  });
}
