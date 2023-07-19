package Admin;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import kioskDAO.FoodAdminDAO;
import kioskDAO.ReservationDao;
import kioskDTO.FoodAdminDTO;
import kioskDTO.ReservationAdminDTO;

@SuppressWarnings("serial")
public class Total_Admin_Panel extends JFrame {
	DefaultTableModel dm, dm2;
	JTable Movie_table, Food_table;
	String[] cols_Movie = { "영화이름", "예매횟수", "매출" };
	String[] cols_Food = { "음식번호", "음식종류", "이름", "판매수량", "가격", "매출" };

	Font font = new Font("테스트", Font.PLAIN, 20);
	private JTextField textField;
	private JTextField textField_1;
	private JTextField movieSalesTextField, foodSalesTextField;

	public Total_Admin_Panel() throws SQLException {
		getContentPane().setBackground(new Color(255, 255, 255));
		dm = new DefaultTableModel(null, cols_Movie);
		Movie_table = new JTable(dm);
		Movie_table.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Movie_table.setBackground(Color.white);

		dm2 = new DefaultTableModel(null, cols_Food);
		Food_table = new JTable(dm2);
		Food_table.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		Food_table.setBackground(Color.white);

		JLabel tiTle = new JLabel("매출 관리 시스템", JLabel.CENTER);
		tiTle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		tiTle.setBounds(51, -11, 700, 75);

		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(Movie_table);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane jsp2 = new JScrollPane(Food_table);
		jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp2.setToolTipText("");

		// 테이블 내용 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		Movie_table.setDefaultRenderer(Object.class, centerRenderer);
		Food_table.setDefaultRenderer(Object.class, centerRenderer);

		JTextField search_t = new JTextField();
		search_t.setBounds(135, 59, 137, 21);
		search_t.setHorizontalAlignment(JTextField.CENTER);

		JComboBox<String> search_c = new JComboBox<>(cols_Movie);
		search_c.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		search_c.setBounds(38, 59, 85, 21);

		JButton search_b = new JButton("검색");
		search_b.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		search_b.setBounds(284, 59, 65, 21);
		search_b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = search_t.getText();
				int columnIndex = search_c.getSelectedIndex(); // Get the selected column index

				// Create a sorter based on the column index and search word
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);
				sorter.setRowFilter(RowFilter.regexFilter(word, columnIndex));

				// Apply the sorter to the table
				Movie_table.setRowSorter(sorter);
			}
		});

		// 종료 버튼
		JButton exitButton = new JButton("돌아가기");
		exitButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		exitButton.setBounds(670, 881, 102, 56);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		jsp.setBounds(353, 59, 419, 382);
		jsp2.setBounds(353, 469, 419, 382);

		ctn.setLayout(null);
		ctn.add(jsp);
		ctn.add(jsp2);
		ctn.add(exitButton);
		ctn.add(search_t);
		ctn.add(search_c);
		ctn.add(search_b);
		ctn.add(tiTle);

		JTextField search_t2 = new JTextField();
		search_t2.setBounds(135, 469, 137, 21);
		getContentPane().add(search_t2);

		JComboBox<String> search_c2 = new JComboBox<String>(cols_Food);
		search_c2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		search_c2.setBounds(38, 468, 85, 21);
		getContentPane().add(search_c2);

		JButton search_b2 = new JButton("검색");
		search_b2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		search_b2.setBounds(284, 468, 65, 22);
		getContentPane().add(search_b2);

		JLabel saLesLabel = new JLabel("전체 매출 :");
		saLesLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		saLesLabel.setToolTipText("영화 전체 매출");
		saLesLabel.setBounds(45, 423, 78, 18);
		getContentPane().add(saLesLabel);

		movieSalesTextField = new JTextField();
		movieSalesTextField.setHorizontalAlignment(SwingConstants.CENTER);
		movieSalesTextField.setBounds(135, 423, 137, 21);
		getContentPane().add(movieSalesTextField);
		movieSalesTextField.setColumns(10);

		JLabel saLesLabel2 = new JLabel("전체 매출 :");
		saLesLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		saLesLabel2.setToolTipText("상품 전체 매출");
		saLesLabel2.setBounds(49, 830, 91, 21);
		getContentPane().add(saLesLabel2);

		foodSalesTextField = new JTextField();
		foodSalesTextField.setHorizontalAlignment(SwingConstants.CENTER);
		foodSalesTextField.setColumns(10);
		foodSalesTextField.setBounds(135, 833, 137, 21);
		getContentPane().add(foodSalesTextField);

		// ImageIcon icon = new
		// ImageIcon(theaterAdmin.class.getResource("/image/186e1beb7ae47d283.gif"));
		// getContentPane().setLayout(null);
		// bg.setIcon(icon);
		// getContentPane().add(bg);
		ImageIcon icon = new ImageIcon(theaterAdmin.class.getResource("/image/total_icon.png"));
		getContentPane().setLayout(null);

		JLabel icon1 = new JLabel("New label");
		icon1.setBounds(46, 502, 289, 299);
		icon1.setIcon(icon);
		getContentPane().add(icon1);

		ImageIcon icon_ask = new ImageIcon(theaterAdmin.class.getResource("/image/admin_ask.png"));
		JLabel icon2 = new JLabel("New label");
		icon2.setBounds(52, 103, 289, 299);
		icon2.setIcon(icon_ask);
		icon2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/sojeongsj/TeamProject")); // 원하는 웹사이트 주소로 변경
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		});
		getContentPane().add(icon2);

		search_b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String word = search_t2.getText();
				int columnIndex = search_c2.getSelectedIndex(); // Get the selected column index

				// Create a sorter based on the column index and search word
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm2);
				sorter.setRowFilter(RowFilter.regexFilter(word, columnIndex));

				// Apply the sorter to the table
				Food_table.setRowSorter(sorter);
			}
		});

		setTitle("매출 현황");
		setBounds(0, 0, 800, 1000);
		setVisible(true);

		fetchReservationData();
		fetchFoodData();
		calculateTotalSales();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // Gui 설정

	/// data 입력

	private void fetchReservationData() throws SQLException {
		try {
			ReservationDao reservationDao = ReservationDao.getDao();
			List<ReservationAdminDTO> reservations = reservationDao.reservationTotal();

			for (ReservationAdminDTO reservation : reservations) {
				String formattedPrice = formatPrice1(reservation.getMoviePrice());

				String[] rowData = { reservation.getMovieTitle(), String.valueOf(reservation.getCount()),
						formattedPrice };

				dm.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String formatPrice1(int price) {
		return String.format("%,d", price);
	}

/// data 입력

	private void fetchFoodData() throws SQLException {
		try {
			FoodAdminDAO foodDAO = FoodAdminDAO.getDao();
			List<FoodAdminDTO> foods = FoodAdminDAO.FoodAdmin();

			for (FoodAdminDTO food : foods) {
				String formattedPrice = formatPrice1(food.getFOODPRICE());
				String formattedTotalSales = formatPrice1((food.getFOODQUANTITY() * (food.getFOODPRICE())));

				Object[] rowData = { food.getFOODBUNO(), food.getFOODCATE(), food.getFOODNAME(), food.getFOODQUANTITY(),
						formattedPrice, formattedTotalSales };

				dm2.addRow(rowData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

// 매출 계산 
	private void calculateTotalSales() {
		int movieSales = 0;
		int foodSales = 0;

		// 영화 매출 합산
		for (int i = 0; i < dm.getRowCount(); i++) {
			int sales = Integer.parseInt(((String) dm.getValueAt(i, 2)).replace(",", ""));
			movieSales += sales;
		}
		movieSalesTextField.setText(formatPrice1(movieSales) + "원");

		// 음식 매출 합산
		for (int i = 0; i < dm2.getRowCount(); i++) {
			int sales = Integer.parseInt(((String) dm2.getValueAt(i, 5)).replace(",", ""));
			foodSales += sales;
		}
		foodSalesTextField.setText(formatPrice2(foodSales) + "원");
	}

	// 가격 포맷팅

	private String formatPrice2(int price) {
		return String.format("%,d", price);
	}
}
