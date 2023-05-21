function toggleComentarios() {
    var comentarios = document.querySelector('.post-forum');
    if (comentarios.style.display === 'none') {
      comentarios.style.display = 'block';
    } else {
      comentarios.style.display = 'none';
    }
  }

  function addPost() {
    // pega o valor do texto do usuário
    const postText = document.getElementById("text-area").value;
  
    // verifica se o valor não está vazio
    if (postText !== "") {
      // cria um objeto post com a propriedade "texto"
      const post = {
        texto: postText,
      };
  
      // pega o array de posts do LocalStorage ou cria um array vazio
      const posts = JSON.parse(localStorage.getItem("posts")) || [];
  
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
    posts.forEach(post => {
      const postElement = document.createElement("div");
      postElement.innerText = post.texto;
      postsDiv.appendChild(postElement);
    });
  }

  var btnResponder = document.getElementById("respnder-btn");

  // adiciona um listener de clique para o botão "Responder"
  btnResponder.addEventListener("click", function() {
    // seleciona a caixa de resposta
    var caixaResposta = document.getElementById("caixa-resposta");
    
    // altera o estilo da caixa de resposta para exibi-la
    if (caixaResposta.style.display === 'none') {
      caixaResposta.style.display = "block";
    } 
    else {
      caixaResposta.style.display = 'none';
    }
  });

  
  