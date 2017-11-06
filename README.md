# SYPreference

SYPreference is a simple Android Preference helper library. With SYPreference you can easily to write and read  data in litter code.


## Setup

1,Copy  the [PreferenceUtil.java](https://github.com/SunnyLin2008/SYPreferenceUtils) file to your project.
2,Create a PreferenceManage class like example and preference fields with the data type what will be write and read.
3, Write code like
```java
static {
PreferenceUtil.init(ExampleApplication.getInstance(), PreferenceManage.class);
}
```
to the PreferenceManage with your customer Application that like example which can get the Application can context when  PreferenceManage static initial.

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

Copyright (C) 2014-2017 Sunny-Lin

Licensed under the Apache License, Version 2.0
