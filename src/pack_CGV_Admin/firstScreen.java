package pack_CGV_Admin;



import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class firstScreen extends JFrame{
	private static final long serialVersionUID = 1L;
	JButton b1,b2,b3,b4;
	
	public firstScreen() {
		//b1 = new JButton("<html><body><center>상영 중<br> <br>영화</center></body></html>");
		b1 = new JButton("");
		b1.setIcon(new ImageIcon("d:/image/1.jpg"));
		b1.setBounds(40, 400, 100, 200);
		
		b2 = new JButton("<html><body><center>영화표<br> <br>출력</center></body></html>");
		b2.setBounds(190, 400, 100, 200);
		
		b3 = new JButton("음식 주문");
		b3.setBounds(340, 400, 100, 200);
		
		b4 = new JButton("관리자모드");
		b4.setBounds(10, 20, 100, 30);
		
		ImageIcon img = new ImageIcon("d:/image/11.jpg");
		Image imgg = img.getImage();
		Image changeImg = imgg.getScaledInstance(300, 300, DO_NOTHING_ON_CLOSE);
		img = new ImageIcon(changeImg);
		JLabel imageLabel = new JLabel(img);
		imageLabel.setIcon(img);
		imageLabel.setBounds(90, 70, 300, 300);
		

		
		add(b1);add(b2);add(b3);add(b4);
		add(imageLabel);
		
		Font font = new Font("나눔바른고딕 보통", Font.PLAIN, 13);
		Font font1 = new Font("나눔바른고딕 보통", Font.PLAIN, 10);
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new KeypadGUI();
				setVisible(false);
			}
		});
		
		
		b1.setFont(font);
		b2.setFont(font);
		b3.setFont(font);
		b4.setFont(font1);
		
		
		b4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CGVadmin();
			}
		});
			
		
		setLayout(null);		
		setSize(800,1000);       
										
		setResizable(false);	
		setVisible(true);		
		
	}
	
	
	public static void main(String[] args) {
		new firstScreen();
	}


}
