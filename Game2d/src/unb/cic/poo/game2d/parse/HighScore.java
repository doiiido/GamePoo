package unb.cic.poo.game2d.parse;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("HighScore")
public class HighScore extends ParseObject{
	private String tableTitle = "Best Defenders";
	private String tableUser = "User";
	private String tableScore = "Score";
	private String tablePosition = "Rank";
	private String tableStage = "Stage";

	public String getTitle() {
        return getString(tableTitle);
    }
 
    public void setTitle(String title) {
        put(tableTitle, title);
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
    
    public int getPosition() {
        return getInt(tablePosition);
    }
 
    public void setPosition(int position) {
        put(tablePosition, position);
    }
    
    public int getStage() {
        return getInt(tableStage);
    }
 
    public void setStage(int stage) {
        put(tableStage, stage);
    }
}
