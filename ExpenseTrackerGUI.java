import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseTrackerGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Personal Expense Tracker");
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        // Table to display expenses
        String[] columnNames = {"Date", "Category", "Amount", "Payment Method", "Notes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 6, 5, 5));
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        JTextField dateField = new JTextField();
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();
        JLabel methodLabel = new JLabel("Payment Method:");
        JTextField methodField = new JTextField();
        JLabel notesLabel = new JLabel("Notes:");
        JTextField notesField = new JTextField();
        JButton addButton = new JButton("Add Expense");

        inputPanel.add(dateLabel);
        inputPanel.add(dateField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryField);
        inputPanel.add(amountLabel);
        inputPanel.add(amountField);
        inputPanel.add(methodLabel);
        inputPanel.add(methodField);
        inputPanel.add(notesLabel);
        inputPanel.add(notesField);
        inputPanel.add(new JLabel()); // Empty space
        inputPanel.add(addButton);

        panel.add(inputPanel, BorderLayout.SOUTH);

        // Total expense panel
        JPanel totalPanel = new JPanel();
        JLabel totalLabel = new JLabel("Total Expense: ₹0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalLabel);
        panel.add(totalPanel, BorderLayout.NORTH);

        // Variable to keep track of total expense
        final double[] totalExpense = {0.0};

        // Action for the Add button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = dateField.getText();
                String category = categoryField.getText();
                String amount = amountField.getText();
                String method = methodField.getText();
                String notes = notesField.getText();

                if (date.isEmpty() || category.isEmpty() || amount.isEmpty() || method.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields except Notes!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    double expenseAmount = Double.parseDouble(amount); // Check if amount is valid

                    // Add to the table
                    tableModel.addRow(new Object[]{date, category, expenseAmount, method, notes});

                    // Update total expense
                    totalExpense[0] += expenseAmount;
                    totalLabel.setText(String.format("Total Expense: ₹%.2f", totalExpense[0]));

                    // Clear input fields
                    dateField.setText("");
                    categoryField.setText("");
                    amountField.setText("");
                    methodField.setText("");
                    notesField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Amount must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }
}
