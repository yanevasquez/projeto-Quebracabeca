/**
 * IFPB - TSI/POO
 * Prof. Fausto Ayres
 * 
 * Aplicação swing do Quebra-Cabeca
 * 
 */

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import java.awt.Font;

public class TesteQuebraCabecaSwing {

	private JFrame frame;
	private JButton button;
	private QuebraCabeca jogo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteQuebraCabecaSwing window = new TesteQuebraCabecaSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TesteQuebraCabecaSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setTitle("Quebra Cabeca");
		this.frame.setBounds(100, 100, 719, 510);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("3x3");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setForeground(SystemColor.textHighlight);
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("4x4");
		rdbtnNewRadioButton_1.setForeground(SystemColor.textHighlight);
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("5x5");
		rdbtnNewRadioButton_2.setForeground(SystemColor.textHighlight);
		rdbtnNewRadioButton_2.setBackground(SystemColor.menu);
		
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
				rdbtnNewRadioButton_2.setSelected(false);
			}
		});
		rdbtnNewRadioButton.setBounds(257, 21, 59, 23);
		frame.getContentPane().add(rdbtnNewRadioButton);
		

		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(false);
				rdbtnNewRadioButton_2.setSelected(false);
			}
		});
		rdbtnNewRadioButton_1.setBounds(318, 21, 51, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_1);
		

		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton_1.setSelected(false);
				rdbtnNewRadioButton.setSelected(false);
			}
		});
		rdbtnNewRadioButton_2.setBounds(371, 21, 53, 23);
		frame.getContentPane().add(rdbtnNewRadioButton_2);
		
		
	
		this.button = new JButton("Iniciar");
		button.setBackground(SystemColor.text);
		button.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		button.setForeground(SystemColor.textHighlight);
		this.button.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent arg0) {
				int tipo = 0;
				int di = 0;
				String nome=null;
				if(rdbtnNewRadioButton.isSelected()==true) {

					tipo=1;
					di=3;
					nome="/imagens3x3/";
				}
				
				else if(rdbtnNewRadioButton_1.isSelected()==true){

					tipo=1;
					di=4;
					nome="/imagens4x4/";	
				}
				
				else if(rdbtnNewRadioButton_2.isSelected()==true) {

					tipo=1;
					di = 5;
					nome="/imagens5x5/";
				}
				
				if (tipo != 0) {
					try {
						TesteQuebraCabecaSwing.this.frame.getContentPane().remove(TesteQuebraCabecaSwing.this.jogo);
					} catch (Exception e) {}
					try {
						TesteQuebraCabecaSwing.this.jogo = new QuebraCabeca (di,nome);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					TesteQuebraCabecaSwing.this.jogo.setSize(530, 275);
					TesteQuebraCabecaSwing.this.jogo.setLocation(193, 135);
					TesteQuebraCabecaSwing.this.frame.getContentPane().add(TesteQuebraCabecaSwing.this.jogo);
				}
				try {
					TesteQuebraCabecaSwing.this.jogo.iniciar();
				} catch (Exception e) {
					System.out.println("");
					
				}
			}
		});
		this.button.setBounds(134, 20, 100, 23);
		this.frame.getContentPane().add(this.button);
		
	}
}