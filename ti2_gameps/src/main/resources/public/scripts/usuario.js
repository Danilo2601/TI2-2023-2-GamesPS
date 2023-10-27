let btn_logout = document.querySelector('#btn_logout')

let listaUser = JSON.parse(localStorage.getItem('listaUser'))

btn_logout.addEventListener('click', () => {

    listaUser?.forEach((item) => {
        if (item.usuario == userLogado.usuario && item.senha == userLogado.senha) {
            item.sobre = userLogado.sobre
        }
    })

    localStorage.setItem('listaUser', JSON.stringify(listaUser))

    localStorage.removeItem('token')
    localStorage.removeItem('userLogado')
    window.location.href = 'login.html'

})

let userLogado = JSON.parse(localStorage.getItem("userLogado"));

const logado = document.querySelector("#logado");
logado.innerHTML = `${userLogado.usuario}`;

let btn_editar = document.querySelector('#btn_editar')
let btn_salvar = document.querySelector('#btn_salvar')
let sobreMim = document.querySelector('#sobreMim')
let containerEdit = document.querySelector("#edit")
let containerShow = document.querySelector("#show")
let foto = document.querySelector('#fotoPerfil')

containerEdit.style.display = 'none'


btn_editar.addEventListener('click', () => {

    if (containerEdit.style.display == 'none') {
        containerEdit.style.display = 'block'
        containerShow.style.display = 'none'
    } else {
        containerEdit.style.display = 'none'
        containerShow.style.display = 'block'
    }

})

btn_salvar.addEventListener('click', () => {



    if (containerShow.style.display == 'none') {
        containerShow.style.display = 'block'
        containerEdit.style.display = 'none'
    } else {
        containerShow.style.display = 'none'
        containerEdit.style.display = 'block'
    }

    userLogado['sobre'] = sobreMim.value;
    localStorage.setItem('userLogado', JSON.stringify(userLogado));

    if(userLogado.sobre == null || userLogado.sobre == undefined || userLogado.sobre == ''){
        userLogado['sobre'] = 'Escreva algo sobre você'
        localStorage.setItem('userLogado', JSON.stringify(userLogado));
    }

    const about = document.querySelector('#about');
    about.innerHTML = `${userLogado.sobre}`;
})

if(userLogado.sobre == null || userLogado.sobre == undefined || userLogado.sobre == ''){
    userLogado['sobre'] = 'Escreva algo sobre você'
    localStorage.setItem('userLogado', JSON.stringify(userLogado));
}

const about = document.querySelector('#about');
    about.innerHTML = `${userLogado.sobre}`;