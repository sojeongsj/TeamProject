package pack_CGV_Admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import reservation.ReservationDao;

public class CGVadmin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTextField MovieTF, CustomTF, FoodTF;
	JButton total_btn, Movie_btn, Custom_btn, Food_btn, 
			total_btn_back, Movie_back_btn, Custom_btn_back, Food_btn_back,
			Main_back_btn;
	JLabel bg;
	JPanel MoviePanel, TotalPanel, CustomPanel, FoodPanel;

	public CGVadmin() {

		total_btn = new JButton("매출 현황");
		total_btn.setBounds(40, 789, 150, 150);

		Movie_btn = new JButton("영화정보관리");
		Movie_btn.setBounds(221, 789, 150, 150);

		Movie_back_btn = new JButton("돌아가기");
		Movie_back_btn.setBounds(650, 800, 100, 80);

		total_btn_back = new JButton("돌아가기");
		total_btn_back.setBounds(650, 800, 100, 80);

		Custom_btn_back = new JButton("돌아가기");
		Custom_btn_back.setBounds(650, 800, 100, 80);

		Food_btn_back = new JButton("돌아가기");
		Food_btn_back.setBounds(650, 800, 100, 80);

		Custom_btn = new JButton("회원정보관리");
		Custom_btn.setBounds(403, 789, 150, 150);

		Food_btn = new JButton("상품정보수정");
		Food_btn.setBounds(588, 789, 150, 150);
		
		Main_back_btn = new JButton("초기화면");
		Main_back_btn.setBounds(645,10,127,50); // 초기 키오스크 화면으로 돌아가는 버튼 (아직 만들지 않음)

		bg = new JLabel();

		ImageIcon icon = new ImageIcon(CGVadmin.class.getResource("/image/CGV_Admin_ICon.jpg"));
		bg.setIcon(icon);
		getContentPane().add(bg);
		setVisible(true);
		bg.setHorizontalAlignment(SwingConstants.CENTER);
		bg.setBackground(Color.white);
		bg.setBounds(22, 52, 730, 714);
		getContentPane().add(total_btn);
		getContentPane().add(Movie_btn);
		getContentPane().add(Custom_btn);
		getContentPane().add(Food_btn);
		getContentPane().add(Main_back_btn);

		total_btn.addActionListener(this);
		Movie_btn.addActionListener(this);
		Custom_btn.addActionListener(this);
		Food_btn.addActionListener(this);
		
		total_btn_back.addActionListener(this);
		Movie_back_btn.addActionListener(this);
		Custom_btn_back.addActionListener(this);
		Food_btn_back.addActionListener(this);

		
		Main_back_btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
			}
		});
		setTitle("관리자모드");
		getContentPane().setLayout(null);
		setSize(800, 1000);
		setResizable(false); // 윈도우 크기조절 불가
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == total_btn) {
			total_btn.setComponentPopupMenu(null);
			showTotalPanel(); // 정상동작
		} else if (e.getSource() == Movie_btn) {
			Movie_btn.setComponentPopupMenu(null);
			showMoviePanel(); // 정상동작
		} else if (e.getSource() == Custom_btn) {
			Custom_btn.setComponentPopupMenu(null);
			showCustomPanel(); // 정상동작
		} else if (e.getSource() == Food_btn) {
			Food_btn.setComponentPopupMenu(null);
			showFoodPanel(); // 정상동작
		} else if (e.getSource() == total_btn_back) {
			showMainPanel();
		} else if (e.getSource() == Movie_back_btn) {
			showMainPanel();
		} else if (e.getSource() == Custom_btn_back) {
			showMainPanel();
		} else if (e.getSource() == Food_btn_back) {
			showMainPanel();
		}
				}
		
	

	public void showMainPanel() {
	    if (TotalPanel != null) {
	        TotalPanel.setVisible(false);
	    }
	    if (MoviePanel != null) {
	        MoviePanel.setVisible(false);
	    }
	    if (CustomPanel != null) {
	        CustomPanel.setVisible(false);
	    }
	    if (FoodPanel != null) {
	        FoodPanel.setVisible(false);
	    }
	    total_btn.setVisible(true);
	    Movie_btn.setVisible(true);
	    Custom_btn.setVisible(true);
	    Food_btn.setVisible(true);
	    bg.setVisible(true);
	    total_btn_back.setVisible(false);
	    Movie_back_btn.setVisible(false);
	    Custom_btn_back.setVisible(false);
	    Food_btn_back.setVisible(false);
	    Main_back_btn.setVisible(true); // 돌아가기 버튼 보이게 추가
	    
	    // 버튼들을 다시 보이게 하는 부분 추가
	    total_btn.setVisible(true);
	    Movie_btn.setVisible(true);
	    Custom_btn.setVisible(true);
	    Food_btn.setVisible(true);
	    
	}

	private void showFoodPanel() {
		new Food_Admin_Panel();
		
	}

	private void showCustomPanel() {
		new Custom_Admin_Panel();
		
	}

	private void showMoviePanel() {
	new Movie_Admin_Panel();
	}

	private void showTotalPanel()  {
		new Total_Admin_Panel();
	
	} 

	
	public static void main(String[] args) {
		new CGVadmin();
	}
}