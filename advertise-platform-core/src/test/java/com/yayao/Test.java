package com.yayao;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;

/**
 * Created by 聂跃 on 2018/7/18.
 * 解析动态网页
 */
public class Test {
    protected static void aa() throws IOException{
        // 屏蔽HtmlUnit等系统 log
//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log","org.apache.commons.logging.impl.NoOpLog");
//        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
//        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);

        String url = "https://im.qq.com/download/index.shtml";
        url = "http://app.nalu888.cn/home/gooddetail.html?goodid=5";
        //url = "https://detail.tmall.hk/hk/item.htm?id=565907350514&spm=a223v.7835278.t0.1.7b902312fjYghl&pvid=0ec3778d-cb87-4023-aa65-22e47adec164&scm=1007.12144.81309.9011_8949&utparam={%22x_hestia_source%22:%228949%22,%22x_mt%22:10,%22x_object_id%22:565907350514,%22x_object_type%22:%22item%22,%22x_pos%22:1,%22x_pvid%22:%220ec3778d-cb87-4023-aa65-22e47adec164%22,%22x_src%22:%228949%22}";
        url = "/s?__biz=MzI1NjcyNzg3Nw==&mid=2247484691&idx=1&sn=446089293fc87e0eb996ca0cc3b37f66&chksm=ea230b39dd54822f1afc208997e322bcccb9d763eca6a071455603b7ae69d6f50517c97dcce4&mpshare=1&scene=1&srcid=0917okq1WCgV39xoR4Riw8jI&from=singlemessage&ascene=1&devicetype=android-26&version=2607023a&nettype=WIFI&abtest_cookie=AwABAAoACwATAAMAI5ceAFaZHgBhmR4AAAA%3D&lang=zh_CN&pass_ticket=zpquWZKTmPnEbI7H5jsipGeVXHT2%2Bl4BEZBg0RLmieozt0OqFVxleEie3kBqtO3l&wx_header=1";
        //url = "https://mp.weixin.qq.com/s?__biz=MzUzNjg4NDMzMg==&mid=2247485537&idx=3&sn=5413e687ce6fe95854e8f0ec8c33b328&chksm=faee2d62cd99a47476462b58da96a842799d74e2081ff184342c6096c2bb566d71846edd5a52#rd";
        //url = "https://mp.weixin.qq.com/mp/getmasssendmsg?f=json&mock=&uin=777&key=777&pass_ticket=Gvj%25252Bwp%25252F1ppN2V3KRF3p4lgVZ4q4Gv32S%25252Fz4aY8BoUwjJitjnd%25252FRcudHHby%25252B%25252BRue5&wxtoken=777&devicetype=android-26&clientversion=26070239&appmsg_token=973_3pUD5yRilhRicvC1iTDzjipEhbuCTGDcR4eTwLvDo4BDuhdRZH0-ciZ6jE3TKexR7Uq476lZ6ica-NCk&x5=1&f=json";
        System.out.println("Loading page now-----------------------------------------------: "+url);

        URL link=new URL(url);
        WebRequest request=new WebRequest(link);
        //request.setProxyHost("120.120.120.x");
        //request.setProxyPort(8080);
        request.setAdditionalHeader("Referer","https://mp.weixin.qq.com");//设置请求报文头里的refer字段
        request.setAdditionalHeader("Origin", "https://mp.weixin.qq.com");//设置请求报文头里的refer字段
        ////设置请求报文头里的User-Agent字段
        String androidweixinheader="Mozilla/5.0 (Linux; Android 5.0; SM-N9100 Build/LRX21V) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile Safari/537.36 MicroMessenger/6.0.2.56_r958800.520 NetType/WIFI";
        String iosweixinheader="Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Mobile/11D257 micromessage/6.0.1 NetType/WIFI";
        request.setAdditionalHeader("User-Agent", iosweixinheader);
        // HtmlUnit 模拟浏览器
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);              // 启用JS解释器，默认为true
        webClient.getOptions().setCssEnabled(false);                    // 禁用css支持
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // js运行错误时，是否抛出异常
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //webClient.getOptions().setTimeout(10 * 1000);                   // 设置连接超时时间
        //HtmlPage page = webClient.getPage(url);
        HtmlPage page = webClient.getPage(request);
        //webClient.waitForBackgroundJavaScript(30 * 1000);               // 等待js后台执行30秒
        String pageAsXml = page.asXml();

        // Jsoup解析处理
        //Document doc = Jsoup.parse(pageAsXml, url);
        Document doc = Jsoup.parse(pageAsXml);
        //Element idd = doc.getElementById("cur_online");
        //idd = doc.getElementsByClass("tm-price").get(0);
        // 此处省略其他操作
        System.err.println(doc.toString());
        //System.out.println(idd.text());
    }

    public static void main(String[] args)throws IOException {
            aa();
    }
}
