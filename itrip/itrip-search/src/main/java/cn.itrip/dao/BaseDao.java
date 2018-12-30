package cn.itrip.dao;

import cn.itrip.common.Constants;
import cn.itrip.common.EmptyUtils;
import cn.itrip.common.Page;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

public class BaseDao<T> {
    private HttpSolrClient httpSolrClient = null;
    private QueryResponse queryResponse = null;

    public BaseDao(String url) {
        //初始化HttpSolrClient
        httpSolrClient = new HttpSolrClient(url);
        // 设置响应解析器
        httpSolrClient.setParser(new XMLResponseParser());
        // 建立连接的最长时间
        httpSolrClient.setConnectionTimeout(500);
    }

    //使用SolrQuery 查询列表数据
    public List<T> queryList(SolrQuery query,Integer pageSize, Class clazz) {
        System.out.println("method queryList=====================");
        //设置起始页
        query.setStart(0);
        //一页显示多少条
        query.setRows(EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize);
        List<T> list = null;
        try {
            queryResponse = httpSolrClient.query(query);
            list = queryResponse.getBeans(clazz);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Page<T> queryPage(SolrQuery query, Integer pageNo,
                             Integer pageSize, Class clazz) throws Exception {
        //设置起始页
        Integer rows = EmptyUtils.isEmpty(pageSize) ? Constants.DEFAULT_PAGE_SIZE : pageSize;
        Integer currPage = (EmptyUtils.isEmpty(pageNo) ? Constants.DEFAULT_PAGE_NO - 1 : pageNo);
        Integer start = currPage*rows;
        query.setStart(start);//设置查询结果的起始位置
        query.setRows(rows);//一页显示多少
        //solr客户端对象请求solr服务,得到一个响应对象
        QueryResponse queryResponse = httpSolrClient.query(query);
        //封装了多条数据的document集合
        SolrDocumentList docs = queryResponse.getResults();
        //把结果封装成一个page对象
        Page<T> page = new Page(currPage + 1, query.getRows(),
                new Long(docs.getNumFound()).intValue());
        //从响应中取出数据
        List<T> list = queryResponse.getBeans(clazz);
        page.setRows(list);
        return page;
    }
}
