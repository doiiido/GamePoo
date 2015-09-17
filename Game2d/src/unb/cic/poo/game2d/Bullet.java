package unb.cic.poo.game2d;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

//Essa classe é responsável pela configuração dos tiros

public abstract class Bullet extends Rectangle{
	protected int damage;
	protected int speed;
	protected boolean enemyBullet;

	public Bullet(float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
	}
	
	//Getters e Setters
	
	public int getDano() {
		return damage;
	}

	public void setDano(int dano) {
		this.damage = dano;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public boolean isEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(boolean enemyBullet) {
		this.enemyBullet = enemyBullet;
	}
	
	public abstract void setMovement(float pX, float pY, boolean isEnemyBullet);

}
