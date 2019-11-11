package be.dog.D.steven.GUI;

import be.dog.D.steven.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

class TablePanel extends JPanel {

    private PersonTableModel tableModel;
    private JPopupMenu popup;
    private PersonTableListener personTableListener;

    TablePanel(){

        tableModel = new PersonTableModel();
        JTable table = new JTable(tableModel);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Right mouse-button popup
        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete");
        popup.add(removeItem);

        // RMB Listener - select row and show popup
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());

                table.getSelectionModel().setSelectionInterval(row,row);

                if(e.getButton() == MouseEvent.BUTTON3){
                    popup.show(table, e.getX(),e.getY());
                }
            }
        });
        // JMenuItem ActionListener - 'Delete'
        removeItem.addActionListener(actionEvent -> {
            int row = table.getSelectedRow();

            if(personTableListener != null){
                personTableListener.rowDeleted(row);
                tableModel.fireTableRowsDeleted(row,row);
            }
        });
    }

    // Methods
    void setData(List<Person> db){
        tableModel.setData(db);
    }
    void refresh(){ tableModel.fireTableDataChanged(); }
    void setPersonTableListener(PersonTableListener listener){
        this.personTableListener = listener;
    }
}
