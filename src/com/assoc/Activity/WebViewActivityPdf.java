package com.assoc.Activity;



import com.demo.slidingmenu_tabhostviewpager.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivityPdf extends Activity {

	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webviewpdf);

		webView = (WebView) findViewById(R.id.webView2);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("https://drive.google.com/open?id=1blXy6ooFNSGfMC4jlzlaTrTUjwpVHVO-HYuZOI4y5FxqtLE-bEP8SYAH44X4pfIyU8mZ4FWrymkJh6RR");
	   
	}

}