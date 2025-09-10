import java.util.*;

public class InteractiveKnightsTour {
    // Все возможные ходы коня (8 направлений)
    private static final int[] ROW_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    private int[][] board;
    private int size;

    public InteractiveKnightsTour(int size) {
        this.size = size;
        this.board = new int[size][size];
        // Заполняем доску -1 (непосещенные клетки)
        for (int[] row : board) {
            Arrays.fill(row, -1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Шахматный обход коня");

        // Запрос размера доски
        System.out.print("Введите размер доски (например, 8): ");
        int n = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        // Создаем экземпляр класса
        InteractiveKnightsTour kt = new InteractiveKnightsTour(n);

        // Запрос начальной позиции
        System.out.print("Введите начальную позицию коня (например, a1): ");
        String startPos = scanner.nextLine().toLowerCase();

        // Преобразование шахматных координат в индексы массива
        int startX = startPos.charAt(0) - 'a';
        int startY = startPos.charAt(1) - '1';

        // Проверка корректности ввода
        if (!kt.isValidPosition(startX, startY)) {
            System.out.println("Ошибка: неверные начальные координаты!");
            return;
        }

        // Запуск решения и вывод результата
        if (kt.solve(startX, startY)) {
            System.out.println("\nРешение найдено:");
            kt.printChessBoard(); // Вывод в шахматном формате
        } else {
            System.out.println("Решение не существует для данных параметров");
        }

        scanner.close();
    }

    public boolean solve(int startX, int startY) {
        board[startX][startY] = 0; // Начальная позиция
        return solveUtil(startX, startY, 1);
    }

    private boolean solveUtil(int x, int y, int moveCount) {
        if (moveCount == size * size) {
            return true;
        }

        // Список возможных ходов с приоритетом
        List<int[]> possibleMoves = new ArrayList<>();

        // Собираем все допустимые ходы
        for (int i = 0; i < 8; i++) {
            int nextX = x + ROW_MOVES[i];
            int nextY = y + COL_MOVES[i];
            if (isValidMove(nextX, nextY)) {
                // Для каждого хода считаем, сколько у него дальнейших вариантов
                int furtherMoves = countPossibleMoves(nextX, nextY);
                possibleMoves.add(new int[]{nextX, nextY, furtherMoves});
            }
        }

        // Сортируем ходы по возрастанию дальнейших вариантов (правило Варнсдорфа)
        possibleMoves.sort(Comparator.comparingInt(a -> a[2]));

        // Пробуем ходы в оптимальном порядке
        for (int[] move : possibleMoves) {
            int nextX = move[0];
            int nextY = move[1];
            board[nextX][nextY] = moveCount;
            if (solveUtil(nextX, nextY, moveCount + 1)) {
                return true;
            } else {
                board[nextX][nextY] = -1; // Backtracking
            }
        }

        return false;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && board[x][y] == -1;
    }

    private int countPossibleMoves(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nextX = x + ROW_MOVES[i];
            int nextY = y + COL_MOVES[i];
            if (isValidMove(nextX, nextY)) count++;
        }
        return count;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    // Вывод доски в шахматном формате (с буквами и цифрами)
    public void printChessBoard() {
        // Вывод букв столбцов сверху
        System.out.print("   ");
        for (char c = 'a'; c < 'a' + size; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();

        // Вывод строк доски
        for (int y = size - 1; y >= 0; y--) {
            System.out.print((y + 1) + " "); // Номер строки
            for (int x = 0; x < size; x++) {
                System.out.printf("%3d", board[x][y]); // Изменили на %3d
            }
            System.out.println(" " + (y + 1)); // Номер строки справа
        }

        // Вывод букв столбцов снизу
        System.out.print("   ");
        for (char c = 'a'; c < 'a' + size; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();
    }
}