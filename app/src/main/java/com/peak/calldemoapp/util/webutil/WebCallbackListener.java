package com.peak.calldemoapp.util.webutil;

/**
 * Created by ly on 10/13/2015.
 */
public interface WebCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
