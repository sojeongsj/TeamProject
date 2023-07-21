package Admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kioskDAO.CustomerDAO2;
import kioskDTO.CustomerDto;


@SuppressWarnings("serial")
public class Custom_Admin_Panel extends JFrame {
   DefaultTableModel dm;
   JTable jt;
   String[] cols = { "회원번호", "회원이름", "전화번호", "포인트" };
   private String selectedCustNO;
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;

   public Custom_Admin_Panel() {
      getContentPane().setBackground(new Color(255, 255, 255));
      getContentPane().setFont(new Font("맑은 고딕", Font.BOLD, 15));
      Container ctn = getContentPane();
      dm = new DefaultTableModel(null, cols);
      jt = new JTable(dm);
      jt.setBounds(270, 132, 502, 745);
      jt.setBorder(new TitledBorder(new LineBorder(Color.black),""));

      JTableHeader header = jt.getTableHeader();
      header.setBounds(jt.getX(), jt.getY() - 20, jt.getWidth(), 20);
      ctn.add(header);

      ctn.setLayout(null);
      setBounds(0, 0, 800, 1000);
      setTitle("회원 정보");
      setVisible(true);

      JLabel tiTle = new JLabel("영화관 회원 관리 시스템", JLabel.CENTER);
      tiTle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      tiTle.setBounds(50, 10, 700, 64);
      ctn.add(tiTle);

      jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            JLabel[] rowDataLabels = new JLabel[cols.length];
            for (int i = 0; i < cols.length; i++) {
               JLabel label = new JLabel();
               label.setBounds(20 + i * 120, 95, 100, 20);
               rowDataLabels[i] = label;
               ctn.add(label);
            }
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
               for (int i = 0; i < cols.length; i++) {
                  switch (i) {
                  case 0:
                     textField.setText((String) dm.getValueAt(selectedRow, i));
                     break;
                  case 1:
                     textField_1.setText((String) dm.getValueAt(selectedRow, i));
                     break;
                  case 2:
                     textField_2.setText(dm.getValueAt(selectedRow, i).toString());
                     break;
                  case 3:
                     textField_3.setText((String) dm.getValueAt(selectedRow, i));
                     break;
                  }
               }
            }
         }
      });
//광고배너
      ImageIcon icon = new ImageIcon(theaterAdmin.class.getResource("/image/ad_burger.png"));
      getContentPane().setLayout(null);
      
      JLabel icon1 = new JLabel("");
      icon1.setBounds(12,409,249,468);
      icon1.setIcon(icon);
      getContentPane().add(icon1);
      
      JButton UpdateBtn = new JButton("수정하기");
      UpdateBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      UpdateBtn.setBounds(20, 899, 99, 41);
      UpdateBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
               int custNo = Integer.parseInt((String) dm.getValueAt(selectedRow, 0));
               String custName = (String) dm.getValueAt(selectedRow, 1);
               String custPhone = (String) dm.getValueAt(selectedRow, 2);
               int custPoint = Integer.parseInt((String) dm.getValueAt(selectedRow, 3));

               JFrame inputFrame = new JFrame();
               inputFrame.setBounds(100, 100, 400, 300);
               inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               inputFrame.setTitle("회원 정보 수정");

               JLabel custNameLabel = new JLabel("회원 이름:");
               custNameLabel.setBounds(50, 50, 80, 20);
               JTextField custNameTextField = new JTextField(custName);
               custNameTextField.setBounds(150, 50, 200, 20);

               JLabel custPhoneLabel = new JLabel("전화번호:");
               custPhoneLabel.setBounds(50, 100, 80, 20);
               JTextField custPhoneTextField = new JTextField(custPhone);
               custPhoneTextField.setBounds(150, 100, 200, 20);

               JLabel custPointLabel = new JLabel("포인트:");
               custPointLabel.setBounds(50, 150, 80, 20);
               
               JTextField custPointTextField = new JTextField(Integer.toString(custPoint));
               custPointTextField.setBounds(150, 150, 200, 20);

               Container inputContainer = inputFrame.getContentPane();
               inputContainer.setLayout(null);

               JButton confirmBtn = new JButton("확인");
               confirmBtn.setBounds(150, 200, 80, 30);
               confirmBtn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String newcustName = custNameTextField.getText();
                       String newcustPhone = custPhoneTextField.getText();
                       int newcustPoint = Integer.parseInt(custPointTextField.getText());

                       dm.setValueAt(newcustName, selectedRow, 1);
                       dm.setValueAt(newcustPhone, selectedRow, 2);
                       dm.setValueAt(newcustPoint, selectedRow, 3);

                       CustomerDto custom = new CustomerDto(custNo, newcustName, newcustPhone, newcustPoint);
                       CustomerDAO2 custDao = CustomerDAO2.getDao();

                       try {
                           custDao.update(custom);
                       } catch (SQLException e1) {
                           e1.printStackTrace();
                       }
                       refreshCustomData();
                       inputFrame.dispose();
                   }
               });

               inputContainer.add(custNameLabel);
               inputContainer.add(custNameTextField);
               inputContainer.add(custPhoneLabel);
               inputContainer.add(custPhoneTextField);
               inputContainer.add(custPointLabel);
               inputContainer.add(custPointTextField);
               inputContainer.add(confirmBtn);

               inputFrame.setVisible(true);
            }
         }
      });

      JButton deletebtn = new JButton("삭제하기");
      deletebtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      deletebtn.setBounds(150, 899, 99, 41);
      ctn.add(deletebtn);
      deletebtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int selectedRow = jt.getSelectedRow(); // 선택된 행의 인덱스를 가져옴
            if (selectedRow != -1) {
               int custNO = Integer.parseInt((String) jt.getValueAt(selectedRow, 0)); // 선택된 행의 영화 번호를 가져옴
               try {
                  CustomerDAO2.getDao().delete(custNO); // 영화 번호를 인자로 delete 메서드 호출
                  refreshCustomData(); // 데이터 갱신
               } catch (SQLException ex) {
                  ex.printStackTrace();
               }
            }
         }
      });

      // 추가 기능
      JButton insertBtn = new JButton("추가하기");
      insertBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      insertBtn.setBounds(273, 899, 99, 41);
      ctn.add(insertBtn);

      insertBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JFrame inputFrame = new JFrame();
            inputFrame.setBounds(100, 100, 400, 300);
            inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inputFrame.setTitle("회원 정보 추가");

            // 회원 번호 입력 필드
            JLabel custNoLabel = new JLabel("회원 번호:");
            custNoLabel.setBounds(20, 20, 100, 20);

            JTextField custNoTextField = new JTextField();
            custNoTextField.setBounds(150, 20, 200, 20);

            // 회원 이름 입력 필드
            JLabel custNameLabel = new JLabel("회원 이름:");
            custNameLabel.setBounds(20, 60, 100, 20);

            JTextField custNameTextField = new JTextField();
            custNameTextField.setBounds(150, 60, 200, 20);

            // 회원 전화번호 입력 필드
            JLabel custPhoneLabel = new JLabel("전화 번호:");
            custPhoneLabel.setBounds(20, 100, 100, 20);

            JTextField custPhoneTextField = new JTextField();
            custPhoneTextField.setBounds(150, 100, 200, 20);

            // 회원 포인트 입력 필드
            JLabel custPointLabel = new JLabel("포인트:");
            custPointLabel.setBounds(20, 140, 100, 20);

            JTextField custPointTextField = new JTextField();
            custPointTextField.setBounds(150, 140, 200, 20);

            Container inputContainer = inputFrame.getContentPane();
            inputContainer.setLayout(null);

            JButton confirmBtn = new JButton("확인");
            confirmBtn.setBounds(150, 220, 80, 30);
            confirmBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int newcustNo = Integer.parseInt(custNoTextField.getText());
                  String newcustName = custNameTextField.getText();
                  String newcustPhone = custPhoneTextField.getText();
                  int newcustPoint = Integer.parseInt(custPointTextField.getText());

                  try {
                     CustomerDto custom = new CustomerDto(newcustNo, newcustName, newcustPhone, newcustPoint);
                     CustomerDAO2 custdao = CustomerDAO2.getDao();
                     custdao.insert(custom);
                     refreshCustomData(); // 데이터 갱신
                  } catch (SQLException ex) {
                     ex.printStackTrace();
                  }

                  inputFrame.dispose();
               }
            });

            inputContainer.add(custNoLabel);
            inputContainer.add(custNoTextField);
            inputContainer.add(custNameLabel);
            inputContainer.add(custNameTextField);
            inputContainer.add(custPhoneLabel);
            inputContainer.add(custPhoneTextField);
            inputContainer.add(custPointLabel);
            inputContainer.add(custPointTextField);
            inputContainer.add(confirmBtn);

            inputFrame.setVisible(true);
         }
      });

      JButton backButton = new JButton("돌아가기");
      backButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      backButton.setBounds(673, 899, 99, 41);

      try {
         fetchCustomData(); // 패널이 처음 생성될 때 데이터를 가져옴
      } catch (SQLException e1) {
         e1.printStackTrace();
      }
      // "돌아가기" 버튼에 ActionListener 등록
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false); // 패널 숨기기
            refreshCustomData();
         }
      });
      ctn.add(jt);
      ctn.add(backButton);
      ctn.add(UpdateBtn);

      textField = new JTextField();
      textField.setBounds(132, 112, 117, 27);
      getContentPane().add(textField);
      textField.setColumns(10);

      JLabel lblNewLabel = new JLabel("회원 번호");
      lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel.setBounds(20, 109, 99, 32);
      getContentPane().add(lblNewLabel);

      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(132, 187, 117, 27);
      getContentPane().add(textField_1);

      JLabel lblNewLabel_1 = new JLabel("회원 이름");
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel_1.setBounds(20, 184, 99, 32);
      getContentPane().add(lblNewLabel_1);

      textField_2 = new JTextField();
      textField_2.setColumns(10);
      textField_2.setBounds(132, 275, 117, 27);
      getContentPane().add(textField_2);

      JLabel lblNewLabel_2 = new JLabel("전화 번호");
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel_2.setBounds(20, 272, 99, 32);
      getContentPane().add(lblNewLabel_2);

      textField_3 = new JTextField();
      textField_3.setColumns(10);
      textField_3.setBounds(132, 372, 117, 27);
      getContentPane().add(textField_3);

      JLabel lblNewLabel_3 = new JLabel("포인트");
      lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel_3.setBounds(20, 369, 99, 32);
      getContentPane().add(lblNewLabel_3);

   }

   private void refreshCustomData() {
      try {
         dm.setRowCount(0); // 테이블의 기존 데이터 삭제
         fetchCustomData(); // 테이블 데이터 다시 가져오기
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private void fetchCustomData() throws SQLException {
      CustomerDAO2 customdao = CustomerDAO2.getDao();
      List<CustomerDto> customs = customdao.customerslectall();

      for (CustomerDto custom : customs) {
         int custNo = custom.getCustNo();
         String custName = custom.getCustName();
         String custPhone = custom.getCustPhone();
         int custPoint = custom.getCustPoint();

         String[] rowData = { String.valueOf(custNo), custName, custPhone, String.valueOf(custPoint) };
         dm.addRow(rowData);
      }

      jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
               selectedCustNO = (String) dm.getValueAt(selectedRow, 0); // 선택된 행의 영화 번호 저장
            }
         }
      });
   }
}