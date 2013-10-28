package com.probojnik.quotes_collection;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.probojnik.quotes_collection.tasks.ThinkService;

/**
 * @author Stanislav Shamji
 */
public class MainActivity extends DrawerActivity {

    public static final String CATEGORY_KEY = "probojnik.quotes_collection.CATEGORY";
    public static final String THINK_ID_KEY= "probojnik.quotes_collection.THINKING_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentListThink list = new FragmentListThink();
        Intent i = getIntent();

        int category;
        long think_id;

        category = i.getIntExtra(CATEGORY_KEY, -1);
        think_id = i.getLongExtra(THINK_ID_KEY, -1);


        if (category != -1)
            list = new FragmentListThink(category, think_id);
        

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.list, list);
        ft.commit();


        Intent is = new Intent(this, ThinkService.class);
        startService(is);

    }

}
