package com.github.zhangkaitao.proto;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.http.converter.protobuf.ExtensionRegistryInitializer;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.github.zhangkaitao.web.controller.MyExtensionRegistryInitializer;

/**
* User: zhangkaitao
* Date: 14-8-5
* Time: ����8:48
* Version: 1.0
*/
public class ProtoTest {

    static RestTemplate restTemplate;
    String baseUri = "http://localhost:8080/";

    private static Server server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        //����һ��server
        server = new Server(8080);
        WebAppContext context = new WebAppContext();
        String webapp = "spring4.1-mvc/src/main/webapp";
        context.setDescriptor(webapp + "/WEB-INF/web.xml");  //ָ��web.xml�����ļ�
        context.setResourceBase(webapp);  //ָ��webappĿ¼
        context.setContextPath("/");
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
        restTemplate = new RestTemplate();
        ExtensionRegistryInitializer extensionRegistryInitializer = new MyExtensionRegistryInitializer();
        ProtobufHttpMessageConverter protobufHttpMessageConverter =
                new ProtobufHttpMessageConverter(extensionRegistryInitializer);
        restTemplate.getMessageConverters().add(0, protobufHttpMessageConverter);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.stop(); //�����Խ���ʱֹͣ������
    }


   /* @Test
    public void testRead() {
        HttpHeaders headers = new HttpHeaders();
        RequestEntity<UserProtos.User> requestEntity =
                new RequestEntity<UserProtos.User>(headers, HttpMethod.POST, URI.create(baseUri + "/proto/read"));

        ResponseEntity<UserProtos.User> responseEntity =
                restTemplate.exchange(requestEntity, UserProtos.User.class);

        System.out.println(responseEntity.getBody());
    }

    @Test
    public void testWrite() {
        UserProtos.User user = UserProtos.User.newBuilder().setId(1).setName("zhangsan").build();
        HttpHeaders headers = new HttpHeaders();
        RequestEntity<UserProtos.User> requestEntity =
                new RequestEntity<UserProtos.User>(user, headers, HttpMethod.POST, URI.create(baseUri + "/proto/write"));

        ResponseEntity<UserProtos.User> responseEntity =
                restTemplate.exchange(requestEntity, UserProtos.User.class);
        System.out.println(responseEntity.getBody());
    }
*/

}
