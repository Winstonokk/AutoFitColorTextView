# AutoFitColorTextView
固定边界，内容字体大小自适应并且可以指定同一字符串不同内容不同颜色的 TextView

![image](https://github.com/wangfeng19930909/AutoFitColorTextView/blob/master/screenshot/show.png)

使用步骤
=================================== 

step1:
-------

在gradle中直接引用

在你项目根目录的build.gradle中添加

	allprojects {
	
		repositories {
		
		...
		
		maven { url 'https://jitpack.io' }
		
		}
		
	}
   
step2:
-------

在module下的build.gradle中添加：

		dependencies {
		
	        	implementation 'com.github.wangfeng19930909:AutoFitColorTextView:1.0.0'
		
		}
		
step3:
-------

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:autofit="http://schemas.android.com/apk/res-auto"
    tools:context=".MainActivity"
    android:orientation="vertical">

    <com.barnettwong.autofitcolortextview_library.AutoFitColorTextView
        android:id="@+id/textview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="20dp"
        android:layout_marginRight="20dp"
        android:layout_marginTop="20dp"
        android:singleLine="true"
        android:maxLines="1"
        android:textSize="40sp"
        autofit:minTextSize="8sp"/>

</LinearLayout>

MIT License
=================================== 
Copyright 2019, wangfeng19930909

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
