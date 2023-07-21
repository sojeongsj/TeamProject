package Main;



import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Admin.adminLogin;

public class firstScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	JButton b1,b2,b3,b4;
	
	public firstScreen() {
		//b1 = new JButton("<html><body><center>상영 중<br> <br>영화</center></body></html>");
		b1 = new JButton("");
		b1.setIcon(new ImageIcon("d:/image/buyticket.png"));
		b1.setBounds(81, 622, 150, 300);
		
		b2 = new JButton("");
		b2.setIcon(new ImageIcon("d:/image/ticket.png"));
		b2.setBounds(318, 622, 150, 300);
		
		b3 = new JButton("음식 주문");
		b3.setIcon(new ImageIcon("d:/image/buypopcorn.png"));
		b3.setBounds(555, 622, 150, 300);
		
		b4 = new JButton("관리자모드");
		b4.setIcon(new ImageIcon("d:/image/admin.png"));
		b4.setBounds(12, 10, 120, 50);
		
		ImageIcon img = new ImageIcon("d:/image/firstscreen.png");
//		Image imgg = img.getImage();
//		Image changeImg = imgg.getScaledInstance(300, 300, DO_NOTHING_ON_CLOSE);
//		img = new ImageIcon(changeImg);
		JLabel imageLabel = new JLabel(img);
		imageLabel.setIcon(img);
		imageLabel.setBounds(0, 0, 800, 1000);
		

		
		getContentPane().add(b1);getContentPane().add(b2);getContentPane().add(b3);getContentPane().add(b4);
		getContentPane().add(imageLabel);
		
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovieRe();
				setVisible(false);
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new KeypadGUI();
				setVisible(false);
			}
		});
		
		b3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new FoodUiNew();
				setVisible(false);
			}
		});
		
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new adminLogin(firstScreen.this);
			}
		});

		
		getContentPane().setLayout(null);
		setSize(800, 1000);

		setResizable(false);
		setVisible(true);

	
		
	
		getContentPane().setLayout(null);		
		setSize(800,1000);       
										
		setResizable(false);	
		setVisible(true);		
		
	}
	
	
	public static void main(String[] args) {
		new firstScreen();
	}


}
