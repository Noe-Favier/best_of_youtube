package fr.noais.byt.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fr.noais.byt.models.VideoModel;

@Dao
public interface VideoDao {
    @Query("SELECT * FROM video WHERE id = :id")
    public VideoModel find(long id);
    @Query("SELECT * FROM video")
    public List<VideoModel> list();
    @Insert
    public void add(VideoModel... videoModels);
    @Update
    public void update(VideoModel... videoModels);
    @Delete
    public void delete(VideoModel... videoModels);
}
