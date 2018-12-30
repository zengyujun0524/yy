package cn.itrip.service;
import cn.itrip.beans.vo.hotel.ItripHotelVO;
import cn.itrip.beans.vo.hotel.SearchHotelVO;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import cn.itrip.dao.BaseDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
@Service
public class SerachHotelServiceImpl implements SearchHotelService {
    public static String URL ="http://localhost:8081/solr/hotel/";
    private BaseDao<ItripHotelVO> itripHotelVOBaseDao = new BaseDao(URL);

    /**
     * 根据多个条件查询分页
     *
     */

    @Override
    public List<ItripHotelVO> searchItripHotelListByHotCityId(Integer cityId, Integer count) throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        if (EmptyUtils.isNotEmpty(cityId)) {
            query.addFilterQuery("cityId:" + cityId);
        } else {
            return null;
        }
        List<ItripHotelVO> hotelVOList = itripHotelVOBaseDao.queryList(query, count, ItripHotelVO.class);
        return hotelVOList;
    }

    @Override
    public Page<ItripHotelVO> searchItripHotelPage(SearchHotelVO searchHotelVO, Integer pageNo, Integer pageSize) throws Exception {
        SolrQuery query = new SolrQuery("*:*");
        StringBuffer q = new StringBuffer();
        if (EmptyUtils.isNotEmpty(searchHotelVO)) {      //如果searchHotelVO不为空,拼接查询条件
            if (EmptyUtils.isNotEmpty(searchHotelVO.getHotelLevel())) {
                System.out.println("11111111111111111111==========="+searchHotelVO.getHotelLevel());
                System.out.println("q========================="+q);
                //酒店星级作为过滤条件
                query.addFilterQuery("hotelLevel:" + searchHotelVO.getHotelLevel() + "");
            }

            boolean hasDestination = false;
            if (EmptyUtils.isNotEmpty(searchHotelVO.getDestination())) {
                System.out.println("222222222222222222222==========="+searchHotelVO.getDestination());
                System.out.println("q========================="+q);
                //目的作为q的关键字条件
                q.append(" destination :" + searchHotelVO.getDestination());
                hasDestination = true;
            }

            if (EmptyUtils.isNotEmpty(searchHotelVO.getKeywords())) {//keyword作为q的关键字
                if (hasDestination) {
                    System.out.println("222222222222222222222==========="+searchHotelVO.getKeywords());
                    q.append(" AND keyword :" + searchHotelVO.getKeywords());
                    System.out.println("q========================="+q);
                } else {
                    System.out.println("else==========="+searchHotelVO.getKeywords());
                    q.append(" keyword :" + searchHotelVO.getKeywords());
                    System.out.println("q========================="+q);
                }
            }

            if (EmptyUtils.isNotEmpty(searchHotelVO.getFeatureIds())) {//酒店特色作为q的关键字
                StringBuffer buffer = new StringBuffer("(");
                int flag = 0;
                System.out.println("searchHotelVO.getFeatureIds()============"+searchHotelVO.getFeatureIds());
                String featureIdArray[] = searchHotelVO.getFeatureIds().split(",");
                for (String featureId : featureIdArray) {
                    if (flag == 0) {
                        buffer.append(" featureIds:" + "*," + featureId + ",*");
                        System.out.println("buffer======if============="+buffer);
                    } else {
                        buffer.append(" OR featureIds:" + "*," + featureId + ",*");
                        System.out.println("buffer=========other=========="+buffer);
                    }
                    flag++;
                }
                buffer.append(")");
                System.out.println("buffer=========out=========="+buffer);
                query.addFilterQuery(buffer.toString());
            }

            if (EmptyUtils.isNotEmpty(searchHotelVO.getTradeAreaIds())) {//商圈作为过滤条件
                StringBuffer buffer = new StringBuffer("(");
                int flag = 0;
                String tradeAreaIdArray[] = searchHotelVO.getTradeAreaIds().split(",");
                for (String tradeAreaId : tradeAreaIdArray) {
                    if (flag == 0) {
                        buffer.append(" tradingAreaIds:" + "*," + tradeAreaId + ",*");
                        System.out.println("buffer2222222======if============="+buffer);
                    } else {
                        buffer.append(" OR tradingAreaIds:" + "*," + tradeAreaId + ",*");
                        System.out.println("buffer222222======other============="+buffer);
                    }
                    flag++;
                }
                buffer.append(")");
                query.addFilterQuery(searchHotelVO.toString());
                System.out.println("query====================="+query);
            }

            if (EmptyUtils.isNotEmpty(searchHotelVO.getMaxPrice())) {//最高价作为过滤条件
                query.addFilterQuery("maxPrice:" + "[* TO " + searchHotelVO.getMaxPrice() + "]");
            }

            if (EmptyUtils.isNotEmpty(searchHotelVO.getMinPrice())) {//最低价作为过滤条件
                query.addFilterQuery("minPrice:" + "[" + searchHotelVO.getMinPrice() + " TO *]");
            }

            if (EmptyUtils.isNotEmpty(searchHotelVO.getAscSort())) {//排序规则
                query.addSort(searchHotelVO.getDescSort(), SolrQuery.ORDER.desc);
            }
        }
        if (EmptyUtils.isNotEmpty(q.toString())) {
            query.setQuery(q.toString());
        }
        Page<ItripHotelVO> page = itripHotelVOBaseDao.queryPage(//请求到solr服务
                query, pageNo, pageSize, ItripHotelVO.class);
        return page;
    }
}
