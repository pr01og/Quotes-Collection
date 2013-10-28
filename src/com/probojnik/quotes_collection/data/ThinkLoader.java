package com.probojnik.quotes_collection.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Shamji
 */
public class ThinkLoader  extends AsyncTaskLoader<List<Think>> {

    List<Think> mThink;
    private int category;

    public ThinkLoader(Context ctx, int category){
        super(ctx);

        this.category = category;
    }

    @Override
    public List<Think> loadInBackground() {

        Log.d("thinkLoader", "We proceed to load the information");

        SQLiteDatabase db = new ThinkBDHelper(getContext()).getReadableDatabase();
        ArrayList<Think> think = new ArrayList<Think>();
        Think nuevo;

        String query = "SELECT quote, name, description, name, think._id FROM think, authors WHERE authors._id=author_id"; // photo

        if (category != 0)
            query += " AND categoria=" + category;

        Cursor c = db.rawQuery(query, null);

        Log.d("Loader", "The query was executed " + query);

        c.moveToFirst();

        while (!c.isAfterLast()){

            nuevo = new Think();

            nuevo.setQuote(c.getString(0));
            nuevo.setAuthor_name(c.getString(1));
            nuevo.setAuthor_description(c.getString(2));
            nuevo.setAuthor_photo(c.getString(3));
            nuevo.setId(c.getLong(4));

            think.add(nuevo);

            c.moveToNext();

        }

        db.close();

        return think;
    }

    @Override
    public void deliverResult(List<Think> data) {
        super.deliverResult(data);
    }
}
