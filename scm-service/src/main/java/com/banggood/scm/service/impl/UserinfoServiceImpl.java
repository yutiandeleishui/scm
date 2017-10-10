package com.banggood.scm.service.impl;




import com.banggood.scm.mapper.UserinfoMapper;
import com.banggood.scm.model.Userinfo;
import com.banggood.scm.model.UserinfoExample;
import com.banggood.scm.service.UserinfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;


    @Override
    public List<Userinfo> list() {

        UserinfoExample userinfoExample=new UserinfoExample();
        UserinfoExample.Criteria criteria = userinfoExample.createCriteria();
        criteria.andAgeIsNotNull();
        Page page=PageHelper.startPage(1, 20);
        System.out.println(page.getPageNum()  + " : " + page.getPageSize());
        List<Userinfo> userinfoList= userinfoMapper.selectByExample(userinfoExample);
       // userinfoList.add(userinfoMapper.selectByPrimaryKey(1));
        return  userinfoList;
    }


    @Override
    public int insertUserinfo(Userinfo userinfo) {
        return  userinfoMapper.insert(userinfo);
    }

    @Override
    public int updateUserinfo(Userinfo userinfo) {
        return  userinfoMapper.updateByPrimaryKey(userinfo);
    }

    @Override
    public int deleteUserinfo(int id) {
        return  userinfoMapper.deleteByPrimaryKey(id);
    }
}
