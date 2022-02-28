public class GameLogic {
    String[] board = new String[9];
    int count = 0;

    public void initBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = "";
        }
        count = 0;
    }

    // check if spot is empty
    public boolean spotIsEmpty(int position) {
        return board[position].equals("");
    }

    // insert letter into the board
    public void insertMove(int position, String symbol) {
        if (spotIsEmpty(position)) {
            board[position] = symbol;
            count++;
        }
    }

    // check for winner
    public int checkWinner() {
        // player won
        if (
            board[0] == board[1] && board[1] == board[2] && board[2] == "X" || 
            board[3] == board[4] && board[4] == board[5] && board[5] == "X" || 
            board[6] == board[7] && board[7] == board[8] && board[8] == "X" || 

            board[0] == board[3] && board[3] == board[6] && board[6] == "X" || 
            board[1] == board[4] && board[4] == board[7] && board[7] == "X" || 
            board[2] == board[5] && board[5] == board[8] && board[8] == "X" ||
            
            board[0] == board[4] && board[4] == board[8] && board[8] == "X" || 
            board[2] == board[4] && board[4] == board[6] && board[6] == "X"
        ) {
            return 1;
        }

        // computer won
        else if (
            board[0] == board[1] && board[1] == board[2] && board[2] == "O" || 
            board[3] == board[4] && board[4] == board[5] && board[5] == "O" || 
            board[6] == board[7] && board[7] == board[8] && board[8] == "O" || 

            board[0] == board[3] && board[3] == board[6] && board[6] == "O" || 
            board[1] == board[4] && board[4] == board[7] && board[7] == "O" || 
            board[2] == board[5] && board[5] == board[8] && board[8] == "O" ||
            
            board[0] == board[4] && board[4] == board[8] && board[8] == "O" || 
            board[2] == board[4] && board[4] == board[6] && board[6] == "O"
        ) {
            return 2;
        }

        // draw
        else if (count == 9) {
            return 3;
        }

        // keep playing
        else {
            return 0;
        }
    }

    public int getBotMove() {
        int bestScore = -Integer.MAX_VALUE;
        int bestMove = -1;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == "") {
                board[i] = "O";
                count++;
                int score = minimax(0, false);
                board[i] = "";
                count--;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    public int minimax(int depth, boolean isMaximizing) {
        int winner = checkWinner();

        // if bot wins, return positive value
        if (winner == 2) {
            return 1;
        }

        // if player wins, return negative value
        else if (winner == 1) {
            return -1;
        }

        // if it's a tie, return 0
        else if (winner == 3) {
            return 0;
        }

        // bot move
        if (isMaximizing) {
            int bestScore = -1;

            for (int i = 0; i < 9; i++) {
                if (board[i] == "") {
                    board[i] = "O";
                    count++;
                    int score = minimax(0, false);
                    board[i] = "";
                    count--;
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            }

            return bestScore;
        }

        // is minimizing
        // player's best move
        else {
            int bestScore = 1;
            
            for (int i = 0; i < 9; i++) {
                if (board[i] == "") {
                    board[i] = "X";
                    count++;
                    int score = minimax(depth + 1, true);
                    board[i] = "";
                    count--;
                    if (score < bestScore) {
                        bestScore = score;
                    }
                }
            }

            return bestScore;
        }
    }
}
