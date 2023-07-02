// Java para o Header

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min + 1)) + min;
}





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

// Java para o slider
var slides = document.querySelectorAll('.slide');
var btns = document.querySelectorAll('.btn');
let currentSlide = 1;

var manualNav = function (manual) {
  slides.forEach((slide) => {
    slide.classList.remove('active');

    btns.forEach((btn) => {
      btn.classList.remove('active');
    });
  });

  slides[manual].classList.add('active');
  btns[manual].classList.add('active');
}

btns.forEach((btn, i) => {
  btn.addEventListener("click", () => {
    manualNav(i);
    currentSlide = i;
  });
});

var repeat = function (activeClass) {
  let active = document.getElementsByClassName('active');
  let i = 1;

  var repeater = () => {
    setTimeout(function () {
      [...active].forEach((activeSlide) => {
        activeSlide.classList.remove('active');
      });

      slides[i].classList.add('active');
      btns[i].classList.add('active');
      i++;

      if (slides.length == 1) {
        i = 0;
      }
      if (i >= slides.length) {
        i = 0; // Volta para a primeira imagem quando atingir a última imagem
      }
      repeater();
    }, 5000);
  }
  repeater();
}
repeat();

// Função para embaralhar um array usando o algoritmo de Fisher-Yates
function shuffleArray(array) {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}

// Função para carregar as imagens aleatórias
function loadRandomImages(images) {
  const randomImages = shuffleArray(images).slice(0, 4); // Seleciona aleatoriamente 4 imagens do array
  slides.forEach((slide, index) => {
    const image = randomImages[index];
    if (image) {
      slide.innerHTML = `<img src="${image.url}" alt="Imagem ${index + 1}">`;
      slide.classList.add(image.class);
    } else {
      slide.innerHTML = ''; // Limpa o slide se não houver imagem correspondente
      slide.classList.remove('active');
      btns[index].classList.remove('active');
    }
  });
}

// Carregar imagens do JSON
fetch('Json/imagens.json')
  .then(response => response.json())
  .then(data => {
    // Atualizar o HTML com as imagens do JSON
    loadRandomImages(data.imagens);

    // Iniciar o slider
    var manualNav = function (manual) {
      slides.forEach((slide) => {
        slide.classList.remove('active');
        btns.forEach((btn) => {
          btn.classList.remove('active');
        });
      });

      slides[manual].classList.add('active');
      btns[manual].classList.add('active');
    }

    btns.forEach((btn, i) => {
      btn.addEventListener("click", () => {
        manualNav(i);
        currentSlide = i;
      });
    });

    var repeat = function (activeClass) {
      let active = document.getElementsByClassName('active');
      let i = 1;

      var repeater = () => {
        setTimeout(function () {
          [...active].forEach((activeSlide) => {
            activeSlide.classList.remove('active');
          });

          slides[i].classList.add('active');
          btns[i].classList.add('active');
          i++;

          if (slides.length == 1) {
            i = 0;
          }
          if (i >= slides.length) {
            return;
          }
          repeater();
        }, 5000);
      }
      repeater();
    }

    repeat();
  })





fetch('Databank/data.json')
  .then(response => response.json())
  .then(data => {
    console.log(data);
    console.log(data[0].id);

    let conteiner = document.getElementById('conteiner-posts');
    let strHtml = '';

    for (let i = 0; i < data.length; i++) {
      strHtml += `
        <div class="card">
        <a class="barra-azul" href="pagJogo/chatdecomunidade/chatcomunidade.html#${data[i].id}" target="_blank">
          <h3> <img class="logo"
              src="${data[i].mainImage}" </h3>
          </a>
        </div>
          `;
    }


    conteiner.innerHTML = strHtml;


  })