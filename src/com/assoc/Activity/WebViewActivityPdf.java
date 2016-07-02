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
		webView.loadUrl("http://kiaai.com/files/pdf/0340481628886f7f2197bf133f37636c.pdf");
	   
	}

}