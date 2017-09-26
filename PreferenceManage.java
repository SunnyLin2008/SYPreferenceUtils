package com.example.sunnylin.myapplication;



public class PreferenceManage {

    public final static PreferenceUtil<Boolean> BOOL_TYPE = new PreferenceUtil<>(true);
    public final static PreferenceUtil<String> STRING_TYPE = new PreferenceUtil<>("");
    public final static PreferenceUtil<Integer> INT_TYPE = new PreferenceUtil<>(2);
    public final static PreferenceUtil<ExampleEnum> ENUM_TYPE = new PreferenceUtil<>(ExampleEnum.A);

    static {
        PreferenceUtil.init(ExampleApplication.getInstance(), PreferenceManage.class);
    }
}
