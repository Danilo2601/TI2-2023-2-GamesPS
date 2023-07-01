
let content = ""

window.addEventListener('load', () => 
{
    fetch('games.json') 
      .then(response => response.json())
      .then(data => {
        let estado = 0
        let fala = ""
        for (let i = 0; i < data.jogos.length; i++) {
            estado = data.jogos[i].promo.state
            console.log(estado)
            if(estado == 1)
            {
                content += `<li><div class="vengeance"><img id="item-${data.jogos[i].id}" class="foton" src="${data.jogos[i].image}" alt=""><div class="nombre">${data.jogos[i].name}</div><div class="promo">${data.jogos[i].promo.prcnt}%</div></div></li>`
                fala += `Promoção no jogo: ${data.jogos[i].name}\n`
            }
            else
            {
                content += `<li><div class="revenge"><img id="item-${data.jogos[i].id}" class="foton" src="${data.jogos[i].image}" alt=""><div class="nombre">${data.jogos[i].name}</div></div></li>`;
            }
        }
        document.getElementById('caroussel').innerHTML = content
        carrosselLoad()
        if(fala != "")
        {
           alert(fala)
        }
      })
      .catch(error => {
        console.error('Erro ao carregar o arquivo JSON:', error)
    })
    
})
   