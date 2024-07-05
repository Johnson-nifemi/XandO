import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XandO extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isX = true; // True if it's X's turn, false for O's turn

    public XandO() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("")) {
            buttonClicked.setText(isX ? "X" : "O");
            buttonClicked.setEnabled(false);
            isX = !isX;
            checkForWinner();
        }
    }

    private void checkForWinner() {
        String[][] board = new String[3][3];

        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getText();
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i][0], board[i][1], board[i][2])) {
                announceWinner(board[i][0]);
                return;
            }
            if (checkLine(board[0][i], board[1][i], board[2][i])) {
                announceWinner(board[0][i]);
                return;
            }
        }

        if (checkLine(board[0][0], board[1][1], board[2][2])) {
            announceWinner(board[0][0]);
            return;
        }

        if (checkLine(board[0][2], board[1][1], board[2][0])) {
            announceWinner(board[0][2]);
            return;
        }

        // Check for draw
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkLine(String s1, String s2, String s3) {
        return s1.equals(s2) && s2.equals(s3) && !s1.equals("");
    }

    private void announceWinner(String winner) {
        JOptionPane.showMessageDialog(this, winner + " wins!");
        resetBoard();
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
        }
        isX = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XandO();
            }
        });
    }
}
