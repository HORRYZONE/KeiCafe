import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CafeManagementSystem extends JFrame {
    private JLabel lblCoffee, lblTea, lblQuantity, lblTotal;
    private JTextField txtCoffee, txtTea, txtQuantity, txtTotal;
    private JButton btnCalculate, btnExit;

    public CafeManagementSystem() {
        setLayout(new FlowLayout());

        lblCoffee = new JLabel("Coffee: ");
        add(lblCoffee);

        txtCoffee = new JTextField(10);
        add(txtCoffee);

        lblTea = new JLabel("Tea: ");
        add(lblTea);

        txtTea = new JTextField(10);
        add(txtTea);

        lblQuantity = new JLabel("Quantity: ");
        add(lblQuantity);

        txtQuantity = new JTextField(10);
        add(txtQuantity);

        btnCalculate = new JButton("Calculate");
        add(btnCalculate);

        lblTotal = new JLabel("Total: ");
        add(lblTotal);

        txtTotal = new JTextField(10);
        txtTotal.setEditable(false);
        add(txtTotal);

        btnExit = new JButton("Exit");
        add(btnExit);

        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double coffee = Double.parseDouble(txtCoffee.getText());
                    double tea = Double.parseDouble(txtTea.getText());
                    int quantity = Integer.parseInt(txtQuantity.getText());

                    double total = (coffee * quantity) + (tea * quantity);
                    txtTotal.setText(String.format("%.2f", total));
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CafeManagementSystem();
    }
}