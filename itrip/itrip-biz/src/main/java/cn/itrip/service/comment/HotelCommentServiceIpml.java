package cn.itrip.service.comment;

import cn.itrip.beans.pojo.ItripComment;
import cn.itrip.dao.comment.HotelCommentMapper;
import cn.itrip.dao.hotelroom.HotelroomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HotelCommentServiceIpml implements HotelCommentService {
    @Resource
    private HotelCommentMapper hotelCommentMapper;
    @Override
    public ItripComment getHotelByScore(Long id) throws Exception {
        return hotelCommentMapper.getHotelByScore(id);
    }

    @Override
    public ItripComment getHotelCount(Long id) throws Exception {
        return hotelCommentMapper.getHotelCount(id);
    }
}
