package cn.com.hinova.ruvs.config.action;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.common.bean.Result;
import cn.com.hinova.ruvs.config.bean.Patriarch;
import cn.com.hinova.ruvs.config.service.IPatriarchService;
import cn.com.hinova.ruvs.ic.util.ICDeviceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 家长操作
 */
@Controller

@RequestMapping("/config/patriarch")
public class PatriarchAction extends SuperAction<Patriarch> {

    @Autowired
    private IPatriarchService patriarchService;

    @RequestMapping("synPatriarchByDevice.do")
    @ResponseBody
    public Object synPatriarchByDevice(HttpServletRequest req){

        String deviceIp=req.getParameter("deviceIp");

        int count=this.patriarchService.synPatriarchByDeviceIp(deviceIp);


        return new Result(200, "同步数据【"+count+"】条",null, null);
    }


    @RequestMapping("synPatriarchToOtherDevice.do")
    @ResponseBody
    public Object synPatriarchToOtherDevice(HttpServletRequest req){

        String[] faceCodes=req.getParameterValues("faceCode");


        String targetIp=req.getParameter("deviceIp");

        int count=this.patriarchService.sysPatriarchToOtherDevice(faceCodes,targetIp);

        if(faceCodes==null){

            return new Result(200, "同步数据所有数据到"+targetIp,null, null);
        }else{

            return new Result(200, "同步数据【"+count+"】条到"+targetIp,null, null);
        }

    }


}
