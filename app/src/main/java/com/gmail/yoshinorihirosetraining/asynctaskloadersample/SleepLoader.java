package com.gmail.yoshinorihirosetraining.asynctaskloadersample;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * AsyncTaskLoader that just sleeps 5s.
 */

public class SleepLoader extends AsyncTaskLoader<Model> {
    private Model initModel;

    public SleepLoader(Context context, Model initModel) {
        super(context);
        this.initModel = initModel;
    }

    @Override
    public Model loadInBackground() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            ;
        }
        int count = Integer.parseInt(initModel.getResult());
        Model result = new Model(State.Finished, "" + (count+1));
        return result;
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(Model model) {
        super.onCanceled(model);
    }
}
