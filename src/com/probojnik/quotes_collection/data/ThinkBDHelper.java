package com.probojnik.quotes_collection.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.probojnik.quotes_collection.R;


/**
 * @author Stanislav Shamji
 */
public class ThinkBDHelper  extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "think.db";

    public static final String ID_COLUMN = "_id";

    public static final String THINK_NAME_TABLE  = "think";
    public static final String THINK_COLUMN_QUOTE = "quote";
    public static final String THINK_COLUMN_AUTHOR = "author_id";

    public static final String AUTHORS_NAME_TABLE = "authors";
    public static final String AUTHORS_COLUMN_NAME  = "name";
    public static final String AUTHORS_COLUMN_DESCRIPTION = "description";
    public static final String AUTHORS_COLUMN_PHOTO = "photo";
    public static final String AUTHORS_COLUMN_CATEGORY = "category";

    public static final int CATEGORY_ENTREPRENEURS = 1;
    public static final int CATEGORY_LEADERS = 3;
    public static final int CATEGORY_WRITERS = 2;

    public static final String PHOTO_JOBS = "steve";
    public static final String PHOTO_JOBS_2 = "jobs";
    public static final String PHOTO_DORSEY = "dorsey";
    public static final String PHOTO_ZUCKERBERG = "zuckerberg";
    public static final String PHOTO_GATES = "gates";
    public static final String PHOTO_FORD = "ford";
    public static final String PHOTO_MANDELA = "mandela";

    public ThinkBDHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String authors_tabla_create = "CREATE TABLE " + AUTHORS_NAME_TABLE + "( " +
        		ID_COLUMN + " INTEGER PRIMARY KEY, " +
        		AUTHORS_COLUMN_NAME + " TEXT, " +
        		AUTHORS_COLUMN_DESCRIPTION + " TEXT, " +
        		AUTHORS_COLUMN_PHOTO + " TEXT, " +
        		AUTHORS_COLUMN_CATEGORY + " INTEGER " +
                ");";

        String think_tabla_create = "CREATE TABLE " + THINK_NAME_TABLE + "( " +
        		ID_COLUMN + " INTEGER PRIMARY KEY, " +
        		THINK_COLUMN_QUOTE + " TEXT, " +
        		THINK_COLUMN_AUTHOR + " INTEGER, " +
                "FOREIGN KEY(" + THINK_COLUMN_AUTHOR + ") REFERENCES " + AUTHORS_NAME_TABLE + "(" + ID_COLUMN + "));";

        db.execSQL(authors_tabla_create);
        db.execSQL(think_tabla_create);

        long id_autor;

        ContentValues content = new ContentValues();
        content.put(AUTHORS_COLUMN_NAME, "Steve Jobs");
        content.put(AUTHORS_COLUMN_DESCRIPTION, "Apple Founder");
        content.put(AUTHORS_COLUMN_PHOTO, PHOTO_JOBS_2);
        content.put(AUTHORS_COLUMN_CATEGORY, CATEGORY_ENTREPRENEURS);

        id_autor = db.insert(AUTHORS_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Your time is limited, so do not waste it living someone else's life [...] Do not let the noise of others' opinions off your own inner voice.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "You can not ask consumers what they want and then pretend to give. In the time that has been manufacturing it, they will want something new.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "We were not the first, but we will be the best.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Innovation has nothing to do with how much money do you have to research and development. When Apple released the Mac, IBM was spending at least a hundred times more in that activity. It's not about money. It is the people you have, how is led, and how much you accomplished.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "I am convinced that half of what separates the successful entrepreneurs who do not succeed is perseverance");
        db.insert(THINK_NAME_TABLE, null, content);


        content.clear();
        content.put(AUTHORS_COLUMN_NAME, "Jack Dorsey");
        content.put(AUTHORS_COLUMN_DESCRIPTION, "Fundador of Twitter");
        content.put(AUTHORS_COLUMN_PHOTO, PHOTO_DORSEY);
        content.put(AUTHORS_COLUMN_CATEGORY, CATEGORY_ENTREPRENEURS);

        id_autor = db.insert(AUTHORS_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "I do not know if you necessarily try to solve problems. If you have an idea for something you think should be in the world then you do, and may not solve the problem of no one else but only yours. Or your desire. I think you should be selfish in that regard. I want to see something in the world, and if I can attract more people to think the same, eventually you will create a team and potentially a business around it. We had to solve a problem, but a desire");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(AUTHORS_COLUMN_NAME, "Mark Zuckerberg");
        content.put(AUTHORS_COLUMN_DESCRIPTION, "Founder of Facebook");
        content.put(AUTHORS_COLUMN_PHOTO, PHOTO_ZUCKERBERG);
        content.put(AUTHORS_COLUMN_CATEGORY, CATEGORY_ENTREPRENEURS);

        id_autor = db.insert(AUTHORS_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "The biggest risk is not taking any chances. In a rapidly changing world, the only strategy that ensures fail is not taking risks.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "We do not build services to make money, we make money to build better services.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "I spent a lot of time thinking about how to build it and how to achieve success. I think that's more interesting work than any other work.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(AUTHORS_COLUMN_NAME, "Bill Gates");
        content.put(AUTHORS_COLUMN_DESCRIPTION, "Founder of Microsoft");
        content.put(AUTHORS_COLUMN_PHOTO, PHOTO_GATES);
        content.put(AUTHORS_COLUMN_CATEGORY, CATEGORY_ENTREPRENEURS);

        id_autor = db.insert(AUTHORS_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Fine to celebrate success but it is more important to heed the lessons of failure.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "If you think your teacher is tough, wait till you get a boss. That it does not have teaching vocation nor the patience required.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "You will not win 5,000 euros per month just after leaving university, and vice president will not be anything until, with your effort, you will earn both.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Before you were born, your parents were not as boring as they are now. Got that way paying your bills, cleaning your clothes and listening to your complaints. So, before you begin your fight for the virgin forests contaminated by the generation of your parents, start cleaning things the way your own life, starting with your room.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(AUTHORS_COLUMN_NAME, "Henry Ford");
        content.put(AUTHORS_COLUMN_DESCRIPTION, "Founder of Ford Motors");
        content.put(AUTHORS_COLUMN_PHOTO, PHOTO_FORD);
        content.put(AUTHORS_COLUMN_CATEGORY, CATEGORY_ENTREPRENEURS);

        id_autor = db.insert(AUTHORS_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Money does not change men, it unmasks simply. If a man is naturally selfish, greedy and arrogant, this manifests with money.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "True progress is putting technology to everyone.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Obstacles are those frightful things you see when you take your eyes off your goal.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Whether you think you can, or if you think you can not, you're right.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "The secret to my success is to pay like prodigal and sold as if it were in bankruptcy.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "Failure is sometimes more fruitful than success.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "If there is a secret to success lies in the ability to appreciate the point of view of others and see things from that point of view as well as his own.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(AUTHORS_COLUMN_NAME, "Nelson Mandela");
        content.put(AUTHORS_COLUMN_DESCRIPTION, "South African President and Nobel Peace Prize");
        content.put(AUTHORS_COLUMN_PHOTO, PHOTO_MANDELA);
        content.put(AUTHORS_COLUMN_CATEGORY, CATEGORY_LEADERS);

        id_autor = db.insert(AUTHORS_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "If you want to make peace with your enemy, you have to work with. Then he becomes your partner.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "If you want to make peace with your enemy, you have to work with. Then he becomes your partner.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "The greatest glory is not never falling, but in rising forever.");
        db.insert(THINK_NAME_TABLE, null, content);

        content.clear();
        content.put(THINK_COLUMN_AUTHOR, id_autor);
        content.put(THINK_COLUMN_QUOTE, "No one is born hating another person because of the color of their skin, or their origin, or religion.");
        db.insert(THINK_NAME_TABLE, null, content);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly())
            db.execSQL("PRAGMA foreign_keys=ON");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public static int obtenerPhoto(String name){

        if (PHOTO_JOBS.equals(name))
            return R.drawable.steve;

        if (PHOTO_JOBS_2.equals(name))
            return R.drawable.jobs;

        if (PHOTO_DORSEY.equals(name))
            return R.drawable.dorsey;

        if (PHOTO_ZUCKERBERG.equals(name))
            return R.drawable.zuckerberg;

        if (PHOTO_GATES.equals(name))
            return R.drawable.gates;

        if (PHOTO_FORD.equals(name))
            return R.drawable.ford;

        if (PHOTO_MANDELA.equals(name))
            return R.drawable.mandela;

         return R.drawable.steve;
    }

}