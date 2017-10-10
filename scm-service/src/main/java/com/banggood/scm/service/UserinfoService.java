package com.banggood.scm.service;

import com.banggood.scm.model.Userinfo;

import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
public interface UserinfoService {

    List<Userinfo> list();

    int insertUserinfo(Userinfo userinfo);

    int updateUserinfo(Userinfo userinfo);

    int deleteUserinfo(int id);
}
