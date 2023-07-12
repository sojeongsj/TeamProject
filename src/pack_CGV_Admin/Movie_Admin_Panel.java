package pack_CGV_Admin;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import movie.MovieDao;
import movie.MovieDto;

// 미완성 입니다. 

@SuppressWarnings("serial")
public class Movie_Admin_Panel extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = { "영화번호", "영화이름", "개봉날짜", "영화등급", "가격" };
	private String selectedMovieNo;
	public Movie_Admin_Panel() {
		// 테이블 컴포넌트에 표시될 데이터를 리스트에 저장

		// 테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달
		Container ctn = getContentPane();
		dm = new DefaultTableModel(null, cols);
		jt = new JTable(dm);
		jt.setBounds(325, 95, 450, 800);
		
		ctn.setLayout(null);
		setBounds(0, 0, 800, 1000); // 프레임의 속성들 설정. 프레임의 좌표는 화면 기준
		setTitle("영화 정보 ");
		setVisible(true);

		JButton UpdateBtn = new JButton("수정하기");
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

					// 개봉 날짜 입력 필드
					JLabel movieDateLabel = new JLabel("개봉 날짜:");
					movieDateLabel.setBounds(50, 50, 80, 20);

					JTextField movieDateTextField = new JTextField(movieDateString);
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
							int newMoviePrice = Integer.parseInt(moviePriceTextField.getText());

							Timestamp newMovieDate = Timestamp.valueOf(newMovieDateString);
			
							dm.setValueAt(newMovieDate, selectedRow, 2);
							dm.setValueAt(newMovieGrade, selectedRow, 3);
							dm.setValueAt(newMoviePrice, selectedRow, 4);

							MovieDto movie = new MovieDto(movieNo, movieTitle,newMovieDate, newMovieGrade,
									newMoviePrice);
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
		}); // 수정하기 버튼

		JButton deletebtn = new JButton("삭제하기");
		deletebtn.setBounds(130, 899, 99, 41);
		ctn.add(deletebtn);

		deletebtn.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = jt.getSelectedRow();  // 선택된 행의 인덱스를 가져옴
		        if (selectedRow != -1) {
		            String movieNo = (String) dm.getValueAt(selectedRow, 0);  // 선택된 행의 영화 번호를 가져옴

		            try {
		                MovieDao.getDao().delete(movieNo);  // 영화 번호를 인자로 delete 메서드 호출
		                refreshMovieData();  // 데이터 갱신
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		// 삭제하기 기능

		// 추가 기능
		JButton insertBtn = new JButton("추가하기");
		insertBtn.setBounds(250, 899, 99, 41);
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
		                Timestamp newMovieDate2 = Timestamp.valueOf(newmovieDate1);
		                String newmovieGrade = movieGradeTextField.getText();
		                int newmoviePrice = Integer.parseInt(moviePriceTextField.getText());
						System.out.println(newmovieDate1);
		                try {
		                    MovieDto movie = new MovieDto(newmovieNo,newmovieTitle,newMovieDate2,newmovieGrade,newmoviePrice);
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

		JButton backButton = new JButton("돌아가기");
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
		ctn.add(jt);
		ctn.add(backButton);
		ctn.add(UpdateBtn);
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
	        Timestamp movieDate = movie.getMovieDate();
	        String movieGrade = movie.getMovieGrade();
	        int moviePrice = movie.getMoviePrice();

	        String[] rowData = { movieNo, movieTitle, movieDate.toString(), movieGrade, String.valueOf(moviePrice) };
	        dm.addRow(rowData);
	    }

	    jt.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent e) {
	            int selectedRow = jt.getSelectedRow();
	            if (selectedRow != -1) {
	                selectedMovieNo = (String) dm.getValueAt(selectedRow, 0);  // 선택된 행의 영화 번호 저장
	            }
	        }
	    });
}
}
