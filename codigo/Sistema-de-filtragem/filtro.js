// Dados do post em formato JSON
var post = [
  {
    "titulo": "Hades",
    "imagem": "https://cdn.cloudflare.steamstatic.com/steam/apps/1145360/capsule_616x353.jpg?t=1680293801",
    "classe": "jogo",
    "link": "https://store.steampowered.com/app/1145360/Hades/?l=brazilian",
    "id": 0
  },
  {
    "titulo": "Hollow Knight",
    "imagem": "https://cdn.cloudflare.steamstatic.com/steam/apps/367520/capsule_616x353.jpg?t=1667006028",
    "classe": "jogo",
    "link": "https://store.steampowered.com/app/367520/Hollow_Knight/",
    "id": 1
  },
  {
    "titulo": "Fiz uma fanart do meu jogo favorito",
    "imagem": "https://i.pinimg.com/564x/34/81/d7/3481d733010fe09cdc729ac12178b40a.jpg",
    "classe": "comunidade",
    "link": "https://br.pinterest.com/pin/697143217304173838/?amp_client_id=CLIENT_ID%28_%29&mweb_unauth_id=%7B%7Bdefault.session%7D%7D&simplified=true",
    "id": 2
  },
  {
    "titulo": "hahaha",
    "imagem": "https://preview.redd.it/6e7xim2s8ls81.png?width=960&crop=smart&auto=webp&v=enabled&s=d5367919661d78e4a9ae5f787faa68458407da7c",
    "classe": "comunidade",
    "link": "https://www.reddit.com/r/Eldenring/comments/u054op/elden_ring_community/",
    "id": 3
  }
];

// Função para gerar o HTML dos posts
function gerarPosts() {
  let conteiner = document.getElementById('conteiner-posts');
  let strHtml = '';

  for (let i = 0; i < post.length; i++) {
    if (post[i].classe === "jogo") {
      strHtml += `
      <div class="filterDiv jogos">
      <div class="column">
      <div class="card">
      <a href="${post[i].link}" target="_blank">
        <h3> <img class="logo barra-azul"
            src="${post[i].imagem}" </h3>
        </a>
      </div>
    </div>
    </div>
        `;
    } else {
      strHtml += `
      <div class="filterDiv comunidade">
      <div class="column">
      <div class="card">
      <a href="${post[i].link}" target="_blank">
        <h3> <img class="logo barra-laranja"
            src="${post[i].imagem}" </h3>
        </a>
      </div>
    </div>
    </div>
        `;
    }
  }


  conteiner.innerHTML = strHtml;
}



// Chama a função para gerar os posts quando a página é carregada
window.onload = gerarPosts;



function filterSelection(c) {
  var x, i;
  x = document.getElementsByClassName("filterDiv");

  // Verificar cada elemento gerado dinamicamente
  for (i = 0; i < x.length; i++) {
    if (x[i].className.indexOf(c) > -1 || c === "all") {
      // Aplicar a classe "show" aos elementos correspondentes
      x[i].style.display = "block";
    } else {
      // Esconder os elementos que não correspondem à categoria selecionada
      x[i].style.display = "none";
    }
  }

  // Remover a classe "active" de todos os botões
  var btns = document.getElementsByClassName("btn");
  for (i = 0; i < btns.length; i++) {
    btns[i].classList.remove("active");
  }

  // Adicionar a classe "active" ao botão clicado
  var clickedBtn = document.getElementById(c);
  clickedBtn.classList.add("active");
}


