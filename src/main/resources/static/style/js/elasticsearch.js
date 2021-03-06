var message = "";
var EspageNum = "";
var pageSize = 2;
/**
 * 博客渲染
 * @param data
 */
function putInAllArticle(data) {
    var length = data.length;
    $(".jsun").html('');
    var artiles = $(".jsun");
    if (length == 0) {
        var center = $(
            '<div class="qz-center">' +
            '<header class="qz-article">' +
            '<h1 itemprop="name" style="padding-bottom: 4%;color: #8f3786">客官,查询到底了(⊙o⊙)？</h1>' +
            '</header>' +
            '</div>'
        );
        artiles.append(center);

        return;
    }
    $.each(data, function (index, obj) {
        var center = $(
            '<div class="qz-center">' +
            '<header class="qz-article">' +
            '<h1 itemprop="name">' +
            '<a class="qz-article-title" href="' + obj['articleUrl'] + '">' + obj['title'] + '</a>' +
            '</h1>' +
            '<div class="qz-tt">' +
            '<span class="am-badge am-badge-success qz-mark">' + obj['selectType'] + '</span>&nbsp;&nbsp;' +
            '<span class=""><span class="am-icon-calendar"></span>&nbsp;&nbsp;' + obj['createTime'] + '</span>&nbsp;&nbsp;' +
            '<span style="font-size: 14px;"><i class="am-icon-user">&nbsp;' + obj['name'] + '</i></span>&nbsp;&nbsp;' +
            '</div>' +
            '</header>' +
            '<div class="article-entry" style="height: 130px;">' +
            ' <p>' + obj['articleTabloid'] + '</p>' +
            '</div>' +
            '<div class="read-all">' +
            '<a href="' + obj['articleUrl'] + '">阅读全文 <i class="am-icon-angle-double-right"></i></a>' +
            '</div>' +
            '<hr>' +
            '<div class="article-tags">' +

            '</div>' +
            '</div>');
        artiles.append(center);
        var articleTags = $('.article-tags');
        for (var i = 0; i < obj['tagValue'].length; i++) {
            var articleTag = $('<i class="am-icon-tag"><a class="tag aa" href="/tags?tag=' + obj['tagValue'][i] + '">&nbsp;' + obj['tagValue'][i] + '&nbsp;</a></i>');
            articleTags.eq(index).append(articleTag);
        }
    })
};

$.ajax({
    type: 'HEAD', // 获取头信息，type=HEAD即可
    url: window.location.href,
    async: false,
    success: function (data, status, xhr) {
        message = xhr.getResponseHeader("message");
    }
});

$(function () {
    EspageNum = 1;
    f(EspageNum);
})

function f(EspaNum) {
    EspageNum = EspaNum;
    var data1 = {pageSize: pageSize, pageNum: EspageNum, wordKey: message};
    $.ajax({
        type: "GET",
        url: "/getArticleByEs",
        contentType: "application/json",
        // dataType: "json",
        data: data1,
        success: function (data) {
            // 放入数据
            putInAllArticle(data.data);
            var len = data.data.length;
            scrollTo(0, 0);//回到顶部
            if (EspageNum == 1) {
                if (len != 0) {
                    if(data.data.length < pageSize){
                        $(".left-page").hide();
                        $(".right-page").hide();
                    }else{
                        $(".left-page").hide();
                        $(".right-page").show();
                    }
                }else{
                    $(".left-page").hide();
                    $(".right-page").hide();
                }
            } else if(len == 0){
                $(".left-page").show();
                $(".right-page").hide();
            }else if (len < pageSize) {
                $(".right-page").hide();
                $(".left-page").show();
            } else {
                $(".left-page").show();
                $(".right-page").show();
            }
        },
        error: function () {
        }
    })
}

$(".right-page").click(function () {
    f(EspageNum + 1, message);
});
$(".left-page").click(function () {
    f(EspageNum - 1, message);
});