package cn.itrip.dao.impl;
import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.dao.BaseDao;
import cn.itrip.dao.ItripHotelDao;
import org.apache.solr.client.solrj.SolrQuery;

import java.util.List;

public class  ItripHotelDaoImpl implements ItripHotelDao {
    public static String url = "http://localhost:8081/solr/hotel/";
    private BaseDao<ItripHotelVO> ItripHotelVOBaseDao = new BaseDao<ItripHotelVO>(url);
    public List<ItripHotelVO> queryItripHotelList(String keyword) {
        Integer pageSize=0;
        SolrQuery solrQuery = new SolrQuery("keyword:" + keyword);
        List<ItripHotelVO> list = ItripHotelVOBaseDao.queryList(solrQuery,pageSize,ItripHotelVO.class);
        return list;
    }
}
