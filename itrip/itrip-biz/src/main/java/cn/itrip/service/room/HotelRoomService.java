package cn.itrip.service.room;

import cn.itrip.beans.pojo.ItripHotelRoom;

import java.util.List;

public interface HotelRoomService {
    public List<ItripHotelRoom> getItripRoomBedList()throws Exception;
}
