package com.jsun.modules.sys.controller;

import com.jsun.common.utils.BlogJSONResult;
import com.jsun.common.utils.PagedResult;
import com.jsun.common.utils.TransCodingUtil;
import com.jsun.modules.sys.entity.VO.BlogMessageVOEntity;
import com.jsun.modules.sys.service.ArticleService;
import com.jsun.modules.sys.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jsun
 * @ProjectName: adminsystem
 * @Package: com.jsun.controller
 * @Description: 文章controller
 * @Date: 2019/7/8 0008 15:26
 **/
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BlogService blogService;


    /**
     * 首页分页查询文章
     *
     * @param pageSize 一页几篇文章
     * @param pageNum  当前页
     * @return
     */
    @GetMapping("/myArticles")
    public BlogJSONResult myArticles(@RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "pageNum") Integer pageNum) {
        PagedResult allBlog = articleService.findAllBlog(pageNum, pageSize);
        return BlogJSONResult.ok(allBlog);
    }


    /**
     * 文章详情
     *
     * @return
     */
    @GetMapping("getArticleDetail")
    public BlogJSONResult getArticleDetail(@RequestParam("articleId") long articleId) {
        BlogMessageVOEntity byId = blogService.findBlogById(articleId);
        if(byId != null){
            return BlogJSONResult.ok(byId);
        }else{
            return BlogJSONResult.errorMsg("已删除");
        }

    }

    /**
     * 通过标签查看文章
     *
     * @param pageSize 页面大小
     * @param pageNum  当前页
     * @param tags     标签
     * @return
     */
    @GetMapping("/getTagsDetail")
    public BlogJSONResult getTagsDetail(@RequestParam("pageSize") int pageSize,
                                        @RequestParam("pageNum") int pageNum,
                                        @RequestParam("tags") String tags) {
        String s = TransCodingUtil.unicodeToString(tags);
        PagedResult byTag = articleService.findByTag(pageNum, pageSize, s);
        return BlogJSONResult.ok(byTag);
    }

    /**
     * 点赞(游客即可)
     *
     * @param articleId
     * @return
     */
    @GetMapping("getArticleLike")
    public BlogJSONResult getArticleLike(@RequestParam("articleId") long articleId) {
        int result = articleService.updLike(articleId);
        if (result > 0) {
            return BlogJSONResult.ok(result);
        }
        return BlogJSONResult.errorMsg("点赞失败");
    }


}
