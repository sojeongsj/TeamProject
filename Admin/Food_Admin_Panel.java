package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import kioskDAO.FoodAdminDAO;
import kioskDTO.FoodDto1;

// 미완성 입니다. 

@SuppressWarnings("serial")
public class Food_Admin_Panel extends JFrame {
   DefaultTableModel dm;
   JTable jt;
   String[] cols = { "상품번호", "상품종류", "상품이름", "가격" };
   private String selectedFoodNo;
   private JTextField textField;
   private JTextField textField_1;
   private JTextField textField_2;
   private JTextField textField_3;

   public Food_Admin_Panel() {
      getContentPane().setBackground(new Color(255, 255, 255));
      // 테이블 컴포넌트에 표시될 데이터를 리스트에 저장

      // 테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달
      Container ctn = getContentPane();
      dm = new DefaultTableModel(null, cols);
      jt = new JTable(dm);
      jt.setBackground(new Color(255, 255, 255));
      jt.setBounds(273, 131, 502, 735);
jt.setBorder(new TitledBorder(new LineBorder(Color.black),""));

ctn.add(jt, BorderLayout.CENTER);
      JTableHeader header = jt.getTableHeader();
      header.setBounds(jt.getX(), jt.getY() - 20, jt.getWidth(), 20);
      ctn.add(header);

      ctn.setLayout(null);
      setBounds(0, 0, 800, 1000); // 프레임의 속성들 설정. 프레임의 좌표는 화면 기준
      setTitle("상품 정보 ");
      setVisible(true);

      JLabel tiTle = new JLabel("상품 관리 시스템", JLabel.CENTER);
      tiTle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
      tiTle.setBounds(45, 29, 700, 40);

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
                  // Object rowData = dm.getValueAt(selectedRow, i);
                  // rowDataLabels[i].setText(cols[i] + ": " + rowData.toString());

                  // 데이터를 JLabel에 출력
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
      
      ImageIcon icon = new ImageIcon(theaterAdmin.class.getResource("/image/ad_yaladin.png"));
      getContentPane().setLayout(null);
      
      JLabel icon1 = new JLabel("New label");
      icon1.setBounds(21,435,240,411);
      icon1.setIcon(icon);
      getContentPane().add(icon1);
      
      // 상품 목록 테이블 헤더에 정렬기능 넣기
      JTableHeader tableHeader = jt.getTableHeader();
      tableHeader.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              int columnIndex = jt.columnAtPoint(e.getPoint());

              // Sort the table based on the clicked column index
              sortFoodTableByColumn(columnIndex);
          }

          private void sortFoodTableByColumn(int columnIndex) {
              TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);
              jt.setRowSorter(sorter);

              List<RowSorter.SortKey> sortKeys = new ArrayList<>(sorter.getSortKeys());

              // Check if the column is already sorted
              boolean isColumnAlreadySorted = false;
              SortOrder currentSortOrder = SortOrder.UNSORTED;

              for (RowSorter.SortKey sortKey : sortKeys) {
                  if (sortKey.getColumn() == columnIndex) {
                      isColumnAlreadySorted = true;
                      currentSortOrder = sortKey.getSortOrder();
                      break;
                  }
              }

              // Update the sort keys
              sortKeys.clear();
              if (isColumnAlreadySorted) {
                  if (currentSortOrder == SortOrder.DESCENDING) {
                      sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
                  } else {
                      sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.UNSORTED));
                  }
              } else {
                  sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
              }

              sorter.setSortKeys(sortKeys);

              // Reverse the sorted rows for columns 1 and 2
              if (columnIndex == 1 || columnIndex == 2) {
                  List<RowSorter.SortKey> reverseSortKeys = new ArrayList<>(sortKeys);
                  reverseSortKeys.set(0, new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
                  sorter.setSortKeys(reverseSortKeys);
              }
          }
      });
      // 수정하기 버튼
      JButton UpdateBtn = new JButton("수정하기");
      UpdateBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      UpdateBtn.setBounds(20, 899, 99, 41);

      UpdateBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
               int fOODBUNO = Integer.parseInt((String) dm.getValueAt(selectedRow, 0));
               String fOODCATE = (String) dm.getValueAt(selectedRow, 1);
               String fOODNAME = (String) dm.getValueAt(selectedRow, 2);
               String priceString = ((String) dm.getValueAt(selectedRow, 3));
            //   int fOODPRICE = Integer.parseInt(priceString);

               JFrame inputFrame = new JFrame();
               inputFrame.setBounds(100, 100, 400, 300);
               inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               inputFrame.setTitle("상품 정보 수정");

               // 상품 종류 입력 필드
               JLabel fOODCATElabel = new JLabel("상품 종류:");
               fOODCATElabel.setBounds(50, 50, 80, 20);

               JTextField fOODCATETextField = new JTextField(fOODCATE);
               fOODCATETextField.setBounds(150, 50, 200, 20);

               // 상품 이름 입력 필드
               JLabel fOODNAMELabel = new JLabel("상품 이름:");
               fOODNAMELabel.setBounds(50, 100, 80, 20);

               JTextField fOODNAMETextField = new JTextField(fOODNAME);
               fOODNAMETextField.setBounds(150, 100, 200, 20);

               // 상품가격 입력시 경고
               JLabel fOODPRICEwarningLabel = new JLabel("※상품 가격 입력시 ,를 지워주세요※");
               fOODPRICEwarningLabel.setBounds(150, 130, 210, 20);
               // 상품 가격 입력 필드
               JLabel fOODPRICELabel = new JLabel("상품 가격:");
               fOODPRICELabel.setBounds(50, 150, 80, 20);

               JTextField fOODPRICETextField = new JTextField(priceString);
               fOODPRICETextField.setBounds(150, 150, 200, 20);

               Container inputContainer = inputFrame.getContentPane();
               inputContainer.setLayout(null);

               JButton confirmBtn = new JButton("확인");

               confirmBtn.setBounds(150, 200, 80, 30);
               confirmBtn.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                     String newfOODCATE = fOODCATETextField.getText();
                     String newfOODNAME = fOODNAMETextField.getText();
                     int newfOODPRICE = Integer.parseInt(fOODPRICETextField.getText());

                     dm.setValueAt(newfOODCATE, selectedRow, 1);
                     dm.setValueAt(newfOODNAME, selectedRow, 2);
                     dm.setValueAt(newfOODPRICE, selectedRow, 3);

                     FoodDto1 food = new FoodDto1(fOODBUNO, newfOODCATE, newfOODNAME, newfOODPRICE);
                     FoodAdminDAO foodDao = FoodAdminDAO.getDao();

                     try {
                        foodDao.update(food);
                     } catch (SQLException e1) {
                        e1.printStackTrace();
                     }
                     refreshFoodData();
                     inputFrame.dispose();
                  }
               });

               inputContainer.add(fOODCATElabel);
               inputContainer.add(fOODCATETextField);
               inputContainer.add(fOODPRICELabel);
               inputContainer.add(fOODPRICETextField);
               inputContainer.add(fOODPRICEwarningLabel);
               inputContainer.add(fOODNAMELabel);
               inputContainer.add(fOODNAMETextField);
               inputContainer.add(confirmBtn);

               inputFrame.setVisible(true);
               refreshFoodData();
            }
         }
      });
      // 삭제하기 기능
      JButton deletebtn = new JButton("삭제하기");
      deletebtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      deletebtn.setBounds(150, 899, 99, 41);
      ctn.add(deletebtn);

      deletebtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int selectedRow = jt.getSelectedRow(); // 선택된 행의 인덱스를 가져옴
            if (selectedRow != -1) {
                  String selectedFoodNoString = (String) dm.getValueAt(selectedRow, 0); // Get the value from the first column as a String
                     int selectedFoodNo = Integer.parseInt(selectedFoodNoString);
               try {
                  FoodAdminDAO.getDao().delete(selectedFoodNo); // 영화 번호를 인자로 delete 메서드 호출
                  refreshFoodData(); // 데이터 갱신
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
            inputFrame.setTitle("상품 정보 추가");

            // 상품 번호 입력 필드
            JLabel fOODBUNOLabel = new JLabel("상품번호:");
            fOODBUNOLabel.setBounds(20, 30, 100, 20);

            JTextField fOODBUNOTextField = new JTextField();
            fOODBUNOTextField.setBounds(150, 30, 200, 20);

            // 상품 종류 입력 필드
            JLabel fOODCATELabel = new JLabel("상품 종류:");
            fOODCATELabel.setBounds(20, 70, 100, 20);

            JTextField fOODCATETextField = new JTextField();
            fOODCATETextField.setBounds(150, 70, 200, 20);

            // 상품 이름 입력 필드
            JLabel fOODNAMELabel = new JLabel("상품 이름:");
            fOODNAMELabel.setBounds(20, 110, 100, 20);

            JTextField fOODNAMETextField = new JTextField();
            fOODNAMETextField.setBounds(150, 110, 200, 20);

            // 상품 가격 입력 필드
            JLabel fOODPRICELabel = new JLabel("상품 가격:");
            fOODPRICELabel.setBounds(20, 150, 100, 20);

            JTextField fOODPRICETextField = new JTextField();
            fOODPRICETextField.setBounds(150, 150, 200, 20);

            Container inputContainer = inputFrame.getContentPane();
            inputContainer.setLayout(null);

            JButton confirmBtn = new JButton("확인");
            confirmBtn.setBounds(150, 220, 80, 30);
            confirmBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  int newfOODBUNO = Integer.parseInt(fOODBUNOTextField.getText());
                  String newfOODCATE = fOODCATETextField.getText();
                  String newfOODNAME = fOODNAMETextField.getText();
                  int newfOODPRICE = Integer.parseInt(fOODPRICETextField.getText());
                  try {
                     FoodDto1 food = new FoodDto1(newfOODBUNO, newfOODCATE, newfOODNAME, newfOODPRICE);
                     FoodAdminDAO foodDao = FoodAdminDAO.getDao();
                     foodDao.insert(food);
                     refreshFoodData(); // 데이터 갱신
                  } catch (SQLException ex) {
                     ex.printStackTrace();
                  }

                  inputFrame.dispose();
               }
            });

            inputContainer.add(fOODBUNOLabel);
            inputContainer.add(fOODBUNOTextField);
            inputContainer.add(fOODNAMELabel);
            inputContainer.add(fOODNAMETextField);
            inputContainer.add(fOODPRICELabel);
            inputContainer.add(fOODPRICETextField);
            inputContainer.add(fOODCATELabel);
            inputContainer.add(fOODCATETextField);
            inputContainer.add(confirmBtn);

            inputFrame.setVisible(true);
         }
      });

      JButton backButton = new JButton("돌아가기");
      backButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      backButton.setBounds(673, 899, 99, 41);

      try {
         fetchFoodData(); // 패널이 처음 생성될 때 데이터를 가져옴
      } catch (SQLException e1) {
         e1.printStackTrace();
      }
      // "돌아가기" 버튼에 ActionListener 등록
      backButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false); // 패널 숨기기
            refreshFoodData();
         }
      });
      ctn.add(jt);
      ctn.add(backButton);
      ctn.add(UpdateBtn);

      textField = new JTextField();
      textField.setBounds(132, 112, 117, 27);
      getContentPane().add(textField);
      textField.setColumns(10);

      JLabel lblNewLabel = new JLabel("상품 번호");
      lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel.setBounds(20, 109, 99, 32);
      getContentPane().add(lblNewLabel);

      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(132, 187, 117, 27);
      getContentPane().add(textField_1);

      JLabel lblNewLabel_1 = new JLabel("상품 종류");
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel_1.setBounds(20, 184, 99, 32);
      getContentPane().add(lblNewLabel_1);

      textField_2 = new JTextField();
      textField_2.setColumns(10);
      textField_2.setBounds(132, 275, 117, 27);
      getContentPane().add(textField_2);

      JLabel lblNewLabel_2 = new JLabel("상품 이름");
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel_2.setBounds(20, 272, 99, 32);
      getContentPane().add(lblNewLabel_2);

      textField_3 = new JTextField();
      textField_3.setColumns(10);
      textField_3.setBounds(132, 372, 117, 27);
      getContentPane().add(textField_3);

      JLabel lblNewLabel_3 = new JLabel("상품 가격");
      lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
      lblNewLabel_3.setBounds(20, 369, 99, 32);
      getContentPane().add(lblNewLabel_3);

   }

   private void refreshFoodData() {
      try {
         dm.setRowCount(0); // 테이블의 기존 데이터 삭제
         fetchFoodData(); // 테이블 데이터 다시 가져오기
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   private void fetchFoodData() throws SQLException {
      List<FoodDto1> foods = FoodAdminDAO.getDao().FoodselectAll1();

      for (FoodDto1 food : foods) {
         int fOODBUNO = food.getFOODBUNO();
         String fOODCATE = food.getFOODCATE();
         String fOODNAME = food.getFOODNAME();
         int fOODPRICE = food.getFOODPRICE();

           String formattedPrice = formatPrice(fOODPRICE); 
         String[] rowData = { String.valueOf(fOODBUNO), fOODCATE, fOODNAME, formattedPrice };
         dm.addRow(rowData);
      }

      jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            int selectedRow = jt.getSelectedRow();
            if (selectedRow != -1) {
               selectedFoodNo = (String) dm.getValueAt(selectedRow, 0); // 선택된 행의 영화 번호 저장
            }
         }
      });
   }
   private String formatPrice(int price) {
       return String.format("%,d", price);
   }
}