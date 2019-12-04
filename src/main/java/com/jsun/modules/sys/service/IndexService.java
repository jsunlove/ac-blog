package com.jsun.modules.sys.service;

/**
 * @Author: jsun
 * @ProjectName: adminsystem
 * @Package: com.jsun.modules.sys.service
 * @Description: 网页信息
 * @Date: 2019/8/8 0008 16:09
 **/
public interface IndexService {

    /**
     * 文章总数
     * @return
     */
    public Long myArticlesCount();

    /**
     * 标签总数
     * @return
     */
    public int myLabelsCount();


    /**
     * 评论总数
     * @return
     */
    public Integer myReportsCount();

    /**
     * 留言总数
     * @return
     */
    public Integer myGuestCount();

    /**
     * 网站访问量
     * @return
     */
    public int myWebCount();

}
