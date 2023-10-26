let btn_salvar = document.querySelector('#btn_salvar')


let login = document.querySelector('#txt_login')
let labelLogin = document.querySelector('#labelLogin')
let validLogin = false

let nome = document.querySelector('#txt_nome')
let labelNome = document.querySelector('#labelNome')
let validNome = false

let dataNascimento = document.querySelector('#txt_data_nascimento');
let labelDataNascimento = document.querySelector('#labelDate');
let validDataNascimento = false;

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
        labelLogin.innerHTML = '<label for="usuario" class="form-label"><h4 style="color: red;">Usuário *insira no mínimo 3 caracteres</h4></label>'
        login.setAttribute('style', 'border-color:red')
        validLogin = false
    } else {
        login.setAttribute('style', 'border-color:green')
        labelLogin.innerHTML = '<label for="usuario" class="form-label"><h4 style="color: white;">Usuário</h4></label>'
        validLogin = true
    }
})

nome.addEventListener('keyup', () => {
    if (nome.value.length <= 2) {
        labelNome.innerHTML = '<label for="nome" class="form-label"><h4 style="color: red;">Nome *insira no mínimo 3 caracteres</h4></label>'
        nome.setAttribute('style', 'border-color:red')
        validNome = false
    } else {
        nome.setAttribute('style', 'border-color:green')
        labelNome.innerHTML = '<label for="nome" class="form-label"><h4 style="color: white;">Nome</h4></label>'
        validNome = true
    }
})

email.addEventListener('keyup', () => {
    if (email.value.length <= 4) {
        labelEmail.innerHTML = '<label for="email" class="form-label"><h4 style="color: red;">E-mail *e-mail inválido</h4></label>'
        email.setAttribute('style', 'border-color:red')
        validEmail = false
    } else {
        email.setAttribute('style', 'border-color:green')
        labelEmail.innerHTML = '<label for="email" class="form-label"><h4 style="color: white;">E-mail</h4></label>'
        validEmail = true
    }
})

dataNascimento.addEventListener('blur', () => {
    const enteredDate = new Date(dataNascimento.value);
    const currentDate = new Date();
    
    if (isNaN(enteredDate.getTime()) || enteredDate > currentDate) {
        labelDataNascimento.innerHTML = '<label for="data_nascimento" class="form-label"><h4 style="color: red;">Data de Nascimento inválida</h4></label>';
        dataNascimento.setAttribute('style', 'border-color:red');
        validDataNascimento = false;
    } else {
        dataNascimento.setAttribute('style', 'border-color:green');
        labelDataNascimento.innerHTML = '<label for="data_nascimento" class="form-label"><h4 style="color: white;">Data de Nascimento</h4></label>';
        validDataNascimento = true;
    }
});

senha.addEventListener('keyup', () => {
    if (senha.value.length <= 5) {
        senha.setAttribute('style', 'border-color:red')
        labelSenha.innerHTML = '<label for="senha" class="form-label"><h4 style="color: red;">Senha *insira no mínimo 6 caracteres</h4></label>'
        validSenha = false
    } else {
        senha.setAttribute('style', 'border-color:green')
        labelSenha.innerHTML = '<label for="senha" class="form-label"><h4 style="color: white;">Senha</h4></label>'
        validSenha = true
    }
})

senha2.addEventListener('keyup', () => {
    if (senha2.value != senha.value) {
        senha2.setAttribute('style', 'border-color:red')
        labelSenha2.innerHTML = '<label for="senha" class="form-label"><h4 style="color: red;">Confirmação de Senha *as senhas não batem</h4></label>'
        validSenha2 = false
    } else {
        senha2.setAttribute('style', 'border-color:green')
        labelSenha2.innerHTML = '<label for="senha" class="form-label"><h4 style="color: white;">Confirmação de Senha</h4></label>'
        validSenha2 = true
    }
})


btn_salvar.addEventListener('click', () => {
    if (validEmail && validLogin && validNome && validSenha && validSenha2 && validDataNascimento) {
		
		alert('Conta Criada')
       window.location.href = '/gamesps/cadastro';

    } else {

        alert('Cadastro Inválido')
        window.location.href = 'login.html';
        
    }


})