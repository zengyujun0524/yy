package cn.itrip.dao.hotelroom;

import cn.itrip.beans.pojo.ItripHotelRoom;
import java.util.List;
import java.util.Map;

public interface HotelroomMapper {
    public List<ItripHotelRoom> getItripRoomBedList()throws Exception;

}
