package unb.cic.poo.game2d;

//Classe que vai armazenar as propriedades do player: Vida, Pontuação...

//Atualmente ela herda da classe Rectangle, mas assim que tivermos os 
//sprites podemos mudar. 


import org.andengine.entity.modifier.MoveByModifier;

public class Player extends SpaceshipAnimated{
	public final static int PLAYER_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	public final static int PLAYER_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	public final static int DEFAULT_PLAYER_SPEED = 1500;
	public final static int DEFAULT_PLAYER_LIFE = 4;
	
	private int score;
	private MoveByModifier lastMoveByModifier; // Armazena o ultimo modificador de movimento utilizado na classe.
	private float targetX; // targetX e targetY armazenam as coordenadas para as quais a nave esta se movendo.
	private float targetY;
	private BulletType bulletType;
	
	//Método construtor, por enquanto esta apenas chamando o construtor da superclasse e configurando a variável de velocidade.
	// Para instanciar a sprite no construtor basta colocar ResourceManager.playerTextureRegion ou o nome da sprite desejada
	public Player(){
		super(0f, (float) (GameActivity.CAMERA_HEIGHT/2) - (PLAYER_HEIGHT/2), ResourceManager.playerTextureRegion 
				,GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.speed = DEFAULT_PLAYER_SPEED;
		this.bulletType = new CommonBulletType();
		this.life = DEFAULT_PLAYER_LIFE;
	}
	
	//Método para atirar
	
	@Override
	public void shoot() {
		Bullet bullet = this.bulletType.getBullet(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2), false);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}

	// getters e setters.
	
	public BulletType getBulletType() {
		return bulletType;
	}

	public void setBulletType(BulletType bulletType) {
		this.bulletType = bulletType;
	}

	public int getScore() {
		return score;
	}

	public void incrementScore(int scoreIncrement) {
		this.score += scoreIncrement;
	}

	public float getTargetY() {
		return targetY;
	}

	public void setTargetY(float targetY) {
		this.targetY = targetY;
	}

	public float getTargetX() {
		return targetX;
	}

	public void setTargetX(float targetX) {
		this.targetX = targetX;
	}

	public MoveByModifier getLastMoveByModifier() {
		return lastMoveByModifier;
	}

	public void setLastMoveByModifier(MoveByModifier lastMoveByModifier) {
		this.lastMoveByModifier = lastMoveByModifier;
	}
	
	public void decrementLife(int decrement) {
		super.decrementLife(decrement);
		
		if(this.life <= 0){
			GameManager.getInstance().gameOver();
		}
		
	}
}