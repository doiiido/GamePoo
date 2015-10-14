package unb.cic.poo.game2d;



import unb.cic.poo.game2d.BulletType;

public class LaserBulletType implements BulletType{

	@Override
	public LaserBullet getBullet(float pX, float pY, boolean isEnemy) {
		return new LaserBullet(pX, pY, isEnemy);
	}

}
