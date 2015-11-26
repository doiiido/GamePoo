package unb.cic.poo.game2d.items;

public class DoubleBulletGen implements ItemGen {

	@Override
	public Item getItem(float pX, float pY) {
		return new DoubleBulletItem(pX, pY);
	}

}
