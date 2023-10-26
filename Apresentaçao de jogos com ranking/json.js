// NOT DONE : SCROLL TO SELECTED

var ID = -1;
var start = (x) => {
    mImage(x);
    sImage(x);
    comp(x);
    score(x);
    plat(x);
    sino(x);
    price(x);
}
var price = (x) => {
    $('#price').text('Price: ' + x.plataform[0].price)
}
var score = (x) => {
    let f = (x.score.likes * 100) / x.score.total;
    x.score.avarage = f;
    let [n, n2] = (((Math.round(f) / 10)).toString()).split('.');
    $('#score').text(n + '.' + (n2 ? n2 : '0'));
}
var plat = (x) => {
    $('#plat #name').text(x.plataform[0].name)
    if (x.plataform.length > 1) {
        document.querySelector('#comp').innerHTML += `<input type="checkbox" id="menuToggle"></input> <nav id="plat"><label for="menuToggle" id="name">${x.plataform[0].name}</label><div id="expand"> </div></nav>`;
        for (let i = 1; i < x.plataform.length; i++) {
            document.querySelector('#expand').innerHTML += `<button id="${i}Button" class="exp">${x.plataform[i].name}</button>`;
        }
    }

}
var comp = (x) => {
    $('#comp #name').text(`${x.company}`);
}
var mImage = (x) => {
    let y = `<div class="carousel-item active"><img src="${x.mainImage}" class="d-block w-100"alt="..." /></div>`;
    let i;
    for (i = 0; i < x.subImages.length; i++) {
        y += `<div class="carousel-item"><img src="${x.subImages[i]}" class="d-block w-100"alt="..." /></div>`;
    }
    document.querySelector('.carousel-inner').innerHTML += y;
}
var sImage = (x) => {
    let y = `<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active 0 previw b S" aria-current="true" aria-label="Slide 1"> <img class="d-block"src="${x.mainImage}"class="img-fluid" />`;
    let i;
    for (i = 0; i < x.subImages.length - 1; i++) {
        y += `<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i + 1}" aria-label="Slide ${i + 2}" class="previw ${i + 1} b"> <img class="d-block"src="${x.subImages[i]}"class="img-fluid" />`;
    }
    y += `<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${i + 1}" aria-label="Slide ${i + 2}" class="previw ${i + 1} b E"> <img class="d-block"src="${x.subImages[i]}"class="img-fluid" />`
    document.querySelector('.carousel-indicators').innerHTML += y;
}
var sino = (x) => {
    document.querySelector('#sinopsys').innerHTML = '&nbsp&nbsp' + x.sinopsys;
}
var games;
onload = () => {


    fetch('../../Databank/data.json')
        .then(response => response.json())
        .then(data => {

            games = data;
            let url = window.location.href;
            let id = url.substring(url.lastIndexOf('#') + 1);
            let C;
            for (let i = 0; i < games.length; i++) {
                if (id == games[i].id) {
                    ID = i;
                    break;
                }
            }

            ready();

            let star = document.querySelector("#SAVE");

            star.addEventListener("click", function (event) {
                star.style.color = "yellow";

                const userLogado = JSON.parse(localStorage.getItem('userLogado')) || { wishlist: [] };

                if (userLogado['wishlist'] == '' || userLogado['wishlist'] == undefined || userLogado['wishlist'] == null) {
                    userLogado['wishlist'] = id;
                    localStorage.setItem('userLogado', JSON.stringify(userLogado));
                } else {
                    userLogado['wishlist'] += ',' + id;
                    localStorage.setItem('userLogado', JSON.stringify(userLogado));
                }

                if (userLogado['wishlist'].includes(id)) return alert('Jogo já adicionado à wishlist!');
            });

            let GO = () => {
                let [url1, url2] = url.split('#');
                if ((games.length > parseFloat($('#debugger').val())) && (parseFloat($('#debugger').val()) >= 0)) {
                    window.location.href = (url1 + '#' + games[parseFloat($('#debugger').val())].id);
                    location.reload();
                } else {
                    $('#debugger').val(ID);
                }
            }



            if (ID == -1) {
                $('#image').css("display", "none");
                ID = 0;
            }

            $('#debugger').val((ID < 1 ? 0 : ID));

            document.querySelector('#debugger').max = games.length - 1;

            $("#debugger").on('keyup', function (e) {
                if (e.key === 'Enter' || e.keyCode === 13) {
                    GO();
                }
            });

            $('#pag').text(games[ID].name);

            $(":input").bind('keyup change click', function (e) {
                if (!$(this).data("previousValue") || $(this).data("previousValue") != $(this).val()) {

                    $('#pag').text(games[$('#debugger').val()].name);

                    $(this).data("previousValue", $(this).val());
                }
            });
            $(":input").each(function () {
                $(this).data("previousValue", $(this).val());
            });

            start(games[ID]);
            $('#like').on("click", function () {
                games[ID].score.likes += 1;
                games[ID].score.total += 1;
                score(games[ID]);
            })
            $('#dislike').on("click", function () {
                games[ID].score.dislikes += 1;
                games[ID].score.total += 1;
                score(games[ID]);
            })

            $('.plus').on("click", function () {
                if (parseFloat($('#debugger').val()) < games.length - 1) {
                    $("#debugger").val(parseFloat($('#debugger').val()) + 1);
                    $('#pag').text(games[parseFloat($('#debugger').val())].name);
                    GO();
                }
            });
            $('.minus').on("click", function () {
                if (parseFloat($('#debugger').val()) > 1) {
                    $("#debugger").val(parseFloat($('#debugger').val()) - 1);
                    $('#pag').text(games[parseFloat($('#debugger').val())].name);
                    GO();
                }
            });
        });
}