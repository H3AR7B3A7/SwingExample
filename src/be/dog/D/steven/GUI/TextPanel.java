package be.dog.D.steven.GUI;

import javax.swing.*;
import java.awt.*;

class TextPanel extends JPanel {

    // Fields
    private JTextArea textArea;

    TextPanel() {
        // Create TextArea
        textArea = new JTextArea();

        // Set Layout
        setLayout(new BorderLayout());

        // Put textArea in a JScrollPane
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    // Add text to TextArea
    void appendText(String text) {
        textArea.append(text);
    }
}

