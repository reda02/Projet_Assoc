package com.assoc.events;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class EventsFragment extends Fragment {

	ArrayList<Event> EventList;

	ActorAdapter adapter;
	String image, imagep ;
	
	private String photo_URL = "http://associationcomores.com/event/";

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_events, container, false);

		EventList = new ArrayList<Event>();
		//http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors
		new JSONAsyncTask().execute("http://associationcomores.com/servicetest.svc/evenement/Liste/");

		ListView listview = (ListView)v.findViewById(R.id.list);
		adapter = new ActorAdapter(getActivity().getApplicationContext(), R.layout.row, EventList);

		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity().getApplicationContext(), EventList.get(position).getTitre(), Toast.LENGTH_LONG).show();				
			}
		});

		return v;
	}


	class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(getActivity());
			dialog.setMessage("Loading, please wait");
			dialog.setTitle("Connecting server");
			dialog.show();
			dialog.setCancelable(false);
		}

		@Override
		protected Boolean doInBackground(String... urls) {
			try {

				//------------------>>
				HttpGet httppost = new HttpGet(urls[0]);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);

				// StatusLine stat = response.getStatusLine();
				int status = response.getStatusLine().getStatusCode();

				if (status == 200) {
					HttpEntity entity = response.getEntity();
					String data = EntityUtils.toString(entity);


					//JSONObject jsono = new JSONObject(data);
					
					//JSONArray jarray = jsono.getJSONArray(data);
					 JSONArray jarray = new JSONArray(data);
					for (int i = 0; i < jarray.length(); i++) {
						JSONObject object = jarray.getJSONObject(i);
						Event actor = new Event();
						actor.setTitre(object.getString("titre"));
						actor.setDescription(object.getString("contenu"));
						actor.setDate(object.getString("date"));
						
						image = object.getString("photo");
						imagep = photo_URL.concat(image);
						actor.setImage(imagep);
						EventList.add(actor);
					}
					
					return true;
				}

				//------------------>>

			} catch (ParseException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		}

		protected void onPostExecute(Boolean result) {
			dialog.cancel();
			adapter.notifyDataSetChanged();
			if(result == false)
				Toast.makeText(getActivity().getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

		}
	}






}
