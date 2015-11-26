package unb.cic.poo.game2d.items;

public class MachineGunGen implements ItemGen {

	@Override
	public Item getItem(float pX, float pY) {
		return new MachineGunItem(pX, pY);
	}

}
