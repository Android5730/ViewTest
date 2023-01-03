package com.itaem.viewtest;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(),R.layout.dialog_bottomsheet,null);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View)view.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 默认全屏展开
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }
    public void doClick(View v){
        // 点击任意布局关闭
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
