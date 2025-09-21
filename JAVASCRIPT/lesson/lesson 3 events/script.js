function test() {
    console.log("Hello world!");
}


// let h1 = document.querySelector("h1");
// h1.onclick = test;


// let h1 = document.querySelector("h1");
// h1.onclick = () => {
//     console.log("Hello world!");
// };


// let h1 = document.querySelector("h1");
// h1.addEventListener('click', test)



// let h1 = document.querySelector("h1");
// h1.addEventListener('click', () => {
//     console.log("Hello world!");
// })

// let h1 = document.querySelector("h1");
// h1.addEventListener('click', function() {
//     console.log("Hello world!");
// })

// let i = 1;

// let box = document.querySelector('.box');
// box.addEventListener('click', function() {
// box.addEventListener('dblclick', function() {
// box.addEventListener('mousedown', function() {
// box.addEventListener('mouseup', function() {
// box.addEventListener('mousemove', function() {
// box.addEventListener('mouseout', function() {
// box.addEventListener('mouseover', function() {
// box.addEventListener('contextmenu', function() {
//     console.log("Hello : " + i++);

// });




// box.addEventListener('mouseover', function() {
//     console.log("Welcome : " + i++);

// });


// box.addEventListener('mouseout', function() {
//     console.log("Bye Bye : " + i++);

// });

// let i = 2;

// let box = document.querySelector('.box');
// box.addEventListener('click', function() {
//     if (i == 5) {
//         box.remove();
//     }
//     box.innerText = i++;
// })




// let boxes = document.querySelectorAll('.box');

// for (const box of boxes) {
//     let i = 2;
//     box.addEventListener('click', function() {
//         if (i == 5) {
//             box.remove();
//         }
//         box.innerText = i++;
//     })
// }

// function random(min, max) {
//     return Math.floor(Math.random() * (max - min + 1) + min);
// }

// let boxes = document.querySelectorAll('.box');
// const removeCount = 11;

// for (const box of boxes) {
//     box.addEventListener('click', function() {
//         console.log(10);

//         let i = box.innerText;
//         i++;
//         box.innerText = i;
//         box.style.backgroundColor = `rgb(${random(0,255)},${random(0,255)},${random(0,255)})`
//         if (i == removeCount) {
//             box.remove();
//         }
//     })
// }



// let btn = document.querySelector('button');
// let container = document.querySelector('.container');
// btn.addEventListener('click', () => {
//     let box = document.createElement('div');
//     box.innerText = container.children.length + 1;
//     box.className = 'box';
//     box.onclick = () => {
//         let i = box.innerText;
//         i++;
//         box.innerText = i;
//         box.style.backgroundColor = `rgb(${random(0,255)},${random(0,255)},${random(0,255)})`
//         if (i == removeCount) {
//             box.remove();
//         }
//     }
//     container.appendChild(box);
//     // container.innerHTML += `<div class="box">1</div>`
// });




// function random(min, max) {
//     return Math.floor(Math.random() * (max - min + 1) + min);
// }

// let container = document.querySelector('.container');
// const removeCount = 11;


// container.addEventListener('click', function() {
//     // console.log(event.target.tagName);
//     console.log(event.target.className = 'box');

//     // if (event.target.tagName == 'DIV') {
//     // if (event.target.className == 'box') {
//     if (event.target.classList.contains('box')) {
//         console.log("Da");

//         let i = event.target.innerText;
//         i++;
//         event.target.innerText = i;
//         event.target.style.backgroundColor = `rgb(${random(0,255)},${random(0,255)},${random(0,255)})`
//         if (i == removeCount) {
//             event.target.remove();
//         }
//     } else {
//         console.log("Net");

//     }

//     // console.log(event.type);
//     console.log(event.target);
//     // console.log(event.currentTarget);
//     // console.log(event.clientX);
//     // console.log(event.clientY);


// })



// let btn = document.querySelector('button');
// btn.addEventListener('click', () => {
//     container.innerHTML += `<div class="box">1</div>`
// });



// let btn = document.querySelector('button');
// let h1 = document.querySelector('h1');
// let isHidden = true;

// btn.addEventListener('click', () => {
//     if (isHidden) {
//         h1.style.display = 'none'
//     } else {
//         h1.style.display = 'block'
//     }

//     isHidden = !isHidden;
// });



let parent = document.querySelector('.parent');
let child = document.querySelector('.child');


// parent.addEventListener('click', () => {
//     console.log('parent');
//     console.log("target        : ");
//     console.log(event.target);
//     console.log("currentTarget : ");
//     console.log(event.currentTarget);
//     console.log("--------------------------------------");
//     console.log("--------------------------------------");

// })
// child.addEventListener('click', () => {
//     console.log('child');
//     console.log("target        : ");
//     console.log(event.target);
//     console.log("currentTarget : ");
//     console.log(event.currentTarget);
//     console.log("--------------------------------------");
//     console.log("--------------------------------------");
//     event.stopPropagation();
// })


// 10:15