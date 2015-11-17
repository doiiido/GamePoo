package unb.cic.poo.game2d.bullets;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

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

	public Bullet(float pX, float pY, ITiledTextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		this.movementFinished = false;
		ResourceManager.mBullet.play();
		
		this.updateHandler = new IUpdateHandler() {
			@Override
			public void reset() {
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
					@Override
					public void run() {
						if(checkEnemyHit()){
							if(!alreadyHit){
								alreadyHit = true;
								if(!isEnemyBullet()){
									OnEnemyHit(); //Sobrescrevendo esse método poderemos adicionar o comportamento da bala quando ela atingir um inimigo.
													//(Explodir, aumentar score do player, etc).
								}
								else{
									unregisterUpdateHandler(updateHandler);
									GameManager.getInstance().getGameScene().unregisterUpdateHandler(updateHandler);
									GameManager.getInstance().getPlayer().decrementLife(damage);
									removeBullet();
								}
							}
							
						}
						else if(movementFinished){
							removeBullet();
						}
						
					}
				});
			}
		};
		//Verifica se atingiu inimigos a cada ciclo do game.
		this.registerUpdateHandler(updateHandler);
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
