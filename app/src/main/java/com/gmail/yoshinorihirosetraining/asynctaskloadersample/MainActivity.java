package com.gmail.yoshinorihirosetraining.asynctaskloadersample;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    private static Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (controller == null) {
            controller = new Controller(this);
        } else {
            controller.setActivity(this);
        }

        updateUi(controller.getModel());
    }

    public void updateUi(Model model) {
        Button button = (Button)findViewById(R.id.button);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressbar);
        TextView textView = (TextView)findViewById(R.id.text);

        switch (model.getState()) {
            case Init:
                textView.setVisibility(View.VISIBLE);
                textView.setText("Push button");
                progressBar.setVisibility(GONE);
                break;
            case Processing:
                textView.setVisibility(GONE);
                textView.setText("");
                progressBar.setVisibility(View.VISIBLE);
                break;
            case Finished:
                textView.setVisibility(View.VISIBLE);
                textView.setText("Result: " + model.getResult());
                progressBar.setVisibility(GONE);
                break;
        }
    }

    public void onClickButton(View view) {
        LoaderManager manager = getLoaderManager();
        Loader loader = manager.getLoader(0);
        if (loader != null) {
            loader.stopLoading();
            manager.restartLoader(0, null, controller).forceLoad();
        } else {
            manager.initLoader(0, null, controller).forceLoad();
        }
    }
}
