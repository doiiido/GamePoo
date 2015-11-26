package unb.cic.poo.game2d.enemies;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.GameActivity;
import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

//Inimigo Basico

public class CommonEnemy extends Enemy{
	private static final int DEFAULT_COMMON_ENEMY_SPEED = 150;
	//private static final int COMMON_ENEMY_HEIGHT = GameActivity.CAMERA_HEIGHT/22; //32
	private static final int COMMON_ENEMY_WIDTH = GameActivity.CAMERA_WIDTH/40; //32
	private static final int COMMON_ENEMY_LIFE = 1;
	
	public CommonEnemy(float pX, float pY) {
		super(pX, pY, ResourceManager.walkerTextureRegion, 
				GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		this.speed = DEFAULT_COMMON_ENEMY_SPEED;
		this.life = COMMON_ENEMY_LIFE;
		this.setMovement(pX);
	}
	
	private void setMovement(float pX){
		float distance = pX+this.getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -distance-COMMON_ENEMY_WIDTH, 0);
		moveByModifier.addModifierListener(this);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
	}

	@Override
	public void shoot() {
	}
	
	@Override
	public void removeEnemy() {
		GameManager.getInstance().getEnemies().remove(this);
		GameManager.getInstance().getGameScene().detachChild(this);
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}
	
	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		this.movementFinished = true;
	}

	@Override
	public void handleTouchEvent(TouchEvent pSceneTouchEvent) {
		// TODO Auto-generated method stub
		
	}
}
