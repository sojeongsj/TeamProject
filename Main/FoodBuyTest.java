package Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class FoodBuyTest {
	private JFrame frame;
	private JPanel seatPanel;
	private JTextField tf1;
	private JButton cardpay, cashpay, payment;
	private JLabel la;
	private int payCount = 0; // 신용카드 버튼 클릭 카운트

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FoodBuyTest().buyTestGui(0);
			}
		});
	}

	public void buyTestGui(int moneynaena) {
		// JFrame 생성
		frame = new JFrame("결제 창");

		// 버튼 생성
		cardpay = new JButton(new ImageIcon("d:/image/card.png"));
		cardpay.setBounds(200, 350, 200, 200);
		cardpay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (payCount != 1) { // 현재 이미 선택된 버튼이 아닌 경우에만 처리
		            payCount = 1;
		            cardpay.setIcon(new ImageIcon("d:/image/cardclick.png"));
		            cashpay.setIcon(new ImageIcon("d:/image/cash.png")); // 다른 버튼의 이미지를 원래 이미지로 되돌림
		        }
		    }
		});

		cashpay = new JButton(new ImageIcon("d:/image/cash.png"));
		cashpay.setBounds(400, 350, 200, 200);
		cashpay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (payCount != 2) { // 현재 이미 선택된 버튼이 아닌 경우에만 처리
		            payCount = 2;
		            cashpay.setIcon(new ImageIcon("d:/image/cashclick.png"));
		            cardpay.setIcon(new ImageIcon("d:/image/card.png")); // 다른 버튼의 이미지를 원래 이미지로 되돌림
		        }
		    }
		});

		la = new JLabel(" 가격 " + moneynaena);
		la.setFont(new Font("나눔바른고딕",Font.BOLD,20));
		la.setBounds(350, 50, 150, 150);

		payment = new JButton(new ImageIcon("d:/image/buy.png"));
		payment.setBounds(250, 750, 300, 150);
		payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (payCount == 1) {
					int result = JOptionPane.showConfirmDialog(frame, "신용카드로 결제하겠습니까?", "결제 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						Random r = new Random();
						JOptionPane.showMessageDialog(frame, "결제가 완료되었습니다. 주문번호 : " + (r.nextInt(1000)+100), "결제 완료",
								JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
						new foodlastScreen().createAndShowGUI();
					}
				} else if (payCount == 2) {
					int result = JOptionPane.showConfirmDialog(frame, "현금으로 결제하겠습니까?", "결제 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						//랜덤함수 돌려서 주문번호 아무렇게나 출력해주기
						Random r = new Random();
						JOptionPane.showMessageDialog(frame, "결제가 완료되었습니다. 주문번호 : " + (r.nextInt(1000)+100), "결제 완료",
								JOptionPane.INFORMATION_MESSAGE);
						frame.dispose();
						new foodlastScreen().createAndShowGUI();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "신용카드 또는 현금으로 결제를 진행해주세요.", "결제 오류",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 버튼을 프레임에 추가
		frame.add(cardpay);
		frame.add(cashpay);
		frame.add(payment);
		frame.add(la);

		// 프레임 크기 설정
		frame.setLayout(null);
		frame.setSize(800, 1000);

		// 프레임을 닫을 때 프로그램 종료
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임을 표시
		frame.setVisible(true);
	}
}