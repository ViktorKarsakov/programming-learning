/**
 * KVD Application - Settings (Настройки)
 *
 * Функционал:
 * 1) Отображение информации о текущем пользователе (логин, роль)
 * 2) Смена собственного пароля (POST /api/auth/change-password)
 */

document.addEventListener('DOMContentLoaded', () => {
    loadUserInfo();
});

/**
 * Загружает информацию о текущем пользователе.
 * Использует тот же эндпоинт GET /api/auth/me, что и common.js для сайдбара.
 */
async function loadUserInfo() {
    try {
        const user = await get('/auth/me');

        document.getElementById('settingsUsername').textContent = user.username || '—';

        // Отображаем роль по-русски
        const roleNames = {
            'ADMIN': 'Администратор',
            'OPERATOR': 'Оператор'
        };
        document.getElementById('settingsRole').textContent = roleNames[user.role] || user.role;

    } catch (error) {
        console.error('Ошибка загрузки пользователя:', error);
    }
}

/**
 * Смена пароля.
 *
 * Валидация на фронте:
 * 1) Все три поля заполнены
 * 2) Новый пароль минимум 4 символа
 * 3) Новый пароль и подтверждение совпадают
 *
 * Бэкенд дополнительно проверяет:
 * - Старый пароль правильный (иначе 400 Bad Request)
 */
async function changePassword() {
    const oldPassword = document.getElementById('oldPassword').value;
    const newPassword = document.getElementById('newPassword').value;
    const confirmPassword = document.getElementById('confirmPassword').value;

    // Валидация
    if (!oldPassword || !newPassword || !confirmPassword) {
        showToast('Заполните все поля', 'error');
        return;
    }

    if (newPassword.length < 4) {
        showToast('Новый пароль должен быть не менее 4 символов', 'error');
        return;
    }

    if (newPassword !== confirmPassword) {
        showToast('Новый пароль и подтверждение не совпадают', 'error');
        return;
    }

    try {
        await post('/auth/change-password', {
            oldPassword: oldPassword,
            newPassword: newPassword
        });

        showToast('Пароль успешно изменён', 'success');

        // Очищаем поля после успешной смены
        document.getElementById('oldPassword').value = '';
        document.getElementById('newPassword').value = '';
        document.getElementById('confirmPassword').value = '';

    } catch (error) {
        showToast(error.message || 'Ошибка смены пароля', 'error');
    }
}
