package unb.cic.poo.game2d;

//Classe responsável por instanciar uma bala.

public class CommonBulletType implements BulletType{
	
	@Override
	public Bullet getBullet(float pX, float pY, boolean isEnemy) {
		return new CommonBullet(pX, pY, isEnemy);
	}

	//Getters e Setters
	
}
