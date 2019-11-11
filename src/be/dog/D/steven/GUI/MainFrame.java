package be.dog.D.steven.GUI;

import be.dog.D.steven.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener {

    //Fields
    private TextPanel textPanel;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;


    public MainFrame() {

        // Window Title
        super("Registration");

        // Select overall LAYOUT in Main Frame
        setLayout(new BorderLayout());

        // Creating Main Frame content - from UI folder
        Toolbar toolbar = new Toolbar();
        textPanel = new TextPanel();
        JButton btn = new JButton("Quick Save");
        formPanel = new FormPanel();
        tablePanel = new TablePanel();

        controller = new Controller();

        tablePanel.setData(controller.getPeople());

        // Pass new PersonTableListener to implement rowDeleted in tablePanel
        tablePanel.setPersonTableListener(row -> {
            controller.removePerson(row);
        });

        // One fileChooser for import/export
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new PersonFileFilter()); //.addChoosableFileFilter to add more

        // Set J!!!Menu Bar
        setJMenuBar(createMenuBar());

        // StringListener: output directed to textPanel
        toolbar.getTablePanelButton().addActionListener(this);
        toolbar.getTextPanelButton().addActionListener(this);

        // ActionListener for okBtn
        formPanel.setFormListener(e -> {
            String name = e.getName();
            String occupation = e.getOccupation();
            int ageCat = e.getAgeCategory();
            String empCat = e.getEmpCat();
            String gender = e.getGender();
            // Old prints to TextPanel
            textPanel.appendText(name + ": " + occupation + ": " + ageCat + ", " + empCat + " - " + gender + "\n");
            controller.addPerson(e);
            tablePanel.refresh();
        });

        // Simple ActionListener for bottom button
        btn.addActionListener(actionEvent -> {
            String saveName = JOptionPane.showInputDialog(MainFrame.this,
                    "File name", "Save file", JOptionPane.WARNING_MESSAGE);
            // Quick save to a local temp.per
            if (saveName != null && !saveName.equals("")) {
                if (saveName.matches("^[a-zA-Z0-9_.-]{1,18}$")) {
                    File file = new File(saveName + ".per");
                    try {
                        controller.saveToFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    textPanel.appendText("Table saved...\n");
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Invalid file name!");
                }
            }
        });

        // Add content to Main Frame Layout
        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(btn, BorderLayout.SOUTH);

        // Window settings
        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Show Main Frame
        setVisible(true);

    }

    // Methods to switch panels
    private void switchToText() {
        remove(tablePanel);
        add(textPanel, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    private void switchToTable() {
        remove(textPanel);
        add(tablePanel, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }

    private JMenuBar createMenuBar() {
        //Create MenuBar
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");

        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Window Menu
        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Form Interaction");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        // Add Menus to MenuBar
        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        // Create MenuItem ActionListener
        showFormItem.addActionListener(actionEvent -> {
            JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) actionEvent.getSource();
            formPanel.setVisible(menuItem.isSelected());
        });

        // Set up Mnemonics
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        // Mnemonics with OUT-OF-MENU accelerator
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
        exportDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));

        // Import dialog ActionListener
        importDataItem.addActionListener(actionEvent -> {
            if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.loadFromFile(fileChooser.getSelectedFile());
                    tablePanel.refresh();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failed to load data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Export dialog ActionListener
        exportDataItem.addActionListener(actionEvent -> {
            if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                try {
                    controller.saveToFile(fileChooser.getSelectedFile());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Failed to save data.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitItem.addActionListener(actionEvent -> {
            String saveName = JOptionPane.showInputDialog(MainFrame.this,
                    "File name", "Save file", JOptionPane.WARNING_MESSAGE);
            // Save method goes here !=null catches cancel, !"" catches empty input
            // Quick save to a local temp.per
            if (saveName != null && !saveName.equals("")) {
                if (saveName.matches("^[a-zA-Z0-9_.-]{1,18}$")) { //regex could catch empty input too
                    File file = new File(saveName + ".per");
                    try {
                        controller.saveToFile(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    textPanel.appendText("Table saved...\n");
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Invalid file name!");
                }
            }

            int action = JOptionPane.showConfirmDialog(MainFrame.this,
                    "Exit application?", "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if (action == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });

        return menuBar;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clicked = (JButton) actionEvent.getSource();
        if (clicked.getText().equals("Text Panel")) {
            switchToText();
        } else if (clicked.getText().equals("Table Panel")) {
            switchToTable();
        }
    }
}
