package unb.cic.poo.game2d;

public class PlayerStatistics {
	private int amountShoot;
	private int damageTaken;
	
	
	public int getAmountShoot() {
		return amountShoot;
	}
	public void setAmountShoot(int amountShoot) {
		this.amountShoot = amountShoot;
	}
	public void incrementDamageTaken(int damage){
		this.damageTaken += damage;
	}
	
	
	public int getDamageTaken() {
		return damageTaken;
	}
	public void setDamageTaken(int damageTaken) {
		this.damageTaken = damageTaken;
	}
	public void incrementAmountShoot(){
		amountShoot++;
	}
	
	
}
