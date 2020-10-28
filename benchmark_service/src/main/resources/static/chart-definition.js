var chLine = document.getElementById("callStatistics");
if (chLine) {
  new Chart(chLine, {
    type: 'bar',
    data: chartData,
    options: {
      legend:{
        display: false
      }
    }
  });
}