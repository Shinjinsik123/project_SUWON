package game1;

//리듬과 비트(타이밍)에 관한 관리 클래스
public class Beat {
	
	private int time;
	private String noteName;
	
	public Beat(int time, String noteName) {
		super();
		this.time = time;
		this.noteName = noteName;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	
}
