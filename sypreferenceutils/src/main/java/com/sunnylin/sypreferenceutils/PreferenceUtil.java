package com.sunnylin.sypreferenceutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

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

    private static String defaultFileName = null;
    private static boolean defaultUseKeyAsFileName = false;

    private static Context context;
    private T defaultObject;
    private T cache;
    private PreferencesType type;
    private Boolean enableCache = true;
    private SharedPreferences preferences;
    private boolean useKeyAsFileName = false;

    private static String prefix = "";
    private String key = null;

    private String fileName = null;

    public PreferenceUtil(T defaultObject) {
        this(defaultObject, "");
    }

    public PreferenceUtil(T defaultObject, String key) {
        this.defaultObject = defaultObject;
        this.key = key;
        if (defaultObject instanceof Enum<?>) {
            type = PreferencesType.ENUM;
        } else if (defaultObject instanceof Boolean) {
            type = PreferencesType.BOOL;
        } else if (defaultObject instanceof Integer) {
            type = PreferencesType.INT;
        } else if (defaultObject instanceof String) {
            type = PreferencesType.STRING;
        }
    }

    public SharedPreferences getPreferences() {
        return context.getSharedPreferences(getFileName(), Context.MODE_PRIVATE);
    }

    public T getDefault() {
        return defaultObject;
    }

    public T get() {
        if (cache != null) {
            return cache;
        }
        preferences = getPreferences();
        Object retObject = null;
        switch (type) {
            case BOOL:
                retObject = preferences.getBoolean(getKey(), (Boolean) defaultObject);
                break;
            case INT:
                retObject = preferences.getInt(getKey(), (Integer) defaultObject);
                break;
            case STRING:
                retObject = preferences.getString(getKey(), (String) defaultObject);
                break;
            case ENUM:
                Enum<?> enumDefault = (Enum<?>) defaultObject;
                int original = preferences.getInt(getKey(), enumDefault.ordinal());
                Class<?> enumClass = enumDefault.getClass();
                try {
                    Method method = enumClass.getMethod("values", new Class[0]);
                    Object[] enums = (Object[]) method.invoke(null, new Object[]{});
                    retObject = enums[original];
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        if (enableCache) {
            cache = (T) retObject;
            return cache;
        } else {
            return (T) retObject;
        }
    }

    public void set(T pre) {
        preferences = getPreferences();
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        switch (type) {
            case BOOL:
                preferencesEditor.putBoolean(getKey(), (Boolean) pre);
                break;
            case INT:
                preferencesEditor.putInt(getKey(), (Integer) pre);
                break;
            case STRING:
                preferencesEditor.putString(getKey(), (String) pre);
                break;
            case ENUM:
                preferencesEditor.putInt(getKey(), ((Enum<?>) pre).ordinal());
                break;
            default:
                break;
        }
        preferencesEditor.commit();
        if (enableCache) {
            cache = pre;
        }
    }


    public void setKey(String key) {
        this.key = key;
    }

    private String getKey() {
        return prefix + key;
    }

    public void reset() {
        set(defaultObject);
    }

    public void enableCache(Boolean enableCache) {
        this.enableCache = enableCache;
        cache = null;
    }

    public void cleanCache() {
        cache = null;
    }

    public Context getDefaultContext() {
        return context;
    }

    public static void setPrefix(String prefix) {
        PreferenceUtil.prefix = prefix;
    }

    public static void setDefaultFileName(String defaultFileName) {
        PreferenceUtil.defaultFileName = defaultFileName;
    }

    public static String getDefaultFileName() {
        if (TextUtils.isEmpty(defaultFileName)) {
            return context.getPackageName() + "_share_preferences";
        } else {
            return defaultFileName;
        }
    }

    public static void setDefaultUseKeyAsFileName(boolean defaultUseKeyAsFileName) {
        PreferenceUtil.defaultUseKeyAsFileName = defaultUseKeyAsFileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        if (!TextUtils.isEmpty(fileName)) {
            return fileName;
        }
        if (useKeyAsFileName || defaultUseKeyAsFileName) {
            return getKey();
        }
        return getDefaultFileName();
    }


    public static void init(Context context, Class<?> tClass) {
        PreferenceUtil.context = context;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                if (field.getType() == PreferenceUtil.class) {
                    try {
                        ((PreferenceUtil) field.get(null)).setKey(field.getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
