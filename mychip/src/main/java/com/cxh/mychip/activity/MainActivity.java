package com.cxh.mychip.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxh.mychip.R;
import com.cxh.mychip.adapter.ChipAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Chengxinghe
 */
public class MainActivity extends AppCompatActivity {
    private RecyclerView mRvChip;
    private ChipAdapter mChipAdapter;
    private List<String> mStrings;
    private LinearLayout mLlSnack;
    private ShapeCornerView mShapeCornerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRvChip = findViewById(R.id.recycle_chip);
        mLlSnack = findViewById(R.id.ll_snack);
        mShapeCornerView = findViewById(R.id.custom_view);
        itemData();
        ChipsLayoutManager layoutManager = ChipsLayoutManager.newBuilder(this)
                .build();
        mRvChip.setLayoutManager(layoutManager);
        mRvChip.addItemDecoration(new SpacingItemDecoration(8,
                8));
        mChipAdapter = new ChipAdapter(R.layout.item_recycle_chip, mStrings);
        mRvChip.setAdapter(mChipAdapter);
        mChipAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                new SnackBarUtil.SnackBarBuilder(MainActivity.this, mLlSnack, "这是警告")
                        .setGravity(mRvChip, Gravity.BOTTOM)
                        .setWarnConfig()
                        .show();
            }
        });

        //弹出dialog
        mShapeCornerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFragment myDialogFragment;
                MyDialogFragment.MyDialogFragmentBuild myDialogFragmentBuild = new MyDialogFragment
                        .MyDialogFragmentBuild();
                myDialogFragment = myDialogFragmentBuild
                        .setContext(MainActivity.this)
                        .setContent("这是一个好玩的东西")
                        .create();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                myDialogFragment.show(transaction, "这是提示信息");
            }
        });
    }

    public void itemData() {
        String[] srt = {"溪谷雨"};
        mStrings = new ArrayList<>();
        mStrings.addAll(Arrays.asList(srt));
    }
}
