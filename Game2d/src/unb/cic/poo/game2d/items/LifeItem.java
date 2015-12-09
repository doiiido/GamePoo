package unb.cic.poo.game2d.items;


import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;

public class LifeItem extends Item {

	public  LifeItem(float pX, float pY) {
		super(pX, pY, ResourceManager.getInstance().lifeDropTextureRegion);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void doEffect() {
		if(GameManager.getInstance().getPlayer().getLife() < GameManager.getInstance().getPlayer().DEFAULT_PLAYER_LIFE)
			GameManager.getInstance().getPlayer().incrementLife(1);
	}

}
