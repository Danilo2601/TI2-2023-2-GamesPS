const inputChat = document.getElementById("search-tela");
const mensagemChat = document.getElementById("mensagem");

inputChat.addEventListener("keyup", function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    const mensagem = inputChat.value;
    if (mensagem.trim() !== "") {
      inputChat.value = "";
      salvarMensagem(mensagem);
      exibirMensagensSalvas();
    }
  }
});

function salvarMensagem(mensagem) {
  const mensagensSalvas = localStorage.getItem("mensagens");
  let mensagens = [];

  if (mensagensSalvas) {
    mensagens = JSON.parse(mensagensSalvas);
  }

  mensagens.push(mensagem);
  localStorage.setItem("mensagens", JSON.stringify(mensagens));
}

function exibirMensagensSalvas() {
  mensagemChat.innerHTML = "";

  const mensagensSalvas = localStorage.getItem("mensagens");
  let mensagens = [];

  if (mensagensSalvas) {
    mensagens = JSON.parse(mensagensSalvas);
  }

  mensagens.forEach(function(mensagem) {
    const novaDiv = document.createElement("div"); // Criar uma div para cada mensagem
    const novaMensagem = document.createElement("p");
    novaMensagem.textContent = mensagem;
    novaDiv.appendChild(novaMensagem); // Adicionar a mensagem à div
    mensagemChat.appendChild(novaDiv); // Adicionar a div ao elemento de mensagens
  });
}

// Carregar mensagens salvas ao carregar a página
exibirMensagensSalvas();
