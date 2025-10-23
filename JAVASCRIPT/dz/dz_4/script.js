let myForm = document.forms.toDoForm;
let myList = document.querySelector('.list-group');
let clear = document.querySelector('.clear-btn');

function getAllItems() {
  return Array.from(myList.querySelectorAll('.list-group-item'));
}
function getItemTitle(li) {
  return li.querySelector('span')?.textContent?.trim() ?? '';
}
function selectOnly(li) {
  getAllItems().forEach(item => item.classList.remove('selected'));
  if (li) li.classList.add('selected');
}
function getSelected() {
  return myList.querySelector('.list-group-item.selected');
}
function adjustFont(li, deltaPx) {
  const span = li.querySelector('span');
  const current = parseFloat(getComputedStyle(span).fontSize);
  const next = Math.max(12, Math.min(48, current + deltaPx));
  span.style.fontSize = next + 'px';
}
function adjustFontAllOrSelected(deltaPx) {
  const sel = getSelected();
  if (sel) {
    adjustFont(sel, deltaPx);
  } else {
    getAllItems().forEach(li => adjustFont(li, deltaPx));
  }
}
function sortList(dir = 'asc') {
  const items = getAllItems();
  items.sort((a, b) => {
    const ta = getItemTitle(a).toLowerCase();
    const tb = getItemTitle(b).toLowerCase();
    if (ta < tb) return dir === 'asc' ? -1 : 1;
    if (ta > tb) return dir === 'asc' ? 1 : -1;
    return 0;
  });

  const frag = document.createDocumentFragment();
  items.forEach(li => frag.appendChild(li));
  myList.appendChild(frag);
}

myForm.addEventListener('submit', function (event) {
  event.preventDefault();
  const title = myForm.title.value;

  if (title.trim()) {
    const li = document.createElement('li');
    li.className = 'list-group-item';
    li.innerHTML = `
      <span>${title}</span>
      <div class="btns">
        <i class="isDone">✔</i>
        <i class="removeBtn">❌</i>
      </div>
    `;
    myList.append(li);
  }
  myForm.reset();
});

clear.addEventListener('click', function () {
  myList.innerHTML = '';
});

myList.addEventListener('click', function (event) {
  const target = event.target;

  if (target.className === 'removeBtn') {
    target.closest('.list-group-item')?.remove();
    return;
  }

  if (target.className === 'isDone') {
    const li = target.closest('.list-group-item');
    li.classList.toggle('done');
    return;
  }

  const li = target.closest('.list-group-item');
  if (li) {
    if (target.classList.contains('isDone') || target.classList.contains('removeBtn')) return;

    if (li.classList.contains('selected')) {
      li.classList.remove('selected');
    } else {
      selectOnly(li);
    }
  }
});

const options = document.querySelector('.options');

options.addEventListener('click', function (event) {
  const btn = event.target.closest('button');
  if (!btn) return;

  if (btn.classList.contains('font-inc')) {
    adjustFontAllOrSelected(+2);
  } else if (btn.classList.contains('font-dec')) {
    adjustFontAllOrSelected(-2);
  } else if (btn.classList.contains('sort-asc')) {
    sortList('asc');
  } else if (btn.classList.contains('sort-desc')) {
    sortList('desc');
  } else if (btn.classList.contains('delete-selected')) {
    const sel = getSelected();
    if (sel) sel.remove();
  }
});
