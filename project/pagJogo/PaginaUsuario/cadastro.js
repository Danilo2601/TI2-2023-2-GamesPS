let btn_salvar = document.querySelector('#btn_salvar')


let login = document.querySelector('#txt_login')
let labelLogin = document.querySelector('#labelLogin')
let validLogin = false

let nome = document.querySelector('#txt_nome')
let labelNome = document.querySelector('#labelNome')
let validNome = false

let sobrenome = document.querySelector('#txt_sobrenome')
let labelSobrenome = document.querySelector('#labelSobrenome')
let validSobrenome = false

let email = document.querySelector('#txt_email')
let labelEmail = document.querySelector('#labelEmail')
let validEmail = false

let senha = document.querySelector('#txt_senha')
let labelSenha = document.querySelector('#labelSenha')
let validSenha = false

let senha2 = document.querySelector('#txt_senha2')
let labelSenha2 = document.querySelector('#labelSenha2')
let validSenha2 = false


login.addEventListener('keyup', () => {
    if (login.value.length <= 2) {
        labelLogin.innerHTML = '<label for="username" class="form-label"><h4 style="color: red;">Usuário *insira no mínimo 3 caracteres</h4></label>'
        login.setAttribute('style', 'border-color:red')
        validLogin = false
    } else {
        login.setAttribute('style', 'border-color:green')
        labelLogin.innerHTML = '<label for="username" class="form-label"><h4 style="color: white;">Usuário</h4></label>'
        validLogin = true
    }
})

nome.addEventListener('keyup', () => {
    if (nome.value.length <= 2) {
        labelNome.innerHTML = '<label for="username" class="form-label"><h4 style="color: red;">Nome *insira no mínimo 3 caracteres</h4></label>'
        nome.setAttribute('style', 'border-color:red')
        validNome = false
    } else {
        nome.setAttribute('style', 'border-color:green')
        labelNome.innerHTML = '<label for="username" class="form-label"><h4 style="color: white;">Nome</h4></label>'
        validNome = true
    }
})

sobrenome.addEventListener('keyup', () => {
    if (sobrenome.value.length <= 3) {
        labelSobrenome.innerHTML = '<label for="username" class="form-label"><h4 style="color: red;">Sobrenome *insira no mínimo 4 caracteres</h4></label>'
        sobrenome.setAttribute('style', 'border-color:red')
        validSobrenome = false
    } else {
        sobrenome.setAttribute('style', 'border-color:green')
        labelSobrenome.innerHTML = '<label for="username" class="form-label"><h4 style="color: white;">Sobrenome</h4></label>'
        validSobrenome = true
    }
})

email.addEventListener('keyup', () => {
    if (email.value.length <= 4) {
        labelEmail.innerHTML = '<label for="username" class="form-label"><h4 style="color: red;">E-mail *e-mail inválido</h4></label>'
        email.setAttribute('style', 'border-color:red')
        validEmail = false
    } else {
        email.setAttribute('style', 'border-color:green')
        labelEmail.innerHTML = '<label for="username" class="form-label"><h4 style="color: white;">E-mail</h4></label>'
        validEmail = true
    }
})

senha.addEventListener('keyup', () => {
    if (senha.value.length <= 5) {
        senha.setAttribute('style', 'border-color:red')
        labelSenha.innerHTML = '<label for="username" class="form-label"><h4 style="color: red;">Senha *insira no mínimo 6 caracteres</h4></label>'
        validSenha = false
    } else {
        senha.setAttribute('style', 'border-color:green')
        labelSenha.innerHTML = '<label for="username" class="form-label"><h4 style="color: white;">Senha</h4></label>'
        validSenha = true
    }
})

senha2.addEventListener('keyup', () => {
    if (senha2.value != senha.value) {
        senha2.setAttribute('style', 'border-color:red')
        labelSenha2.innerHTML = '<label for="username" class="form-label"><h4 style="color: red;">Confirmação de Senha *as senhas não batem</h4></label>'
        validSenha2 = false
    } else {
        senha2.setAttribute('style', 'border-color:green')
        labelSenha2.innerHTML = '<label for="username" class="form-label"><h4 style="color: white;">Confirmação de Senha</h4></label>'
        validSenha2 = true
    }
})

function generateUUID() { // Public Domain/MIT
    var d = new Date().getTime();//Timestamp
    var d2 = (performance && performance.now && (performance.now()*1000)) || 0;//Time in microseconds since page-load or 0 if unsupported
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16;//random number between 0 and 16
        if(d > 0){//Use timestamp until depleted
            r = (d + r)%16 | 0;
            d = Math.floor(d/16);
        } else {//Use microseconds since page-load if supported
            r = (d2 + r)%16 | 0;
            d2 = Math.floor(d2/16);
        }
        return (c === 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
}


btn_salvar.addEventListener('click', () => {
    if (validEmail && validLogin && validNome && validSobrenome && validSenha && validSenha2) {

        alert('Conta criada')

        let listaUser = JSON.parse(localStorage.getItem('listaUser') || '[]')

        listaUser.push(
            {
                id: generateUUID(),
                usuario: login.value,
                nome: nome.value,
                sobrenome: sobrenome.value,
                email: email.value,
                senha: senha.value,
                sobre: null
            }
        )

        localStorage.setItem('listaUser', JSON.stringify(listaUser))
        
        setTimeout(()=>{
            window.location.href = 'login.html'
        }, 2000)


    } else {

        alert('Cadastro Inválido')
        
    }


})