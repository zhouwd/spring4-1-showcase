package com.github.zhangkaitao.proto;

import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.protobuf.ExtensionRegistryInitializer;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.github.zhangkaitao.web.controller.MyExtensionRegistryInitializer;

/**
* User: zhangkaitao
* Date: 14-8-5
* Time: ����8:48
* Version: 1.0
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring-mvc.xml")
@WebAppConfiguration
public class ProtoTest2 {

    @Autowired
    private WebApplicationContext context;

    MockMvc mockMvc;
    MockRestServiceServer mockRestServiceServer;
    RestTemplate restTemplate;


    @Before
    public void setup() throws Exception {
        mockMvc = webAppContextSetup(context).apply(defaultSetup()).build();

        restTemplate = new RestTemplate();
        ExtensionRegistryInitializer extensionRegistryInitializer = new MyExtensionRegistryInitializer();
        ProtobufHttpMessageConverter protobufHttpMessageConverter =
                new ProtobufHttpMessageConverter(extensionRegistryInitializer);
        restTemplate.getMessageConverters().add(0, protobufHttpMessageConverter);

        mockRestServiceServer = createServer(restTemplate);
    }

    private MockMvcConfigurer defaultSetup() {
        return new MockMvcConfigurer() {
            @Override
            public void afterConfigurerAdded(ConfigurableMockMvcBuilder<?> configurableMockMvcBuilder) {
                configurableMockMvcBuilder.alwaysExpect(status().isOk());
            }
            @Override
            public RequestPostProcessor beforeMockMvcCreated(ConfigurableMockMvcBuilder<?> configurableMockMvcBuilder, WebApplicationContext webApplicationContext) {
                return new RequestPostProcessor() {
                    @Override
                    public MockHttpServletRequest postProcessRequest(MockHttpServletRequest mockHttpServletRequest) {
                        mockHttpServletRequest.setAttribute("aa", "aa");
                        return mockHttpServletRequest;
                    }
                };
            }
        };
    }

//    @Test
//    public void testRead() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/proto/read")).andReturn();
//
//        Assert.assertEquals("aa", mvcResult.getRequest().getAttribute("aa"));
//
//        String response = mvcResult.getResponse().getContentAsString();
//        mockRestServiceServer
//                .expect(requestTo("/proto/read"))
//                .andExpect(method(HttpMethod.POST))
//                .andRespond(withSuccess(response, ProtobufHttpMessageConverter.PROTOBUF));
//
//        HttpHeaders headers = new HttpHeaders();
//        RequestEntity<UserProtos.User> requestEntity =
//                new RequestEntity<UserProtos.User>(headers, HttpMethod.POST, URI.create("/proto/read"));
//
//        ResponseEntity<UserProtos.User> responseEntity =
//                restTemplate.exchange(requestEntity, UserProtos.User.class);
//
//        System.out.println(responseEntity.getBody());
//    }
//
}
