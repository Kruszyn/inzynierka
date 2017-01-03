package gui;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class MenuBar extends JFrame{

	private JMenuBar bar;
	
	public MenuBar(JFrame frame){
		createMenuBar(frame);
	}
	
	private void createMenuBar(JFrame frame){
		
		bar = new JMenuBar();
		JMenu display = new JMenu("Wyœwietl bazê danych");
		
		JMenuItem menuItem1 = new JMenuItem("Publikacje");
		menuItem1.addActionListener((ActionEvent event) -> {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new JScrollPane(new DatabaseTable(5).getTable()));
			frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
		});
		
        JMenuItem menuItem2 = new JMenuItem("Cytowania");
        menuItem2.addActionListener((ActionEvent event) -> {
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(new JScrollPane(new DatabaseTable(1).getTable()));
        	frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
        });
        
        JMenuItem menuItem3 = new JMenuItem("Artyku³y");
        menuItem3.addActionListener((ActionEvent event) -> {
        	frame.getContentPane().removeAll();
        	frame.getContentPane().add(new JScrollPane(new DatabaseTable(0).getTable()));
        	frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
        });
        
        
        display.add(menuItem1);
        display.add(menuItem2);
        display.add(menuItem3);
        bar.add(display);
	}
	
	public JMenuBar getBar(){
		return bar;
	}

}
