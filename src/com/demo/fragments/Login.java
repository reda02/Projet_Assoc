package com.demo.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.assoc.adherentespace.Adherent;
import com.assoc.adherentespace.JSONParser;
import com.assoc.adherentespace.SessionManager;
import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Fragment  implements OnClickListener{



	SessionManager manager;
	private EditText  mail=null;
	private EditText  pass=null;
	private Button mSubmit, mRegister;
	public static String USER_NOM = null;
	public static String USER_PRRENOM = null;
	public static String USER_St= null;
	public static String USER_DN = null;
	public static String USER_PHOTO = null;
	public static String USER_GENRE = null;
	public static String USER_Ville = null;
	public static final String USER_PWD = "USERPWD";
	public static final String USER_id = "USERID";
	public static final String USER_email = "USEREMAIl";
	// Progress Dialog
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();
	ArrayList<Adherent> AdherentList;

	//php login script location:

	//localhost :  
	//testing on your device
	//put your local ip instead,  on windows, run CMD > ipconfig
	//or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

	//testing on Emulator:
	private static final String LOGIN_URL = "http://associationcomores.com/servicetest.svc/AdherentInfo/Log";

	//testing from a real server:
	//private static final String LOGIN_URL = "http://10.33.0.233/webservice/login.php";

	//JSON element ids from repsonse of php script:
	private static final String TAG_SUCCESS = "StatutConnexion";
	private static final String TAG_MESSAGE = "messagStatus";

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_login, container, false);




		//setup input fields
		mail = (EditText)v.findViewById(R.id.emailSF);
		pass = (EditText)v.findViewById(R.id.passwordSF);

		//setup buttons
		mSubmit = (Button)v.findViewById(R.id.signin_btn);
		mRegister = (Button)v.findViewById(R.id.notreg_btn);

		//register listeners
		mSubmit.setOnClickListener(this);
		mRegister.setOnClickListener(this);

		return v;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.signin_btn:
			new AttemptLogin().execute();
			break;
		case R.id.notreg_btn:
			Fragment reg = null;
			reg = new Register();
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.main_content, reg);
			transaction.commit();
			break;

		default:
			break;
		}
	}

	class AttemptLogin extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * 
		 * */

		SessionManager manager = new SessionManager();

		boolean failure = false;

		int success;

		String email =  "Adjouza36"   ;            //mail.getText().toString();
		String password = "Adjouza@Mohamed"  ;     //pass.getText().toString();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Attempting login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag

			try {
				// Building Parameters
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("email", email);;
				params.put( "password",password);

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(
						LOGIN_URL, "POST", params);

				// check your log for json response
				Log.d("Login attempt", json.toString());
				// String message = json.getString("msg");

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				
				USER_GENRE =  json.getString("Genre");
				USER_NOM =  json.getString("Nom");
				USER_PRRENOM = json.getString("Prenom");
				USER_St=  json.getString("SituatFam");
				USER_DN =  json.getString("Naissance");
				USER_Ville =  json.getString("VilOrig");
				USER_PHOTO = "2P.JPG";
				//USER_NAME =json.getString("nom");json.getString("Photo");

				if(!email.equals("") && !password.equals("") ) {
					if (success == 1) {

						// Log.d("your message is ",message);//login detail is wrong--u r invalide user
						Log.d("Login Successful!2", json.toString());

						return "success";
					}else{

						//Log.d("your message is ",message);//login detail is wrong--u r invalide user
						Log.d("Login Failure!1", json.getString(TAG_MESSAGE));
						
						return "echec";

						//return json.getString(TAG_MESSAGE);

					}
				}else{
					Log.d("Login Failure!1 ", json.toString());

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null && file_url.equals("success")){
				manager.setPreferences(getActivity(), "status", "1");
				String status=manager.getPreferences(getActivity(),"status");
				Log.d("status", status);

				Adherent ad =  new Adherent();
				Intent i = new Intent(getActivity(), MyProfil.class);
				i.putExtra(USER_email, email);
				i.putExtra(USER_PWD, password);
				i.putExtra(USER_GENRE, USER_GENRE);
				i.putExtra(USER_NOM, USER_NOM);
				i.putExtra(USER_PRRENOM, USER_PRRENOM);
				i.putExtra(USER_St, USER_St);
				i.putExtra(USER_DN, USER_DN);
				i.putExtra(USER_Ville, USER_Ville);
				i.putExtra(USER_PHOTO, USER_PHOTO);
				i.putExtra("nom", ad.getNom());
				startActivity(i);
				
				Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
			}else{


				Toast.makeText(getActivity(), " Login ou Password Invalid ", Toast.LENGTH_LONG).show();
				pass.setText("");
				mail.setText("");

			}

		}

	}


}
