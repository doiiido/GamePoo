package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.util.modifier.IModifier;



/* Ainda existe o problema que as naves continuam a atirar depois de removidas. */



public class FreezedShootingEnemy extends Enemy{
	//private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	
	private static final int DEFAULT_COMMON_ENEMY_SPEED = 1000; /* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType;
	private float posicaoFinalX;
	private float posicaoInicialX;
	private float timer = 2f;
	private IUpdateHandler shootHandler;
	
	//É necessário passar a posição X final
	public FreezedShootingEnemy(float pX, float pY, float pFinalX) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_ENEMY_SPEED;
		this.posicaoFinalX = pFinalX;
		this.posicaoInicialX = pX;
		this.bulletType = new CommonBulletType();
		this.setMovement();
		
		this.shootHandler = new IUpdateHandler(){
			
			/* O inimigo irá atirar de 1 em 1 segundo. */
			public void onUpdate(float pSecondsElapsed){
				timer -= pSecondsElapsed;
				if(timer <= 0){
					shoot();
					timer = 1f;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
		};
		this.registerUpdateHandler(this.shootHandler);
	}
	
	/* O inimigo se movimenta até a posição final em X definida no construtor.  */
	private void setMovement() {
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -(this.posicaoInicialX-this.posicaoFinalX), 0);
		moveByModifier.addModifierListener(this);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
		
	}


	public void shoot(){
		Bullet bullet = this.bulletType.getBullet(this.getX(), this.getY()+(this.getHeight()/2), true);
		GameManager.getInstance().getGameScene().attachChild(bullet);
	}
	
	public void removeEnemy(){
		this.unregisterUpdateHandler(shootHandler);
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);
	}


	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		//this.movementFinished = false;
		
	}

}
