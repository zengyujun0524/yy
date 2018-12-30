package cn.itrip.controller;
import cn.itrip.beans.dto.Dto;
import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotCityVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.DtoUtil;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;

import cn.itrip.service.SearchHotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 酒店信息Controller
 * <p/>
 * 包括API接口：
 * 1、根据酒店id查询酒店拓展属性
 * 2、根据酒店id查询酒店介绍，酒店政策，酒店设施
 * 3、根据酒店id查询酒店特色属性列表
 * 4、根据type 和target id 查询酒店图片
 * 5、查询热门城市
 * 6、查询热门商圈列表（搜索-酒店列表）
 * 7、查询数据字典特色父级节点列表（搜索-酒店列表）
 * 8、根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）
 * <p/>
 * 注：错误码（100201 ——100300）
 * <p/>
 * Created by hanlu on 2017/5/9.
 */

@Controller
@Api(value = "API", basePath = "/http://api.itrap.com/api")
@RequestMapping(value = "/api/hotellist")
public class HotelListController {

    private Logger logger = Logger.getLogger(HotelListController.class);

    @Resource
    private SearchHotelService searchHotelService;

/*    @Resource
    private ItripHotelDao itripHotelDao;*/

    /****
     * 根据热门城市查询6个酒店的接口
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "根据热门城市查询6个酒店", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询国内、国外的热门城市(1:国内 2:国外)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>10201 : hotelId不能为空 </p>" +
            "<p>10202 : 系统异常,获取失败</p>")
    @RequestMapping(value = "/searchItripHotelListByHotCity", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripHotelVO>> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO searchHotCityVO) throws Exception {
        System.out.println("method searchItripHotelListByHotCity===============");
        System.out.println("SearchHotCityVO cityId"+searchHotCityVO.getCityId());
        System.out.println("SearchHotCityVO cityId"+searchHotCityVO.getCount());
        if(EmptyUtils.isEmpty(searchHotCityVO) || EmptyUtils.isEmpty(searchHotCityVO.getCityId())){
            return DtoUtil.returnFail("城市id不能为空", "20004");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("cityId", searchHotCityVO.getCityId());
        List<ItripHotelVO> itripHotelVOList = searchHotelService.searchItripHotelListByHotCityId(searchHotCityVO.getCityId(), searchHotCityVO.getCount());
        return DtoUtil.returnDataSuccess(itripHotelVOList);
    }

    /****
     * 根据热门城市查询6个酒店的接口
     *
     * @param
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "搜索酒店（分页）", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = Dto.class, notes = "查询酒店分页(用于查询酒店列表)" +
            "<p>成功：success = ‘true’ | 失败：success = ‘false’ 并返回错误码，如下：</p>" +
            "<p>错误码：</p>" +
            "<p>20001 : 系统异常，获取失败 </p>" +
            "<p>20002 : 目的地不能为空</p>")
    @RequestMapping(value = "/searchItripHotelPage", produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public Dto<Page<ItripHotelVO>> searchItripHotelPage(@RequestBody SearchHotelVO searchHotelVO) throws Exception {
        System.out.println("method searchItripHotelPage===============");
        Page page=null;
        if(EmptyUtils.isEmpty(searchHotelVO) || EmptyUtils.isEmpty(searchHotelVO.getDestination())){
            return DtoUtil.returnFail("目的地不能为空", "20002");
        }
        page=searchHotelService.searchItripHotelPage(searchHotelVO,searchHotelVO.getPageNo(),searchHotelVO.getPageSize());
        return DtoUtil.returnDataSuccess(page);
    }

}
