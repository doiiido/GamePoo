package unb.cic.poo.game2d;

import java.util.Timer;
import java.util.TimerTask;

import org.andengine.engine.handler.IUpdateHandler;

//Classe que vai armazenar as propriedades do player: Vida, Pontuacao...

//Atualmente ela herda da classe Rectangle, mas assim que tivermos os 
//sprites podemos mudar. 


import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.input.touch.TouchEvent;

import unb.cic.poo.game2d.bullets.Bullet;
import unb.cic.poo.game2d.bullets.BulletType;
import unb.cic.poo.game2d.bullets.CommonBulletType;
import unb.cic.poo.game2d.bullets.FlamethrowerBulletType;
import unb.cic.poo.game2d.bullets.LaserBulletType;
import unb.cic.poo.game2d.bullets.MachineGunBulletType;
import unb.cic.poo.game2d.scenes.BaseScene;
import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.scenes.SceneManager;

public class Player extends SpaceshipAnimated{
	
	// ===========================================================
	// Constants
	// ===========================================================
	public final static int PLAYER_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	public final static int PLAYER_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	public final static int DEFAULT_PLAYER_SPEED = 1500;
	public final static int DEFAULT_PLAYER_LIFE = 10;
	public static final int DX = 10; // deslocamento da nave em relacao ao toque
	public static final int DY = -30; // ver valores melhores
	
	// ===========================================================
	// Fields
	// ===========================================================
	public float lifewidth = GameScene.getLifeBar().getWidth();
	public final float lifescale = (lifewidth/DEFAULT_PLAYER_LIFE);
	
	private int score;
	private MoveByModifier lastMoveByModifier; // Armazena o ultimo modificador de movimento utilizado na classe.
	private float targetX; // targetX e targetY armazenam as coordenadas para as quais a nave esta se movendo.
	private float targetY;
	private BulletType bulletType;
	private BulletType common; 
	private BulletType secondaryBulletType;
	private int bullet = 0;
	private boolean screenBeingPressed = false;
	private IUpdateHandler pressedShootHandler;
	private PlayerStatistics statistics;
	private boolean isDead;
	
	// ===========================================================
	// Constructors
	// ===========================================================
	//Metodo construtor, por enquanto esta apenas chamando o construtor da superclasse e configurando a variavel de velocidade.
	// Para instanciar a sprite no construtor basta colocar ResourceManager.playerTextureRegion ou o nome da sprite desejada
	public Player(){
		super(0f, (float) (GameActivity.CAMERA_HEIGHT/2) - (PLAYER_HEIGHT/2), ResourceManager.playerTextureRegion 
				,GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.speed = DEFAULT_PLAYER_SPEED;
		this.life = DEFAULT_PLAYER_LIFE;
		this.statistics = new PlayerStatistics();
		this.common = new CommonBulletType();
		this.secondaryBulletType = new LaserBulletType(this);
		this.bulletType = this.common;
		
		pressedShootHandler = new IUpdateHandler() {
			
			@Override
			public void reset() {
				
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				if(bulletType.isAutomatic()){
					shoot();
				}
				if(!screenBeingPressed){
					screenBeingPressed = true;
				}
			}
		};

	}
	
	//Método para atirar
	
	public void shoot() {
		if(!this.bulletType.isOnCooldown() && GameScene.getGameStop() == false && bulletType.getAmmoQuantity() > 0){
			statistics.incrementAmountShoot();
			this.bulletType.setBullet(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2), false);
		}
	}
	
	public void reInit(){
		this.setX(0);
		this.setY(GameActivity.CAMERA_HEIGHT/2);
		this.statistics = new PlayerStatistics();
		this.secondaryBulletType = new CommonBulletType();
		this.bulletType = common;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================
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

	public BulletType getSecondaryBulletType() {
		return secondaryBulletType;
	}

	public void setSecondaryBulletType(BulletType secondaryBulletType) {
		((GameScene)GameManager.getInstance().getGameScene()).setSwitcher(secondaryBulletType.getSwt());
		this.secondaryBulletType = secondaryBulletType;
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
	
	public boolean isScreenBeingPressed() {
		return screenBeingPressed;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	/* (non-Javadoc)
	 * @see unb.cic.poo.game2d.SpaceshipAnimated#handleTouchEvent(org.andengine.input.touch.TouchEvent)
	 *
	 *Calcula o tempo de duracao de cada movimento com base na velocidade configurada no Player.
	 *Foi feita uma simplificacao no calculo da distancia para evitar o uso de raiz quadrada.
	 *Alem disso, para evitar calculos quando o valor float eh muito pequeno, foi utilizado uma duracao 
	 *fixa para quando as distancias sao muito curtas.
	 * 
	 */
	@Override
	public void handleTouchEvent(TouchEvent pSceneTouchEvent) {
		if(pSceneTouchEvent.getX() <= (GameManager.getInstance().getGameCamera().getWidth()/2) - Player.DX){
		
			if(this.getLastMoveByModifier() != null){
				this.unregisterEntityModifier(this.getLastMoveByModifier());
			}
			
			float durationTime;
			float deltaX = pSceneTouchEvent.getX()-this.getX() + Player.DX;
			float deltaY = pSceneTouchEvent.getY()-this.getY() + Player.DY;
			float absDistance = Math.abs(deltaX) + Math.abs(deltaY);
			
			if(absDistance <= 0.5){
				durationTime = 0.0001f;
			}
			else{
				durationTime = (absDistance)/this.getSpeed();
			}
			
			MoveByModifier movePlayer = new MoveByModifier(durationTime, deltaX, deltaY);
			this.setLastMoveByModifier(movePlayer);
			this.registerEntityModifier(movePlayer);
			
		}
		else if(pSceneTouchEvent.isActionDown()){
			this.unregisterUpdateHandler(pressedShootHandler);
			shoot();
			this.registerUpdateHandler(pressedShootHandler);
		} 
		else if(pSceneTouchEvent.isActionUp() || pSceneTouchEvent.isActionCancel() || pSceneTouchEvent.isActionOutside()){
			this.unregisterUpdateHandler(pressedShootHandler);
			screenBeingPressed = false;
		}
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	public void decrementLife(int decrement) {
		super.decrementLife(decrement);
		statistics.incrementDamageTaken(decrement);
		
		this.lifewidth -= this.lifescale;
		GameScene.setLifeBar(lifewidth);
		
		if(this.life <= 0){
			this.isDead = true;
			BaseScene aux = SceneManager.gameScene;
			((GameScene) aux).gameOver(false);
		}
		
	}
	
	public void incrementLife(int increment) {
		super.incrementLife(increment);
		statistics.incrementDamageTaken(increment);
		
		this.lifewidth += this.lifescale;
		GameScene.setLifeBar(lifewidth);
		
		if(this.life > 10){
			this.life = 10;
		}
		
	}
	
	public int changeBullet(){
		if(bullet == 0) {
			this.bulletType = this.secondaryBulletType;
			bullet = 1;
		} else {
			this.bulletType = this.common;
			bullet = 0;
		}
		return bullet;
	}

	public PlayerStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(PlayerStatistics statistics) {
		this.statistics = statistics;
	}

	public boolean isDead() {
		// TODO Auto-generated method stub
		return isDead;
	}

	public void setIsDead(boolean b) {
		this.isDead = b;
		
	}
}