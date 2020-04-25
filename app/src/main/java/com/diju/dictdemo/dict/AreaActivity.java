package com.diju.dictdemo.dict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diju.dictdemo.MainActivity;
import com.diju.dictdemo.R;
import com.diju.dictdemo.dict.model.CityInfoBean;
import com.diju.dictdemo.dict.model.Dict;

import java.util.ArrayList;
import java.util.List;

import static com.diju.dictdemo.dict.CityListLoader.BUNDATA;
import static com.diju.dictdemo.dict.ProvinceActivity.RESULT_DATA;

public class AreaActivity extends Activity {

    private TextView mCityNameTv;

//    private ImageView mImgBack;
//    private TextView tv_back;
    private Button btn_back;

    private RecyclerView mCityRecyclerView;

    private Dict dictBean = null;

//    private CityInfoBean areaBean = new CityInfoBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        dictBean = this.getIntent().getParcelableExtra(BUNDATA);
        initView();

        setData();

    }

    private void setData() {

        if (dictBean != null && dictBean.getSubDictList().size() > 0) {
            mCityNameTv.setText("" + dictBean.getDictValue());

            final List<Dict> dictList = new ArrayList<>(dictBean.getSubDictList());
            if (dictList == null) {
                return;
            }

            DictAdapter dictAdapter = new DictAdapter(AreaActivity.this, dictList);
            mCityRecyclerView.setAdapter(dictAdapter);
            dictAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {

//                    areaBean.setName(dictList.get(position).getName());
//                    areaBean.setId(dictList.get(position).getId());

                    //将计算的结果回传给第一个Activity
                    Intent reReturnIntent = new Intent();
                    reReturnIntent.putExtra("dict", (Parcelable) dictList.get(position));
                    setResult(RESULT_DATA, reReturnIntent);
                    //退出第二个Activity
                    AreaActivity.this.finish();

                }
            });

        }
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        btn_back.setTypeface(MainActivity.ICON_FONT);
        btn_back.setVisibility(View.VISIBLE);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));

    }

}