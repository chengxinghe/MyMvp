package com.cxh.mylogin.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;
import com.beloo.widget.chipslayoutmanager.SpacingItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cxh.mylogin.R;
import com.cxh.mylogin.adapter.ChipsNewAdapter;
import com.cxh.mylogin.di.component.DaggerLoginComponent;
import com.cxh.mylogin.di.module.LoginModule;
import com.cxh.mylogin.mvp.contract.LoginContract;
import com.cxh.mylogin.mvp.entity.ReceiverChipEntity;
import com.cxh.mylogin.mvp.presenter.LoginPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * @author Chengxinghe
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private Button mBtnInternet;
    private TextView mTvOkHttp;
    private RecyclerView mRvChip;
    private ChipsNewAdapter mNewAdapter;
    private List<ReceiverChipEntity> mReceiverChipEntities;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mBtnInternet = findViewById(R.id.btn_internet);
        mTvOkHttp = findViewById(R.id.tv_ok_http);
        mRvChip = findViewById(R.id.recycle_chip);
        mReceiverChipEntities = new ArrayList<>();
        itemData();
        ChipsLayoutManager layoutManager = ChipsLayoutManager.newBuilder(this)
                .build();
        mRvChip.setLayoutManager(layoutManager);
        mRvChip.addItemDecoration(new SpacingItemDecoration(getResources().getDimensionPixelOffset(R.dimen.item_space),
                getResources().getDimensionPixelOffset(R.dimen.item_space)));
        mNewAdapter = new ChipsNewAdapter(mReceiverChipEntities);
        mRvChip.setAdapter(mNewAdapter);

        mNewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(LoginActivity.this, "点击了" + position, Toast.LENGTH_LONG).show();
                mReceiverChipEntities.remove(position);
                mNewAdapter.notifyItemRemoved(position);
            }
        });
        mBtnInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                request();
                okHttp();
            }
        });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    public void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface create = retrofit.create(PostInterface.class);
        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = create.getCall("I love you");
        call.enqueue(new Callback<Translation1>() {
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                Log.e("aaaaaaaaa", response.body().getTranslateResult().get(0).get(0).tgt);
            }

            @Override
            public void onFailure(Call<Translation1> call, Throwable t) {

            }
        });
    }

    public void okHttp() {
        OkHttpClient client = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "admin")
                .build();
        final Request request = new Request.Builder()
                .url("http://www.jianshu.com/")
                .post(formBody)
                .build();
        okhttp3.Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Toast.makeText(LoginActivity.this, "Post Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String res = response.body().toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTvOkHttp.setText(res);
                    }
                });
            }
        });
    }

    public void itemData() {
        ReceiverChipEntity receiverChipEntity = new ReceiverChipEntity(ReceiverChipEntity.RECEIVER_TEXT);
        receiverChipEntity.setReceiverName("接收人");
        mReceiverChipEntities.add(receiverChipEntity);
//        ReceiverChipEntity receiverChipEntity1 = new ReceiverChipEntity(ReceiverChipEntity.RECEIVER_IMG);
////        String[] srt = {"溪谷雨","陈立农","李玖哲","林宥嘉","陈奕迅","尚雯婕","薛之谦","张靓颖","池子","李诞"};
//        receiverChipEntity1.setReceiverName("aaa");
//        receiverChipEntity1.setReceiverIcon(R.drawable.ic_icon);
//        mReceiverChipEntities.add(receiverChipEntity1);

        String[] srt = {"溪谷雨", "陈立农", "李玖哲", "林宥嘉", "陈奕迅", "尚雯婕", "薛之谦", "张靓颖", "池子", "李诞"};
//        receiverChipEntity2.setReceiverName("qqq");
//        receiverChipEntity2.setReceiverIcon(R.drawable.ic_icon);
//        mReceiverChipEntities.add(receiverChipEntity2);
        for (int i = 0; i < srt.length; i++) {
            ReceiverChipEntity receiverChipEntity2 = new ReceiverChipEntity(ReceiverChipEntity.RECEIVER_IMG);
            receiverChipEntity2.setReceiverName(srt[i]);
            receiverChipEntity2.setReceiverIcon(R.drawable.ic_icon);
            if (i == 1 || i == 2 || i == 3) {
                receiverChipEntity2.setDelete(false);
            } else {
                receiverChipEntity2.setDelete(true);
            }
            mReceiverChipEntities.add(receiverChipEntity2);
        }
    }

}
