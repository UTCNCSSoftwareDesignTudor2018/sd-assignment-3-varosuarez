package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import communication.ServerConnection;
import ro.tuc.dsrl.ds.handson.assig.two.rpc.Naming;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class clientWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frmRpcApplication;
	private JTextField txYear;
	private JTextField txSize;
	private JTextField txPrice;
	private JTextField txResult;
	
	/*private ITaxService taxService = null;
	private IPriceService priceService = null;*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//new Server(8889);
					clientWindow window = new clientWindow();
					window.frmRpcApplication.setVisible(true);
					
				} catch (Exception e) {
					try {
						ServerConnection.getInstance().closeAll();
					} catch (IOException e1) {
						//e1.printStackTrace();
					}
					//e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public clientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRpcApplication = new JFrame();
		frmRpcApplication.setTitle("RPC application");
		frmRpcApplication.setBounds(100, 100, 450, 300);
		frmRpcApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRpcApplication.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnContainer = new JPanel();
		frmRpcApplication.getContentPane().add(pnContainer);
		pnContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth1 = new JPanel();
		pnContainer.add(pnNorth1, BorderLayout.NORTH);
		
		JLabel lblYear = new JLabel("Year:");
		pnNorth1.add(lblYear);
		
		txYear = new JTextField();
		pnNorth1.add(txYear);
		txYear.setColumns(10);
		
		JPanel pnCenter = new JPanel();
		pnContainer.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel pnCenterNorth = new JPanel();
		pnCenter.add(pnCenterNorth, BorderLayout.NORTH);
		pnCenterNorth.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth2 = new JPanel();
		pnCenterNorth.add(pnNorth2);
		
		JLabel lblSize = new JLabel("Engine Size:");
		pnNorth2.add(lblSize);
		
		txSize = new JTextField();
		pnNorth2.add(txSize);
		txSize.setColumns(10);
		
		JPanel pnCenter2 = new JPanel();
		pnCenter.add(pnCenter2, BorderLayout.CENTER);
		pnCenter2.setLayout(new BorderLayout(0, 0));
		
		JPanel pnNorth3 = new JPanel();
		pnCenter2.add(pnNorth3, BorderLayout.NORTH);
		pnNorth3.setLayout(new BorderLayout(0, 0));
		
		JPanel pnPrice = new JPanel();
		pnNorth3.add(pnPrice);
		
		JLabel lbPrice = new JLabel("Price:");
		pnPrice.add(lbPrice);
		
		txPrice = new JTextField();
		pnPrice.add(txPrice);
		txPrice.setText("");
		txPrice.setColumns(10);
		
		JPanel pnSouth = new JPanel();
		pnNorth3.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btCompute = new JButton("Compute Tax");
		btCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String year = txYear.getText();
				String size = txSize.getText();
				String price = txPrice.getText();
				
				if(year.equals("") || size.equals("") || price.equals("")){
					JOptionPane.showMessageDialog(null, "fields cannot be empty!.");
				}else{
					/*try{
						taxService = Naming.lookup(ITaxService.class,
								ServerConnection.getInstance());
						
						Double tax = taxService.computeTax(new Car(Integer.parseInt(year), 
								Integer.parseInt(size),Double.parseDouble(price)));
						txResult.setText(String.valueOf(tax));
						
						
					}*/
					/*catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error computing the tax!.");
					}*/
				}
				/*try {
					ServerConnection.getInstance().closeAll();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		pnSouth.add(btCompute);
		
		JButton btPrice = new JButton("Compute Price");
		btPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				String year = txYear.getText();
				String size = txSize.getText();
				String price = txPrice.getText();
				
				if(year.equals("") || size.equals("") || price.equals("")){
					JOptionPane.showMessageDialog(null, "fields cannot be empty!.");
				}else{
					/*try{
						priceService = Naming.lookup(IPriceService.class,
								ServerConnection.getInstance());
						
						Double tax = priceService.computePrice(new Car(Integer.parseInt(year), 
								Integer.parseInt(size),Double.parseDouble(price)));
						txResult.setText(String.valueOf(tax));
					}*/
					/*catch(Exception e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error computing the price!.");
					}*/
				}
				/*try {
					ServerConnection.getInstance().closeAll();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		});
		pnSouth.add(btPrice);
		
		JPanel panel_1 = new JPanel();
		pnCenter2.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		JLabel lblResult = new JLabel("Result:");
		panel_2.add(lblResult);
		
		txResult = new JTextField();
		panel_2.add(txResult);
		txResult.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JButton btExit = new JButton("Exit");
		btExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ServerConnection.getInstance().closeAll();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);
				dispose();
				System.exit(0);
				
			}
		});
		panel_3.add(btExit);
		
		JPanel pnTitle = new JPanel();
		frmRpcApplication.getContentPane().add(pnTitle, BorderLayout.NORTH);
		pnTitle.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		pnTitle.add(panel, BorderLayout.SOUTH);
		
		JLabel lbTitle = new JLabel("RPC Application");
		lbTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 45));
		panel.add(lbTitle);
		
		
	}

}
