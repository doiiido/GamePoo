package unb.cic.poo.game2d.parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import unb.cic.poo.game2d.GameActivity;

import com.example.game2d.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignupActivity extends Activity {
	// Declare Variables
	Button loginbutton;
	Button signup;
	Button back;
	String usernametxt;
	String passwordtxt;
	EditText password;
	EditText username;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from main.xml
		setContentView(R.layout.loginsignup);
		// Locate EditTexts in main.xml
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);

		// Locate Buttons in main.xml
		loginbutton = (Button) findViewById(R.id.login);
		signup = (Button) findViewById(R.id.signup);
		back = (Button) findViewById(R.id.back);

		// Login Button Click Listener
		loginbutton.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();

				// Send data to Parse.com for verification
				ParseUser.logInInBackground(usernametxt, passwordtxt,
						new LogInCallback() {
							public void done(ParseUser user, ParseException e) {
								if (user != null) {
									// If user exist and authenticated, send user to ScoreTableActivity.class
									Intent intent = new Intent(
											LoginSignupActivity.this,
											ScoreTableActivity.class);
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Successfully Logged in",
											Toast.LENGTH_LONG).show();
									finish();
								} else {
									Toast.makeText(getApplicationContext(),
											"No such user exist, please signup",
											Toast.LENGTH_LONG).show();
								}
							}
						});
			}
		});
		// Sign up Button Click Listener
		signup.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Retrieve the text entered from the EditText
				usernametxt = username.getText().toString();
				passwordtxt = password.getText().toString();
				
				// Force user to fill up the form
				if (usernametxt.equals("") && passwordtxt.equals("")) {
					Toast.makeText(getApplicationContext(),
							"Please complete the sign up form.",
							Toast.LENGTH_LONG).show();

				} else {
					// Save new user data into Parse.com Data Storage
					ParseUser user = new ParseUser();
					user.setUsername(usernametxt);
					user.setPassword(passwordtxt);
					user.signUpInBackground(new SignUpCallback() {
						public void done(ParseException e) {
							if (e == null) {
								// Show a simple Toast message upon successful registration
								Toast.makeText(getApplicationContext(),
										"Successfully Signed up, please Log in.",
										Toast.LENGTH_LONG).show();
							} else {
								Toast.makeText(getApplicationContext(),
										"Sign up Error", Toast.LENGTH_LONG)
										.show();
							}
						}
					});
				}

			}
		});
		
		back.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Intent intent = new Intent(LoginSignupActivity.this,
						GameActivity.class);
				startActivity(intent);
				finish();

			}
		});

	}
}
