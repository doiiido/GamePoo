package unb.cic.poo.game2d.parse;

import com.example.game2d.R;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import unb.cic.poo.game2d.GameActivity;

public class ScoreTableActivity extends Activity {
	 
		// Declare Variable
		Button logout;
		Button back;
	 
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// Get the view from singleitemview.xml
			setContentView(R.layout.welcome);
	 
			// Retrieve current user from Parse.com
			ParseUser currentUser = ParseUser.getCurrentUser();
	 
			// Convert currentUser into String
			String struser = currentUser.getUsername().toString();
	 
			// Locate TextView in welcome.xml
			TextView txtuser = (TextView) findViewById(R.id.txtuser);
	 
			// Set the currentUser String into TextView
			txtuser.setText("You are logged in as " + struser);
	 
			// Locate Button in welcome.xml
			logout = (Button) findViewById(R.id.logout);
			back = (Button) findViewById(R.id.back);
	 
			// Logout Button Click Listener
			logout.setOnClickListener(new OnClickListener() {
	 
				public void onClick(View arg0) {
					// Logout current user
					ParseUser.logOut();
					Intent intent = new Intent(ScoreTableActivity.this,
							LoginSignupActivity.class);
					startActivity(intent);
					finish();
				}
			});
			
			back.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					Intent intent = new Intent(ScoreTableActivity.this,
							GameActivity.class);
					startActivity(intent);
					finish();

				}
			});
		}
}
