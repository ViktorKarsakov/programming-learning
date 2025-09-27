
let box = {
        element: document.querySelector('.box'),
        top: 0,
        left: 0,
        bottom: 0,
        right: 0,
    }
   
let isTop = false;
let isBottom = false;
let isRight = false;
let isLeft = false;
let count = 1;
    
let interval = setInterval(() => {
    
    if (box.top < window.innerHeight - 120 && isBottom == false) {
        box.top += 10;
        isTop = true;
    } else if (box.left < window.innerWidth - 120 && isRight == false) {
        box.left += 10;
        isLeft = true;
    } else if (box.top > 0 && isTop == true) {
        box.top -= 10;
        isBottom = true;
    } else if (box.left > 0 && isLeft == true){
        box.left -= 10;
        isRight = true;
        
        if (box.left <= 0) {
            isTop = false;
            isBottom = false;
            isRight = false;
            isLeft = false;
            count++;
            box.element.innerText = count;
            
        }
        if(count >= 4){
            box.element.remove();
            clearInterval(interval);
        }
    }
    

    box.element.style.top = box.top + "px";
    box.element.style.left = box.left + "px";
}, 10);

