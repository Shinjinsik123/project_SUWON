package game1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

//���� ���� ��� ������ ���� ������ �ϴ� Ŭ����
public class Music extends Thread{

	private Player player; /*�ܺο��� �ٿ���� ���̺귯���� �ϳ�*/
	private boolean isRepeat;/*���� ���ѹݺ��Ǵ��� �ѹ��� �Ǵ���*/
	private File file;
	private FileInputStream finput;
	private BufferedInputStream bfinput;
	
	
	public Music(String name, boolean isRepeat) {
		try {
			this.isRepeat = isRepeat;
			file = new File(Main.class.getResource("../music/"+name).toURI()); /*music�̶�� �����ȿ� �ִ� ������ �������� ����*/
			finput = new FileInputStream(file);
			bfinput = new BufferedInputStream(finput); /*�ش������� ���ۿ� ��Ƽ� �о� �ü� �ֵ��� �ϴ°�*/
			player = new Player(bfinput); /*player�� ��� ������ ����*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int remainingTime() { //getTime(���� �޼ҵ��)  /*���� ����Ǵ� ������ � ��ġ�� ����Ǵ���*/
		if(player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() { /*�������� ���ᰡ���ϰ�*/
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
			}while(isRepeat);    /*isRepeat���� true�� ���ѹݺ�*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
