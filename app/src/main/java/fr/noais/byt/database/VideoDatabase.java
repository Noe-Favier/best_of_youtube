package fr.noais.byt.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.noais.byt.dao.VideoDao;
import fr.noais.byt.models.VideoModel;

@Database(entities = {VideoModel.class}, version = 1)
public abstract class VideoDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "video";

    public static VideoDatabase getDb(Context context) {
        return Room.databaseBuilder(context, VideoDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
    }

    public abstract VideoDao videoDao();
}
