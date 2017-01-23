package com;


import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private PublicationEdit editFrame;
	/**
	 * Create the frame.
	 */
	public PublicationFrame() {
		contentPane = new JPanel();

		contentPane.setLayout(new MigLayout("", "[]", "[][][]"));
		
		
		editFrame = new PublicationEdit();
		contentPane.add(editFrame.getContentPane(), "cell 0 2");
		
		
		table = new PublicationTable().getTable();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(rowSorter);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(table.getSelectedRow()==-1){
					editFrame.getSpinnerId().setValue(1);
				}
				else{
					Object i =  table.getValueAt(table.getSelectedRow(), 0);
					editFrame.getSpinnerId().setValue(i);
				}
	        }
	    });
		
		contentPane.add(new JScrollPane(table), "cell 0 0");
		


		
		
		JLabel lblNewLabel = new JLabel("Wyszukaj publikacje:");
		contentPane.add(lblNewLabel, "cell 0 1,alignx left");
		
		

		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener(){
			
            public void insertUpdate(DocumentEvent e) {
                String text = textField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                	
                	if(!text.contains(" ")){
                		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                	}
                	else{
                		String[] keywords = text.split(" ");
                		List<RowFilter<Object,Object>> rfs =  new ArrayList<RowFilter<Object,Object>>();
                		for(String keyword : keywords) {
                			rfs.add(RowFilter.regexFilter("(?i)" + keyword, 0, 1, 2, 3, 4, 5));
                		}
                		RowFilter<TableModel, Object> rf = RowFilter.andFilter(rfs);
                		rowSorter.setRowFilter(rf);
                	}
                }
                
                table.setRowSorter(rowSorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = textField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                	if(!text.contains(" ")){
                		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                	}
                	else{
                		String[] keywords = text.split(" ");
                		List<RowFilter<Object,Object>> rfs = new ArrayList<RowFilter<Object,Object>>();
                		for(String keyword : keywords) {
                			rfs.add(RowFilter.regexFilter("(?i)" + keyword, 0, 1, 2, 3, 4, 5));
                		}
                		RowFilter<TableModel, Object> rf = RowFilter.andFilter(rfs);
                		rowSorter.setRowFilter(rf);
                	}
                }
                
                table.setRowSorter(rowSorter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    
		contentPane.add(textField, "cell 0 1,growx");
		textField.setColumns(10);
		
		setContentPane(contentPane);
		
	}

}
