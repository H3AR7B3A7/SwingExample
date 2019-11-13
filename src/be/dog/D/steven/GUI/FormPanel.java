package be.dog.D.steven.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;

class FormPanel extends JPanel {

    // Fields
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private transient FormListener formListener;
    private JList<AgeCategory> ageList;
    private JComboBox<String> empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    FormPanel(){
        // Change PreferredSize
        Dimension dim = getPreferredSize(); // Fill dim with all PreferredSizes from Layout
        dim.width = 250; // Change width in dim
        setPreferredSize(dim); // set dim as new PreferredSizes

        // Creating JObjects
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);

        ageList = new JList<>();
        empCombo = new JComboBox<>();

        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");

        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");

        okBtn = new JButton("OK");

        // Set up Mnemonics
        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);
        occupationLabel.setDisplayedMnemonic(KeyEvent.VK_C);
        occupationLabel.setLabelFor((occupationField));


        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        // Grouping RadioButtons
        genderGroup = new ButtonGroup();

        // Make sure 1 of group is selected
        maleRadio.setSelected(true);

        // Set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        //Set tax ID to be greyed out
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        // Create ActionListener for CheckBox
        citizenCheck.addActionListener(actionEvent -> {
            boolean isTicked = citizenCheck.isSelected();
            taxLabel.setEnabled(isTicked);
            taxField.setEnabled(isTicked);
        });

        // Set up list box, holding 'inner Class' AgeCategory objects
        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();
        ageModel.addElement(new AgeCategory(0,"Under 18"));
        ageModel.addElement(new AgeCategory(1,"18 to 65"));
        ageModel.addElement(new AgeCategory(2,"Over 65"));
        ageList.setModel(ageModel);

        // Modifiers to ageList
        ageList.setPreferredSize(new Dimension(115,60));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Set up combo box, holding Strings
        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);

        // OK Button ActionListener holding ActionPerformed Method
        okBtn.addActionListener(actionEvent -> {
            // Invoking Methods to get required data
            String name = nameField.getText();
            String occupation = occupationField.getText();
            AgeCategory ageCat = ageList.getSelectedValue();
            String empCat = (String)empCombo.getSelectedItem();
            String taxId = taxField.getText();
            boolean euCitizen = citizenCheck.isSelected();
            String gender = genderGroup.getSelection().getActionCommand();

            // Creating a new type of 'Event' from class FormEvent
            FormEvent ev = new FormEvent(this,name,occupation,ageCat.getId(),
                    empCat,taxId,euCitizen,gender);

            // Pass FormEvent to FormListener
            if(formListener !=null){
                formListener.formEventOccurred(ev);
            }

        });

        // Creating an outer and inner border for FormPanel
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Cleaner to set up components with void method
        layoutComponents();
    }

    // Setting up layout components
    private void layoutComponents(){
        // Choosing layout
        setLayout(new GridBagLayout());
        // Creating constraints
        GridBagConstraints gc = new GridBagConstraints();

        //Setting constraints
        gc.weightx=1; // Possibly here if rarely changed
        gc.weighty=0.1;
        Insets withInset = new Insets(0,0,0,5);         // more efficient
        Insets withoutInset = new Insets(0, 0, 0, 0);   // when repeated a lot

        ///////////FIRST ROW//////////
        gc.gridy=0;

        gc.gridx=0;
        gc.fill=GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = withInset;
        add(nameLabel, gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = withoutInset;
        add(nameField, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = withInset;
        add(occupationLabel, gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = withoutInset;
        add(occupationField, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;
        gc.weighty=0.2;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = withInset;
        add(new JLabel("Age: "), gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = withInset;
        add(new JLabel("Employment: "), gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = withInset;
        add(new JLabel("EU Citizen: "), gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citizenCheck, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = withInset;
        add(taxLabel, gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxField, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;
        gc.weighty=0.05;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = withInset;
        add(new JLabel("Gender: "), gc);

        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleRadio, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;

        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadio, gc);

        ///////////NEXT ROW//////////
        gc.gridy++;
        gc.weighty=2.0;

        gc.gridx=1; // Doesn't need repeating, but for readability
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);
    }

    // Setter for FormListener
    void setFormListener(FormListener listener){
        this.formListener = listener;
    }
}

// Inner class AgeCategory

class AgeCategory {

    // Fields
    private int id;
    private String text;

    // Constructor
    AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    // toString Method
    @Override
    public String toString() {
        return text;
    }

    // Getter
    int getId(){
        return id;
    }
}