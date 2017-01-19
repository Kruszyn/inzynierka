package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import javax.swing.JTable;

public class PublicationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7254767999622115205L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public PublicationFrame() {
		contentPane = new JPanel();

		contentPane.setLayout(new MigLayout("", "[][][][][][][grow][grow]", "[grow][]"));
		
		table = new PublicationTable().getTable();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(rowSorter);
		contentPane.add(new JScrollPane(table), "cell 0 0 7 1,alignx center,growy");
		
		panel = new JPanel();
		panel.add(new EditPublication().getContentPane());
		contentPane.add(panel, "cell 7 0 1 2,grow");
		
		
		JLabel lblNewLabel = new JLabel("Wyszukaj publikacje:");
		contentPane.add(lblNewLabel, "cell 0 1 6 1,alignx center");
		
		

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
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    
		contentPane.add(textField, "cell 6 1,growx");
		textField.setColumns(10);
		
		setContentPane(contentPane);
		
	}

}
