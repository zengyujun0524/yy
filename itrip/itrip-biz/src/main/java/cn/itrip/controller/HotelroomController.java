package cn.itrip.controller;

import cn.itrip.beans.dtos.Dto;
import cn.itrip.beans.pojo.ItripAreaDic;
import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.beans.pojo.ItripUser;
import cn.itrip.beans.pojo.ItripUserLinkUser;
import cn.itrip.beans.vo.ItripAreaDicVO;
import cn.itrip.beans.vo.hotelroom.ItripHotelRoomVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.service.room.HotelRoomService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotelroom")
public class HotelroomController {

    private Logger logger = Logger.getLogger(HotelController.class);

    @Resource
    private HotelRoomService hotelRoomService;

    /***
     * 查询酒店床型
     * @param request
     * @return
     */
     @ResponseBody
    @RequestMapping(value = "/queryhotelroombed", method = RequestMethod.GET, produces = "application/json")
    public Dto queryhotelroombed( HttpServletRequest request) {
         List<ItripHotelRoom> itripHotelRooms =null;
         List<ItripHotelRoomVO> itripHotelRoomVOS=null;
         try {
             itripHotelRooms=hotelRoomService.getItripRoomBedList();
             itripHotelRoomVOS = new ArrayList();
             for (ItripHotelRoom dic : itripHotelRooms) {
                 ItripHotelRoomVO vo = new ItripHotelRoomVO();
                 BeanUtils.copyProperties(dic, vo);
                 itripHotelRoomVOS.add(vo);
             }
         }catch (Exception e){
              e.printStackTrace();
         }
               return DtoUtil.returnDataSuccess(itripHotelRoomVOS);
     }
}
