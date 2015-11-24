package unb.cic.poo.game2d.items;

import org.andengine.opengl.texture.region.ITextureRegion;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.bullets.LaserBulletType;

public class LaserBulletItem extends Item {

	public LaserBulletItem(float pX, float pY) {
		super(pX, pY, ResourceManager.playerTextureRegion);
		this.setY(this.getY()-(this.getHeight()/2));
	}

	@Override
	public void doEffect() {
		GameManager.getInstance().getPlayer().setSecondaryBulletType(new LaserBulletType());
		GameManager.getInstance().getPlayer().changeBullet();
		GameManager.getInstance().getPlayer().changeBullet();
	}

}
