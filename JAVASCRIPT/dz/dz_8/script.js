const input = document.getElementById('cityInput');
const list = document.getElementById('historyList');
const btn = document.getElementById('searchBtn');

function getHistory() {
  return JSON.parse(localStorage.getItem('cities') || '[]');
}

function saveHistory(arr) {
  localStorage.setItem('cities', JSON.stringify(arr));
}

function addCity(name) {
  let cities = getHistory();
  if (!cities.includes(name)) {
    cities.push(name);
    saveHistory(cities);
  }
}

function showHistory() {
  const cities = getHistory();
  if (cities.length === 0) {
    list.style.display = 'none';
    return;
  }
  list.innerHTML = cities.map(c => `<div>${c}</div>`).join('');
  list.style.display = 'block';
}

list.addEventListener('click', (e) => {
  if (e.target.tagName === 'DIV') {
    input.value = e.target.textContent;
    list.style.display = 'none';
  }
});

input.addEventListener('focus', showHistory);

btn.addEventListener('click', () => {
  const city = input.value.trim();
  if (city) {
    addCity(city);
    alert('Вы выбрали: ' + city);
    input.value = '';
    list.style.display = 'none';
  }
});
