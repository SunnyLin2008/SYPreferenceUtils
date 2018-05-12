package com.example.sunnylin.sypreferenceutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    static private String TAG_BEFORE_SET = "PreferenceBeforeSet";
    static private String TAG_AFTER_SET = "PreferenceAfterSet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Before set
        Boolean boolType = PreferenceManage.BOOL_TYPE.get();
        Log.i(TAG_BEFORE_SET, TAG_BEFORE_SET + "  " + boolType);
        String stringType = PreferenceManage.STRING_TYPE.get();
        Log.i(TAG_BEFORE_SET, TAG_BEFORE_SET + "  " + stringType);
        int intType = PreferenceManage.INT_TYPE.get();
        Log.i(TAG_BEFORE_SET, TAG_BEFORE_SET + "  " + intType);
        ExampleEnum enumType = PreferenceManage.ENUM_TYPE.get();
        Log.i(TAG_BEFORE_SET, TAG_BEFORE_SET + "  " + enumType.toString());
        // set the preference
        PreferenceManage.BOOL_TYPE.set(false);
        PreferenceManage.STRING_TYPE.set("Change the string");
        PreferenceManage.INT_TYPE.set(999);
        PreferenceManage.ENUM_TYPE.set(ExampleEnum.B);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //After set
        Boolean boolType = PreferenceManage.BOOL_TYPE.get();
        Log.i(TAG_AFTER_SET, TAG_AFTER_SET + "  " + boolType);
        String stringType = PreferenceManage.STRING_TYPE.get();
        Log.i(TAG_AFTER_SET, TAG_AFTER_SET + "  " + stringType);
        int intType = PreferenceManage.INT_TYPE.get();
        Log.i(TAG_AFTER_SET, TAG_AFTER_SET + "  " + intType);
        ExampleEnum enumType = PreferenceManage.ENUM_TYPE.get();
        Log.i(TAG_AFTER_SET, TAG_AFTER_SET + "  " + enumType.toString());
    }
}
