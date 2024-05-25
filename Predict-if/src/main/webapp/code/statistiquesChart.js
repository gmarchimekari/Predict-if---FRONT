$(document).ready(function () {
  $.ajax({
    url: "../ActionServlet",
    method: "GET",
    data: { todo: "getStatistiques" },
    dataType: "json"
  })
  .done(function (response) {
      console.log(response.top5)
    if (response.nbConsultations) {
      let chartData = {
        denomination: [],
        nbConsultations: []
      };

      response.nbConsultations.forEach((consultation) => {
        chartData.denomination.push(consultation.denomination);
        chartData.nbConsultations.push(consultation.nbConsultations);
      });

      const container = $("#nbs-consultations-mediums");
      buildConsultationChart(container, chartData);
    }

    if (response.repartitionClients) {
      let chartData = {
        nomClient: [],
        repartitionClient: []
      };

      response.repartitionClients.forEach((repartition) => {
        chartData.nomClient.push(repartition.nomClient);
        chartData.repartitionClient.push(repartition.repartitionClient);
      });

      const container = $("#repartition-client-par-employe");
      buildRepartitionChart(container, chartData);
    }

    if (response.top5) {
      let top5Container = $("#top5-mediums");
      response.top5.forEach((medium) => {
        let mediumElement = $("<li></li>");
        mediumElement.text(medium.denomination);
        top5Container.append(mediumElement);
      });
    }
  })
  .fail(function (error) {
    alert("Erreur lors de l'appel AJAX");
  });
});

function buildConsultationChart(container, graphData) {
  Highcharts.chart(container[0], {
    chart: { type: "column" },
    title: { text: "Nombre de consultations par médium" },
    xAxis: {
      title: { text: "Médiums" },
      categories: graphData.denomination
    },
    yAxis: {
      title: { text: "Nb de consultations" }
    },
    legend: { enabled: false },
    credits: { enabled: false },
    series: [{ name: "Nombre de consultations", data: graphData.nbConsultations }]
  });
}

function buildRepartitionChart(container, graphData) {
  Highcharts.chart(container[0], {
    chart: { type: "column" },
    title: { text: "Nombre d'appels par client" },
    xAxis: {
      title: { text: "Clients" },
      categories: graphData.nomClient
    },
    yAxis: {
      title: { text: "Nb d'appels" }
    },
    legend: { enabled: false },
    credits: { enabled: false },
    series: [{ name: "Répartition du client", data: graphData.repartitionClient }]
  });
}
