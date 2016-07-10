



package com.demo.fragments;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.assoc.adherentespace.JSONParser;
import com.assoc.adherentespace.JSONParserPwd;
import com.assoc.adherentespace.SessionManager;
import com.demo.fragments.Login.AttemptLogin;
import com.demo.slidingmenu_tabhostviewpager.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilAdherent extends  FragmentActivity implements OnClickListener {
	SessionManager manager;
	String image, imagep ;
	Bitmap b , circularBitmap;
	String username;

	ImageView  photo ;
	Dialog dlg;
	HashMap<String, String> params;
	public static String value ;
	// private static final String LOGIN_URL = "http://associationcomores.com/servicetest.svc/AdherentInfo/Log";

	private static final String PwdCH_URL = "http://associationcomores.com/servicetest.svc/setAdherentPasword";


	private String photo_URL = "http://associationcomores.com/imagesAdherent/";
	Button chgpass,chgpassfr,chgbtn,cancel;
	EditText oldpass,newpass;
	String token,grav,oldpasstxt,newpasstxt;   

	JSONObject json ;
	// JSON parser class
	JSONParserPwd jsonParser = new JSONParserPwd();
	//onBackpressed to stop user from going MainActivity to Login Screen on back button press 
	@Override
	public void onBackPressed() {
		super.onBackPressed();

		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
		startActivity(intent);
		finish();
		System.exit(0);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.compte_user);





		Intent intent = getIntent();
		String genre = intent.getStringExtra("USER_GENRE");
		String ville = intent.getStringExtra("USER_Ville");
		String email = intent.getStringExtra("USER_email");
		String nom = intent.getStringExtra("USER_NOM");
		String prenom = intent.getStringExtra("USER_PRRENOM");
		String st = intent.getStringExtra("USER_St");
		String dte = intent.getStringExtra("USER_DN");
		image = intent.getStringExtra("USER_PHOTO");
		imagep = photo_URL.concat(image);

		//TextView textprofil = (TextView) findViewById(R.id.textprenom);TextView textnom = (TextView) findViewById


		TextView textemail = (TextView) findViewById(R.id.textviewemail);
		TextView textville = (TextView) findViewById(R.id.textville);
		TextView textprofil = (TextView) findViewById(R.id.textprenom);
		TextView textsituation = (TextView) findViewById(R.id.textstiuatio);
		TextView textdatens = (TextView) findViewById(R.id.textdatens);
		photo = (ImageView) findViewById(R.id.imageprofil);


		username = prenom+"_"+nom;



		//textnom.setText(nom);
		textprofil.setText(genre+": "+prenom +" "+nom);
		textsituation.setText("Situation familiale : "+st);
		textdatens.setText("Date de naissance :"+dte);
		textemail.setText("E-mail : "+email);
		textville.setText("Ville : "+ville);
		information info = new information();
		info.execute("");


		manager = new SessionManager();

		Button button = (Button) findViewById(R.id.btn_logout);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				manager.setPreferences(ProfilAdherent.this, "status", "0");
				String status= manager.getPreferences(ProfilAdherent.this, "status");
				//  Intent i = new Intent(ProfilAdherent.this, Login.class);
				// Log.d("status", status);
				finish();
				//startActivity(i);setFragment
				Fragment login ;
				onAttachFragment(new Login());
			}
		});

		chgpass = (Button)findViewById(R.id.chgbtn);

		chgpass.setOnClickListener(this);




	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.chgbtn:



			dlg = new Dialog(ProfilAdherent.this);
			dlg.setContentView(R.layout.chgpassword_frag);
			dlg.setTitle("Changer le Password");
			oldpass = (EditText)dlg.findViewById(R.id.oldpass);
			newpass = (EditText)dlg.findViewById(R.id.newpass);
			oldpasstxt = oldpass.getText().toString(); //"aid@Omar";
			newpasstxt = newpass.getText().toString(); //"Said@Omar";
			chgbtn = (Button)dlg.findViewById(R.id.chgbtnt);
			
			chgbtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					new AttemptPwd().execute();
				}
			});
			
			
			

			//  params.put("newpass", newpasstxt);
			//	params.put("id", token);



			cancel = (Button)dlg.findViewById(R.id.cancelbtn);
			cancel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					dlg.dismiss();
				}
			});

			dlg.show();



			break;

		default:
			break;
		}
	}


	
	public class information extends AsyncTask<String, String, String>
	{
		@Override
		protected String doInBackground(String... arg0) {

			try
			{
				URL url = new URL(imagep);
				InputStream is = new BufferedInputStream(url.openStream());
				b = BitmapFactory.decodeStream(is);
				circularBitmap = ImageConverter.getRoundedCornerBitmap(b, 100);
				

			} catch (IOException e) {}
			return null;
		}


		@Override
		protected void onPostExecute(String result) {
			photo.setImageBitmap(circularBitmap);
		}
	}


	@SuppressLint("NewApi")
	public void setFragment(Fragment frag)
	{    
		android.app.FragmentManager fm = getFragmentManager();   
		if (fm.findFragmentById(R.id.main_content) == null) {
			fm.beginTransaction().add(R.id.main_content, frag).commit();
		}

	}

	// pour prensenter l'image dans la circle 
	public static class ImageConverter {

		public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(output);

			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(rect);
			final float roundPx = pixels;

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

			paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect, paint);

			return output;
		}
	}
	class AttemptPwd extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * 
		 * */
  //pass.getText().toString();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		
		}

		@Override
		protected String doInBackground(String... urls) {
			// TODO Auto-generated method stub
			// Check for success tag

			
				// Building Parameters
				params = new HashMap<String, String>();
				params.put("UserName", username);
				params.put("PasswordOld", oldpasstxt);
				params.put("PasswordNew", newpasstxt);
				//URL url = new URL(imagep);
				//InputStream is = new BufferedInputStream(url.openStream());
				//b = BitmapFactory.decodeStream(is);
				//circularBitmap = ImageConverter.getRoundedCornerBitmap(b, 100);
				try {
				JSONObject json;
				
					json = jsonParser.makeHttpRequest(PwdCH_URL, "POST", params);
				
				value =  json.getString("result");

			
					if (value == "true") {
						dlg.cancel();
						// Log.d("your message is ",message);//login detail is wrong--u r invalide user
						Log.d("pwd Successful!2", json.toString());
					    //Toast.makeText(ProfilAdherent.this, "success", Toast.LENGTH_LONG).show();
						
						return "success";
						
					}else{

						//Log.d("your message is ",message);//login detail is wrong--u r invalide user
					
						
						return "echec";
					}} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				return null;

		}
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			if (file_url != null && file_url.equals("success")){
				
				
				Toast.makeText(ProfilAdherent.this, "le mot de passe a été modifié avec succès", Toast.LENGTH_LONG).show();
			}else{


				Toast.makeText(ProfilAdherent.this, "l'ancienne mot de passe peut être incorrecte ", Toast.LENGTH_LONG).show();
				Toast.makeText(ProfilAdherent.this, "le mot de passe n'etais pas changé  ", Toast.LENGTH_LONG).show();
				oldpass.setText("");
				newpass.setText("");

			}	
				
				
			}
	

		}

	}














