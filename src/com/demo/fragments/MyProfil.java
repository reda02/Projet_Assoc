package com.demo.fragments;
import com.assoc.Activity.ContcatActivity;
import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


public class MyProfil extends TabActivity 
{
	public static String USER_email=null;
	        public static String USER_PWD = null;
            public static String USER_NOM = null;
			public static String USER_PRRENOM = null;
			public static String USER_St = null;
			public static String USER_DN = null;
			public static String USER_PHOTO = null;
			public static String USER_GENRE = null;
			public static String USER_Ville = null;
			/** Called when the activity is first created. */
           
			
                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);

                        //Remove title bar
                        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

                        setContentView(R.layout.homprofil);

                        TabHost tabHost = getTabHost();

                        // Tab for Tatva
                        TabSpec t = tabHost.newTabSpec("Mon Profil");
                        // setting Title and Icon for the Tab
                        t.setIndicator("Mon Profil");
                                   
        				Intent i = new Intent(this, ProfilAdherent.class);
        				Intent intent = getIntent();
        				String genre = intent.getStringExtra(Login.USER_GENRE);
        				String ville = intent.getStringExtra(Login.USER_Ville);
        				String email = intent.getStringExtra(Login.USER_email);
        		        String nom = intent.getStringExtra(Login.USER_NOM);
        		        String prenom = intent.getStringExtra(Login.USER_PRRENOM);
        		        String st = intent.getStringExtra(Login.USER_St);
        		        String dte = intent.getStringExtra(Login.USER_DN);
        		        String image = intent.getStringExtra(Login.USER_PHOTO);
        		        
        		        i.putExtra("USER_GENRE", genre);
        		        i.putExtra("USER_Ville", ville);
        		        i.putExtra("USER_email", email);
        				i.putExtra("USER_NOM", nom);
        				i.putExtra("USER_PRRENOM", prenom);
        				i.putExtra("USER_St", st);
        				i.putExtra("USER_DN", dte);
        				i.putExtra("USER_PHOTO", image);
        				
                        t.setContent(i);
                        // Tab for Moksh
                        TabSpec m = tabHost.newTabSpec("autres ");
                        m.setIndicator("Autres");
                        Intent mokshIntent = new Intent(this, ContcatActivity.class);
                        m.setContent(mokshIntent);

                        // Adding all TabSpec to TabHost
                        tabHost.addTab(t); // Adding Tatva tab
                        tabHost.addTab(m); // Adding Moksh tab
                   
                    }
                    
                    }

