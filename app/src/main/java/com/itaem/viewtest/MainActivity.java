package com.itaem.viewtest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TitleBar mTitleBar;
    private ListView lv_one;
    private ListView lv_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_one = findViewById(R.id.lv_one);
        lv_two = findViewById(R.id.lv_two);
        FloatingActionButton button = findViewById(R.id.bSBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_bottomsheet,null);
                dialog.setContentView(view);
                dialog.show();*/
                // bottomSheetDialogFragment
                new MyBottomSheetDialogFragment().show(getSupportFragmentManager(),"dialog");
            }
        });
        List<String> strings = new ArrayList<>();
        for (int i = 0;i<15;i++){
            strings.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strings);
        lv_one.setAdapter(adapter);
        ArrayList<String> strings1 = new ArrayList<>();
        for (int i = 97;i<112;i++){
            strings1.add(String.valueOf((char) i));
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, strings1);
        lv_two.setAdapter(adapter1);



        //   CustomView customView = findViewById(R.id.customView);
     //   WrapperView wrapperView = new WrapperView(customView);
/*        MaterialButton btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
*//*                ObjectAnimator animator = ObjectAnimator.ofInt(wrapperView,"width",100);
                animator.setDuration(300);
                animator.start();*//*
            }
        });*/
/*        mTitleBar = findViewById(R.id.title);
        mTitleBar.setIconOnClickListener(new TitleBar.IconOnClickListener() {
            @Override
            public void onLeftListener() {
                Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onRightListener() {
                Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_LONG).show();
            }
        });*/
    }
    private static class WrapperView {
        private View mTarget;
        public WrapperView(View target) {
            this.mTarget = target;
        }
        public int getWidth() {
            // ???????????????????????????LayoutParams??? ????????????????????????????????????
            return mTarget.getLayoutParams().width;
        }
        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
