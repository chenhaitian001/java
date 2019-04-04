package link.x86.wx.map.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

import link.x86.util.HttpUtil;
import link.x86.wx.map.entity.EmployeeCheckinData;
import link.x86.wx.map.entity.QueryCheckinVO;
import link.x86.wx.map.entity.Result;
import link.x86.wx.map.service.FindRelationService;
import link.x86.wx.map.util.ComparatorItem;
import link.x86.wx.map.util.GetDateUtil;
import link.x86.wx.map.util.GetDistance;



//@Controller 控制器注解.
@Controller
@RequestMapping(value="/config",produces = "application/json;charset=utf-8")
public class CheckMapDateController {
	protected static final String AJAX = "ajaxDone";
	private static Logger logger = LoggerFactory.getLogger(CheckMapDateController.class);
	@Resource
	private FindRelationService findRelationService;
	private Integer year;

    private Integer month;
    //@requestMapping
    @RequestMapping("/checkmap")
    @ResponseBody
    public Result check(@RequestParam(value = "longitude", required = false) BigDecimal longitude,
    		@RequestParam(value = "latitude", required = false) BigDecimal latitude,
    		HttpServletResponse response,HttpServletRequest request) {
    	long startTime = System.currentTimeMillis();

        if (longitude == null || latitude == null) {
        	return new Result(2120, "操作失败", "",null);
        }
        BigDecimal sysLong = new BigDecimal(116.57881);
        BigDecimal syslat = new BigDecimal(39.796264);
        Integer precise = 500;
      
        double distance = GetDistance.getDistance2(sysLong.doubleValue(), syslat.doubleValue(),
                longitude.doubleValue(), latitude.doubleValue());
        if (Math.abs(distance) <= precise) {
        	//在考勤打卡范围内
        	return new Result();
        }
        long endTime = System.currentTimeMillis();

        logger.info("----da ka search  getCheckinDataByGPS----" + (endTime - startTime));
        return new Result(2120, "未在打卡范围内哦", "",null);
        
        
    }
    /**
     * 考勤打卡
     * @param oppenidno
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/go_work")
    @ResponseBody
    public String go(@RequestParam(value = "oppenidno", required = false) String  oppenidno,HttpServletResponse response,HttpServletRequest request) {
    	logger.info("获取oppenid,查询注册码"+oppenidno);
    	List findnoList=findRelationService.findNumber(oppenidno);
    	String noo = "";
    	if(GetDateUtil.ifEmpty(findnoList)){
    		logger.info("获取查询"+findnoList.get(0).toString());
    		//判断打卡间隔 是否3分钟内打卡
    		logger.info(oppenidno);
    		int ifCheck =  findRelationService.checkTime(oppenidno);
    		logger.info("判断是否打卡间隔允许范围内"+ifCheck);
    		if(ifCheck==0){
                JSONObject returnJson = new JSONObject();
                returnJson.put("code", 250);
    	    	return returnJson.toString();
    		}
    		
    		//保存打卡信息
    		logger.info("准备保存打卡信息");
    		EmployeeCheckinData data = new EmployeeCheckinData();
            data.setCheckinAddressId(0);
            data.setLoginid(findnoList.get(0).toString());// 打卡人
            GregorianCalendar ca = new GregorianCalendar();  
            String flag  = "10";
            if (ca.get(GregorianCalendar.AM_PM) == 0) {
            	flag  = "10";
            }else{
            	flag  = "20";
            }
			
            data.setFlag(flag);// 打卡类型
            data.setDepartmentCode("0");
            data.setDepartmentName("0");
            data.setUsername("0");
            // 打卡日期和时间
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date date = new Date();
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(date);
                String hour = String.format("%02d", cal.get(GregorianCalendar.HOUR_OF_DAY));

                String minute = String.format("%02d", cal.get(GregorianCalendar.MINUTE));

                String second = String.format("%02d", cal.get(GregorianCalendar.SECOND));
                data.setCheckinDate(sdf.format(date));// 打卡日期
                data.setCheckinTime(hour + minute + second);// 打卡具体时间
                data.setYear(new Integer(cal.get(Calendar.YEAR)).toString());
                data.setMonth(new Integer(cal.get(Calendar.MONTH) + 1).toString());
            } catch (Exception e) {
                logger.info("打卡记录实体创建错误"+e);
                e.printStackTrace();
            }
            logger.info("保存打卡记录");
            //事务
            findRelationService.saveEmployeeCheckinData(data);
            logger.info("记录保存成功");
    		//更新打卡时间
            List findworkList=findRelationService.findWork(oppenidno);
            JSONObject returnJson = new JSONObject();
            returnJson.put("code", 200);
            returnJson.put("msg", "操作成功!");
            returnJson.put("errorMsg", "");
            returnJson.put("errors", null);
            returnJson.put("newAmTime", "待签到");
            returnJson.put("newPmTime", "待签退");
            returnJson.put("newAmRelation", "待签到");
            returnJson.put("newPmRelation", "待签退");
            
	    	if(GetDateUtil.ifEmpty(findworkList)){
	    		for(int i=0;i<findworkList.size();i++){
		    		String time = "";
		    		String timelong= "";
		    		String amTime = "";
		    		String pmTime = "";
		    		String relation = "";
		    			HashMap paperMap = (HashMap) findworkList.get(i);
		    			timelong = paperMap.get("checktime").toString();
		    			time = GetDateUtil.getStr(timelong);
		    			flag =  paperMap.get("flag").toString();
		    			relation =  paperMap.get("relation").toString();
		    			if(flag.equals("10")){
		    				relation = findRelationService.amRelation(oppenidno);
		    				returnJson.put("newAmTime", time);
		    				returnJson.put("newAmRelation", relation);
		    			}else if(flag.equals("20")){
		    				relation = findRelationService.pmRelation(oppenidno);
		    				returnJson.put("newPmTime", time);
		    				returnJson.put("newPmRelation", relation);
		    			}
		    	}
	    	}
            
    		noo=findnoList.get(0).toString();
    		String resuString=HttpUtil.getInstance().doGet("http://www.yuzyun.com/ruvs/Alaram/search_TX_map.do?secretkeycode='"+noo+"'");
        	logger.info("推送来园信息");
        	return returnJson.toString();
    		
		}else{
			JSONObject returnJson = new JSONObject();
            returnJson.put("code", 2120);
            returnJson.put("msg", "打卡失败");
            returnJson.put("errorMsg", "");
            returnJson.put("errors", null);
            return returnJson.toString();
			//return new Result(2120, "未注册", "",null);
		}
    }
    
    @RequestMapping("/leave_work")
    @ResponseBody
    public String leave(@RequestParam(value = "loginid", required = false) String  loginid,
    		@RequestParam(value = "queryWholeMonthTime", required = false) String  queryWholeMonthTime,
    		HttpServletResponse response,HttpServletRequest request) throws ParseException {
    	long startTime = System.currentTimeMillis();
        /*if (StringUtils.isEmpty(loginid)) {
            this.statusCode = "400";
            this.message = "查询错误";
            return SUCCESS;
        }*/
        // 1、进行日期格式换行有yyyy-MM-dd转成yyyyMMdd；
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfSecond = new SimpleDateFormat("yyyyMMdd");
        Date nowTime = new Date();
        //系统当前时间
        if (StringUtils.isEmpty(queryWholeMonthTime)) {// 首次进入页面，查询服务器当前月份

            queryWholeMonthTime = sdf.format(nowTime);
            
            /*SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdfd.parse(queryWholeMonthTime));
            year =cal.get(Calendar.YEAR);
            month =cal.get(Calendar.MONTH)+1;*/
        }else{
        	/*SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdfd.parse(queryWholeMonthTime));
            year =cal.get(Calendar.YEAR);
            month =cal.get(Calendar.MONTH)+1;*/
        	
        }
        
        String queryWholeMonthTimeFinal = "";
        Date basicDate = null;// 查询的基准日期
        try {
            Date firstDate = sdf.parse(queryWholeMonthTime);
            basicDate = firstDate;// 查询的日期
            queryWholeMonthTimeFinal = sdfSecond.format(firstDate);// 查询的日期 字符串yyyyMMdd格式
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 2、得到基准日期所在月第一天的日期，最后一天的日期
        Date fisrtDate = GetDateUtil.getFirstDayInMonth(basicDate);
        Date lastDate = GetDateUtil.getLastDayInMonth(basicDate);
        
        
        
        GregorianCalendar firstCal = new GregorianCalendar();
       firstCal.setTime(fisrtDate);

        GregorianCalendar lastCal = new GregorianCalendar();
        lastCal.setTime(lastDate);
        Map<String, QueryCheckinVO> basicMap = new HashMap<String, QueryCheckinVO>(0);
        for (; firstCal.compareTo(lastCal) <= 0;) {
            QueryCheckinVO aa = new QueryCheckinVO();
            String time = sdf.format(firstCal.getTime()).substring(8);
            aa.setShortCheckinDate(time);
            aa.setCheckinResultFlag("M");
          //获取星期
            int wek = firstCal.get(firstCal.DAY_OF_WEEK);
            if(wek==7||wek==1){
            	aa.setCheckinResultFlag("W");
            }
            basicMap.put(sdfSecond.format(firstCal.getTime()), aa);// 上班
            firstCal.add(GregorianCalendar.DATE, 1);
        }

        // 3、得到这个月第一天是星期几 ，最后一天是星期几
        int firstWeek = GetDateUtil.getWeek(fisrtDate);
        // int lastWeek = GetDateUtil.getWeek(lastDate);
        
        List<EmployeeCheckinData> list = findRelationService.findEmployeeCheckinDataList(loginid, year, month);
        // Map<String, EmployeeCheckinData> map = new HashMap<String, EmployeeCheckinData>(0);
        List<EmployeeCheckinData> dataList = new ArrayList<EmployeeCheckinData>(0);

        // dataList.addAll(basicMap.values());

        List<QueryCheckinVO> resultList = new ArrayList<QueryCheckinVO>(0);

        for (int i = 0; i < list.size(); i++) {
            EmployeeCheckinData vo = list.get(i);
            // 上午上班
            // QueryCheckinVO que = new QueryCheckinVO();
            if (basicMap.containsKey(vo.getCheckinDate())) {
                QueryCheckinVO que = basicMap.get(vo.getCheckinDate());
                String time = vo.getCheckinTime();
                if (!StringUtils.isEmpty(time)) {
                    String timeStr = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4);
                    if ("10".equals(vo.getFlag())) {
                        que.setAmBeforeWorkTime(timeStr);
                        
                    } else if ("20".equals(vo.getFlag())) {
                        que.setPmAfterWorkTime(timeStr);
                    }
                }
                if (!StringUtils.isEmpty(vo.getCheckinDate())) {

                    try {
                        que.setCheckinDate(sdf.format(sdfSecond.parse(vo.getCheckinDate())));
                    } catch (ParseException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                que.setCheckinResultFlag("S");
                que.setLoginid(vo.getLoginid());
                que.setUsername(vo.getUsername());
               // que.setCheckinDate(vo.getCheckinDate());
                basicMap.put(vo.getCheckinDate(), que);
                // resultList.add(que);
            }
        }
        resultList.addAll(basicMap.values());
        try {
            Collections.sort(resultList, new ComparatorItem());
        } catch (Exception e) {
            // TODO: handle exception
        }
        //判断出勤天数
        //SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(queryWholeMonthTime));
        Integer years =cal.get(Calendar.YEAR);
        Integer months =cal.get(Calendar.MONTH)+1;
        int days;
        int works = findRelationService.findCountWorks(loginid, years, months);
        //统计休息天数
        //计算修改天数
        //如果是当前月份用当前月当前天数减出勤，若非当前月，用非当前月减出勤天数
        long date3=nowTime.getTime()-fisrtDate.getTime();  //时间差的毫秒数
        //计算出相差天数
        if(months.equals(GetDateUtil.month())){
        	days=(int) (Math.floor(date3/(24*3600*1000)))+1;
        }else{
        	days =GetDateUtil.getDaysOfMonth(sdf.parse(queryWholeMonthTime));
        }
        
        
        int rests = days - works;
        
        JSONObject returnJson = new JSONObject();
        returnJson.put("queryCheckinVOList", resultList);
        returnJson.put("firstMonthWeek", firstWeek);
        returnJson.put("statusCode", 200);
        returnJson.put("message", "查询正确");
        returnJson.put("rests", rests);
        returnJson.put("works", works);
        request.setAttribute("queryCheckinVOList", resultList);
      //更新打卡时间
        GregorianCalendar ca = new GregorianCalendar();  
        String flag  = "10";
        if (ca.get(GregorianCalendar.AM_PM) == 0) {
        	flag  = "10";
        }else{
        	flag  = "20";
        }
        List findworkList=findRelationService.findWorkbyLogin(loginid);
        returnJson.put("newAmTime", "待签到");
        returnJson.put("newPmTime", "待签退");
        returnJson.put("ifshow", true);
       // returnJson.put("newAmRelation", "待签到");
       // returnJson.put("newPmRelation", "待签退");
        
    	if(!findworkList.isEmpty()){
    		for(int i=0;i<findworkList.size();i++){
	    		String time = "";
	    		String timelong= "";
	    		String amTime = "";
	    		String pmTime = "";
	    		String relation = "";
	    			HashMap paperMap = (HashMap) findworkList.get(i);
	    			timelong = paperMap.get("checktime").toString();
	    			time = GetDateUtil.getStr(timelong);
	    			flag =  paperMap.get("flag").toString();
	    			relation =  paperMap.get("relation").toString();
	    			if(flag.equals("10")){
	    				relation = findRelationService.amRelationbyLogin(loginid);
	    				returnJson.put("newAmTime", time);
	    				returnJson.put("newAmRelation", relation);
	    			}else if(flag.equals("20")){
	    				relation = findRelationService.pmRelationbyLogin(loginid);
	    				returnJson.put("newPmTime", time);
	    				returnJson.put("newPmRelation", relation);
	    			}
	    	}
    	}else{
    		returnJson.put("ifshow", false);
    	}
        
        long endTime = System.currentTimeMillis();

        System.out.println("----考勤查询耗时  getWholeMonthInfo----" + (endTime - startTime));
        
        return returnJson.toString();
    }
}
