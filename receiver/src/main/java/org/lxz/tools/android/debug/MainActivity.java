package org.lxz.tools.android.debug;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private FloatingActionButton fab;
    private AutoCompleteTextView edit;
    private Button btn_clear;
    private Button btn_type;
    private TextView label_type;
    private Button btn_level;
    private TextView label_level;
    private RecyclerView mRecyclerView;
    private MessageBeanAdapter mAdapter;
    private RecyclerView.ItemDecoration divider;
    private AutoCompleteTextViewAdapter autoAdaper;

    private String key_history="history";
    private AndroidShare share;
    private ArrayAdapter<String> arrayAdapter;
    private Toolbar toolbar;

    public void onEventMainThread(PostEvent event) {
        mAdapter.notifyDataSetChanged();
    }


    public List<String> autoSearchHistoryList;

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        share=new AndroidShare(this,"key_history");
        setContentView(R.layout.activity_main);
        setOverflowShowingAlways();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edit = (AutoCompleteTextView) toolbar.findViewById(R.id.edit);
        btn_clear = (Button) toolbar.findViewById(R.id.btn_clear);
        btn_type = (Button) toolbar.findViewById(R.id.btn_type);
        label_type = (TextView) toolbar.findViewById(R.id.tv_type);
        label_level = (TextView) toolbar.findViewById(R.id.tv_level);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);




        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.getText().clear();
                btn_clear.setVisibility(View.GONE);
                MessageCache.clearCurrentkeyWord();
                mAdapter.notifyDataSetChanged();

            }
        });

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext()).setTitle("指定type").setIcon(
                        android.R.drawable.ic_dialog_info).setSingleChoiceItems(
                        MessageBean.TYPES, -1,
                                                new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                label_type.setText(MessageBean.TYPES[which]);
                                MessageCache.setCurrentType(MessageBean.TYPES[which]);
                                MessageCache.buildSearch();
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("全选", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MessageCache.setCurrentType(null);
                        MessageCache.buildSearch();
                        mAdapter.notifyDataSetChanged();
                        label_type.setText("");
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        btn_level = (Button) findViewById(R.id.btn_level);
        btn_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext()).setTitle("指定level").setIcon(
                        android.R.drawable.ic_dialog_info).setSingleChoiceItems(
                        MessageBean.LEVELS, -1,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                label_level.setText(MessageBean.LEVELS[which].toLowerCase());
                                MessageCache.setCurrentLevel(MessageBean.LEVELS[which]);
                                MessageCache.buildSearch();
                                mAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("全选", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MessageCache.clearCurrentLevel();
                        MessageCache.buildSearch();
                        mAdapter.notifyDataSetChanged();
                        label_level.setText("");
                        dialog.dismiss();
                    }
                }).show();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setBackgroundResource(R.drawable.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View f) {
                MessageCache.clearAll();
                mAdapter.notifyDataSetChanged();


            }
        });






       initDrawer();
       initRecycler();
       initEdit();

    }

    private void initDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        divider = new EmptyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, Color.TRANSPARENT);
        mAdapter = new MessageBeanAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initEdit() {
        String history=share.get(key_history);
        if(history==null)
        {
            autoSearchHistoryList=new LinkedList();
        }else
        {

            autoSearchHistoryList= new Gson().fromJson(history,new TypeToken<List<String>>() {}.getType());
        }

        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (edit.getText().toString().equals("")) {
                        Toast.makeText(v.getContext(), "not empty", Toast.LENGTH_LONG);
                        return false;
                    } else {
                        search();
                    }
                    return true;
                }
                return false;
            }
        });
        edit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.actionbar_search_icon, 0, 0, 0);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if ("".equals(edit.getText().toString())) {
                    btn_clear.setVisibility(View.GONE);
                } else {
                    btn_clear.setVisibility(View.VISIBLE);
                }

            }
        });

        edit.setThreshold(-1);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(edit.getText().toString())) {
                    edit.showDropDown();
                }


            }
        });

        arrayAdapter = new ArrayAdapter<String>(this, R.layout.adapter_auto_textview,android.R.id.text1, autoSearchHistoryList);
        edit.setAdapter(arrayAdapter);
        edit.addTextChangedListener(new TextWatcher() {


            public void afterTextChanged(Editable editable) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {


            }

        });

        edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search();
            }
        });

    }


    public void search(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        MessageCache.setCurrentkeyWord(edit.getText().toString());
        MessageCache.buildSearch();
        mAdapter.notifyDataSetChanged();
        if (autoSearchHistoryList.contains(edit.getText().toString())) {
            autoSearchHistoryList.remove(edit.getText().toString());
        }
        autoSearchHistoryList.add(0, edit.getText().toString());
        share.put(key_history, new Gson().toJson(autoSearchHistoryList));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            mAdapter = new MessageBeanAdapter(MainActivity.this);
            mAdapter.setLayoutId(R.layout.adapter_message_list_item);
            mRecyclerView.removeItemDecoration(divider);
            mRecyclerView.addItemDecoration(divider = new EmptyDividerItemDecoration(MainActivity.this, EmptyDividerItemDecoration.VERTICAL_LIST, Color.TRANSPARENT));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            mRecyclerView.setAdapter(mAdapter);

        } else if (id == R.id.nav_gallery) {
            mAdapter = new MessageBeanAdapter(MainActivity.this);
            mAdapter.setLayoutId(R.layout.adapter_message_grid_item);
            mRecyclerView.removeItemDecoration(divider);
            mRecyclerView.addItemDecoration(divider = new EmptyDividerItemDecoration(MainActivity.this, EmptyDividerItemDecoration.BOTH, Color.TRANSPARENT));
            mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
            mRecyclerView.setAdapter(mAdapter);

        } else if (id == R.id.nav_slideshow) {
            this.startService(new Intent(this, FloatViewService.class));
        } else if (id == R.id.nav_manage) {
            this.stopService(new Intent(this, FloatViewService.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
