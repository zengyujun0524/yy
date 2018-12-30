
import cn.itrip.service.SearchHotelService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestService {


    @Test
    public void testfindAll() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext-mybatis.xml");
        SearchHotelService searchHotelService = ctx.getBean(SearchHotelService.class);
        searchHotelService.searchItripHotelListByHotCityId(2,6);
        System.out.println("ok");
    }
}
