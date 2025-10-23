let myForm = document.forms.toDoForm;
let myList = document.querySelector('.list-group');
let clear = document.querySelector('.clear-btn');
const options = document.querySelector('.options');

const STORAGE_KEY = 'todo:list:v1';

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
  persist();
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
  persist();
}

function createItemElement(title) {
  const li = document.createElement('li');
  li.className = 'list-group-item';

  const span = document.createElement('span');
  span.textContent = title;

  const btns = document.createElement('div');
  btns.className = 'btns';

  const done = document.createElement('i');
  done.className = 'isDone';
  done.textContent = '✔';

  const del = document.createElement('i');
  del.className = 'removeBtn';
  del.textContent = '❌';

  btns.append(done, del);
  li.append(span, btns);
  return li;
}

function serializeList() {
  return getAllItems().map(li => {
    const span = li.querySelector('span');
    const font = parseFloat(getComputedStyle(span).fontSize);
    return {
      title: getItemTitle(li),
      done: li.classList.contains('done'),
      fontSize: isFinite(font) ? font : null
    };
  });
}

function persist() {
  const data = serializeList();
  try {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(data));
  } catch (e) {
    console.warn('Не удалось сохранить список в localStorage:', e);
  }
}

function restore() {
  let raw = localStorage.getItem(STORAGE_KEY);
  if (!raw) return;

  try {
    const data = JSON.parse(raw);
    if (!Array.isArray(data)) return;

    myList.innerHTML = '';
    data.forEach(({ title, done, fontSize }) => {
      const li = createItemElement(title);
      if (done) li.classList.add('done');
      if (fontSize) li.querySelector('span').style.fontSize = fontSize + 'px';
      myList.append(li);
    });
  } catch (e) {
    console.warn('Ошибка чтения данных из localStorage:', e);
  }
}

myForm.addEventListener('submit', function (event) {
  event.preventDefault();
  const title = myForm.title.value;

  if (title.trim()) {
    const li = createItemElement(title.trim());
    myList.append(li);
    persist();
  }
  myForm.reset();
});

clear.addEventListener('click', function () {
  myList.innerHTML = '';
  persist();
});

myList.addEventListener('click', function (event) {
  const target = event.target;

  if (target.className === 'removeBtn') {
    target.closest('.list-group-item')?.remove();
    persist();
    return;
  }

  if (target.className === 'isDone') {
    const li = target.closest('.list-group-item');
    li.classList.toggle('done');
    persist();
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
    persist();
  }
});

restore();
