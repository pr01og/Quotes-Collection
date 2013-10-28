package com.probojnik.quotes_collection.tasks;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.probojnik.quotes_collection.ThinkActivity;
import com.probojnik.quotes_collection.data.Think;
import com.probojnik.quotes_collection.data.ThinkBDHelper;


/**
 * @author Stanislav Shamji
 */
public class ThinkService extends IntentService {

    private static final int MIN_WAIT = 1;
    private static final int MILLISECONDS_IN_MIN = 60*1000;

    public ThinkService() {
        super("ThinkScheduleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        long tiempo_espera = System.currentTimeMillis() + MIN_WAIT*MILLISECONDS_IN_MIN;
        int n, i;
        Think p = new Think();

        synchronized (this){
            try{

                SQLiteDatabase db = new ThinkBDHelper(this).getReadableDatabase();

                String query = "SELECT quote, name, description, photo FROM think, authors WHERE authors._id=author_id";

                Cursor c = db.rawQuery(query, null);

                i = (int) (Math.random() * c.getCount()-1 );
                c.moveToPosition(i);
                p.setQuote(c.getString(0));
                p.setAuthor_name(c.getString(1));
                p.setAuthor_photo(c.getString(3));

                db.close();

                Log.d("Service", p.toString());

                wait(tiempo_espera - System.currentTimeMillis());

                Intent intent_pensamiento = new Intent(this, ThinkActivity.class);
                intent_pensamiento.putExtra(ThinkActivity.THINK_OBJECT, p);
                
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
                stackBuilder.addNextIntent(intent_pensamiento);
                
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(ThinkBDHelper.obtenerPhoto(p.getAuthor_photo()))
                        .setContentTitle(p.getAuthor_name())
                        .setContentText(p.getQuote())
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true);
                
                

                NotificationManager nmanager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                nmanager.notify(24, mBuilder.build());


            }catch (Exception e){}
        }


    }
}