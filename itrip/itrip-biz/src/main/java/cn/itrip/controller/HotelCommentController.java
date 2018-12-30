package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.vo.comment.ItripCommentCountVO;
import cn.itrip.beans.vo.comment.ItripListCommentVO;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import cn.itrip.beans.vo.comment.ItripSearchCommentVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.service.comment.HotelCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/comment")
public class HotelCommentController {

    private Logger logger = Logger.getLogger(HotelCommentController.class);

    @Resource
    private HotelCommentService itripCommentService;

    /****
     * 查询酒店评分
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店评分", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/gethotelscore/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripScoreCommentVO> gethotelscore(@PathVariable Long cityId) {
        ItripComment itripComment=null;
        ItripScoreCommentVO itripScoreCommentVOS = new ItripScoreCommentVO();
        if (EmptyUtils.isNotEmpty(cityId)) {
            try {
                itripComment = itripCommentService.getHotelByScore(cityId);
                itripScoreCommentVOS.setAvgFacilitiesScore(itripComment.getFacilitiesScore());
                itripScoreCommentVOS.setAvgHygieneScore(itripComment.getHygieneScore());
                itripScoreCommentVOS.setAvgPositionScore(itripComment.getPositionScore());
                itripScoreCommentVOS.setAvgServiceScore(itripComment.getServiceScore());
                itripScoreCommentVOS.setAvgScore(itripComment.getScore());

            } catch (Exception e1) {
                DtoUtil.returnFail("系统异常", "10202");
                e1.printStackTrace();
            }

        }
        return DtoUtil.returnSuccess("获取评分成功",itripScoreCommentVOS);
    }

    /****
     * 查询酒店评分
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店评分", httpMethod = "GET",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/getcount/{cityId}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public Dto<ItripComment> getcount(@PathVariable Long cityId) {
        ItripComment itripComment=null;
        ItripCommentCountVO itripCommentCountVO = new ItripCommentCountVO();

        if (EmptyUtils.isNotEmpty(cityId)) {
            try {
                itripComment = itripCommentService.getHotelCount(cityId);
                itripCommentCountVO.setAllcomment(itripComment.getAllComment());
                itripCommentCountVO.setHavingimg(itripComment.getIsHavingImg());
                itripCommentCountVO.setIsok(itripComment.getIsOk());
                itripCommentCountVO.setImprove(itripComment.getImprove());

            } catch (Exception e1) {
                DtoUtil.returnFail("系统异常", "10202");
                e1.printStackTrace();
            }

        }
        return DtoUtil.returnSuccess("获取评分成功",itripCommentCountVO);
    }
    /*@RequestMapping(value = "/getcommentlist", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripListCommentVO>> getCommentList(@RequestBody ItripSearchCommentVO itripSearchCommentVO, HttpServletRequest request) {
//        String tokenString = CookieUtil.readLoginToken(request);
//        log.info("tokenString>>>>>>>>>>>>>" + tokenString);
//        log.debug("token name is from header : " + tokenString);
//        ItripUser currentUser = validationToken.getCurrentUser(tokenString);
        List<ItripListCommentVO> itripListCommentVOS = new ArrayList<>();
        List<ItripComment> itripComments = new ArrayList<>();
        Page<ItripListCommentVO> page = new Page<>();
        try {
//            if (null != currentUser) {
            PageInfo pageInfo = commentService.getItripCommentByComment(itripSearchCommentVO);
            itripComments = pageInfo.getList();
            if (EmptyUtils.isNotEmpty(itripComments)) {
                itripListCommentVOS = itripComments.stream()
                        .map(e -> new ItripListCommentVO(e.getId(),null,null,new Date(),null,e.getTripMode(),e.getContent(),e.getCreationDate(),e.getScore(),e.getIsHavingImg()))
                        .collect(Collectors.toList());
            }

            page.setBeginPos(pageInfo.getFirstPage());
            page.setCurPage(pageInfo.getPageNum());
            page.setTotal((int)pageInfo.getTotal());
            page.setPageSize(itripSearchCommentVO.getPageSize());
            page.setPageCount(pageInfo.getSize());
            for (ItripListCommentVO itripListCommentVO : itripListCommentVOS) {
                log.info(itripListCommentVO.getContent());
            }
            page.setRows(itripListCommentVOS);
//            }
        } catch (Exception e) {
            DtoUtil.returnFail("系统异常", "10205");
            e.printStackTrace();
        }
        return DtoUtil.returnSuccess("获取评论内容列表成功",page);
    }*/
}


