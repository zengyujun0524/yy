package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import org.apache.ibatis.annotations.Param;

public interface HotelCommentService {
    //得到评分
    public ItripComment getHotelByScore (Long id)  throws Exception ;

    //得到评论数量
    public ItripComment getHotelCount(@Param("hotelId") Long id )  throws Exception ;
}
