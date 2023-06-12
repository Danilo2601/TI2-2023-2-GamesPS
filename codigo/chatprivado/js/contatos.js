function abrirChat() {
    var chatDiv = document.getElementById("chat-aberto");
    chatDiv.className = "";
    chatDiv.classList.toggle("disabled");

  const chatPopup = document.getElementById("contato-aberto");
  chatPopup.className = "";
  chatPopup.classList.toggle("active");
}

function voltar() {
  const chatPopupExit = document.getElementById("contato-aberto");
  chatPopupExit.className = "";
  chatPopupExit.classList.toggle("disabled");

  var chatDiv = document.getElementById("chat-aberto");
  chatDiv.className = "";
  chatDiv.classList.toggle("active");
}
