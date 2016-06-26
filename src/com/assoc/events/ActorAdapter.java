package com.assoc.events;

import java.io.InputStream;
import java.util.ArrayList;

import com.demo.slidingmenu_tabhostviewpager.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ActorAdapter extends ArrayAdapter<Event> {
	ArrayList<Event> EventList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public ActorAdapter(Context context, int resource, ArrayList<Event> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		EventList = objects;
	}
 
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
			holder.tvTitre = (TextView) v.findViewById(R.id.tvName);
			holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);
			holder.tvDate = (TextView) v.findViewById(R.id.tvDateOfBirth);
	
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.imageview.setImageResource(R.drawable.ic_launcher);
		new DownloadImageTask(holder.imageview).execute(EventList.get(position).getImage());
		holder.tvTitre.setText(EventList.get(position).getTitre());
		holder.tvDescription.setText(EventList.get(position).getDescription());
		holder.tvDate.setText("Date de publication: " + EventList.get(position).getDate());
		return v;
		

	}

	static class ViewHolder {
		public ImageView imageview;
		public TextView tvTitre;
		public TextView tvDescription;
		public TextView tvDate;

	

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}
}