package unb.cic.poo.game2d.items;

import org.andengine.entity.modifier.IEntityModifier.IEntityModifierListener;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.util.modifier.IModifier;

import unb.cic.poo.game2d.GameManager;

public abstract class Item extends Sprite implements IEntityModifierListener{
	private int speed;
	private boolean movementFinished;
	
	public Item(float pX, float pY, ITextureRegion pTextureRegion) {
		super(pX, pY, pTextureRegion, GameManager.getInstance().getGameEngine().getVertexBufferObjectManager());
		speed = 125;
		setMovement(pX);
		movementFinished = false;
		this.registerUpdateHandler(new ItemHandler(this));
	}

	private void setMovement(float pX) {
		float distance = pX+this.getWidth();
		float durationTime = distance/this.speed;
		
		MoveByModifier moveByModifier = new MoveByModifier(durationTime, -distance-this.getWidth(), 0);
		moveByModifier.addModifierListener(this);
		
		moveByModifier.setAutoUnregisterWhenFinished(true);
		this.registerEntityModifier(moveByModifier);
	}

	public abstract void doEffect();

	public boolean isMovementFinished() {
		return movementFinished;
	}

	public void setMovementFinished(boolean movementFinished) {
		this.movementFinished = movementFinished;
	}

	@Override
	public void onModifierStarted(IModifier<IEntity> pModifier, IEntity pItem) {
	}
	
	@Override
	public void onModifierFinished(IModifier<IEntity> pModifier, IEntity pItem) {
		this.movementFinished = true;
	}
	
	public void drop(){
		GameManager.getInstance().getGameScene().attachChild(this);	
	}
	
}
