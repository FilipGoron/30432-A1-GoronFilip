package presentation;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class ReportsWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea reports;
	private JPanel content;
	private JScrollPane editorScrollPane;

	public ReportsWindow(ArrayList<String> reportsText) {

		reports = new JTextArea();

		for (String line : reportsText) {
			reports.append(line);
			reports.append("\n");
		}
		
		reports.setEditable(false);

		editorScrollPane = new JScrollPane(reports);
		editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setPreferredSize(new Dimension(250, 145));
		editorScrollPane.setMinimumSize(new Dimension(10, 10));

		content = new JPanel();
		content.setVisible(true);
		content.setLayout(new GridLayout(1, 1));
		content.add(editorScrollPane);

		this.setVisible(true);
		this.setSize(800, 350);
		this.setLocation(500, 500);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.add(content);

	}

}
