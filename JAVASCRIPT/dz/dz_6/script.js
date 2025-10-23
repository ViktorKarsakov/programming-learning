const API_KEY = "ea5c4c75";
const OMDB_URL = "https://www.omdbapi.com/";
const form = document.getElementById("searchForm");
const titleInput = document.getElementById("titleInput");
const typeSelect = document.getElementById("typeSelect");
const resultsSec = document.getElementById("results");
const cardsEl = document.getElementById("cards");
const paginationEl = document.getElementById("pagination");
const detailsSec = document.getElementById("details");
const detailsContent = document.getElementById("detailsContent");
const cardTpl = document.getElementById("cardTpl");
const toast = document.getElementById("toast");

let state = {
  query: "",
  type: "",
  page: 1,
  totalPages: 0,
};

function showToast(msg, ms = 2200) {
  toast.textContent = msg;
  toast.hidden = false;
  clearTimeout(showToast._t);
  showToast._t = setTimeout(() => (toast.hidden = true), ms);
}
function placeholderPoster(w, h) {
  const svg = `<svg xmlns='http://www.w3.org/2000/svg' width='${w}' height='${h}'><rect width='100%' height='100%' fill='#ddd'/><text x='50%' y='50%' dominant-baseline='middle' text-anchor='middle' font-family='Arial' font-size='14' fill='#777'>No poster</text></svg>`;
  return "data:image/svg+xml;charset=utf-8," + encodeURIComponent(svg);
}

async function omdbSearch(query, type = "", page = 1) {
  const url = new URL(OMDB_URL);
  url.search = new URLSearchParams({
    apikey: API_KEY,
    s: query,
    type,
    page,
  }).toString();

  const res = await fetch(url);
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  const data = await res.json();
  return data;
}

async function omdbDetails(imdbID) {
  const url = new URL(OMDB_URL);
  url.search = new URLSearchParams({
    apikey: API_KEY,
    i: imdbID,
    plot: "full",
  }).toString();

  const res = await fetch(url);
  if (!res.ok) throw new Error(`HTTP ${res.status}`);
  return res.json();
}

function renderMovies(items) {
  cardsEl.innerHTML = "";
  const frag = document.createDocumentFragment();

  items.forEach((m) => {
    const node = cardTpl.content.firstElementChild.cloneNode(true);
    const poster = node.querySelector(".poster");
    const type = node.querySelector(".type");
    const title = node.querySelector(".title");
    const year = node.querySelector(".year");
    const btn = node.querySelector(".detailsBtn");

    poster.src = m.Poster && m.Poster !== "N/A" ? m.Poster : placeholderPoster(90, 130);
    poster.alt = `${m.Title} poster`;
    type.textContent = m.Type || "";
    title.textContent = m.Title || "";
    year.textContent = m.Year || "";

    btn.addEventListener("click", async () => {
      try {
        btn.disabled = true;
        const full = await omdbDetails(m.imdbID);
        renderDetails(full);
        window.scrollTo({ top: detailsSec.offsetTop - 12, behavior: "smooth" });
      } catch (e) {
        showToast("Failed to load details");
      } finally {
        btn.disabled = false;
      }
    });

    frag.appendChild(node);
  });

  cardsEl.appendChild(frag);
}

function renderPagination(totalPages, current) {
  paginationEl.innerHTML = "";
  if (totalPages <= 1) return;

  const makeBtn = (label, page, disabled = false, active = false) => {
    const b = document.createElement("button");
    b.textContent = label;
    if (disabled) b.disabled = true;
    if (active) b.classList.add("active");
    b.addEventListener("click", () => gotoPage(page));
    return b;
  };

  const start = Math.max(1, current - 3);
  const end = Math.min(totalPages, current + 3);

  paginationEl.append(
    makeBtn("<<", 1, current === 1),
    makeBtn("<", Math.max(1, current - 1), current === 1)
  );

  for (let p = start; p <= end; p++) {
    paginationEl.append(makeBtn(String(p), p, false, p === current));
  }

  paginationEl.append(
    makeBtn(">", Math.min(totalPages, current + 1), current === totalPages),
    makeBtn(">>", totalPages, current === totalPages)
  );
}

function renderDetails(d) {
  detailsSec.hidden = false;

  const poster = d.Poster && d.Poster !== "N/A" ? d.Poster : placeholderPoster(220, 320);
  detailsContent.innerHTML = `
    <img src="${poster}" alt="Poster"/>
    <dl>
      <dt>Title:</dt><dd>${d.Title || "-"}</dd>
      <dt>Released:</dt><dd>${d.Released || "-"}</dd>
      <dt>Genre:</dt><dd>${d.Genre || "-"}</dd>
      <dt>Country:</dt><dd>${d.Country || "-"}</dd>
      <dt>Director:</dt><dd>${d.Director || "-"}</dd>
      <dt>Writer:</dt><dd>${d.Writer || "-"}</dd>
      <dt>Actors:</dt><dd>${d.Actors || "-"}</dd>
      <dt>Awards:</dt><dd>${d.Awards || "-"}</dd>
      <dt>IMDB Rating:</dt><dd>${d.imdbRating || "-"}</dd>
      <dt>Plot:</dt><dd>${d.Plot || "-"}</dd>
    </dl>
  `;
}

async function performSearch(page = 1) {
  const q = state.query.trim();
  if (!q) {
    showToast("Enter a title");
    return;
  }

  resultsSec.hidden = false;
  detailsSec.hidden = true;
  cardsEl.innerHTML = "<p>Loading…</p>";
  paginationEl.innerHTML = "";

  try {
    const data = await omdbSearch(q, state.type, page);

    if (data.Response === "False") {
      cardsEl.innerHTML = `<p><strong>Movie not found!</strong></p>`;
      state.totalPages = 0;
      return;
    }

    const total = Number(data.totalResults) || 0;
    state.page = page;
    state.totalPages = Math.ceil(total / 10);

    renderMovies(data.Search);
    renderPagination(state.totalPages, state.page);
  } catch (e) {
    cardsEl.innerHTML = `<p>Request failed. Check API key / network.</p>`;
  }
}

function gotoPage(page) {
  if (page === state.page) return;
  if (page < 1 || page > state.totalPages) return;
  performSearch(page);
}

form.addEventListener("submit", (e) => {
  e.preventDefault();
  state.query = titleInput.value;
  state.type = typeSelect.value;
  performSearch(1);
});

titleInput.addEventListener("keydown", (e) => {
  if (e.key === "Enter" && !titleInput.value.trim()) e.preventDefault();
});
