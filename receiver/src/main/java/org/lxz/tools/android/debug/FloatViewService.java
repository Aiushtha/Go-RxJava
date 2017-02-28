package org.lxz.tools.android.debug;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import widget.FloatView;

public class FloatViewService extends Service {

    private FloatView mFloatView;

    @Override
    public IBinder onBind(Intent intent) {
        return new FloatViewServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatView = new FloatView(this);
        showFloat();
    }

    public void showFloat() {
        if ( mFloatView != null ) {
            mFloatView.show();
        }
    }

    public void hideFloat() {
        if ( mFloatView != null ) {
            mFloatView.hide();
        }
    }

    public void destroyFloat() {
        if ( mFloatView != null ) {
            mFloatView.destroy();
        }
        mFloatView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyFloat();
    }

    public class FloatViewServiceBinder extends Binder {
        public FloatViewService getService() {
            return FloatViewService.this;
        }
    }
}
