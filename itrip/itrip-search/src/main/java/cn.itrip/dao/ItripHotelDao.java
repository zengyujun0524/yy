package cn.itrip.dao;

import cn.itrip.beans.vo.hotel.ItripHotelVO;
import java.util.List;

public interface ItripHotelDao {
    public List<ItripHotelVO> queryItripHotelList(String keyword);
}
