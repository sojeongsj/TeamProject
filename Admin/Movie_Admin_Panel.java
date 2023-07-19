package Admin;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import kioskDAO.MovieDao;
import kioskDTO.MovieDto;

// 미완성 입니다. 

@SuppressWarnings("serial")
public class Movie_Admin_Panel extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = { "영화번호", "영화이름", "개봉날짜", "영화등급", "가격" };
	private String selectedMovieNo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public Movie_Admin_Panel() {
		getContentPane().setBackground(new Color(255, 255, 255));
		// 테이블 컴포넌트에 표시될 데이터를 리스트에 저장
		 Container ctn = getContentPane();
		    dm = new DefaultTableModel(null, cols);
		    jt = new JTable(dm);
		    jt.setBackground(Color.WHITE);
		// 테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달

		ctn.setLayout(null);
		setBounds(0, 0, 800, 1000); // 프레임의 속성들 설정. 프레임의 좌표는 화면 기준
		setTitle("영화 정보 ");
		setVisible(true);

		JLabel tiTle = new JLabel("영화 관리 시스템", JLabel.CENTER);
		tiTle.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		tiTle.setBounds(50, 10, 700, 75);
		
		JTableHeader movieTableHeader = jt.getTableHeader();
		movieTableHeader.setBounds(jt.getX(), jt.getY() - 20, jt.getWidth(), 20);
		ctn.add(movieTableHeader);

		ctn.add(tiTle);
		movieTableHeader.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int columnIndex = jt.columnAtPoint(e.getPoint());

		        // Sort the table based on the clicked column index
		        sortMovieTableByColumn(columnIndex);
		    }

		    private void sortMovieTableByColumn(int columnIndex) {
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
		
		//광고배너
		
		ImageIcon icon = new ImageIcon(theaterAdmin.class.getResource("/image/ad_soulRent.png"));
		getContentPane().setLayout(null);
		
		JLabel icon1 = new JLabel("New label");
		icon1.setBounds(0,594,800,300);
		icon1.setIcon(icon);
		getContentPane().add(icon1);
		
		
		// 수정하기 버튼	
		JButton UpdateBtn = new JButton("수정하기");
		UpdateBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		UpdateBtn.setBounds(20, 899, 99, 41);

		UpdateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jt.getSelectedRow();
				if (selectedRow != -1) {
		            String movieNo = (String) dm.getValueAt(selectedRow, 0);
		            String movieTitle = (String) dm.getValueAt(selectedRow, 1);
		            String movieDateString = (String) dm.getValueAt(selectedRow, 2);
		            String movieGrade = (String) dm.getValueAt(selectedRow, 3);
		            String moviePrice = (String) dm.getValueAt(selectedRow, 4);

		            JFrame inputFrame = new JFrame();
					inputFrame.setBounds(100, 100, 400, 300);
					inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					inputFrame.setTitle("영화 정보 수정");

					JLabel cauTion = new JLabel("※개봉날짜 입력 시 YYYY-MM-DD 형식 유지※");
					cauTion.setBounds(50,25,300,20);
					
					// 개봉 날짜 입력 필드
					JLabel movieDateLabel = new JLabel("개봉 날짜:");
					movieDateLabel.setBounds(50, 50, 80, 20);

					JTextField movieDateTextField = new JTextField(movieDateString.replace("00:00:00", ""));
					movieDateTextField.setBounds(150, 50, 200, 20);

					// 영화 등급 입력 필드
					JLabel movieGradeLabel = new JLabel("영화 등급:");
					movieGradeLabel.setBounds(50, 100, 80, 20);

					JTextField movieGradeTextField = new JTextField(movieGrade);
					movieGradeTextField.setBounds(150, 100, 200, 20);

					// 영화 가격 입력 필드
					JLabel moviePriceLabel = new JLabel("영화 가격:");
					moviePriceLabel.setBounds(50, 150, 80, 20);

					JTextField moviePriceTextField = new JTextField(moviePrice);
					moviePriceTextField.setBounds(150, 150, 200, 20);

					Container inputContainer = inputFrame.getContentPane();
					inputContainer.setLayout(null);

					JButton confirmBtn = new JButton("확인");

					confirmBtn.setBounds(150, 200, 80, 30);
					confirmBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String newMovieDateString = movieDateTextField.getText();
							String newMovieGrade = movieGradeTextField.getText();
							String newMoviePrice = moviePriceTextField.getText();

							//Timestamp newMovieDate = Timestamp.valueOf(newMovieDateString);
							String newMovieDate = String.valueOf(newMovieDateString);
							dm.setValueAt(newMovieDate, selectedRow, 2);
							dm.setValueAt(newMovieGrade, selectedRow, 3);
							dm.setValueAt(newMoviePrice, selectedRow, 4);

							MovieDto movie = new MovieDto(movieNo, movieTitle, newMovieDate, newMovieGrade,
									Integer.parseInt(newMoviePrice));
							MovieDao movieDao = MovieDao.getDao();

							try {
								movieDao.update(movie);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							refreshMovieData();
							inputFrame.dispose();
						}
					});
					inputContainer.add(cauTion);
					inputContainer.add(movieDateLabel);
					inputContainer.add(movieDateTextField);
					inputContainer.add(moviePriceLabel);
					inputContainer.add(moviePriceTextField);
					inputContainer.add(movieGradeLabel);
					inputContainer.add(movieGradeTextField);
					inputContainer.add(confirmBtn);

					inputFrame.setVisible(true);
					refreshMovieData();
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
					String movieNo = (String) dm.getValueAt(selectedRow,0); // 선택된 행의 영화 번호를 가져옴
					try {
						MovieDao.getDao().delete(movieNo); // 영화 번호를 인자로 delete 메서드 호출
						refreshMovieData(); // 데이터 갱신
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
	

		// 추가 기능
		JButton insertBtn = new JButton("추가하기");
		insertBtn.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		insertBtn.setBounds(280, 899, 99, 41);
		ctn.add(insertBtn);

		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame inputFrame = new JFrame();
				inputFrame.setBounds(100, 100, 400, 300);
				inputFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				inputFrame.setTitle("영화 정보 추가");

				// 영화 번호 입력 필드
				JLabel movieNoLabel = new JLabel("영화번호");
				movieNoLabel.setBounds(20, 20, 100, 20);

				JTextField movieNoTextField = new JTextField();
				movieNoTextField.setBounds(150, 20, 200, 20);

				// 영화 제목 입력 필드
				JLabel movieTitleLabel = new JLabel("영화 제목:");
				movieTitleLabel.setBounds(20, 60, 100, 20);

				JTextField movieTitleTextField = new JTextField();
				movieTitleTextField.setBounds(150, 60, 200, 20);

				// 개봉 날짜 입력 필드
				JLabel movieDateLabel = new JLabel("개봉 날짜:");
				movieDateLabel.setBounds(20, 100, 100, 20);

				JTextField movieDateTextField = new JTextField();
				movieDateTextField.setBounds(150, 100, 200, 20);

				// 영화 등급 입력 필드
				JLabel movieGradeLabel = new JLabel("영화 등급:");
				movieGradeLabel.setBounds(20, 140, 100, 20);

				JTextField movieGradeTextField = new JTextField();
				movieGradeTextField.setBounds(150, 140, 200, 20);

				// 영화 가격 입력 필드
				JLabel moviePriceLabel = new JLabel("영화 가격:");
				moviePriceLabel.setBounds(20, 180, 100, 20);

				JTextField moviePriceTextField = new JTextField();
				moviePriceTextField.setBounds(150, 180, 200, 20);

				Container inputContainer = inputFrame.getContentPane();
				inputContainer.setLayout(null);

				JButton confirmBtn = new JButton("확인");
				confirmBtn.setBounds(150, 220, 80, 30);
				confirmBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String newmovieNo = movieNoTextField.getText();
						String newmovieTitle = movieTitleTextField.getText();

						String newmovieDate1 = movieDateTextField.getText();
//						Timestamp newMovieDate2 = Timestamp.valueOf(newmovieDate1);
						String newmovieGrade = movieGradeTextField.getText();
						int newmoviePrice = Integer.parseInt(moviePriceTextField.getText());
						// System.out.println(newmovieDate1); 오류 확인용
						try {
							MovieDto movie = new MovieDto(newmovieNo, newmovieTitle, newmovieDate1,
									newmovieGrade, newmoviePrice);
							MovieDao movieDao = MovieDao.getDao();
							movieDao.insert(movie);
							refreshMovieData(); // 데이터 갱신
						} catch (SQLException ex) {
							ex.printStackTrace();
						}

						inputFrame.dispose();
					}
				});

				inputContainer.add(movieNoLabel);
				inputContainer.add(movieNoTextField);
				inputContainer.add(movieTitleLabel);
				inputContainer.add(movieTitleTextField);
				inputContainer.add(movieDateLabel);
				inputContainer.add(movieDateTextField);
				inputContainer.add(movieGradeLabel);
				inputContainer.add(movieGradeTextField);
				inputContainer.add(moviePriceLabel);
				inputContainer.add(moviePriceTextField);
				inputContainer.add(confirmBtn);

				inputFrame.setVisible(true);
			}
		});
		
		//돌아가기 버튼
		JButton backButton = new JButton("돌아가기");
		backButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		backButton.setBounds(673, 899, 99, 41);

		try {
			fetchMovieData(); // 패널이 처음 생성될 때 데이터를 가져옴
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// "돌아가기" 버튼에 ActionListener 등록
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); // 패널 숨기기
				refreshMovieData();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 112, 502, 472);
		getContentPane().add(scrollPane);
		jt = new JTable(dm);
		scrollPane.setViewportView(jt);
		
						
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
						                //rowDataLabels[i].setText(cols[i] + ": " + rowData.toString());
						                
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
						                    case 4:
						                        textField_4.setText(dm.getValueAt(selectedRow, i).toString());
						                        break;
						                }
						            }
						        }
						    }
						});
		ctn.add(backButton);
		ctn.add(UpdateBtn);
		
		textField = new JTextField();
		textField.setBounds(132, 112, 117, 27);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("영화 번호");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel.setBounds(20, 109, 99, 32);
		getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(132, 187, 117, 27);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("영화 제목");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_1.setBounds(20, 184, 99, 32);
		getContentPane().add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(132, 275, 117, 27);
		getContentPane().add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("개봉 날짜");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_2.setBounds(20, 272, 99, 32);
		getContentPane().add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(132, 372, 117, 27);
		getContentPane().add(textField_3);
		
		JLabel lblNewLabel_3 = new JLabel("관람 등급");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_3.setBounds(20, 369, 99, 32);
		getContentPane().add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(132, 471, 117, 27);
		getContentPane().add(textField_4);
		
		JLabel lblNewLabel_4 = new JLabel("영화 가격");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblNewLabel_4.setBounds(20, 468, 99, 32);
		getContentPane().add(lblNewLabel_4);

	}

	private void refreshMovieData() {
		try {
			dm.setRowCount(0); // 테이블의 기존 데이터 삭제
			fetchMovieData(); // 테이블 데이터 다시 가져오기
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void fetchMovieData() throws SQLException {
	    MovieDao movieDao = MovieDao.getDao();
	    List<MovieDto> movies = movieDao.MovieselectAll();

	    for (MovieDto movie : movies) {
	        String movieNo = movie.getMovieNO();
	        String movieTitle = movie.getMovieTitle();
	        String movieDate = movie.getMovieDate();
	        String movieGrade = movie.getMovieGrade();
	        int moviePrice = movie.getMoviePrice();

	        String formattedPrice = formatPrice(moviePrice); // 가격 포맷팅

	        String[] rowData = { movieNo, movieTitle, movieDate.toString(), movieGrade, formattedPrice };
	        dm.addRow(rowData);
	    }

	    jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent e) {
	            int selectedRow = jt.getSelectedRow();
	            if (selectedRow != -1) {
	                selectedMovieNo = (String) dm.getValueAt(selectedRow, 0); // 선택된 행의 영화 번호 저장
	            }
	        }
	    });
	}

	private String formatPrice(int price) {
	    return String.format("%,d", price);
	}
}
