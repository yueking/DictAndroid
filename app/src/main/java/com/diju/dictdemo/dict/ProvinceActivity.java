package com.diju.dictdemo.dict;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.diju.dictdemo.MainActivity;
import com.diju.dictdemo.R;
import com.diju.dictdemo.dict.model.Dict;

import java.util.ArrayList;
import java.util.List;

import static com.diju.dictdemo.dict.CityListLoader.BUNDATA;

public class ProvinceActivity  extends Activity {
    private Button btn_back;

    private TextView mCityNameTv;

    private RecyclerView mCityRecyclerView;

    public static final int RESULT_DATA = 1001;

    private Dict dictBean = null;
    private List<Dict> dictList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citylist);
        initView();
        setData();
    }

    private void setData() {
        dictBean = this.getIntent().getParcelableExtra(BUNDATA);
        if(dictBean == null){
            dictList = CityListLoader.getInstance().getDictListData();
            btn_back.setVisibility(View.INVISIBLE);
        }else {
            dictList = new ArrayList<>(dictBean.getSubDictList());
            btn_back.setVisibility(View.VISIBLE);
        }

        if (dictList == null || dictList.size() < 1) {
            return;
        }

        DictAdapter dictAdapter = new DictAdapter(ProvinceActivity.this, dictList);
        mCityRecyclerView.setAdapter(dictAdapter);
        dictAdapter.setOnItemClickListener(new CityAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position) {
                dictBean = dictList.get(position);
                Toast.makeText(getApplicationContext(),dictBean.getDictValue(),Toast.LENGTH_SHORT).show();
                //最叶
                if(dictBean.getSubDictList() != null && dictBean.getSubDictList().size() >0){
                    Intent intent = new Intent(ProvinceActivity.this, ProvinceActivity.class);
                    intent.putExtra(BUNDATA, (Parcelable) dictList.get(position));
                    startActivityForResult(intent, RESULT_DATA);
                }else {
                    //将计算的结果回传给第一个Activity
                    Intent reReturnIntent = new Intent();
                    reReturnIntent.putExtra("dict", (Parcelable) dictBean);
                    setResult(RESULT_DATA, reReturnIntent);
                    //
                    finish();
                }


            }
        });

    }

    private void initView() {
        mCityNameTv = (TextView) findViewById(R.id.cityname_tv);
//        mCityNameTv.setText("选择省份");
        mCityRecyclerView = (RecyclerView) findViewById(R.id.city_recyclerview);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCityRecyclerView.addItemDecoration(new RecycleViewDividerForList(this, LinearLayoutManager.HORIZONTAL, true));

        btn_back = findViewById(R.id.btn_back);
        btn_back.setTypeface(MainActivity.ICON_FONT);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(),"ya",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_DATA && data != null) {
            Dict dict = data.getParcelableExtra("dict");
            Intent intent = new Intent();
            intent.putExtra("dict", (Parcelable) dict);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
