# CycleLayout
Android 快速实现 圆角布局。
Android 通用圆角布局，快速实现圆角需求。


支持的特性
 包裹任意组件。
 设置圆角大小。
 分别对每一个角设置圆角大小。
 设置描边宽度。
 设置描边颜色。
 圆形。
 支持Padding。
 圆角抗锯齿。
 内容可点击区域即为显示区域。
主要文件
名字	摘要

CycleLayout	圆角相对布局

1. 基本用法
CycleLayout(Round Corner RelativeLayout)，使用圆角布局包裹需要圆角的内容然后添加自定义属性即可


<com.gu.widget.CycleLayout
    android:padding="20dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:round_corner="40dp">

  	<!--任意View-->
    <ImageView
        android:scaleType="centerCrop"
        android:src="@drawable/test"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
    <TextView
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:background="#aaffffff"
        android:gravity="center"
        android:layout_alignParentBottom="true"
        android:text="圆角测试"/>

</com.gu.widget.CycleLayout>

2. 配置属性

可以在布局文件中配置的属性如下：

属性名称	摘要	是否必须设置	类型
round_corner	总体圆角半径	否	dp
round_corner_top_left	左上角圆角半径	否	dp
round_corner_top_right	右上角圆角半径	否	dp
round_corner_bottom_left	左下角圆角半径	否	dp
round_corner_bottom_right	右下角圆角半径	否	dp
round_as_circle	是否剪裁为圆形	否	boolean
stroke_width	描边半径	否	dp
stroke_color	描边颜色	否	color
3. 属性简介

3.1 圆角属性

round_as_circle 的权限最高，在默认情况下它的值为false，如果设置这个属性为 true，则会忽略圆角大小的数值，剪裁结果均为圆形。

设置圆角大小的一共有5个属性，一个是全局的圆角大小round_corner，其余四个round_corner_xx_xx是分别对每一个角进行设置，它们之间存在替代关系。

仅设置全局，所有的角都跟随全局。
仅对某些角设置，则只有设置过的角会有圆角效果。
全局和部分都有设置，则有具体设置的角跟随具体设置的数值，没有具体设置的角跟随全局设置。

3.2 描边属性

描边宽度stroke_width默认情况下数值为 0，即不存在描边效果。
描边颜色stroke_color默认情况下为白色，允许自定义颜色。

