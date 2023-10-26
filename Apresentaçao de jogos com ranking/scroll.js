

var ready = () => {
    // scroll inicial
    $(".carousel-indicators").scrollLeft(1);

    // declaracao dos botoes
    let Left = $('#scroll-left');
    let Right = $("#scrollRight");

    // teste de botoes
    let LeftT = () => { return ($(".carousel-indicators").scrollLeft() > 0) };
    let RightT = () => { return ($(".carousel-indicators").scrollLeft() < Width()) };


    // declaraçao de claridade
    $('.carousel-indicators').ready(function () {

        if (LeftT()) {
            $(Left).css('opacity', '0.5');
        } else $(Left).css('opacity', '0');

        if (RightT()) {
            $(Right).css('opacity', '0.5');
        } else $(Right).css('opacity', '0');

    })

    // teste de tamanho
    let Width = () => {
        let y = (((2 + parseFloat($('.previw').css("width"))) * (1 + games[ID].subImages.length)) - parseFloat($('.carousel-indicators').css("width")) - 2);
        return (y);
    }

    // funçao de repetiçao
    let t;
    function holdit(btn, action, start, speedup) {
        var repeat = function () {
            action();
            t = setTimeout(repeat, start);
            start = start / speedup;
        }
        btn.onmousedown = function () {
            repeat();
        }
        btn.onmouseup = function () {
            clearTimeout(t);
        }
    }

    // teste de hover
    Left.on("mouseenter", function () {

        if (LeftT()) {
            $(this).css('opacity', '1');
        } else $(this).css('opacity', '0');
    }).on("mouseleave", function () {
        if (LeftT()) {
            $(this).css('opacity', '0.5');
        } else $(this).css('opacity', '0');
    });
    Right.on("mouseenter", function () {
        if (RightT()) {
            $(this).css('opacity', '1');
        } else $(this).css('opacity', '0');
    }).on("mouseleave", function () {
        if (RightT()) {
            $(this).css('opacity', '0.5');
        } else $(this).css('opacity', '0');
    });


    // movimentaçao
    holdit(document.querySelector('#scroll-left'), function () {
        if (LeftT) {
            $(".carousel-indicators").scrollLeft($(".carousel-indicators").scrollLeft() - 10);
            Right.css('opacity', '0.5');

        } else {
            clearTimeout(t);
        }
        Left.triggerHandler("mouseenter");
    }, 10, 1)
    holdit(document.querySelector('#scrollRight'), function () {
        if (RightT) {
            $(".carousel-indicators").scrollLeft($(".carousel-indicators").scrollLeft() + 10);
            Left.css('opacity', '0.5');
        } else {
            clearTimeout(t);
        }
        Right.triggerHandler("mouseenter");
    }, 10, 1)

    $('.b').on("click", function () {
        for (let i = 0; i <= games[ID].subImages.length; i++) {
            if ($(`.carousel-indicators .${i}`).hasClass("active")) {

                let y = ((2 + parseFloat($('.previw').css("width"))) * (1 + i)); //- parseFloat($('.carousel-indicators').css("width")));
                if (y > $(".carousel-indicators").scrollLeft() + parseFloat($('.carousel-indicators').css("width"))){
                    $(".carousel-indicators").scrollLeft ($(".carousel-indicators").scrollLeft() + (((2 + parseFloat($('.previw').css("width"))) * (1 + i))- parseFloat($('.carousel-indicators').css("width"))));
                } else if (y - (2 + parseFloat($('.previw').css("width"))) < ($(".carousel-indicators").scrollLeft())){
                    $(".carousel-indicators").scrollLeft (y - (2 + parseFloat($('.previw').css("width"))));
                }
                else{
                    break;
                }
                break;
            }
        }
    })
}