package com.demo.slidingmenu_tabhostviewpager;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.assoc.Activity.ContcatActivity;
import com.assoc.Activity.WebViewActivityPdf;
import com.demo.adapters.NavListAdapter;
import com.demo.fragments.Login;
import com.demo.fragments.MyAbout;
import com.demo.fragments.MyHome;
import com.demo.models.NavItem;


public class MainActivity extends ActionBarActivity {
	private final String tceBlog = "http://www.associationcomores.com";
	DrawerLayout drawerLayout;
	RelativeLayout drawerPane;
	ListView lvNav;

	public List<NavItem>  listNavItems;
	public List<Fragment> listFragments;

	ActionBarDrawerToggle actionBarDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
		lvNav = (ListView) findViewById(R.id.nav_list);

		listNavItems = new ArrayList<NavItem>();

		listNavItems.add(new NavItem("Accueil", "portail mobile",
				R.drawable.ic_action_home));
		listNavItems.add(new NavItem("Me connecter", "Espace Adhérent",
				R.drawable.ic_action_settings));
		listNavItems.add(new NavItem("À propos", "Qui sommes nous",
				R.drawable.ic_action_about));


		NavListAdapter navListAdapter = new NavListAdapter(
				getApplicationContext(), R.layout.item_nav_list, listNavItems);

		lvNav.setAdapter(navListAdapter);

		listFragments = new ArrayList<Fragment>();
		listFragments.add(new MyHome());
		listFragments.add(new Login());
		listFragments.add(new MyAbout());
		//listFragments.add(new ProfilAdherent());

		// load first fragment as default:
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction()
		.replace(R.id.main_content, listFragments.get(0)).commit();

		setTitle(listNavItems.get(0).getTitle());
		lvNav.setItemChecked(0, true);
		drawerLayout.closeDrawer(drawerPane);

		// set listener for navigation items:
		lvNav.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// replace the fragment with the selection correspondingly:
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager
				.beginTransaction()
				.replace(R.id.main_content, listFragments.get(position))
				.commit();

				setTitle(listNavItems.get(position).getTitle());
				lvNav.setItemChecked(position, true);
				drawerLayout.closeDrawer(drawerPane);

			}
		});

		// create listener for drawer layout
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.string.drawer_opened, R.string.drawer_closed) {

			@SuppressLint("NewApi")
			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				super.onDrawerOpened(drawerView);
			}

			@SuppressLint("NewApi")
			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				invalidateOptionsMenu();
				super.onDrawerClosed(drawerView);
			}

		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.


		getMenuInflater().inflate(R.menu.mymenu, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the MyHome/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.


		if (actionBarDrawerToggle.onOptionsItemSelected(item)){
			return true;
		}else 	if(item != null){
			switch(item.getItemId())
			{
			case R.id.item1:
				goToActivity(ContcatActivity.class);
				break;
			case R.id.item2:
				goToActivity(WebViewActivityPdf.class);
				break;
			case R.id.item3:
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tceBlog));
				PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
				@SuppressWarnings("deprecation")
				Notification noti = new Notification.Builder(this)
				.setTicker("un simple clic...!")
				.setContentTitle("Besoin d'informations?")
				.setContentText("Cliquez ici")
				.setSmallIcon(R.drawable.arrowstars)
				.setContentIntent(pIntent).getNotification();
				noti.flags=Notification.FLAG_AUTO_CANCEL;
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(0, noti);
				break;

			}
			return false;
		}

		return super.onOptionsItemSelected(item);


	}


	private void goToActivity(Class<? extends Activity> activityClass)
	{
		Intent newActivity=new Intent(MainActivity.this,activityClass);
		startActivity(newActivity);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
		actionBarDrawerToggle.syncState();
	}
}
