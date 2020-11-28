package game1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

//게임 기능에 대한 클래스

public class Game extends Thread {

	private Image notePathLineImage = new ImageIcon(Main.class.getResource("../images/notePathLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/JudgementLine.png")).getImage();
	private Image gameGraphImage = new ImageIcon(Main.class.getResource("../images/gameGraph.png")).getImage();
	private Image notePathSImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathDImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathFImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathSpaceImage1 = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathSpaceImage2 = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathJImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathKImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image notePathLImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
	private Image scorebackgroundImage = new ImageIcon(Main.class.getResource("../images/scorebackground.png")).getImage();
	private Image effect1Image;
	private Image effect2Image;
	private Image judgeImage;

	// KeyImage(S,D,F,Space,J,K,L)
	private Image keySImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	private Image keyDImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	private Image keyFImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	private Image keySpaceImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	private Image keyJImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	private Image keyKImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	private Image keyLImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();

	private String musicTitle;
	public String difficulty;
	public String titleName;
	private Music gameMusic;
	private int playtime;

	Score score = new Score();

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle,int playtime) {
		this.difficulty = difficulty;
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		this.playtime = playtime;
		gameMusic = new Music(this.musicTitle, false);
	}

	public void frameDraw(Graphics2D g) {
		g.drawImage(notePathSImage, 228, 30, null);
		g.drawImage(notePathDImage, 332, 30, null);
		g.drawImage(notePathFImage, 436, 30, null);
		g.drawImage(notePathSpaceImage1, 540, 30, null);
		g.drawImage(notePathSpaceImage2, 640, 30, null);
		g.drawImage(notePathJImage, 744, 30, null);
		g.drawImage(notePathKImage, 848, 30, null);
		g.drawImage(notePathLImage, 952, 30, null);
		g.drawImage(notePathLineImage, 224, 30, null);
		g.drawImage(notePathLineImage, 328, 30, null);
		g.drawImage(notePathLineImage, 432, 30, null);
		g.drawImage(notePathLineImage, 536, 30, null);
		g.drawImage(notePathLineImage, 740, 30, null);
		g.drawImage(notePathLineImage, 844, 30, null);
		g.drawImage(notePathLineImage, 948, 30, null);
		g.drawImage(notePathLineImage, 1052, 30, null);
		g.drawImage(gameGraphImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		//프레임에 있는 노트에 모든것을 만듦
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
			}
			if (!note.isProcessing()) {
				noteList.remove(i);  /*현재노트에서 실행되지 않으면 지워버림*/
				i--;
			} else {
				note.frameDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.white);
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("SPACE", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawImage(effect1Image, 470, 430, null);
		g.drawImage(judgeImage, 450, 450, null);
		g.drawString(score.getScore(), 565, 702);

		g.drawImage(keySImage, 228, 580, null);
		g.drawImage(keyDImage, 332, 580, null);
		g.drawImage(keyFImage, 436, 580, null);
		g.drawImage(keySpaceImage, 540, 580, null);
		g.drawImage(keySpaceImage, 640, 580, null);
		g.drawImage(keyJImage, 744, 580, null);
		g.drawImage(keyKImage, 848, 580, null);
		g.drawImage(keyLImage, 952, 580, null);
		
		
	}
	//등수와 스코어를포함한 결과 나오도록 하는 메소드
	public void resultFrame(Graphics2D g) {
		
		String grade=null;
		int totalScore = Integer.parseInt(score.getScore());
		if(totalScore > (300*100*0.9)) {
			 grade = "S";
		}else if(totalScore > (200*100*0.6)) {
		     grade = "A";
		}else if(totalScore > (100*100*0.4)) {
			 grade = "B";
		}else if(totalScore >= 0) {
			 grade = "C";
		}
		g.drawImage(scorebackgroundImage, 253, 45, null);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		g.setColor(Color.white);
		g.drawString(score.getScore(), 500, 290);
		g.setColor(Color.pink);
		g.drawString(grade, 600, 400);
		
	}
	
	
	public void pressSBtn() {
		judge("S");
		notePathSImage = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keySImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseSBtn() {
		notePathSImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keySImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	public void pressDBtn() {
		judge("D");
		notePathDImage = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keyDImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseDBtn() {
		notePathDImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keyDImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	public void pressFBtn() {
		judge("F");
		notePathFImage = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keyFImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseFBtn() {
		notePathFImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keyFImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	public void pressJBtn() {
		judge("J");
		notePathJImage = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keyJImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseJBtn() {
		notePathJImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keyJImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	public void pressKBtn() {
		judge("K");
		notePathKImage = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keyKImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseKBtn() {
		notePathKImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keyKImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	public void pressLBtn() {
		judge("L");
		notePathLImage = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keyLImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseLBtn() {
		notePathLImage = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keyLImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	public void pressSpaceBar() {
		judge("Space");
		notePathSpaceImage1 = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		notePathSpaceImage2 = new ImageIcon(Main.class.getResource("../images/notePathPressed.png")).getImage();
		keySpaceImage = new ImageIcon(Main.class.getResource("../images/keyPressed.png")).getImage();
		new Music("drumBig1.mp3", false).start();
	}

	public void releaseSpaceBar() {
		notePathSpaceImage1 = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		notePathSpaceImage2 = new ImageIcon(Main.class.getResource("../images/notePath.png")).getImage();
		keySpaceImage = new ImageIcon(Main.class.getResource("../images/keyBasic.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	public void dropNotes(String titleName) {
		Beat[] beats = null;

		if (titleName.equals("Zico - Anysong") && difficulty.equals("Hard")) {
			int startTime = 5000 - Main.REACH_TIME*1000;
			int gap = 125;
			beats = new Beat[] { 	
					new Beat(startTime + gap * 1, "F"), new Beat(startTime + gap * 5, "K"),new Beat(startTime + gap * 7, "F"),
					new Beat(startTime + gap * 10, "K"),new Beat(startTime + gap * 11, "K"), new Beat(startTime + gap * 13, "S"),
					new Beat(startTime + gap * 16, "K"), new Beat(startTime + gap * 17, "K"),new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 20, "K"),new Beat(startTime + gap * 21, "S"), new Beat(startTime + gap * 22, "F"),
					new Beat(startTime + gap * 24, "F"), new Beat(startTime + gap * 26, "K"),new Beat(startTime + gap * 27, "K"),
					new Beat(startTime + gap * 30, "F"),new Beat(startTime + gap * 31, "S"), new Beat(startTime + gap * 32, "S"),
					new Beat(startTime + gap * 36, "D"),new Beat(startTime + gap * 37, "D"),new Beat(startTime + gap * 39, "K"),
					new Beat(startTime + gap * 42, "F"),new Beat(startTime + gap * 44, "K"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 50, "F"), new Beat(startTime + gap * 52, "D"),new Beat(startTime + gap * 53, "L"),
					new Beat(startTime + gap * 56, "J"),new Beat(startTime + gap * 58, "J"), new Beat(startTime + gap * 60, "S"),
					new Beat(startTime + gap * 62, "S"), new Beat(startTime + gap * 64, "K"),new Beat(startTime + gap * 66, "K"),
					new Beat(startTime + gap * 69, "S"),new Beat(startTime + gap * 73, "F"), new Beat(startTime + gap * 75, "F"),
					new Beat(startTime + gap * 78, "K"), new Beat(startTime + gap * 80, "K"),
					new Beat(startTime + gap * 81, "D"), new Beat(startTime + gap * 83, "J"),
					new Beat(startTime + gap * 85, "S"), new Beat(startTime + gap * 86, "K"),
					new Beat(startTime + gap * 97, "Space"), new Beat(startTime + gap * 99, "J"),
					new Beat(startTime + gap * 100, "S"), new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 104, "D"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 110, "D"), new Beat(startTime + gap * 115, "S"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 126, "K"), new Beat(startTime + gap * 127, "Space"),
					new Beat(startTime + gap * 129, "F"), new Beat(startTime + gap * 130, "K"),
					new Beat(startTime + gap * 132, "K"), new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 137, "D"), new Beat(startTime + gap * 139, "K"),
					new Beat(startTime + gap * 142, "F"), new Beat(startTime + gap * 144, "F"),
					new Beat(startTime + gap * 146, "K"), new Beat(startTime + gap * 150, "F"),
					new Beat(startTime + gap * 152, "J"), new Beat(startTime + gap * 153, "F"),
					new Beat(startTime + gap * 156, "J"), new Beat(startTime + gap * 158, "F"),
					new Beat(startTime + gap * 10, "K"), new Beat(startTime + gap * 160, "S"),
					new Beat(startTime + gap * 162, "S"), new Beat(startTime + gap * 164, "K"),
					new Beat(startTime + gap * 166, "K"), new Beat(startTime + gap * 169, "Space"),
					new Beat(startTime + gap * 170, "J"), new Beat(startTime + gap * 171, "S"),
					new Beat(startTime + gap * 174, "K"), new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 178, "D"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 182, "K"), new Beat(startTime + gap * 183, "J"),
					new Beat(startTime + gap * 186, "K"), new Beat(startTime + gap * 190, "D"),
					new Beat(startTime + gap * 194, "Space"), new Beat(startTime + gap * 198, "S"),
					new Beat(startTime + gap * 205, "F"), new Beat(startTime + gap * 212, "D"),
					new Beat(startTime + gap * 216, "F"), new Beat(startTime + gap * 219, "K"),
					new Beat(startTime + gap * 220, "F"), new Beat(startTime + gap * 224, "Space"),
					new Beat(startTime + gap * 225, "K"), new Beat(startTime + gap * 227, "K"),
					new Beat(startTime + gap * 229, "F"), new Beat(startTime + gap * 230, "S"),
					new Beat(startTime + gap * 232, "K"), new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 237, "F"), new Beat(startTime + gap * 239, "K"),
					new Beat(startTime + gap * 242, "F"), new Beat(startTime + gap * 243, "J"),
					new Beat(startTime + gap * 246, "K"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 251, "D"), new Beat(startTime + gap * 253, "L"),
					new Beat(startTime + gap * 256, "J"), new Beat(startTime + gap * 257, "K"),
					new Beat(startTime + gap * 260, "S"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 263, "F"), new Beat(startTime + gap * 266, "K"),
					new Beat(startTime + gap * 269, "S"), new Beat(startTime + gap * 270, "K"),
					new Beat(startTime + gap * 271, "S"), new Beat(startTime + gap * 274, "K"),
					new Beat(startTime + gap * 275, "K"), new Beat(startTime + gap * 278, "D"),
					new Beat(startTime + gap * 281, "F"), new Beat(startTime + gap * 282, "F"),
					new Beat(startTime + gap * 283, "J"), new Beat(startTime + gap * 286, "K"),
					new Beat(startTime + gap * 287, "K"), new Beat(startTime + gap * 297, "Space"),
					new Beat(startTime + gap * 300, "S"), new Beat(startTime + gap * 301, "K"),
					new Beat(startTime + gap * 302, "D"), new Beat(startTime + gap * 306, "F"),
					new Beat(startTime + gap * 307, "K"), new Beat(startTime + gap * 310, "J"),
					new Beat(startTime + gap * 320, "S"), new Beat(startTime + gap * 321, "J"),
					new Beat(startTime + gap * 322, "L"), new Beat(startTime + gap * 324, "F"),
					new Beat(startTime + gap * 326, "F"), new Beat(startTime + gap * 327, "K"),
					new Beat(startTime + gap * 329, "F"), new Beat(startTime + gap * 330, "J"),
					new Beat(startTime + gap * 332, "K"), new Beat(startTime + gap * 336, "D"),
					new Beat(startTime + gap * 337, "S"), new Beat(startTime + gap * 339, "K"),
					new Beat(startTime + gap * 342, "F"), new Beat(startTime + gap * 343, "F"),
					new Beat(startTime + gap * 346, "K"), new Beat(startTime + gap * 350, "F"),
					new Beat(startTime + gap * 351, "K"), new Beat(startTime + gap * 353, "L"),
					new Beat(startTime + gap * 356, "J"), new Beat(startTime + gap * 357, "K"),
					new Beat(startTime + gap * 360, "S"), new Beat(startTime + gap * 362, "S"),
					new Beat(startTime + gap * 363, "F"), new Beat(startTime + gap * 366, "K"),
					new Beat(startTime + gap * 369, "L"), new Beat(startTime + gap * 370, "D"),
					new Beat(startTime + gap * 371, "S"), new Beat(startTime + gap * 374, "K"),
					new Beat(startTime + gap * 375, "F"), new Beat(startTime + gap * 378, "D"),
					new Beat(startTime + gap * 381, "F"), new Beat(startTime + gap * 382, "K"),
					new Beat(startTime + gap * 383, "J"), new Beat(startTime + gap * 384, "Space"),
					new Beat(startTime + gap * 385, "F"), new Beat(startTime + gap * 388, "S"),
					new Beat(startTime + gap * 392, "D"), new Beat(startTime + gap * 393, "J"),
					new Beat(startTime + gap * 396, "F"), new Beat(startTime + gap * 400, "S"),
					new Beat(startTime + gap * 401, "K"), new Beat(startTime + gap * 404, "F"),
					new Beat(startTime + gap * 407, "K"), new Beat(startTime + gap * 408, "L"),
					new Beat(startTime + gap * 410, "F"), new Beat(startTime + gap * 412, "K"),
					new Beat(startTime + gap * 413, "S"), new Beat(startTime + gap * 416, "D"),
					new Beat(startTime + gap * 419, "K"), new Beat(startTime + gap * 420, "F"),
					new Beat(startTime + gap * 422, "F"), new Beat(startTime + gap * 426, "J"),
					new Beat(startTime + gap * 427, "D"), new Beat(startTime + gap * 430, "F"),
					new Beat(startTime + gap * 433, "L"), new Beat(startTime + gap * 434, "K"),
					new Beat(startTime + gap * 436, "J"), new Beat(startTime + gap * 440, "S"),
					new Beat(startTime + gap * 441, "F"), new Beat(startTime + gap * 442, "F"),
					new Beat(startTime + gap * 446, "K"), new Beat(startTime + gap * 447, "J"),
					new Beat(startTime + gap * 449, "S"), new Beat(startTime + gap * 455, "F"),
					new Beat(startTime + gap * 456, "K"), new Beat(startTime + gap * 458, "K"),
					new Beat(startTime + gap * 459, "D"), new Beat(startTime + gap * 460, "F"),
					new Beat(startTime + gap * 463, "J"), new Beat(startTime + gap * 466, "K"),
					new Beat(startTime + gap * 467, "K"), new Beat(startTime + gap * 477, "Space"),
					new Beat(startTime + gap * 480, "S"), new Beat(startTime + gap * 482, "F"),
					new Beat(startTime + gap * 482, "D"), new Beat(startTime + gap * 486, "F"),
					new Beat(startTime + gap * 487, "J"), new Beat(startTime + gap * 490, "D"),
					new Beat(startTime + gap * 493, "F"), new Beat(startTime + gap * 494, "F"),
					new Beat(startTime + gap * 494, "S"), new Beat(startTime + gap * 497, "Space"),
					new Beat(startTime + gap * 498, "F"), new Beat(startTime + gap * 499, "F"),
					new Beat(startTime + gap * 502, "K"), new Beat(startTime + gap * 503, "S"),
					new Beat(startTime + gap * 506, "D"), new Beat(startTime + gap * 509, "L"),
					new Beat(startTime + gap * 510, "K"), new Beat(startTime + gap * 512, "F"),
					new Beat(startTime + gap * 516, "K"), new Beat(startTime + gap * 517, "F"),
					new Beat(startTime + gap * 520, "F"), new Beat(startTime + gap * 523, "D"),
					new Beat(startTime + gap * 524, "S"), new Beat(startTime + gap * 526, "J"),
					new Beat(startTime + gap * 530, "S"), new Beat(startTime + gap * 531, "D"),
					new Beat(startTime + gap * 532, "F"), new Beat(startTime + gap * 536, "K"),
					new Beat(startTime + gap * 537, "F"), new Beat(startTime + gap * 539, "Space"),
					new Beat(startTime + gap * 541, "S"), new Beat(startTime + gap * 542, "K"),
					new Beat(startTime + gap * 547, "K"), new Beat(startTime + gap * 548, "D"),
					new Beat(startTime + gap * 549, "J"), new Beat(startTime + gap * 551, "F"),
					new Beat(startTime + gap * 553, "J"), new Beat(startTime + gap * 554, "F"),
					new Beat(startTime + gap * 556, "K"), new Beat(startTime + gap * 564, "Space"),
					new Beat(startTime + gap * 565, "K"), new Beat(startTime + gap * 568, "S"),
					new Beat(startTime + gap * 572, "D"), new Beat(startTime + gap * 573, "L"),
					new Beat(startTime + gap * 576, "F"), new Beat(startTime + gap * 580, "F"),
					new Beat(startTime + gap * 581, "F"), new Beat(startTime + gap * 584, "Space"),
					new Beat(startTime + gap * 587, "K"), new Beat(startTime + gap * 588, "S"),
					new Beat(startTime + gap * 589, "F"), new Beat(startTime + gap * 592, "K"),
					new Beat(startTime + gap * 593, "K"), new Beat(startTime + gap * 596, "D"),
					new Beat(startTime + gap * 599, "K"), new Beat(startTime + gap * 600, "D"),
					new Beat(startTime + gap * 602, "F"), new Beat(startTime + gap * 606, "K"),
					new Beat(startTime + gap * 607, "K"), new Beat(startTime + gap * 610, "F"),
					new Beat(startTime + gap * 613, "L"), new Beat(startTime + gap * 614, "F"),
					new Beat(startTime + gap * 616, "J"), new Beat(startTime + gap * 620, "S"),
					new Beat(startTime + gap * 621, "J"), new Beat(startTime + gap * 622, "S"),
					new Beat(startTime + gap * 626, "K"), new Beat(startTime + gap * 627, "L"),
					new Beat(startTime + gap * 629, "S"), new Beat(startTime + gap * 631, "S"),
					new Beat(startTime + gap * 632, "Space"), new Beat(startTime + gap * 634, "K"),
					new Beat(startTime + gap * 638, "D"), new Beat(startTime + gap * 639, "F"),
					new Beat(startTime + gap * 641, "F"), new Beat(startTime + gap * 643, "J"),
					new Beat(startTime + gap * 644, "D"), new Beat(startTime + gap * 646, "K"),
					new Beat(startTime + gap * 647, "Space"), new Beat(startTime + gap * 648, "K"),
					new Beat(startTime + gap * 650, "S"), new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 653, "L"), new Beat(startTime + gap * 656, "F"),
					new Beat(startTime + gap * 660, "D"), new Beat(startTime + gap * 661, "J"),
					new Beat(startTime + gap * 665, "F"), new Beat(startTime + gap * 671, "F"),
					new Beat(startTime + gap * 672, "F"), new Beat(startTime + gap * 674, "F"),
					new Beat(startTime + gap * 677, "K"), new Beat(startTime + gap * 678, "K"),
					new Beat(startTime + gap * 679, "F"), new Beat(startTime + gap * 682, "K"),
					new Beat(startTime + gap * 683, "S"), new Beat(startTime + gap * 686, "D"),
					new Beat(startTime + gap * 689, "K"), new Beat(startTime + gap * 690, "D"),
					new Beat(startTime + gap * 692, "F"), new Beat(startTime + gap * 696, "K"),
					new Beat(startTime + gap * 697, "F"), new Beat(startTime + gap * 700, "F"),
					new Beat(startTime + gap * 703, "L"), new Beat(startTime + gap * 704, "K"),
					new Beat(startTime + gap * 706, "J"), new Beat(startTime + gap * 710, "S"),
					new Beat(startTime + gap * 711, "D"), new Beat(startTime + gap * 712, "S"),
					new Beat(startTime + gap * 716, "K"), new Beat(startTime + gap * 717, "F"),
					new Beat(startTime + gap * 719, "F"), new Beat(startTime + gap * 720, "K"),
					new Beat(startTime + gap * 725, "K"), new Beat(startTime + gap * 726, "D"),
					new Beat(startTime + gap * 728, "J"), new Beat(startTime + gap * 729, "F"),
					new Beat(startTime + gap * 730, "J"), new Beat(startTime + gap * 733, "F"),
					new Beat(startTime + gap * 746, "K"), new Beat(startTime + gap * 749, "Space"),
					new Beat(startTime + gap * 750, "K"), new Beat(startTime + gap * 756, "S"),
					new Beat(startTime + gap * 757, "D"), new Beat(startTime + gap * 758, "L"),
					new Beat(startTime + gap * 759, "F"), new Beat(startTime + gap * 760, "F"),
					new Beat(startTime + gap * 762, "F"), new Beat(startTime + gap * 763, "Space"),
					new Beat(startTime + gap * 765, "K"), new Beat(startTime + gap * 768, "S"),
					new Beat(startTime + gap * 770, "F"), new Beat(startTime + gap * 770, "K"),
					new Beat(startTime + gap * 772, "K"), new Beat(startTime + gap * 773, "D"),
					new Beat(startTime + gap * 774, "K"), new Beat(startTime + gap * 775, "D"),
					new Beat(startTime + gap * 776, "F"), new Beat(startTime + gap * 777, "K"),
					new Beat(startTime + gap * 780, "K"), new Beat(startTime + gap * 781, "F"),
					new Beat(startTime + gap * 782, "L"), new Beat(startTime + gap * 783, "F"),
					new Beat(startTime + gap * 784, "J"), new Beat(startTime + gap * 784, "S"),
					new Beat(startTime + gap * 785, "J"), new Beat(startTime + gap * 785, "S"),
					new Beat(startTime + gap * 787, "K"), new Beat(startTime + gap * 789, "L"),
					new Beat(startTime + gap * 790, "S"), new Beat(startTime + gap * 792, "S"),
					new Beat(startTime + gap * 793, "Space"), new Beat(startTime + gap * 794, "K"),
					new Beat(startTime + gap * 795, "D"), new Beat(startTime + gap * 795, "F"),
					new Beat(startTime + gap * 800, "F"), new Beat(startTime + gap * 801, "J"),
					new Beat(startTime + gap * 802, "D"), new Beat(startTime + gap * 803, "K"),
					new Beat(startTime + gap * 810, "D"), new Beat(startTime + gap * 812, "L"),
					new Beat(startTime + gap * 813, "F"), new Beat(startTime + gap * 816, "F"),
					new Beat(startTime + gap * 820, "F"), new Beat(startTime + gap * 821, "Space"),
					new Beat(startTime + gap * 822, "K"), new Beat(startTime + gap * 822, "S"),
					new Beat(startTime + gap * 823, "F"), new Beat(startTime + gap * 824, "K"),
					new Beat(startTime + gap * 825, "K"), new Beat(startTime + gap * 826, "D"),
					new Beat(startTime + gap * 827, "K"), new Beat(startTime + gap * 830, "D"),
					new Beat(startTime + gap * 831, "F"), new Beat(startTime + gap * 832, "K"),
					new Beat(startTime + gap * 834, "K"), new Beat(startTime + gap * 835, "F"),
					new Beat(startTime + gap * 838, "L"), new Beat(startTime + gap * 839, "F"),
					new Beat(startTime + gap * 840, "J"), new Beat(startTime + gap * 841, "S"),
					new Beat(startTime + gap * 842, "F"), new Beat(startTime + gap * 843, "K"),
					new Beat(startTime + gap * 845, "J"), new Beat(startTime + gap * 845, "Space"),
					new Beat(startTime + gap * 846, "F"), new Beat(startTime + gap * 847, "S"),
					new Beat(startTime + gap * 848, "D"), new Beat(startTime + gap * 848, "J"),
					new Beat(startTime + gap * 849, "F"), new Beat(startTime + gap * 855, "S"),
					new Beat(startTime + gap * 856, "K"), new Beat(startTime + gap * 857, "F"),
					new Beat(startTime + gap * 858, "K"), new Beat(startTime + gap * 859, "L"),
					new Beat(startTime + gap * 860, "F"), new Beat(startTime + gap * 862, "K"),
					new Beat(startTime + gap * 863, "S"), new Beat(startTime + gap * 864, "D"),
					new Beat(startTime + gap * 865, "K"), new Beat(startTime + gap * 865, "F"),
					new Beat(startTime + gap * 870, "F"), new Beat(startTime + gap * 875, "J"),
					new Beat(startTime + gap * 877, "D"), new Beat(startTime + gap * 878, "F"),
					new Beat(startTime + gap * 879, "L"), new Beat(startTime + gap * 880, "K"),
					new Beat(startTime + gap * 881, "F"), new Beat(startTime + gap * 882, "F"),
					new Beat(startTime + gap * 883, "K"), new Beat(startTime + gap * 884, "K"),
					new Beat(startTime + gap * 885, "F"), new Beat(startTime + gap * 886, "K"),
					new Beat(startTime + gap * 887, "S"), new Beat(startTime + gap * 889, "D"),
					new Beat(startTime + gap * 892, "K"), new Beat(startTime + gap * 893, "D"),
					new Beat(startTime + gap * 895, "F"), new Beat(startTime + gap * 896, "K"),
					new Beat(startTime + gap * 897, "F"), new Beat(startTime + gap * 898, "F"),
					new Beat(startTime + gap * 898, "L"), new Beat(startTime + gap * 900, "K"),
					new Beat(startTime + gap * 910, "J"), new Beat(startTime + gap * 911, "S"),
					new Beat(startTime + gap * 912, "D"), new Beat(startTime + gap * 913, "S"),
					new Beat(startTime + gap * 914, "K"), new Beat(startTime + gap * 915, "F"),
					new Beat(startTime + gap * 916, "F"), new Beat(startTime + gap * 917, "K"),  
					new Beat(startTime + gap * 920, "S"), new Beat(startTime + gap * 922, "J"),
					new Beat(startTime + gap * 923, "S"), new Beat(startTime + gap * 924, "D"),
					new Beat(startTime + gap * 925, "F"), new Beat(startTime + gap * 928, "K"),
					new Beat(startTime + gap * 929, "F"), new Beat(startTime + gap * 930, "Space"),
					new Beat(startTime + gap * 933, "S"), new Beat(startTime + gap * 934, "K"),
					new Beat(startTime + gap * 935, "K"), new Beat(startTime + gap * 937, "D"),
					new Beat(startTime + gap * 938, "J"), new Beat(startTime + gap * 939, "F"),
					new Beat(startTime + gap * 941, "J"), new Beat(startTime + gap * 943, "F"),
					new Beat(startTime + gap * 945, "K"), new Beat(startTime + gap * 947, "Space"),
					new Beat(startTime + gap * 948, "K"), new Beat(startTime + gap * 949, "S"),
					new Beat(startTime + gap * 950, "D"), new Beat(startTime + gap * 955, "L"),
					new Beat(startTime + gap * 956, "F"), new Beat(startTime + gap * 957, "F"),
					new Beat(startTime + gap * 959, "F"), new Beat(startTime + gap * 960, "Space"),
					new Beat(startTime + gap * 961, "K"), new Beat(startTime + gap * 962, "S"),
					new Beat(startTime + gap * 963, "F"), new Beat(startTime + gap * 967, "K"),
					new Beat(startTime + gap * 968, "K"), new Beat(startTime + gap * 970, "D"),
					new Beat(startTime + gap * 972, "K"), new Beat(startTime + gap * 973, "D"),
					new Beat(startTime + gap * 974, "F"), new Beat(startTime + gap * 975, "K"),
					new Beat(startTime + gap * 976, "K"), new Beat(startTime + gap * 979, "F"),
					new Beat(startTime + gap * 985, "L"), new Beat(startTime + gap * 986, "F"),
					new Beat(startTime + gap * 989, "J"), new Beat(startTime + gap * 991, "S"),
					new Beat(startTime + gap * 993, "J"), new Beat(startTime + gap * 994, "S"),
					new Beat(startTime + gap * 1000, "S"), new Beat(startTime + gap * 1002, "S"),
					new Beat(startTime + gap * 1003, "F"), new Beat(startTime + gap * 1005, "K"),
					new Beat(startTime + gap * 1006, "S"), new Beat(startTime + gap * 1007, "K"),
					new Beat(startTime + gap * 1008, "S"), new Beat(startTime + gap * 1009, "K"),
					new Beat(startTime + gap * 1010, "K"), new Beat(startTime + gap * 1015, "D"),
					new Beat(startTime + gap * 1016, "Space"), new Beat(startTime + gap * 1019, "S"),
					new Beat(startTime + gap * 1022, "F"), new Beat(startTime + gap * 1023, "D"),
					new Beat(startTime + gap * 1025, "F"), new Beat(startTime + gap * 1026, "K"),
					new Beat(startTime + gap * 1027, "F"), new Beat(startTime + gap * 1029, "Space"),
					new Beat(startTime + gap * 1030, "K"), new Beat(startTime + gap * 1035, "K"),
					new Beat(startTime + gap * 1036, "F"), new Beat(startTime + gap * 1037, "S"),
					new Beat(startTime + gap * 1038, "K"), new Beat(startTime + gap * 1039, "D"),
					new Beat(startTime + gap * 1040, "F"), new Beat(startTime + gap * 1055, "K"),
					new Beat(startTime + gap * 1056, "S"), new Beat(startTime + gap * 1057, "K"),
					new Beat(startTime + gap * 1058, "K"), new Beat(startTime + gap * 1059, "Space"),
					new Beat(startTime + gap * 1060, "J"), new Beat(startTime + gap * 1061, "S"),
					new Beat(startTime + gap * 1062, "K"), new Beat(startTime + gap * 1063, "F"),
					new Beat(startTime + gap * 1063, "D"), new Beat(startTime + gap * 1066, "F"),
					new Beat(startTime + gap * 1067, "K"), new Beat(startTime + gap * 1068, "J"),
					new Beat(startTime + gap * 1069, "F"), new Beat(startTime + gap * 1070, "J"),
					new Beat(startTime + gap * 1075, "K"), new Beat(startTime + gap * 1076, "F"),
					new Beat(startTime + gap * 1077, "D"), new Beat(startTime + gap * 1078, "L"),
					new Beat(startTime + gap * 1079, "J"), new Beat(startTime + gap * 1080, "K"),
					new Beat(startTime + gap * 1082, "K"), new Beat(startTime + gap * 1087, "D"),
					new Beat(startTime + gap * 1088, "F"), new Beat(startTime + gap * 1090, "F"),
					new Beat(startTime + gap * 1092, "J"), new Beat(startTime + gap * 1099, "K"),
					new Beat(startTime + gap * 1100, "K"), new Beat(startTime + gap * 1108, "Space"),
					new Beat(startTime + gap * 1109, "S"), new Beat(startTime + gap * 1110, "K"),
					new Beat(startTime + gap * 1115, "D"), new Beat(startTime + gap * 1116, "F"),
					new Beat(startTime + gap * 1117, "K"), new Beat(startTime + gap * 1118, "J"),
					new Beat(startTime + gap * 1125, "S"), new Beat(startTime + gap * 1126, "J"),
					new Beat(startTime + gap * 1127, "L"), new Beat(startTime + gap * 1128, "F"),
					new Beat(startTime + gap * 1129, "F"), new Beat(startTime + gap * 1132, "K"),
					new Beat(startTime + gap * 1135, "F"), new Beat(startTime + gap * 1136, "J"),
					new Beat(startTime + gap * 1138, "K"), new Beat(startTime + gap * 1139, "D"),
					new Beat(startTime + gap * 1140, "S"), new Beat(startTime + gap * 1142, "K"),
					new Beat(startTime + gap * 1145, "F"), new Beat(startTime + gap * 1146, "F"),
					new Beat(startTime + gap * 1148, "K"), new Beat(startTime + gap * 1150, "F"),
					new Beat(startTime + gap * 1153, "K"), new Beat(startTime + gap * 1154, "L"),
					new Beat(startTime + gap * 1156, "J"), new Beat(startTime + gap * 1159, "K"),
					new Beat(startTime + gap * 1162, "D"), new Beat(startTime + gap * 1163, "L"),
					new Beat(startTime + gap * 1164, "F"), new Beat(startTime + gap * 1165, "F"),
					new Beat(startTime + gap * 1166, "F"), new Beat(startTime + gap * 1168, "Space"),
					new Beat(startTime + gap * 1170, "K"), new Beat(startTime + gap * 1178, "S"),
					new Beat(startTime + gap * 1200, "F"), new Beat(startTime + gap * 1212, "K"),
					new Beat(startTime + gap * 1215, "K"), new Beat(startTime + gap * 1222, "D"),
					new Beat(startTime + gap * 1223, "K"), new Beat(startTime + gap * 1223, "D"),
					new Beat(startTime + gap * 1224, "F"), new Beat(startTime + gap * 1225, "K"),
					new Beat(startTime + gap * 1226, "K"), new Beat(startTime + gap * 1227, "F"),
					new Beat(startTime + gap * 1228, "L"), new Beat(startTime + gap * 1229, "F"),
					new Beat(startTime + gap * 1230, "J"), new Beat(startTime + gap * 1234, "S"),
					new Beat(startTime + gap * 1235, "J"), new Beat(startTime + gap * 1236, "S"),
					new Beat(startTime + gap * 1240, "K"), new Beat(startTime + gap * 1241, "L"),
					new Beat(startTime + gap * 1242, "S"), new Beat(startTime + gap * 1245, "S"),
					new Beat(startTime + gap * 1249, "Space"), new Beat(startTime + gap * 1250, "K"),
					new Beat(startTime + gap * 1251, "D"), new Beat(startTime + gap * 1253, "F"),
					new Beat(startTime + gap * 1255, "S"), new Beat(startTime + gap * 1257, "S"),
					new Beat(startTime + gap * 1258, "F"), new Beat(startTime + gap * 1259, "K"),
					new Beat(startTime + gap * 1270, "S"), new Beat(startTime + gap * 1271, "K"),
					new Beat(startTime + gap * 1272, "S"), new Beat(startTime + gap * 1273, "K"),
					new Beat(startTime + gap * 1274, "K"), new Beat(startTime + gap * 1275, "D"),
					new Beat(startTime + gap * 1276, "F"), new Beat(startTime + gap * 1277, "F"),
					new Beat(startTime + gap * 1278, "J"), new Beat(startTime + gap * 1279, "K"),
					new Beat(startTime + gap * 1280, "K"), new Beat(startTime + gap * 1281, "Space"),
					new Beat(startTime + gap * 1282, "S"), new Beat(startTime + gap * 1283, "K"),
					new Beat(startTime + gap * 1284, "D"), new Beat(startTime + gap * 1285, "F"),
					new Beat(startTime + gap * 1286, "K"), new Beat(startTime + gap * 1287, "J"),
					new Beat(startTime + gap * 1288, "S"), new Beat(startTime + gap * 1289, "J"),
					new Beat(startTime + gap * 1290, "L"), new Beat(startTime + gap * 1291, "F"),
					new Beat(startTime + gap * 1292, "F"), new Beat(startTime + gap * 1293, "K"),
					new Beat(startTime + gap * 1294, "F"), new Beat(startTime + gap * 1295, "J"),
					new Beat(startTime + gap * 1296, "K"), new Beat(startTime + gap * 1298, "D"),
					new Beat(startTime + gap * 1299, "S"), new Beat(startTime + gap * 1300, "K"),
					new Beat(startTime + gap * 1311, "F"), new Beat(startTime + gap * 1312, "F"),
					new Beat(startTime + gap * 1313, "K"), new Beat(startTime + gap * 1315, "F"),
					new Beat(startTime + gap * 1316, "K"), new Beat(startTime + gap * 1318, "L"),
					new Beat(startTime + gap * 1320, "J"), new Beat(startTime + gap * 1321, "K"),
					new Beat(startTime + gap * 1324, "S"), new Beat(startTime + gap * 1325, "S"),
					new Beat(startTime + gap * 1326, "F"), new Beat(startTime + gap * 1328, "K"),
					new Beat(startTime + gap * 1330, "L"), new Beat(startTime + gap * 1332, "D"),
					new Beat(startTime + gap * 1333, "S"), new Beat(startTime + gap * 1335, "K"),
					new Beat(startTime + gap * 1336, "F"), new Beat(startTime + gap * 1337, "D"),
					new Beat(startTime + gap * 1338, "F"), new Beat(startTime + gap * 1339, "K"),
					new Beat(startTime + gap * 1342, "J"), new Beat(startTime + gap * 1345, "Space"),
					new Beat(startTime + gap * 1346, "F"), new Beat(startTime + gap * 1347, "S"),
					new Beat(startTime + gap * 1348, "D"), new Beat(startTime + gap * 1350, "J"),
					new Beat(startTime + gap * 1351, "F"), new Beat(startTime + gap * 1352, "S"),
					new Beat(startTime + gap * 1355, "K"), new Beat(startTime + gap * 1356, "F"),
					new Beat(startTime + gap * 1358, "K"), new Beat(startTime + gap * 1359, "L"),
					new Beat(startTime + gap * 1360, "F"), new Beat(startTime + gap * 1362, "K"),
					new Beat(startTime + gap * 1363, "S"), new Beat(startTime + gap * 1368, "D"),
					new Beat(startTime + gap * 1369, "K"), new Beat(startTime + gap * 1370, "F"),
					new Beat(startTime + gap * 1371, "F"), new Beat(startTime + gap * 1372, "J"),
					new Beat(startTime + gap * 1373, "D"), new Beat(startTime + gap * 1375, "F"),
					new Beat(startTime + gap * 1376, "L"), new Beat(startTime + gap * 1379, "K"),
					new Beat(startTime + gap * 1380, "J"), new Beat(startTime + gap * 1381, "S"),
					new Beat(startTime + gap * 1382, "F"), new Beat(startTime + gap * 1384, "F"),
					new Beat(startTime + gap * 1386, "K"), new Beat(startTime + gap * 1389, "J"),
					new Beat(startTime + gap * 1390, "S"), new Beat(startTime + gap * 1392, "F"),
					new Beat(startTime + gap * 1393, "K"), new Beat(startTime + gap * 1394, "K"),
					new Beat(startTime + gap * 1395, "D"), new Beat(startTime + gap * 1400, "F"),			
					new Beat(startTime + gap * 1402, "J"), new Beat(startTime + gap * 1403, "K"),
					new Beat(startTime + gap * 1404, "K"), new Beat(startTime + gap * 1405, "Space"),
					new Beat(startTime + gap * 1406, "S"), new Beat(startTime + gap * 1407, "F"),
					new Beat(startTime + gap * 1408, "D"), new Beat(startTime + gap * 1409, "F"),
					new Beat(startTime + gap * 1410, "J"), new Beat(startTime + gap * 1412, "D"),
					new Beat(startTime + gap * 1413, "F"), new Beat(startTime + gap * 1415, "F"),
					new Beat(startTime + gap * 1416, "S"), new Beat(startTime + gap * 1416, "Space"),
					new Beat(startTime + gap * 1417, "F"), new Beat(startTime + gap * 1418, "F"),
					new Beat(startTime + gap * 1419, "K"), new Beat(startTime + gap * 1420, "S"),
					new Beat(startTime + gap * 1422, "D"), new Beat(startTime + gap * 1423, "L"),
					new Beat(startTime + gap * 1424, "K"), new Beat(startTime + gap * 1425, "F"),
					new Beat(startTime + gap * 1426, "K"), new Beat(startTime + gap * 1427, "F"),
					new Beat(startTime + gap * 1428, "F"), new Beat(startTime + gap * 1429, "D"),
					new Beat(startTime + gap * 1430, "S"), new Beat(startTime + gap * 1431, "J"),
					new Beat(startTime + gap * 1432, "S"), new Beat(startTime + gap * 1433, "D"),
					new Beat(startTime + gap * 1433, "F"), new Beat(startTime + gap * 1434, "K"),
					new Beat(startTime + gap * 1435, "F"),new Beat(startTime + gap * 1436, "K"),
					new Beat(startTime + gap * 1437, "K"), new Beat(startTime + gap * 1438, "S"),
					new Beat(startTime + gap * 1439, "K"), new Beat(startTime + gap * 1440, "K"),
					new Beat(startTime + gap * 1442, "F"),new Beat(startTime + gap * 1444, "K"),
					new Beat(startTime + gap * 1446, "S"), new Beat(startTime + gap * 1447, "F"),
					new Beat(startTime + gap * 1448, "F"), new Beat(startTime + gap * 1449, "K"),
					new Beat(startTime + gap * 1450, "K"),new Beat(startTime + gap * 1451, "F"),
					new Beat(startTime + gap * 1455, "S"), new Beat(startTime + gap * 1456, "S"),
					new Beat(startTime + gap * 1457, "D"),new Beat(startTime + gap * 1458, "D"),
					new Beat(startTime + gap * 1459, "K"),new Beat(startTime + gap * 1460, "F"),
					new Beat(startTime + gap * 1461, "K"), new Beat(startTime + gap * 1462, "S"),
					new Beat(startTime + gap * 1463, "F"), new Beat(startTime + gap * 1464, "D"),
					new Beat(startTime + gap * 1465, "L"),
					new Beat(startTime + gap * 1466, "J"),
					new Beat(startTime + gap * 1467, "J"), new Beat(startTime + gap * 1468, "S"),
					new Beat(startTime + gap * 1470, "S"), new Beat(startTime + gap * 1472, "K"),
					new Beat(startTime + gap * 1478, "K"),
					new Beat(startTime + gap * 1479, "S"),
					new Beat(startTime + gap * 1480, "F"), new Beat(startTime + gap * 1481, "F"),
					new Beat(startTime + gap * 1482, "K"), new Beat(startTime + gap * 1483, "K"),
					new Beat(startTime + gap * 1484, "D"), new Beat(startTime + gap * 1486, "J"),
					new Beat(startTime + gap * 1489, "S"), new Beat(startTime + gap * 1490, "K"),
					new Beat(startTime + gap * 1491, "Space"), new Beat(startTime + gap * 1492, "J"),
					new Beat(startTime + gap * 1493, "S"), new Beat(startTime + gap * 1494, "D"),
					new Beat(startTime + gap * 1496, "D"), new Beat(startTime + gap * 1497, "F"),
					new Beat(startTime + gap * 1498, "D"), new Beat(startTime + gap * 1500, "S"),
					new Beat(startTime + gap * 1502, "F"), new Beat(startTime + gap * 1503, "F"),
					new Beat(startTime + gap * 1504, "K"), new Beat(startTime + gap * 1505, "Space"),
					new Beat(startTime + gap * 1506, "F"), new Beat(startTime + gap * 1507, "K"),
					new Beat(startTime + gap * 1508, "K"), new Beat(startTime + gap * 1510, "D"),
					new Beat(startTime + gap * 1512, "D"), new Beat(startTime + gap * 1513, "K"),
					new Beat(startTime + gap * 1514, "F"), new Beat(startTime + gap * 1515, "F"),
					new Beat(startTime + gap * 1516, "K"), new Beat(startTime + gap * 1520, "F"),
					new Beat(startTime + gap * 1521, "J"), new Beat(startTime + gap * 1522, "F"),
					new Beat(startTime + gap * 1523, "J"), new Beat(startTime + gap * 1524, "F"),
					new Beat(startTime + gap * 1525, "K"), new Beat(startTime + gap * 1526, "S"),
					new Beat(startTime + gap * 1527, "S"), new Beat(startTime + gap * 1528, "K"),
					new Beat(startTime + gap * 1529, "K"), new Beat(startTime + gap * 1530, "Space"),
					new Beat(startTime + gap * 1531, "J"), new Beat(startTime + gap * 1532, "S"),
					new Beat(startTime + gap * 1533, "K"), new Beat(startTime + gap * 1534, "F"),
					new Beat(startTime + gap * 1535, "D"), new Beat(startTime + gap * 1536, "F"),
					new Beat(startTime + gap * 1537, "K"), new Beat(startTime + gap * 1538, "J"),
					new Beat(startTime + gap * 1539, "K"), new Beat(startTime + gap * 1540, "D"),
					new Beat(startTime + gap * 1541, "Space"), new Beat(startTime + gap * 1542, "S"),
					new Beat(startTime + gap * 1543, "F"), new Beat(startTime + gap * 1545, "D"),
					new Beat(startTime + gap * 1546, "F"), new Beat(startTime + gap * 1547, "K"),
					new Beat(startTime + gap * 1548, "F"), new Beat(startTime + gap * 1549, "K"),
					new Beat(startTime + gap * 1550, "F"), new Beat(startTime + gap * 1551, "F"),
					new Beat(startTime + gap * 1552, "L"), new Beat(startTime + gap * 1553, "K"),
					new Beat(startTime + gap * 1554, "J"), new Beat(startTime + gap * 1555, "S"),
					new Beat(startTime + gap * 1556, "D"), new Beat(startTime + gap * 1557, "S"),
					new Beat(startTime + gap * 1560, "K"), new Beat(startTime + gap * 1561, "F"),
					new Beat(startTime + gap * 1562, "F"), new Beat(startTime + gap * 1563, "K"),  
					new Beat(startTime + gap * 1564, "S"), new Beat(startTime + gap * 1564, "J"),
					new Beat(startTime + gap * 1568, "S"), new Beat(startTime + gap * 1570, "D"),
					new Beat(startTime + gap * 1571, "F"), new Beat(startTime + gap * 1573, "K"),
					new Beat(startTime + gap * 1574, "F"), new Beat(startTime + gap * 1575, "Space"),
					new Beat(startTime + gap * 1576, "S"), new Beat(startTime + gap * 1577, "K"),
					new Beat(startTime + gap * 1578, "K"), new Beat(startTime + gap * 1579, "D"),
					new Beat(startTime + gap * 1580, "J"), new Beat(startTime + gap * 1581, "F"),
					new Beat(startTime + gap * 1582, "J"), new Beat(startTime + gap * 1583, "F"),
					new Beat(startTime + gap * 1584, "K"), new Beat(startTime + gap * 1585, "Space"),
					new Beat(startTime + gap * 1590, "K"), new Beat(startTime + gap * 1591, "S"),
					new Beat(startTime + gap * 1592, "D"), new Beat(startTime + gap * 1593, "L"),
					new Beat(startTime + gap * 1594, "F"), new Beat(startTime + gap * 1595, "F"),
					new Beat(startTime + gap * 1596, "F"), new Beat(startTime + gap * 1597, "Space"),
					new Beat(startTime + gap * 1600, "K"), new Beat(startTime + gap * 1601, "S"),
					new Beat(startTime + gap * 1602, "F"), new Beat(startTime + gap * 1603, "K"),
					new Beat(startTime + gap * 1604, "K"), new Beat(startTime + gap * 1605, "D"),
					new Beat(startTime + gap * 1606, "K"), new Beat(startTime + gap * 1608, "D"),
					new Beat(startTime + gap * 1610, "F"), new Beat(startTime + gap * 1611, "K"),
					new Beat(startTime + gap * 1612, "K"), new Beat(startTime + gap * 1612, "F"),
					new Beat(startTime + gap * 1613, "L"), new Beat(startTime + gap * 1614, "F"),
					new Beat(startTime + gap * 1615, "J"), new Beat(startTime + gap * 1616, "S"),
					new Beat(startTime + gap * 1618, "J"), new Beat(startTime + gap * 1619, "S"),
					new Beat(startTime + gap * 1620, "S"), new Beat(startTime + gap * 1621, "S"),
					new Beat(startTime + gap * 1622, "F"), new Beat(startTime + gap * 1623, "K"),
					new Beat(startTime + gap * 1625, "S"), new Beat(startTime + gap * 1626, "K"),
					new Beat(startTime + gap * 1627, "S"), new Beat(startTime + gap * 1628, "K"),
					new Beat(startTime + gap * 1629, "K"), new Beat(startTime + gap * 1632, "D"),
					new Beat(startTime + gap * 1633, "Space"), new Beat(startTime + gap * 1633, "S"),
					new Beat(startTime + gap * 1634, "F"), new Beat(startTime + gap * 1634, "D"),
					new Beat(startTime + gap * 1640, "F"), new Beat(startTime + gap * 1641, "K"),
					new Beat(startTime + gap * 1642, "F"), new Beat(startTime + gap * 1643, "Space"),
					new Beat(startTime + gap * 1644, "K"), new Beat(startTime + gap * 1650, "K"),
					new Beat(startTime + gap * 1651, "F"), new Beat(startTime + gap * 1652, "S"),
					new Beat(startTime + gap * 1653, "K"), new Beat(startTime + gap * 1654, "D"),
					new Beat(startTime + gap * 1655, "F"), new Beat(startTime + gap * 1656, "K"),				
					new Beat(startTime + gap * 1657, "F"), new Beat(startTime + gap * 1658, "K"),
					new Beat(startTime + gap * 1659, "S"), new Beat(startTime + gap * 1660, "K"),
					new Beat(startTime + gap * 1661, "K"), new Beat(startTime + gap * 1661, "Space"),
					new Beat(startTime + gap * 1661, "J"), new Beat(startTime + gap * 1662, "S"),
					new Beat(startTime + gap * 1662, "K"), new Beat(startTime + gap * 1663, "F"),
					new Beat(startTime + gap * 1664, "D"), new Beat(startTime + gap * 1665, "F"),
					new Beat(startTime + gap * 1666, "K"), new Beat(startTime + gap * 1667, "J"),
					new Beat(startTime + gap * 1668, "F"), new Beat(startTime + gap * 1668, "J"),
					new Beat(startTime + gap * 1669, "K"), new Beat(startTime + gap * 1670, "F"),
					new Beat(startTime + gap * 1671, "D"), new Beat(startTime + gap * 1673, "L"),
					new Beat(startTime + gap * 1674, "J"), new Beat(startTime + gap * 1675, "K"),
					new Beat(startTime + gap * 1676, "K"), new Beat(startTime + gap * 1677, "D"),
					new Beat(startTime + gap * 1678, "F"), new Beat(startTime + gap * 1679, "F"),
					new Beat(startTime + gap * 1680, "J"), new Beat(startTime + gap * 1681, "K"),
					new Beat(startTime + gap * 1682, "K"), new Beat(startTime + gap * 1683, "Space"),
					new Beat(startTime + gap * 1684, "S"), new Beat(startTime + gap * 1686, "K"),
					new Beat(startTime + gap * 1687, "D"), new Beat(startTime + gap * 1688, "F"),
					new Beat(startTime + gap * 1689, "K"), new Beat(startTime + gap * 1690, "J"),
					new Beat(startTime + gap * 1691, "S"), new Beat(startTime + gap * 1692, "J"),					
					new Beat(startTime + gap * 1693, "D"), new Beat(startTime + gap * 1694, "L"),
					new Beat(startTime + gap * 1695, "J"), new Beat(startTime + gap * 1696, "K"),
					new Beat(startTime + gap * 1697, "S"), new Beat(startTime + gap * 1698, "S"),
					new Beat(startTime + gap * 1699, "F"), new Beat(startTime + gap * 1699, "K"),
					new Beat(startTime + gap * 1699, "S"), new Beat(startTime + gap * 1700, "K"),
					new Beat(startTime + gap * 1700, "S"), new Beat(startTime + gap * 1705, "K"),
					new Beat(startTime + gap * 1710, "K"), new Beat(startTime + gap * 1711, "D"),
					new Beat(startTime + gap * 1712, "F"), new Beat(startTime + gap * 1715, "F"),
					new Beat(startTime + gap * 1716, "J"), new Beat(startTime + gap * 1720, "K"),
					new Beat(startTime + gap * 1722, "K"), new Beat(startTime + gap * 1723, "Space"),
					new Beat(startTime + gap * 1724, "D"), new Beat(startTime + gap * 1725, "L"),
					new Beat(startTime + gap * 1726, "F"), new Beat(startTime + gap * 1727, "F"),
					new Beat(startTime + gap * 1728, "F"), new Beat(startTime + gap * 1730, "Space"),
					new Beat(startTime + gap * 1731, "K"), new Beat(startTime + gap * 1732, "S"),
					new Beat(startTime + gap * 1733, "F"), new Beat(startTime + gap * 1734, "K"),
					new Beat(startTime + gap * 1735, "K"), new Beat(startTime + gap * 1736, "D"),
					new Beat(startTime + gap * 1739, "K"), new Beat(startTime + gap * 1740, "D"),
					new Beat(startTime + gap * 1741, "F"), new Beat(startTime + gap * 1742, "K"),
					new Beat(startTime + gap * 1743, "J"), new Beat(startTime + gap * 1743, "K"),
					new Beat(startTime + gap * 1750, "S"), new Beat(startTime + gap * 1752, "S"),
					new Beat(startTime + gap * 1753, "F"), new Beat(startTime + gap * 1754, "K"),
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					new Beat(startTime + gap * 1760, "F"),new Beat(startTime + gap * 1760, "K"),
					new Beat(startTime + gap * 1760, "J"),new Beat(startTime + gap * 1760, "S")
			};

		} else if (titleName.equals("Zico - Anysong") && difficulty.equals("Easy")) {
			int startTime = 5000 - Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { 

					new Beat(startTime, "Space"), new Beat(startTime + gap * 24, "F"),
					new Beat(startTime + gap * 27, "K"), new Beat(startTime + gap * 30, "F"),
					new Beat(startTime + gap * 32, "K"), new Beat(startTime + gap * 36, "D"),
					new Beat(startTime + gap * 39, "K"), new Beat(startTime + gap * 42, "F"),
					new Beat(startTime + gap * 46, "K"), new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 53, "L"), new Beat(startTime + gap * 56, "J"),
					new Beat(startTime + gap * 60, "S"), new Beat(startTime + gap * 62, "S"),
					new Beat(startTime + gap * 66, "K"), new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 78, "K"),
					new Beat(startTime + gap * 79, "D"), new Beat(startTime + gap * 83, "J"),
					new Beat(startTime + gap * 86, "K"), new Beat(startTime + gap * 97, "Space"),
					new Beat(startTime + gap * 100, "S"), new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 106, "F"), new Beat(startTime + gap * 110, "D"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 127, "Space"), new Beat(startTime + gap * 129, "F"),
					new Beat(startTime + gap * 132, "K"), new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 139, "K"), new Beat(startTime + gap * 142, "F"),
					new Beat(startTime + gap * 146, "K"), new Beat(startTime + gap * 150, "F"),
					new Beat(startTime + gap * 153, "F"), new Beat(startTime + gap * 156, "J"),
					new Beat(startTime + gap * 160, "S"), new Beat(startTime + gap * 162, "S"),
					new Beat(startTime + gap * 166, "K"), new Beat(startTime + gap * 169, "Space"),
					new Beat(startTime + gap * 171, "S"), new Beat(startTime + gap * 174, "K"),
					new Beat(startTime + gap * 178, "D"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 183, "J"), new Beat(startTime + gap * 186, "K"),
					new Beat(startTime + gap * 194, "Space"), new Beat(startTime + gap * 198, "S"),
					new Beat(startTime + gap * 212, "D"), new Beat(startTime + gap * 216, "F"),
					new Beat(startTime + gap * 220, "F"), new Beat(startTime + gap * 224, "Space"),
					new Beat(startTime + gap * 227, "K"), new Beat(startTime + gap * 229, "F"),
					new Beat(startTime + gap * 232, "D"), new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 239, "L"), new Beat(startTime + gap * 242, "F"),
					new Beat(startTime + gap * 246, "J"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 253, "L"), new Beat(startTime + gap * 256, "J"),
					new Beat(startTime + gap * 260, "S"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 266, "K"), new Beat(startTime + gap * 269, "S"),
					new Beat(startTime + gap * 271, "S"), new Beat(startTime + gap * 274, "K"),
					new Beat(startTime + gap * 278, "D"), new Beat(startTime + gap * 281, "F"),
					new Beat(startTime + gap * 283, "J"), new Beat(startTime + gap * 286, "K"),
					new Beat(startTime + gap * 297, "Space"), new Beat(startTime + gap * 300, "S"),
					new Beat(startTime + gap * 302, "D"), new Beat(startTime + gap * 306, "F"),
					new Beat(startTime + gap * 310, "J"), new Beat(startTime + gap * 320, "S"),
					new Beat(startTime + gap * 322, "L"), new Beat(startTime + gap * 324, "F"),
					new Beat(startTime + gap * 327, "K"), new Beat(startTime + gap * 329, "F"),
					new Beat(startTime + gap * 332, "K"), new Beat(startTime + gap * 336, "D"),
					new Beat(startTime + gap * 339, "K"), new Beat(startTime + gap * 342, "F"),
					new Beat(startTime + gap * 346, "K"), new Beat(startTime + gap * 350, "F"),
					new Beat(startTime + gap * 353, "L"), new Beat(startTime + gap * 356, "J"),
					new Beat(startTime + gap * 360, "S"), new Beat(startTime + gap * 362, "S"),
					new Beat(startTime + gap * 366, "K"), new Beat(startTime + gap * 369, "L"),
					new Beat(startTime + gap * 371, "S"), new Beat(startTime + gap * 374, "K"),
					new Beat(startTime + gap * 378, "D"), new Beat(startTime + gap * 381, "F"),
					new Beat(startTime + gap * 383, "J"), new Beat(startTime + gap * 384, "Space"),
					new Beat(startTime + gap * 388, "S"), new Beat(startTime + gap * 392, "D"),
					new Beat(startTime + gap * 396, "F"), new Beat(startTime + gap * 400, "S"),
					new Beat(startTime + gap * 404, "D"), new Beat(startTime + gap * 407, "K"),
					new Beat(startTime + gap * 410, "F"), new Beat(startTime + gap * 412, "L"),
					new Beat(startTime + gap * 416, "D"), new Beat(startTime + gap * 419, "K"),
					new Beat(startTime + gap * 422, "F"), new Beat(startTime + gap * 426, "J"),
					new Beat(startTime + gap * 430, "F"), new Beat(startTime + gap * 433, "L"),
					new Beat(startTime + gap * 436, "J"), new Beat(startTime + gap * 440, "S"),
					new Beat(startTime + gap * 442, "F"), new Beat(startTime + gap * 446, "K"),
					new Beat(startTime + gap * 449, "S"), new Beat(startTime + gap * 455, "F"),
					new Beat(startTime + gap * 458, "K"), new Beat(startTime + gap * 459, "D"),
					new Beat(startTime + gap * 463, "J"), new Beat(startTime + gap * 466, "K"),
					new Beat(startTime + gap * 477, "Space"), new Beat(startTime + gap * 480, "S"),
					new Beat(startTime + gap * 482, "D"), new Beat(startTime + gap * 486, "F"),
					new Beat(startTime + gap * 490, "L"), new Beat(startTime + gap * 493, "K"),
					new Beat(startTime + gap * 494, "S"), new Beat(startTime + gap * 497, "Space"),
					new Beat(startTime + gap * 499, "F"), new Beat(startTime + gap * 502, "K"),
					new Beat(startTime + gap * 506, "D"), new Beat(startTime + gap * 509, "L"),
					new Beat(startTime + gap * 512, "F"), new Beat(startTime + gap * 516, "K"),
					new Beat(startTime + gap * 520, "F"), new Beat(startTime + gap * 523, "D"),
					new Beat(startTime + gap * 526, "J"), new Beat(startTime + gap * 530, "S"),
					new Beat(startTime + gap * 532, "F"), new Beat(startTime + gap * 536, "K"),
					new Beat(startTime + gap * 539, "Space"), new Beat(startTime + gap * 541, "S"),
					new Beat(startTime + gap * 547, "K"), new Beat(startTime + gap * 548, "D"),
					new Beat(startTime + gap * 551, "F"), new Beat(startTime + gap * 553, "J"),
					new Beat(startTime + gap * 556, "K"), new Beat(startTime + gap * 564, "Space"),
					new Beat(startTime + gap * 568, "S"), new Beat(startTime + gap * 572, "D"),
					new Beat(startTime + gap * 576, "F"), new Beat(startTime + gap * 580, "F"),
					new Beat(startTime + gap * 584, "Space"), new Beat(startTime + gap * 587, "K"),
					new Beat(startTime + gap * 589, "F"), new Beat(startTime + gap * 592, "K"),
					new Beat(startTime + gap * 596, "D"), new Beat(startTime + gap * 599, "J"),
					new Beat(startTime + gap * 602, "F"), new Beat(startTime + gap * 606, "S"),
					new Beat(startTime + gap * 610, "D"), new Beat(startTime + gap * 613, "L"),
					new Beat(startTime + gap * 616, "J"), new Beat(startTime + gap * 620, "S"),
					new Beat(startTime + gap * 622, "S"), new Beat(startTime + gap * 626, "K"),
					new Beat(startTime + gap * 629, "S"), new Beat(startTime + gap * 631, "S"),
					new Beat(startTime + gap * 634, "K"), new Beat(startTime + gap * 638, "D"),
					new Beat(startTime + gap * 641, "F"), new Beat(startTime + gap * 643, "J"),
					new Beat(startTime + gap * 646, "K"), new Beat(startTime + gap * 647, "Space"),
					new Beat(startTime + gap * 650, "S"), new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 656, "F"), new Beat(startTime + gap * 660, "D"),
					new Beat(startTime + gap * 665, "S"), new Beat(startTime + gap * 671, "F"),
					new Beat(startTime + gap * 674, "F"), new Beat(startTime + gap * 677, "K"),
					new Beat(startTime + gap * 679, "D"), new Beat(startTime + gap * 682, "S"),
					new Beat(startTime + gap * 686, "D"), new Beat(startTime + gap * 689, "K"),
					new Beat(startTime + gap * 692, "F"), new Beat(startTime + gap * 696, "D"),
					new Beat(startTime + gap * 700, "J"), new Beat(startTime + gap * 703, "L"),
					new Beat(startTime + gap * 710, "S"), new Beat(startTime + gap * 715, "S"),
					new Beat(startTime + gap * 719, "K"), new Beat(startTime + gap * 723, "Space"),
					new Beat(startTime + gap * 726, "S"), new Beat(startTime + gap * 729, "K"),
					new Beat(startTime + gap * 733, "D"), new Beat(startTime + gap * 736, "F"),
					new Beat(startTime + gap * 739, "J"), new Beat(startTime + gap * 742, "K"),
					new Beat(startTime + gap * 745, "Space"), new Beat(startTime + gap * 749, "S"),
					new Beat(startTime + gap * 753, "D"), new Beat(startTime + gap * 757, "F"),
					new Beat(startTime + gap * 761, "F"), new Beat(startTime + gap * 764, "Space"),
					new Beat(startTime + gap * 767, "K"), new Beat(startTime + gap * 769, "F"),
					new Beat(startTime + gap * 772, "D"), new Beat(startTime + gap * 776, "D"),
					new Beat(startTime + gap * 780, "L"), new Beat(startTime + gap * 784, "F"),
					new Beat(startTime + gap * 787, "J"), new Beat(startTime + gap * 791, "F"),
					new Beat(startTime + gap * 794, "L"), new Beat(startTime + gap * 798, "J"),
					new Beat(startTime + gap * 802, "S"), new Beat(startTime + gap * 804, "S"),
					new Beat(startTime + gap * 806, "K"), new Beat(startTime + gap * 810, "S"),
					new Beat(startTime + gap * 814, "S"), new Beat(startTime + gap * 818, "K"),
					new Beat(startTime + gap * 821, "D"), new Beat(startTime + gap * 825, "F"),
					new Beat(startTime + gap * 828, "J"), new Beat(startTime + gap * 831, "K"),
					new Beat(startTime + gap * 835, "Space"), new Beat(startTime + gap * 840, "S"),
					new Beat(startTime + gap * 843, "D"), new Beat(startTime + gap * 846, "F"),
					new Beat(startTime + gap * 849, "J"), new Beat(startTime + gap * 852, "S"),
					new Beat(startTime + gap * 855, "L"), new Beat(startTime + gap * 858, "F"),
					new Beat(startTime + gap * 861, "K"), new Beat(startTime + gap * 865, "F"),
					new Beat(startTime + gap * 870, "K"), new Beat(startTime + gap * 874, "D"),
					new Beat(startTime + gap * 878, "K"), new Beat(startTime + gap * 881, "F"),
					new Beat(startTime + gap * 884, "K"), new Beat(startTime + gap * 887, "F"),
					new Beat(startTime + gap * 890, "L"), new Beat(startTime + gap * 893, "J"),
					new Beat(startTime + gap * 896, "S"), new Beat(startTime + gap * 900, "S"),
					new Beat(startTime + gap * 904, "K"), new Beat(startTime + gap * 909, "L"),
					new Beat(startTime + gap * 913, "S"), new Beat(startTime + gap * 917, "K"),
					new Beat(startTime + gap * 921, "D"), new Beat(startTime + gap * 925, "F"),
					new Beat(startTime + gap * 929, "J"), new Beat(startTime + gap * 933, "Space"),
					new Beat(startTime + gap * 938, "S"), new Beat(startTime + gap * 940, "D"),
					new Beat(startTime + gap * 944, "F"), new Beat(startTime + gap * 948, "S"),
					new Beat(startTime + gap * 952, "D"), new Beat(startTime + gap * 956, "K"),
					new Beat(startTime + gap * 960, "F"), new Beat(startTime + gap * 968, "L"),
					new Beat(startTime + gap * 970, "D"), new Beat(startTime + gap * 974, "K"),
					new Beat(startTime + gap * 978, "F"), new Beat(startTime + gap * 982, "J"),
					new Beat(startTime + gap * 986, "F"), new Beat(startTime + gap * 990, "L"),
					new Beat(startTime + gap * 994, "J"), new Beat(startTime + gap * 998, "S"),
					new Beat(startTime + gap * 1002, "F"), new Beat(startTime + gap * 1006, "K"),
					new Beat(startTime + gap * 1010, "S"), new Beat(startTime + gap * 1014, "F"),
					new Beat(startTime + gap * 1018, "K"), new Beat(startTime + gap * 1022, "D"),
					new Beat(startTime + gap * 1026, "K"), new Beat(startTime + gap * 1030, "D"),
					new Beat(startTime + gap * 1034, "K"), new Beat(startTime + gap * 1037, "F"),
					new Beat(startTime + gap * 1040, "K"), new Beat(startTime + gap * 1043, "F"),
					new Beat(startTime + gap * 1046, "L"), new Beat(startTime + gap * 1050, "J"),
					new Beat(startTime + gap * 1053, "S"), new Beat(startTime + gap * 1056, "S"),
					new Beat(startTime + gap * 1060, "K"), new Beat(startTime + gap * 1064, "S"),
					new Beat(startTime + gap * 1070, "F"), new Beat(startTime + gap * 1073, "K"),
					new Beat(startTime + gap * 1076, "D"), new Beat(startTime + gap * 1080, "J"),
					new Beat(startTime + gap * 1083, "K"), new Beat(startTime + gap * 1086, "Space"),
					new Beat(startTime + gap * 1090, "S"), new Beat(startTime + gap * 1100, "D"),
					new Beat(startTime + gap * 1101, "F"), new Beat(startTime + gap * 1104, "D"),
					new Beat(startTime + gap * 1105, "F"), new Beat(startTime + gap * 1108, "F"),
					new Beat(startTime + gap * 1110, "Space"), new Beat(startTime + gap * 1115, "F"),
					new Beat(startTime + gap * 1118, "K"), new Beat(startTime + gap * 1121, "D"),
					new Beat(startTime + gap * 1125, "K"), new Beat(startTime + gap * 1128, "F"),
					new Beat(startTime + gap * 1131, "K"), new Beat(startTime + gap * 1135, "F"),
					new Beat(startTime + gap * 1138, "F"), new Beat(startTime + gap * 1141, "J"),
					new Beat(startTime + gap * 1145, "S"), new Beat(startTime + gap * 1148, "S"),
					new Beat(startTime + gap * 1151, "K"), new Beat(startTime + gap * 1154, "Space"),
					new Beat(startTime + gap * 1158, "S"), new Beat(startTime + gap * 1161, "K"),
					new Beat(startTime + gap * 1170, "D"), new Beat(startTime + gap * 1174, "F"),
					new Beat(startTime + gap * 1180, "J"), new Beat(startTime + gap * 1183, "K"),
					new Beat(startTime + gap * 1186, "Space"), new Beat(startTime + gap * 1190, "S"),
					new Beat(startTime + gap * 1194, "D"), new Beat(startTime + gap * 1198, "F"),
					new Beat(startTime + gap * 1202, "F"), new Beat(startTime + gap * 1208, "Space"),
					new Beat(startTime + gap * 1211, "K"), new Beat(startTime + gap * 1215, "F"),
					new Beat(startTime + gap * 1219, "D"), new Beat(startTime + gap * 1222, "D"),
					new Beat(startTime + gap * 1225, "L"), new Beat(startTime + gap * 1228, "F"),
					new Beat(startTime + gap * 1231, "J"), new Beat(startTime + gap * 1240, "F"),
					new Beat(startTime + gap * 1244, "L"), new Beat(startTime + gap * 1248, "J"),
					new Beat(startTime + gap * 1251, "S"), new Beat(startTime + gap * 1255, "S"),
					new Beat(startTime + gap * 1258, "K"), new Beat(startTime + gap * 1261, "S"),
					new Beat(startTime + gap * 1265, "K"),new Beat(startTime + gap * 1268, "K"), new Beat(startTime + gap * 1272, "S"),
					new Beat(startTime + gap * 1275, "K"), new Beat(startTime + gap * 1278, "K"),new Beat(startTime + gap * 1281, "F"),
					new Beat(startTime + gap * 1284, "K"),new Beat(startTime + gap * 1287, "S"), new Beat(startTime + gap * 1290, "F"),
					new Beat(startTime + gap * 1293, "F"), new Beat(startTime + gap * 1296, "K"),new Beat(startTime + gap * 1300, "K"),
					new Beat(startTime + gap * 1310, "F"),new Beat(startTime + gap * 1315, "S"), new Beat(startTime + gap * 1319, "S"),
					new Beat(startTime + gap * 1320, "D"),new Beat(startTime + gap * 1325, "D"),new Beat(startTime + gap * 1329, "K"),
					new Beat(startTime + gap * 1334, "D"), new Beat(startTime + gap * 1339, "F"),
					new Beat(startTime + gap * 1341, "D"), new Beat(startTime + gap * 1345, "S"),
					new Beat(startTime + gap * 1348, "F"), new Beat(startTime + gap * 1351, "F"),
					new Beat(startTime + gap * 1354, "K"), new Beat(startTime + gap * 1357, "Space"),
					new Beat(startTime + gap * 1360, "F"), new Beat(startTime + gap * 1365, "K"),
					new Beat(startTime + gap * 1367, "K"), new Beat(startTime + gap * 1370, "D"),
					new Beat(startTime + gap * 1374, "D"), new Beat(startTime + gap * 1377, "K"),
					new Beat(startTime + gap * 1380, "F"), new Beat(startTime + gap * 1384, "F"),
					new Beat(startTime + gap * 1387, "K"), new Beat(startTime + gap * 1390, "F"),					
					new Beat(startTime + gap * 1400, "K"), new Beat(startTime + gap * 1405, "D"),
					new Beat(startTime + gap * 1409, "F"), new Beat(startTime + gap * 1413, "K"),
					new Beat(startTime + gap * 1417, "F"), new Beat(startTime + gap * 1420, "J"),
					new Beat(startTime + gap * 1424, "K"), new Beat(startTime + gap * 1428, "F"),
					new Beat(startTime + gap * 1430, "D"), new Beat(startTime + gap * 1435, "L"),
					new Beat(startTime + gap * 1440, "J"), new Beat(startTime + gap * 1444, "K"),
					new Beat(startTime + gap * 1447, "S"), new Beat(startTime + gap * 1450, "S"),
					new Beat(startTime + gap * 1450, "S"),new Beat(startTime + gap * 1460, "K"),
					
					
						new Beat(startTime + gap * 1465, "K"), new Beat(startTime + gap * 1470, "F"),
						new Beat(startTime + gap * 1474, "K"), new Beat(startTime + gap * 1478, "D"),
						new Beat(startTime + gap * 1481, "K"), new Beat(startTime + gap * 1484, "F"),
						new Beat(startTime + gap * 1487, "K"), new Beat(startTime + gap * 1490, "F"),
						new Beat(startTime + gap * 1494, "L"), new Beat(startTime + gap * 1498, "J"),
						new Beat(startTime + gap * 1500, "S"), new Beat(startTime + gap * 1502, "S"),
						new Beat(startTime + gap * 1504, "K"), new Beat(startTime + gap * 1508, "S"),
						new Beat(startTime + gap * 1511, "F"), new Beat(startTime + gap * 1514, "K"),
						new Beat(startTime + gap * 1517, "D"), new Beat(startTime + gap * 1520, "J"),
						new Beat(startTime + gap * 1525, "K"), new Beat(startTime + gap * 1529, "Space"),
						new Beat(startTime + gap * 1531, "S"), new Beat(startTime + gap * 1535, "D"),
						new Beat(startTime + gap * 1540, "F"), new Beat(startTime + gap * 1544, "D"),
						new Beat(startTime + gap * 1547, "F"), new Beat(startTime + gap * 1550, "F"),
						new Beat(startTime + gap * 1555, "Space"), new Beat(startTime + gap * 1559, "F"),
						new Beat(startTime + gap * 1560, "K"), new Beat(startTime + gap * 1564, "D"),
						new Beat(startTime + gap * 1567, "K"), new Beat(startTime + gap * 1570, "F"),
						new Beat(startTime + gap * 1574, "K"), new Beat(startTime + gap * 1578, "F"),
						new Beat(startTime + gap * 1581, "F"), new Beat(startTime + gap * 1585, "J"),
						new Beat(startTime + gap * 1590, "S"), new Beat(startTime + gap * 1593, "S"),
						new Beat(startTime + gap * 1597, "K"), new Beat(startTime + gap * 1600, "Space"),
						new Beat(startTime + gap * 1603, "S"), new Beat(startTime + gap * 1605, "K"),
						new Beat(startTime + gap * 1608, "D"), new Beat(startTime + gap * 1611, "F"),
						new Beat(startTime + gap * 1615, "J"), new Beat(startTime + gap * 1620, "K"),
						new Beat(startTime + gap * 1624, "Space"), new Beat(startTime + gap * 1628, "S"),
						new Beat(startTime + gap * 1630, "D"), new Beat(startTime + gap * 1635, "F"),
						new Beat(startTime + gap * 1640, "F"), new Beat(startTime + gap * 1645, "Space"),
						new Beat(startTime + gap * 1647, "K"), new Beat(startTime + gap * 1649, "F"),
						new Beat(startTime + gap * 1651, "D"), new Beat(startTime + gap * 1655, "D"),
						new Beat(startTime + gap * 1660, "L"), new Beat(startTime + gap * 1664, "F"),
						new Beat(startTime + gap * 1668, "J"), new Beat(startTime + gap * 1671, "F"),
						new Beat(startTime + gap * 1675, "L"), new Beat(startTime + gap * 1680, "J"),
						new Beat(startTime + gap * 1685, "S"), new Beat(startTime + gap * 1689, "S"),
						new Beat(startTime + gap * 1693, "K"), new Beat(startTime + gap * 1697, "S"),
						new Beat(startTime + gap * 1700, "S"), new Beat(startTime + gap * 1705, "K"),
						new Beat(startTime + gap * 1710, "D"), new Beat(startTime + gap * 1713, "F"),
						new Beat(startTime + gap * 1716, "J"), new Beat(startTime + gap * 1720, "K"),
						new Beat(startTime + gap * 1725, "Space"), new Beat(startTime + gap * 1730, "S"),
						new Beat(startTime + gap * 1734, "D"), new Beat(startTime + gap * 1737, "F"),
						new Beat(startTime + gap * 1740, "J"), new Beat(startTime + gap * 1743, "S"),
						new Beat(startTime + gap * 1746, "L"), new Beat(startTime + gap * 1750, "F"),
						new Beat(startTime + gap * 1753, "K"), new Beat(startTime + gap * 1756, "F"),
						new Beat(startTime + gap * 1758, "D"), new Beat(startTime + gap * 1760, "D"),
						new Beat(startTime + gap * 1760, "J"),new Beat(startTime + gap * 1760, "S")
			};

		} else if (titleName.equals("DPR live- Martini blue") && difficulty.equals("Easy")) {
			int startTime = 5000 - Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { new Beat(startTime, "Space"), new Beat(startTime + gap * 24, "F"),
					new Beat(startTime + gap * 27, "K"), new Beat(startTime + gap * 30, "F"),
					new Beat(startTime + gap * 32, "K"), new Beat(startTime + gap * 36, "D"),
					new Beat(startTime + gap * 39, "K"), new Beat(startTime + gap * 42, "F"),
					new Beat(startTime + gap * 46, "K"), new Beat(startTime + gap * 50, "F"),
					new Beat(startTime + gap * 53, "L"), new Beat(startTime + gap * 56, "J"),
					new Beat(startTime + gap * 60, "S"), new Beat(startTime + gap * 62, "S"),
					new Beat(startTime + gap * 66, "K"), new Beat(startTime + gap * 69, "S"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 78, "K"),
					new Beat(startTime + gap * 79, "D"), new Beat(startTime + gap * 83, "J"),
					new Beat(startTime + gap * 86, "K"), new Beat(startTime + gap * 97, "Space"),
					new Beat(startTime + gap * 100, "S"), new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 106, "F"), new Beat(startTime + gap * 110, "D"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 127, "Space"), new Beat(startTime + gap * 129, "F"),
					new Beat(startTime + gap * 132, "K"), new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 139, "K"), new Beat(startTime + gap * 142, "F"),
					new Beat(startTime + gap * 146, "K"), new Beat(startTime + gap * 150, "F"),
					new Beat(startTime + gap * 153, "F"), new Beat(startTime + gap * 156, "J"),
					new Beat(startTime + gap * 160, "S"), new Beat(startTime + gap * 162, "S"),
					new Beat(startTime + gap * 166, "K"), new Beat(startTime + gap * 169, "Space"),
					new Beat(startTime + gap * 171, "S"), new Beat(startTime + gap * 174, "K"),
					new Beat(startTime + gap * 178, "D"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 183, "J"), new Beat(startTime + gap * 186, "K"),
					new Beat(startTime + gap * 194, "Space"), new Beat(startTime + gap * 198, "S"),
					new Beat(startTime + gap * 212, "D"), new Beat(startTime + gap * 216, "F"),
					new Beat(startTime + gap * 220, "F"), new Beat(startTime + gap * 224, "Space"),
					new Beat(startTime + gap * 227, "K"), new Beat(startTime + gap * 229, "F"),
					new Beat(startTime + gap * 232, "D"), new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 239, "L"), new Beat(startTime + gap * 242, "F"),
					new Beat(startTime + gap * 246, "J"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 253, "L"), new Beat(startTime + gap * 256, "J"),
					new Beat(startTime + gap * 260, "S"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 266, "K"), new Beat(startTime + gap * 269, "S"),
					new Beat(startTime + gap * 271, "S"), new Beat(startTime + gap * 274, "K"),
					new Beat(startTime + gap * 278, "D"), new Beat(startTime + gap * 281, "F"),
					new Beat(startTime + gap * 283, "J"), new Beat(startTime + gap * 286, "K"),
					new Beat(startTime + gap * 297, "Space"), new Beat(startTime + gap * 300, "S"),
					new Beat(startTime + gap * 302, "D"), new Beat(startTime + gap * 306, "F"),
					new Beat(startTime + gap * 310, "J"), new Beat(startTime + gap * 320, "S"),
					new Beat(startTime + gap * 322, "L"), new Beat(startTime + gap * 324, "F"),
					new Beat(startTime + gap * 327, "K"), new Beat(startTime + gap * 329, "F"),
					new Beat(startTime + gap * 332, "K"), new Beat(startTime + gap * 336, "D"),
					new Beat(startTime + gap * 339, "K"), new Beat(startTime + gap * 342, "F"),
					new Beat(startTime + gap * 346, "K"), new Beat(startTime + gap * 350, "F"),
					new Beat(startTime + gap * 353, "L"), new Beat(startTime + gap * 356, "J"),
					new Beat(startTime + gap * 360, "S"), new Beat(startTime + gap * 362, "S"),
					new Beat(startTime + gap * 366, "K"), new Beat(startTime + gap * 369, "L"),
					new Beat(startTime + gap * 371, "S"), new Beat(startTime + gap * 374, "K"),
					new Beat(startTime + gap * 378, "D"), new Beat(startTime + gap * 381, "F"),
					new Beat(startTime + gap * 383, "J"), new Beat(startTime + gap * 384, "Space"),
					new Beat(startTime + gap * 388, "S"), new Beat(startTime + gap * 392, "D"),
					new Beat(startTime + gap * 396, "F"), new Beat(startTime + gap * 400, "S"),
					new Beat(startTime + gap * 404, "D"), new Beat(startTime + gap * 407, "K"),
					new Beat(startTime + gap * 410, "F"), new Beat(startTime + gap * 412, "L"),
					new Beat(startTime + gap * 416, "D"), new Beat(startTime + gap * 419, "K"),
					new Beat(startTime + gap * 422, "F"), new Beat(startTime + gap * 426, "J"),
					new Beat(startTime + gap * 430, "F"), new Beat(startTime + gap * 433, "L"),
					new Beat(startTime + gap * 436, "J"), new Beat(startTime + gap * 440, "S"),
					new Beat(startTime + gap * 442, "F"), new Beat(startTime + gap * 446, "K"),
					new Beat(startTime + gap * 449, "S"), new Beat(startTime + gap * 455, "F"),
					new Beat(startTime + gap * 458, "K"), new Beat(startTime + gap * 459, "D"),
					new Beat(startTime + gap * 463, "J"), new Beat(startTime + gap * 466, "K"),
					new Beat(startTime + gap * 477, "Space"), new Beat(startTime + gap * 480, "S"),
					new Beat(startTime + gap * 482, "D"), new Beat(startTime + gap * 486, "F"),
					new Beat(startTime + gap * 490, "L"), new Beat(startTime + gap * 493, "K"),
					new Beat(startTime + gap * 494, "S"), new Beat(startTime + gap * 497, "Space"),
					new Beat(startTime + gap * 499, "F"), new Beat(startTime + gap * 502, "K"),
					new Beat(startTime + gap * 506, "D"), new Beat(startTime + gap * 509, "L"),
					new Beat(startTime + gap * 512, "F"), new Beat(startTime + gap * 516, "K"),
					new Beat(startTime + gap * 520, "F"), new Beat(startTime + gap * 523, "D"),
					new Beat(startTime + gap * 526, "J"), new Beat(startTime + gap * 530, "S"),
					new Beat(startTime + gap * 532, "F"), new Beat(startTime + gap * 536, "K"),
					new Beat(startTime + gap * 539, "Space"), new Beat(startTime + gap * 541, "S"),
					new Beat(startTime + gap * 547, "K"), new Beat(startTime + gap * 548, "D"),
					new Beat(startTime + gap * 551, "F"), new Beat(startTime + gap * 553, "J"),
					new Beat(startTime + gap * 556, "K"), new Beat(startTime + gap * 564, "Space"),
					new Beat(startTime + gap * 568, "S"), new Beat(startTime + gap * 572, "D"),
					new Beat(startTime + gap * 576, "F"), new Beat(startTime + gap * 580, "F"),
					new Beat(startTime + gap * 584, "Space"), new Beat(startTime + gap * 587, "K"),
					new Beat(startTime + gap * 589, "F"), new Beat(startTime + gap * 592, "K"),
					new Beat(startTime + gap * 596, "D"), new Beat(startTime + gap * 599, "J"),
					new Beat(startTime + gap * 602, "F"), new Beat(startTime + gap * 606, "S"),
					new Beat(startTime + gap * 610, "D"), new Beat(startTime + gap * 613, "L"),
					new Beat(startTime + gap * 616, "J"), new Beat(startTime + gap * 620, "S"),
					new Beat(startTime + gap * 622, "S"), new Beat(startTime + gap * 626, "K"),
					new Beat(startTime + gap * 629, "S"), new Beat(startTime + gap * 631, "S"),
					new Beat(startTime + gap * 634, "K"), new Beat(startTime + gap * 638, "D"),
					new Beat(startTime + gap * 641, "F"), new Beat(startTime + gap * 643, "J"),
					new Beat(startTime + gap * 646, "K"), new Beat(startTime + gap * 647, "Space"),
					new Beat(startTime + gap * 650, "S"), new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 656, "F"), new Beat(startTime + gap * 660, "D"),
					new Beat(startTime + gap * 665, "S"), new Beat(startTime + gap * 671, "F"),
					new Beat(startTime + gap * 674, "F"), new Beat(startTime + gap * 677, "K"),
					new Beat(startTime + gap * 679, "D"), new Beat(startTime + gap * 682, "S"),
					new Beat(startTime + gap * 686, "D"), new Beat(startTime + gap * 689, "K"),
					new Beat(startTime + gap * 692, "F"), new Beat(startTime + gap * 696, "D"),
					new Beat(startTime + gap * 700, "J"), new Beat(startTime + gap * 703, "L"),
					new Beat(startTime + gap * 710, "S"), new Beat(startTime + gap * 715, "S"),
					new Beat(startTime + gap * 719, "K"), new Beat(startTime + gap * 723, "Space"),
					new Beat(startTime + gap * 726, "S"), new Beat(startTime + gap * 729, "K"),
					new Beat(startTime + gap * 733, "D"), new Beat(startTime + gap * 736, "F"),
					new Beat(startTime + gap * 739, "J"), new Beat(startTime + gap * 742, "K"),
					new Beat(startTime + gap * 745, "Space"), new Beat(startTime + gap * 749, "S"),
					new Beat(startTime + gap * 753, "D"), new Beat(startTime + gap * 757, "F"),
					new Beat(startTime + gap * 761, "F"), new Beat(startTime + gap * 764, "Space"),
					new Beat(startTime + gap * 767, "K"), new Beat(startTime + gap * 769, "F"),
					new Beat(startTime + gap * 772, "D"), new Beat(startTime + gap * 776, "D"),
					new Beat(startTime + gap * 780, "L"), new Beat(startTime + gap * 784, "F"),
					new Beat(startTime + gap * 787, "J"), new Beat(startTime + gap * 791, "F"),
					new Beat(startTime + gap * 794, "L"), new Beat(startTime + gap * 798, "J"),
					new Beat(startTime + gap * 802, "S"), new Beat(startTime + gap * 804, "S"),
					new Beat(startTime + gap * 806, "K"), new Beat(startTime + gap * 810, "S"),
					new Beat(startTime + gap * 814, "S"), new Beat(startTime + gap * 818, "K"),
					new Beat(startTime + gap * 821, "D"), new Beat(startTime + gap * 825, "F"),
					new Beat(startTime + gap * 828, "J"), new Beat(startTime + gap * 831, "K"),
					new Beat(startTime + gap * 835, "Space"), new Beat(startTime + gap * 840, "S"),
					new Beat(startTime + gap * 843, "D"), new Beat(startTime + gap * 846, "F"),
					new Beat(startTime + gap * 849, "J"), new Beat(startTime + gap * 852, "S"),
					new Beat(startTime + gap * 855, "L"), new Beat(startTime + gap * 858, "F"),
					new Beat(startTime + gap * 861, "K"), new Beat(startTime + gap * 865, "F"),
					new Beat(startTime + gap * 870, "K"), new Beat(startTime + gap * 874, "D"),
					new Beat(startTime + gap * 878, "K"), new Beat(startTime + gap * 881, "F"),
					new Beat(startTime + gap * 884, "K"), new Beat(startTime + gap * 887, "F"),
					new Beat(startTime + gap * 890, "L"), new Beat(startTime + gap * 893, "J"),
					new Beat(startTime + gap * 896, "S"), new Beat(startTime + gap * 900, "S"),
					new Beat(startTime + gap * 904, "K"), new Beat(startTime + gap * 909, "L"),
					new Beat(startTime + gap * 913, "S"), new Beat(startTime + gap * 917, "K"),
					new Beat(startTime + gap * 921, "D"), new Beat(startTime + gap * 925, "F"),
					new Beat(startTime + gap * 929, "J"), new Beat(startTime + gap * 933, "Space"),
					new Beat(startTime + gap * 938, "S"), new Beat(startTime + gap * 940, "D"),
					new Beat(startTime + gap * 944, "F"), new Beat(startTime + gap * 948, "S"),
					new Beat(startTime + gap * 952, "D"), new Beat(startTime + gap * 956, "K"),
					new Beat(startTime + gap * 960, "F"), new Beat(startTime + gap * 968, "L"),
					new Beat(startTime + gap * 970, "D"), new Beat(startTime + gap * 974, "K"),
					new Beat(startTime + gap * 978, "F"), new Beat(startTime + gap * 982, "J"),
					new Beat(startTime + gap * 986, "F"), new Beat(startTime + gap * 990, "L"),
					new Beat(startTime + gap * 994, "J"), new Beat(startTime + gap * 998, "S"),
					new Beat(startTime + gap * 1002, "F"), new Beat(startTime + gap * 1006, "K"),
					new Beat(startTime + gap * 1010, "S"), new Beat(startTime + gap * 1014, "F"),
					new Beat(startTime + gap * 1018, "K"), new Beat(startTime + gap * 1022, "D"),
					new Beat(startTime + gap * 1026, "K"), new Beat(startTime + gap * 1030, "D"),
					new Beat(startTime + gap * 1034, "K"), new Beat(startTime + gap * 1037, "F"),
					new Beat(startTime + gap * 1040, "K"), new Beat(startTime + gap * 1043, "F"),
					new Beat(startTime + gap * 1046, "L"), new Beat(startTime + gap * 1050, "J"),
					new Beat(startTime + gap * 1053, "S"), new Beat(startTime + gap * 1056, "S"),
					new Beat(startTime + gap * 1060, "K"), new Beat(startTime + gap * 1064, "S"),
					new Beat(startTime + gap * 1070, "F"), new Beat(startTime + gap * 1073, "K"),
					new Beat(startTime + gap * 1076, "D"), new Beat(startTime + gap * 1080, "J"),
					new Beat(startTime + gap * 1083, "K"), new Beat(startTime + gap * 1086, "Space"),
					new Beat(startTime + gap * 1090, "S"), new Beat(startTime + gap * 1100, "D"),
					new Beat(startTime + gap * 1101, "F"), new Beat(startTime + gap * 1104, "D"),
					new Beat(startTime + gap * 1105, "F"), new Beat(startTime + gap * 1108, "F"),
					new Beat(startTime + gap * 1110, "Space"), new Beat(startTime + gap * 1115, "F"),
					new Beat(startTime + gap * 1118, "K"), new Beat(startTime + gap * 1121, "D"),
					new Beat(startTime + gap * 1125, "K"), new Beat(startTime + gap * 1128, "F"),
					new Beat(startTime + gap * 1131, "K"), new Beat(startTime + gap * 1135, "F"),
					new Beat(startTime + gap * 1138, "F"), new Beat(startTime + gap * 1141, "J"),
					new Beat(startTime + gap * 1145, "S"), new Beat(startTime + gap * 1148, "S"),
					new Beat(startTime + gap * 1151, "K"), new Beat(startTime + gap * 1154, "Space"),
					new Beat(startTime + gap * 1158, "S"), new Beat(startTime + gap * 1161, "K"),
					new Beat(startTime + gap * 1170, "D"), new Beat(startTime + gap * 1174, "F"),
					new Beat(startTime + gap * 1180, "J"), new Beat(startTime + gap * 1183, "K"),
					new Beat(startTime + gap * 1186, "Space"), new Beat(startTime + gap * 1190, "S"),
					new Beat(startTime + gap * 1194, "D"), new Beat(startTime + gap * 1198, "F"),
					new Beat(startTime + gap * 1202, "F"), new Beat(startTime + gap * 1208, "Space"),
					new Beat(startTime + gap * 1211, "K"), new Beat(startTime + gap * 1215, "F"),
					new Beat(startTime + gap * 1219, "D"), new Beat(startTime + gap * 1222, "D"),
					new Beat(startTime + gap * 1225, "L"), new Beat(startTime + gap * 1228, "F"),
					new Beat(startTime + gap * 1231, "J"), new Beat(startTime + gap * 1240, "F"),
					new Beat(startTime + gap * 1244, "L"), new Beat(startTime + gap * 1248, "J"),
					new Beat(startTime + gap * 1251, "S"), new Beat(startTime + gap * 1255, "S"),
					new Beat(startTime + gap * 1258, "K"), new Beat(startTime + gap * 1261, "S"),
					new Beat(startTime + gap * 1265, "K"),new Beat(startTime + gap * 1268, "K"), new Beat(startTime + gap * 1272, "S"),
					new Beat(startTime + gap * 1275, "K"), new Beat(startTime + gap * 1278, "K"),new Beat(startTime + gap * 1281, "F"),
					new Beat(startTime + gap * 1284, "K"),new Beat(startTime + gap * 1287, "S"), new Beat(startTime + gap * 1290, "F"),
					new Beat(startTime + gap * 1293, "F"), new Beat(startTime + gap * 1296, "K"),new Beat(startTime + gap * 1300, "K"),
					new Beat(startTime + gap * 1310, "F"),new Beat(startTime + gap * 1315, "S"), new Beat(startTime + gap * 1319, "S"),
					new Beat(startTime + gap * 1320, "D"),new Beat(startTime + gap * 1325, "D"),new Beat(startTime + gap * 1329, "K"),
					new Beat(startTime + gap * 1334, "D"), new Beat(startTime + gap * 1339, "F"),
					new Beat(startTime + gap * 1341, "D"), new Beat(startTime + gap * 1345, "S"),
					new Beat(startTime + gap * 1348, "F"), new Beat(startTime + gap * 1351, "F"),
					new Beat(startTime + gap * 1354, "K"), new Beat(startTime + gap * 1357, "Space"),
					new Beat(startTime + gap * 1360, "F"), new Beat(startTime + gap * 1365, "K"),
					new Beat(startTime + gap * 1367, "K"), new Beat(startTime + gap * 1370, "D"),
					new Beat(startTime + gap * 1374, "D"), new Beat(startTime + gap * 1377, "K"),
					new Beat(startTime + gap * 1380, "F"), new Beat(startTime + gap * 1384, "F"),
					new Beat(startTime + gap * 1387, "K"), new Beat(startTime + gap * 1390, "F"),					
					new Beat(startTime + gap * 1400, "K"), new Beat(startTime + gap * 1405, "D"),
					new Beat(startTime + gap * 1409, "F"), new Beat(startTime + gap * 1413, "K"),
					new Beat(startTime + gap * 1417, "F"), new Beat(startTime + gap * 1420, "J"),
					new Beat(startTime + gap * 1424, "K"), new Beat(startTime + gap * 1428, "F"),
					new Beat(startTime + gap * 1430, "D"), new Beat(startTime + gap * 1435, "L"),
					new Beat(startTime + gap * 1440, "J"), new Beat(startTime + gap * 1444, "K"),
					new Beat(startTime + gap * 1447, "S"), new Beat(startTime + gap * 1450, "S"),
					new Beat(startTime + gap * 1450, "S"),new Beat(startTime + gap * 1460, "K")
					};
		} else if (titleName.equals("DPR live- Martini blue") && difficulty.equals("Hard")) {
			int startTime = 5000 - Main.REACH_TIME;
			int gap = 125;
			beats = new Beat[] { 
					new Beat(startTime + gap * 1, "F"), new Beat(startTime + gap * 5, "K"),new Beat(startTime + gap * 7, "F"),
					new Beat(startTime + gap * 10, "K"),new Beat(startTime + gap * 11, "K"), new Beat(startTime + gap * 13, "S"),
					new Beat(startTime + gap * 16, "K"), new Beat(startTime + gap * 17, "K"),new Beat(startTime + gap * 18, "F"),
					new Beat(startTime + gap * 20, "K"),new Beat(startTime + gap * 21, "S"), new Beat(startTime + gap * 22, "F"),
					new Beat(startTime + gap * 24, "F"), new Beat(startTime + gap * 26, "K"),new Beat(startTime + gap * 27, "K"),
					new Beat(startTime + gap * 30, "F"),new Beat(startTime + gap * 31, "S"), new Beat(startTime + gap * 32, "S"),
					new Beat(startTime + gap * 36, "D"),new Beat(startTime + gap * 37, "D"),new Beat(startTime + gap * 39, "K"),
					new Beat(startTime + gap * 42, "F"),new Beat(startTime + gap * 44, "K"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 50, "F"), new Beat(startTime + gap * 52, "D"),new Beat(startTime + gap * 53, "L"),
					new Beat(startTime + gap * 56, "J"),new Beat(startTime + gap * 58, "J"), new Beat(startTime + gap * 60, "S"),
					new Beat(startTime + gap * 62, "S"), new Beat(startTime + gap * 64, "K"),new Beat(startTime + gap * 66, "K"),
					new Beat(startTime + gap * 69, "S"),new Beat(startTime + gap * 73, "F"), new Beat(startTime + gap * 75, "F"),
					new Beat(startTime + gap * 78, "K"), new Beat(startTime + gap * 80, "K"),
					new Beat(startTime + gap * 81, "D"), new Beat(startTime + gap * 83, "J"),
					new Beat(startTime + gap * 85, "S"), new Beat(startTime + gap * 86, "K"),
					new Beat(startTime + gap * 97, "Space"), new Beat(startTime + gap * 99, "J"),
					new Beat(startTime + gap * 100, "S"), new Beat(startTime + gap * 102, "D"),
					new Beat(startTime + gap * 104, "D"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 110, "D"), new Beat(startTime + gap * 115, "S"),
					new Beat(startTime + gap * 120, "F"), new Beat(startTime + gap * 124, "F"),
					new Beat(startTime + gap * 126, "K"), new Beat(startTime + gap * 127, "Space"),
					new Beat(startTime + gap * 129, "F"), new Beat(startTime + gap * 130, "K"),
					new Beat(startTime + gap * 132, "K"), new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 137, "D"), new Beat(startTime + gap * 139, "K"),
					new Beat(startTime + gap * 142, "F"), new Beat(startTime + gap * 144, "F"),
					new Beat(startTime + gap * 146, "K"), new Beat(startTime + gap * 150, "F"),
					new Beat(startTime + gap * 152, "J"), new Beat(startTime + gap * 153, "F"),
					new Beat(startTime + gap * 156, "J"), new Beat(startTime + gap * 158, "F"),
					new Beat(startTime + gap * 10, "K"), new Beat(startTime + gap * 160, "S"),
					new Beat(startTime + gap * 162, "S"), new Beat(startTime + gap * 164, "K"),
					new Beat(startTime + gap * 166, "K"), new Beat(startTime + gap * 169, "Space"),
					new Beat(startTime + gap * 170, "J"), new Beat(startTime + gap * 171, "S"),
					new Beat(startTime + gap * 174, "K"), new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 178, "D"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 182, "K"), new Beat(startTime + gap * 183, "J"),
					new Beat(startTime + gap * 186, "K"), new Beat(startTime + gap * 190, "D"),
					new Beat(startTime + gap * 194, "Space"), new Beat(startTime + gap * 198, "S"),
					new Beat(startTime + gap * 205, "F"), new Beat(startTime + gap * 212, "D"),
					new Beat(startTime + gap * 216, "F"), new Beat(startTime + gap * 219, "K"),
					new Beat(startTime + gap * 220, "F"), new Beat(startTime + gap * 224, "Space"),
					new Beat(startTime + gap * 225, "K"), new Beat(startTime + gap * 227, "K"),
					new Beat(startTime + gap * 229, "F"), new Beat(startTime + gap * 230, "S"),
					new Beat(startTime + gap * 232, "K"), new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 237, "F"), new Beat(startTime + gap * 239, "K"),
					new Beat(startTime + gap * 242, "F"), new Beat(startTime + gap * 243, "J"),
					new Beat(startTime + gap * 246, "K"), new Beat(startTime + gap * 250, "F"),
					new Beat(startTime + gap * 251, "D"), new Beat(startTime + gap * 253, "L"),
					new Beat(startTime + gap * 256, "J"), new Beat(startTime + gap * 257, "K"),
					new Beat(startTime + gap * 260, "S"), new Beat(startTime + gap * 262, "S"),
					new Beat(startTime + gap * 263, "F"), new Beat(startTime + gap * 266, "K"),
					new Beat(startTime + gap * 269, "S"), new Beat(startTime + gap * 270, "K"),
					new Beat(startTime + gap * 271, "S"), new Beat(startTime + gap * 274, "K"),
					new Beat(startTime + gap * 275, "K"), new Beat(startTime + gap * 278, "D"),
					new Beat(startTime + gap * 281, "F"), new Beat(startTime + gap * 282, "F"),
					new Beat(startTime + gap * 283, "J"), new Beat(startTime + gap * 286, "K"),
					new Beat(startTime + gap * 287, "K"), new Beat(startTime + gap * 297, "Space"),
					new Beat(startTime + gap * 300, "S"), new Beat(startTime + gap * 301, "K"),
					new Beat(startTime + gap * 302, "D"), new Beat(startTime + gap * 306, "F"),
					new Beat(startTime + gap * 307, "K"), new Beat(startTime + gap * 310, "J"),
					new Beat(startTime + gap * 320, "S"), new Beat(startTime + gap * 321, "J"),
					new Beat(startTime + gap * 322, "L"), new Beat(startTime + gap * 324, "F"),
					new Beat(startTime + gap * 326, "F"), new Beat(startTime + gap * 327, "K"),
					new Beat(startTime + gap * 329, "F"), new Beat(startTime + gap * 330, "J"),
					new Beat(startTime + gap * 332, "K"), new Beat(startTime + gap * 336, "D"),
					new Beat(startTime + gap * 337, "S"), new Beat(startTime + gap * 339, "K"),
					new Beat(startTime + gap * 342, "F"), new Beat(startTime + gap * 343, "F"),
					new Beat(startTime + gap * 346, "K"), new Beat(startTime + gap * 350, "F"),
					new Beat(startTime + gap * 351, "K"), new Beat(startTime + gap * 353, "L"),
					new Beat(startTime + gap * 356, "J"), new Beat(startTime + gap * 357, "K"),
					new Beat(startTime + gap * 360, "S"), new Beat(startTime + gap * 362, "S"),
					new Beat(startTime + gap * 363, "F"), new Beat(startTime + gap * 366, "K"),
					new Beat(startTime + gap * 369, "L"), new Beat(startTime + gap * 370, "D"),
					new Beat(startTime + gap * 371, "S"), new Beat(startTime + gap * 374, "K"),
					new Beat(startTime + gap * 375, "F"), new Beat(startTime + gap * 378, "D"),
					new Beat(startTime + gap * 381, "F"), new Beat(startTime + gap * 382, "K"),
					new Beat(startTime + gap * 383, "J"), new Beat(startTime + gap * 384, "Space"),
					new Beat(startTime + gap * 385, "F"), new Beat(startTime + gap * 388, "S"),
					new Beat(startTime + gap * 392, "D"), new Beat(startTime + gap * 393, "J"),
					new Beat(startTime + gap * 396, "F"), new Beat(startTime + gap * 400, "S"),
					new Beat(startTime + gap * 401, "K"), new Beat(startTime + gap * 404, "F"),
					new Beat(startTime + gap * 407, "K"), new Beat(startTime + gap * 408, "L"),
					new Beat(startTime + gap * 410, "F"), new Beat(startTime + gap * 412, "K"),
					new Beat(startTime + gap * 413, "S"), new Beat(startTime + gap * 416, "D"),
					new Beat(startTime + gap * 419, "K"), new Beat(startTime + gap * 420, "F"),
					new Beat(startTime + gap * 422, "F"), new Beat(startTime + gap * 426, "J"),
					new Beat(startTime + gap * 427, "D"), new Beat(startTime + gap * 430, "F"),
					new Beat(startTime + gap * 433, "L"), new Beat(startTime + gap * 434, "K"),
					new Beat(startTime + gap * 436, "J"), new Beat(startTime + gap * 440, "S"),
					new Beat(startTime + gap * 441, "F"), new Beat(startTime + gap * 442, "F"),
					new Beat(startTime + gap * 446, "K"), new Beat(startTime + gap * 447, "J"),
					new Beat(startTime + gap * 449, "S"), new Beat(startTime + gap * 455, "F"),
					new Beat(startTime + gap * 456, "K"), new Beat(startTime + gap * 458, "K"),
					new Beat(startTime + gap * 459, "D"), new Beat(startTime + gap * 460, "F"),
					new Beat(startTime + gap * 463, "J"), new Beat(startTime + gap * 466, "K"),
					new Beat(startTime + gap * 467, "K"), new Beat(startTime + gap * 477, "Space"),
					new Beat(startTime + gap * 480, "S"), new Beat(startTime + gap * 482, "F"),
					new Beat(startTime + gap * 482, "D"), new Beat(startTime + gap * 486, "F"),
					new Beat(startTime + gap * 487, "J"), new Beat(startTime + gap * 490, "D"),
					new Beat(startTime + gap * 493, "F"), new Beat(startTime + gap * 494, "F"),
					new Beat(startTime + gap * 494, "S"), new Beat(startTime + gap * 497, "Space"),
					new Beat(startTime + gap * 498, "F"), new Beat(startTime + gap * 499, "F"),
					new Beat(startTime + gap * 502, "K"), new Beat(startTime + gap * 503, "S"),
					new Beat(startTime + gap * 506, "D"), new Beat(startTime + gap * 509, "L"),
					new Beat(startTime + gap * 510, "K"), new Beat(startTime + gap * 512, "F"),
					new Beat(startTime + gap * 516, "K"), new Beat(startTime + gap * 517, "F"),
					new Beat(startTime + gap * 520, "F"), new Beat(startTime + gap * 523, "D"),
					new Beat(startTime + gap * 524, "S"), new Beat(startTime + gap * 526, "J"),
					new Beat(startTime + gap * 530, "S"), new Beat(startTime + gap * 531, "D"),
					new Beat(startTime + gap * 532, "F"), new Beat(startTime + gap * 536, "K"),
					new Beat(startTime + gap * 537, "F"), new Beat(startTime + gap * 539, "Space"),
					new Beat(startTime + gap * 541, "S"), new Beat(startTime + gap * 542, "K"),
					new Beat(startTime + gap * 547, "K"), new Beat(startTime + gap * 548, "D"),
					new Beat(startTime + gap * 549, "J"), new Beat(startTime + gap * 551, "F"),
					new Beat(startTime + gap * 553, "J"), new Beat(startTime + gap * 554, "F"),
					new Beat(startTime + gap * 556, "K"), new Beat(startTime + gap * 564, "Space"),
					new Beat(startTime + gap * 565, "K"), new Beat(startTime + gap * 568, "S"),
					new Beat(startTime + gap * 572, "D"), new Beat(startTime + gap * 573, "L"),
					new Beat(startTime + gap * 576, "F"), new Beat(startTime + gap * 580, "F"),
					new Beat(startTime + gap * 581, "F"), new Beat(startTime + gap * 584, "Space"),
					new Beat(startTime + gap * 587, "K"), new Beat(startTime + gap * 588, "S"),
					new Beat(startTime + gap * 589, "F"), new Beat(startTime + gap * 592, "K"),
					new Beat(startTime + gap * 593, "K"), new Beat(startTime + gap * 596, "D"),
					new Beat(startTime + gap * 599, "K"), new Beat(startTime + gap * 600, "D"),
					new Beat(startTime + gap * 602, "F"), new Beat(startTime + gap * 606, "K"),
					new Beat(startTime + gap * 607, "K"), new Beat(startTime + gap * 610, "F"),
					new Beat(startTime + gap * 613, "L"), new Beat(startTime + gap * 614, "F"),
					new Beat(startTime + gap * 616, "J"), new Beat(startTime + gap * 620, "S"),
					new Beat(startTime + gap * 621, "J"), new Beat(startTime + gap * 622, "S"),
					new Beat(startTime + gap * 626, "K"), new Beat(startTime + gap * 627, "L"),
					new Beat(startTime + gap * 629, "S"), new Beat(startTime + gap * 631, "S"),
					new Beat(startTime + gap * 632, "Space"), new Beat(startTime + gap * 634, "K"),
					new Beat(startTime + gap * 638, "D"), new Beat(startTime + gap * 639, "F"),
					new Beat(startTime + gap * 641, "F"), new Beat(startTime + gap * 643, "J"),
					new Beat(startTime + gap * 644, "D"), new Beat(startTime + gap * 646, "K"),
					new Beat(startTime + gap * 647, "Space"), new Beat(startTime + gap * 648, "K"),
					new Beat(startTime + gap * 650, "S"), new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 653, "L"), new Beat(startTime + gap * 656, "F"),
					new Beat(startTime + gap * 660, "D"), new Beat(startTime + gap * 661, "J"),
					new Beat(startTime + gap * 665, "F"), new Beat(startTime + gap * 671, "F"),
					new Beat(startTime + gap * 672, "F"), new Beat(startTime + gap * 674, "F"),
					new Beat(startTime + gap * 677, "K"), new Beat(startTime + gap * 678, "K"),
					new Beat(startTime + gap * 679, "F"), new Beat(startTime + gap * 682, "K"),
					new Beat(startTime + gap * 683, "S"), new Beat(startTime + gap * 686, "D"),
					new Beat(startTime + gap * 689, "K"), new Beat(startTime + gap * 690, "D"),
					new Beat(startTime + gap * 692, "F"), new Beat(startTime + gap * 696, "K"),
					new Beat(startTime + gap * 697, "F"), new Beat(startTime + gap * 700, "F"),
					new Beat(startTime + gap * 703, "L"), new Beat(startTime + gap * 704, "K"),
					new Beat(startTime + gap * 706, "J"), new Beat(startTime + gap * 710, "S"),
					new Beat(startTime + gap * 711, "D"), new Beat(startTime + gap * 712, "S"),
					new Beat(startTime + gap * 716, "K"), new Beat(startTime + gap * 717, "F"),
					new Beat(startTime + gap * 719, "F"), new Beat(startTime + gap * 720, "K"),
					new Beat(startTime + gap * 725, "K"), new Beat(startTime + gap * 726, "D"),
					new Beat(startTime + gap * 728, "J"), new Beat(startTime + gap * 729, "F"),
					new Beat(startTime + gap * 730, "J"), new Beat(startTime + gap * 733, "F"),
					new Beat(startTime + gap * 746, "K"), new Beat(startTime + gap * 749, "Space"),
					new Beat(startTime + gap * 750, "K"), new Beat(startTime + gap * 756, "S"),
					new Beat(startTime + gap * 757, "D"), new Beat(startTime + gap * 758, "L"),
					new Beat(startTime + gap * 759, "F"), new Beat(startTime + gap * 760, "F"),
					new Beat(startTime + gap * 762, "F"), new Beat(startTime + gap * 763, "Space"),
					new Beat(startTime + gap * 765, "K"), new Beat(startTime + gap * 768, "S"),
					new Beat(startTime + gap * 770, "F"), new Beat(startTime + gap * 770, "K"),
					new Beat(startTime + gap * 772, "K"), new Beat(startTime + gap * 773, "D"),
					new Beat(startTime + gap * 774, "K"), new Beat(startTime + gap * 775, "D"),
					new Beat(startTime + gap * 776, "F"), new Beat(startTime + gap * 777, "K"),
					new Beat(startTime + gap * 780, "K"), new Beat(startTime + gap * 781, "F"),
					new Beat(startTime + gap * 782, "L"), new Beat(startTime + gap * 783, "F"),
					new Beat(startTime + gap * 784, "J"), new Beat(startTime + gap * 784, "S"),
					new Beat(startTime + gap * 785, "J"), new Beat(startTime + gap * 785, "S"),
					new Beat(startTime + gap * 787, "K"), new Beat(startTime + gap * 789, "L"),
					new Beat(startTime + gap * 790, "S"), new Beat(startTime + gap * 792, "S"),
					new Beat(startTime + gap * 793, "Space"), new Beat(startTime + gap * 794, "K"),
					new Beat(startTime + gap * 795, "D"), new Beat(startTime + gap * 795, "F"),
					new Beat(startTime + gap * 800, "F"), new Beat(startTime + gap * 801, "J"),
					new Beat(startTime + gap * 802, "D"), new Beat(startTime + gap * 803, "K"),
					new Beat(startTime + gap * 810, "D"), new Beat(startTime + gap * 812, "L"),
					new Beat(startTime + gap * 813, "F"), new Beat(startTime + gap * 816, "F"),
					new Beat(startTime + gap * 820, "F"), new Beat(startTime + gap * 821, "Space"),
					new Beat(startTime + gap * 822, "K"), new Beat(startTime + gap * 822, "S"),
					new Beat(startTime + gap * 823, "F"), new Beat(startTime + gap * 824, "K"),
					new Beat(startTime + gap * 825, "K"), new Beat(startTime + gap * 826, "D"),
					new Beat(startTime + gap * 827, "K"), new Beat(startTime + gap * 830, "D"),
					new Beat(startTime + gap * 831, "F"), new Beat(startTime + gap * 832, "K"),
					new Beat(startTime + gap * 834, "K"), new Beat(startTime + gap * 835, "F"),
					new Beat(startTime + gap * 838, "L"), new Beat(startTime + gap * 839, "F"),
					new Beat(startTime + gap * 840, "J"), new Beat(startTime + gap * 841, "S"),
					new Beat(startTime + gap * 842, "F"), new Beat(startTime + gap * 843, "K"),
					new Beat(startTime + gap * 845, "J"), new Beat(startTime + gap * 845, "Space"),
					new Beat(startTime + gap * 846, "F"), new Beat(startTime + gap * 847, "S"),
					new Beat(startTime + gap * 848, "D"), new Beat(startTime + gap * 848, "J"),
					new Beat(startTime + gap * 849, "F"), new Beat(startTime + gap * 855, "S"),
					new Beat(startTime + gap * 856, "K"), new Beat(startTime + gap * 857, "F"),
					new Beat(startTime + gap * 858, "K"), new Beat(startTime + gap * 859, "L"),
					new Beat(startTime + gap * 860, "F"), new Beat(startTime + gap * 862, "K"),
					new Beat(startTime + gap * 863, "S"), new Beat(startTime + gap * 864, "D"),
					new Beat(startTime + gap * 865, "K"), new Beat(startTime + gap * 865, "F"),
					new Beat(startTime + gap * 870, "F"), new Beat(startTime + gap * 875, "J"),
					new Beat(startTime + gap * 877, "D"), new Beat(startTime + gap * 878, "F"),
					new Beat(startTime + gap * 879, "L"), new Beat(startTime + gap * 880, "K"),
					new Beat(startTime + gap * 881, "F"), new Beat(startTime + gap * 882, "F"),
					new Beat(startTime + gap * 883, "K"), new Beat(startTime + gap * 884, "K"),
					new Beat(startTime + gap * 885, "F"), new Beat(startTime + gap * 886, "K"),
					new Beat(startTime + gap * 887, "S"), new Beat(startTime + gap * 889, "D"),
					new Beat(startTime + gap * 892, "K"), new Beat(startTime + gap * 893, "D"),
					new Beat(startTime + gap * 895, "F"), new Beat(startTime + gap * 896, "K"),
					new Beat(startTime + gap * 897, "F"), new Beat(startTime + gap * 898, "F"),
					new Beat(startTime + gap * 898, "L"), new Beat(startTime + gap * 900, "K"),
					new Beat(startTime + gap * 910, "J"), new Beat(startTime + gap * 911, "S"),
					new Beat(startTime + gap * 912, "D"), new Beat(startTime + gap * 913, "S"),
					new Beat(startTime + gap * 914, "K"), new Beat(startTime + gap * 915, "F"),
					new Beat(startTime + gap * 916, "F"), new Beat(startTime + gap * 917, "K"),  
					new Beat(startTime + gap * 920, "S"), new Beat(startTime + gap * 922, "J"),
					new Beat(startTime + gap * 923, "S"), new Beat(startTime + gap * 924, "D"),
					new Beat(startTime + gap * 925, "F"), new Beat(startTime + gap * 928, "K"),
					new Beat(startTime + gap * 929, "F"), new Beat(startTime + gap * 930, "Space"),
					new Beat(startTime + gap * 933, "S"), new Beat(startTime + gap * 934, "K"),
					new Beat(startTime + gap * 935, "K"), new Beat(startTime + gap * 937, "D"),
					new Beat(startTime + gap * 938, "J"), new Beat(startTime + gap * 939, "F"),
					new Beat(startTime + gap * 941, "J"), new Beat(startTime + gap * 943, "F"),
					new Beat(startTime + gap * 945, "K"), new Beat(startTime + gap * 947, "Space"),
					new Beat(startTime + gap * 948, "K"), new Beat(startTime + gap * 949, "S"),
					new Beat(startTime + gap * 950, "D"), new Beat(startTime + gap * 955, "L"),
					new Beat(startTime + gap * 956, "F"), new Beat(startTime + gap * 957, "F"),
					new Beat(startTime + gap * 959, "F"), new Beat(startTime + gap * 960, "Space"),
					new Beat(startTime + gap * 961, "K"), new Beat(startTime + gap * 962, "S"),
					new Beat(startTime + gap * 963, "F"), new Beat(startTime + gap * 967, "K"),
					new Beat(startTime + gap * 968, "K"), new Beat(startTime + gap * 970, "D"),
					new Beat(startTime + gap * 972, "K"), new Beat(startTime + gap * 973, "D"),
					new Beat(startTime + gap * 974, "F"), new Beat(startTime + gap * 975, "K"),
					new Beat(startTime + gap * 976, "K"), new Beat(startTime + gap * 979, "F"),
					new Beat(startTime + gap * 985, "L"), new Beat(startTime + gap * 986, "F"),
					new Beat(startTime + gap * 989, "J"), new Beat(startTime + gap * 991, "S"),
					new Beat(startTime + gap * 993, "J"), new Beat(startTime + gap * 994, "S"),
					new Beat(startTime + gap * 1000, "S"), new Beat(startTime + gap * 1002, "S"),
					new Beat(startTime + gap * 1003, "F"), new Beat(startTime + gap * 1005, "K"),
					new Beat(startTime + gap * 1006, "S"), new Beat(startTime + gap * 1007, "K"),
					new Beat(startTime + gap * 1008, "S"), new Beat(startTime + gap * 1009, "K"),
					new Beat(startTime + gap * 1010, "K"), new Beat(startTime + gap * 1015, "D"),
					new Beat(startTime + gap * 1016, "Space"), new Beat(startTime + gap * 1019, "S"),
					new Beat(startTime + gap * 1022, "F"), new Beat(startTime + gap * 1023, "D"),
					new Beat(startTime + gap * 1025, "F"), new Beat(startTime + gap * 1026, "K"),
					new Beat(startTime + gap * 1027, "F"), new Beat(startTime + gap * 1029, "Space"),
					new Beat(startTime + gap * 1030, "K"), new Beat(startTime + gap * 1035, "K"),
					new Beat(startTime + gap * 1036, "F"), new Beat(startTime + gap * 1037, "S"),
					new Beat(startTime + gap * 1038, "K"), new Beat(startTime + gap * 1039, "D"),
					new Beat(startTime + gap * 1040, "F"), new Beat(startTime + gap * 1055, "K"),
					new Beat(startTime + gap * 1056, "S"), new Beat(startTime + gap * 1057, "K"),
					new Beat(startTime + gap * 1058, "K"), new Beat(startTime + gap * 1059, "Space"),
					new Beat(startTime + gap * 1060, "J"), new Beat(startTime + gap * 1061, "S"),
					new Beat(startTime + gap * 1062, "K"), new Beat(startTime + gap * 1063, "F"),
					new Beat(startTime + gap * 1063, "D"), new Beat(startTime + gap * 1066, "F"),
					new Beat(startTime + gap * 1067, "K"), new Beat(startTime + gap * 1068, "J"),
					new Beat(startTime + gap * 1069, "F"), new Beat(startTime + gap * 1070, "J"),
					new Beat(startTime + gap * 1075, "K"), new Beat(startTime + gap * 1076, "F"),
					new Beat(startTime + gap * 1077, "D"), new Beat(startTime + gap * 1078, "L"),
					new Beat(startTime + gap * 1079, "J"), new Beat(startTime + gap * 1080, "K"),
					new Beat(startTime + gap * 1082, "K"), new Beat(startTime + gap * 1087, "D"),
					new Beat(startTime + gap * 1088, "F"), new Beat(startTime + gap * 1090, "F"),
					new Beat(startTime + gap * 1092, "J"), new Beat(startTime + gap * 1099, "K"),
					new Beat(startTime + gap * 1100, "K"), new Beat(startTime + gap * 1108, "Space"),
					new Beat(startTime + gap * 1109, "S"), new Beat(startTime + gap * 1110, "K"),
					new Beat(startTime + gap * 1115, "D"), new Beat(startTime + gap * 1116, "F"),
					new Beat(startTime + gap * 1117, "K"), new Beat(startTime + gap * 1118, "J"),
					new Beat(startTime + gap * 1125, "S"), new Beat(startTime + gap * 1126, "J"),
					new Beat(startTime + gap * 1127, "L"), new Beat(startTime + gap * 1128, "F"),
					new Beat(startTime + gap * 1129, "F"), new Beat(startTime + gap * 1132, "K"),
					new Beat(startTime + gap * 1135, "F"), new Beat(startTime + gap * 1136, "J"),
					new Beat(startTime + gap * 1138, "K"), new Beat(startTime + gap * 1139, "D"),
					new Beat(startTime + gap * 1140, "S"), new Beat(startTime + gap * 1142, "K"),
					new Beat(startTime + gap * 1145, "F"), new Beat(startTime + gap * 1146, "F"),
					new Beat(startTime + gap * 1148, "K"), new Beat(startTime + gap * 1150, "F"),
					new Beat(startTime + gap * 1153, "K"), new Beat(startTime + gap * 1154, "L"),
					new Beat(startTime + gap * 1156, "J"), new Beat(startTime + gap * 1159, "K"),
					new Beat(startTime + gap * 1162, "D"), new Beat(startTime + gap * 1163, "L"),
					new Beat(startTime + gap * 1164, "F"), new Beat(startTime + gap * 1165, "F"),
					new Beat(startTime + gap * 1166, "F"), new Beat(startTime + gap * 1168, "Space"),
					new Beat(startTime + gap * 1170, "K"), new Beat(startTime + gap * 1178, "S"),
					new Beat(startTime + gap * 1200, "F"), new Beat(startTime + gap * 1212, "K"),
					new Beat(startTime + gap * 1215, "K"), new Beat(startTime + gap * 1222, "D"),
					new Beat(startTime + gap * 1223, "K"), new Beat(startTime + gap * 1223, "D"),
					new Beat(startTime + gap * 1224, "F"), new Beat(startTime + gap * 1225, "K"),
					new Beat(startTime + gap * 1226, "K"), new Beat(startTime + gap * 1227, "F"),
					new Beat(startTime + gap * 1228, "L"), new Beat(startTime + gap * 1229, "F"),
					new Beat(startTime + gap * 1230, "J"), new Beat(startTime + gap * 1234, "S"),
					new Beat(startTime + gap * 1235, "J"), new Beat(startTime + gap * 1236, "S"),
					new Beat(startTime + gap * 1240, "K"), new Beat(startTime + gap * 1241, "L"),
					new Beat(startTime + gap * 1242, "S"), new Beat(startTime + gap * 1245, "S"),
					new Beat(startTime + gap * 1249, "Space"), new Beat(startTime + gap * 1250, "K"),
					new Beat(startTime + gap * 1251, "D"), new Beat(startTime + gap * 1253, "F"),
					new Beat(startTime + gap * 1255, "S"), new Beat(startTime + gap * 1257, "S"),
					new Beat(startTime + gap * 1258, "F"), new Beat(startTime + gap * 1259, "K"),
					new Beat(startTime + gap * 1270, "S"), new Beat(startTime + gap * 1271, "K"),
					new Beat(startTime + gap * 1272, "S"), new Beat(startTime + gap * 1273, "K"),
					new Beat(startTime + gap * 1274, "K"), new Beat(startTime + gap * 1275, "D"),
					new Beat(startTime + gap * 1276, "F"), new Beat(startTime + gap * 1277, "F"),
					new Beat(startTime + gap * 1278, "J"), new Beat(startTime + gap * 1279, "K"),
					new Beat(startTime + gap * 1280, "K"), new Beat(startTime + gap * 1281, "Space"),
					new Beat(startTime + gap * 1282, "S"), new Beat(startTime + gap * 1283, "K"),
					new Beat(startTime + gap * 1284, "D"), new Beat(startTime + gap * 1285, "F"),
					new Beat(startTime + gap * 1286, "K"), new Beat(startTime + gap * 1287, "J"),
					new Beat(startTime + gap * 1288, "S"), new Beat(startTime + gap * 1289, "J"),
					new Beat(startTime + gap * 1290, "L"), new Beat(startTime + gap * 1291, "F"),
					new Beat(startTime + gap * 1292, "F"), new Beat(startTime + gap * 1293, "K"),
					new Beat(startTime + gap * 1294, "F"), new Beat(startTime + gap * 1295, "J"),
					new Beat(startTime + gap * 1296, "K"), new Beat(startTime + gap * 1298, "D"),
					new Beat(startTime + gap * 1299, "S"), new Beat(startTime + gap * 1300, "K"),
					new Beat(startTime + gap * 1311, "F"), new Beat(startTime + gap * 1312, "F"),
					new Beat(startTime + gap * 1313, "K"), new Beat(startTime + gap * 1315, "F"),
					new Beat(startTime + gap * 1316, "K"), new Beat(startTime + gap * 1318, "L"),
					new Beat(startTime + gap * 1320, "J"), new Beat(startTime + gap * 1321, "K"),
					new Beat(startTime + gap * 1324, "S"), new Beat(startTime + gap * 1325, "S"),
					new Beat(startTime + gap * 1326, "F"), new Beat(startTime + gap * 1328, "K"),
					new Beat(startTime + gap * 1330, "L"), new Beat(startTime + gap * 1332, "D"),
					new Beat(startTime + gap * 1333, "S"), new Beat(startTime + gap * 1335, "K"),
					new Beat(startTime + gap * 1336, "F"), new Beat(startTime + gap * 1337, "D"),
					new Beat(startTime + gap * 1338, "F"), new Beat(startTime + gap * 1339, "K"),
					new Beat(startTime + gap * 1342, "J"), new Beat(startTime + gap * 1345, "Space"),
					new Beat(startTime + gap * 1346, "F"), new Beat(startTime + gap * 1347, "S"),
					new Beat(startTime + gap * 1348, "D"), new Beat(startTime + gap * 1350, "J"),
					new Beat(startTime + gap * 1351, "F"), new Beat(startTime + gap * 1352, "S"),
					new Beat(startTime + gap * 1355, "K"), new Beat(startTime + gap * 1356, "F"),
					new Beat(startTime + gap * 1358, "K"), new Beat(startTime + gap * 1359, "L"),
					new Beat(startTime + gap * 1360, "F"), new Beat(startTime + gap * 1362, "K"),
					new Beat(startTime + gap * 1363, "S"), new Beat(startTime + gap * 1368, "D"),
					new Beat(startTime + gap * 1369, "K"), new Beat(startTime + gap * 1370, "F"),
					new Beat(startTime + gap * 1371, "F"), new Beat(startTime + gap * 1372, "J"),
					new Beat(startTime + gap * 1373, "D"), new Beat(startTime + gap * 1375, "F"),
					new Beat(startTime + gap * 1376, "L"), new Beat(startTime + gap * 1379, "K"),
					new Beat(startTime + gap * 1380, "J"), new Beat(startTime + gap * 1381, "S"),
					new Beat(startTime + gap * 1382, "F"), new Beat(startTime + gap * 1384, "F"),
					new Beat(startTime + gap * 1386, "K"), new Beat(startTime + gap * 1389, "J"),
					new Beat(startTime + gap * 1390, "S"), new Beat(startTime + gap * 1392, "F"),
					new Beat(startTime + gap * 1393, "K"), new Beat(startTime + gap * 1394, "K"),
					new Beat(startTime + gap * 1395, "D"), new Beat(startTime + gap * 1400, "F"),			
					new Beat(startTime + gap * 1402, "J"), new Beat(startTime + gap * 1403, "K"),
					new Beat(startTime + gap * 1404, "K"), new Beat(startTime + gap * 1405, "Space"),
					new Beat(startTime + gap * 1406, "S"), new Beat(startTime + gap * 1407, "F"),
					new Beat(startTime + gap * 1408, "D"), new Beat(startTime + gap * 1409, "F"),
					new Beat(startTime + gap * 1410, "J"), new Beat(startTime + gap * 1412, "D"),
					new Beat(startTime + gap * 1413, "F"), new Beat(startTime + gap * 1415, "F"),
					new Beat(startTime + gap * 1416, "S"), new Beat(startTime + gap * 1416, "Space"),
					new Beat(startTime + gap * 1417, "F"), new Beat(startTime + gap * 1418, "F"),
					new Beat(startTime + gap * 1419, "K"), new Beat(startTime + gap * 1420, "S"),
					new Beat(startTime + gap * 1422, "D"), new Beat(startTime + gap * 1423, "L"),
					new Beat(startTime + gap * 1424, "K"), new Beat(startTime + gap * 1425, "F"),
					new Beat(startTime + gap * 1426, "K"), new Beat(startTime + gap * 1427, "F"),
					new Beat(startTime + gap * 1428, "F"), new Beat(startTime + gap * 1429, "D"),
					new Beat(startTime + gap * 1430, "S"), new Beat(startTime + gap * 1431, "J"),
					new Beat(startTime + gap * 1432, "S"), new Beat(startTime + gap * 1433, "D"),
					new Beat(startTime + gap * 1433, "F"), new Beat(startTime + gap * 1434, "K"),new Beat(startTime + gap * 1435, "F"),
					new Beat(startTime + gap * 1436, "K"),new Beat(startTime + gap * 1437, "K"), new Beat(startTime + gap * 1438, "S"),
					new Beat(startTime + gap * 1439, "K"), new Beat(startTime + gap * 1440, "K"),new Beat(startTime + gap * 1442, "F"),
					new Beat(startTime + gap * 1444, "K"),new Beat(startTime + gap * 1446, "S"), new Beat(startTime + gap * 1447, "F"),
					new Beat(startTime + gap * 1450, "S"),new Beat(startTime + gap * 1460, "K")
			};
		}  
		int i = 0;
		gameMusic.start();
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if (beats[i].getTime() <= gameMusic.remainingTime()) {
				Note note = new Note(beats[i].getNoteName(), score, difficulty);
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}

		}
	}

	
	//점수를 얻으면 돌려주는 메소드
	public Score getScore() {
		return score;
	}
// 판정에 일치할 때 이벤트 설정 함수
	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			effect1Image = new ImageIcon(Main.class.getResource("../images/effect1.png")).getImage();
		}
		if (judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/miss.png")).getImage();
		} else if (judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/late.png")).getImage();
		} else if (judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/good.png")).getImage();
		} else if (judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/great.png")).getImage();
		} else if (judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfect.png")).getImage();
		} else if (judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/early.png")).getImage();
		}
	}
}
