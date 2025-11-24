package view;

import interface_adapter.autosave.AutosaveController;
import interface_adapter.autosave.AutosaveViewModel;
import interface_adapter.transactions.TransactionsViewModel;
import interface_adapter.transactions.ViewTransactionsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.*;

public class TransactionsView implements ActionListener, PropertyChangeListener {
    private ViewTransactionsController controller;

    private final TransactionsViewModel viewModel;


//    private final JButton saveNowButton = new JButton("Save Now");


    public TransactionsView(TransactionsViewModel viewModel) {
        this.viewModel = viewModel;
}

//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        if (!"autosaveState".equals(evt.getPropertyName())) {
//            return;
///
//        }
//    }

    public void setTransactionController(ViewTransactionsController viewTrnasactionsController) {
        this.controller = viewTrnasactionsController;
    }


    static final int ROWS = 4;
    static final int COLS = 2;
    static final int WIDTH = 850;
    static final int HEIGHT = 500;


// utility methods that take care of setting up each JPanel to be displayed
// in our GUI
private static JPanel createDefaultCard() {
    final JPanel defaultCard = new JPanel();
    defaultCard.setLayout(new BoxLayout(defaultCard, BoxLayout.Y_AXIS));

    defaultCard.add( new JLabel("Home Page"));
    defaultCard.add( new JLabel("Welcome! "));

    return defaultCard;
}

private static JPanel createGetTransactionCard(JFrame jFrame) {
    final int ROWS = 4;
    final int COLS = 2;
    final int WIDTH = 850;
    final int HEIGHT = 500;


    //Testing the transactions using a hashmps
    final JPanel getTransactionCard = new JPanel();
    getTransactionCard.setLayout(new BorderLayout(ROWS, COLS));

    ArrayList<HashMap<String, Object>> transaction_test_data = new ArrayList<>();


 //TODO: Convert to trnsaction Object
//    HashMap<String, Object> trans1 = new HashMap<>();
//    trans1.put("date", "2025-11-01");
//    trans1.put("source", "Employer");
//    trans1.put("category", "Income");
//    trans1.put("amount", 3500.00);
//    transaction_test_data.add(trans1);

    JPanel tileBlock = new JPanel();
    tileBlock.setLayout(new GridLayout(0, 1));


    // building the UI as a function.
    //Create a container for the tiles
    rebuildTiles(tileBlock, jFrame, transaction_test_data);

    getTransactionCard.add(tileBlock, BorderLayout.NORTH);

//        JButton okayButton = new JButton("Okay");
//        okayButton.addActionListener(cancelEvent -> {
//            dialog.dispose();
//        });

//        getTransactionCard.add(okayButton, BorderLayout.SOUTH);



    return getTransactionCard;


}
private static void rebuildTiles(JPanel tileBlock, JFrame jFrame,
                                 ArrayList<HashMap<String, Object>> transaction_test_data) {

    tileBlock.removeAll();

    final JButton editButton = new JButton("edit");


    //transaction panel
    JPanel transactionTile = new JPanel();
    transactionTile.setLayout(new GridLayout(1, 6));

    transactionTile.add(new JLabel("Date"));
    transactionTile.add(new JLabel("Source"));
    transactionTile.add(new JLabel("Category"));
    transactionTile.add(new JLabel("Amount"));
    JButton placeholderButton = new JButton("Edit");


    placeholderButton.setEnabled(false); // Makes it look like a label
    transactionTile.add(placeholderButton);
    transactionTile.add(new JLabel("                             "));

    tileBlock.setLayout(new GridLayout(0, 1));
    tileBlock.add(transactionTile);
    tileBlock.add(new JLabel("   ")); //sepeartor btw tiles


// Create tiles
    int num_trans = transaction_test_data.size();

    for (int i = 0; i < num_trans; i++) {
        HashMap<String, Object> transaction = transaction_test_data.get(i);

        JPanel tile = new JPanel();
        tile.setLayout(new GridLayout(1, 5));

        tile.add(new JLabel((String) transaction.get("date")));
        tile.add(new JLabel((String) transaction.get("source")));
        tile.add(new JLabel((String) transaction.get("category")));
        tile.add(new JLabel(String.format("%.2f", (Double) transaction.get("amount")))); // *********


        JButton editBtn = new JButton("edit");

        //set the number of the buttons be the name
        editBtn.setName(String.valueOf(i));
        editBtn.addActionListener(e -> {


            // create a daiglof as a pop-up
            JDialog dialog = new JDialog(jFrame, "Edit category", true); // modal
            dialog.setSize(400, 300);
            dialog.setLayout(new BorderLayout());

            //edit the button
            JButton btn = (JButton) e.getSource();
            int tile_num = Integer.parseInt(btn.getName());

            //debuggin try
            System.out.println(tile_num);

            //Creatte a panel to put inside the drop_down
            JPanel popUpPanel = new JPanel();
            popUpPanel.setLayout(new GridLayout(0, 2));

            //create a drop_down
            String[] categories = {"Income", "Rent_Utilities", "Food", "Transportation", "Shopping", "Other"};
            JComboBox<String> categoryCombo = new JComboBox<>(categories);
            categoryCombo.setSelectedItem(transaction_test_data.get(tile_num).get("category"));
            dialog.add(new JLabel("Category:"));
            dialog.add(categoryCombo);
            transaction_test_data.get(tile_num).put("category", categoryCombo.getSelectedItem());

            //add the drop_down to the popUpPanel
            popUpPanel.add(new JLabel("Category:"));
            popUpPanel.add(categoryCombo);


            //create save and cancel buttons
            JPanel buttonPanel = new JPanel();
            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");

            saveButton.addActionListener(save_cat -> {

                //TODO: add the function that connects with transaction Java
                transaction_test_data.get(tile_num).put("category", categoryCombo.getSelectedItem());
                System.out.println(categoryCombo.getSelectedItem());
                JOptionPane.showMessageDialog(dialog, " Category updated!");
                dialog.dispose();

                rebuildTiles(tileBlock, jFrame, transaction_test_data);



            });


            //when you cancel
            cancelButton.addActionListener(cancelEvent -> {
                dialog.dispose();
            });

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            dialog.add(popUpPanel, BorderLayout.CENTER);
            dialog.add(buttonPanel, BorderLayout.SOUTH);
            dialog.setVisible(true); //makes sure to turn on dialog


        });

        tile.add(editBtn);
        tile.add(new JLabel("   "));

        tileBlock.add(tile);
    }

    tileBlock.revalidate();
    tileBlock.repaint();



}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
