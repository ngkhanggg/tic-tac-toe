import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel gameTitlePanel;
    private JPanel gameBoard;
    private JPanel winnerPanel;
    private JPanel startPanel;
    private JPanel exitPanel;
    private JButton gameButtons[] = new JButton[9];
    private JButton startButton;
    private JButton exitButton;
    private JLabel gameTitle;
    private JLabel winner;

    GameLogic algo = new GameLogic();

    private final String PLAYER = "X", BOT = "O";

    public GUI() {
        // frame setup
        frame = new JFrame();
        frame.setTitle("Tic Tac Toe");
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);

        // game's title setup
        gameTitle = new JLabel();
        gameTitle.setFont(new Font("Cambria", Font.PLAIN, 80));
        gameTitle.setForeground(Color.WHITE);
        gameTitle.setText("Tic Tac Toe");

        // game's title panel setup
        gameTitlePanel = new JPanel();
        gameTitlePanel.setBounds(95, 240, 400, 100);
        gameTitlePanel.setBackground(Color.BLACK);
        gameTitlePanel.add(gameTitle);
        frame.add(gameTitlePanel);

        // game panel setup
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3));
        gameBoard.setBounds(95, 90, 400, 400);
        gameBoard.setBackground(Color.BLACK);

        // winner label setup
        winner = new JLabel();
        winner.setFont(new Font("Cambria", Font.PLAIN, 28));
        winner.setForeground(Color.WHITE);

        // winner pannel setup
        winnerPanel = new JPanel();
        winnerPanel.setBounds(195, 20, 200, 50);
        winnerPanel.setBackground(Color.BLACK);
        winnerPanel.add(winner);
        frame.add(winnerPanel);

        // exit button setup
        exitButton = new JButton();
        exitButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        exitButton.setText("EXIT");
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        // exit panel setup
        exitPanel = new JPanel();
        exitPanel.setBounds(495, 550, 120, 40);
        exitPanel.setBackground(Color.BLACK);
        exitPanel.add(exitButton);
        frame.add(exitPanel);

        // reset button setup
        startButton = new JButton();
        startButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        startButton.setText("START");
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(this);
        startButton.setEnabled(true);
        startButton.setFocusable(false);

        // reset button panel setup
        startPanel = new JPanel();
        startPanel.setBounds(236, 505, 120, 40);
        startPanel.setBackground(Color.BLACK);
        startPanel.add(startButton);
        frame.add(startPanel);

        // game buttons setup
        for (int i = 0; i < gameButtons.length; i++) {
            gameButtons[i] = new JButton();
            gameButtons[i].setFocusable(false);
            // gameButtons[i].setText(String.valueOf(i);
            gameButtons[i].setBackground(Color.BLACK);
            gameButtons[i].setForeground(Color.WHITE);
            gameButtons[i].setFont(new Font("Cambria", Font.PLAIN, 90));
            gameButtons[i].addActionListener(this);
            gameButtons[i].setEnabled(false);
            gameBoard.add(gameButtons[i]);
        }

        frame.setVisible(true);
    }
    
    // auto check for winner
    public void autoCheck() {
        int status = algo.getWinner();

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
        algo.insertLetter(botMove, BOT);
    }

    // action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startButton.setEnabled(false);
            frame.getContentPane().remove(startPanel);
            frame.getContentPane().remove(gameTitlePanel);
            frame.repaint();
            frame.add(gameBoard);
            botMove();
        }
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        for (int i = 0; i < algo.board.length; i++) {
            if (e.getSource() == gameButtons[i]) {
                if (algo.spotIsFree(i)) {
                    gameButtons[i].setText(PLAYER);
                    algo.insertLetter(i, PLAYER);

                    int winner = algo.getWinner();
                    if (winner != 1 || winner != 2 || winner != 3) {
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
