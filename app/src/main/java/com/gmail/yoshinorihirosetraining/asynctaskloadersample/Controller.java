package com.gmail.yoshinorihirosetraining.asynctaskloadersample;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

/**
 * Controller Shared by Activities.
 */

public class Controller implements LoaderManager.LoaderCallbacks<Model> {
    private Model model;
    private MainActivity activity;
    private Context context;

    public Controller(MainActivity activity) {
        model = new Model(State.Init, "0");
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public Model getModel() {
        return this.model;
    }

    /**
     * Implementation of LoaderManager.LoaderCallbacks
     */

    public Loader<Model> onCreateLoader(int id, Bundle args) {
        this.model = new Model(State.Processing, this.model.getResult());
        this.activity.updateUi(this.model);
        return new SleepLoader(context, model);
    }

    public void onLoadFinished(Loader<Model> loader, Model data) {
        this.model = data;
        this.activity.updateUi(this.model);
    }

    public void onLoaderReset(Loader<Model> loader) {
        ;
    }
}
