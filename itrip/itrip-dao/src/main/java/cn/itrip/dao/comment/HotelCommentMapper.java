package cn.itrip.dao.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.beans.vo.comment.ItripScoreCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelCommentMapper {

     public ItripComment getHotelByScore (@Param("hotelId") Long id)  throws Exception ;

     public ItripComment getHotelCount(@Param("hotelId") Long id )  throws Exception ;

     public List<ItripComment> getHoteComment(@Param("hotelId") Long id)throws Exception ;

}
