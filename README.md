# SYPreference

SYPreference is a simple Android Preference helper library base on SharePreferences. With SYPreference you can easily to write and read  data in simple code. You can even save the enum to SharePreferences with SYPreference.


## Setup


1.  Including in your project  with :
    ```
    implementation 'com.sunnylin2008:sypreferenceutils:1.1'
    ```
    (Also you can only copy  the [PreferenceUtil.java](https://github.com/SunnyLin2008/SYPreferenceUtils/blob/master/sypreferenceutils/src/main/java/com/sunnylin/sypreferenceutils/PreferenceUtil.java) file into your project.)
2. Create a PreferenceManage class like [example](https://github.com/SunnyLin2008/SYPreferenceUtils/blob/master/sample/src/main/java/com/example/sunnylin/sypreferenceutils/PreferenceManage.java) and preference fields with the data type what will be write and read.
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
  ExampleApplication is your customer Application that like [example](https://github.com/SunnyLin2008/SYPreferenceUtils/blob/master/sample/src/main/java/com/example/sunnylin/sypreferenceutils/ExampleApplication.java)

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
 ### Use with prefix
 ```java
 PreferenceUtil.setPrefix("Your prefix");
 ```

## License

```
Copyright (C) 2017 Sunny Lin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
