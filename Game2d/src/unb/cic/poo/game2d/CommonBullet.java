package unb.cic.poo.game2d;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.util.modifier.IModifier;

//Tiro Básico

public class CommonBullet extends Bullet{
	public static final int BULLET_HEIGHT = GameActivity.CAMERA_HEIGHT/360; // 2
	public static final int BULLET_WIDTH = GameActivity.CAMERA_WIDTH/320; // 4
	public static final int BULLET_SPEED = 1000;
	public static final int BULLET_DAMAGE = 1;

	
	//nesse construtor, adicionamos uma pequena correcao na posicao inicial da bala(para ela ser criada no meio do atirador).
	public CommonBullet(float pX, float pY, boolean isEnemyBullet) {
		
		super(pX, pY-BULLET_HEIGHT, ResourceManager.bulletTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		
		this.speed = BULLET_SPEED;
		this.damage = BULLET_DAMAGE;
		this.enemyBullet = isEnemyBullet;
		
		this.setMovement(pX, pY, isEnemyBullet);
	}
	
	public void setMovement(float pX, float pY, boolean isEnemyBullet){
		float distance;
		if(isEnemyBullet == true)
			distance = - (BULLET_WIDTH + pX);
		else
			distance = GameManager.getInstance().getGameCamera().getWidth() - pX;
		
		float timeDuration = Math.abs(distance/BULLET_SPEED);
		
		MoveByModifier moveByModifier = new MoveByModifier(timeDuration, distance, 0);
		moveByModifier.addModifierListener(this);
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
	}
	
	public boolean checkEnemyHit(){
		
		for(Enemy enemy : GameManager.getInstance().getEnemies()){
			if(this.collidesWith(enemy)){
				enemy.decrementLife(this.damage);
				return true;
			}
		}
		return false;
	}

	@Override
	public void OnEnemyHit() {
		GameManager.getInstance().getGameScene().detachChild(this);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}

	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		movementFinished = true;
	}

	@Override
	public void removeBullet() {
		GameManager.getInstance().getGameScene().detachChild(this);
	}
}
