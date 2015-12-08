package unb.cic.poo.game2d.items;

import org.andengine.opengl.texture.region.ITextureRegion;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.bullets.FlamethrowerBulletType;

public class FlameThrowerItem extends Item {

	public FlameThrowerItem(float pX, float pY) {
		super(pX, pY, ResourceManager.getInstance().flameDropTextureRegion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doEffect() {
		GameManager.getInstance().getPlayer().setSecondaryBulletType(new FlamethrowerBulletType(GameManager.getInstance().getPlayer()));
		GameManager.getInstance().getPlayer().changeBullet();
		GameManager.getInstance().getPlayer().changeBullet();
		
	}


}
