package Main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Admin.adminLogin;
import kioskDAO.ReservationseatDAO;
import kioskDAO.ScreenInfoDAO;
import kioskDTO.ScreenInfoDto;



public class CustomerCheckGui extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton plusYouthButton;    // 청소년 증가 버튼
    private JButton plusAdultButton;    // 일반 증가 버튼
    private JButton minusYouthButton;   // 마이너스
    private JButton minusAdultButton;   // 마이너스
    private JButton reserveButton;      // 예약 버튼
    private JButton backButton;         // 뒤로가기 버튼
    private int numberOfYouth;
    private int numberOfAdult;
    private JFrame frame;
    private JPanel seatPanel;
    private JCheckBox[] seatCheckBoxes;
    private JButton paymentButton;
    private JLabel Screen;
    private int totalNumberOfAttendees;
    private int moviePrice;
    private String screenTheater;
    private String movieTitle;
    private StringBuilder selectedSeats = new StringBuilder();
    private int screenNo;

    

   public List<ScreenInfoDto> getScrrenInfo(String title, String selectedTime) {
      ScreenInfoDAO getScreenInfo = new ScreenInfoDAO();
      
       List<ScreenInfoDto> ScreenInfo =  new ArrayList<>();
       try {
          ScreenInfo = getScreenInfo.getScreenInfo(title, selectedTime);
       } catch (SQLException e) {
           e.printStackTrace();
       }
   
       return ScreenInfo;
   }
    
    public CustomerCheckGui(String title, Date selectedTime) {
//       System.out.println(title);
//       System.out.println(selectedTime);
       SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
       String test = dateFormat.format(selectedTime);
       
       List<ScreenInfoDto> screenInfoList = getScrrenInfo(title, test);
       
//       System.out.println(test);
      for (ScreenInfoDto screenInfo : screenInfoList) {
          moviePrice = screenInfo.getMoviePrice();
          screenNo = screenInfo.getScreenNo();
          screenTheater = screenInfo.getScreenTheater();
          movieTitle = screenInfo.getMovieTitle();

//         System.out.println(moviePrice);
//         System.out.println(screenNo);
//         System.out.println(screenTheater);
//         System.out.println(movieTitle);
      }
       
        setTitle("영화 예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 텍스트 필드 생성
        textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(200, 50));
        textField.setFont(new Font("고딕체", Font.PLAIN, 20));
        add(textField, BorderLayout.NORTH);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        // 청소년 인원수 추가 버튼 생성
        plusYouthButton = new JButton("+ 청소년");
        plusYouthButton.addActionListener(this);
        minusYouthButton = new JButton("- 청소년");
        minusYouthButton.addActionListener(this);

        // 일반 인원수 추가 버튼 생성
        plusAdultButton = new JButton("+ 일반");
        plusAdultButton.addActionListener(this);
        minusAdultButton = new JButton("- 일반");
        minusAdultButton.addActionListener(this);

        // 버튼 패널에 버튼 추가
        buttonPanel.add(minusYouthButton);
        buttonPanel.add(plusYouthButton);
        buttonPanel.add(minusAdultButton);
        buttonPanel.add(plusAdultButton);

        add(buttonPanel, BorderLayout.CENTER);

        // 예약 버튼 생성
        reserveButton = new JButton("예약하기");
        reserveButton.addActionListener(this);
        

        // 뒤로가기 버튼
        backButton = new JButton("뒤로가기");
        backButton.addActionListener(this);

        // 버튼 패널 생성
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new BorderLayout());

        // 뒤로가기 버튼 크기 설정
        Dimension buttonSize = reserveButton.getPreferredSize();
        backButton.setPreferredSize(buttonSize);

        // 뒤로가기 버튼을 예약하기 버튼의 왼쪽에 추가
        buttonPanel1.add(backButton, BorderLayout.WEST);
        buttonPanel1.add(reserveButton, BorderLayout.CENTER);

        add(buttonPanel1, BorderLayout.SOUTH);

        pack();
        setSize(400, 250);
        setVisible(true);

        numberOfYouth = 0;
        numberOfAdult = 0;
    }
    

    public List<String> checkReservedSeat(int screenNo) {
        ReservationseatDAO getReserSeat = new ReservationseatDAO();
        List<String> reserSeats = new ArrayList<String>();
        try {
            reserSeats = getReserSeat.getReserSeat(screenNo);
//            System.out.println(screenNo);
//            System.out.println(reserSeats);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reserSeats;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == plusYouthButton) {
            numberOfYouth++;
        } else if (e.getSource() == minusYouthButton) {
            if (numberOfYouth > 0) {
                numberOfYouth--;
            }
        } else if (e.getSource() == plusAdultButton) {
            numberOfAdult++;
        } else if (e.getSource() == minusAdultButton) {
            if (numberOfAdult > 0) {
                numberOfAdult--;
            }
        } else if (e.getSource() == reserveButton) {
            totalNumberOfAttendees = numberOfYouth + numberOfAdult;
            if (totalNumberOfAttendees > 0) {
                String message = "청소년: " + numberOfYouth + "명\n일반: " + numberOfAdult + "명\n총 인원수: " + totalNumberOfAttendees
                        + "명\n좌석 예매 진행 하겠습니다.";
                JOptionPane.showMessageDialog(null, message);
                setVisible(false);
                createAndShowGUI();
            } else {
                JOptionPane.showMessageDialog(null, "잘못된 입력입니다.");
            }
        } else if (e.getSource() == backButton) {
            // 메인 화면으로 돌아가는 로직
            dispose();
            JOptionPane.showMessageDialog(null, "메인 화면으로 돌아갑니다.");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new MovieRe().CustomerCheckGui();
                }
            });
        }
        updateTextField();
    }

    private void updateTextField() {
        textField.setText("청소년: " + numberOfYouth + "명, 일반: " + numberOfAdult + "명");
    }

    public void createAndShowGUI() {
        List<String> reservedSeats = checkReservedSeat(screenNo);
//        System.out.println("스크린넘버 : " + screenNo);

//        for (int i = 0; i < reservedSeats.size(); i++) {
//            String seat = reservedSeats.get(i);
//            seat = seat.replace(" ", ""); // 0을 제거
//            reservedSeats.set(i, seat);
//        }
        
        List<String> updatedSeats = new ArrayList<>();

        for (int i = 0; i < reservedSeats.size(); i++) {
            String seat = reservedSeats.get(i);
            //seat = seat.replace(" ", ""); // 공백 제거
            
            // 공백을 기준으로 분할하여 개별적으로 저장
            String[] seatArray = seat.split(" ");
            
            // 분할된 각각의 문자열을 updatedSeats 리스트에 추가
            updatedSeats.add(seatArray[0]);
            for (int j = 1; j < seatArray.length; j++) {
                updatedSeats.add(seatArray[j]);
            }
        }

        // updatedSeats 리스트를 reservedSeats에 할당
        reservedSeats = updatedSeats;
//        System.out.println(reservedSeats);

        frame = new JFrame("CGV 키오스크 - 좌석 확인");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        seatPanel = new JPanel(new GridLayout(4, 5)); // 4행 5열의 그리드 레이아웃

        Screen = new JLabel("SCREEN");
        Screen.setBounds(340, 170, 100, 100);
        Font labelFont = Screen.getFont();
        int fontSize = labelFont.getSize();
        Font largerFont = labelFont.deriveFont(fontSize + 10f);
        Screen.setFont(largerFont);
        


        // 좌석 체크박스 배열 초기화
        seatCheckBoxes = new JCheckBox[20];

        // 좌석 체크박스 생성
        char[] rows = { 'A', 'B', 'C', 'D' };
        int[] columns = { 1, 2, 3, 4, 5 };
        int index = 0;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                char seatRow = rows[row];
                int seatColumn = columns[col];
                String seatNumber = seatRow + "0"+  Integer.toString(seatColumn);

                seatCheckBoxes[index] = new JCheckBox(seatNumber);
                seatCheckBoxes[index].setHorizontalAlignment(JCheckBox.CENTER);
                seatCheckBoxes[index].setVerticalAlignment(JCheckBox.CENTER);
                seatCheckBoxes[index].setPreferredSize(new Dimension(50, 50));

                if (reservedSeats.contains(seatNumber)) { // 예약된 좌석인 경우
                    seatCheckBoxes[index].setEnabled(false); // 체크박스를 비활성화하여 선택 불가능하게 설정
                } else { // 예약되지 않은 좌석인 경우
                    seatCheckBoxes[index].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JCheckBox checkBox = (JCheckBox) e.getSource();
                            String selectedSeat = checkBox.getText(); // 체크박스에 표시된 좌석 번호 가져오기
                            String message;
                            if (checkBox.isSelected()) {
                                message = "좌석 " + selectedSeat + "을 선택하셨습니다.";
                            } else {
                                message = "좌석 " + selectedSeat + "의 선택을 취소하셨습니다.";
                            }
                            JOptionPane.showMessageDialog(frame, message);

                            // 선택 가능한 좌석 수 제한
                            int selectedSeatsCount = countSelectedSeats();
                            if (selectedSeatsCount > totalNumberOfAttendees) {
                                checkBox.setSelected(false);
                                message = "더 이상 좌석을 선택할 수 없습니다.";
                                JOptionPane.showMessageDialog(frame, message);
                            }
                        }
                    });
                }

                seatPanel.add(seatCheckBoxes[index]);

                index++;
            }
        }

        JButton bt1 = new JButton("뒤로가기");
        bt1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new MovieRe();
        		seatPanel.setVisible(false);
        		frame.setVisible(false);
        		setVisible(false);
        	}
        });
        // 결제하기 버튼 생성
        paymentButton = new JButton("결제 하기");
        
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 선택된 좌석 확인
                
                for (int i = 0; i < seatCheckBoxes.length; i++) {
                    if (seatCheckBoxes[i].isSelected()) {
                        selectedSeats.append(seatCheckBoxes[i].getText()).append(" ");
                    }
                }

                if (selectedSeats.length() > 0) {
                    String message = "결제가 완료되었습니다. 선택한 좌석: " + selectedSeats.toString().trim();
                    JOptionPane.showMessageDialog(frame, message);
                    BuyTest buyTest = new BuyTest(totalNumberOfAttendees, moviePrice,screenTheater,
                    		movieTitle,selectedSeats,screenNo);
                	seatPanel.setVisible(false);
            		frame.setVisible(false);
                    setVisible(false);
                    buyTest.buyTestGui();
                } else {
                    JOptionPane.showMessageDialog(frame, "좌석을 선택해주세요.");
                }
            }
        });

        seatPanel.setBounds(210, 250, 300, 200); // 좌석 패널 위치 및 크기 설정
        bt1.setBounds(500, 900, 200, 50);
        paymentButton.setBounds(100, 900, 300, 50); // 결제하기 버튼 위치 및 크기 설정

        frame.add(Screen);
        frame.getContentPane().setLayout(null); // 레이아웃 매니저 해제
        frame.getContentPane().add(seatPanel);
        frame.getContentPane().add(paymentButton);
        frame.add(bt1);
        frame.setSize(800, 1000); // 프레임 크기 설정
        frame.setVisible(true);
    }

    private int countSelectedSeats() {
        int count = 0;
        for (int i = 0; i < seatCheckBoxes.length; i++) {
            if (seatCheckBoxes[i] != null && seatCheckBoxes[i].isSelected()) {
                count++;
            }
        }
        return count;
    }
    public void actionPerformed1(ActionEvent e) {
       frame.dispose(); // 현재 프레임 닫기
       // 메인 화면으로 돌아가는 로직
       JOptionPane.showMessageDialog(null, "메인 화면으로 돌아갑니다.");
       new MovieRe().CustomerCheckGui();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CustomerCheckGui(null, null);
            }
        });
    }
    
    public int getTotalNumberOfAttendees() {
        return totalNumberOfAttendees;
     }

     public void setTotalNumberOfAttendees(int totalNumberOfAttendees) {
        this.totalNumberOfAttendees = totalNumberOfAttendees;
     }
}
