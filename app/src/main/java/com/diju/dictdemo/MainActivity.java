package com.diju.dictdemo;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.diju.dictdemo.dict.ProvinceActivity;
import com.diju.dictdemo.dict.model.Dict;

public class MainActivity extends AppCompatActivity {
    public static Typeface ICON_FONT;
    Button btn_login;
    TextView result_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ICON_FONT = Typeface.createFromAsset(this.getAssets(), "fontawesome-webfont.ttf");//记得加上这句
        setContentView(R.layout.activity_main);
        result_tv = findViewById(R.id.textView);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list();
            }
        });



    }

    public void list() {
        Intent intent = new Intent(MainActivity.this, ProvinceActivity.class);
        startActivityForResult(intent, ProvinceActivity.RESULT_DATA);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProvinceActivity.RESULT_DATA) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }

                Dict dict = data.getParcelableExtra("dict");
                String result = dict.getDictKey()+":"+dict.getDictValue();
                Toast.makeText(this, result,Toast.LENGTH_LONG).show();
                result_tv.setText(result);
            }

        }
    }
}
