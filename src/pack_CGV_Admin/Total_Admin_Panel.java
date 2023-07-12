package pack_CGV_Admin;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import food.FoodDAO;
import food.FoodDTO;
import food.FoodDto1;
import reservation.ReservationAdminDTO;
import reservation.ReservationDao;

@SuppressWarnings("serial")
public class Total_Admin_Panel extends JFrame {
	DefaultTableModel dm, dm2;
	JTable Movie_table, Food_table;
	String[] cols_Movie = { "영화이름", "예매횟수", "매출"};
	String[] cols_Food = { "음식번호","음식종류", "이름", "판매수량", "가격","매출" };

	Font font = new Font("테스트", Font.PLAIN, 20);
	private JTextField textField;
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

		//테이블 내용 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		Movie_table.setDefaultRenderer(Object.class, centerRenderer);
		Food_table.setDefaultRenderer(Object.class, centerRenderer);
		
		JTextField search_t = new JTextField();
		search_t.setBounds(135,59,137,21);
		search_t.setHorizontalAlignment(JTextField.CENTER);
		
		JComboBox<String> search_c = new JComboBox<>(cols_Movie);
		search_c.setBounds(38,59,85,21);
		
		JButton search_b = new JButton("검색");
		search_b.setBounds(284,59,65,20);
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
		
		// 영화 테이블 헤드에 정렬기능(내림차순) 넣기 
		JTableHeader movieTableHeader = Movie_table.getTableHeader();
		movieTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int columnIndex = Movie_table.columnAtPoint(e.getPoint());

		        // Sort the table based on the clicked column index
		        sortMovieTableByColumn(columnIndex);
		    }

		    private void sortMovieTableByColumn(int columnIndex) {
		        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm);
		        Movie_table.setRowSorter(sorter);
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
		                // If the column is already sorted in descending order, sort in ascending order
		                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
		            } else {
		                // If the column is already sorted in ascending order or unsorted, revert to the original order
		                sorter.setSortKeys(null); // Clear all sort keys to revert to the original order
		            }
		        } else {
		            // Sort the column in descending order
		            sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
		        }

		        if (!sortKeys.isEmpty()) {
		            sorter.setSortKeys(sortKeys);
		        }
		    }

		});
		
		// 상품테이블 헤드에 정렬기능 넣기 
		JTableHeader foodTableHeader = Food_table.getTableHeader();
		foodTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int columnIndex = Food_table.columnAtPoint(e.getPoint());

		        // Sort the table based on the clicked column index
		        sortFoodTableByColumn(columnIndex);
		    }

		    private void sortFoodTableByColumn(int columnIndex) {
		        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(dm2);
		        Food_table.setRowSorter(sorter);
		        List<RowSorter.SortKey> sortKeys = new ArrayList<>(sorter.getSortKeys());

		        // Check if the column is already sorted
		        boolean isColumnAlreadySorted = false;
		        for (RowSorter.SortKey sortKey : sortKeys) {
		            if (sortKey.getColumn() == columnIndex) {
		                isColumnAlreadySorted = true;
		                break;
		            }
		        }

		        // Update the sort keys
		        if (isColumnAlreadySorted) {
		            List<RowSorter.SortKey> newSortKeys = new ArrayList<>();
		            for (RowSorter.SortKey sortKey : sortKeys) {
		                if (sortKey.getColumn() == columnIndex) {
		                    SortOrder currentSortOrder = sortKey.getSortOrder();
		                    SortOrder nextSortOrder = (currentSortOrder == SortOrder.ASCENDING) ? SortOrder.DESCENDING : SortOrder.ASCENDING;
		                    newSortKeys.add(new RowSorter.SortKey(columnIndex, nextSortOrder));
		                } else {
		                    newSortKeys.add(sortKey);
		                }
		            }
		            sortKeys = newSortKeys;
		        } else {
		            sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
		        }

		        sorter.setSortKeys(sortKeys);
		    }
		});
		
		// 종료 버튼 
		JButton exitButton = new JButton("종료");
		exitButton.setBounds(670, 881, 102, 56);
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		jsp.setBounds(353, 59, 419, 382);
		jsp2.setBounds(353, 465, 419, 382);

		ctn.setLayout(null);
		ctn.add(jsp);
		ctn.add(jsp2);
		ctn.add(exitButton);
		ctn.add(search_t);
		ctn.add(search_c);
		ctn.add(search_b);
		
		JTextField search_t2 = new JTextField();
		search_t2.setBounds(135, 469, 137, 21);
		getContentPane().add(search_t2);
		
		JComboBox<String> search_c2 = new JComboBox<String>(cols_Food);
		search_c2.setBounds(38, 468, 85, 21);
		getContentPane().add(search_c2);
		
		
		JButton search_b2 = new JButton("검색");
		search_b2.setBounds(284, 468, 65, 22);
		getContentPane().add(search_b2);
		
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
	  { Object[] rowData = { 
	  food.getFOODBUNO(),
	  food.getFOODCATE(), 
	  food.getFOODNAME(), 
	  food.getFOODQUANTITY(),
	  food.getFOODPRICE(),
	  food.getPrice()
	  }; dm2.addRow(rowData); } } catch (SQLException e) {
	  e.printStackTrace(); } }
	}