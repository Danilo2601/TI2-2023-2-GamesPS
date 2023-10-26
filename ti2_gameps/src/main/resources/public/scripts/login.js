let btn_entrar = document.querySelector('#btn_entrar')

btn_entrar.addEventListener('click',() =>{

    let login = document.querySelector('#username')

    let senha = document.querySelector('#password')

    let listaUser = []

    let userValid = {
        usuario: null,
        nome: null,
        sobrenome: null,
        email: null,
        senha: null
    }

    listaUser = JSON.parse(localStorage.getItem('listaUser'))

    listaUser?.forEach((item) => {

        if (login.value == item.usuario && senha.value == item.senha) {

            userValid = {
                usuario: item.usuario,
                nome: item.nome,
                sobrenome: item.sobrenome,
                email: item.email,
                senha: item.senha,
                sobre: item.sobre
            }

        }
    })

    if (login.value == userValid.usuario && senha.value == userValid.senha) {

        window.location.href = 'usuario.html'

    } else {

        alert('Login ou senha inválidos')
        window.location.href = 'login.html'

    }

})