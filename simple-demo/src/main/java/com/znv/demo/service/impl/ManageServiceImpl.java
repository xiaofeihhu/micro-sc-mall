package com.znv.demo.service.impl;

import com.znv.demo.common.bean.Result;
import com.znv.demo.common.exception.BusinessException;
import com.znv.demo.common.exception.ResultCodeEnum;
import com.znv.demo.dao.ManageDao;
import com.znv.demo.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author znv
 * @ClassName:
 * @Description: 服务层
 * @date 2018/5/18 14:57
 */
@Service
public class ManageServiceImpl implements ManageService{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(ManageServiceImpl.class);

    /**
     * dao管理类
     */
    @Resource
    private ManageDao manageDao;


    /**
     *  启动服务
     * @param id id号
     * @return
     */
    @Override
    public Result startServer(String id) throws Exception{
        //TODO 多个参数使用{} ,{} ,{}
        logger.info("启动服务....   服务id={}",id);
        Map<String,Object> recordMap = new HashMap<>(2);
        recordMap.put("id",id);

        try {
            List<Map<String, Object>> list = manageDao.queryServer(recordMap);
            if(!CollectionUtils.isEmpty(list)){
                //TODO 业务逻辑
            }
        } catch (Exception e) {
            //TODO 异常日志说明
            logger.error("异常说明xxx",e);
            recordMap.put("status","-1");
            manageDao.updateServer(recordMap);
            //TODO 返回状态码 以及状态信息封装
            throw new BusinessException(ResultCodeEnum.SYSTEMERROR.getCode(), "启动异常");
        }
        logger.info("服务启动成功....   服务id={}",id);
        return new Result("启动成功");
    }

    @Override
    public List<Map<String,Object>> queryServer(Map<String, Object> recordMap){
    	return  manageDao.queryServer(recordMap);
    }

}
