package com.example.sunnylin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by SunnyLin on 2017/09/16.
 */

public class PreferenceUtil<T> {

    enum PreferencesType {
        BOOL, INT, STRING, ENUM
    }

    private static Context context;
    private T defaultObject;
    private PreferencesType type;
    private Boolean neverChange = false;
    private Object firstObject = null;
    private Object secondObject = null;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    private String name = "";

    public PreferenceUtil(T defaultObject) {
        this(defaultObject, false);
    }

    public PreferenceUtil(T defaultObject, Boolean neverChange) {
        this.defaultObject = defaultObject;
        this.neverChange = neverChange;
        if (defaultObject instanceof Enum<?>) {
            type = PreferencesType.ENUM;
        } else if (defaultObject instanceof Boolean) {
            type = PreferencesType.BOOL;
            secondObject = !(Boolean) defaultObject;
        } else if (defaultObject instanceof Integer) {
            type = PreferencesType.INT;
            secondObject = (Integer) defaultObject - 1;
        } else if (defaultObject instanceof String) {
            type = PreferencesType.STRING;
            secondObject = "--";
        }
    }

    public T getDefault() {
        return defaultObject;
    }


    public T get() {
        if (neverChange && firstObject != null) {
            return (T) firstObject;
        } else {
            preferences = getDefaultContext().getSharedPreferences(name, Context.MODE_PRIVATE);
            Object retObject = null;
            switch (type) {
                case BOOL:
                    retObject = preferences.getBoolean(name, (Boolean) defaultObject);
                    break;
                case INT:
                    retObject = preferences.getInt(name, (Integer) defaultObject);
                    break;
                case STRING:
                    retObject = preferences.getString(name, (String) defaultObject);
                    break;
                case ENUM:
                    Enum<?> enumDefault = (Enum<?>) defaultObject;
                    int original = preferences.getInt(name, enumDefault.ordinal());
                    Class<?> enumClass = enumDefault.getClass();
                    Method method = null;
                    try {
                        method = enumClass.getMethod("values", new Class[0]);
                        Object[] enums = (Object[]) method.invoke(null, new Object[]{});
                        retObject = enums[original];
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
            if (neverChange) {
                if (firstObject == null) {
                    set((T) secondObject);
                }
                firstObject = retObject;
            }
            return (T) retObject;
        }
    }

    public void set(T pre) {
        preferences = getDefaultContext().getSharedPreferences(name, Context.MODE_PRIVATE);
        preferencesEditor = preferences.edit();
        switch (type) {
            case BOOL:
                preferencesEditor.putBoolean(name, (Boolean) pre);
                break;
            case INT:
                preferencesEditor.putInt(name, (Integer) pre);
                break;
            case STRING:
                preferencesEditor.putString(name, (String) pre);
                break;
            case ENUM:
                preferencesEditor.putInt(name, ((Enum<?>) pre).ordinal());
                break;
            default:
                break;
        }
        preferencesEditor.commit();
    }

    public byte getByte() {
        T object = get();
        byte retByte = -1;
        switch (type) {
            case BOOL:
                retByte = (byte) (((Boolean) object) ? 1 : 0);
                break;
            case INT:
                retByte = (byte) ((Integer) object & 0xff);
                break;
            case ENUM:
                retByte = (byte) ((Enum<?>) object).ordinal();
                break;
            default:
                break;
        }
        return retByte;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Context getDefaultContext() {
        return context;
    }

    public static void init(Context context, Class<?> tClass) {
        PreferenceUtil.context = context;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                if (field.getType() == PreferenceUtil.class) {
                    try {
                        ((PreferenceUtil) field.get(null)).setName(field.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
