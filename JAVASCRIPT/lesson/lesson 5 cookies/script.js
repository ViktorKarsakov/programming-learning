/* document.cookie = 'name=vitya';
document.cookie = 'lasname=karsakov';

let str = document.cookie;
console.log(str);
 */

let container = document.querySelector('.container');
let body = document.querySelector('box');
container.addEventListener('click', function () {
    if (event.target.classList.contains('box')){
    /* console.log("yes"); */
    let arr = event.target.className.split(' ')
    let color = arr[1];
    console.log(color);
    body.style.backgroundColor = color;
    document.cookie = 'backgroundColor' + color;
    }
})