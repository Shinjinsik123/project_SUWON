package game1;

//트랙에 대한 모든 정보에 대한 관리
public class Track {
	private String titleImage; ///제목부분 이미지
	private String startImage; //게임선택창 표지 이미지
	private String playImage; //해당 곡을 실행했을때 표지 이미지
	private String startMusic; //게임 선택창 음악
	private String gameMusic; //해당 곡을 실행했을때 음악
	private String titleName; ///곡제목
	private int playtime; // 음악 재생 시간

	public Track(String titleImage, String startImage, String playImage, String startMusic, String gameMusic,
			String titleName, int playtime) {
		super();
		this.titleImage = titleImage;
		this.startImage = startImage;
		this.playImage = playImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.titleName = titleName;
		this.playtime = playtime;
	}

	
	// getter, setter
	public int getPlaytime() {
		return playtime;
	}

	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}
	
	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public String getStartImage() {
		return startImage;
	}

	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}

	public String getPlayImage() {
		return playImage;
	}

	public void setPlayImage(String playImage) {
		this.playImage = playImage;
	}

	public String getStartMusic() {
		return startMusic;
	}

	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}

	public String getGameMusic() {
		return gameMusic;
	}

	public void setGameMusic(String musicTitle) {
		this.gameMusic = gameMusic;
	}

	public String gettitleName() {
		return titleName;
	}

	public void settitleName(String titleName) {
		this.titleName = titleName;
	}

}
