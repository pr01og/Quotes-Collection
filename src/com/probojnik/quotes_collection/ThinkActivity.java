package com.probojnik.quotes_collection;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import com.probojnik.quotes_collection.data.Think;

/**
 * @author Stanislav Shamji
 */
public class ThinkActivity extends SherlockFragmentActivity {

	public static final String THINK_OBJECT = "com.probojnik.quotes_collection.THINK_OBJECT";
    public static final String THINK_MESSAGE = "com.probojnik.quotes_collection.THINK_MESSAGE";
    public static final String THINK_AUTHOR = "com.probojnik.quotes_collection.THINK_AUTHOR";
    public static final String THINK_AUTHOR_DESCRIPTION = "com.probojnik.quotes_collection.THINK_AUTHOR_DESCRIPTION";
    public static final String THINK_AUTHOR_PHOTO = "com.probojnik.quotes_collection.THINK_AUTHOR_PHOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_think);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
        }

        if (savedInstanceState == null){
            Intent i = getIntent();

            Think pensamiento = i.getExtras().getParcelable(THINK_OBJECT);
            
            FragmentThink pensamientoFr = new FragmentThink(pensamiento);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.think, pensamientoFr);
            ft.commit();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}