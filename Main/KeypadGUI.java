package Main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class KeypadGUI extends JFrame implements ActionListener {
    private JTextField textField;

    public KeypadGUI() {
        setTitle("Keypad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 텍스트 필드 생성
        textField = new JTextField();
        textField.setEditable(false);
        textField.setPreferredSize(new Dimension(200, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setBackground(Color.white);
        add(textField, BorderLayout.NORTH);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(800, 800);
        JLabel back = new JLabel(new ImageIcon("d:/image/keypadback.png"));
        back.setBounds(250,800, 300, 100);
        add(back);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new GridBagLayout()); // GridBagLayout으로 변경

        // GridBagConstrains 생성
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE; // 컴포넌트가 채워질 수 있는 최대 크기로 설정
        gbc.insets = new Insets(5, 5, 5, 5); // 컴포넌트 간의 간격 설정
        Font btfont = new Font("Tenada",Font.PLAIN,20);

        // 버튼 추가
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(this);
            gbc.gridx = (i - 1) % 3; // 열 위치 설정
            gbc.gridy = (i - 1) / 3; // 행 위치 설정
            button.setPreferredSize(new Dimension(150, 100));
            button.setFont(btfont);
            buttonPanel.add(button, gbc);
        }

        // Clear 버튼 추가
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        gbc.gridx = 0; // 열 위치 설정
        gbc.gridy = 3; // 행 위치 설정
        clearButton.setPreferredSize(new Dimension(150, 100));
        clearButton.setFont(btfont);
        buttonPanel.add(clearButton, gbc);

        // 0 버튼 추가
        JButton zeroButton = new JButton("0");
        zeroButton.addActionListener(this);
        gbc.gridx = 1; // 열 위치 설정
        gbc.gridy = 3; // 행 위치 설정
        zeroButton.setPreferredSize(new Dimension(150, 100));
        zeroButton.setFont(btfont);
        buttonPanel.add(zeroButton, gbc);

        // Enter 버튼 추가
        JButton enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        gbc.gridx = 2; // 열 위치 설정
        gbc.gridy = 3; // 행 위치 설정
        enterButton.setPreferredSize(new Dimension(150, 100));
        enterButton.setFont(btfont);
        buttonPanel.add(enterButton, gbc);

        JButton backButton = new JButton(new ImageIcon("/image/gofirst.png"));
        backButton.addActionListener(this);
        gbc.gridx = 1; // 열 위치 설정
        gbc.gridy = 4; // 행 위치 설정
        backButton.setPreferredSize(new Dimension(120, 50));
        buttonPanel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new firstScreen();
                setVisible(false);
            }
        });

        add(buttonPanel, BorderLayout.CENTER);

        // Enter 키 이벤트 처리
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleEnterKey();
                }
            }
        });

        pack();
        getContentPane().setBackground(Color.WHITE);
        setSize(800, 1000);
        
        setVisible(true);
    }

    private void handleEnterKey() {
        String text = textField.getText().replaceAll("-", "");
        System.out.println("입력된 값: " + text);
        textField.setText("");

        try {
            ReservationDto reservation = ReservationDao.ReservationOne(text);
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String currentDateTime = currentTime.format(formatter);
            ReservationDao reservationDao = ReservationDao.getDao();
            String reservationNumber = reservationDao.getLatestReservationNumber();
            ScreeningDao screeningDao = new ScreeningDao();
            ScreeningDto screening = screeningDao.search(reservationNumber);
            if (reservation != null) {
            	 //string buider를 사용하여 조합
                StringBuilder receiptText = new StringBuilder();
                receiptText.append("상영일자: ").append(screening.getScreenDate()).append("\n");
                receiptText.append("영화제목: ").append(reservation.getMovieTitle()).append("\n");
                receiptText.append("좌석: ").append(reservation.getReserSeat()).append("\n");
                receiptText.append("상영관 번호: ").append(reservation.getScreenTheater()).append("\n");

                JOptionPane.showMessageDialog(this, receiptText.toString(), "영수증", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "해당 예약번호에 대한 예약이 없습니다. 다시 입력해주세요.", "예약 조회 오류",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "예약 정보 검색 중 오류 발생: " + ex.getMessage(), "예약 조회 오류",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Clear")) {
            textField.setText("");
        } else if (actionCommand.equals("Enter")) {
            handleEnterKey();
        } else {
            if (textField.getText().replaceAll("-", "").length() < 16) {
                textField.setText(textField.getText() + actionCommand);
                if (textField.getText().replaceAll("-", "").length() % 4 == 0 && textField.getText().length() != 16) {
                    if (textField.getText().length() != 19)
                        textField.setText(textField.getText() + "-");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KeypadGUI();
            }
        });
    }
}









