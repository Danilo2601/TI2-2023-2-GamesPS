function aparecerChat() {
  var messages = document.getElementById("messages");
  var userImg = document.getElementById("user-img");
  var notification = document.getElementById("notification");
  var chat = document.getElementById("chat");

  messages.style.display = "none";
  userImg.style.display = "none";
  notification.style.display = "none";
  chat.style.display = "none";
}

function fecharChat() {
    var messages = document.getElementById("messages");
    var userImg = document.getElementById("user-img");
    var notification = document.getElementById("notification");
    var chat = document.getElementById("chat");
  
    messages.style.display = "block";
    userImg.style.display = "block";
    notification.style.display = "block";
    chat.style.display = "block";
  }
  

const chatButton = document.getElementById("btn");
const chatPopup = document.getElementById("chat-aberto");


chatButton.addEventListener("click", () => {
  chatPopup.className = '';
  chatPopup.classList.toggle("active");
});

const chatButtonOpen = document.getElementById("btn-chatAberto");

chatButtonOpen.addEventListener("click", () => {
  chatPopup.className = '';
  chatPopup.classList.toggle("disabled");
});
