package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

public class MenuBar{

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
			frame.setLayout(new BorderLayout());
			frame.getContentPane().add(new JScrollPane(new DatabaseTable(5).getTable()));
			frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
		});
        display.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("Cytowania");
        menuItem2.addActionListener((ActionEvent event) -> {
        	frame.getContentPane().removeAll();
			frame.setLayout(new BorderLayout());
        	frame.getContentPane().add(new JScrollPane(new DatabaseTable(1).getTable()));
        	frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
        });
        display.add(menuItem2);

        JMenuItem menuItem3 = new JMenuItem("Artyku³y");
        menuItem3.addActionListener((ActionEvent event) -> {
        	frame.getContentPane().removeAll();
			frame.setLayout(new BorderLayout());
        	frame.getContentPane().add(new JScrollPane(new DatabaseTable(0).getTable()));
        	frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
        });
        display.add(menuItem3);

        JMenu add = new JMenu("Dodaj");
        
		JMenuItem menuItem21 = new JMenuItem("Publikacje/Artyku³ obcy");
		menuItem21.addActionListener((ActionEvent event) -> {
			frame.getContentPane().removeAll();
			frame.setContentPane(new AddFrame().getContentPane());
			frame.getContentPane().revalidate(); 
			frame.getContentPane().repaint();
		});
        add.add(menuItem21);

        //TODO ekran dodawania cytowania
		JMenuItem menuItem22 = new JMenuItem("Cytowanie");
		menuItem22.addActionListener((ActionEvent event) -> {
			frame.getContentPane().removeAll();
			//frame.getContentPane().add(new JScrollPane(new DatabaseTable(5).getTable()));
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
