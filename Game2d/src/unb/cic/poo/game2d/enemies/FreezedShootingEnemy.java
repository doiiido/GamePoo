package unb.cic.poo.game2d.enemies;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.*;
import unb.cic.poo.game2d.bullets.BulletType;
import unb.cic.poo.game2d.bullets.CommonBulletType;
import unb.cic.poo.game2d.scenes.SceneManager;




public class FreezedShootingEnemy extends Enemy{
	//private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	
	private static final int DEFAULT_COMMON_ENEMY_SPEED = 1000; /* Velocidade vertical alterada. */
	
	private static final int COMMON_ENEMY_LIFE = 1;
	private BulletType bulletType;
	private float posicaoFinalX;
	private float posicaoInicialX;
	private ShootHandler shootHandler;
	
	//E necessario passar a posicao X final
	public FreezedShootingEnemy(float pX, float pY, float pFinalX) {
		super(pX, pY, ResourceManager.shooterTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.life = COMMON_ENEMY_LIFE;
		this.speed = DEFAULT_COMMON_ENEMY_SPEED;
		this.posicaoFinalX = pFinalX;
		this.posicaoInicialX = pX;
		this.bulletType = new CommonBulletType();
		this.setMovement();
		this.shootHandler = new ShootHandler(this);
		this.registerUpdateHandler(this.shootHandler);
	}
	
	/* O inimigo se movimenta ate a posicao final em X definida no construtor.  */
	private void setMovement() {
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -(this.posicaoInicialX-this.posicaoFinalX), 0);
		moveByModifier.addModifierListener(this);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
		
	}


	public void shoot(){
		this.bulletType.setBullet(this.getX(), this.getY()+(this.getHeight()/2), true);
	}
	
	public ShootHandler getShootHandler() {
		return shootHandler;
	}

	public void setShootHandler(ShootHandler shootHandler) {
		this.shootHandler = shootHandler;
	}

	@Override
	public void removeEnemy(){
		GameManager.getInstance().getEnemies().remove(this);
		this.unregisterUpdateHandler(this);
		SceneManager.gameScene.detachChild(this);
	}


	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		//this.movementFinished = false;
		
	}

	@Override
	public void handleTouchEvent(TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		
	}

}
