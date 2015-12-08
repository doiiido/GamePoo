package unb.cic.poo.game2d.enemies;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.bullets.BulletType;
import unb.cic.poo.game2d.bullets.CommonBulletType;
import unb.cic.poo.game2d.items.ItemGen;
import unb.cic.poo.game2d.*;


/* Ainda existe o problema que as naves continuam a atirar depois de removidas. */

// ===========================================================
// Constructors
// ===========================================================
public class ChasingYEnemy extends Enemy{
	//private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	//private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	
	private static final int DEFAULT_COMMON_VENEMY_SPEED = 500;/* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType = new CommonBulletType();
	private float timer;
	private float xFinal;
	private float xInicial;
	private float mSpeed;
	private IUpdateHandler shootHandler;
	private MoveByModifier moveX;
	private MoveByModifier lastMoveByModifier;
	private ParallelEntityModifier parallelEntityModifier;
	
	/**
	 * @param pX posicao x inicial
	 * @param pY posicao y inicial
	 * @param posXfinal posicao final 
	 * @param speedY velocidade na dire��o y para seguir o player
	 * 
	 * @ O inimigo usa um MoveByModifier para caminhar para a posicao final -(pX-pXFinal)
	 * 
	 */
	public ChasingYEnemy(float pX, float pY, float pXFinal, float speedY) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.xInicial = pX;
		this.xFinal = pXFinal;	
		this.mSpeed = speedY;
		
		this.setMovement();
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 0.5f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(shootHandler);
		
	}
	
	
	public ChasingYEnemy(float pX, float pY, float pXFinal, float speedY, boolean dropsIten){
		
		super(pX, pY, ResourceManager.laserTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager(), dropsIten);
		
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.xInicial = pX;
		this.xFinal = pXFinal;	
		this.mSpeed = speedY;
		
		this.setMovement();
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 0.5f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(shootHandler);
	}

	
	
	
	
	
	public ChasingYEnemy(float pX, float pY, float pXFinal, float speedY, ItemGen itenDropped){
		super(pX, pY, ResourceManager.laserTextureRegion, 
			GameManager.getInstance().getGameEngine().getVertexBufferObjectManager(), itenDropped);
		
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.xInicial = pX;
		this.xFinal = pXFinal;	
		this.mSpeed = speedY;
		
		this.setMovement();
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo atira de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 0.5f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(shootHandler);
	}
	
	
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public MoveByModifier getLastMoveByModifier() {
		return lastMoveByModifier;
	}

	public void setLastMoveByModifier(MoveByModifier moveByModifier) {
		this.lastMoveByModifier = moveByModifier;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
	}

	@Override
	public void shoot() {
		this.bulletType.setBullet(this.getX(), this.getY()+(this.getHeight()/2), true);
	}

	@Override
	public void removeEnemy() {
		this.unregisterUpdateHandler(shootHandler);
		this.unregisterEntityModifier(moveX);
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);		
	}

	/* (non-Javadoc)
	 * @see unb.cic.poo.game2d.SpaceshipAnimated#handleTouchEvent(org.andengine.input.touch.TouchEvent)
	 * 
	 * Muda a posicao y do inimigo ao mesmo tempo que entra na cena
	 * baseado no toque da tela
	 */
	@Override
	public void handleTouchEvent(TouchEvent pSceneTouchEvent) {
		if(pSceneTouchEvent.getX() <= (GameManager.getInstance().getGameCamera().getWidth()/2)){
			this.unregisterEntityModifier(getLastMoveByModifier());
	
			float deltaY = pSceneTouchEvent.getY()-this.getY();
			float duration;
			float absDistance = Math.abs(deltaY);
			
			if(absDistance <= 0.5){
				duration = 0.0001f;
			}
			else{
				duration = (absDistance)/mSpeed;
			}
	
			MoveByModifier chaseY = new MoveByModifier(duration, 0, deltaY);
			
			if(moveX.isFinished()){
				this.setLastMoveByModifier(chaseY);
				this.registerEntityModifier(chaseY);
			}
			else{
				this.unregisterEntityModifier(parallelEntityModifier);
				parallelEntityModifier = new ParallelEntityModifier(moveX, chaseY);
				this.registerEntityModifier(parallelEntityModifier);
			}
		}
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	/* Metodo que posiciona a nave inimiga na posicao X final. */
	public void setMovement(){
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		moveX = new MoveByModifier(durationTime, -(this.xInicial-this.xFinal), 0);
		
		this.registerEntityModifier(moveX);
	}
}
