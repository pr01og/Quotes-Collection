package com.probojnik.quotes_collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.probojnik.quotes_collection.data.Think;
import com.probojnik.quotes_collection.data.ThinkBDHelper;

import java.util.List;

/**
 * @author Stanislav Shamji
 */
public class ThinkAdapter extends ArrayAdapter<Think> {

    public ThinkAdapter(Context context, List<Think> objects) {
        super(context, R.layout.think_list_view, objects);
    }

    @Override
    public View getView(int position, View vista, ViewGroup parent) {

        if (vista == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.think_list_view, parent, false);
        }

        Think p = getItem(position);

        TextView texto = (TextView) vista.findViewById(R.id.think_preview);
        texto.setText(p.getQuote().substring(0,23) + "...");

        texto = (TextView) vista.findViewById(R.id.author_surname);
        String[] names = p.getAuthor_name().split(" ");

        texto.setText(names[names.length-1]);

        ImageView imagen = (ImageView) vista.findViewById(R.id.photo_list_view);
        imagen.setImageResource(ThinkBDHelper.obtenerPhoto(p.getAuthor_photo()));

        return vista;
    }
}