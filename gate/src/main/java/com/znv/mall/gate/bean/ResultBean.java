package com.znv.mall.gate.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询接口包装类
 * @param <T> T为返回结果的类型
 */
public class ResultBean<T> {
    public static final int SUCESS = 0;
    public static final int FAILED = 1;
    public static final int TOCKEN_EXPIRED = 2;


    private int result;
    private String remark;
    private List<T> data = new ArrayList<>();

    public ResultBean() {
        this.result = SUCESS;
        this.remark = "";
    }

    public ResultBean(int result, String remark) {
        this.result = result;
        this.remark = remark;
    }

    public void addData(List<T> datas) throws Exception{
        if (datas == null) throw new Exception("data null error, can not add null in result data");
        data.addAll(datas);
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
