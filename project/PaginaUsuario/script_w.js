
let content = ""

window.addEventListener('load', () => 
{
    fetch('../Databank/data.json') 
      .then(response => response.json())
      .then(data => {
        let estado = 0
        let fala = ""
        userlogado = JSON.parse(localStorage.getItem('userLogado')) || { wishlist: [] };
        let resultado = userlogado.wishlist.split(',')
        for (let i = 0; i < resultado.length; i++) {
            if(resultado[i] == data[i].id)
            {
                if(estado == 1)
            {
                content += `<li><div class="vengeance"><img id="item-${data[i].id}" class="foton" src="${data[i].mainImage}" alt=""><div class="nombre">${data[i].name}</div><div class="promo">${data[i].promo.prcnt}%</div></div></li>`
                fala += `Promoção no jogo: ${data[i].name}\n`
            }
            else
            {
                content += `<li><div class="revenge"><img id="item-${data[i].id}" class="foton" src="${data[i].mainImage}" alt=""><div class="nombre">${data[i].name}</div></div></li>`;
            }
            }
            console.log(estado)
            
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
   