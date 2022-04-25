package com.mobileproject.mobileprojectqr.ui.resultfragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.SMSParsedResult;
import com.mobileproject.mobileprojectqr.R;

public class SMSResultFragment extends ResultFragment {

    SMSParsedResult result;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_result_sms, container, false);
        result = (SMSParsedResult) parsedResult;

        TextView to = (TextView) v.findViewById(R.id.fragment_result_sms_to);
        TextView toLabel = (TextView) v.findViewById(R.id.fragment_result_sms_to_label);
        TextView body = (TextView) v.findViewById(R.id.fragment_result_sms_body);
        TextView bodyLabel = (TextView) v.findViewById(R.id.fragment_result_sms_message_label);

        if(result != null) {
            StringBuilder numberSb = new StringBuilder();
            ParsedResult.maybeAppend(result.getNumbers(), numberSb);

            StringBuilder viaSb = new StringBuilder();
            ParsedResult.maybeAppend(result.getVias(), viaSb);

            to.setText(numberSb.toString());
            body.setText(result.getBody());

            to.setVisibility(TextUtils.isEmpty(numberSb.toString()) ? View.GONE : View.VISIBLE);
            toLabel.setVisibility(TextUtils.isEmpty(numberSb.toString()) ? View.GONE : View.VISIBLE);
            body.setVisibility(TextUtils.isEmpty(result.getBody()) ? View.GONE : View.VISIBLE);
            bodyLabel.setVisibility(TextUtils.isEmpty(result.getBody()) ? View.GONE : View.VISIBLE);
        }

        return v;
    }

    public void onProceedPressed(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.choose_action)
                .setItems(R.array.sms_array, (dialog, which) -> {
                    String caption = "";
                    switch (which) {
                        case 0:
                            Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + result.getNumbers()[0]));
                            sms.putExtra("sms_body", result.getBody());
                            caption = getResources().getStringArray(R.array.sms_array)[0];
                            startActivity(Intent.createChooser(sms, caption));
                            break;
                        case 1:
                            Intent call = new Intent("android.intent.action.DIAL");
                            call.setData(Uri.parse("tel:" + result.getNumbers()[0]));
                            caption = getResources().getStringArray(R.array.sms_array)[1];
                            startActivity(Intent.createChooser(call, caption));
                            break;
                        case 2:
                            Intent contact = new Intent(
                                    ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                                    Uri.parse("tel:" + result.getNumbers()[0]));
                            caption = getResources().getStringArray(R.array.sms_array)[2];
                            startActivity(Intent.createChooser(contact, caption));
                            break;
                        default:
                    }
                });
        builder.create().show();
    }
}
