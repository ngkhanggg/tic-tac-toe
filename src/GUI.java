import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
    private JFrame frame;
    private JPanel gameBoard;
    private JPanel winnerPanel;
    private JPanel resetPanel;
    private JPanel exitPanel;
    private JButton buttons[] = new JButton[9];
    private JButton resetButton;
    private JButton exitButton;
    private JLabel winner;
    
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

        // game panel setup
        gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(3, 3));
        gameBoard.setBounds(95, 90, 400, 400);
        gameBoard.setBackground(Color.BLACK);
        frame.add(gameBoard);

        // winner label setup
        winner = new JLabel();
        winner.setFont(new Font("Cambria", Font.PLAIN, 28));
        winner.setText("Player Won!");
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
        resetButton = new JButton();
        resetButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        resetButton.setText("RESET");
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusable(false);

        // reset button panel setup
        resetPanel = new JPanel();
        resetPanel.setBounds(236, 505, 120, 40);
        resetPanel.setBackground(Color.BLACK);
        resetPanel.add(resetButton);
        frame.add(resetPanel);

        // buttons setup
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            // buttons[i].setText(String.valueOf(i);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("Cambria", Font.PLAIN, 70));
            buttons[i].addActionListener(this);
            gameBoard.add(buttons[i]);
        }

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                buttons[i].setText("X");
            }
        }
    }
}
