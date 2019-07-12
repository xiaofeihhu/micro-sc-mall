package com.znv.mall.logistics.service;

import com.znv.mall.core.entity.vo.Result;

/**
 * @Auther: yf
 * @Date: 2019-7-12
 * @Description:
 */
public interface ILogisticsService {

    // 创建物流订单 通过定义可扩展的request对象，可以支持快递、仓储、配送等等物流订单的创建
    public Result createLogistics();

    // 发货 通过定义可扩展的request对象，可以支持快递、仓储、配送等等物流订单的发货
    public Result consignLogistics();

    // 物流回传 通过定义可扩展的request对象，可以支持仓接单、仓分拣、仓出库、配送揽收、配送派送、配送签收等等物流回传过程
    public Result logisticsCallback();

}
