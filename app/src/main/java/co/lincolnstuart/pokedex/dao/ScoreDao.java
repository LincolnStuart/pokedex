package co.lincolnstuart.pokedex.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import co.lincolnstuart.pokedex.model.Score;

@Dao
public interface ScoreDao {

    @Insert
    void insertScore(Score score);

    @Update
    void updateScore(Score score);

    @Query("SELECT * FROM Score WHERE pk = 1")
    Score getScore();

}
