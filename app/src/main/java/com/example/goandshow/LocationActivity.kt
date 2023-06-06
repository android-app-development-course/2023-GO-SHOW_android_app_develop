package com.example.goandshow


import android.Manifest
import android.app.Activity
import android.os.Bundle
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.SDKInitializer

class LocationActivity : Activity() {

    private lateinit var mMapView: MapView
    private lateinit var mBaiduMap: BaiduMap
    private lateinit var mLocationClient: LocationClient
    private lateinit var myLocationListener: MyLocationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        SDKInitializer.setAgreePrivacy(this, true)

        // 加入权限声明
        val permissions = arrayOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        requestPermissions(permissions, 1)

        // 初始化地图
        mMapView = findViewById(R.id.mapView)
        mBaiduMap = mMapView.map

        // 开启地图的定位图层
        mBaiduMap.isMyLocationEnabled = true

        // 定位初始化
        mLocationClient = LocationClient(this)

        // 通过LocationClientOption设置LocationClient相关参数
        val option = LocationClientOption()
        option.isOpenGps = true // 打开gps
        option.setCoorType("bd09ll") // 设置坐标类型
        option.scanSpan = 1000

        // 设置locationClientOption
        mLocationClient.locOption = option

        // 注册LocationListener监听器
        myLocationListener = MyLocationListener()
        mLocationClient.registerLocationListener(myLocationListener)

        // 开启地图定位图层
        mLocationClient.start()
    }

    // 定位数据的监听器
    inner class MyLocationListener : BDAbstractLocationListener() {
        override fun onReceiveLocation(location: BDLocation) {
            // mapView 销毁后不再处理新接收的位置
            if (location == null || mMapView == null) {
                return
            }
            val locData = MyLocationData.Builder()
                .accuracy(location.radius)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.direction)
                .latitude(location.latitude)
                .longitude(location.longitude)
                .build()
            mBaiduMap.setMyLocationData(locData)
        }
    }

    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // 停止定位
        mLocationClient.stop()

        // 关闭地图的定位图层
        mBaiduMap.isMyLocationEnabled = false

        // 销毁MapView
        mMapView.onDestroy()
//        mMapView = null
    }
}

//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.baidu.location.BDAbstractLocationListener
//import com.baidu.location.BDLocation
//import com.baidu.location.LocationClient
//import com.baidu.location.LocationClientOption
//import com.baidu.mapapi.SDKInitializer
//import com.baidu.mapapi.map.BaiduMap
//import com.baidu.mapapi.map.MapView
//import com.baidu.mapapi.map.MyLocationData
//
//class MainActivity3 : AppCompatActivity() {
//
//    private lateinit var mMapView: MapView
//    private lateinit var mBaiduMap: BaiduMap
//    private lateinit var mLocationClient: LocationClient
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main3)
//
//        // 初始化地图视图
//        mMapView = findViewById(R.id.mapView)
//        mBaiduMap = mMapView.map
//
//        // 检查并请求定位权限
//        if (checkLocationPermission()) {
////            mBaiduMap.setMyLocationEnabled(true);
//            // 初始化定位相关
//            initLocation()
//        }
//    }
//
//    private fun checkLocationPermission(): Boolean {
//        val coarsePermission = Manifest.permission.ACCESS_COARSE_LOCATION
//        val finePermission = Manifest.permission.ACCESS_FINE_LOCATION
//
//        if (ContextCompat.checkSelfPermission(this, coarsePermission) != PackageManager.PERMISSION_GRANTED ||
//            ContextCompat.checkSelfPermission(this, finePermission) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(coarsePermission, finePermission), 100)
//            return false
//        }
//
//        return true
//    }
//
//    private fun initLocation() {
//        // 创建定位客户端
//        mLocationClient = LocationClient(this)
//
//        // 配置定位参数
//        val option = LocationClientOption().apply {
//            isOpenGps = true // 打开 GPS
//            coorType = "bd09ll" // 设置坐标类型
//            scanSpan = 1000 // 设置定位间隔
//        }
//        mLocationClient.locOption = option
//
//        // 注册定位监听器
//        val myLocationListener = MyLocationListener()
//        mLocationClient.registerLocationListener(myLocationListener)
//
//        // 开启地图定位图层
//        mBaiduMap.isMyLocationEnabled = true
//
//        // 启动定位
//        mLocationClient.start()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mMapView.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mMapView.onPause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mLocationClient.stop()
//        mBaiduMap.isMyLocationEnabled = false
//        mMapView.onDestroy()
////        mMapView = null
//    }
//
//    private inner class MyLocationListener : BDAbstractLocationListener() {
//        override fun onReceiveLocation(location: BDLocation?) {
//            if (location == null || mMapView == null) {
//                return
//            }
//
//            val locData = MyLocationData.Builder()
//                .accuracy(location.radius)
//                .direction(location.direction)
//                .latitude(location.latitude)
//                .longitude(location.longitude)
//                .build()
//
//            mBaiduMap.setMyLocationData(locData)
//        }
//    }
//}