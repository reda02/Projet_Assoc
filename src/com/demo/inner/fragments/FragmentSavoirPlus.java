package com.demo.inner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.view.ViewGroup;
import com.demo.slidingmenu_tabhostviewpager.R;

public class FragmentSavoirPlus extends Fragment {

	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		

		WebView webView;
		View v = inflater.inflate(R.layout.webview, container, false);
		webView = (WebView) v.findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("http://www.associationcomores.com/#service");
		return v;
}
}
