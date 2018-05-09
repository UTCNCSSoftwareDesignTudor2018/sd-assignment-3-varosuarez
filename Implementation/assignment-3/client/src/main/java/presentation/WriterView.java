package presentation;

import javax.swing.JDialog;
import javax.swing.JFrame;

import common.entities.Article;
import common.entities.Writer;
import common.repositories.IArticleDao;
import common.repositories.IWriterDao;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class WriterView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;

	@SuppressWarnings("unused")
	private IWriterDao wD;
	@SuppressWarnings("unused")
	private IArticleDao aD;
	@SuppressWarnings("unused")
	private MainView mainView;
	@SuppressWarnings("unused")
	private Writer w;
	private JTextField txTitle;
	private JTextField txAbstract;
	private JTextField txBody;
	private JTextField txTitleDelete;
	private JTextField txBodyUpdate;
	private JTextField txTitleUpdate;

	public WriterView(MainView mainView, IWriterDao wD, IArticleDao aD, Writer w) {
		this.setSize(600, 300);
		initialize();
		this.wD =wD;
		this.aD = aD;
		this.mainView = mainView;
		this.w = w;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lbTitle = new JLabel("Writer View");
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel.add(lbTitle);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_2.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lbtitleaddArtcile = new JLabel("Add Article Section:");
		lbtitleaddArtcile.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lbtitleaddArtcile, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lbTitleArticle = new JLabel("Title: ");
		panel_5.add(lbTitleArticle);
		
		txTitle = new JTextField();
		panel_5.add(txTitle);
		txTitle.setColumns(10);
		
		JLabel lbAbstract = new JLabel("Abstract");
		panel_5.add(lbAbstract);
		
		txAbstract = new JTextField();
		panel_5.add(txAbstract);
		txAbstract.setColumns(10);
		
		JLabel lbBody = new JLabel("Body: ");
		panel_5.add(lbBody);
		
		txBody = new JTextField();
		panel_5.add(txBody);
		txBody.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9, BorderLayout.SOUTH);
		
		JButton btAddArticle = new JButton("Add Article");
		btAddArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String title = txTitle.getText();
				String abstractHeader = txAbstract.getText();
				String body = txBody.getText();
				boolean alreadyExistArticle = false;
				try {
					for(Article a: aD.findAll(true)){
						if(a.getTitle().equals(title)){
							alreadyExistArticle=true;
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(title.equals("") || abstractHeader.equals("") || body.equals("")){
					JOptionPane.showMessageDialog(null, "Error! fields in add section cannot be empty!");
				}
				else if(alreadyExistArticle){
					JOptionPane.showMessageDialog(null, "Error! article with that title already exist!");
				}else{
					try {
						aD.addArticle(new Article(title, abstractHeader,w, body));
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Error while adding article");
						e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Error while adding article");
						e.printStackTrace();
					}
				}
				
			}
		});
		panel_9.add(btAddArticle);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lbDeleteArticle = new JLabel("DeleteArticle: ");
		lbDeleteArticle.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_7.add(lbDeleteArticle);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.NORTH);
		
		JLabel lbTitleDelete = new JLabel("Title: ");
		panel_10.add(lbTitleDelete);
		
		txTitleDelete = new JTextField();
		panel_10.add(txTitleDelete);
		txTitleDelete.setColumns(10);
		
		JButton btDelete = new JButton("Delete");
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String title = txTitleDelete.getText();
				boolean alreadyExistArticle = false;
				try {
					for(Article a: aD.findAll(true)){
						if(a.getTitle().equals(title)){
							alreadyExistArticle=true;
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(title.equals("")){
					JOptionPane.showMessageDialog(null, "Error! field title cannot be empty!");
				}
				else if(!alreadyExistArticle){
					JOptionPane.showMessageDialog(null, "Error! Article with that Title does not exist!");
				}
				else{
					try {
						aD.deleteArticleByTitle(title);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Error deleting article");
						e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null,  "Error deleting article");
						e.printStackTrace();
					}
				}
			}
		});
		panel_10.add(btDelete);
		
		JPanel panel_11 = new JPanel();
		panel_8.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_11.add(panel_12, BorderLayout.NORTH);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JLabel lbtitleUpdate = new JLabel("Update Section");
		lbtitleUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_12.add(lbtitleUpdate);
		
		JPanel panel_14 = new JPanel();
		panel_11.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_14.add(panel_13, BorderLayout.NORTH);
		
		JLabel lbtitleupdate2 = new JLabel("Title: ");
		panel_13.add(lbtitleupdate2);
		
		txTitleUpdate = new JTextField();
		txTitleUpdate.setColumns(10);
		panel_13.add(txTitleUpdate);
		
		JLabel lbBodyUpdate = new JLabel("Body: ");
		panel_13.add(lbBodyUpdate);
		
		txBodyUpdate = new JTextField();
		txBodyUpdate.setColumns(10);
		panel_13.add(txBodyUpdate);
		
		JButton btUpdate = new JButton("Update");
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String body = txBodyUpdate.getText();
				String title = txTitleUpdate.getText();
				boolean alreadyExistArticle = false;
				try {
					for(Article a: aD.findAll(true)){
						if(a.getTitle().equals(title)){
							alreadyExistArticle=true;
							break;
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(body.equals("") || title.equals("")){
					JOptionPane.showMessageDialog(null, "Error! field body cannot be empty!");
				}
				else if(!alreadyExistArticle){
					JOptionPane.showMessageDialog(null, "Error! article with that title does not exist");
				}
				else{
					try {
						aD.updateBody(title, body);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Error while updating");
						e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Error while updating");
						e.printStackTrace();
					}
				}
			}
		});
		panel_13.add(btUpdate);
		
		JPanel panel_15 = new JPanel();
		panel_14.add(panel_15, BorderLayout.SOUTH);
		
		JButton btLogOut = new JButton("Log Out");
		btLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel_15.add(btLogOut);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
