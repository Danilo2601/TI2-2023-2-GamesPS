// Java para o Header

var header = document.getElementById('header');
var navigationHeader = document.getElementById('navigation_header');
var content = document.getElementById('content');
var showSidebar = false;


function toggleSidebar() {
  showSidebar = !showSidebar;
  if (showSidebar) {
    navigationHeader.style.marginLeft = '-10vw';
    navigationHeader.style.animationName = 'showSidebar';
    content.style.filter = 'blur(2px)';
  }
  else {
    navigationHeader.style.marginLeft = '-100vw';
    navigationHeader.style.animationName = '';
    content.style.filter = '';
  }
}

function closeSidebar() {
  if (showSidebar) {
    toggleSidebar();
  }
}

window.addEventListener('resize', function (event) {
  if (window.innerWidth > 768 && showSidebar) {
    toggleSidebar();
    content.style.filter = '';
  }
});
// Fim Java para o Header


// JavaScript para a Barra de Pesquisa
const searchInput = document.getElementById("search-bar");
const resultsContainer = document.querySelector(".autocomplete-items");

// Função para carregar o arquivo JSON de jogos
function loadJogos(callback) {
  const xhr = new XMLHttpRequest();
  xhr.overrideMimeType("application/json");
  xhr.open("GET", "../Databank/data.json", true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      const jogos = JSON.parse(xhr.responseText);
      callback(jogos);
    }
  };
  xhr.send();
}

// Função para criar e exibir os cards dos jogos
function createGameCards(jogos) {
  let filtrage = document.querySelector('#flitargei').value.toLowerCase()
  const gameCardsContainer = document.getElementById("game-cards");
  gameCardsContainer.innerHTML = ""; // Limpa o container dos cards

  jogos.forEach(function (jogo) {

    if (filtrage == "") {
      const gameCard = document.createElement("div");
      gameCard.className = "game-card";

      const gameImage = document.createElement("img");
      gameImage.src = jogo.mainImage;
      gameCard.appendChild(gameImage);

      const gameTitle = document.createElement("h3");
      gameTitle.textContent = jogo.name;
      gameCard.appendChild(gameTitle);

      const gameURL = document.createElement("p");
      const gameLink = document.createElement("a");
      gameLink.href = "../pagJogo/chatdecomunidade/chatcomunidade.html#" + jogo.id;
      gameLink.textContent = "Ver mais";
      gameURL.appendChild(gameLink);
      gameCard.appendChild(gameURL);

      gameCardsContainer.appendChild(gameCard);
    }
    else if (filtrage == jogo.classificacao.toLowerCase()) {
      const gameCard = document.createElement("div");
      gameCard.className = "game-card";

      const gameImage = document.createElement("img");
      gameImage.src = jogo.mainImage;
      gameCard.appendChild(gameImage);

      const gameTitle = document.createElement("h3");
      gameTitle.textContent = jogo.name;
      gameCard.appendChild(gameTitle);

      const gameURL = document.createElement("p");
      const gameLink = document.createElement("a");
      gameLink.href = "../pagJogo/chatdecomunidade/chatcomunidade.html#" + jogo.id;
      gameLink.textContent = "Ver mais";
      gameURL.appendChild(gameLink);
      gameCard.appendChild(gameURL);

      gameCardsContainer.appendChild(gameCard);
    }

  });


}

// Função para exibir os jogos correspondentes à pesquisa
function showResults(jogos, searchString) {
  const filteredResults = jogos.filter(function (jogo) {

    return jogo.name.toLowerCase().includes(searchString.toLowerCase());
  });

  createGameCards(filteredResults);
}

// Variável para controlar se a pesquisa foi realizada por meio do Enter
let searchByEnter = false;

// Evento disparado ao digitar na barra de pesquisa
searchInput.addEventListener("input", function (event) {
  const searchString = this.value;

  if (searchString === "") {
    resultsContainer.innerHTML = "";
    return;
  }
});

// Evento disparado ao pressionar Enter
searchInput.addEventListener("keydown", function (event) {
  if (event.key === "Enter") { // Verifica se a tecla pressionada é o Enter
    event.preventDefault(); // Impede o comportamento padrão de enviar o formulário
    handleSearch();
  }
});

// Evento disparado ao clicar na lupa
document.querySelector(".search-btn").addEventListener("click", handleSearch);

// Função para lidar com a pesquisa
function handleSearch() {
  const searchString = searchInput.value.trim();

  if (searchString === "") {
    resultsContainer.innerHTML = "";
    return;
  }

  loadJogos(function (jogos) {
    showResults(jogos, searchString);
  });
}

function clearGameCards() {
  const gameCardsContainer = document.getElementById("game-cards");
  gameCardsContainer.innerHTML = "";
}







