package pack_CGV_Admin;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import food.FoodDAO;
import food.FoodDTO;
import reservation.ReservationAdminDTO;
import reservation.ReservationDao;

@SuppressWarnings("serial")
public class Total_Admin_Panel extends JFrame {
	DefaultTableModel dm, dm2;
	JTable Movie_table, Food_table;
	String[] cols_Movie = { "영화이름", "예매횟수", "매출"};
	String[] cols_Food = { "음식번호","음식종류", "이름", "판매수량", "가격","매출" };

	public Total_Admin_Panel() throws SQLException {
		dm = new DefaultTableModel(null, cols_Movie);
		Movie_table = new JTable(dm);

		dm2 = new DefaultTableModel(null, cols_Food);
		Food_table = new JTable(dm2);

		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(Movie_table);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JScrollPane jsp2 = new JScrollPane(Food_table);
		jsp2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp2.setToolTipText("");

		JLabel search = new JLabel("검색");
		search.setBounds(10,10,20,20);
		JTextField search_t = new JTextField();
		search_t.setBounds(20,20,20,20);
		JButton exitButton = new JButton("종료");
		exitButton.setBounds(650, 850, 100, 80);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		jsp.setBounds(12, 59, 760, 341);
		jsp2.setBounds(12, 432, 760, 415);

		ctn.setLayout(null);
		ctn.add(jsp);
		ctn.add(jsp2);
		ctn.add(exitButton);
		ctn.add(search);
		ctn.add(search_t);

		setTitle("매출 현황");
		setBounds(0, 0, 800, 1000);
		setVisible(true);

		fetchReservationData();
		fetchFoodData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // Gui 설정

	/// data 입력
	
	  private void fetchReservationData() throws SQLException { try {
	 ReservationDao reservationDao = ReservationDao.getDao();
	 List<ReservationAdminDTO> reservations = reservationDao.reservationTotal();
	  
	 for (ReservationAdminDTO reservation : reservations) { String[] rowData = {
	  reservation.getMovieTitle(), String.valueOf(reservation.getCount()),
	  String.valueOf(reservation.getMoviePrice()) }; dm.addRow(rowData); } } catch
	  (SQLException e) { e.printStackTrace(); } }
	 

/// data 입력
	
	  private void fetchFoodData() throws SQLException { try {
	  
	  FoodDAO foodDAO = FoodDAO.getDao(); 
	  List<FoodDTO> foods =
	  foodDAO.FoodAdmin();
	  for (FoodDTO food : foods) 
	  { Object[] rowData = { food.getFOODBUNO(),
	  food.getFOODCATE(), 
	  food.getFOODNAME(), 
	  food.getFOODQUANTITY(),
	  food.getFOODPRICE(),
	  food.getPrice()}; dm2.addRow(rowData); } } catch (SQLException e) {
	  e.printStackTrace(); } }
	 
	}