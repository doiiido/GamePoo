package unb.cic.poo.game2d;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

//Superclasse dos inimigos.

public abstract class Enemy extends Spaceship{
	
	public Enemy(float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
	}
	
	public void decrementLife(int decrement){
		super.decrementLife(decrement);
		if(this.life <= 0){
			this.kill();
		}
	}
	
	public void kill(){
		GameManager.getInstance().getGameScene().detachChild(this);
		this.clearEntityModifiers();
	}

	@Override
	public abstract void shoot();
}