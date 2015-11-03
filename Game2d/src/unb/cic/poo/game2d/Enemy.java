package unb.cic.poo.game2d;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.scenes.SceneManager;

//Superclasse dos inimigos.

public abstract class Enemy extends SpaceshipAnimated implements IEntityModifierListener{
	protected boolean movementFinished;
	
	public Enemy(float pX, float pY, ITiledTextureRegion texture,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, texture, pVertexBufferObjectManager);
		movementFinished = false;
		
		this.registerUpdateHandler(new IUpdateHandler() {
			
			@Override
			public void reset() {
			}
			
			@Override
			public void onUpdate(float pSecondsElapsed) {
				GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {			
					@Override
					public void run() {
						if(movementFinished){
							removeEnemy();
						}
					}
				});
			}
		});
	}
	
	public void decrementLife(int decrement){
		super.decrementLife(decrement);
		if(this.life <= 0){
			this.kill();
			ResourceManager.mXplosion.play();
		}
	}
	
	public void kill(){
		GameScene.createExplosion(this.getX(), this.getY(), this.getParent());
		this.removeEnemy();
		this.clearEntityModifiers();
	}

	@Override
	public abstract void shoot();
	
	public abstract void removeEnemy();
}