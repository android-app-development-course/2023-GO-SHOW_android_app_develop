package com.example.goandshow
import android.app.Activity
import android.content.Intent
import android.os.Bundle

import android.widget.FrameLayout
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.*
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener
import com.baidu.mapapi.model.LatLng


class MainActivity : Activity() {
    private var mapView: MapView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初始化百度地图 SDK
        SDKInitializer.setCoordType(CoordType.BD09LL)
        SDKInitializer.initialize(applicationContext)

// 设置 Activity 的布局文件
        setContentView(R.layout.activity_main)

// 创建 MapView 对象
        mapView = MapView(this)

// 获取布局中的 FrameLayout，将 MapView 添加到其中
        val container = findViewById<FrameLayout>(R.id.map_view_container)

        container.addView(mapView)

// 创建 BaiduMap 对象
        val baiduMap = mapView!!.map

// 设置地图初始位置和缩放级别
        val point = LatLng(23.005004974572703, 113.33556043924663)
        val builder = MapStatus.Builder()
        builder.target(point).zoom(17.7f)
        baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))

// 创建标记图标
        val bitmap: BitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_red_shixin)

        // 创建 MarkerInfo 数据类
        data class MarkerInfo(
            val position: LatLng,
            val title: String,
            val imgid: String,
            val description: String
        )

// 添加标记
        val markerInfoList = listOf(
            MarkerInfo(LatLng(23.00775958164016, 113.33425207726806), "Hotel", "hotel","长隆酒店是长隆集团倾力打造的生态主题及会展酒店，总建筑面积达36万平方米，拥有融入不同生态主题的特色客房及套房1500间，毗邻长隆欢乐世界、长隆野生动物世界、长隆水上乐园及长隆国际大马戏等主题乐园。酒店拥有放养雪虎、鹤、鹦鹉等珍稀动物的动物岛。位于酒店的长隆国际会展中心，内设6000平方米无柱宴会大厅，以及39间可作不同用途的多功能会议厅。2018年4月，长隆酒店再度荣获TTG中国旅游大奖“中国最佳主题酒店”，这也是长隆酒店连续十年蝉联该奖项。2019年、2020年，长隆酒店连续两年荣获TTG中国旅游大奖的 “最佳家庭度假酒店”大奖。"),
            MarkerInfo(LatLng(23.009120223004135, 113.33798523511037), "国际大马戏", "maxi","长隆国际大马戏位于国家首批5A级景区广州长隆旅游度假区内，拥有全世界最大的马戏表演场馆，能容纳近7000名观众同时观看.隆国际大马戏由来自20多个国家，横跨亚洲、欧洲、美洲、非洲共300余名的马戏精英同台演绎 。凭借全球顶尖的舞台灯光与音响设施，国际化的演艺团队，从2000年上演，经久不衰，深受游客喜爱。长隆国际大马戏不但创造了世界马戏表演艺术的奇迹，还凝结着世界各国马戏演员一代又一代的创新与传承。"),
            MarkerInfo(LatLng(23.00501974572703, 113.33553043924663), "丛林探险四维影院", "cinema" ,"亚洲先进四维影院，将为广大游客带来极致的观影体验,四维影院完全突破了传统电影的概念，在运用最新三维立体影像科技的基础上，通过高精尖电脑程控技术和高科技机械设备的配合，制造出神奇的四维特技效果，影院中，震动、强风、水柱、激光、烟雾、爆炸等现场特技效果与立体影像同步发生，充分调动起观众的视觉、听觉和触觉感官神经，获得模拟实地感受"),
            MarkerInfo(LatLng(23.00298471184537, 113.33518269552779), "垂直过山车", "roster","设计灵感和理念来自人类最初的飞翔梦想，它模拟了空中之王——雄鹰的各种空中绝技。最高落差达60米，相当于20层楼的落差，为亚洲垂直下落距离第二高的垂直下坠过山车，轨道全长981米，是世界上最长的垂直下坠过山车，拥有近90度垂直俯冲和冲浪体验，最高时速达112公里/小时,逍遥搏击任飞翔，彩虹绘出真体验，就让我们一起尽情去体验过山车带来的巅峰感受吧。"),
            MarkerInfo(LatLng(23.009647091816618, 113.33135622408854), "大喇叭餐厅", "canteen","大喇叭餐厅主打麻辣香锅，远远经过就能闻到扑鼻而来的香气，如果你是吃辣一族一定要去试试，让你吃得舒心，辣得过过瘾！大喇叭餐厅位于大喇叭设施及漂流河旁，环境优雅舒适，如果你不能吃辣也没有关系，我们还专门准备了口味清淡的套餐汤粉等，总有一款适合你。"),
            MarkerInfo(LatLng(23.006496769775065, 113.33850293972448), "十环过山车", "ten_roster","十环过山车，时而盘旋上升，时而骤然反转，时而急速骤降……下一环会有什么呢？该过山车轨道长度850米，高30米，最高时速72.4km/h，单次乘坐时长1分32秒。在广州番禺长隆欢乐世界可看到在树丛后若隐若现的鲜艳的橙色轨道，高低盘旋，从眼前一直延伸到远处，这就是园中曾经创下翻滚数最多的过山车之一的吉尼斯世界纪录的“十环过山车”。"),

            MarkerInfo(LatLng(23.00454582822114, 113.33140648714722), "巨蟒滑道", "snake","“巨蟒”滑道是一个肾上腺素冲击之旅，时刻将充满这“下一秒将会怎样？”的新奇体验。从弯形盘旋滑道划出后会进入长达6米的全封闭滑道——MEGA tube，它将给游客一个独一无二的体验，集合了扭转，螺旋和振动的感觉，绝对能让您乐而忘返。")
        )

        val markerList = mutableListOf<Marker>()
        for (markerInfo in markerInfoList) {
            // 创建标记
            val bundle = Bundle()
            bundle.putString("title", markerInfo.title)
            bundle.putString("imgid", markerInfo.imgid)
            bundle.putString("description",markerInfo.description)

            val option: OverlayOptions = MarkerOptions()
                .position(markerInfo.position)
                .icon(bitmap)
                .extraInfo(bundle)
                .scaleX(0.2f)
                .scaleY(0.2f)
                .animateType(MarkerOptions.MarkerAnimateType.jump)//跳出效果
                .clickable(true)
            val marker = baiduMap.addOverlay(option) as Marker

            // 将标记保存到列表中
            markerList.add(marker)
        }

// 设置标记点击事件监听器
        baiduMap.setOnMarkerClickListener(object : OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean {
                // 获取所选标记的信息
                val bundle = marker.extraInfo
                val title = bundle.getString("title")
                val imgid = bundle.getString("imgid")
                val description = bundle.getString("description")


                // 创建一个 Intent 对象并传递参数，然后启动 PlaceDetailActivity
                val intent = Intent(this@MainActivity, PlaceDetailActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("imgid", imgid)
                intent.putExtra("description", description)
                startActivity(intent)

                return true
            }
        })
    }}