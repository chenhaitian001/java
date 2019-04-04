package link.x86.wx.map.util;

public class GetDistance {
    /**
     * 计算地球上任意两点(经纬度)距离
     * 
     * @param long1
     * 第一点经度
     * @param lat1
     * 第一点纬度
     * @param long2
     * 第二点经度
     * @param lat2
     * 第二点纬度
     * @return 返回距离 单位：米
     */
    public static double getDistance(double long1, double lat1, double long2, double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * R * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1) * Math.cos(lat2) * sb2 * sb2));
        return d;
    }
        public static double getDistance2(double longitude1, double latitude1, double longitude2, double latitude2) {
    	         // 维度
    	         double lat1 = (Math.PI / 180) * latitude1;
    	         double lat2 = (Math.PI / 180) * latitude2;
    	 
    	         // 经度
    	         double lon1 = (Math.PI / 180) * longitude1;
    	         double lon2 = (Math.PI / 180) * longitude2;
    	 
    	         // 地球半径
    	         double R = 6371;
    	 
    	         // 两点间距离 km，如果想要米的话，结果*1000就可以了
    	         double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
    	 
    	         return d * 1000;
    	     }
}
