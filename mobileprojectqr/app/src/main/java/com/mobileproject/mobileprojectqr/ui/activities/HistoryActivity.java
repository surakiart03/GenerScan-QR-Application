package com.mobileproject.mobileprojectqr.ui.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileproject.mobileprojectqr.R;
import com.mobileproject.mobileprojectqr.database.AppRepository;
import com.mobileproject.mobileprojectqr.ui.adapter.HistoryAdapter;
import com.mobileproject.mobileprojectqr.ui.viewmodel.HistoryViewModel;


public class HistoryActivity extends AppCompatActivity {

    private HistoryAdapter mHistoryAdapter;
    private RecyclerView mHistoryRecyclerView;
    private HistoryViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        mHistoryAdapter = new HistoryAdapter(this);

        mHistoryRecyclerView = findViewById(R.id.activity_history_list);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHistoryRecyclerView.setAdapter(mHistoryAdapter);

        mViewModel.getHistoryItems().observe(this, (list) -> {
            mHistoryAdapter.setHistoryEntries(list);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_clear:
                new AlertDialog.Builder(this)
                        .setTitle(R.string.D_all)
                        .setMessage(R.string.all_records)
                        .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AppRepository.getInstance(HistoryActivity.this).deleteAllHistoryEntries();
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .create().show();
                return true;
            default:
                return false;
        }
    }


}

