import com.unicom.wogame.util.HttpClientUtil;
import org.apache.http.NameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestZteIf extends AbstractJUnit4SpringContextTests {

//    @Autowired
//    private GameService gameService;

    @Test
    public void gameDetail() {

        String url = "http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid={serviceid}&productid={productid}&state={state}";
        Map<String, Object> urlVariables = new HashMap<String, Object>();

        urlVariables.put("serviceid", "productdetail");
        urlVariables.put("productid", "187834");
        urlVariables.put("state", "101");

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.setContentLength(57);
        headers.set("user-agent", "Anroid/Lenovo798T");
        headers.set("storeua", "Anroid/Lenovo798T");
        headers.set("x-up-calling-line-id", "00000000000");
        headers.set("handphone", "00000000000");
        headers.set("handua", "9000000000");
        headers.set("settertype", "3");
        headers.set("version", "3");
        headers.set("imei", "000000000000000");
        headers.set("imsi", "000000000000000");
        headers.set("preassemble", "Android-v16>Common>V2.0.0>20120328>NA>NA>NA>beiyong>NA>NA");
        headers.set("companylogo", "18150");
        headers.set("sessionid", "202dc1f08ee64f8f896fafc3c5c62c03");
        headers.set("appfrom", "openfeint");
        headers.set("newclient", "1");
        headers.set("phoneAccessMode", "3");
        headers.set("usertype", "3");
        headers.set("clientchannelflag", "8");


        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, urlVariables);

        System.out.println(response);

    }

    @Test
    public void gameDetail2() {

        String url = "http://client.wostore.cn:6106/appstore_agent/unistore/servicedata.do?serviceid=productdetail&productid=187834&state=101";

        Map<String, String> headers = new HashMap<String, String>();

        headers.put("user-agent", "Anroid/Lenovo798T");
        headers.put("storeua", "Anroid/Lenovo798T");
        headers.put("x-up-calling-line-id", "00000000000");
        headers.put("handphone", "00000000000");
        headers.put("handua", "9000000000");
        headers.put("settertype", "3");
        headers.put("version", "3");
        headers.put("imei", "000000000000000");
        headers.put("imsi", "000000000000000");
        headers.put("preassemble", "Android-v16>Common>V2.0.0>20120328>NA>NA>NA>beiyong>NA>NA");
        headers.put("companylogo", "18150");
        headers.put("sessionid", "202dc1f08ee64f8f896fafc3c5c62c03");
        headers.put("appfrom", "openfeint");
        headers.put("newclient", "1");
        headers.put("phoneAccessMode", "3");
        headers.put("usertype", "3");
        headers.put("clientchannelflag", "3");

        String response = HttpClientUtil.get(url, new ArrayList<NameValuePair>(), headers);

        System.out.println(response);

    }

}
