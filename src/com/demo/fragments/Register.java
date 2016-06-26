package com.demo.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.assoc.adherentespace.JSONParser;
import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.ProgressDialog;
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

public class Register extends Fragment implements OnClickListener{
	
	private EditText name ,mail, pass;
	private Button  mRegister,mlogin;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    //php login script
    
    //localhost :  
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/register.php";
    
    //testing on Emulator:
    private static final String LOGIN_URL = "http://192.168.1.88/webservice/register.php";
    
  //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/register.php";
    
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
    @Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_registrer, container, false);

	
		
		name = (EditText)v.findViewById(R.id.fullnameRF);
		mail = (EditText)v.findViewById(R.id.emailRF);
		pass = (EditText)v.findViewById(R.id.passwordRF);
		

		mRegister = (Button)v.findViewById(R.id.register_btn);
		mlogin = (Button)v.findViewById(R.id.signinto_btn);
		
	mRegister.setOnClickListener(this);
	mlogin.setOnClickListener(this);

		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_btn:
			new CreateUser().execute();
			break;
		case R.id.signinto_btn:
			Fragment reg = null;
			reg = new Login();
			FragmentManager manager = getFragmentManager();
			FragmentTransaction transaction = manager.beginTransaction();
			transaction.replace(R.id.main_content, reg);
			transaction.commit();
			break;

		default:
			break;
		}
				
		
	}
	
	class CreateUser extends AsyncTask<String, String, String> {

		 /**
         * Before starting background thread Show Progress Dialog
         * */
		boolean failure = false;
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Creating Profil ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String nom = name.getText().toString();
            String email = mail.getText().toString();
            String password = pass.getText().toString();
            try {
                // Building Parameters
                //List<NameValuePair> params = new ArrayList<NameValuePair>();
               // params.add(new BasicNameValuePair("nom", nom));
                //params.add(new BasicNameValuePair("email", email));
               // params.add(new BasicNameValuePair("password", password));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
             JSONObject json = null ;
                //= jsonParser.makeHttpRequest(
                  //     LOGIN_URL, "POST", params);
 
                // full json response
                Log.d("Login attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("User Created!", json.toString());              	
                	//finish();
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);
                	
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
            if (file_url != null){
            	Toast.makeText(getActivity(), file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
		 

}
