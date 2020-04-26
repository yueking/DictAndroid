package com.diju.dictdemo.dict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diju.dictdemo.MainActivity;
import com.diju.dictdemo.R;
import com.diju.dictdemo.dict.model.Dict;

import java.util.ArrayList;
import java.util.List;

import static com.diju.dictdemo.dict.DictListLoader.BUNDATA;
import static com.diju.dictdemo.dict.DictActivity.RESULT_DATA;

public class CityActivity extends Activity {

    private TextView mCityNameTv;

    private Button btn_back;

    private RecyclerView mCityRecyclerView;

    private Dict dictBean = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictlist);
        dictBean = this.getIntent().getParcelableExtra(BUNDATA);
        initView();
        setData(dictBean);

    }

    private void setData(Dict dict) {

        if (dict != null && dict.getSubDictList().size() > 0) {
            mCityNameTv.setText("" + dict.getDictValue());

            final List<Dict> dictList =  new ArrayList<>(dict.getSubDictList());
            if (dictList == null) {
                return;
            }

            DictAdapter dictAdapter = new DictAdapter(CityActivity.this, dictList);
            mCityRecyclerView.setAdapter(dictAdapter);
            dictAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
                @Override
                public void onItemSelected(View view, int position) {

                    Intent intent = new Intent(CityActivity.this, AreaActivity.class);
                    intent.putExtra(BUNDATA, (Parcelable) dictList.get(position));
                    startActivityForResult(intent, RESULT_DATA);
                }
            });

        }
    }

    private void initView() {
        btn_back = findViewById(R.id.btn_back);
        btn_back.setTypeface(MainActivity.ICON_FONT);

        btn_back.setVisibility(View.VISIBLE);
        btn_back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_DATA && data != null) {
            Dict dict = data.getParcelableExtra("dict");
            Intent intent = new Intent();
            intent.putExtra("dict", (Parcelable) dict);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
