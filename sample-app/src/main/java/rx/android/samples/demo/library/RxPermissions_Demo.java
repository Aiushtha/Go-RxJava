package rx.android.samples.demo.library;


import android.Manifest;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.IOException;

import rx.android.samples.R;
import rx.android.samples.demo.BaseFragment;
import rx.functions.Action0;
import rx.functions.Action1;


/**
 * Created by Lin on 2017/2/27.
 */

public class RxPermissions_Demo extends BaseFragment {

    private SurfaceView surfaceView;
    public Camera camera;
    public String TAG = this.getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rxpermissions, null);
        RxPermissions rxPermissions = new RxPermissions(getActivity());
        rxPermissions.setLogging(true);
        surfaceView = (SurfaceView) view.findViewById(R.id.surfaceView);

        RxView.clicks(view.findViewById(R.id.enableCamera))
                // Ask for permissions when button is clicked
                .compose(rxPermissions.ensureEach(Manifest.permission.CAMERA))
                .subscribe(new Action1<Permission>() {
                               @Override
                               public void call(Permission permission) {
                                   Log.i(TAG, "Permission result " + permission);
                                   if (permission.granted) {
                                       releaseCamera();
                                       camera = Camera.open(0);
                                       try {
                                           camera.setPreviewDisplay(surfaceView.getHolder());
                                           camera.startPreview();
                                       } catch (IOException e) {
                                           Log.e(TAG, "Error while trying to display the camera preview", e);
                                       }
                                   } else if (permission.shouldShowRequestPermissionRationale) {
                                       // Denied permission without ask never again
                                       Toast.makeText(getActivity(),
                                               "Denied permission without ask never again",
                                               Toast.LENGTH_SHORT).show();
                                   } else {
                                       // Denied permission with ask never again
                                       // Need to go to the settings
                                       Toast.makeText(getActivity(),
                                               "Permission denied, can't enable the camera",
                                               Toast.LENGTH_SHORT).show();
                                   }
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable t) {
                                Log.e(TAG, "onError", t);
                            }
                        },
                        new Action0() {
                            @Override
                            public void call() {
                                Log.i(TAG, "OnComplete");
                            }
                        });

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }


}
