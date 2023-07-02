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
    let str = ""

    str += `<p class="p-mensagem">${mensagem}</p>`
    document.getElementById("mensagem").innerHTML += str;
  });
}

// Carregar mensagens salvas ao carregar a página
window.addEventListener("DOMContentLoaded", function() {
  exibirMensagensSalvas();
});
