package cn.itrip.dao.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItripHotelMapper {

    public List<ItripHotel> getItripHotelListByMap(Map<String, Object> param) throws Exception;

    public ItripHotel getItripHote() throws Exception;
    //酒店设施
    public ItripHotel getHotelFacilities(@Param("cityId") Integer cityId)throws Exception;
    //酒店政策
    public  ItripHotel getHotelPolicy(@Param("cityId") Integer cityId)throws Exception;

}
