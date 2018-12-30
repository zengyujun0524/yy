package cn.itrip.service.room;

import cn.itrip.beans.pojo.ItripHotelRoom;
import cn.itrip.dao.hotelroom.HotelroomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
    @Service
public class HotelRoomServiceImpl  implements   HotelRoomService  {

    @Resource
        HotelroomMapper hotelroomMapper;

    @Override
    public List<ItripHotelRoom> getItripRoomBedList() throws Exception {
        return hotelroomMapper.getItripRoomBedList();
    }
}
