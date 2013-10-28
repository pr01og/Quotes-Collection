package com.probojnik.quotes_collection;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

/**
 * @author Stanislav Shamji
 */
public class DrawerActivity extends SherlockFragmentActivity {

    ArrayAdapter mAdapter;
    ActionBarDrawerToggle mDrawerToggle;
    ListView optionsDrawer;
    String[] options;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        options = getResources().getStringArray(R.array.categories);

        mAdapter = new ArrayAdapter(this, R.layout.option_view, options );

        optionsDrawer = (ListView) findViewById(R.id.nav_drawer);
        optionsDrawer.setAdapter(mAdapter);
        optionsDrawer.setOnItemClickListener(new DrawerItemSeleccionadoEscucha());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getSupportActionBar().setTitle("Categorias");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                int i = optionsDrawer.getCheckedItemPosition();

                if (i != ListView.INVALID_POSITION)
                    getSupportActionBar().setTitle(options[i]);
                else
                    getSupportActionBar().setTitle("Todos");

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if ( savedInstanceState == null ){
            cambiarContenido(0);
            getSupportActionBar().setTitle(options[0]);
            optionsDrawer.setSelection(0);
        }

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(optionsDrawer))
                    mDrawerLayout.closeDrawer(optionsDrawer);
                else
                    mDrawerLayout.openDrawer(optionsDrawer);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cambiarContenido(int seleccionado){

        if (seleccionado >= 0){
            FragmentListThink fr = new FragmentListThink(seleccionado);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.list, fr);
            ft.commit();
        }

    }

    private class DrawerItemSeleccionadoEscucha implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
            view.setSelected(true);
            mDrawerLayout.closeDrawer(optionsDrawer);
            cambiarContenido(pos);
        }
    }
}