package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.beans.vo.userinfo.ItripAddUserLinkUserVO;
import cn.itrip.beans.vo.userinfo.ItripModifyUserLinkUserVO;
import cn.itrip.beans.vo.userinfo.ItripSearchUserLinkUserVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.ValidationToken;
import cn.itrip.service.userlinkuser.ItripUserLinkUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * 用户信息Controller
 * <p>
 * 包括API接口：
 * 1、根据UserId、联系人姓名查询常用联系人接口
 * 2、指定用户下添加联系人
 * 3、修改指定用户下的联系人信息
 * 4、删除指定用户下的联系人信息
 * <p>
 * 注：错误码（100401 ——100500）
 * <p>
 * Created by hanlu on 2017/5/9.
 */

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/userinfo")
public class UserInfoController {
    private Logger logger = Logger.getLogger(UserInfoController.class);


    @Resource
    private ValidationToken validationToken;

    @Resource
    private ItripUserLinkUserService itripUserLinkUserService;

    /**
     * 获得信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getlinkusername", method = RequestMethod.POST, produces = "application/json")
    public Dto getlinkusername(@RequestBody Long id, HttpServletRequest request) {
        String tokenString=request.getHeader("token");
        System.out.println("tokenString>>>>>>>>>>>>>"+tokenString);
        ItripUser currentUser=validationToken.getCurrentUser(tokenString);
        ItripUserLinkUser itripUserLinkUserList = null;
        if (null != currentUser) {
            Map param = new HashMap();
            param.put("userId", currentUser.getId());
            try {
                itripUserLinkUserList=itripUserLinkUserService.getItripUserLinkUserById(id);
                return DtoUtil.returnSuccess("查询常用联系人信息成功",itripUserLinkUserList);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取常用联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }

    @RequestMapping(value = "/modifyuserlinkuser", method = RequestMethod.POST)
    @ResponseBody
    public Dto modifyuserlinkuser(@RequestBody ItripModifyUserLinkUserVO itripModifyUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        logger.info("tokenString>>>>>>>>>>>>>" + tokenString);
        logger.info("linkUserName>>>>>>>>>" + itripModifyUserLinkUserVO.getLinkUserName());
        logger.info("linkIdCard>>>>>>>>>" + itripModifyUserLinkUserVO.getLinkIdCard());
        logger.info("userId>>>>>>>>>" + itripModifyUserLinkUserVO.getId());
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();

        ItripUserLinkUser itripUserLinkUser = new ItripUserLinkUser();
        itripUserLinkUser.setLinkUserName(itripModifyUserLinkUserVO.getLinkUserName());
        itripUserLinkUser.setLinkIdCardType(itripModifyUserLinkUserVO.getLinkIdCardType());
        itripUserLinkUser.setLinkIdCard(itripModifyUserLinkUserVO.getLinkIdCard());
        itripUserLinkUser.setLinkPhone(itripModifyUserLinkUserVO.getLinkPhone());
        itripUserLinkUser.setId(itripModifyUserLinkUserVO.getId());
        int count = 0;
        if (null != currentUser) {
            itripUserLinkUser.setUserId(currentUser.getId());
            try {
                count = itripUserLinkUserService.modifyItripUserLinkUser(itripUserLinkUser);
                logger.debug("count>>>>>>>>>"+count);
                return DtoUtil.returnSuccess("修改联系人信息成功");
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("修改联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    /**
     * 根据UserId,联系人姓名查询常用联系人-add by donghai
     *
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询常用联系人接口", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询常用联系人信息(可根据联系人姓名进行模糊查询)" +
            "<p>若不根据联系人姓名进行查询，不输入参数即可 | 若根据联系人姓名进行查询，须进行相应的入参，比如：{\"linkUserName\":\"张三\"}</p>" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100401 : 获取常用联系人信息失败 </p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/queryuserlinkuser", method = RequestMethod.POST)
    @ResponseBody
    public Dto<ItripUserLinkUser> queryuserlinkuser(@RequestBody ItripSearchUserLinkUserVO itripSearchUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        logger.info("tokenString>>>>>>>>>>>>>" + tokenString);
        logger.info("linkUserName>>>>>>>>>" + itripSearchUserLinkUserVO.getLinkUserName());
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();
        String linkUserName = (null == itripSearchUserLinkUserVO) ? null : itripSearchUserLinkUserVO.getLinkUserName();
        Dto dto = null;
        if (null != currentUser) {
            Map param = new HashMap();
            param.put("userId", currentUser.getId());
            param.put("linkUserName", linkUserName);
            try {
                userLinkUserList = itripUserLinkUserService.getItripUserLinkUserListByMap(param);
                logger.debug("userLinkUserList size " + userLinkUserList.size());
                return DtoUtil.returnSuccess("获取常用联系人信息成功", userLinkUserList);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("获取常用联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    @ApiOperation(value = "添加常用联系人接口", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "添加常用联系人信息，需要在head中设置token" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100401 : 获取常用联系人信息失败 </p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/adduserlinkuser", method = RequestMethod.POST)
    @ResponseBody
    public Dto adduserlinkuser(@RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        logger.info("tokenString>>>>>>>>>>>>>" + tokenString);
        logger.info("linkUserName>>>>>>>>>" + itripAddUserLinkUserVO.getLinkUserName());
        logger.info("linkIdCard>>>>>>>>>" + itripAddUserLinkUserVO.getLinkIdCard());
        logger.info("userId>>>>>>>>>" + itripAddUserLinkUserVO.getUserId());
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();

        ItripUserLinkUser itripUserLinkUser = new ItripUserLinkUser();
        itripUserLinkUser.setLinkUserName(itripAddUserLinkUserVO.getLinkUserName());
        itripUserLinkUser.setLinkIdCardType(itripAddUserLinkUserVO.getLinkIdCardType());
        itripUserLinkUser.setLinkIdCard(itripAddUserLinkUserVO.getLinkIdCard());
        itripUserLinkUser.setLinkPhone(itripAddUserLinkUserVO.getLinkPhone());

        int count = 0;
        if (null != currentUser) {
            itripUserLinkUser.setUserId(currentUser.getId());
            try {
                count = itripUserLinkUserService.addItripUserLinkUser(itripUserLinkUser);
                logger.debug("count " + count);
                return DtoUtil.returnSuccess("新增联系人信息成功");
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("新增常用联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }

    @ApiOperation(value = "修改常用联系人接口", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "修改常用联系人信息，需要在head中设置token" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100401 : 获取常用联系人信息失败 </p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/modifylink", method = RequestMethod.POST)
    @ResponseBody
    public Dto modifylink(@RequestBody ItripAddUserLinkUserVO itripAddUserLinkUserVO, HttpServletRequest request) {
        String tokenString = request.getHeader("token");
        logger.info("tokenString>>>>>>>>>>>>>" + tokenString);
        logger.info("linkUserName>>>>>>>>>" + itripAddUserLinkUserVO.getLinkUserName());
        logger.info("linkIdCard>>>>>>>>>" + itripAddUserLinkUserVO.getLinkIdCard());
        logger.info("userId>>>>>>>>>" + itripAddUserLinkUserVO.getUserId());
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();

        ItripUserLinkUser itripUserLinkUser = new ItripUserLinkUser();
        itripUserLinkUser.setLinkUserName(itripAddUserLinkUserVO.getLinkUserName());
        itripUserLinkUser.setLinkIdCardType(itripAddUserLinkUserVO.getLinkIdCardType());
        itripUserLinkUser.setLinkIdCard(itripAddUserLinkUserVO.getLinkIdCard());
        itripUserLinkUser.setLinkPhone(itripAddUserLinkUserVO.getLinkPhone());
        //修改页面
        int count = 0;
        if (null != currentUser) {
            itripUserLinkUser.setUserId(currentUser.getId());
            try {
                count = itripUserLinkUserService.modifyItripUserLinkUser(itripUserLinkUser);
                logger.debug("count " + count);
                return DtoUtil.returnSuccess("新增联系人信息成功");
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("新增常用联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
    @ApiOperation(value = "修改常用联系人接口", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "修改常用联系人信息，需要在head中设置token" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>100401 : 获取常用联系人信息失败 </p>" +
            "<p>100000 : token失效，请重登录</p>")
    @RequestMapping(value = "/deluserlinkuser", method = RequestMethod.GET)
    @ResponseBody
    public Dto deluserlinkuser( HttpServletRequest request,@RequestParam Long [] ids  ) {
        String tokenString = request.getHeader("token");
        logger.info("tokenString>>>>>>>>>>>>>" + tokenString);
        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();
        //获得登录信息
        // List<ItripUserLinkUser> userLinkUserList = new ArrayList<ItripUserLinkUser>();
        if (null != currentUser) {
            Map param = new HashMap();
            param.put("userId", currentUser.getId());
          /*  Map param = new HashMap();
            param.put("userId", currentUser.getId());*/
            //System.out.println("itripUserLinkUser>>>>>>>>>>>>Id:"+currentUser.getId());
            Integer count=0;
            try {
                count=itripUserLinkUserService.deleteItripUserLinkUserByIds(ids);
                logger.info("count>>>>>>>>>>>>"+count);
                userLinkUserList=itripUserLinkUserService.getItripUserLinkUserListByMap(param);
                return DtoUtil.returnSuccess("删除常用联系人信息成功",userLinkUserList);
            } catch (Exception e) {
                e.printStackTrace();
                return DtoUtil.returnFail("删除常用联系人信息失败", "100401");
            }
        } else {
            return DtoUtil.returnFail("token失效，请重新登录", "100000");
        }
    }
}