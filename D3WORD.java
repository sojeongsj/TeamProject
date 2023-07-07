package pack_CGV_Admin;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

// Serializable 인터페이스 사용 - 객체의 직렬화
public class D3WORD implements Serializable {
	
// Serializable 에서 권장하는 상수 선언. 없으면 경고(노란줄).	
	private static final long serialVersionUID = 1L;
	private String english;
	private String korean;
	private int level;    
	private LocalDate wday;    
	
//생성자
	public D3WORD() { }
	
	public D3WORD(String english, String korean, int level, LocalDate wday) {
		super();
		this.english = english;
		this.korean = korean;
		this.level = level;
		this.wday = wday;
	}

	
	//getter,setter
	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getKorean() {
		return korean;
	}

	public void setKorean(String korean) {
		this.korean = korean;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public LocalDate getWday() {
		return wday;
	}

	public void setWday(LocalDate wday) {
		this.wday = wday;
	}
	
	
	
	
}
