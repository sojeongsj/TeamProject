package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lastScreen {
    private JFrame frame;
    private Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new lastScreen().createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 이미지 파일 경로
        String imagePath1 = "d:/image/printticket.png";
//        String imagePath2 = "d:/temp/movieposter.jpg";

        // 이미지 아이콘 생성
        ImageIcon imageIcon1 = new ImageIcon(imagePath1);
//        ImageIcon imageIcon2 = new ImageIcon(imagePath2);

        // 이미지를 표시할 라벨 생성
        JLabel imageLabel1 = new JLabel();
        imageLabel1.setIcon(new ImageIcon(imageIcon1.getImage().getScaledInstance(800, 1000, Image.SCALE_SMOOTH)));

//        JLabel imageLabel2 = new JLabel();
//        imageLabel2.setIcon(new ImageIcon(imageIcon2.getImage().getScaledInstance(800, 300, Image.SCALE_SMOOTH)));

        // 상단에 이미지를 배치할 패널
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.BLACK);
        topPanel.setLayout(new BorderLayout());
//        topPanel.add(imageLabel2, BorderLayout.CENTER);

        // 중앙에 이미지를 배치할 패널
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.BLACK);
        centerPanel.add(imageLabel1);

        // 전체 프레임에 패널 배치
        frame.getContentPane().setBackground(Color.BLACK);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setSize(800, 1000);
        frame.setVisible(true);

        // 10초 후에 메인 화면으로 돌아가는 타이머 시작
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 현재 프레임 닫기
                // 메인 화면으로 돌아가는 로직
                JOptionPane.showMessageDialog(null, "메인 화면으로 돌아갑니다.");
                new firstScreen();
            }
        });
        timer.setRepeats(false); // 한 번만 실행되도록 설정
        timer.start();
    }
}