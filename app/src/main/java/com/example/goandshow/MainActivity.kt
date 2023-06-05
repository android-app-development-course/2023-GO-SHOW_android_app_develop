package com.example.goandshow

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.MapView

class MainActivity : Activity() {
    private var mMapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //获取地图控件引用
        mMapView = findViewById<View>(R.id.bmapView) as MapView

        SDKInitializer.initialize(getApplicationContext())
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


//import com.baidu.mapapi.map.MyLocationData

//class MyLocationListener : BDAbstractLocationListener() {
//    fun onReceiveLocation(location: BDLocation?) {
//        //mapView 销毁后不在处理新接收的位置
//        if (location == null || mMapView == null) {
//            return
//        }
//        val locData = MyLocationData.Builder()
//            .accuracy(location.getRadius()) // 此处设置开发者获取到的方向信息，顺时针0-360
//            .direction(location.getDirection()).latitude(location.getLatitude())
//            .longitude(location.getLongitude()).build()
//        mBaiduMap.setMyLocationData(locData)
//    }
//}
