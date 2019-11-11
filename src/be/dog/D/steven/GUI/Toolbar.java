package be.dog.D.steven.GUI;

import javax.swing.*;
import java.awt.*;

class Toolbar extends JPanel  {

    // Fields
    private JButton textPanelButton;
    private JButton tablePanelButton;

    Toolbar() {

        // optional BORDER ..
        // setBorder(BorderFactory.createEtchedBorder());

        // Create buttons
        textPanelButton = new JButton("Text Panel");
        tablePanelButton = new JButton("Table Panel");


        // Set layout
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Add buttons to layout
        add(textPanelButton);
        add(tablePanelButton);
    }

    JButton getTextPanelButton() {
        return textPanelButton;
    }

    JButton getTablePanelButton() { return tablePanelButton; }
}