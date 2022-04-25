package com.mobileproject.mobileprojectqr.ui.resultfragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileproject.mobileprojectqr.R;

public class TextResultFragment extends ResultFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_result_text, container, false);

        TextView tv = v.findViewById(R.id.result_field_text);

        tv.setText(parsedResult.getDisplayResult());

        return v;
    }

    public void onProceedPressed(Context context) {
        final String searchEngineURI = "https://www.google.com/search?q=%s"+(context);

        final Uri uri = Uri.parse(String.format(searchEngineURI, parsedResult.getDisplayResult()));

        Intent search = new Intent(Intent.ACTION_VIEW, uri);
        String caption = getResources().getStringArray(R.array.text_array)[0];
        startActivity(Intent.createChooser(search, caption));
    }

}
