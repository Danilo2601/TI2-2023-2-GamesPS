


function toggleComentarios() {
  var comentarios = document.querySelector(".post-forum");
  if (comentarios.style.display === "none") {
    comentarios.style.display = "block";
  } else {
    comentarios.style.display = "none";
  }
}

function addPost() {
  // pega o valor do texto do usuário
  const postText = document.getElementById("text-area").value;

  // verifica se o valor não está vazio
  if (postText !== "") {
    // cria um objeto post com a propriedade "texto"

    // pega o array de posts do LocalStorage ou cria um array vazio
    const posts = JSON.parse(localStorage.getItem("posts")) || [];

    const post = {
      id: posts.length + 1,
      texto: postText,
    };

    // adiciona o novo post ao array de posts
    posts.push(post);

    // salva o array de posts atualizado no LocalStorage
    localStorage.setItem("posts", JSON.stringify(posts));

    // limpa o campo de texto
    document.getElementById("text-area").value = "";

    // atualiza a lista de posts
    updatePosts();
  }

  alert("Post criado!");
}

function updatePosts() {
  // pega o elemento onde os posts serão exibidos
  const postsDiv = document.getElementById("posts");

  // pega o array de posts do LocalStorage ou cria um array vazio
  const posts = JSON.parse(localStorage.getItem("posts")) || [];

  // limpa o conteúdo anterior
  postsDiv.innerHTML = "";

  // adiciona cada post ao elemento
  posts.forEach((post) => {
    let str = "";

    str += `<div id="topo-post-forum">
      <div class="img-user-forum left">
        <img src="http://placehold.it/60x60">
      </div>
      <div class="descricao-post left">
        <h2><a href="">Nome do usuário</a></h2>
        <ul class="interacao-forum">
          <li>Usuário desde setembro/2014</li>
          <li><a href="">Ver perfil</a></li>
          <li><a href="post.html">Ver postagem</a></li>
        </ul>

      </div>
      <div class="clear"></div>
    </div>
      <div>${post.texto}</div>
    <div id="texto-comunidade">

      <ul class="interacao-forum">
        <li>Postado há 2 minutos</li>
        <li onclick="responder()" id="respnder-btn"><a href="#">Responder</a></li>
        <div id="caixa-resposta" style="display:none">
          <textarea id="texto-resposta"></textarea>
          <button id="enviar-resposta">Enviar</button>
        </div>
    </div>
    <input onclick="toggleComentarios()" id="resposta-button" class="btn mb-2 toggle-replies" type="button"
      value="Comentários">`;

    str = `<div data-post-id="${post.id}">${str}</div>`;

    postsDiv.innerHTML += str;
  });
}

window.addEventListener("DOMContentLoaded", function () {
  updatePosts();
});

var btnResponder = document.getElementById("enviar-resposta");

// adiciona um listener de clique para o botão "Responder"
/*document.getElementById("enviar-resposta").addEventListener("click", function ()*/
var responder = () => {
  // seleciona a caixa de resposta
  var caixaResposta = document.getElementById("caixa-resposta");

  // altera o estilo da caixa de resposta para exibi-la
  if (caixaResposta.style.display === "none") {
    caixaResposta.style.display = "block";
  } else {
    caixaResposta.style.display = "none";
  }
};

onload = function () {

  url = window.location.href;
  id = url.substring(url.lastIndexOf('#') + 1);
  document.querySelector("div.i1").innerHTML = `<iframe src="../Apresentaçao de jogos com ranking/index.html#${id}" frameborder="0"></iframe>`;
  document.querySelector("div.i2").style.display = "none";
  var show = false;
  document.querySelector("#graph").addEventListener("click", () => {
    if (!show) {
      document.querySelector("div.i2").style.display = "block";
      document.querySelector("div.i2").innerHTML = `<iframe src="../Grafico/index.html#${id - 1}" frameborder="0"></iframe>`;
      show = true;
    }
    else {
      document.querySelector("div.i2").style.display = "none";
      document.querySelector("div.i2").innerHTML = "";
      show = false;
    }
  });
}