package game1;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

//main program that shows screen, buttons, and all the other components to user
public class BeatMaster extends JFrame {

	
	private Image backgroundImage;
	private Graphics BackgroundGraphic; /* 원하는 컴포넌트만 화면에 출력, 버퍼에 이미지 받아서 이미지 갱신-> 전체 화면에 이미지를 담는거 */
	private Image background = new ImageIcon(Main.class.getResource("../images/background2.jpg")).getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menubar.png")));  /* menuBar라는 객체에 해당 이미지가 들어가는것 */
	
	private ImageIcon exitBtnOnImage =  new ImageIcon(Main.class.getResource("../images/exitButtonOn.png"));
	private ImageIcon exitBtnImage =  new ImageIcon(Main.class.getResource("../images/exitButton.png"));
	
	
	private ImageIcon StartBtnImage =  new ImageIcon(Main.class.getResource("../images/startBtn.png"));
	private ImageIcon StartBtnOnImage =  new ImageIcon(Main.class.getResource("../images/startBtnOn.png"));
	private ImageIcon QuitBtnImage =  new ImageIcon(Main.class.getResource("../images/quitBtn.png"));
	private ImageIcon QuitBtnOnImage =  new ImageIcon(Main.class.getResource("../images/quitBtnOn.png"));
	
	
	private ImageIcon rightBtnImage =  new ImageIcon(Main.class.getResource("../images/rightArrow.png"));
	private ImageIcon rightBtnOnImage =  new ImageIcon(Main.class.getResource("../images/rightArrowOn.png"));
	private ImageIcon leftBtnImage =  new ImageIcon(Main.class.getResource("../images/leftArrow.png"));
	private ImageIcon leftBtnOnImage =  new ImageIcon(Main.class.getResource("../images/leftArrowOn.png"));
	
	private ImageIcon backBtnImage =  new ImageIcon(Main.class.getResource("../images/backBtn.png"));
	private ImageIcon backBtnOnImage =  new ImageIcon(Main.class.getResource("../images/backBtnOn.png"));
	
	private ImageIcon easyBtnImage =  new ImageIcon(Main.class.getResource("../images/EasyBtn.png"));
	private ImageIcon easyBtnOnImage =  new ImageIcon(Main.class.getResource("../images/EasyBtnOn.png"));
	private ImageIcon hardBtnImage =  new ImageIcon(Main.class.getResource("../images/HardBtn.png"));
	private ImageIcon hardBtnOnImage =  new ImageIcon(Main.class.getResource("../images/HardBtnOn.png"));
	
	//변수생성
	private JButton exitBtn = new JButton(exitBtnImage);
	private JButton startBtn = new JButton(StartBtnImage);
	private JButton quitBtn = new JButton(QuitBtnImage);
	private JButton rightArrowBtn = new JButton(rightBtnImage);
	private JButton leftArrowBtn = new JButton(leftBtnImage);
	private JButton easyBtn = new JButton(easyBtnImage);
	private JButton hardBtn = new JButton(hardBtnImage);
	private JButton backBtn = new JButton(backBtnImage);
	public static Game game; //동시에 게임 실행하지 않도록 static
	
	public String score;
////	public String userid;
	
	//배경음악 시장
	Music backgroundMusic = new Music("intromusic.mp3", true);
	
	private int mouseX, mouseY; /* mouse의 x,y좌표를 의미 */

	private boolean isMainFrame = false;
	private boolean isGameFrame = false;
	private boolean isResultFrame = false;
	
	ArrayList<Track> trackList = new ArrayList<Track>();
	
	//이미지 , 음악 선택
	private Image selectedImage;
	private Image titleImage;
	private Music selectedMusic;
	private int nowSelected = 0;
	
	//설정 모드
	////public BeatMaster(String userid)
	public BeatMaster(){   //생성자
		
		////this.userid = userid;
		
//		trackList.add(new Track("yummy title.png","Yummy Start Image.png","Ymmuy Game Image.png",
//				"yummy Selected.mp3","Justin Bieber - Yummy.mp3","Justin Bieber - Yummy",211000));
		trackList.add(new Track("anysong title.png","Any Song Start Image.png","AnySong Game Image.png",
				"zico Selected.mp3","지코 (ZICO) - 아무노래 (Any song).mp3","Zico - Anysong",228000));
		trackList.add(new Track("martini blue title.png","Martini Blue Start Image.png","Martini blue Game Image.png",
				"dpr Selected.mp3","DPR live- Martini blue.mp3","DPR live- Martini blue",195000));
				
		
		setUndecorated(true); /* 실행을 했을때 기존의 메뉴바가 보이지 않게 함 */
		setTitle("BeatMaster");
		setSize(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		setResizable(false);//사용자가 임의적으로 줄이거나 크게 할 수 없음
		setLocationRelativeTo(null); // 화면을 중앙에 나오게 함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//창이 닫아도 프로그램이 돌아갈수 있는데 프로그램도 꺼지도록 설정
		addKeyListener(new KeyListener());
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		leftArrowBtn.setVisible(false);
		rightArrowBtn.setVisible(false);
		easyBtn.setVisible(false);
		hardBtn.setVisible(false);
		backgroundMusic.start();
		
		//exitButton setting
		exitBtn.setBounds(1245, 0, 30, 30);
		exitBtn.setBorderPainted(false);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { /* 마우스를 enter했을때 */
				exitBtn.setIcon(exitBtnOnImage);
				exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); /* mouse cursor를 올릴때 손모양설정 */
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {   /* 마우스를Exited했을때 */
				exitBtn.setIcon(exitBtnImage);
				exitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); /* mouse cursor를 올릴때 기본모양설정 */
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				try {
					Thread.sleep(500);
				}catch(Exception e2) {
					System.out.println(e2.getMessage());
				}
				System.exit(0);
			}
		});
		add(exitBtn);
		
		//Start Button Setting
		startBtn.setBounds(40, 200, 400, 100);
		startBtn.setBorderPainted(false);
		startBtn.setContentAreaFilled(false);
		startBtn.setFocusPainted(false);
		startBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startBtn.setIcon(StartBtnOnImage);
				startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startBtn.setIcon(StartBtnImage);
				startBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonClicked = new Music("BtnClicked.mp3",false);
				buttonClicked.start();
				backgroundMusic.close();
				mainSetting();
			}});
				add(startBtn);
		
		//Quit Button Setting
		quitBtn.setBounds(40, 330, 400, 100);
		quitBtn.setBorderPainted(false);
		quitBtn.setContentAreaFilled(false);
		quitBtn.setFocusPainted(false);
		quitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitBtn.setIcon(QuitBtnOnImage);
				quitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitBtn.setIcon(QuitBtnImage);
				quitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				try {
					Thread.sleep(500);
				}catch(Exception e2) {
					System.out.println(e2.getMessage());
				}
				System.exit(0);
			}});
				add(quitBtn);
				
		//rightArrow Button Setting
		rightArrowBtn.setBounds(1080, 310, 60, 60);
		rightArrowBtn.setBorderPainted(false);
		rightArrowBtn.setContentAreaFilled(false);
		rightArrowBtn.setFocusPainted(false);
		rightArrowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightArrowBtn.setIcon(rightBtnOnImage);
				rightArrowBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightArrowBtn.setIcon(rightBtnImage);
				rightArrowBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				clickRight();
			}});
				add(rightArrowBtn);
		
		//leftArrow Button Setting
		leftArrowBtn.setBounds(140, 310, 60, 60);
		leftArrowBtn.setBorderPainted(false);
		leftArrowBtn.setContentAreaFilled(false);
		leftArrowBtn.setFocusPainted(false);
		leftArrowBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftArrowBtn.setIcon(leftBtnOnImage);
				leftArrowBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftArrowBtn.setIcon(leftBtnImage);
				leftArrowBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				clickLeft();
			}});
				add(leftArrowBtn);
		
		//Hard Button Setting
		hardBtn.setBounds(655, 580, 250, 67);
		hardBtn.setBorderPainted(false);
		hardBtn.setContentAreaFilled(false);
		hardBtn.setFocusPainted(false);
		hardBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardBtn.setIcon(hardBtnOnImage);
				hardBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardBtn.setIcon(hardBtnImage);
				hardBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				gameStart(nowSelected, "Hard");
			}});
				add(hardBtn);
						
		//Easy Button Setting
		easyBtn.setBounds(375, 580, 250, 67);
		easyBtn.setBorderPainted(false);
		easyBtn.setContentAreaFilled(false);
		easyBtn.setFocusPainted(false);
		easyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyBtn.setIcon(easyBtnOnImage);
				easyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyBtn.setIcon(easyBtnImage);
				easyBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				gameStart(nowSelected, "Easy");
			}});
				add(easyBtn);
				
		//Back Button Setting
		backBtn.setVisible(false);
		backBtn.setBounds(20, 50, 60, 60);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backBtn.setIcon(backBtnOnImage);
				backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonOnExit = new Music("BtnOn.mp3",false);
				buttonOnExit.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backBtn.setIcon(backBtnImage);
				backBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonExit = new Music("BtnClicked.mp3",false);
				buttonExit.start();
				backMain();
			}});
				add(backBtn);
				
		//menu bar setting
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);

		
	}

	public void paint(Graphics g) {
		backgroundImage = createImage(Main.FRAME_WIDTH, Main.FRAME_HEIGHT);
		BackgroundGraphic = backgroundImage.getGraphics();
		backgroundSetting((Graphics2D)BackgroundGraphic);
		g.drawImage(backgroundImage, 0, 0, null);
	}

	public void backgroundSetting(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		if(isMainFrame) {
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 400, null);
		}
		if(isGameFrame) {
			game.frameDraw(g);
		}
		if(isResultFrame) {
			game.resultFrame(g);
		}
		paintComponents(g);
		try {
			Thread.sleep(5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		this.repaint();
	}
	
	
	public void selectedTrack(int nowSelected) {
		if(selectedMusic != null) {
			selectedMusic.close();
		}
		titleImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/"+trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(),true);
		selectedMusic.start();
		
	}
	public void clickLeft() {
		if(nowSelected == 0) {
			nowSelected = trackList.size() - 1;}
		else
			nowSelected--;
		selectedTrack(nowSelected);
		}
	
	public void clickRight() {
		if(nowSelected == trackList.size() - 1) {
			nowSelected = 0;}
		else
			nowSelected++;
		selectedTrack(nowSelected);
		}
	
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
			selectedMusic.close();
		isMainFrame = false;
		leftArrowBtn.setVisible(false);
		rightArrowBtn.setVisible(false);
		easyBtn.setVisible(false);
		hardBtn.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getPlayImage()))
				.getImage();
		backBtn.setVisible(true);
		isGameFrame = true;
		game = new Game(trackList.get(nowSelected).gettitleName(),difficulty, 
				trackList.get(nowSelected).getGameMusic(),trackList.get(nowSelected).getPlaytime());
		game.start();
		setFocusable(true);
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				isResultFrame = true;
			}
		};
		timer.schedule(task, trackList.get(nowSelected).getPlaytime());
	}
	
	public void gameFinished(Game game) {
		score = game.score.getScore();
	}
	
	public void backMain() {
		isMainFrame = true;
		leftArrowBtn.setVisible(true);
		rightArrowBtn.setVisible(true);
		easyBtn.setVisible(true);
		hardBtn.setVisible(true);
		background = new ImageIcon(Main.class.getResource("../images/playbackground1.png"))
				.getImage();
		backBtn.setVisible(false);
		selectedTrack(nowSelected);
		isGameFrame = false;
		isResultFrame = false;
		game.close();   //뒤로 갔을 때 게임 닫아지도록 설정
	}
	
	public void mainSetting() {
		
		startBtn.setVisible(false);
		quitBtn.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/playbackground1.png")).getImage();
		isMainFrame = true;
		leftArrowBtn.setVisible(true);
		rightArrowBtn.setVisible(true);
		easyBtn.setVisible(true);
		hardBtn.setVisible(true);
		backgroundMusic.close();
	    selectedTrack(0);
	}
	}
	