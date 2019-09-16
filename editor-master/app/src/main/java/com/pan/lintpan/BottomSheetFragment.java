package com.pan.lintpan;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        //Set the view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bottom_sheet, null);
        dialog.setContentView(view);
    }
}
