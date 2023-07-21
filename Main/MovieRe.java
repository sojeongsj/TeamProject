package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import kioskDAO.MovieDao;
import kioskDAO.ScreeningDao;
import kioskDTO.ScreeningDto;


public class MovieRe extends JFrame {
   private static final long serialVersionUID = 1L;
   private ScreeningDao screeningDao;
   private MovieDao movieDao;
   private JButton bt1;
   private JButton bt2;
   private CustomerCheckGui customerCheckGui;

   public MovieRe() {
   	getContentPane().setBackground(new Color(255, 255, 255));
      bt1 = new JButton("초기화면");
      bt1.setBounds(100, 800, 200, 100);
      getContentPane().add(bt1);

//      bt2 = new JButton("인원선택");
//      bt2.setBounds(500, 800, 200, 100);
//      add(bt2);

      bt1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            new firstScreen();
            setVisible(false);
         }
      });

//      bt2.addActionListener(new ActionListener() {
//         @Override
//         public void actionPerformed(ActionEvent e) {
//            setVisible(false);
//            customerCheckGui = new CustomerCheckGui(null,null);
         //}
      //} 만능 해결사 : 김민섭!!!!!!!!!!!!!!!!!!!!!!!!!
   ;

      // 스크롤패널
      JScrollPane mvscr = new JScrollPane();
      mvscr.setBounds(50, 50, 700, 700);
      getContentPane().add(mvscr);

      // 스크롤 패널 안의 패널
      JPanel mvpanel = new JPanel();
      mvpanel.setBackground(new Color(255, 255, 255));
      mvpanel.setLayout(new BoxLayout(mvpanel, BoxLayout.Y_AXIS));

      screeningDao = new ScreeningDao();
      movieDao = new MovieDao();

      try {
         List<ScreeningDto> screens = screeningDao.screentitle();
         for (int i = 0; i < screens.size(); i++) {
            ScreeningDto screen = screens.get(i);
            JPanel mblistPn = new JPanel();
            mblistPn.setBackground(Color.white);
            mblistPn.setLayout(new BoxLayout(mblistPn, BoxLayout.X_AXIS)); // 가로로 정렬
            ImageIcon img = new ImageIcon("d:/image/m" + (i + 1) + ".png");
            Image imgg = img.getImage();
            Image changeImg = imgg.getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
            img = new ImageIcon(changeImg);
            JLabel mvposter = new JLabel(img);
            String title = screen.getMovieTitle();
            JLabel mvnmlb = new JLabel("      "+title);
            mvnmlb.setFont(new Font("나눔바른고딕", Font.BOLD, 15));
            mblistPn.add(mvposter);
            mblistPn.add(mvnmlb);

            ArrayList<Timestamp> sctime = screeningDao.getScreeningTimes(screen.getMovieTitle());
            JPanel radioPanel = new JPanel();
            //radioPanel.setBackground(Color.white);
            radioPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2행 2열로 GridLayout 설정
            radioPanel.setBorder(new LineBorder(Color.BLACK)); // 테두리 설정

            ButtonGroup buttongroup = new ButtonGroup();
            
            
            for (Timestamp date : sctime) {
               ///System.out.println(date);
               SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
               String dateString = dateFormat2.format(date);
               CustomRadioButton sctimebt = new CustomRadioButton(dateString,date);
               
               //JRadioButton sctimebt = new JRadioButton(dateString);
               buttongroup.add(sctimebt);
               radioPanel.add(sctimebt);

               sctimebt.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                     // 선택한 라디오 버튼의 처리
                     if (sctimebt.isSelected()) {
                        Date selectedTime = sctimebt.getCustomValue();
                        customerCheckGui = new CustomerCheckGui(title, selectedTime);
                        setVisible(false);
                     }
                  }
               });
            }

            mblistPn.add(Box.createHorizontalGlue()); // 오른쪽 정렬
            mblistPn.add(Box.createHorizontalStrut(10)); // 오른쪽 여백
            mblistPn.add(radioPanel);

            mvpanel.add(mblistPn);
            mvpanel.add(Box.createRigidArea(new Dimension(0, 10)));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }

      mvscr.setViewportView(mvpanel);

      getContentPane().setLayout(null);
      setSize(800, 1000);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      
   }

   public static void main(String[] args) {
      new MovieRe();
   }
   
   public void CustomerCheckGui() {
      
   }
}
