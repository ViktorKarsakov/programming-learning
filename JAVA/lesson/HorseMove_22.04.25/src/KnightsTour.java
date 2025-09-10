import java.util.Arrays;

public class KnightsTour {
    // Все возможные ходы коня по горизонтали (соответствуют ходу буквой "Г")
    private static final int[] ROW_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    // Все возможные ходы коня по вертикали (в паре с ROW_MOVES образуют все 8 ходов)
    private static final int[] COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    // Шахматная доска (двумерный массив)
    private int[][] board;
    // Размер доски (n x n)
    private int size;

    // Конструктор класса
    public KnightsTour(int size) {
        this.size = size;
        this.board = new int[size][size];
        // Инициализируем все клетки доски значением -1 (не посещены)
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
    }

    // Основной метод для запуска решения
    public boolean solve(int startX, int startY) {
        // Помечаем начальную позицию коня (первый ход с номером 0)
        board[startX][startY] = 0;
        // Запускаем рекурсивный поиск решения
        return solveUtil(startX, startY, 1);
    }

    // Рекурсивный метод для поиска решения
    private boolean solveUtil(int x, int y, int moveCount) {
        // Базовый случай: если все клетки посещены
        if (moveCount == size * size) {
            return true;
        }

        // Перебираем все 8 возможных ходов коня
        for (int i = 0; i < 8; i++) {
            // Вычисляем координаты следующей клетки
            int nextX = x + ROW_MOVES[i];
            int nextY = y + COL_MOVES[i];

            // Проверяем, можно ли сделать такой ход
            if (isValidMove(nextX, nextY)) {
                // Помечаем клетку номером текущего хода
                board[nextX][nextY] = moveCount;

                // Рекурсивно пытаемся продолжить маршрут из новой позиции
                if (solveUtil(nextX, nextY, moveCount + 1)) {
                    return true; // Решение найдено
                } else {
                    // Если решение не найдено, откатываем изменения (backtracking)
                    board[nextX][nextY] = -1;
                }
            }
        }
        // Если ни один ход не привел к решению
        return false;
    }

    // Проверка, является ли ход допустимым
    private boolean isValidMove(int x, int y) {
        // Ход допустим, если:
        // 1. Координаты находятся в пределах доски
        // 2. Клетка еще не посещалась (значение -1)
        return x >= 0 && x < size && y >= 0 && y < size && board[x][y] == -1;
    }

    // Метод для вывода решения
    public void printSolution() {
        for (int[] row : board) {
            for (int cell : row) {
                // Форматированный вывод (2 символа на число)
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
    }
}