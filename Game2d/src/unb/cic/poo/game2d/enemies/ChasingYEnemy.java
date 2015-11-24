package unb.cic.poo.game2d.enemies;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.modifier.IModifier;

import android.util.Log;
import unb.cic.poo.game2d.bullets.BulletType;
import unb.cic.poo.game2d.bullets.CommonBulletType;
import unb.cic.poo.game2d.*;


/* Ainda existe o problema que as naves continuam a atirar depois de removidas. */



public class ChasingYEnemy extends Enemy{
	private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	//private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	
	private static final int DEFAULT_COMMON_VENEMY_SPEED = 500;/* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType = new CommonBulletType();
	private float timer;
	private float posXfinal;
	private float yInicial;
	private IUpdateHandler shootHandler;
	private MoveByModifier moveX;
	private MoveByModifier lastMoveByModifier;
	
	public ChasingYEnemy(float pX, float pY, float posXfinal) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_VENEMY_SPEED;
		this.yInicial = pY;
		this.posXfinal = posXfinal;		
		
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
	

	/* Método que posiciona a nave inimiga na posição X final. */
	public void setMovement(){
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		moveX = new MoveByModifier(durationTime, -280, 0);
		
		this.registerEntityModifier(moveX);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {

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

	public void handleTouchEvent(TouchEvent pSceneTouchEvent) {
		if(moveX.isFinished()){
			this.unregisterEntityModifier(getLastMoveByModifier());
			
			float deltaY = pSceneTouchEvent.getY()-this.getY();
			float duration = deltaY/ChasingYEnemy.DEFAULT_COMMON_VENEMY_SPEED;
			
			MoveByModifier chaseY = new MoveByModifier(duration, 0, deltaY);
			
			this.setLastMoveByModifier(chaseY);
			this.registerEntityModifier(chaseY);
		}
	}

	public MoveByModifier getLastMoveByModifier() {
		return lastMoveByModifier;
	}

	public void setLastMoveByModifier(MoveByModifier lastMoveByModifier) {
		this.lastMoveByModifier = lastMoveByModifier;
	}
}
