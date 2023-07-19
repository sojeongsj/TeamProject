package Admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class adminLogin extends JDialog {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;

	public adminLogin(Frame parent) {
		super(parent, "관리자 로그인", true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);

		JPanel panel = new JPanel(new GridLayout(3, 2, 0, 20));
		JLabel usernameLabel = new JLabel("관리자 ID:");
		usernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		JLabel passwordLabel = new JLabel("비밀번호:");
		passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("로그인");
		loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));

		panel.add(usernameLabel);
		panel.add(usernameField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		JLabel label = new JLabel();
		label.setBackground(new Color(255, 255, 255));
		panel.add(label);
		panel.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());

				if (isValidCredentials(username, password)) {
					dispose(); // Close the login dialog
					theaterAdmin admin = new theaterAdmin();
				} else {
					JOptionPane.showMessageDialog(adminLogin.this, "잘못된 입력 정보 입니다. ",
							"로그인 오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.setPreferredSize(new Dimension(250, 150));
		setContentPane(panel);
		pack();
	   setVisible(true);
	}

	public adminLogin() {
	}

	private boolean isValidCredentials(String username, String password) {
		return username.equals("admin") && password.equals("1234");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				adminLogin loginDialog = new adminLogin(null);
				loginDialog.setVisible(true);
			}
		});
	}
}