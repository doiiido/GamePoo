package unb.cic.poo.game2d.bullets;

import java.util.ArrayList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import unb.cic.poo.game2d.enemies.*;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

//Essa classe é responsável pela configuração dos tiros

public abstract class Bullet extends AnimatedSprite implements IEntityModifierListener{
	protected int damage;
	protected int speed;
	protected boolean enemyBullet;
	protected boolean movementFinished;
	protected boolean alreadyHit = false;
	protected IUpdateHandler updateHandler;
	protected ResourceManager rs;
	protected ArrayList<Enemy> enemiesHit = new ArrayList<Enemy>();

	public Bullet(float pX, float pY, ITiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.movementFinished = false;
		ResourceManager.mBullet.play();
		
		this.updateHandler = new BulletHandler(this);
		//Verifica se atingiu inimigos a cada ciclo do game.
		this.registerUpdateHandler(updateHandler);
	}
	
	
	public boolean checkHit(){
		
		if(isEnemyBullet()){
			if(this.collidesWith(GameManager.getInstance().getPlayer())){
				return true;
			}
			return false;
		}
		for(Enemy enemy : GameManager.getInstance().getEnemies()){
			if(this.collidesWith(enemy)){
				enemiesHit.add(enemy);
			}
		}
		return !enemiesHit.isEmpty();
	}
	
	//Getters e Setters
	
	public ArrayList<Enemy> getEnemiesHit() {
		return enemiesHit;
	}


	public void setEnemiesHit(ArrayList<Enemy> enemiesHit) {
		this.enemiesHit = enemiesHit;
	}


	public int getDamage() {
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
	
	public abstract void removeBullet();
	
	public abstract void setMovement(float pX, float pY, boolean isEnemyBullet);
	
	public void OnEnemyHit(Enemy enemy){
		enemy.decrementLife(this.getDamage());
	}


	public void onPlayerHit() {
		GameManager.getInstance().getPlayer().decrementLife(damage);
		removeBullet();
		this.unregisterUpdateHandler(updateHandler);
	}

}
