package com.mobileproject.mobileprojectqr.result;

import android.os.Parcel;
import android.os.Parcelable;

import com.journeyapps.barcodescanner.BarcodeResult;

public class ParcelableBarcodeResultDecorator implements Parcelable {
    private final BarcodeResult barcodeResult;

    protected ParcelableBarcodeResultDecorator(Parcel in) {
        this.barcodeResult = new BarcodeResult(
                ((ParcelableResultDecorator) in.readParcelable(ParcelableResultDecorator.class.getClassLoader())).getResult(),
                ((ParcelableSourceDataDecorator) in.readParcelable(ParcelableSourceDataDecorator.class.getClassLoader())).getSourceData()
        );
    }

    public BarcodeResult getResult() {
        return barcodeResult;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(new ParcelableResultDecorator(barcodeResult.getResult()), 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableBarcodeResultDecorator> CREATOR = new Creator<ParcelableBarcodeResultDecorator>() {
        @Override
        public ParcelableBarcodeResultDecorator createFromParcel(Parcel in) {
            return new ParcelableBarcodeResultDecorator(in);
        }

        @Override
        public ParcelableBarcodeResultDecorator[] newArray(int size) {
            return new ParcelableBarcodeResultDecorator[size];
        }
    };
}
