package unb.cic.poo.game2d;

import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.util.color.Color;

//Inimigo Básico

public class CommonEnemy extends Enemy{
	private static final int DEFAULT_COMMON_ENEMY_SPEED = 150;
	private static final int COMMON_ENEMY_HEIGHT = 32;
	private static final int COMMON_ENEMY_WIDTH = 32;
	private static final int COMMON_ENEMY_LIFE = 1;
	
	public CommonEnemy(float pX, float pY) {
		super(pX, pY, COMMON_ENEMY_WIDTH, COMMON_ENEMY_HEIGHT, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.speed = DEFAULT_COMMON_ENEMY_SPEED;
		this.life = COMMON_ENEMY_LIFE;
		this.setColor(Color.BLUE);
		this.setMovement();
		
	}
	
	private void setMovement(){
		float distance = GameManager.getInstance().getGameCamera().getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -distance-COMMON_ENEMY_WIDTH, 0);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
	}
	
	//Verifica se o inimigo colide com o player, caso verdadeiro, o jogo para (Game Over).
	protected void onManagedUpdate(float pSecondsElapsed) {
		if(this.collidesWith(GameManager.getInstance().getPlayer())){
			GameManager.getInstance().getGameEngine().stop();
		}
		super.onManagedUpdate(pSecondsElapsed);
	}

	@Override
	public void shoot() {
	}
}
