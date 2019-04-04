package cn.com.hinova.ruvs.analyze.action;

import cn.com.hinova.ruvs.analyze.bean.FaceHistory;
import cn.com.hinova.ruvs.analyze.service.IBigScreenService;
import cn.com.hinova.ruvs.common.base.ICommonService;
import cn.com.hinova.ruvs.common.bean.Paging;
import cn.com.hinova.ruvs.common.cache.Cache;
import cn.com.hinova.ruvs.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/analyze/bigScreen")
public class BigScreenAction extends MultiActionController {

    @Autowired
    private IBigScreenService bigScreenService;

    @Autowired
    private ICommonService commonService;

    @RequestMapping("last.do")
    @ResponseBody
    public List<FaceHistory> getFaceHistory(){

        return bigScreenService.getFaceHistory(30);
    }
    @RequestMapping("new_last.do")
    @ResponseBody
    public List<FaceHistory> getNewFaceHistory(HttpServletRequest req){

        return bigScreenService.getNewFaceHistory(req.getParameter("lastTime"));
    }


    @RequestMapping("faceHistory.do")
    @ResponseBody
    public Paging getPaging(@ModelAttribute("paging") Paging paging, HttpServletRequest req) throws Exception {

        paging.setRows(NumberUtils.toInt(req.getParameter("rows"), 10));
        Cache.paramMapLocal.set(req.getParameterMap());

        return commonService.findPaging(FaceHistory.class,paging);
    }

}
