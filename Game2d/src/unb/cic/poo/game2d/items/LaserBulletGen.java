package unb.cic.poo.game2d.items;

public class LaserBulletGen implements ItemGen {

	@Override
	public Item getItem(float pX, float pY) {
		return new LaserBulletItem(pX, pY);
	}

}
