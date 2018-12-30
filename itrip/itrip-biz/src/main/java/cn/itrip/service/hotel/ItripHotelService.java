package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import org.apache.ibatis.annotations.Param;

public interface ItripHotelService {
    public ItripHotel getItripHote () throws  Exception;
     //查看酒店设施
    public ItripHotel getHotelFacilities(@Param("cityId") Integer cityId )throws  Exception;
    //查询酒店政策
    public  ItripHotel getHotelPolicy(@Param("cityId") Integer cityId)throws Exception;
}
