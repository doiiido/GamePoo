package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

//Essa classe é responsável pela configuração dos tiros

public abstract class Bullet extends Sprite implements IEntityModifierListener{
	protected int damage;
	protected int speed;
	protected boolean enemyBullet;
	protected boolean movementFinished;

	public Bullet(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, ResourceManager.bulletTextureRegion, pVertexBufferObjectManager);
		this.movementFinished = false;
		//Verifica se atingiu inimigos a cada ciclo do game.
		this.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
					@Override
					public void run() {
						if(checkEnemyHit()){
							if(!isEnemyBullet()){
								OnEnemyHit(); //Sobrescrevendo esse método poderemos adicionar o comportamento da bala quando ela atingir um inimigo.
												//(Explodir, aumentar score do player, etc).
							}
							else{
								GameManager.getInstance().getPlayer().decrementLife(damage);
							}
						}
						else if(movementFinished){
							removeBullet();
						}
					}
				});
			}
		});
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
	
	public abstract void removeBullet();
	
	public abstract void setMovement(float pX, float pY, boolean isEnemyBullet);
	
	public abstract boolean checkEnemyHit();
	
	public abstract void OnEnemyHit();

}
