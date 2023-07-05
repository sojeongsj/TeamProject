package pack_CGV_Admin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CGVadmin extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTextField MovieTF, CustomTF, FoodTF;
	JButton total_btn, Movie_btn, Custom_btn, Food_btn, total_btn_back, Movie_back_btn, Custom_btn_back, Food_btn_back;
	JLabel bg;
	JPanel MoviePanel, TotalPanel, CustomPanel, FoodPanel;

	public CGVadmin() {

		total_btn = new JButton("매출 현황");
		total_btn.setBounds(15, 350, 100, 100);

		Movie_btn = new JButton("영화정보관리");
		Movie_btn.setBounds(125, 350, 110, 100);

		Movie_back_btn = new JButton("돌아가기");
		Movie_back_btn.setBounds(370, 390, 95, 50);

		total_btn_back = new JButton("돌아가기");
		total_btn_back.setBounds(370, 390, 95, 50);

		Custom_btn_back = new JButton("돌아가기");
		Custom_btn_back.setBounds(370, 390, 95, 50);

		Food_btn_back = new JButton("돌아가기");
		Food_btn_back.setBounds(370, 390, 95, 50);

		Custom_btn = new JButton("회원정보관리");
		Custom_btn.setBounds(245, 350, 110, 100);

		Food_btn = new JButton("상품정보수정");
		Food_btn.setBounds(365, 350, 110, 100);

		bg = new JLabel();

		ImageIcon icon = new ImageIcon(CGVadmin.class.getResource("/image/CGV_Admin_ICon.png"));
		bg.setIcon(icon);
		getContentPane().add(bg);
		setVisible(true);
		bg.setHorizontalAlignment(SwingConstants.CENTER);
		bg.setBounds(75, 10, 340, 335);
		add(total_btn);
		add(Movie_btn);
		add(Custom_btn);
		add(Food_btn);

		total_btn.addActionListener(this);
		Movie_btn.addActionListener(this);
		Custom_btn.addActionListener(this);
		Food_btn.addActionListener(this);
		
		total_btn_back.addActionListener(this);
		Movie_back_btn.addActionListener(this);
		Custom_btn_back.addActionListener(this);
		Food_btn_back.addActionListener(this);

		setTitle("관리자모드");
		setLayout(null);
		setSize(500, 500);
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
		
	

	private void showMainPanel() {
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
	}

	private void showFoodPanel() {
		if (FoodPanel == null) {
			FoodPanel = new JPanel();
			FoodPanel.setBounds(5, 5, 480, 480);
			FoodPanel.setBackground(Color.WHITE);
			FoodPanel.setLayout(null);
			getContentPane().add(FoodPanel);
			getContentPane().add(Food_btn_back);
		} else {
		}
		total_btn.setVisible(false);
		Movie_btn.setVisible(false);
		Custom_btn.setVisible(false);
		Food_btn.setVisible(false);
		FoodPanel.setVisible(true);
		bg.setVisible(false);
		
	}

	private void showCustomPanel() {
		if (CustomPanel == null) {
			CustomPanel = new JPanel();
			CustomPanel.setBounds(5, 5, 480, 480);
			CustomPanel.setBackground(Color.WHITE);
			CustomPanel.setLayout(null);
			getContentPane().add(CustomPanel);
			getContentPane().add(Custom_btn_back);
		} else {
			CustomPanel.setVisible(true);
		}

		CustomPanel.setVisible(true);
		Custom_btn_back.setVisible(true);
		total_btn.setVisible(false);
		Movie_btn.setVisible(false);
		Custom_btn.setVisible(false);
		Food_btn.setVisible(false);
		bg.setVisible(false);
	}

	private void showMoviePanel() {
		if (MoviePanel == null) {
			MoviePanel = new JPanel();
			MoviePanel.setBounds(5, 5, 480, 480);
			MoviePanel.setBackground(Color.WHITE);
			MoviePanel.setLayout(null);
			getContentPane().add(MoviePanel);
			getContentPane().add(Movie_back_btn);
		} else {
			MoviePanel.setVisible(true);
		}
		MoviePanel.setVisible(true);
		total_btn.setVisible(false);
		Movie_btn.setVisible(false);
		Custom_btn.setVisible(false);
		Food_btn.setVisible(false);
		bg.setVisible(false);
	}

	private void showTotalPanel() {
		if (TotalPanel == null) {
			TotalPanel = new JPanel();
			TotalPanel.setBounds(5, 5, 480, 480);
			TotalPanel.setBackground(Color.WHITE);
			TotalPanel.setLayout(null);
			getContentPane().add(TotalPanel);
			getContentPane().add(total_btn_back);
		} else {
			TotalPanel.setVisible(true);
		}
		total_btn.setVisible(false);
		Movie_btn.setVisible(false);
		Custom_btn.setVisible(false);
		Food_btn.setVisible(false);
		bg.setVisible(false);
		TotalPanel.setVisible(true);

	}

	public void actionPerformed2(ActionEvent e) {
		if (e.getSource() == total_btn_back) {
			TotalPanel.setVisible(false);
		} else if (e.getSource() == Movie_btn) {
			Movie_btn.setComponentPopupMenu(null);
			showMoviePanel(); // 정상동작
		} else if (e.getSource() == Custom_btn) {
			Custom_btn.setComponentPopupMenu(null);
			showCustomPanel(); // 정상동작
		} else if (e.getSource() == Food_btn) {
			Food_btn.setComponentPopupMenu(null);
			showFoodPanel(); // 정상동작
		}
	}

	public static void main(String[] args) {
		new CGVadmin();
	}
}