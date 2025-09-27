let myForm = document.forms.toDoForm;
let myList = document.querySelector('.list-group');
let clear = document.querySelector('.clear-btn');

myForm.addEventListener('submit', function() {
    event.preventDefault();

    let title = myForm.title.value;
    if (title.trim()) {
        let li = document.createElement('li');
        li.innerHTML = `<span>${title}</span>  <div class="btns">
                    <i class="isDone">✔</i>
                    <i class="removeBtn">❌</i> </div>`;
        li.className = 'list-group-item';
        myList.append(li);
    }
    myForm.reset();
});

clear.addEventListener('click', function() {
    myList.innerHTML = '';
})

myList.addEventListener('click', function() {
    if (event.target.className == 'removeBtn') {
        event.target.parentElement.parentElement.remove();
    } else if (event.target.className == 'isDone') {
        if (event.target.parentElement.parentElement.classList.contains('done')) {
            event.target.parentElement.parentElement.classList.remove('done');
        } else {
            event.target.parentElement.parentElement.classList.add('done');
        }
    }
})



//  1) sortirovka
//  2) udalenie po vibrannomu elementu (mojno vibirat 1 element)
//  3) (+ , -) knopki dlya uvelicenie ili umenwenie texta vnutri li)




// let myForm = document.forms.toDoForm;
// let myList = document.querySelector('.list-group');
// let clear = document.querySelector('.clear-btn');

// myForm.addEventListener('submit', function() {
//     event.preventDefault();

//     let title = myForm.title.value;

//     if (title.trim()) {
//         let li = document.createElement('li');
//         li.innerText = title;
//         li.className = 'list-group-item';
//         myList.append(li);
//     }

//     myForm.reset();
// });

// clear.addEventListener('click', function() {
//     myList.innerHTML = '';
// })