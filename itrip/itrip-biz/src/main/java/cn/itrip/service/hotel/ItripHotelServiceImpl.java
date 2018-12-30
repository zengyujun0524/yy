package cn.itrip.service.hotel;

import cn.itrip.beans.pojo.ItripHotel;
import cn.itrip.dao.hotel.ItripHotelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItripHotelServiceImpl  implements ItripHotelService{

    @Resource
    ItripHotelMapper itripHotelMapper;

    @Override
    public ItripHotel getItripHote() throws Exception {
        return itripHotelMapper.getItripHote();
    }

    @Override
    public ItripHotel getHotelFacilities(Integer cityId) throws Exception {
        return itripHotelMapper.getHotelFacilities(cityId);
    }

    @Override
    public ItripHotel getHotelPolicy(Integer cityId) throws Exception {
        return itripHotelMapper.getHotelPolicy(cityId);
    }
}
