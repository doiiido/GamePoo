package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.util.modifier.IModifier;

public class FreezedShootingEnemy extends Enemy{
	//private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	private static final int DEFAULT_COMMON_ENEMY_SPEED = 150;
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType;
	private int posicaoFinalX;
	private float timer = 3f;
	
	//É necessário passar a posição X final
	public FreezedShootingEnemy(float pX, float pY, int pFinalX) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_ENEMY_SPEED;
		this.posicaoFinalX = pFinalX;
		this.setMovement();
		this.registerUpdateHandler(new IUpdateHandler(){
			
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 3f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private void setMovement() {
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, this.posicaoFinalX-COMMON_ENEMY_WIDTH, 0);
		moveByModifier.addModifierListener(this);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
		
	}


	public void shoot(){
		Bullet bullet = this.bulletType.getBullet(this.getX()+this.getWidth(), this.getY()+(this.getHeight()/2), false);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}
	
	public void removeEnemy(){
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);
	}


	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		this.movementFinished = true;
		
	}

}
