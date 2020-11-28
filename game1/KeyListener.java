package game1;

//게임하는 동안 유저로부터 오는 키 이벤트를 잡는것에 대한 클래스
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter{

	@Override
	public void keyPressed(KeyEvent e) {
		if(BeatMaster.game == null)return;
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			BeatMaster.game.pressSBtn();
			
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			BeatMaster.game.pressDBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			BeatMaster.game.pressFBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			BeatMaster.game.pressSpaceBar();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			BeatMaster.game.pressJBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			BeatMaster.game.pressKBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			BeatMaster.game.pressLBtn();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(BeatMaster.game == null)return;
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			BeatMaster.game.releaseSBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_D) {
			BeatMaster.game.releaseDBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_F) {
			BeatMaster.game.releaseFBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			BeatMaster.game.releaseSpaceBar();
		}else if(e.getKeyCode() == KeyEvent.VK_J) {
			BeatMaster.game.releaseJBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_K) {
			BeatMaster.game.releaseKBtn();
		}else if(e.getKeyCode() == KeyEvent.VK_L) {
			BeatMaster.game.releaseLBtn();
		}
	}
}
