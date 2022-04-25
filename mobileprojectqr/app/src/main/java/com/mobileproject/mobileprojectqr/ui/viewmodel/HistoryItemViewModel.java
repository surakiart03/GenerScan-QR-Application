package com.mobileproject.mobileprojectqr.ui.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.BaseObservable;

import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.mobileproject.mobileprojectqr.R;
import com.mobileproject.mobileprojectqr.database.AppRepository;
import com.mobileproject.mobileprojectqr.database.HistoryItem;
import com.mobileproject.mobileprojectqr.ui.activities.ResultActivity;

import java.text.DateFormat;
import java.util.Date;


public class HistoryItemViewModel extends BaseObservable {

    private Context context;
    private HistoryItem entry;
    private ParsedResult parsed;
    private boolean disabled = false;

    public HistoryItemViewModel(Context context, HistoryItem entry) {
        this.context = context;
        this.entry = entry;
        this.parsed = ResultParser.parseResult(entry.getResult());
    }

    public View.OnClickListener onClickItem() {
        return v -> {
            if(!isDisabled()) {
                ResultActivity.startResultActivity(context, entry);
            }
        };
    }

    public View.OnLongClickListener onLongClickItem() {
        return v -> {
            if(!isDisabled()) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder.setTitle(R.string.dialog_history_delete_title);
                dialogBuilder.setMessage(R.string.dialog_history_delete_message);
                dialogBuilder.setPositiveButton(R.string.delete, (dialog, which) -> {
                    disabled = true;
                    AppRepository.getInstance(context).deleteHistoryEntry(entry);
                });
                dialogBuilder.setNegativeButton(android.R.string.cancel, null);
                dialogBuilder.create().show();
                return true;
            }
            return false;
        };
    }

    public String getTimestamp() {
        if(entry.getTimestamp() != 0) {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.format(new Date(entry.getTimestamp()));
        }
        return "";
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public String getType() {
        if(parsed.getType().name() != null) {
            return parsed.getType().name();
        }
        return "";
    }

    public String getText() {
        return parsed.getDisplayResult();
    }


}
