package presentation;

import javax.swing.JDialog;
import javax.swing.JFrame;

import common.entities.Article;
import common.repositories.IArticleDao;
import common.repositories.IWriterDao;

import javax.swing.JPanel;
import java.awt.BorderLayout;


import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReaderView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	DefaultTableModel model;
	@SuppressWarnings("unused")
	private IWriterDao wD;
	private IArticleDao aD;
	@SuppressWarnings("unused")
	private MainView mainView;
	private JTable tableArtciles;
	
	public ReaderView(MainView mainView, IWriterDao wD, IArticleDao aD) {
		this.setSize(500, 500);
		initialize();
		this.wD =wD;
		this.aD = aD;
		this.mainView = mainView;
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel("Reader View");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lbTitle);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lbArticles = new JLabel("List of Articles:");
		panel_2.add(lbArticles, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);

		
		model = new DefaultTableModel(); 
		tableArtciles = new JTable(model);
		String [] header ={"Number","Title","Abstract","Body","Author"};
		model.setColumnIdentifiers(header);
		List<Article> as = new ArrayList<>();
		try {
			as = aD.findAll(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(as==null){
			JOptionPane.showMessageDialog(null, "Error! not found articles.");
		}
		else
		{
			for (int i = 0; i < as.size(); i++)
			{
			       model.addRow(new Object[] { i+1, as.get(i).getTitle(), as.get(i).getAbstractHeader(),
			    		   as.get(i).getBody(), as.get(i).getAuthor().getName()});
			}
		}
		panel_3.add(tableArtciles);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		
		JButton btLogOut = new JButton("Log Out");
		btLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		panel_4.add(btLogOut);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void update(){
		List<Article> as = new ArrayList<>();
		try {
			as = aD.findAll(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(as==null){
			JOptionPane.showMessageDialog(null, "Error! not found articles.");
		}
		else
		{
			for (int i = 0; i < as.size(); i++)
			{
			       model.addRow(new Object[] { i+1, as.get(i).getTitle(), as.get(i).getAbstractHeader(),
			    		   as.get(i).getBody(), as.get(i).getAuthor().getName()});
			}
		}
	}
}
