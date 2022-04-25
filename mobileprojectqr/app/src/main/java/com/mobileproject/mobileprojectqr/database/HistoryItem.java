package com.mobileproject.mobileprojectqr.database;

import android.graphics.Bitmap;
import android.os.Parcel;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;

@Entity(tableName = "Histories")
public class HistoryItem {

    @PrimaryKey(autoGenerate = true) private int _id;
    private Bitmap image;
    @NonNull
    private String text = "";
    private byte[] rawBytes;
    private int numBits;
    private ResultPoint[] resultPoints;
    private BarcodeFormat format;
    private long timestamp;

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getRawBytes() {
        return rawBytes;
    }

    public void setRawBytes(byte[] rawBytes) {
        this.rawBytes = rawBytes;
    }

    public int getNumBits() {
        return numBits;
    }

    public void setNumBits(int numBits) {
        this.numBits = numBits;
    }

    public ResultPoint[] getResultPoints() {
        return resultPoints;
    }

    public void setResultPoints(ResultPoint[] resultPoints) {
        this.resultPoints = resultPoints;
    }

    public BarcodeFormat getFormat() {
        return format;
    }

    public void setFormat(BarcodeFormat format) {
        this.format = format;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public HistoryItem() {}

    @Ignore
    protected HistoryItem(Parcel in) {
        _id = in.readInt();
        image = Converters.decodeImage(in.readString());
        text = in.readString();
        rawBytes = in.createByteArray();
        numBits = in.readInt();
        timestamp = in.readLong();
        format = BarcodeFormat.values()[in.readInt()];

        resultPoints = new ResultPoint[in.readInt()];
        for(int i = 0; i < resultPoints.length; i++) {
            resultPoints[i] = new ResultPoint(in.readFloat(), in.readFloat());
        }
    }

    public Result getResult() {
        return new Result(text, rawBytes, numBits, resultPoints,format,timestamp);
    }
    public Bitmap getImage() {
        return image;
    }

    public long getTimestamp() {
        return timestamp;
    }


}
