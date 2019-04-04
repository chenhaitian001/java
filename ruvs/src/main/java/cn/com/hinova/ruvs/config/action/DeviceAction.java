package cn.com.hinova.ruvs.config.action;

import cn.com.hinova.ruvs.common.base.SuperAction;
import cn.com.hinova.ruvs.config.bean.Device;
import cn.com.hinova.ruvs.config.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/config/device")
public class DeviceAction extends SuperAction<Device> {


    @Autowired
    private IDeviceService deviceService;




    
}
