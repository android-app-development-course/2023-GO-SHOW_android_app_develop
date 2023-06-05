package com.example.goandshow


import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.*
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener
import com.baidu.mapapi.model.LatLng


class DemoApplication : Application() {
    var mapView: MapView? = null
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate() {
        super.onCreate()

        // 初始化百度地图 SDK
        SDKInitializer.setAgreePrivacy(this, true)
        SDKInitializer.initialize(this)
        SDKInitializer.setCoordType(CoordType.BD09LL)

        // 在这里添加地图相关的代码
        //beijing39.906901, 116.397972
        //changlong中心23.005004974572703, 113.33556043924663
        val point = LatLng(23.005004974572703, 113.33556043924663)
        // 创建 MapView 对象，并在main activity中将其添加到 FrameLayout 中
        mapView = MapView(this)
        // 创建mBaiduMap 好对其操作，对应的方法属性
        val mBaiduMap = mapView!!.map

        val mMarkerList = mutableListOf<Marker>()
        //创建MarkerInfo 自定类
        data class MarkerInfo(
            val position: LatLng,
            val title: String
        )


        mapView!!.postDelayed(null,1000)
        //地图初始点位置

        val bulider = MapStatus.Builder()
        bulider.target(point)
        bulider.zoom(17.7f)
        val status = bulider.build()
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(status))


        val bitmap: BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_loc_red)
        fun addMarkers(markerInfoList: List<MarkerInfo>) {
            for (markerInfo in markerInfoList) {
                // 创建标记
                val option: OverlayOptions = MarkerOptions()
                    .position(markerInfo.position)
                    .icon(bitmap)
                    .title(markerInfo.title)
                    .scaleX(0.2f)
                    .scaleY(0.2f)
                    .animateType(MarkerOptions.MarkerAnimateType.jump)//跳出效果
                    .clickable(true)
                val marker = mBaiduMap.addOverlay(option) as Marker

                // 将标记保存到列表中
                mMarkerList.add(marker)
            }
        }

        val markerInfoList = listOf(
            MarkerInfo(LatLng(23.00775958164016, 113.33425207726806), "Hotel"),
            MarkerInfo(LatLng(23.009120223004135, 113.33803523511037), "国际大马戏"),
            MarkerInfo(LatLng(23.005004974572703, 113.33556043924663), "丛林探险思维影院") ,
            MarkerInfo(LatLng(23.003084571184537, 113.33517269552779), "垂直过山车"),
            MarkerInfo(LatLng(23.003084571184537, 113.33517269552779), "国际大马戏"),
            MarkerInfo(LatLng(23.009647091816618, 113.33135622408854), "国际大马戏"),
            MarkerInfo(LatLng(23.006496769775065, 113.33850293972448), "十环过山车"),
            MarkerInfo(LatLng(23.00470582822114, 113.33141648714722), "巨蟒滑道"),
        )
        //设定定位标工具
        val Indexmarker : BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_loc_blue)
        val option: OverlayOptions = MarkerOptions()
                .position(point)
                .icon(Indexmarker)
                .perspective(true)
                .animateType(MarkerOptions.MarkerAnimateType.jump)
                .scaleX(0.2f)
                .scaleY(0.2f)
                .draggable(true)

            mBaiduMap.addOverlay(option)
        addMarkers(markerInfoList)
        //定位工具
mBaiduMap.setOnMarkerDragListener(object : OnMarkerDragListener {
    override fun onMarkerDragStart(marker: Marker) {
        // 标记开始拖拽时的逻辑处理
    }

    override fun onMarkerDrag(marker: Marker) {
        // 标记正在拖拽时的逻辑处理
    }

    override fun onMarkerDragEnd(marker: Marker) {
        // 标记拖拽结束时的逻辑处理，例如获取标记位置信息
        var mMarkerPosition = marker.position
        Log.d(
            "DemoApplication",
            "onMarkerDragEnd: " + mMarkerPosition.latitude + ", " + mMarkerPosition.longitude
        )
    }
})
    }
}
        // 获取 BaiduMap 对象，并在地图上添加标记
//        val mBaiduMap = mapView!!.map
//        mapView!!.postDelayed({
//            val update = MapStatusUpdateFactory.newLatLng(point)
//            mBaiduMap.animateMapStatus(update)
//            val bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_loc_red)
//            val option: OverlayOptions = MarkerOptions()
//                .position(point)
//                .icon(bitmap)
//                .perspective(true)
//                .animateType(MarkerOptions.MarkerAnimateType.jump)
//                .scaleX(0.2f)
//                .scaleY(0.2f)
//                .title("这是标记")
//
//            mBaiduMap.addOverlay(option)
//        },1000)




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


