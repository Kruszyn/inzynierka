package com;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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

public class ArticleFrame extends JFrame {

	private static final long serialVersionUID = -5007243679125293987L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private ArticleEdit editFrame;

	public ArticleFrame(){
		contentPane = new JPanel();

		contentPane.setLayout(new MigLayout("", "[]", "[][][][]"));
		
		
		editFrame = new ArticleEdit();
		contentPane.add(editFrame.getContentPane(), "cell 0 2");
		
		
		table = new ArticleTable().getTable();
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(rowSorter);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if(table.getSelectedRow()==-1){
					editFrame.getSpinnerIdArt().setValue(1);
				}
				else{
					Object i =  table.getValueAt(table.getSelectedRow(), 0);
					editFrame.getSpinnerIdArt().setValue(i);
				}

	        }
	    });
		
		contentPane.add(new JScrollPane(table), "cell 0 0");
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("Wyszukaj artyku³y:");
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
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });

		contentPane.add(textField, "cell 0 1,growx");
		textField.setColumns(10);
		
		setContentPane(contentPane);
		
	}

}
