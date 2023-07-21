package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class foodlastScreen {
    private JFrame frame;
    private Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new foodlastScreen().createAndShowGUI();
            }
        });
    }

    public void createAndShowGUI() {
        frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 

        // 이미지를 표시할 라벨 생성
        JLabel imageLabel1 = new JLabel(new ImageIcon("d:/image/foodlast3.png"));
        imageLabel1.setBounds(0, 0, 800, 1000);
        frame.add(imageLabel1);

        frame.pack();
        frame.setSize(800, 1000);
        frame.setVisible(true);

        // 초 후에 메인 화면으로 돌아가는 타이머 시작
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