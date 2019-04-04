package link.x86.wx.map.util;

import java.util.Comparator;

import link.x86.wx.map.entity.QueryCheckinVO;


public class ComparatorItem implements Comparator {
    @Override
    public int compare(Object arg0, Object arg1) {
        // TODO Auto-generated method stub
        // 主产品、年份、波段 、曲直、量感、黑白灰
        // 辅产品、年份、波段 、曲直、量感、黑白灰
        QueryCheckinVO item1 = (QueryCheckinVO) arg0;
        QueryCheckinVO item2 = (QueryCheckinVO) arg1;
        // 先比较曲直，如果曲直相等，就比较量感
        int flagMain = item1.getShortCheckinDate().compareTo(item2.getShortCheckinDate());
        return flagMain;
    }
}
