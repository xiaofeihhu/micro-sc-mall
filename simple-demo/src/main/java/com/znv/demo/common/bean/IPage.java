
package com.znv.demo.common.bean;

/**
  * @ClassName: IPage
  * @Description: TODO
  * @author znv
  * @date 2018/5/16 16:29
  *
  */
public class IPage<E>{
    private E list;
    private int pageNo=1;
    private int pageSize=10;
    private long totalSize;
    private int totalPages;
    
    public E getList() {
        return list;
    }
    public void setList(E list) {
        this.list = list;
    }
    public int getPageNo() {
        return pageNo;
    }
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public long getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
