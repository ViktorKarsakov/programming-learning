// console.log(document);

// document.children[0].children[1].children[0].textContent = "Farid";
// document.children[0].children[1].children[0].innerText = "Farid";
// document.children[0].children[1].children[0].innerHTML = "Farid";
// console.log(document.children[0].children[1].children[0]);


// document.getElementById();
// document.getElementsByTagName();
// document.getElementsByClassName();
// document.getElementsByName();
// document.querySelector();
// document.querySelectorAll();


//  document.getElementById();
// document.getElementsByTagName('h1')[0].innerText = "Farid";
// let h1s = document.getElementsByTagName('h1');

// for (const h1 of h1s) {
//     h1.innerText = 'Farid';
// }


// let h1 = document.getElementsByTagName('h1')[1]; 
// h1.innerText = 'Farid';

// let h1s = document.getElementsByClassName('test-text');
// for (const h1 of h1s) {
//     h1.innerText = 'Farid';
// }


// let h1 = document.getElementById('farid');
// h1.innerText = 'Farid';


// let h1s = document.querySelectorAll('h1');
// for (const h1 of h1s) {
//     h1.innerText = 'Farid';
// }


// let h1 = document.querySelector('#farid');
// h1.innerText = 'Farid';


// > + ~ . # div p

// let h1s = document.querySelectorAll('.test-text');
// for (const h1 of h1s) {
//     h1.innerText = 'Farid';
// }


// let h1s = document.querySelectorAll('h1');
// let count = 1;
// for (const h1 of h1s) {
//     h1.innerText = 'Farid ' + count++;
// }


// let h1s = document.querySelectorAll('h1');
// let count = 1;
// for (const h1 of h1s) {
//     h1.innerText = h1.innerText + " " + count++;
// }


// let h1s = document.querySelectorAll('h1');
// let count = 1;
// for (const h1 of h1s) {
//     // h1.innerText = '<button>Farid</button>';
//     // h1.textContent = '<button>Farid</button>';
//     h1.innerHTML = '<del>Farid</del>';
//     // h1.innerHTML = 'Farid';
//     // h1.textContent = 'Farid';
// }



// let element = document.querySelector('h1');
// element.classList.add('test');



// element.style = 'color:red;font-size:150px;margin:100px';
// element.style.color = 'red';
// element.style.fontSize = '100px';
// element.style.margin = '100px';
// element.style.setProperty('color', 'red', 'important');
// element.style.setProperty('font-size', '150px');
// element.style = `  display: flex;
//     justify-content: center;
//     align-items: center;
//     font-size: 50px;
//     margin: 5px;
//     border: 5px solid black;`;




let box = {
        element: document.querySelector('.box'),
        top: 0,
        left: 0,
        bottom: 0,
    }
    //  box.element.remove();
let isTop = false;
let isBotton = false;
setInterval(() => {
    console.log("Farid");

    if (box.top < window.innerHeight - 125 && isBotton == false) {
        box.top += 10;
        isTop = true;
    } else if (box.left < window.innerWidth - 130) {

        box.left += 10;
    } else if (box.top > 0 && isTop == true) {
        box.top -= 10;
        isBotton = true;
    }

    box.element.style.top = box.top + "px";
    box.element.style.left = box.left + "px";
    box.element.style.bottom = box.bottom + "px";
}, 75);


// window.outerHeight
// window.outerWidth
// window.innerHeight
// window.innerWidth