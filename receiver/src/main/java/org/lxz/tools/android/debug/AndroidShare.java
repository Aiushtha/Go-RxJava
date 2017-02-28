package org.lxz.tools.android.debug;


import android.content.Context;
import android.content.SharedPreferences;

public class AndroidShare {

    public interface IExcute {
        void put(SharedPreferences.Editor localEditor);

        void get(SharedPreferences settings);
    }

    private String filename;
    private Context context;
    private SharedPreferences settings;


    public AndroidShare(Context context,String filename) {
        super();
        this.filename = filename;
        this.context=context;
        this.settings = context.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
    }

    public  void put(String key,String value) {

        SharedPreferences.Editor localEditor = settings.edit();
        localEditor.putString(key, value);

        localEditor.commit();
    }

    public  String get(String key) {
        SharedPreferences.Editor localEditor = settings.edit();
        String result =  settings.getString(key,null);
        return result;
    }
    public  String get(String key,String defaultvalue) {
        SharedPreferences.Editor localEditor = settings.edit();
        String result =  settings.getString(key,null);

        if(result==null)
        {
            return defaultvalue;
        }
        return result;
    }

    public SharedPreferences operation(Context c, String filename, IExcute excute) {
        SharedPreferences settings = c.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor localEditor = settings.edit();

        if (excute != null) {
            excute.put(localEditor);
            localEditor.commit();
            excute.get(settings);
        }
        localEditor.commit();
        return settings;
    }

    public void clear(Context c, String filename) {
        SharedPreferences settings = c.getSharedPreferences(filename,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor localEditor = settings.edit();
        localEditor.clear()
                .commit();
    }



}
