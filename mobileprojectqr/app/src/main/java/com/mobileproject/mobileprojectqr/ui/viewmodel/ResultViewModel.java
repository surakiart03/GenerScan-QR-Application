package com.mobileproject.mobileprojectqr.ui.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.mobileproject.mobileprojectqr.R;
import com.mobileproject.mobileprojectqr.database.AppRepository;
import com.mobileproject.mobileprojectqr.database.HistoryItem;
import com.mobileproject.mobileprojectqr.helpers.Utils;

public class ResultViewModel extends AndroidViewModel {

    private BarcodeResult currentBarcodeResult = null;

    public HistoryItem currentHistoryItem = null;
    public ParsedResult mParsedResult = null;
    public Bitmap mCodeImage = null;
    public boolean mSavedToHistory = false;

    private SharedPreferences mPreferences;

    public ResultViewModel(@NonNull Application application) {
        super(application);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(application);
    }

    public void initFromHistoryItem(HistoryItem historyItem) {
        currentHistoryItem = historyItem;
        mParsedResult = ResultParser.parseResult(currentHistoryItem.getResult());
        mCodeImage = currentHistoryItem.getImage();
        if(mCodeImage == null) {
            mCodeImage = Utils.generateCode(currentHistoryItem.getText(), BarcodeFormat.QR_CODE, null, null);
            currentHistoryItem.setImage(mCodeImage);
            currentHistoryItem.setFormat(BarcodeFormat.QR_CODE);
            updateHistoryItem(currentHistoryItem);
        }
        mSavedToHistory = true;
    }

    public void initFromScan(BarcodeResult barcodeResult) {
        currentBarcodeResult = barcodeResult;
        mParsedResult = ResultParser.parseResult(currentBarcodeResult.getResult());
        mCodeImage = currentBarcodeResult.getBitmapWithResultPoints(ContextCompat.getColor(getApplication(), R.color.colorAccent));

        createHistoryItem();
        if(mPreferences.getBoolean("bool_history",true)){
            this.saveHistoryItem(currentHistoryItem);
        }
    }

    public void saveHistoryItem(HistoryItem item) {
        AppRepository.getInstance(getApplication()).insertHistoryEntry(item);
        mSavedToHistory = true;
    }


    public void updateHistoryItem(HistoryItem item) {
        AppRepository.getInstance(getApplication()).updateHistoryEntry(item);
    }

    private void createHistoryItem() {
        currentHistoryItem = new HistoryItem();

        Bitmap image;
        image = Utils.generateCode(currentBarcodeResult.getText(), currentBarcodeResult.getBarcodeFormat(), null, currentBarcodeResult.getResult().getResultMetadata());
        currentHistoryItem.setImage(image);
        currentHistoryItem.setFormat(currentBarcodeResult.getResult().getBarcodeFormat());
        currentHistoryItem.setNumBits(currentBarcodeResult.getResult().getNumBits());
        currentHistoryItem.setRawBytes(currentBarcodeResult.getResult().getRawBytes());
        currentHistoryItem.setResultPoints(currentBarcodeResult.getResult().getResultPoints());
        currentHistoryItem.setText(currentBarcodeResult.getResult().getText());
        currentHistoryItem.setTimestamp(currentBarcodeResult.getResult().getTimestamp());
    }

}
