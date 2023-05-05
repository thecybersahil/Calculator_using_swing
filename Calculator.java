import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    // Declare variables
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JTextField display;
    private JButton[] buttons;
    private String[] buttonLabels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";

    // Constructor
    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(300, 300);
        setLocationRelativeTo(null);

        // Create and add display to panel
        display = new JTextField();
        display.setPreferredSize(new Dimension(250, 30));
        display.setEditable(false);
        panel = new JPanel(new FlowLayout());
        panel.add(display);

        // Add KeyListener to the display field
        display.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c) || c == '.' || c == '+' || c == '-' || c == '*' || c == '/') {
                    display.setText(display.getText() + c);
                } else if (c == KeyEvent.VK_ENTER) {
                    performCalculation();
                } else if (c == KeyEvent.VK_BACK_SPACE) {
                    String text = display.getText();
                    if (text.length() > 0) {
                        display.setText(text.substring(0, text.length() - 1));
                    }
                } else if (c == KeyEvent.VK_ESCAPE) {
                    clearDisplay();
                }
            }
        });

        // Create and add buttons to panel
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttons[i].setPreferredSize(new Dimension(60, 40));
            panel.add(buttons[i]);
        }

        add(panel);
        setVisible(true);
    }

    // Action listener for buttons
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();

        if (label.matches("[0-9.]")) {
            display.setText(display.getText() + label);
        } else if (label.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = label;
            display.setText("");
        } else if (label.equals("=")) {
            performCalculation();
        } else if (label.equals("C")) {
            clearDisplay();
        }
    }

    // Perform calculation
    // Perform calculation
private void performCalculation() {
    try {
        num2 = Double.parseDouble(display.getText());
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            result = num1 / num2;
        }
        display.setText(String.valueOf(result));
    } catch (NumberFormatException ex) {
        display.setText("Error");
    }
}


    // Clear display
    private void clearDisplay() {
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = "";
        display.setText("");
    }

    // Main method to start calculator
   

    public static void main(String[] args) {
        new Calculator();
    }
}
