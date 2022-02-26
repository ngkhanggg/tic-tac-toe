public class GameLogic {
    public String[] board = new String[9];
    public int count;

    public GameLogic() {
        count = 0;
        initializeBoard();
    }

    // initialize board
    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = "";
        }
    }

    // check if spot is free
    public boolean spotIsFree(int position) {
        return board[position] == "";
    }

    // insert letter into board
    public void insertLetter(int position, String symbol) {
        if (spotIsFree(position)) {
            board[position] = symbol;
            this.count++;
        }
    }

    // keep playing
    public boolean keepPlaying() {
        return getWinner() == 0;
    }

    // get winner
    public String winnerNoti() {
        switch(getWinner()) {
            case 1:
                return "Player Won!";
            case 2:
                return "Computer Won!";
            case 3:
                return "Draw!";
            default:
                return "";
        }
    }

    // check for winner
    public int getWinner() {
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
        int bestScore = -1;
        int bestMove = 0;

        for (int i = 0; i < board.length; i++) {
            if (board[i] == "") {
                board[i] = "O";
                this.count++;
                int score = minimax(0, false);
                board[i] = "";
                this.count--;
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }

        return bestMove;
    }

    public int minimax(int depth, boolean isMaximizing) {
        int winner = getWinner();

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
                    this.count++;
                    int score = minimax(0, false);
                    board[i] = "";
                    this.count--;
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
                    this.count++;
                    int score = minimax(depth + 1, true);
                    board[i] = "";
                    this.count--;
                    if (score < bestScore) {
                        bestScore = score;
                    }
                }
            }

            return bestScore;
        }
    }
}
