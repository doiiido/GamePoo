package unb.cic.poo.game2d;

import java.util.ArrayList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import unb.cic.poo.game2d.scenes.GameScene;
import unb.cic.poo.game2d.scenes.SceneManager;

//Superclasse dos inimigos.

public abstract class Enemy extends SpaceshipAnimated implements IEntityModifierListener{
	protected boolean movementFinished;
	
	public static final int INFINITY = 2000000;
	
	public Enemy(float pX, float pY, ITiledTextureRegion texture,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, texture, pVertexBufferObjectManager);
		movementFinished = false;
		
		this.registerUpdateHandler(new EnemyHandler(this));
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
	
	/*protected void onManagedUpdate(float pSecondsElapsed) {
		if(GameManager.getInstance().getEnemies().contains(this) && this.collidesWith(GameManager.getInstance().getPlayer())){
			BaseScene aux = SceneManager.gameScene;
			((GameScene) aux).gameOver(false);
			GameManager.getInstance().getPlayer().decrementLife(INFINITY);
			// Erro aqui! Aparentemente por estar adicionando e retirando em threads diferentes.
			// Uma op��o � colocar para decrementar a vida ao inv�s de dar gameOver direto, pois est�
			// funcionando por meio do Player
		}
		super.onManagedUpdate(pSecondsElapsed);
	}*/

	@Override
	public abstract void shoot();
	
	public abstract void removeEnemy();
}