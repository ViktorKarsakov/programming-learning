interface ObserverMatrix{ //Интерфейс для подписчиков
    void update(char[][] board);
}

class MatrixModel{ // Рассыльщик (отправитель)
    private ObserverMatrix observer;
    private char[][] board = new char[3][3];


    void setPoint(int x, int y, char symbol){
        if (observer != null){
            board[x][y] = symbol;
            observer.update(board);
        }
    };

    public void setObserver(ObserverMatrix view){
        observer = view;
    }

}

class MatrixView implements ObserverMatrix{ //Подписчик (окно отрисовки)

    char[][] board = new char[3][3];

    @Override
    public void update(char[][] board){
        this.board = board;
        printBoard();
    }

    public void printBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(((board[i][j] == '\u0000') ? "_" : board[i][j]) + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MatrixModel game = new MatrixModel();

        MatrixView view = new MatrixView();
        game.setObserver(view);

        while (true){
            game.setPoint(0,0,'X');
        }

    }
}