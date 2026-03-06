/**
 * KVD Application - Dashboard (Главная страница)
 *
 * Загружает статистику с сервера и отображает:
 * 1) Карточки с числами (пациенты, случаи за месяц/год)
 * 2) Распределение по группам диагнозов (горизонтальные полоски)
 * 3) Таблица последних 10 добавленных случаев
 */

document.addEventListener('DOMContentLoaded', () => {
    loadDashboard();
});

async function loadDashboard() {
    try {
        const stats = await get('/dashboard/stats');
        renderStats(stats);
        renderDistribution(stats.diagnosisDistribution || []);
    } catch (error) {
        console.error('Ошибка загрузки дашборда:', error);
        showToast('Ошибка загрузки статистики', 'error');
    }
}

/**
 * Заполняет карточки со счётчиками.
 */
function renderStats(stats) {
    document.getElementById('statPatients').textContent = formatNumber(stats.totalPatients);
    document.getElementById('statCases').textContent = formatNumber(stats.totalCases);
    document.getElementById('statMonth').textContent = formatNumber(stats.casesThisMonth);
    document.getElementById('statYear').textContent = formatNumber(stats.casesThisYear);
}

/**
 * Рисует распределение по группам диагнозов.
 * Горизонтальные полоски с процентами — наглядно и без сторонних библиотек.
 */
function renderDistribution(data) {
    const container = document.getElementById('diagnosisDistribution');

    if (data.length === 0) {
        container.innerHTML = '<p style="color: #9ca3af; padding: 12px;">Нет данных за текущий год</p>';
        return;
    }

    // Находим максимум для масштабирования полосок
    const maxCount = Math.max(...data.map(d => d.count));

    container.innerHTML = '<div class="dist-chart">' +
        data.map(item => {
            const pct = maxCount > 0 ? Math.round((item.count / maxCount) * 100) : 0;
            return `
                <div class="dist-row">
                    <div class="dist-label">${escapeHtml(item.name)}</div>
                    <div class="dist-bar-wrap">
                        <div class="dist-bar" style="width: ${pct}%"></div>
                    </div>
                    <div class="dist-count">${item.count}</div>
                </div>
            `;
        }).join('') +
    '</div>';
}

/**
 * Форматирование числа с разделителями тысяч: 12345 → "12 345"
 */
function formatNumber(n) {
    if (n == null) return '—';
    return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ' ');
}
