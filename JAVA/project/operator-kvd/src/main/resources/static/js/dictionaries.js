/**
 * KVD Application - Dictionaries Management
 */

let currentDictionary = null;
let currentDictionaryTitle = '';
let currentData = [];
let selectedId = null;
let editMode = false;

// Конфигурация справочников
const dictionaryConfig = {
    'genders': {
        title: 'Пол',
        endpoint: '/dictionaries/genders',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'diagnosis-groups': {
        title: 'Группы диагнозов',
        endpoint: '/dictionaries/diagnosis-groups',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'diagnoses': {
        title: 'Диагнозы',
        endpoint: '/dictionaries/diagnoses',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true },
            { name: 'diagnosisGroup.id', label: 'Группа', type: 'select', source: '/dictionaries/diagnosis-groups', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' },
            { key: 'diagnosisGroup.name', label: 'Группа' }
        ]
    },
    'branches': {
        title: 'Филиалы',
        endpoint: '/dictionaries/branches',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'departments': {
        title: 'Отделения',
        endpoint: '/dictionaries/departments',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true },
            { name: 'branch.id', label: 'Филиал', type: 'select', source: '/dictionaries/branches', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' },
            { key: 'branch.name', label: 'Филиал' }
        ]
    },
    'doctors': {
        title: 'Врачи',
        endpoint: '/dictionaries/doctors',
        fields: [
            { name: 'lastName', label: 'Фамилия', type: 'text', required: true },
            { name: 'firstName', label: 'Имя', type: 'text', required: true },
            { name: 'middleName', label: 'Отчество', type: 'text', required: false },
            { name: 'department.id', label: 'Отделение', type: 'select', source: '/dictionaries/departments', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'lastName', label: 'Фамилия' },
            { key: 'firstName', label: 'Имя' },
            { key: 'middleName', label: 'Отчество' },
            { key: 'department.name', label: 'Отделение' }
        ]
    },
    'state-groups': {
        title: 'Группы районов',
        endpoint: '/dictionaries/state-groups',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'states': {
        title: 'Районы',
        endpoint: '/dictionaries/states',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true },
            { name: 'stateGroup.id', label: 'Группа', type: 'select', source: '/dictionaries/state-groups', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' },
            { key: 'stateGroup.name', label: 'Группа' }
        ]
    },
    'places': {
        title: 'Места выявления',
        endpoint: '/dictionaries/places',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'profiles': {
        title: 'Профили',
        endpoint: '/dictionaries/profiles',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'inspections': {
        title: 'Типы осмотра',
        endpoint: '/dictionaries/inspections',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'transfers': {
        title: 'Пути передачи',
        endpoint: '/dictionaries/transfers',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'citizen-categories': {
        title: 'Категории проживания',
        endpoint: '/dictionaries/citizen-categories',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'citizen-types': {
        title: 'Типы населённых пунктов',
        endpoint: '/dictionaries/citizen-types',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'social-groups': {
        title: 'Социальные группы',
        endpoint: '/dictionaries/social-groups',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    },
    'lab-test-types': {
        title: 'Лабораторные тесты',
        endpoint: '/dictionaries/lab-test-types',
        fields: [
            { name: 'name', label: 'Название', type: 'text', required: true }
        ],
        columns: [
            { key: 'id', label: '#', width: '60px' },
            { key: 'name', label: 'Название' }
        ]
    }
};

// Загрузка инициируется из HTML через URL параметр

async function loadDictionary(dictKey) {
    const config = dictionaryConfig[dictKey];
    if (!config) {
        showToast('Справочник не найден', 'error');
        return;
    }
    
    currentDictionary = dictKey;
    currentDictionaryTitle = config.title;
    selectedId = null;
    
    // Обновляем заголовок
    document.getElementById('pageTitle').textContent = `Справочник: ${config.title}`;
    
    // Обновляем активный пункт меню
    document.querySelectorAll('.submenu a').forEach(a => {
        a.classList.remove('active');
        if (a.dataset.dict === dictKey) {
            a.classList.add('active');
        }
    });
    
    // Строим заголовки таблицы
    renderTableHeaders(config.columns);
    
    // Загружаем данные
    try {
        showLoading('tableBody');
        currentData = await get(config.endpoint);
        renderTableData(config.columns);
    } catch (error) {
        showToast('Ошибка загрузки данных', 'error');
        console.error(error);
    }
    
    updateButtons();
}

function renderTableHeaders(columns) {
    const thead = document.getElementById('tableHead');
    thead.innerHTML = `
        <tr>
            ${columns.map(col => `<th style="${col.width ? 'width:' + col.width : ''}">${col.label}</th>`).join('')}
        </tr>
    `;
}

function renderTableData(columns) {
    const tbody = document.getElementById('tableBody');
    
    if (!currentData || currentData.length === 0) {
        tbody.innerHTML = `
            <tr>
                <td colspan="${columns.length}">
                    <div class="empty-state">
                        <div class="empty-state-icon">📋</div>
                        <p>Нет данных. Нажмите "Добавить" для создания записи.</p>
                    </div>
                </td>
            </tr>
        `;
        return;
    }
    
    tbody.innerHTML = currentData.map(item => `
        <tr data-id="${item.id}" onclick="selectDictRow(this)" ondblclick="openEditModal()">
            ${columns.map(col => `<td>${getNestedValue(item, col.key) || ''}</td>`).join('')}
        </tr>
    `).join('');
}

function getNestedValue(obj, path) {
    return path.split('.').reduce((current, key) => current?.[key], obj);
}

function selectDictRow(row) {
    document.querySelectorAll('#tableBody tr').forEach(tr => {
        tr.classList.remove('selected');
    });
    
    row.classList.add('selected');
    selectedId = parseInt(row.dataset.id);
    
    updateButtons();
}

function updateButtons() {
    document.getElementById('btnEdit').disabled = !selectedId;
    document.getElementById('btnDelete').disabled = !selectedId;
}

async function openAddModal() {
    editMode = false;
    selectedId = null;
    
    document.getElementById('modalTitle').textContent = 'Добавить запись';
    
    await generateModalForm();
    
    openModal('dictModal');
}

async function openEditModal() {
    if (!selectedId) {
        showToast('Выберите запись', 'info');
        return;
    }
    
    editMode = true;
    
    document.getElementById('modalTitle').textContent = 'Редактировать запись';
    
    await generateModalForm();
    
    // Заполняем форму текущими данными
    const item = currentData.find(d => d.id === selectedId);
    if (item) {
        fillFormWithData(item);
    }
    
    openModal('dictModal');
}

async function generateModalForm() {
    const config = dictionaryConfig[currentDictionary];
    const formBody = document.getElementById('modalFormBody');
    
    let html = '';
    
    for (const field of config.fields) {
        html += `<div class="form-group">`;
        html += `<label class="${field.required ? 'required' : ''}">${field.label}</label>`;
        
        if (field.type === 'select') {
            html += `<select id="field_${field.name.replace('.', '_')}" name="${field.name}" ${field.required ? 'required' : ''}>
                <option value="">Выберите...</option>
            </select>`;
        } else {
            html += `<input type="text" id="field_${field.name.replace('.', '_')}" name="${field.name}" ${field.required ? 'required' : ''}>`;
        }
        
        html += `</div>`;
    }
    
    formBody.innerHTML = html;
    
    // Загружаем опции для select полей
    for (const field of config.fields) {
        if (field.type === 'select' && field.source) {
            await loadSelectOptionsForField(field);
        }
    }
}

async function loadSelectOptionsForField(field) {
    const selectId = `field_${field.name.replace('.', '_')}`;
    const select = document.getElementById(selectId);
    
    if (!select) return;
    
    try {
        const data = await get(field.source);
        
        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item.id;
            option.textContent = item.name || `${item.lastName} ${item.firstName}`;
            select.appendChild(option);
        });
    } catch (error) {
        console.error(`Error loading options for ${field.name}:`, error);
    }
}

function fillFormWithData(item) {
    const config = dictionaryConfig[currentDictionary];
    
    for (const field of config.fields) {
        const inputId = `field_${field.name.replace('.', '_')}`;
        const input = document.getElementById(inputId);
        
        if (!input) continue;
        
        if (field.name.includes('.')) {
            // Вложенное поле, например diagnosisGroup.id
            const parts = field.name.split('.');
            const value = item[parts[0]]?.[parts[1]];
            input.value = value ?? '';
        } else {
            input.value = item[field.name] ?? '';
        }
    }
}

async function saveDictRecord() {
    const config = dictionaryConfig[currentDictionary];
    const data = {};
    
    // Собираем данные из формы
    for (const field of config.fields) {
        const inputId = `field_${field.name.replace('.', '_')}`;
        const input = document.getElementById(inputId);
        
        if (!input) continue;
        
        const value = input.value.trim();
        
        // Валидация обязательных полей
        if (field.required && !value) {
            showToast(`Заполните поле "${field.label}"`, 'error');
            input.focus();
            return;
        }
        
        if (field.name.includes('.')) {
            // Вложенное поле - создаём объект
            const parts = field.name.split('.');
            if (!data[parts[0]]) {
                data[parts[0]] = {};
            }
            data[parts[0]][parts[1]] = value ? parseInt(value) : null;
        } else {
            data[field.name] = value || null;
        }
    }
    
    try {
        if (editMode && selectedId) {
            await put(`${config.endpoint}/${selectedId}`, data);
            showToast('Запись обновлена', 'success');
        } else {
            await post(config.endpoint, data);
            showToast('Запись добавлена', 'success');
        }
        
        closeModal('dictModal');
        await loadDictionary(currentDictionary);
        
    } catch (error) {
        showToast('Ошибка сохранения: ' + error.message, 'error');
        console.error(error);
    }
}

async function deleteSelected() {
    if (!selectedId) {
        showToast('Выберите запись', 'info');
        return;
    }
    
    if (!confirmAction('Вы уверены, что хотите удалить эту запись?')) {
        return;
    }
    
    const config = dictionaryConfig[currentDictionary];
    
    try {
        await del(`${config.endpoint}/${selectedId}`);
        showToast('Запись удалена', 'success');
        selectedId = null;
        await loadDictionary(currentDictionary);
        
    } catch (error) {
        showToast('Ошибка удаления. Возможно, запись используется.', 'error');
        console.error(error);
    }
}

function closeDictModal() {
    closeModal('dictModal');
}
