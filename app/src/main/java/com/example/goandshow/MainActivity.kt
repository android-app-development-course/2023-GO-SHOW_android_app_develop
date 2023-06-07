package com.example.goandshow

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.MapView

class MainActivity : Activity() {
    private var mMapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 从 DemoApplication 中获取 MapView
        mMapView = (application as DemoApplication).mapView

        // 获取布局中的 FrameLayout，将 MapView 添加到其中
        val container = findViewById<FrameLayout>(R.id.map_view_container)
        container.addView(mMapView)

        SDKInitializer.initialize(getApplicationContext())
        // 添加热力图到地图
        val baiduMap = mMapView!!.map
        val locations = getLocations()
        val heatmap = setHeatmapProperties(locations)
        addHeatmapToMap(baiduMap, heatmap)
        // 关闭热力图
//        val baidumap = mMapView!!.map
//        val location = getLocations()
//        val hide = hideHeatmap(location)
//        addHeatmapToMap(baidumap,hide)


    }


    override fun onResume() {
        super.onResume()
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView!!.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView!!.onDestroy()
    }
}

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}