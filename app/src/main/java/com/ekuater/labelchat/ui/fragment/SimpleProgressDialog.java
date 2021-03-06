package com.ekuater.labelchat.ui.fragment;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekuater.labelchat.R;

/**
 * @author LinYong
 */
public class SimpleProgressDialog extends DialogFragment {

    private String mText;
    private ImageView mLoadImage;

    public static SimpleProgressDialog newInstance() {
        return newInstance(null);
    }

    public static SimpleProgressDialog newInstance(String text) {
        SimpleProgressDialog instance = new SimpleProgressDialog();
        instance.setStyle(STYLE_NO_TITLE, 0);
        instance.mText = text;
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_progress_dialog, container, false);
        TextView textView = (TextView) view.findViewById(R.id.progress_text);
        mLoadImage = (ImageView) view.findViewById(R.id.loading);

        if (!TextUtils.isEmpty(mText)) {
            textView.setText(mText);
            textView.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Drawable drawable = mLoadImage.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Drawable drawable = mLoadImage.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            animationDrawable.stop();
        }
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }
}