package com.example.goandshow


import android.app.Application
import android.util.Log
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.*
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener
import com.baidu.mapapi.model.LatLng


class DemoApplication : Application() {
    var mapView: MapView? = null
    override fun onCreate() {
        super.onCreate()

        // 初始化百度地图 SDK
        SDKInitializer.setAgreePrivacy(this, true)
        SDKInitializer.initialize(this)
        SDKInitializer.setCoordType(CoordType.BD09LL)

        // 在这里添加地图相关的代码
        //beijing39.906901, 116.397972
        //changlong23.008, 113.330
        val point = LatLng(23.008, 113.330)
        // 创建 MapView 对象，并将其添加到 FrameLayout 中
        mapView = MapView(this)
        // 获取 BaiduMap 对象，并在地图上添加标记



        val mBaiduMap = mapView!!.map
        mapView!!.postDelayed({
            val update = MapStatusUpdateFactory.newLatLng(point)
            mBaiduMap.animateMapStatus(update)
            val bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_loc_red)
            val option: OverlayOptions = MarkerOptions()
                .position(point)
                .icon(bitmap)
                .perspective(true)
                .animateType(MarkerOptions.MarkerAnimateType.jump)
                .scaleX(0.2f)
                .scaleY(0.2f)
                .title("这是标记")

            mBaiduMap.addOverlay(option)
        },1000)



    }
}
//定位工具
//mBaiduMap.setOnMarkerDragListener(object : OnMarkerDragListener {
//    override fun onMarkerDragStart(marker: Marker) {
//        // 标记开始拖拽时的逻辑处理
//    }
//
//    override fun onMarkerDrag(marker: Marker) {
//        // 标记正在拖拽时的逻辑处理
//    }
//
//    override fun onMarkerDragEnd(marker: Marker) {
//        // 标记拖拽结束时的逻辑处理，例如获取标记位置信息
//        var mMarkerPosition = marker.position
//        Log.d(
//            "DemoApplication",
//            "onMarkerDragEnd: " + mMarkerPosition.latitude + ", " + mMarkerPosition.longitude
//        )
//    }
//})


