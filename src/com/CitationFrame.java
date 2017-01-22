package com;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;

public class CitationFrame extends JFrame {


	private static final long serialVersionUID = -5007243679125293987L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private CitationEdit editFrame;

	public CitationFrame() {
		contentPane = new JPanel();

		contentPane.setLayout(new MigLayout("", "[][]", "[][]"));
		
		
		editFrame = new CitationEdit();
		contentPane.add(editFrame.getContentPane(), "cell 1 0");
		
		
		table = new CitationTable().getTable();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(rowSorter);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent event) {
				Object i =  table.getValueAt(table.getSelectedRow(), 0);
				editFrame.getSpinnerId().setValue(i);
	        }
	    });
		
		contentPane.add(new JScrollPane(table), "cell 0 0");
		


		
		
		JLabel lblNewLabel = new JLabel("Wyszukaj cytowania:");
		contentPane.add(lblNewLabel, "cell 0 1,alignx left");
		
		

		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener(){
			
            public void insertUpdate(DocumentEvent e) {
                String text = textField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                
                table.setRowSorter(rowSorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                	rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
                
                table.setRowSorter(rowSorter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
    
		contentPane.add(textField, "cell 0 1,growx");
		textField.setColumns(10);
		
		setContentPane(contentPane);
		
	}

}
