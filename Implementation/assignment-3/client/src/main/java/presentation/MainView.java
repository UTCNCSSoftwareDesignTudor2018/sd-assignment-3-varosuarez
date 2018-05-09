package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import common.entities.Writer;
import common.repositories.IArticleDao;
import common.repositories.IWriterDao;
import communication.ServerConnection;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Naming;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MainView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txUserName;
	private JPasswordField txPassword;
	
	private IWriterDao wD;
	private IArticleDao aD;
	private Writer w= new Writer();
	
	private MainView mainView = this;
	private WriterView writerView;
	private ReaderView readerView;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					/*try {
						ServerConnection.getInstance().closeAll();
					} catch (IOException e1) {
						e1.printStackTrace();
					}*/
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		try {
			wD = Naming.lookup(IWriterDao.class,
					ServerConnection.getInstance());
			aD = Naming.lookup(IArticleDao.class,
					ServerConnection.getInstance());
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lbMainView = new JLabel("MainView");
		lbMainView.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_1.add(lbMainView);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.NORTH);
		
		JLabel lbUserName = new JLabel("Username: ");
		panel_3.add(lbUserName);
		
		txUserName = new JTextField();
		panel_3.add(txUserName);
		txUserName.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		JLabel lbPassword = new JLabel("Password: ");
		panel_4.add(lbPassword);
		
		txPassword = new JPasswordField();
		txPassword.setColumns(11);
		panel_4.add(txPassword);
		
		JPanel panel_5 = new JPanel();
		panel_2.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6);
		
		JButton btLogWriter = new JButton("Log As Writer");
		btLogWriter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = txUserName.getText();
				@SuppressWarnings("deprecation")
				String pass = txPassword.getText();
				
				try {
					w = wD.findByUsernameAndPassword(username, pass);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(w==null){
					JOptionPane.showMessageDialog(null, "Error! not found writer.");
				}
				else{
					writerView  =  new WriterView(mainView, wD, aD, w);
					writerView.setModal(true);
					writerView.setLocationRelativeTo(mainView);
					writerView.setVisible(true);
					writerView.setSize(300,300);
				}
				
			}
		});
		panel_6.add(btLogWriter);
		
		JButton btLogReader = new JButton("Log As Reader");
		btLogReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				readerView = new ReaderView(mainView, wD, aD);
				readerView.setModal(true);
				readerView.setLocationRelativeTo(mainView);
				readerView.setVisible(true);
				readerView.setSize(300,300);
				
			}
		});
		panel_6.add(btLogReader);
		
		JPanel panel_7 = new JPanel();
		frame.getContentPane().add(panel_7, BorderLayout.SOUTH);
		
		JButton btLogOut = new JButton("Log Out");
		btLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServerConnection.getInstance().closeAll();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
				
			}
		});
		panel_7.add(btLogOut);
	}

}
