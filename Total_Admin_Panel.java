package pack_CGV_Admin;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;

// 미완성 입니다. 


@SuppressWarnings("serial")
public class Total_Admin_Panel extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = {"영화번호","영화이름","예매좌석","가격"};
	
	
	public Total_Admin_Panel() {
		//테이블 컴포넌트에 표시될 데이터를 리스트에 저장 
		List<D3WORD> list = new ArrayList<>();
		list.add(new D3WORD("kkk", "와이", 1, LocalDate.parse("2021-05-01")));
		list.add(new D3WORD("yyy", "케이", 2, LocalDate.parse("2021-05-01")));
		list.add(new D3WORD("aaa", "에이", 3, LocalDate.parse("2023-06-28")));
		String find="";
		
		//테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달 
		dm = new DefaultTableModel(null, cols);   //cols는 테이블 제목. 배열로 전달
		jt = new JTable(dm);

		String[] data = new String[4];
		
		//리스트에 저장된 데이터를 테이블 행으로 추가하기 -검색기능 관련된 flag 변수는 참고하세요.
		//						ㄴ 데이터는 배열로 전달해야 합니다. 
		for(int i=0;i<list.size();i++) {
			D3WORD temp = list.get(i);
			boolean flag;
			if(find.equals("")) flag =true;
			else
				flag = temp.getEnglish().equals(find);
			
			if(flag) {
				data[0] = temp.getEnglish(); // Word 객체의 영어를 배열로 저장
				data[1] = temp.getKorean(); // Word 객체의 한글을 문자열 배열로 저장
				data[2] = String.valueOf(temp.getLevel()); // Word 객체의 레벨을 문자열 배열로 저장
				data[3] = temp.getWday().toString(); // Word 객체의 날짜를 문자열 배열로 저장
				dm.addRow(data); //테이블 모델 객체에 배열 추가 
			}
		}
	// Container 는 프레임안에서 다른 컴포넌트들을 그룹화 역할 
		// 			다른 Pane 객체들도 포함 할 수 있습니다. 
		// 			여기서는 ScrollPane을 포함하는데, 이것은 스크롤 표시하는
		//				역할의 pane 입니다. 단순 컴포넌트가 아닌 특정 기능을 
		//				갖는 pane들이 있습니다. 
		Container ctn = getContentPane();   
		JScrollPane jsp = new JScrollPane(jt);
		
		String[] temp = {"english","korean"};
		
			JButton back_btn = new JButton("돌아가기");
					back_btn.setBounds(672, 857, 100, 80);
		
		JFrame ftemp = this;
		jt.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			 int row = jt.getSelectedRow();
			 JOptionPane.showMessageDialog(ftemp, "필수 입력내용입니다." + row);
			}
		});
		JButton exitButton = new JButton("종료");
		exitButton.setBounds(650, 800, 100, 80);
		
		// "종료" 버튼에 ActionListener 등록
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 프로그램 종료
			}
		});
		ctn.addInputMethodListener(null);
		// JScrollPane의 좌표(컨테이너기준)와 크기
		jsp.setBounds(12, 20, 760, 351);
		
		//컨테이너에 담기는 컴포넌트들 
		ctn.setLayout(null);
		
		ctn.add(jsp); // JScrollPane도 컨테이너 ctn에 담기기
		setBounds(0,0,800,1000); // 프레임의 속성들 설정. 프레임의 좌표는 화면 기준
		setTitle("매출 현황");
		setVisible(true);
		JScrollPane jsp_1 = new JScrollPane((Component) null);
		jsp_1.setBounds(12, 432, 760, 415);
		getContentPane().add(jsp_1);
		
		JButton backButton = new JButton("돌아가기");
		backButton.setBounds(672, 871, 100, 80);

		// "돌아가기" 버튼에 ActionListener 등록
		backButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        setVisible(false); // 패널 숨기기
		    }
		});

		// 컨테이너에 "돌아가기" 버튼 추가
		ctn.add(backButton);

		// 이후 코드 생략...
	
	
		
	}
	}
		
	
