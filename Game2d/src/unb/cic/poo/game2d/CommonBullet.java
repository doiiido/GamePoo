package unb.cic.poo.game2d;

import org.andengine.entity.modifier.MoveByModifier;

//Tiro Básico

public class CommonBullet extends Bullet {
	public static final int BULLET_HEIGHT = 2;
	public static final int BULLET_WIDTH = 4;
	public static final int BULLET_SPEED = 1800;
	public static final int BULLET_DAMAGE = 1;

	
	//nesse construtor, adicionamos uma pequena correcao na posicao inicial da bala(para ela ser criada no meio do atirador).
	public CommonBullet(float pX, float pY, boolean isEnemyBullet) {
		
		super(pX, pY-BULLET_HEIGHT, BULLET_WIDTH, BULLET_HEIGHT, 
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
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
	}
	
	public Enemy enemyHit(){
		for(Enemy enemy : GameManager.getInstance().getEnemies()){
			if(this.collidesWith(enemy)){
				GameManager.getInstance().getGameScene().detachChild(this);
				enemy.decrementLife(this.damage);
				return enemy;
			}
		}
		
		return null;
	}
	
	//Se a bala colidiu com algum inimigo, precisamos decrementar a sua vida.
	protected void onManagedUpdate(float pSecondsElapsed){
		Enemy collideEnemy = null;
		if(!enemyBullet){
			collideEnemy = enemyHit();
		}
		if(collideEnemy != null){
			GameManager.getInstance().getEnemies().remove(collideEnemy);
		}
		
		super.onManagedUpdate(pSecondsElapsed);
	}
}
