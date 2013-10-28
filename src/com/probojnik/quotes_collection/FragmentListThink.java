package com.probojnik.quotes_collection;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;

import com.probojnik.quotes_collection.data.Think;
import com.probojnik.quotes_collection.data.ThinkLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Stanislav Shamji
 */
@SuppressLint("ValidFragment")
public class FragmentListThink extends SherlockListFragment implements LoaderManager.LoaderCallbacks<List<Think>> {

    private static final String _ID = "FragmentListThink";
    private static final int THINK_LOADER_ID = 256;

    private static final String QUOTE_KEY = "quote";
    private static final String AUTHOR_KEY = "author";
    private static final String DESCRIPTION_AUTHOR_KEY = "description_author";
    private static final String PHOTO_AUTHOR_KEY = "photo";

    private static final String CATEGORY_KEY = "category";
    private static final String SELECTED_KEY = "selected";
    private static final String THINK_KEY = "think";


    private List<Think> think;
    private boolean screen_shared = false;
    private ThinkAdapter mAdapter;
    private int selected = -1;

    private int category = 0;
    private long id_think  = -1;

    public FragmentListThink(){
        this.category = 0;
    }

    public FragmentListThink(int categoria){
        this.category = categoria;
    }

    public FragmentListThink(int categoria, long id_pensamiento){
        this.category = categoria;
        this.id_think = id_pensamiento;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Think> list_view_elements = new ArrayList<Think>();
        Think ps = null;

        getListView().setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        getListView().setBackgroundColor(getResources().getColor(R.color.default_color));

        mAdapter = new ThinkAdapter(getActivity(), list_view_elements);

        setListAdapter(mAdapter);

        View fragment_think = getActivity().findViewById(R.id.detail);
        screen_shared = fragment_think != null && fragment_think.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null){

            category = savedInstanceState.getInt(CATEGORY_KEY);
            selected = savedInstanceState.getInt(SELECTED_KEY);
            ps = savedInstanceState.getParcelable(THINK_KEY);

            if (ps != null ){
                change_think(-1, ps);
            }

        }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle b = new Bundle();
        b.putInt(CATEGORY_KEY, this.category);

        LoaderManager lm = getLoaderManager();

        lm.initLoader(THINK_LOADER_ID, b, this).forceLoad();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (screen_shared)
            v.setSelected(true);

        change_think(position, null);

    }

    @Override
    public Loader<List<Think>> onCreateLoader(int i, Bundle b) {

        Log.d(_ID, "Se creo el Loader");

        return (Loader<List<Think>>) new ThinkLoader(getActivity(), b.getInt(CATEGORY_KEY));
    }

    @Override
    public void onLoadFinished(Loader<List<Think>> listLoader, List<Think> think) {

        Log.d(_ID, "Se recibieron pensamiento " + think.size());

        mAdapter.clear();
        for (Think pensamiento : think) {
            mAdapter.add(pensamiento);
        }

        mAdapter.notifyDataSetChanged();

        this.think =  think;

    }

    @Override
    public void onLoaderReset(Loader<List<Think>> listLoader) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (selected != -1 ){

            Think pensamiento = think.get(selected);

            outState.putInt(SELECTED_KEY, selected);
            outState.putInt(CATEGORY_KEY, category);
            outState.putParcelable(THINK_KEY, pensamiento);

            pensamiento = outState.getParcelable(THINK_KEY);

        }

    }

    private void change_think(int pos, Think p){

        Think pn = null;

        if (pos != -1 )
            pn = think.get(pos);

        if ( p != null){
            pn = p;
        }

        if (screen_shared){
            FragmentThink inicio = new FragmentThink(pn);

            FragmentTransaction ft = getSherlockActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.detail, inicio);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else{
            Intent intent = new Intent(getSherlockActivity(), ThinkActivity.class);
            intent.putExtra(ThinkActivity.THINK_OBJECT, pn);
            
            startActivity(intent);
        }

        selected = pos;
    }
}