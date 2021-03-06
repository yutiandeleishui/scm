package com.banggood.scm.controller;
import com.banggood.scm.enums.ResulEnum;
import com.banggood.scm.message.ResultInfo;
import com.banggood.scm.model.Userinfo;
import com.banggood.scm.redis.RedisServer;
import com.banggood.scm.service.UserinfoService;
import com.banggood.scm.vo.UserinfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

/**
 * Created by Administrator on 2017/8/28.
 */
@RestController
@RequestMapping("/")
@Api(value = "Home",description = "首页")
public class HomeController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserinfoService userinfoService;
    @ApiOperation(value ="获取用户数据",notes = "获取用户数据列表")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResultInfo list(){
      //  UserinfoService userinfoService=new UserinfoServiceImpl();
        List<Userinfo> userinfoList=userinfoService.list();
        return  new ResultInfo(userinfoList);
    }

    @ApiOperation(value ="测试",notes = "用于用户测试")
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public  String index(){
        return "index";
    }


    //添加数据
    @ApiOperation(value ="添加用户",notes = "用户添加接口")
    @RequestMapping(value = "addUserInfo",method = RequestMethod.POST)
    public ResultInfo addUserInfo(@RequestBody UserinfoVo userinfoVo){

        Userinfo userinfo=new Userinfo();
        BeanUtils.copyProperties(userinfoVo,userinfo);
        ResultInfo resultInfo=new ResultInfo();
        resultInfo.setResultCode(userinfoService.insertUserinfo(userinfo)>0? ResulEnum.SUCCESS:ResulEnum.FAIL);
        return  new ResultInfo(userinfo);
    }


    //redis 操作
    @ApiOperation(value ="添加redis中的用户",notes = "redis添加接口")
    @RequestMapping(value = "setRedisCached",method = RequestMethod.POST)
    public String setRedisCached(@RequestBody UserinfoVo userinfoVo){
        RedisServer<String> redisServer=new RedisServer<>(redisTemplate);
        redisServer.setRedisServer(userinfoVo.getUsername(),userinfoVo.toString());
         return    redisServer.getRedisServer(userinfoVo.getUsername());
    }


    //redis 操作
    @ApiOperation(value ="查看redis中用户",notes = "redis查询接口")
    @RequestMapping(value = "getRedisCached",method = RequestMethod.GET)
    public ResultInfo  getRedisCached(String key){
        RedisServer<String> redisServer=new RedisServer<>(redisTemplate);
        return  new ResultInfo(redisServer.getRedisServer(key));
    }

    //redis 操作
    @ApiOperation(value ="执行脚本-添加e个",notes = "redis脚本执行接口")
    @RequestMapping(value = "exectScpit",method = RequestMethod.POST)
    public ResultInfo exectScpit(String key){
        RedisServer<String> redisServer=new RedisServer<>(redisTemplate);
        return  new ResultInfo( redisServer.ExceScpit(key));
    }


    //修改数据
    @ApiOperation(value ="修改用户",notes = "用户修改接口")
    @RequestMapping(value = "updateUserInfo",method = RequestMethod.POST)
    public ResultInfo updateUserInfo(@RequestBody UserinfoVo userinfoVo){
        return  new ResultInfo(userinfoVo);
    }

    //删除数据
    @ApiOperation(value ="删除用户",notes = "用户删除接口")
    @RequestMapping(value = "deleteUserInfo",method = RequestMethod.POST)
    public ResultInfo deleteUserInfo( @RequestBody UserinfoVo userinfoVo){
        return  new ResultInfo(userinfoVo);
    }



}


