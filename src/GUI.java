import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel gameTitlePanel, winnerPanel, menuPanel, gameBoard;
    private JButton resetButton, exitButton;
    private JButton[] gameButtons = new JButton[9];
    private JLabel gameTitle, winner;

    GameLogic algo = new GameLogic();

    private final String PLAYER = "X", BOT = "O";

    public GUI() {
        algo.initBoard();

        // frame setup
        frame = new JFrame();
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);

        // game title setup
        gameTitle = new JLabel();
        gameTitle.setFont(new Font("Cambria", Font.PLAIN, 80));
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setFocusable(false);
        gameTitle.setText("Tic Tac Toe");

        // game title panel setup
        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(95, 0, 400, 100);
        gameTitlePanel.setBackground(Color.BLACK);
        gameTitlePanel.setFocusable(false);
        gameTitlePanel.add(gameTitle);
        frame.add(gameTitlePanel);

        // winner setup
        winner = new JLabel();
        winner.setFont(new Font("Cambria", Font.PLAIN, 28));
        winner.setForeground(Color.WHITE);
        winner.setFocusable(false);
        winner.setText("Player Won!");

        // winner panel setup
        winnerPanel = new JPanel();
        winnerPanel.setBounds(199, 540, 200, 50);
        winnerPanel.setBackground(Color.BLACK);
        winnerPanel.add(winner);
        frame.add(winnerPanel);

        // reset button setup
        resetButton = new JButton();
        resetButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        resetButton.setText("RESET");
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        // exit button setup
        exitButton = new JButton();
        exitButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        exitButton.setText("EXIT");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        // menu panel setup
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        menuPanel.setBounds(420, 555, 175, 40);
        menuPanel.setBackground(Color.BLACK);
        menuPanel.add(resetButton);
        menuPanel.add(Box.createRigidArea(new Dimension(3, 0)));
        menuPanel.add(exitButton);
        frame.add(menuPanel);

        // game board setup
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3));
        gameBoard.setBounds(95, 120, 400, 400);
        gameBoard.setBackground(Color.BLACK);
        gameBoard.setVisible(true);
        frame.add(gameBoard);

        // game buttons setup
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i] = new JButton();
            gameButtons[i].setFocusable(false);
            gameButtons[i].setBackground(Color.BLACK);
            gameButtons[i].setForeground(Color.WHITE);
            gameButtons[i].setFont(new Font("Cambria", Font.PLAIN, 90));
            gameButtons[i].addActionListener(this);
            gameBoard.add(gameButtons[i]);
        }

        frame.setVisible(true);

        while (true) {
            autoCheck();
        }
    }

    // auto check
    public void autoCheck() {
        int status = algo.checkWinner();

        switch(status) {
            case 1:
                winner.setText("Player Won!");
                break;
            case 2:
                winner.setText("Computer Won!");
                break;
            case 3:
                winner.setText("Draw!");
                break;
            default:
                winner.setText("");
                break;
        }

        // disable buttons
        if (status == 1 || status == 2 || status == 3) {
            for (int i  = 0; i < gameButtons.length; i++) {
                gameButtons[i].setEnabled(false);
            }
        }

        // enable buttons
        else {
            for (int i  = 0; i < gameButtons.length; i++) {
                gameButtons[i].setEnabled(true);
            }
        }
    }

    // bot makes its move
    public void botMove() {
        int botMove = algo.getBotMove();
        gameButtons[botMove].setText(BOT);
        algo.insertMove(botMove, BOT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object a = e.getSource();
        
        if (a == resetButton) {
            algo.initBoard();
            for (int i = 0; i < gameButtons.length; i++) {
                gameButtons[i].setText("");
                gameButtons[i].setEnabled(true);
            }
        }

        if (a == exitButton) {
            System.exit(0);
        }

        for (int i = 0; i < gameButtons.length; i++) {
            if (a == gameButtons[i]) {
                if (algo.spotIsEmpty(i)) {
                    gameButtons[i].setText(PLAYER);
                    algo.insertMove(i, PLAYER);

                    int winner = algo.checkWinner();
                    if (winner == 0) {
                        botMove();
                    }
                }
                else {
                    System.out.println("You cannot go here");
                }
            }
        }
    }
    
}
