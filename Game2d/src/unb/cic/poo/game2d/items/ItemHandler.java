package unb.cic.poo.game2d.items;

import org.andengine.engine.handler.IUpdateHandler;

import unb.cic.poo.game2d.GameManager;

public class ItemHandler implements IUpdateHandler {
	Item item;
	
	public ItemHandler(Item item) {
		this.item = item;
	}
	
	@Override
	public void onUpdate(float pSecondsElapsed) {
		GameManager.getInstance().getGameEngine().runOnUpdateThread(new Runnable() {
			
			@Override
			public void run() {
				if(item.isMovementFinished()){
					GameManager.getInstance().getGameScene().detachChild(item);
				}
				else if(item.collidesWith(GameManager.getInstance().getPlayer())){
					item.doEffect();
					GameManager.getInstance().getGameScene().detachChild(item);
				}
			}
		});
		if(item.isMovementFinished()){
			item.unregisterUpdateHandler(this);
		}

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
