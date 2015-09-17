package unb.cic.poo.game2d;

//Essa clase servirá como base para o player e para os inimigos.

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class Spaceship extends Rectangle{
	protected int life;
	protected int speed;
	
	public Spaceship(float pX, float pY, float pWidth, float pHeight,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pWidth, pHeight, pVertexBufferObjectManager);
	}
	
	//Getters e Setters
	
	public int getLife() {
		return life;
	}


	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


	public void decrementLife(int decrement) {
		this.life -= decrement;
	}
	
	public void incrementLife(int increment){
		this.life += increment;
	}
	
	//O método de atirar deve ser sobrescrito nas subclasses, pois cada inimigo pode utilizar uma forma de atirar diferente.
	public abstract void shoot();

}
