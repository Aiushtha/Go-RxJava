package org.lxz.tools.android.debug;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import de.greenrobot.event.EventBus;

/**
 * @author Aidan Follestad (afollestad)
 */
public class MainService extends Service {

    private void log(String message) {
        Log.v("test", message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        register();
    }

    public void onEvent(PostEvent event) {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void register() {
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            if(intent!=null) {
                MessageBean bean = (MessageBean) intent.getSerializableExtra("data");
                if(bean!=null) {
                  MessageCache.addMessage(bean);
                  EventBus.getDefault().post(new PostEvent(bean));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Received binding.");
        return null;
    }

}
