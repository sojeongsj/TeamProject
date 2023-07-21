package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import kioskDAO.FoodBuyDAO;
import kioskDAO.FoodDAO;
import kioskDTO.FoodBuyDTO;
import kioskDTO.FoodDTO;

public class FoodUiNew extends JFrame {
	private static final long serialVersionUID = 1L;
	private JFrame foodui;
	private JPanel panel;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JList list;
	private JTable cartTable;
	private List<FoodBuyDTO> carts = new ArrayList<>();
	private JPanel gridPanel;
	private JSpinner quantitySpinner = null;
	private int moneynaena;

	public FoodUiNew(List<FoodBuyDTO> carts) {
		this.carts = carts;
	}

	public FoodUiNew() {
		
		getContentPane().setBackground(new Color(255, 255, 255));
		// JFrame
		foodui = new JFrame();
		button1 = new JButton(new ImageIcon("d:/image/popcorn.png"));
		button1.setBounds(18, 65, 187, 68);
		button2 = new JButton(new ImageIcon("d:/image/drink.png"));
		button2.setBounds(206, 65, 187, 68);
		button3 = new JButton(new ImageIcon("d:/image/snack.png"));
		button3.setBounds(394, 65, 187, 68);
		button4 = new JButton(new ImageIcon("d:/image/set.png"));
		button4.setBounds(582, 65, 187, 68);
		button5 = new JButton(new ImageIcon("d:/image/buycart.png"));
		button5.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		button5.setBounds(582, 780, 187, 68);
		button6 = new JButton("삭제");
		button6.setFont(new Font("나눔바른고딕", Font.PLAIN, 15));
		button6.setBounds(582, 899, 100, 30);
		button7 = new JButton(new ImageIcon("d:/image/home.png"));
		button7.setBounds(719, 880, 50, 50);

		// JScrollPane에 JTable을 추가
		cartTable = new JTable();
		
		
		JScrollPane cartScroll = new JScrollPane(cartTable);
		
		cartScroll.setBounds(18, 780, 550, 150);

		// 패널에 JScrollPane을 추가
		getContentPane().add(cartScroll);

		JPanel popcornpane = new JPanel();
		popcornpane.setLayout(null);

		// JPanel을 그리드 레이아웃으로 설정
		gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		gridPanel.setBackground(new Color(255, 255, 255));

		// JScrollPane에 JPanel을 추가
		JScrollPane pcScroll = new JScrollPane(gridPanel);
		pcScroll.setBounds(18, 150, 750, 600);

		getContentPane().add(button1);
		getContentPane().add(button2);
		getContentPane().add(button3);
		getContentPane().add(button4);
		getContentPane().add(button5);
		getContentPane().add(button6);
		getContentPane().add(button7);
		getContentPane().add(pcScroll);
		getContentPane().add(cartScroll);
		getContentPane().setLayout(null);
		
		
		ImageIcon logo = new ImageIcon("d:/image/logo.png");
		Image logoimage = logo.getImage();
		logo = new ImageIcon(logoimage.getScaledInstance(250, 50, DO_NOTHING_ON_CLOSE));
		JLabel lblNewLabel = new JLabel(logo);
		lblNewLabel.setBounds(268, 10, 250, 50);
		getContentPane().add(lblNewLabel);
		setSize(800, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 버튼 클릭 이벤트 처리
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOCategory("popcorn");
			}
		});

		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOCategory("drink");
			}
		});

		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOCategory("snack");
			}
		});

		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DAOCategory("combo");
			}
		});

		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(moneynaena != 0) {
					FoodBuyDAO fdo = new FoodBuyDAO();
					int count = fdo.InsertCart(carts);
					new FoodBuyTest().buyTestGui(moneynaena);
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "장바구니에 상품이 없습니다.");
				}
			}
		});

		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = cartTable.getSelectedRow();
				if (selectedRow != -1) {
					// 선택된 행 삭제
					carts.remove(selectedRow);

					// 테이블 업데이트
					updateCartTable();
				}
			}
		});
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				new firstScreen();
				setVisible(false);
			}
		});
		

		// 초기화면
		DAOCategory("popcorn");
		updateCartTable();
	}

	public static void main(String[] args) {
		new FoodUiNew();
	}

	// DAO의 메서드를 호출하는 예시 메서드
	public List<FoodDTO> getFoodsByCategory(String foodCate) throws SQLException {
		FoodDAO foodDAO = new FoodDAO();
		List<FoodDTO> foods = foodDAO.getFoodsByCategory(foodCate);
		return foods;
	}

	public void DAOCategory(String category) {
		DecimalFormat df = new DecimalFormat("###,###,###,###");
		try {
			
			// DAO에서 데이터 가져오기
			List<FoodDTO> foods = getFoodsByCategory(category);

			// 가져온 데이터를 그리드 레이아웃에 표시
			gridPanel.removeAll(); // 기존의 패널 삭제

			for (int i = 0; i < foods.size(); i++) {
				FoodDTO food = foods.get(i);

				JPanel foodPanel = new JPanel();
				foodPanel.setBackground(Color.white);
				foodPanel.setLayout(new BorderLayout());
				foodPanel.setSize(200, 220);

				// 버튼 생성
				JButton foodButton = new JButton();
				
				foodButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JSpinner quantitySpinner = (JSpinner) foodPanel.getClientProperty("quantitySpinner");
						int quantity = (int) quantitySpinner.getValue();
						if (quantity > 0) {
							// 수량 값 사용
							carts.add(new FoodBuyDTO(category, food.getFoodName(), food.getFoodPrice(), quantity));

							moneynaena += food.getFoodPrice() * quantity;

							updateCartTable();
							quantitySpinner.setValue(0);
						}
					}
				});

				// 이미지 크기 조정
				String imagepath = "d:/image/"+food.getFoodName()+".jpg";
				File imageFile = new File(imagepath);
				if (imageFile.exists()) {
					ImageIcon icon = new ImageIcon(imagepath);
					Image img = icon.getImage();
					img = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
					icon = new ImageIcon(img);
					foodButton.setIcon(icon);
				}
				else {
					ImageIcon icon = new ImageIcon("d:/image/ready.png");
					Image img = icon.getImage();
					img = img.getScaledInstance(180, 180, Image.SCALE_SMOOTH);
					icon = new ImageIcon(img);
					foodButton.setIcon(icon);
				}

				foodPanel.add(foodButton, BorderLayout.NORTH);

				JLabel nameLabel = new JLabel("<html><body><center>" + food.getFoodName() + "<br>" + df.format(food.getFoodPrice())
						+ "</center></body></html>");
				nameLabel.setFont(new Font("나눔바른고딕", Font.PLAIN, 13));
				nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
				nameLabel.setVerticalAlignment(SwingConstants.TOP);

				foodPanel.add(nameLabel, BorderLayout.CENTER);

				// 수량 스피너 생성
				SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1); // 최소값, 최대값, 증가값 설정
				JSpinner quantitySpinner = new JSpinner(spinnerModel);
				Dimension spinnerSize = new Dimension(50, 20);
				quantitySpinner.setPreferredSize(spinnerSize);

				// 상품 패널에 수량 스피너를 설정
				foodPanel.putClientProperty("quantitySpinner", quantitySpinner);

				foodPanel.add(quantitySpinner, BorderLayout.SOUTH);
				quantitySpinner.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						int quantity = (int) quantitySpinner.getValue();
					}
				});

				gridPanel.add(foodPanel);
			}

			// 스크롤 패널 내용 업데이트
			gridPanel.revalidate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void updateCartTable() {
		// 테이블 모델 생성
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("상품명");
		model.addColumn("가격");
		model.addColumn("수량");

		// Cart List의 내용을 테이블에 추가
		for (FoodBuyDTO item : carts) {
			Object[] rowData = { item.getFoodName(), item.getFoodPrice(), item.getFoodQuantity() };
			model.addRow(rowData);
		}

		// 테이블 모델 설정
		cartTable.setModel(model);
	}

	public int getMoneynaena() {
		return moneynaena;
	}

	public void setMoneynaena(int moneynaena) {
		this.moneynaena = moneynaena;
	}
}