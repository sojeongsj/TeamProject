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

// 미완성 입니다. 


@SuppressWarnings("serial")
public class Custom_Admin_Panel extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = {"영화번호","영화이름","예매좌석","가격"};
	
	
	public Custom_Admin_Panel() {
		//테이블 컴포넌트에 표시될 데이터를 리스트에 저장 
		
		
		//테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달 
		dm = new DefaultTableModel(null, cols);   //cols는 테이블 제목. 배열로 전달
		jt = new JTable(dm);

		String[] data = new String[4];
		
		//리스트에 저장된 데이터를 테이블 행으로 추가하기 -검색기능 관련된 flag 변수는 참고하세요.
		//						ㄴ 데이터는 배열로 전달해야 합니다. 
		
			
		
	// Container 는 프레임안에서 다른 컴포넌트들을 그룹화 역할 
		// 			다른 Pane 객체들도 포함 할 수 있습니다. 
		// 			여기서는 ScrollPane을 포함하는데, 이것은 스크롤 표시하는
		//				역할의 pane 입니다. 단순 컴포넌트가 아닌 특정 기능을 
		//				갖는 pane들이 있습니다. 
		Container ctn = getContentPane();   
		JScrollPane jsp = new JScrollPane(jt);
		// JSrollPane 에 테이블을 담아서 테이블이 내용이 많아지면 스크롤을 표시합니다.

		JLabel la1 = new JLabel("검색 단어");
		la1.setBounds(10,10,100,30);

		JTextField jtf1 = new JTextField();
		jtf1.setBounds(120,10,200,30);
		
		String[] temp = {"english","korean"};
		JComboBox<String> jc = new JComboBox<>(temp);
		
		jc.setBounds(330, 10, 100, 30);
		
		JButton btn = new JButton("단어 검색");
		btn.setBounds(440, 10, 100, 30);
		
			JButton back_btn = new JButton("돌아가기");
					back_btn.setBounds(650, 800, 100, 80);
			//ActionListner 설정하는 두번째 다른 형태 
			// implements 하지 않고 익명클래스 (구현체)
		btn.addActionListener(new ActionListener() {  
			//ActionListener 익명 구현 클래스 -- 필요한 재정의 메서드를 구현
			                 
			@Override   
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null, cols);
				jt.setModel(dm);
				for(int k=dm.getRowCount()-1;k>=0;k--)    
					// 테이블에서 기존 데이터 삭제 -> 마지막 행부터 삭제
					dm.removeRow(k);
				
				String find = jtf1.getText();
				
				}
				
			}
		);
		
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
		jsp.setBounds(10, 60, 550, 500);
		
		//컨테이너에 담기는 컴포넌트들 
		ctn.setLayout(null);
		ctn.add(btn);
		ctn.add(la1); 
		ctn.add(jtf1);
		add(jc);
		ctn.add(jsp); // JScrollPane도 컨테이너 ctn에 담기기
		setBounds(0,0,800,1000); // 프레임의 속성들 설정. 프레임의 좌표는 화면 기준
		setTitle("매출 현황");
		setVisible(true);
		
		
		JButton backButton = new JButton("돌아가기");
		backButton.setBounds(650, 800, 100, 80);

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
		
	
