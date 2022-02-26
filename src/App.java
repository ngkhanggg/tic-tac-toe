import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        JPanel gamePanel = new JPanel();
        JPanel winnerPanel = new JPanel();
        JPanel resetPanel = new JPanel();
        JButton buttons[] = new JButton[9];
        JButton resetButton = new JButton();
        JLabel winner = new JLabel();

        // frame setup
        frame.setTitle("Tic Tac Toe");
        frame.setSize(600, 600);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        // game panel setup
        gamePanel.setLayout(new GridLayout(3, 3));
        gamePanel.setBounds(95, 90, 400, 400);
        gamePanel.setBackground(Color.BLACK);
        frame.add(gamePanel);

        // winner label setup
        winner.setFont(new Font("Cambria", Font.PLAIN, 28));
        winner.setText("Player Won!");
        winner.setForeground(Color.WHITE);

        // winner pannel setup
        winnerPanel.setBounds(195, 20, 200, 50);
        winnerPanel.setBackground(Color.BLACK);
        winnerPanel.add(winner);
        frame.add(winnerPanel);

        // reset button setup
        resetButton.setFont(new Font("Cambria", Font.PLAIN, 20));
        resetButton.setText("RESET");
        resetButton.setBackground(Color.BLACK);
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusable(false);

        // reset button panel setup
        resetPanel.setBounds(236, 505, 120, 40);
        resetPanel.setBackground(Color.BLACK);
        resetPanel.add(resetButton);
        frame.add(resetPanel);

        // buttons setup
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFocusable(false);
            buttons[i].setText(String.valueOf(i));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            gamePanel.add(buttons[i]);
        }

        frame.setVisible(true);
    }
}
