package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import kioskDAO.ReservationDao;
import kioskDAO.ScreeningDao;
import kioskDTO.ReservationDto;
import kioskDTO.ScreeningDto;
import java.awt.Color;
import java.awt.Font;

public class BuyTest {
	private JFrame frame;
	private JPanel seatPanel;
	private JTextField tf1;
	private JButton cardpay, cashpay, payment;
	private JLabel la;
	private int payCount = 0; // 신용카드 버튼 클릭 카운트
	private boolean paymentClicked = false;
	private boolean receiptGenerated = false;
	private ReservationDto reser;
	private int totalNumberOfAttendees;
	private int moviePrice;
	private String screenTheater;
	private String movieTitle;
	private StringBuilder selectedSeats = new StringBuilder();
	private int screenNo;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BuyTest().buyTestGui();
			}
		});
	}
	
	public BuyTest() {
		
	}
	
	public BuyTest(int totalNumberOfAttendees, int moviePrice,String screenTheater,
			String movieTitle,StringBuilder selectedSeats, int screenNo) {
		this.totalNumberOfAttendees = totalNumberOfAttendees;
		this.moviePrice = moviePrice;
		this.screenTheater = screenTheater;
		this.movieTitle = movieTitle;
		this.selectedSeats = selectedSeats;
		this.screenNo = screenNo;
	}

	public void buyTestGui() {
//	    System.out.println("test : "+screenTheater);
//	    System.out.println("test : "+movieTitle);
//	    System.out.println("test : "+String.valueOf(selectedSeats));
	    
		// JFrame 생성
		frame = new JFrame("결제 창");
		frame.getContentPane().setBackground(Color.WHITE);
		
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

		la = new JLabel(" 가격 : " + totalNumberOfAttendees * moviePrice);
		la.setFont(new Font("나눔바른고딕",Font.BOLD,20));
		la.setBounds(350, 50, 150, 150);

		payment = new JButton(new ImageIcon("d:/image/buy.png"));
		payment.setBounds(250, 750, 300, 150);
		
		ReservationDao rda = ReservationDao.getDao();
		payment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (payCount == 1) {
					int result = JOptionPane.showConfirmDialog(frame, "신용카드로 결제하겠습니까?", "결제 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						paymentClicked = true;
						
						// 여기서 reservation dao에 insert
						ReservationDto rdt = ReservationDto.builder().ReserSeat(String.valueOf(selectedSeats))
								.MovieTitle(movieTitle).ScreenTheater(Integer.parseInt(screenTheater))
								.ScreenNo(screenNo)
								.build();
						try {
							rda.insert(rdt);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						generateReceipt();
					}
				} else if (payCount == 2) {
					int result = JOptionPane.showConfirmDialog(frame, "현금으로 결제하겠습니까?", "결제 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						paymentClicked = true;
						
						// 여기서 reservation dao에 insert
						ReservationDto rdt = ReservationDto.builder().ReserSeat(String.valueOf(selectedSeats))
								.MovieTitle(movieTitle).ScreenTheater(Integer.parseInt(screenTheater))
								.ScreenNo(screenNo).build();
						try {
							rda.insert(rdt);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						generateReceipt();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "신용카드 또는 현금으로 결제를 진행해주세요.", "결제 오류",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// 버튼을 프레임에 추가
		frame.getContentPane().add(cardpay);
		frame.getContentPane().add(cashpay);
		frame.getContentPane().add(payment);
		frame.getContentPane().add(la);

		// 프레임 크기 설정
		frame.getContentPane().setLayout(null);
		frame.setSize(800, 1000);

		// 프레임을 닫을 때 프로그램 종료
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// 프레임을 표시
		frame.setVisible(true);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (receiptGenerated) {
					frame.dispose();
					
					new lastScreen().createAndShowGUI();
				} else {
					int result = JOptionPane.showConfirmDialog(frame, "결제를 하지 않고 종료하시겠습니까?", "종료 확인",
							JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						frame.setVisible(false); // 프레임 숨기기
						new FrameWindow().createAndShowGUI(); // 프레임 창 띄우기
					}
				}
			}
		});
	}

	private void generateReceipt() {
		if (paymentClicked && !receiptGenerated) {
			try {
				ReservationDao reservationDao = ReservationDao.getDao();
				
				String reservationNumber = reservationDao.getLatestReservationNumber();
				
				
				if (reservationNumber != null && !reservationNumber.isEmpty()) {
					ReservationDto reservation = reservationDao.ReservationOne(reservationNumber);
					ScreeningDao screeningDao = new ScreeningDao();
					ScreeningDto screening = screeningDao.search(reservationNumber);
					if (reservation != null && screening != null) {
						Date screenDate = screening.getScreenDate();
						String movieTitle = reservation.getMovieTitle();
						String reserseat = reservation.getReserSeat();

						LocalDateTime currentTime = LocalDateTime.now();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						String currentDateTime = currentTime.format(formatter);

						StringBuilder receiptText = new StringBuilder();
						receiptText.append("상영일자: ").append(screenDate).append("\n");
						receiptText.append("영화제목: ").append(movieTitle).append("\n");
						receiptText.append("상영관 번호: ").append(screenTheater).append("\n");
						receiptText.append("좌석: ").append(reserseat).append("\n");

						JOptionPane.showMessageDialog(frame, receiptText.toString(), "영수증",
								JOptionPane.INFORMATION_MESSAGE);
						receiptGenerated = true;
						frame.dispose();
						new lastScreen().createAndShowGUI();
					} else {
						JOptionPane.showMessageDialog(frame, "예약 정보 또는 상영 정보를 찾을 수 없습니다.", "영수증 오류",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "예약번호를 입력해주세요.", "영수증 오류", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(frame, "예약 정보를 가져오는 중 오류가 발생했습니다.", "영수증 오류", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(frame, "결제를 진행하지 않았거나 이미 영수증이 출력되었습니다.", "영수증 오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class FrameWindow {
		public void createAndShowGUI() {
			JFrame frameWindow = new JFrame("프레임 창");
			frameWindow.setSize(300, 200);
			frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frameWindow.setVisible(true);
		}
	}
}
