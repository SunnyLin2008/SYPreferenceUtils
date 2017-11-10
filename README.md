# SYPreference

SYPreference is a simple Android Preference helper library base on SharePreferences. With SYPreference you can easily to write and read  data in simple code. You can even save the enum to SharePreferences with SYPreference.


## Setup

1. Copy  the [PreferenceUtil.java](https://github.com/SunnyLin2008/SYPreferenceUtils/blob/master/app/PreferenceManage.java) file to your project.
2. Create a PreferenceManage class like [example](https://github.com/SunnyLin2008/SYPreferenceUtils/blob/master/app/PreferenceManage.java) and preference fields with the data type what will be write and read.
```java
public final static PreferenceUtil<Boolean> BOOL_TYPE = new PreferenceUtil<>(true); //a bool value preference 
public final static PreferenceUtil<String> STRING_TYPE = new PreferenceUtil<>(""); //a String value preference 
public final static PreferenceUtil<Integer> INT_TYPE = new PreferenceUtil<>(2); //a int value preference 
public final static PreferenceUtil<ExampleEnum> ENUM_TYPE = new PreferenceUtil<>(ExampleEnum.A); //a enum value that you declare and want to read and write to the preference.
```
3. Add initial code to the PreferenceManage class file under the fields you had declared before :
```java
static {
  PreferenceUtil.init(ExampleApplication.getInstance(), PreferenceManage.class);
}
```
  ExampleApplication is your customer Application that like [example](https://github.com/SunnyLin2008/SYPreferenceUtils/blob/master/app/ExampleApplication.java)

## How to use

### Write
```java
PreferenceManage.BOOL_TYPE.set(false);
PreferenceManage.STRING_TYPE.set("Change the string");
PreferenceManage.INT_TYPE.set(999);
PreferenceManage.ENUM_TYPE.set(ExampleEnum.B);
```
### Read
```java
Boolean boolType = PreferenceManage.BOOL_TYPE.get();
String stringType = PreferenceManage.STRING_TYPE.get();
int intType = PreferenceManage.INT_TYPE.get();
ExampleEnum enumType = PreferenceManage.ENUM_TYPE.get();
 ```

## License

Copyright (C) 2017 Sunny-Lin

Licensed under the Apache License, Version 2.0
