package cn.itrip.service;
import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.Page;
import java.util.List;

public interface SearchHotelService {

    /**
     * 根据条件查询酒店
     */


    public List<ItripHotelVO> searchItripHotelListByHotCityId(Integer cityId, Integer count) throws Exception;

    public Page<ItripHotelVO> searchItripHotelPage(SearchHotelVO searchHotelVO, Integer pageNo, Integer pageSize) throws Exception;
}
