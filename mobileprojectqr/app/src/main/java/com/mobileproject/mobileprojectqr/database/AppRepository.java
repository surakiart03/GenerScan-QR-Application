package com.mobileproject.mobileprojectqr.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AppRepository {

    private static AppRepository INSTANCE = null;

    private AppDatabase appDatabase;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public synchronized static AppRepository getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new AppRepository(context);
        }
        return INSTANCE;
    }

    private AppRepository(Context context) {
        this.appDatabase = AppDatabase.getInstance(context);
    }

    public void deleteHistoryEntry(HistoryItem entry) {
        executor.execute(() -> appDatabase.historyDao().delete(entry));
    }

    public void insertHistoryEntry(HistoryItem entry) {
        executor.execute(() -> appDatabase.historyDao().insert(entry));
    }

    public void updateHistoryEntry(HistoryItem entry) {
        executor.execute(() -> appDatabase.historyDao().update(entry));
    }

    public LiveData<List<HistoryItem>> getHistoryEntriesLiveData() {
        return appDatabase.historyDao().getAllLiveData();
    }

    public void deleteAllHistoryEntries() {
        executor.execute(() -> appDatabase.historyDao().deleteAll());
    }
}
