package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class MenuBar{

	private JMenuBar bar;
	
	public MenuBar(JFrame frame){
		createMenuBar(frame);
	}
	
	private void createMenuBar(JFrame frame){
		
		bar = new JMenuBar();
		JMenu display = new JMenu("Menu");
		
		JMenuItem menuItem1 = new JMenuItem("Wyœwietl bazy danych");
		menuItem1.addActionListener((ActionEvent event) -> {
			frame.getContentPane().removeAll();
			frame.getContentPane().setLayout(new MigLayout("", "[][]", "[][][]"));
			frame.getContentPane().add(new JScrollPane(new DatabaseTable(5).getTable()), "cell 0 0");
			frame.getContentPane().add(new JScrollPane(new DatabaseTable(1).getTable()), "cell 0 1");
			frame.getContentPane().add(new JScrollPane(new DatabaseTable(0).getTable()), "cell 0 2");
			frame.getContentPane().add(new EditCitationFrame().getContentPane(), "cell 1 1");
			frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
		});
        display.add(menuItem1);

        JMenu add = new JMenu("Dodaj");
        
		JMenuItem menuItem21 = new JMenuItem("Publikacje/Artyku³ obcy");
		menuItem21.addActionListener((ActionEvent event) -> {
			frame.getContentPane().removeAll();
			frame.setContentPane(new AddFrame().getContentPane());
			frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
		});
        add.add(menuItem21);

		JMenuItem menuItem22 = new JMenuItem("Cytowanie");
		menuItem22.addActionListener((ActionEvent event) -> {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new AddCitationFrame().getContentPane());
			frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
		});
        add.add(menuItem22);      
        
        bar.add(display);
        bar.add(add);
	}
	
	public JMenuBar getBar(){
		return bar;
	}

}
