package game1;

//Ʈ���� ���� ��� ������ ���� ����
public class Track {
	private String titleImage; ///����κ� �̹���
	private String startImage; //���Ӽ���â ǥ�� �̹���
	private String playImage; //�ش� ���� ���������� ǥ�� �̹���
	private String startMusic; //���� ����â ����
	private String gameMusic; //�ش� ���� ���������� ����
	private String titleName; ///������
	private int playtime; // ���� ��� �ð�

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
