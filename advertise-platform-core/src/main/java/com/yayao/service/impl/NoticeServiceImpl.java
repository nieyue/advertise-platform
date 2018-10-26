package com.yayao.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yayao.bean.Account;
import com.yayao.bean.Notice;
import com.yayao.business.NoticeBusiness;
import com.yayao.service.AccountService;
import com.yayao.service.NoticeService;
import com.yayao.util.MyDom4jUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice,Long> implements NoticeService {
    @Autowired
    AccountService accountService;
    @Autowired
    NoticeBusiness noticeBusiness;

    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public boolean add(Notice notice) {
        boolean b=false;
        notice.setCreateDate(new Date());
        notice.setUpdateDate(new Date());
        notice.setRegion(2);//默认都是个人
        if(notice.getType().equals(1)){
            notice.setRegion(1);//系统通知为全局
        }
        notice.setTitle(noticeBusiness.getTitleByType(notice.getType()));
        //1审核中，2申请成功，3申请失败,个人为0，代表正常
        notice.setStatus(noticeBusiness.getStatusByType(notice.getType()));
        notice.setIsRead(1);//是否阅读，默认1未读，2已读
        notice.setContent(noticeBusiness.getContentByType(notice));
        b = super.add(notice);
        if(b&&notice.getType().equals(1)){//系统通知
            //通知到所有人
            new Thread(new Runnable() {

                @Override
                public void run() {
                    List<Account> al = accountService.list(1, Integer.MAX_VALUE, null,null,null);
                    al.forEach((account)->{
                        Long aid = account.getAccountId();
                        Notice n=new Notice();
                        n.setContent(notice.getContent());
                        n.setCreateDate(notice.getCreateDate());
                        n.setUpdateDate(notice.getUpdateDate());
                        n.setImgAddress(notice.getImgAddress());
                        n.setRegion(2);//个人
                        n.setType(1);//系统通知
                        n.setStatus(0);//系统消息为0
                        n.setIsRead(1);//是否阅读，默认1未读，2已读
                        n.setContent(notice.getContent());//
                        n.setTitle(notice.getTitle());
                        n.setAccountId(aid);//系统消息
                        NoticeServiceImpl.super.add(n);
                    });
                }
            }).start();
        }
        return b;
    }
    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public boolean delete(Long noticeId) {
        Notice notice = super.load(noticeId);
        boolean b = super.delete(noticeId);
        if(b&&notice.getType().equals(1)&&notice.getAccountId()==null){
            //通知到所有人
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Account> al = accountService.list(1, Integer.MAX_VALUE, null,null,null);
                    al.forEach((account)->{
                        Long aid = account.getAccountId();
                        Wrapper<Notice> wrapper=new EntityWrapper<>();
                        Map<String,Object> map=new HashMap<>();
                        map.put("region", 2);
                        map.put("type", 1);
                        map.put("account_id", aid);
                        wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
                        List<Notice> nl = list(1, Integer.MAX_VALUE, null,null,wrapper);
                        if(nl.size()==1){
                            Notice n = nl.get(0);
                            NoticeServiceImpl.super.delete(n.getNoticeId());
                        }
                    });
                }
            }).start();
        }
        return b;
    }
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public boolean update(Notice notice) {
        notice.setUpdateDate(new Date());
        boolean b =false;
        if(notice.getType().equals(1)&&notice.getAccountId()==null){
            b = super.update(notice);
            //通知到所有人
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Account> al = accountService.list( 1, Integer.MAX_VALUE, null,null,null);
                    al.forEach((account)->{
                        Long aid = account.getAccountId();
                        Wrapper<Notice> wrapper=new EntityWrapper<>();
                        Map<String,Object> map=new HashMap<>();
                        map.put("region", 2);
                        map.put("type", 1);
                        map.put("account_id", aid);
                        wrapper.allEq(MyDom4jUtil.getNoNullMap(map));
                        List<Notice> nl = list( 1, Integer.MAX_VALUE, null,null,wrapper);
                        if(nl.size()==1){
                            Notice n = nl.get(0);
                            n.setTitle(notice.getTitle());
                            n.setContent(notice.getContent());
                            n.setUpdateDate(notice.getUpdateDate());
                            n.setImgAddress(notice.getImgAddress());
                            n.setRegion(2);//个人
                            NoticeServiceImpl.super.update(n);
                        }
                    });
                }
            }).start();
        }else if(notice.getType().equals(2)&&notice.getStatus().equals(2)){
            //充值 ,申请成功
            b = super.update(notice);
            Notice n = super.load(notice.getNoticeId());
        }else if(notice.getType().equals(3)&&notice.getStatus().equals(2)){
            //退款 ,申请成功
            Notice n = super.load(notice.getNoticeId());
            b = super.update(notice);
        }else if(notice.getType().equals(4)&&notice.getStatus().equals(2)){
            //提现 ,申请成功
            b = super.update(notice);
        }else{
            b = super.update(notice);
        }
        return b;
    }
    @Override
    public Notice load(Long noticeId) {
        Notice r = super.load(noticeId);
        if(r.getIsRead()!=null&&r.getIsRead().equals(1)){
            r.setIsRead(2);//已读
            super.update(r);
        }
        return r;
    }
}
