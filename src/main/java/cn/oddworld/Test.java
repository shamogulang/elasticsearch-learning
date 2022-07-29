package cn.oddworld;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        CloseableHttpClient instace = HttpClientFactory.getInstace();

        HttpPost httpPost  = new HttpPost("http://www.baidu.com");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("charset", "UTF-8");
        httpPost.setHeader("UsefileMateBeanr-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36");

        //把认证信息发到header中
        Long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; i++){
            try {
                Header[] allHeaders1 = httpPost.getAllHeaders();
                CloseableHttpResponse response = instace.execute(httpPost);
                if(response.getStatusLine().getStatusCode() == 200){
                    System.out.println("成功");
                    Header[] allHeaders = response.getAllHeaders();
                    for (Header allHeader : allHeaders) {

                        System.out.println(allHeader.getName()+" == "+ allHeader.getValue());
                    }
                }else {
                    System.out.println("是吧");
                }
//                String hl = EntityUtils.toString(response.getEntity(), "UTF-8");
//                System.out.println(hl);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
