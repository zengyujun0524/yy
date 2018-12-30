package cn.itrip.test;
import cn.itrip.beans.vo.hotel.ItripHotelVO;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

public class Test {
    public static String url = "http://localhost:8081/solr/hotel/";
   /* public static void main(String[] args) {
        //初始化HttpSolrClient
        HttpSolrClient httpSolrClient = new HttpSolrClient(url);
        // 设置响应解析器
        httpSolrClient.setParser(new XMLResponseParser());
        // 建立连接的最长时间
        httpSolrClient.setConnectionTimeout(500);


        //初始化SolrQuery 模糊查找(SolrQuery query = new SolrQuery("address:天安门");)
        SolrQuery query = new SolrQuery("*:*");
        System.out.println("method ==============1111111111111111111");
        //设置fq  精确查找
        //query.addFilterQuery();
        query.setSort("id", SolrQuery.ORDER.desc);
        //从第一条开始显示
        query.setStart(0);
        //一页显示多少条
        query.setRows(10);

        QueryResponse queryResponse = null;
        System.out.println("method ==============2222222222222222222222");
        try {
            queryResponse = httpSolrClient.query(query);
            System.out.println("method ==============333333333333333333333333333");
            List<ItripHotelVO> list = queryResponse.getBeans(ItripHotelVO.class);
            System.out.println("method ==============4444444444444444444");
            for (ItripHotelVO ItripHotelVO : list) {
                System.out.println("method ==============55555555555555555555");
                System.out.println(ItripHotelVO.getId() + " " + ItripHotelVO.getAddress());
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
   public static void main(String[] args) {
       String str = "what,is,,,,";
       String[] strs = str.split(",", -1);
       for (String s : strs) {
           System.out.println("=========="+s);
       }
   }
}
