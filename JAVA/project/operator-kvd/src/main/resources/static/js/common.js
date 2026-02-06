/**
 * KVD Application - Common Utilities
 */

const API_BASE = '/api';

// ==================== HTTP UTILS ====================

async function fetchApi(endpoint, options = {}) {
    const defaultOptions = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const response = await fetch(`${API_BASE}${endpoint}`, {
        ...defaultOptions,
        ...options,
    });

    if (!response.ok) {
        const error = await response.json().catch(() => ({ message: 'Ошибка сервера' }));
        throw new Error(error.message || `HTTP ${response.status}`);
    }

    // Для DELETE запросов нет тела ответа
    if (response.status === 204) {
        return null;
    }

    return response.json();
}

async function get(endpoint) {
    return fetchApi(endpoint);
}

async function post(endpoint, data) {
    return fetchApi(endpoint, {
        method: 'POST',
        body: JSON.stringify(data),
    });
}

async function put(endpoint, data) {
    return fetchApi(endpoint, {
        method: 'PUT',
        body: JSON.stringify(data),
    });
}

async function del(endpoint) {
    return fetchApi(endpoint, {
        method: 'DELETE',
    });
}

// ==================== TOAST NOTIFICATIONS ====================

function showToast(message, type = 'info') {
    let container = document.querySelector('.toast-container');
    if (!container) {
        container = document.createElement('div');
        container.className = 'toast-container';
        document.body.appendChild(container);
    }

    const icons = {
        success: '✓',
        error: '✕',
        info: 'ℹ',
    };

    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerHTML = `<span>${icons[type] || ''}</span> ${message}`;
    container.appendChild(toast);

    setTimeout(() => {
        toast.style.animation = 'toastIn 0.3s ease reverse';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// ==================== MODAL UTILS ====================

function openModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.add('open');
    }
}

function closeModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
        modal.classList.remove('open');
    }
}

// Закрытие модала по клику на overlay
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('modal-overlay')) {
        e.target.classList.remove('open');
    }
});

// Закрытие модала по Escape
document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape') {
        document.querySelectorAll('.modal-overlay.open').forEach(modal => {
            modal.classList.remove('open');
        });
    }
});

// ==================== FORM UTILS ====================

function getFormData(formId) {
    const form = document.getElementById(formId);
    const formData = new FormData(form);
    const data = {};

    for (const [key, value] of formData.entries()) {
        if (value !== '') {
            data[key] = value;
        }
    }

    return data;
}

function setFormData(formId, data) {
    const form = document.getElementById(formId);
    
    Object.keys(data).forEach(key => {
        const input = form.querySelector(`[name="${key}"]`);
        if (input) {
            input.value = data[key] ?? '';
        }
    });
}

function clearForm(formId) {
    const form = document.getElementById(formId);
    if (form) {
        form.reset();
    }
}

// ==================== SELECT UTILS ====================

async function loadSelectOptions(selectId, endpoint, displayField = 'name', valueField = 'id', emptyLabel = 'Выберите...') {
    const select = document.getElementById(selectId);
    if (!select) return;

    try {
        const data = await get(endpoint);
        
        // Сохраняем текущее значение
        const currentValue = select.value;
        
        // Очищаем и добавляем пустой option
        select.innerHTML = `<option value="">${emptyLabel}</option>`;
        
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item[valueField];
            
            // Для вложенных объектов (например, doctor.department.name)
            if (displayField.includes('.')) {
                const fields = displayField.split('.');
                let value = item;
                for (const field of fields) {
                    value = value?.[field];
                }
                option.textContent = value || '';
            } else {
                option.textContent = item[displayField] || '';
            }
            
            select.appendChild(option);
        });
        
        // Восстанавливаем значение
        if (currentValue) {
            select.value = currentValue;
        }
    } catch (error) {
        console.error(`Error loading options for ${selectId}:`, error);
    }
}

// ==================== DATE UTILS ====================

function formatDate(dateString) {
    if (!dateString) return '';
    const date = new Date(dateString);
    return date.toLocaleDateString('ru-RU');
}

function formatDateTime(dateTimeString) {
    if (!dateTimeString) return '';
    const date = new Date(dateTimeString);
    return date.toLocaleString('ru-RU');
}

function toISODate(dateString) {
    if (!dateString) return null;
    const date = new Date(dateString);
    return date.toISOString().split('T')[0];
}

// ==================== TABLE UTILS ====================

function selectRow(row, tableId) {
    // Убираем выделение с других строк
    document.querySelectorAll(`#${tableId} tbody tr`).forEach(tr => {
        tr.classList.remove('selected');
    });
    
    // Выделяем текущую
    row.classList.add('selected');
}

function getSelectedRowId(tableId) {
    const selected = document.querySelector(`#${tableId} tbody tr.selected`);
    return selected ? selected.dataset.id : null;
}

function clearTableSelection(tableId) {
    document.querySelectorAll(`#${tableId} tbody tr`).forEach(tr => {
        tr.classList.remove('selected');
    });
}

// ==================== VALIDATION ====================

function validateRequired(value, fieldName) {
    if (!value || value.toString().trim() === '') {
        throw new Error(`Поле "${fieldName}" обязательно для заполнения`);
    }
}

function validateForm(rules) {
    for (const [fieldName, { value, label }] of Object.entries(rules)) {
        validateRequired(value, label);
    }
}

// ==================== SIDEBAR ====================

function toggleSubmenu(element) {
    element.classList.toggle('collapsed');
    const submenu = element.nextElementSibling;
    if (submenu && submenu.classList.contains('submenu')) {
        submenu.classList.toggle('collapsed');
    }
}

// Подсветка активного пункта меню
function setActiveMenuItem(page) {
    document.querySelectorAll('.sidebar-nav a').forEach(a => {
        a.classList.remove('active');
    });
    
    const activeLink = document.querySelector(`.sidebar-nav a[href="${page}"]`);
    if (activeLink) {
        activeLink.classList.add('active');
        
        // Если это подменю, раскрываем его
        const submenu = activeLink.closest('.submenu');
        if (submenu) {
            submenu.classList.remove('collapsed');
            const toggle = submenu.previousElementSibling;
            if (toggle) {
                toggle.classList.remove('collapsed');
            }
        }
    }
}

// ==================== CONFIRM DIALOG ====================

function confirmAction(message) {
    return confirm(message);
}

// ==================== LOADING STATE ====================

function showLoading(containerId) {
    const container = document.getElementById(containerId);
    if (container) {
        container.innerHTML = `
            <div class="loading">
                <div class="spinner"></div>
            </div>
        `;
    }
}

function showEmptyState(containerId, message = 'Нет данных') {
    const container = document.getElementById(containerId);
    if (container) {
        container.innerHTML = `
            <div class="empty-state">
                <div class="empty-state-icon">📋</div>
                <p>${message}</p>
            </div>
        `;
    }
}
