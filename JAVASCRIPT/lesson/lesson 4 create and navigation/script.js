// let btn = document.querySelector('a');
// let img = document.querySelector('img');


// btn.addEventListener('click', function() {
//     console.log('100');
//     img.src = './car.jpg';
// });


// let btns = document.querySelectorAll('a');
// let imgs = document.querySelectorAll('img');


// for (let i = 0; i < btns.length; i++) {
//     btns[i].addEventListener('click', function() {
//         console.log('100');
//         imgs[i].src = './car.jpg';
//     });
// }



// let list = document.querySelector('.list');
// let imgs = document.querySelectorAll('img');


// parentElement               -    Roditel
// children                    -    deti
// lastElementChild            -    posledniy element
// firstElementChild           -    perviy element
// previousElementSibiling     -    sosed roditelya vise
// nextElementSibiling         -    sosed roditelya nije


// list.addEventListener('click', function() {
//     if (event.target.tagName == 'A' && event.target.classList.contains('btn-primary')) {
//         // event.target.parentElement.parentElement.firstElementChild.src = './car.jpg';
//         event.target.parentElement.previousElementSibling.src = './car.jpg';
//     }
// })

//  <div class="col-4">
//      <div class="card">
//          <img src="./2.jpg" class="card-img-top" alt="...">
//          <div class="card-body">
//              <h5 class="card-title">Card title</h5>
//              <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card’s content.</p>
//              <a href="#" class="btn btn-primary">Change</a>
//          </div>
//      </div>
//  </div>





// for (let i = 0; i < btns.length; i++) {
//     btns[i].addEventListener('click', function() {
//         console.log('100');
//         imgs[i].src = './car.jpg';
//     });
// }


// let h1 = document.querySelector('h1');
// h1.addEventListener('click', function() {
//     h1.outerHTML = '<mark>Hello</mark>'
// })


// let btn = document.querySelector('.add-btn');
// let myText = document.querySelector('.my-text');
// let list = document.querySelector('.list');


// btn.addEventListener('click', function() {
//     // let li = document.createElement('li');
//     // li.innerText = myText.value;
//     // li.classList.add('list-group-item');
//     // list.append(li);
//     // list.appendChild(li);
//     // list.prepend(li);
//     // list.after(li);
//     // list.before(li);


//     list.insertAdjacentHTML('afterend', ` <li class="list-group-item">${myText.value}</li>`)

//     // list.innerHTML = ` <li class="list-group-item">${myText.value}</li>`;

//     myText.value = '';
// })


let btn = document.querySelector('.add-btn');
let myText = document.querySelector('.my-text');
let list = document.querySelector('.list');
btn.addEventListener('click', function() {
    list.insertAdjacentHTML('beforeend', ` <li class="list-group-item">${myText.value}</li>`)
    myText.value = '';
})

// 9:37
myText.addEventListener('keydown', function() {

    for (let i = 0; i < 10; i++) {
        if (event.key == i) {
            event.preventDefault();
        }
    }
    // if (event.key == 5) {
    //     event.preventDefault();
    // }
    // console.log(event.code);
    console.log(event.key);
})