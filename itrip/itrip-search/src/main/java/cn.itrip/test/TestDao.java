package cn.itrip.test;

import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.dao.ItripHotelDao;
import cn.itrip.dao.impl.ItripHotelDaoImpl;

import java.util.List;

public class TestDao {
    public static void main(String[] args) {
        ItripHotelDao hotelDao = new ItripHotelDaoImpl();

        List<ItripHotelVO> list = hotelDao.queryItripHotelList("北0京");
        System.out.println( list+ " 11111" );
        for (ItripHotelVO hotel : list) {
            System.out.println(hotel.getId() + " 11111" );
        }
    }
}
