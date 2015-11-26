package unb.cic.poo.game2d.items;

import org.andengine.opengl.texture.region.ITextureRegion;

import unb.cic.poo.game2d.GameManager;
import unb.cic.poo.game2d.ResourceManager;
import unb.cic.poo.game2d.bullets.DoubleBulletType;
import unb.cic.poo.game2d.bullets.MachineGunBulletType;

public class MachineGunItem extends Item {

	public MachineGunItem(float pX, float pY) {
		super(pX, pY, ResourceManager.getInstance().heavyDropTextureRegion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doEffect() {
		GameManager.getInstance().getPlayer().setSecondaryBulletType(new MachineGunBulletType());
		GameManager.getInstance().getPlayer().changeBullet();
		GameManager.getInstance().getPlayer().changeBullet();
	}

}
