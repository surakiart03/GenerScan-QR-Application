package com.mobileproject.mobileprojectqr.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface HistoryDao {
    @Delete
    void delete(HistoryItem entry);

    @Query("SELECT * FROM Histories ORDER BY _id DESC, timestamp DESC")
    List<HistoryItem> getAll();

    @Query("SELECT * FROM Histories ORDER BY _id DESC, timestamp DESC")
    LiveData<List<HistoryItem>> getAllLiveData();

    @Insert
    void insert(HistoryItem entry);

    @Query("DELETE FROM Histories")
    void deleteAll();

    @Update
    void update(HistoryItem item);

}
