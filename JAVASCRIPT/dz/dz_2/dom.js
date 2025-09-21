
let box = {
        element: document.querySelector('.box'),
        top: 0,
        left: 0,
        bottom: 0,
        right: 0,
    }
    //  box.element.remove();
let isTop = false;
let isBotton = false;
let isRight = false;
setInterval(() => {
    console.log("Farid");

    if (box.top < window.innerHeight - 125 /* && isBotton == false */) {
        box.top += 10;
       /*  isTop = true; */
    } else if (box.left < window.innerWidth - 130) {

        box.left += 10;
    } else if (box.top > 0 /* && isTop == true */) {
        box.top -= 10;
        /* isBotton = true; */
    } else if (box.left > 0){
        box.right -= 10;
    }

    box.element.style.top = box.top + "px";
    box.element.style.left = box.left + "px";
    box.element.style.bottom = box.bottom + "px";
    box.element.style.bottom = box.right + "px";
}, 10);
