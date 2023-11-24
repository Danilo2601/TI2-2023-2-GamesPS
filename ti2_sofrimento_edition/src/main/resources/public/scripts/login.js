let btn_entrar = document.querySelector('#btn_entrar')

btn_entrar.addEventListener('click',() =>{

    let login = document.querySelector('#username')

    let senha = document.querySelector('#password')

    if (login.value == userValid.usuario && senha.value == userValid.senha) {

        window.location.href = 'usuario.html'

    } else {

        alert('Login ou senha inválidos')
        window.location.href = 'login.html'

    }

})