package unb.cic.poo.game2d.items;

public class FlameThrowerGen implements ItemGen {

	@Override
	public Item getItem(float pX, float pY) {
		return new FlameThrowerItem(pX, pY);
	}

}
