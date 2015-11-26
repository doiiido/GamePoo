package unb.cic.poo.game2d.items;

import org.andengine.opengl.texture.region.ITextureRegion;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.bullets.DoubleBulletType;
import unb.cic.poo.game2d.bullets.LaserBulletType;

public class DoubleBulletItem extends Item {

	public DoubleBulletItem(float pX, float pY) {
		super(pX, pY, ResourceManager.getInstance().doubleDropTextureRegion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doEffect() {
		GameManager.getInstance().getPlayer().setSecondaryBulletType(new DoubleBulletType());
		GameManager.getInstance().getPlayer().changeBullet();
		GameManager.getInstance().getPlayer().changeBullet();
	}

}
