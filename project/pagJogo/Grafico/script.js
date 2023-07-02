/*var preços = {
  "prices": [
    { "date": "2020-01-01", "price": 100 },
    { "date": "2020-02-01", "price": 120 },
    { "date": "2020-02-02", "price": 90 },
    { "date": "2021-02-03", "price": 40 },
    { "date": "2021-02-15", "price": 120 },
    { "date": "2021-03-20", "price": 150 },
    { "date": "2022-02-06", "price": 160 },
    { "date": "2022-03-01", "price": 150 },
    { "date": "2023-04-01", "price": 130 },
    { "date": "2023-05-01", "price": 140 }
  ]
};*/
fetch('../../Databank/data.json')
  .then(response => response.json())
  .then(data => {
    let url = window.location.href;
    let id = url.substring(url.lastIndexOf('#') + 1);
    let preços = data[id];
    console.log(data[id]);

    function diasNoMes(mes, ano) {
      let data = new Date(ano, mes, 0);
      return data.getDate();
    }
    let date_1 = new Date('2020-01-01');
    let date_2 = new Date('2024-12-31');

    const days = (date_1, date_2) => {
      let difference = date_1.getTime() - date_2.getTime();
      let TotalDays = Math.ceil(difference / (1000 * 3600 * 24));
      console.log("Total Days : " + TotalDays);
      return TotalDays;
    }

    const updateM = (dif, day, val, month, year) => {
      let x;
      let date_3 = `${year}-${month}-01`;
      if (val == 0) {
        x = Date.parse(day) - Date.parse(date_3);
      } else if (val < dif) {
        x = dif;
      } else {
        x = val;
      }
      return x;
    }

    let dats = [preços.prices[0]];
    console.log("Date 1 : " + (Date.parse(dats[0].date)));

    let max = -days(date_1, date_2);
    console.log("Max : " + max);
    let day = 1;
    let month = 1;
    let year = 2020;
    let lastPrice = preços.prices[0].price;
    let y = 1;
    let MVe = [0];
    for (let i = 0; i < max; i++) {

      if (y < preços.prices.length - 1 && dats[i].date >= preços.prices[y].date) {
        console.log("Update");
        lastPrice = preços.prices[y].price;
        console.log("Last Price : " + lastPrice);
        y++;
        i--;
        console.log("WHAT : " + y);
        MVe[month] = updateM(Date.parse(preços.prices[y].date) - (Date.parse(preços.prices[y - 1].date) ? Date.parse(preços.prices[y - 1].date) : 0), (dats[i].date), MVe[month], month, year)
      } else {
        console.log("Antigo Igual");
        day++;
        if (day > diasNoMes(month, year)) {
          MVe[month] = updateM(Date.parse(dats[i - 1].date) - Date.parse(preços.prices[y - 1].date), dats[i - 1].date, MVe[month], month, year)
          month++;
          day = 1;
        }
        if (month > 12) {
          year++;
          month = 1;
        }
        dats[i + 1] = { "date": `${year}-${(month > 9 ? month : "0" + month)}-${(day > 9 ? day : "0" + day)}`, "price": lastPrice };
      }
    }
    console.log(dats);
    console.log("MVE : " + MVe);



    // Obtenha o elemento canvas
    var canvas = document.getElementById("price-chart");
    var ctx = canvas.getContext("2d");

    // Função para atualizar o gráfico com base na granularidade selecionada
    function updateChart() {
      var select = document.getElementById("granularity-select");
      var granularity = select.value;

      var filteredDates = dats.map(item => {
        var date = new Date(item.date);
        if (granularity === "month") {
          return date.toLocaleString('default', { month: 'long' });
        } else if (granularity === "day") {
          return date.getDate();
        } else if (granularity === "year") {
          return date.getFullYear();
        }
      });

      // Atualize o gráfico com os novos dados
      chart.data.labels = filteredDates;
      chart.update();
    }
    // Extraia datas e preços do objeto JavaScript
    var dates = dats.map(item => item.date);
    var values = dats.map(item => item.price);

    // Crie o gráfico usando Chart.js
    var chart = new Chart(ctx, {
      type: "line",
      data: {
        labels: dates,
        datasets: [{
          pointRadius: 0,
          pointBorderWidth: 1,
          label: "Preço",
          data: values,
          borderColor: "blue",
          fill: false,
          borderWidth: 3
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            display: true,
            title: {
              display: true,
              text: "Data"
            }
          },
          y: {
            display: true,
            title: {
              display: true,
              text: "Preço"
            }
          }
        }
      }
    });
  });