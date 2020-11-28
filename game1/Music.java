package game1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

//음악 관련 모든 정보에 대한 저장을 하는 클래스
public class Music extends Thread{

	private Player player; /*외부에서 다운받은 라이브러리중 하나*/
	private boolean isRepeat;/*곡이 무한반복되는지 한번만 되는지*/
	private File file;
	private FileInputStream finput;
	private BufferedInputStream bfinput;
	
	
	public Music(String name, boolean isRepeat) {
		try {
			this.isRepeat = isRepeat;
			file = new File(Main.class.getResource("../music/"+name).toURI()); /*music이라는 폴더안에 있는 폴더를 가져오는 역할*/
			finput = new FileInputStream(file);
			bfinput = new BufferedInputStream(finput); /*해당파일을 버퍼에 담아서 읽어 올수 있도록 하는것*/
			player = new Player(bfinput); /*player는 행당 파일을 담음*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int remainingTime() { //getTime(원래 메소드명)  /*현재 실행되는 음악이 어떤 위치에 실행되는지*/
		if(player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() { /*언제든지 종료가능하게*/
		isRepeat = false;
		player.close();
		this.interrupt();
	}
	public void run() {
		try {
			do {
				player.play();
				finput = new FileInputStream(file);
				bfinput = new BufferedInputStream(finput);
				player = new Player(bfinput);
			}while(isRepeat);    /*isRepeat값이 true면 무한반복*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
