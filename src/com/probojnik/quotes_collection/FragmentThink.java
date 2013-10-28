package com.probojnik.quotes_collection;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.probojnik.quotes_collection.data.Think;
import com.probojnik.quotes_collection.data.ThinkBDHelper;

/**
 * @author Stanislav Shamji
 */

@SuppressLint("ValidFragment")
public class FragmentThink extends SherlockFragment {

    private static final String THINK_KEY = "mensaje";
    private static final String AUTHOR_KEY = "autor";

    private String message;
    private String author;
    private String author_description;
    private String photo;

	public FragmentThink(){
        message = "Select sentences";
    }
    
	public FragmentThink(String mensaje) {

        this.message = mensaje;
    }
    
	public FragmentThink(Think pensamiento){
        message = pensamiento.getQuote();
        author = pensamiento.getAuthor_name();
        author_description = pensamiento.getAuthor_description();
        photo = pensamiento.getAuthor_photo();
    }

    public void colocar_pensamiento(Think p, View pensamiento_view){
        TextView texto = (TextView) pensamiento_view.findViewById(R.id.think_body);
        texto.setText("\"" + message + "\"");

        if (author != null ){
            texto = (TextView) pensamiento_view.findViewById(R.id.author);
            texto.setText("- " + author + ",");
        }

        if ( author_description != null ){
            texto = (TextView) pensamiento_view.findViewById(R.id.author);
            texto.setText(author_description);
        }

        if (photo != null) {
            ImageView i = (ImageView) pensamiento_view.findViewById(R.id.photo);
            i.setImageResource(ThinkBDHelper.obtenerPhoto(photo));
            i.setAdjustViewBounds(true);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View pensamiento_view = inflater.inflate(R.layout.fragment_think, container, false);

        Think p = new Think();
        p.setAuthor_photo(photo);
        p.setQuote(message);
        p.setAuthor_description(author_description);
        p.setAuthor_name(author);

        colocar_pensamiento(p, pensamiento_view);

        return pensamiento_view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(THINK_KEY, message);
        outState.putString(AUTHOR_KEY, author);
    }
}