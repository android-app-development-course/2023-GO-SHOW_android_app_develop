package com.example.goandshow

import android.graphics.Color
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.Gradient
import com.baidu.mapapi.map.HeatMap
import com.baidu.mapapi.map.HeatMapAnimation
import com.baidu.mapapi.model.LatLng

// 准备数据集
fun getLocations(): List<LatLng> {
    val locations = mutableListOf<LatLng>()

    // 添加游乐设施的经纬度坐标
    val attractions = listOf(
        LatLng(23.00607, 113.33797),      // 十环过山车
        LatLng(23.00592, 113.33874),      // 十环美食
        LatLng(23.00679, 113.33737),      // 碰碰车
        LatLng(23.00642, 113.33716),      // 飓风飞椅
        LatLng(23.00586, 113.33664),      // U型滑板
        LatLng(23.00595, 113.33724),      // 感动空间欢乐剧场
        LatLng(23.00560, 113.33866),      // 尖叫地带
        LatLng(23.00523, 113.33908),      // 摩托过山车
        LatLng(23.00491, 113.33875),      // 极速跳跃
        LatLng(23.00517, 113.33757),      // 中央演艺广场
        LatLng(23.00445, 113.33678),      // 梦幻转马
        LatLng(23.00411, 113.33682),      // 摇摆屋
        LatLng(23.00420, 113.33731),      // 飞天魔轮
        LatLng(23.00386, 113.33699),      // 欢乐小镇
        LatLng(23.00501, 113.33553),      // 四维影院
        LatLng(23.00457, 113.33497),      // 森林神庙
        LatLng(23.00370, 113.33572),      // 超级大摆锤
        LatLng(23.00343, 113.33610),      // 欢乐摩天轮
        LatLng(23.00296, 113.33519),      // 垂直过山车
        LatLng(23.00372, 113.33428),      // 飞马家庭过山车
        LatLng(23.00258, 113.33405),      // 激浪旋艇
        LatLng(23.00225, 113.33383),      // 欢乐老爷车
        LatLng(23.00633, 113.33671),      // 古堡餐厅
        LatLng(23.00490, 113.33651),      // 椰林餐厅
        LatLng(23.00651, 113.33850), // 十环过山车2
        LatLng(23.00655, 113.33846), // 十环过山车3
        LatLng(23.00647, 113.33844), // 十环过山车4
        LatLng(23.00529, 113.33836), // 火箭过山车1
        LatLng(23.00529, 113.33837), // 火箭过山车2
        LatLng(23.00501, 113.33552), // 四维影院2
        LatLng(23.00314, 113.33348), // 美食街1
        LatLng(23.00314, 113.33351), // 美食街2
        LatLng(23.00373, 113.33353), // 国际特技剧场
        LatLng(23.00340, 113.33528), // 休息区
        LatLng(23.00355, 113.33501), // 急流勇进1
        LatLng(23.00361, 113.33499), // 急流勇进2
        LatLng(23.00352, 113.33499), // 急流勇进3
        LatLng(23.00386, 113.33700), // 欢乐小镇2
        LatLng(23.00395, 113.33709), // 自由落体
        LatLng(23.00373, 113.33715), // 滑翔飞翼
        LatLng(23.00375, 113.33746), // 霹雳转车
        LatLng(23.00315, 113.33724), // 宝贝乐园1
        LatLng(23.00316, 113.33724), // 宝贝乐园2
        LatLng(23.00308, 113.33720), // 诺亚方舟
        LatLng(23.00272, 113.33657), // 超级演艺广场1
        LatLng(23.00273, 113.33659), // 超级演艺广场2
        LatLng(23.00272, 113.33656), // 超级演艺广场3
        LatLng(23.00485, 113.33812), // 火箭穿梭站
        LatLng(23.00486, 113.33469), // 丛林探险 星际决战
        LatLng(23.00805, 113.33918), // 北门广场
        LatLng(23.00773, 113.33903), // 北门广场2
        LatLng(23.00762, 113.33872), // 游客服务中心
        LatLng(23.00323, 113.33708), // 宝贝乐园3
        LatLng(23.00296, 113.33689), // 宝贝乐园4
        LatLng(23.00277, 113.33310), // 游客服务中心 南门
        LatLng(23.00182, 113.33597), // 长隆演播厅1
        LatLng(23.00177, 113.33594), // 长隆演播厅2
        LatLng(23.00647, 113.33834), // 十环过山车5
        LatLng(23.00639, 113.33837) // 十环过山车6
    )

    locations.addAll(attractions)

    return locations
}

// 设置热力图属性
fun setHeatmapProperties(locations: List<LatLng>): HeatMap {
    // 获取多帧热力图数据集
    val datas = listOf(locations)

    // 设置开始动画属性：开启初始动画，时长100毫秒，动画缓动函数类型为线性
    val initAnimation = HeatMapAnimation(true, 100, HeatMapAnimation.AnimationType.Linear)

    // 设置帧动画属性：开启帧动画，时长800毫秒，动画缓动函数类型为线性
    val frameAnimation = HeatMapAnimation(true, 800, HeatMapAnimation.AnimationType.Linear)

    // 设置热力图渐变色用到的所有颜色数组
    val colors = intArrayOf(
        Color.rgb(0, 0, 200),       // 红色
        Color.rgb(0, 225, 0),       // 绿色
        Color.rgb(255, 0, 0)        // 蓝色
    )

    // 设置热力图渐变类
    val gradient = Gradient(colors, floatArrayOf(0.3f, 0.7f, 1f))

    // 创建热力图对象
    val heatmap = HeatMap.Builder()
        .datas(datas)
        .initAnimation(initAnimation)
        .frameAnimation(frameAnimation)
        .gradient(gradient)
        .maxIntensity(3.1f)
        .minIntensity(-0.1f)
        .radius(200)
        .opacity(0.7)
        .build()

    return heatmap
}

// 添加热力图到地图
fun addHeatmapToMap(baiduMap: BaiduMap, heatmap: HeatMap) {
    baiduMap.addHeatMap(heatmap)
}

// 开启热力图帧动画
fun startHeatmapFrameAnimation(baiduMap: BaiduMap) {
    baiduMap.startHeatMapFrameAnimation()
}

// 停止热力图帧动画
fun stopHeatmapFrameAnimation(baiduMap: BaiduMap) {
    baiduMap.stopHeatMapFrameAnimation()
}

// 设置热力图帧回调
fun setHeatmapFrameCallback(baiduMap: BaiduMap, callback: BaiduMap.OnHeatMapDrawFrameCallBack) {
    baiduMap.setOnHeatMapDrawFrameCallBack(callback)
}
