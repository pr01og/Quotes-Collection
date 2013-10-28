package com.probojnik.quotes_collection.data;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * @author Stanislav Shamji
 */
public class Think implements Parcelable{

    private long id;
    private String quote;
    private String author_name;
    private String author_description;
    private String author_photo;

    public Think(){

    }

    public Think(Parcel in){
        readFromParcel(in);
    }

 

    public String toString(){
        return "the quote is: " + getQuote();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public static final Parcelable.Creator<Think> CREATOR= new Parcelable.Creator<Think>() {
    		public Think createFromParcel(Parcel in) {
    				return new Think(in);
    		}
    		
    		public Think[] newArray(int size) {
                return new Think[size];
            }
        };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(id);
        dest.writeString(quote);
        dest.writeString(author_name);
        dest.writeString(author_description);
        dest.writeString(author_photo);
    }

    public void readFromParcel(Parcel in){
        id = in.readLong();
        quote = in.readString();
        author_name = in.readString();
        author_description = in.readString();
        author_photo = in.readString();
    }

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getAuthor_description() {
		return author_description;
	}

	public void setAuthor_description(String author_description) {
		this.author_description = author_description;
	}

	public String getAuthor_photo() {
		return author_photo;
	}

	public void setAuthor_photo(String author_photo) {
		this.author_photo = author_photo;
	}
}