backButton = document.querySelector("#back");

// verificando se o botão foi encontrado
if (backButton) {
  // adicionando um evento de clique ao botão
  backButton.addEventListener("click", () => {
    // redirecionando para a página "chatcomunidade.html"
    window.location.href = "chatcomunidade.html";
  });
} else {
  console.error("O botão de voltar não foi encontrado na página.");
}