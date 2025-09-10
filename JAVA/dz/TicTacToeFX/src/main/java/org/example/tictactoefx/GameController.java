package org.example.tictactoefx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {
    private boolean xTurn = true;
    private boolean gameEnded = false;
    @FXML private Button button00;
    @FXML private Button button01;
    @FXML private Button button02;
    @FXML private Button button10;
    @FXML private Button button11;
    @FXML private Button button12;
    @FXML private Button button20;
    @FXML private Button button21;
    @FXML private Button button22;
    @FXML private Label statusLabel;
    private Button[][] board;

    @FXML
    private void initialize(){
        board = new Button[][]{
                {button00, button01, button02},
                {button10, button11, button12},
                {button20, button21, button22}
        };
        statusLabel.setText("Ход: X");
        for (Button[] row : board){
            for (Button btn : row){
                btn.setOnAction(event -> handleButtonClick(btn));
                btn.setStyle("-fx-font-size: 36px;");
            }
        }
    }
    @FXML
    protected void handleButtonClick(Button button){
        if (gameEnded || !button.getText().isEmpty())
            return;

        button.setText(xTurn ? "X" : "O");

        if (checkWinner()) {
            gameEnded = true;
            statusLabel.setText("Победил: " + (xTurn ? "X" : "O"));

        } else {
            xTurn = !xTurn;
            statusLabel.setText("Ход: " + (xTurn ? "X" : "O"));
        }
    }

    private boolean checkWinner(){
        String currentSymbol = xTurn ? "X" : "O";
        for (int i = 0; i < 3; i++){
            if (currentSymbol.equals(board[i][0].getText()) &&
                currentSymbol.equals(board[i][1].getText()) &&
                currentSymbol.equals(board[i][2].getText()))
                return true;

            if (currentSymbol.equals(board[0][i].getText()) &&
                currentSymbol.equals(board[1][i].getText()) &&
                currentSymbol.equals(board[2][i].getText()))
                return true;
        }
        if (currentSymbol.equals(board[0][0].getText()) &&
            currentSymbol.equals(board[1][1].getText()) &&
            currentSymbol.equals(board[2][2].getText()))
            return true;

        if (currentSymbol.equals(board[0][2].getText()) &&
            currentSymbol.equals(board[1][1].getText()) &&
            currentSymbol.equals(board[2][0].getText()))
            return true;

        return false;
    }


}
