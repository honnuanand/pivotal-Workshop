package io.pivotal.greeting;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ExtendedModelMap;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class GreetingControllerTest {

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Autowired
  private GreetingController controller;

  @MockBean
  private EurekaClient discoveryClient;

  @Before
  public void setup() {
    String stubUrl = String.format("http://localhost:%s/", wireMockRule.port());

    stubFor(get(urlEqualTo("/"))
        .willReturn(aResponse()
          .withStatus(200)
          .withHeader("Content-Type", "application/json")
          .withBody("bachi bouzouk")
        ));

    InstanceInfo instanceInfo = mock(InstanceInfo.class);
    when(instanceInfo.getHomePageUrl()).thenReturn(stubUrl);
    when(discoveryClient.getNextServerFromEureka(anyString(), anyBoolean())).thenReturn(instanceInfo);
  }

  @Test
  public void shouldGetFortune() {
    ExtendedModelMap model = new ExtendedModelMap();
    controller.getGreeting(model);
    verify(getRequestedFor(urlMatching("/")));
    assertThat(model.get("fortune")).isEqualTo("bachi bouzouk");
  }

}
