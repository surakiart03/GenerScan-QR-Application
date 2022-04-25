package com.mobileproject.mobileprojectqr.result;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.journeyapps.barcodescanner.SourceData;

public class ParcelableSourceDataDecorator implements Parcelable {
    private SourceData sourceData = null;

    ParcelableSourceDataDecorator(Parcel in) {
        try {
            byte[] data = new byte[in.readInt()];
            in.readByteArray(data);

            int dataWidth = in.readInt();
            int dataHeight = in.readInt();
            int imageFormat = in.readInt();
            int rotation = in.readInt();
            Rect cropRect = in.readParcelable(Rect.class.getClassLoader());

            this.sourceData = new SourceData(data, dataWidth, dataHeight, imageFormat, rotation);
            this.sourceData.setCropRect(cropRect);
        } catch (Exception e) {

        }
    }

    public SourceData getSourceData() {
        return sourceData;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sourceData.getData().length);
        dest.writeByteArray(sourceData.getData());

        dest.writeInt(sourceData.getDataWidth());
        dest.writeInt(sourceData.getDataHeight());
        dest.writeInt(sourceData.getImageFormat());
        dest.writeInt(sourceData.isRotated() ? 90 : 0);
        dest.writeParcelable(sourceData.getCropRect(), 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableSourceDataDecorator> CREATOR = new Creator<ParcelableSourceDataDecorator>() {
        @Override
        public ParcelableSourceDataDecorator createFromParcel(Parcel in) {
            return new ParcelableSourceDataDecorator(in);
        }

        @Override
        public ParcelableSourceDataDecorator[] newArray(int size) {
            return new ParcelableSourceDataDecorator[size];
        }
    };
}
