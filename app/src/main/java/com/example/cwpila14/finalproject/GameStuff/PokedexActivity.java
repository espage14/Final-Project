package com.example.cwpila14.finalproject.GameStuff;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.cwpila14.finalproject.R;

public class PokedexActivity extends Activity {

    // instance variables
    Button go_button = null;
    WebView web_view = null;
    TextView url_field = null;
    WebView clock = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_pokemon_layout);
        getWindow().getAttributes().windowAnimations = R.style.Fade;
        // get web view
        web_view = (WebView) findViewById(R.id.webView);

        // web stuff - load pokemon info
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl("http://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_Kanto_Pok%C3%A9dex_number");
    }
}
