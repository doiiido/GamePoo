package unb.cic.poo.game2d.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("HighScore")
public class HighScore extends ParseObject {
	private static HighScore highScore = null;
	// Essas strings servem para referenciar o objeto específico
	private String tableUser = "User";
	private String tableScore = "Score";
	//private String tablePosition = "Position";
	private String tableStage = "Stage";
	
	public static HighScore getInstance() {
		if(highScore == null){
			highScore = new HighScore();
			return highScore;
		}
		return highScore;
	}
 
    public ParseUser getUser() {
        return getParseUser(tableUser);
    }
 
    public void setUser(ParseUser user) {
        put(tableUser, user);
    }
 
    public int getScore() {
        return getInt(tableScore);
    }
 
    public void setScore(int score) {
        put(tableScore, score);
    }
    
    public void incrementScore(int increm){
    	highScore.increment(tableScore,increm);
    	// highScore.saveInBackground(); // Conexão obrigatória
    	highScore.saveEventually(); // Quando houver conexão salva, mas não há callback
    }
    
    /*public int getPosition() {
        return getInt(tablePosition);
    }
 
    public void setPosition(int position) {
        put(tablePosition, position);
    }*/
    
    public int getStage() {
        return getInt(tableStage);
    }
 
    public void setStage(int stage) {
        put(tableStage, stage);
    }
}
