package unb.cic.poo.game2d.items;


public class LifeItemGen implements ItemGen {

	@Override
	public Item getItem(float pX, float pY) {
		return new LifeItem(pX, pY);
	}

}
