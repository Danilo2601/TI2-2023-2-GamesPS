var carrosselLoad = () => {
    //Button Left
    let BtLeft = document.getElementById("left");
    //Button Right
    let BtRight = document.getElementById("right");
    //Parent of cards
    const caroussel = document.getElementById("caroussel");


    //First Card
    let Fst = document.querySelector("#caroussel>li:first-child");
    //Last Card
    let Lst = document.querySelector("#caroussel>li:last-child");

    //Inicial Values
    let Wait = false;
    let Size = -1;
    let Start = [];
    let Finish = [];

    //Find Number of Cards
    for (Size = 1; document.querySelector(`#caroussel>li:nth-child(${Size})`) != Lst; Size++);

    //Main card (class "P<value>" --- 0 middle, -1 left, +1 right,can go from -9 to 99)
    let Main = parseInt(Size / 2) + (Size % 2) + parseInt(caroussel.className.substring(caroussel.className.lastIndexOf("P") + 1, caroussel.className.lastIndexOf("P") + 4));
    // Number of Main cards (class "N<value>" --- 1 = 1 main card , 2 = 2 main cards, 3 = 3 main cards,can go from 1 to 99)
    let Nmain = parseInt(caroussel.className.substring(caroussel.className.lastIndexOf("N") + 1, caroussel.className.lastIndexOf("N") + 3));
    // Number of cards to move (class "M<value>" --- 1 = 1 card, 2 = 2 cards, 3 = 3 cards,can go from 1 to 99)
    let Pmain = parseInt(caroussel.className.substring(caroussel.className.lastIndexOf("M") + 1, caroussel.className.lastIndexOf("M") + 3));
    Main = (Main ? Main : 0);
    Nmain = (Nmain > 1 ? Nmain : 1);
    Pmain = (Pmain > 1 ? Pmain : 1);

    // Main organizer
    let Dmain = () => {
        for (let i = 0; i < Nmain; i++) {
            document.querySelector(`#caroussel>li:nth-child(${Main + i - parseInt(Nmain / 2)})`).className = "Main";
        }
    }
    Dmain();

    //Opacity
    let OpacityM = window.getComputedStyle(document.querySelector(".Main")).getPropertyValue("opacity");
    let OpacityS = window.getComputedStyle(Lst).getPropertyValue("opacity");

    //Size
    let Gap = parseInt(window.getComputedStyle(Fst.parentElement).gap);
    let Offset = (x) => {
        return Gap + ((document.querySelector(`#caroussel>li:nth-child(${Main + x})`).clientWidth + document.querySelector(`#caroussel>li:nth-child(${Main})`).clientWidth) / 2)
    }

    if (Size % 2 != Nmain % 2) {
        Fst.style.margin = `0 0 0 ${Lst.clientWidth + Gap}px`;
    }

    //Animation
    const Cgo = (x) => {
        return ([
            { transform: `translateX(0px)` },
            { transform: `translateX(${x}px)`, easing: "ease-out" }]);
    };

    // Speed regulation
    let STi = Nmain * 1;
    let start = STi;
    let STz = Nmain * 100;
    let Rep = (document.querySelector(".carousel-options.repeat") ? true : false)
    let RepTiming = 100 * parseInt(caroussel.className.substring(caroussel.className.lastIndexOf("S") + 1, caroussel.className.lastIndexOf("S") + 4));


    //Animation Timing config
    const CTiming = {
        //Animations duration - Milisseconds
        duration: (Pmain * STz) / 3,
        iterations: 1,
    };
    const ATiming = {
        //Animations duration - Milisseconds
        duration: STz / 4,
        iterations: 1,
    };


    // variavel de repetiçao
    let t;
    let TO = true;
    let Clicked = false;
    let RepA;

    let scrollLeft = (btn, speedup) => {
        let repeat = (x) => {
            if (x > 0) {
                speedup = 1;
                start = x;
            }
            Gap = parseInt(window.getComputedStyle(Fst.parentElement).gap);
            caroussel.animate(Cgo(Pmain * Offset(-1)), CTiming).onfinish = () => {
                for (let j = 0; j < Pmain; j++) {
                    let Lhtml = Lst.innerHTML;
                    for (let i = Size; i > 1; i--) {
                        document.querySelector(`#caroussel>li:nth-child(${i})`).innerHTML = document.querySelector(`#caroussel>li:nth-child(${i - 1}n)`).innerHTML;
                    }
                    Fst.innerHTML = Lhtml;
                }
                if (TO) {
                    t = setTimeout(repeat, start);
                    start = start / speedup;
                } else {
                    clearTimeout(t);
                    for (let i = 0; i < Nmain; i++) {
                        document.querySelector(`#caroussel>li:nth-child(${Main + i - parseInt(Nmain / 2)})`).className = "Main";
                    }
                    Start = [Lst.clientWidth, Lst.clientHeight];
                    Finish = [document.querySelector(".Main").clientWidth, document.querySelector(".Main").clientHeight];
                    Dmain();
                    document.querySelectorAll(".Main").forEach((e) => {
                        e.animate([
                            { width: `${Start[0]}px`, height: `${Start[1]}px`, opacity: `${OpacityS}` },
                            { width: `${Finish[0]}px`, height: `${Finish[1]}px`, opacity: `${OpacityM}`, easing: "ease-out" }
                        ], ATiming);
                    });
                    start = STi;
                    Wait = false;
                    if (Rep) {
                        RepA = setTimeout(() => {
                            if (!TO) {
                                repeat(RepTiming);
                            }
                        }, RepTiming);
                    }
                }
            };
        }
        btn.onmousedown = function () {
            clearTimeout(RepA);
            console.log("LeftButton Pressed");
            btn.style.color = "#034C75";
            if (Wait) {
                console.log("LeftButton Error : Please Wait");
            }
            else {
                S = 0;
                TO = true;
                Wait = true;
                Start = [document.querySelector("#caroussel>li:first-child").clientWidth, document.querySelector("#caroussel>li:first-child").clientHeight];
                Finish = [document.querySelector(".Main").clientWidth, document.querySelector(".Main").clientHeight];
                document.querySelectorAll(".Main").forEach((e) => {
                    e.animate([
                        { width: `${Finish[0]}px`, height: `${Finish[1]}px`, opacity: `${OpacityM}` },
                        { width: `${Start[0]}px`, height: `${Start[1]}px`, opacity: `${OpacityS}`, easing: "ease-out" }
                    ], ATiming)
                });
                document.querySelector(`#caroussel>li:nth-child(${Main})`).animate([
                    { width: `${Finish[0]}px`, height: `${Finish[1]}px`, opacity: `${OpacityM}` },
                    { width: `${Start[0]}px`, height: `${Start[1]}px`, opacity: `${OpacityS}`, easing: "ease-out" }
                ], ATiming).onfinish = () => {
                    document.querySelectorAll(".Main").forEach((e) => { e.className = " "; });
                    Clicked = true;
                    repeat(0);
                }
            }
        }
        btn.onmouseup = function () {
            TO = false;
            btn.style.color = "#0CA789"
        }
        btn.onmouseleave = function () {
            if (Clicked) {
                TO = false;
                btn.style.color = "#0CA789"
            }
        }

    }


    let scrollRight = (btn, speedup) => {
        let repeat = (x) => {
            if (x > 0) {
                speedup = 1;
                start = x;
            }
            Gap = parseInt(window.getComputedStyle(Fst.parentElement).gap);
            caroussel.animate(Cgo(Pmain * -Offset(1)), CTiming).onfinish = () => {
                for (let j = 0; j < Pmain; j++) {
                    let Lhtml = Fst.innerHTML;
                    for (let i = 1; i < Size; i++) {
                        document.querySelector(`#caroussel>li:nth-child(${i})`).innerHTML = document.querySelector(`#caroussel>li:nth-child(${i + 1}n)`).innerHTML;
                    }
                    Lst.innerHTML = Lhtml;
                }
                if (TO) {
                    t = setTimeout(repeat, start);
                    start = start / speedup;
                } else {
                    clearTimeout(t);
                    for (let i = 0; i < Nmain; i++) {
                        document.querySelector(`#caroussel>li:nth-child(${Main + i - (parseInt(Nmain / 2))})`).className = "Main";
                    }
                    Start = [Lst.clientWidth, Fst.clientHeight];
                    Finish = [document.querySelector(".Main").clientWidth, document.querySelector(".Main").clientHeight];
                    Dmain();
                    document.querySelectorAll(".Main").forEach((e) => {
                        e.animate([
                            { width: `${Start[0]}px`, height: `${Start[1]}px`, opacity: `${OpacityS}` },
                            { width: `${Finish[0]}px`, height: `${Finish[1]}px`, opacity: `${OpacityM}`, easing: "ease-out" }
                        ], ATiming);
                    });
                    start = STi;
                    Wait = false;
                    if (Rep) {
                        RepA = setTimeout(() => {
                            if (!TO) {
                                repeat(RepTiming);
                            }
                        }, RepTiming);
                    }
                }
            };
        }

        btn.onmousedown = function () {
            clearTimeout(RepA);
            if (Wait) {
                console.log("RightButton Error : Please Wait");
            }
            else {
                btn.style.color = "#034C75";
                TO = true;
                Wait = true;
                Start = [document.querySelector("#caroussel>li:first-child").clientWidth, document.querySelector("#caroussel>li:first-child").clientHeight];
                Finish = [document.querySelector(".Main").clientWidth, document.querySelector(".Main").clientHeight];
                document.querySelectorAll(".Main").forEach((e) => {
                    e.animate([
                        { width: `${Finish[0]}px`, height: `${Finish[1]}px`, opacity: `${OpacityM}` },
                        { width: `${Start[0]}px`, height: `${Start[1]}px`, opacity: `${OpacityS}`, easing: "ease-out" }
                    ], ATiming)
                });
                document.querySelector(`#caroussel>li:nth-child(${Main})`).animate([
                    { width: `${Finish[0]}px`, height: `${Finish[1]}px`, opacity: `${OpacityM}` },
                    { width: `${Start[0]}px`, height: `${Start[1]}px`, opacity: `${OpacityS}`, easing: "ease-out" }
                ], ATiming).onfinish = () => {
                    document.querySelectorAll(".Main").forEach((e) => { e.className = " "; });
                    repeat();
                    Clicked = true;
                }
            };
            btn.onmouseup = function () {
                TO = false;
                btn.style.color = "#0CA789"
            }
            btn.onmouseleave = function () {
                if (Clicked) {
                    TO = false;
                    btn.style.color = "#0CA789"
                }
            }
        }
    }

    scrollLeft(BtLeft, 100);
    scrollRight(BtRight, 100);
}