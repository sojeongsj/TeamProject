package pack_CGV_Admin;

import java.awt.Button;
import java.awt.Color;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableModel;

// 미완성 입니다. 


@SuppressWarnings("serial")
public class Food_Admin_Panel extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = {"영화번호","영화이름","예매좌석","가격"};
	
	
	public Food_Admin_Panel() {
		getContentPane().setBackground(new Color(169, 182, 226));
		//테이블 컴포넌트에 표시될 데이터를 리스트에 저장 
		List<D3WORD> list = new ArrayList<>();
		list.add(new D3WORD("kkk", "와이", 1, LocalDate.parse("2021-05-01")));
		list.add(new D3WORD("yyy", "케이", 2, LocalDate.parse("2021-05-01")));
		list.add(new D3WORD("aaa", "에이", 3, LocalDate.parse("2023-06-28")));
		String find="";
		
		//테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달 
		dm = new DefaultTableModel(null, cols);

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
		
		String[] temp = {"english","korean"};
		
		
		JFrame ftemp = this;
		ctn.addInputMethodListener(null);
		
		//컨테이너에 담기는 컴포넌트들 
		ctn.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(555, 40, 97, 38);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(675, 40, 97, 38);
		getContentPane().add(btnNewButton_1);
		
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(733, 98, 17, 612);
		getContentPane().add(scrollBar_1);
		
		JScrollBar scrollBar_2 = new JScrollBar();
		scrollBar_2.setBounds(755, 98, 17, 763);
		getContentPane().add(scrollBar_2);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(131, 708, -20, 48);
		getContentPane().add(scrollBar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 98, 151, 763);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2);
		
		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		panel.add(chckbxNewCheckBox_1);
		
		Button button = new Button("New button");
		panel.add(button);
		
		Button button_1 = new Button("New button");
		panel.add(button_1);
		
		Button button_2 = new Button("New button");
		panel.add(button_2);
		
		Button button_3 = new Button("New button");
		panel.add(button_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(35, 41, 75, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setBounds(131, 41, 75, 37);
		getContentPane().add(lblNewLabel_1);
		setBounds(0,0,800,1000); // 프레임의 속성들 설정. 프레임의 좌표는 화면 기준
		setTitle("매출 현황");
		setVisible(true);
		
		JButton exitButton = new JButton("종료");
		exitButton.setBounds(650, 800, 100, 80);

		// "종료" 버튼에 ActionListener 등록
		exitButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	setVisible(false);
		    }
		});

		// 컨테이너에 "종료" 버튼 추가
		ctn.add(exitButton);
	}
	
	}
		
	
