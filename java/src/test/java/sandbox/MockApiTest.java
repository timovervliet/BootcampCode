package sandbox;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.*;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.*;
import org.mockserver.verify.VerificationTimes;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockserver.character.Character.NEW_LINE;
import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpForward.Scheme.HTTP;
import static org.mockserver.model.HttpForward.forward;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.StringBody.exact;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;

public class MockApiTest {
    private ClientAndProxy proxy;

    private static ClientAndServer mockServer;

    @BeforeClass
    public static void startServer() {
        mockServer = startClientAndServer(1080);
    }

    private void createExpectationForGet() {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/index.html"),
                        exactly(10))
                .respond(
                        response()
                                .withStatusCode(401)
                                .withHeader(new Header("Content-Type", "application/json"))
                                .withBody("" +
                                        "[" + NEW_LINE +
                                        "    {" + NEW_LINE +
                                        "        \"id\": \"1\"," + NEW_LINE +
                                        "        \"title\": \"Xenophon's imperial fiction : on the education of Cyrus\"," + NEW_LINE +
                                        "        \"author\": \"James Tatum\"," + NEW_LINE +
                                        "        \"isbn\": \"0691067570\"," + NEW_LINE +
                                        "        \"publicationDate\": \"1989\"" + NEW_LINE +
                                        "    }," + NEW_LINE +
                                        "    {" + NEW_LINE +
                                        "        \"id\": \"2\"," + NEW_LINE +
                                        "        \"title\": \"You are here : personal geographies and other maps of the imagination\"," + NEW_LINE +
                                        "        \"author\": \"Katharine A. Harmon\"," + NEW_LINE +
                                        "        \"isbn\": \"1568984308\"," + NEW_LINE +
                                        "        \"publicationDate\": \"2004\"" + NEW_LINE +
                                        "    }," + NEW_LINE +
                                        "    {" + NEW_LINE +
                                        "        \"id\": \"3\"," + NEW_LINE +
                                        "        \"title\": \"You just don't understand : women and men in conversation\"," + NEW_LINE +
                                        "        \"author\": \"Deborah Tannen\"," + NEW_LINE +
                                        "        \"isbn\": \"0345372050\"," + NEW_LINE +
                                        "        \"publicationDate\": \"1990\"" + NEW_LINE +
                                        "    }" +
                                        "]")
                                //.withBody("Contact!!")
                );
    }

    private void createExpectationForInvalidAuth() {
        new MockServerClient("127.0.0.1", 1080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/validate")
                                .withHeader("\"Content-type\", \"application/json\"")
                                .withBody(exact("{username: 'foo', password: 'bar'}")),
                        exactly(10))
                .respond(
                        response()
                                .withStatusCode(401)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"),
                                        new Header("Cache-Control", "public, max-age=86400"))
                                .withBody("{ message: 'incorrect username and password combination' }")
                                .withDelay(TimeUnit.SECONDS,1)
                );
    }

    private org.apache.http.HttpResponse hitTheServerWithPostRequest() {
        String url = "http://127.0.0.1:1080/validate";
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json");
        org.apache.http.HttpResponse response=null;

        try {
            StringEntity stringEntity = new StringEntity("{username: 'foo', password: 'bar'}");
            post.getRequestLine();
            post.setEntity(stringEntity);
            response=client.execute(post);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    private void verifyPostRequest() {
        new MockServerClient("localhost", 1080).verify(
                request()
                        .withMethod("POST")
                        .withPath("/validate")
                        .withBody(exact("{username: 'fo', password: 'bar'}")),
                VerificationTimes.exactly(1)
        );
    }

    private void verifyGetRequest() {
        given().when().get("http://localhost:1080/index.html").then().assertThat().statusCode(200);

        System.out.println(given().when().get("http://localhost:1080/index.html").body().prettyPrint());
    }

    @Test
    public void whenPostRequestMockServer_thenServerReceived() throws InterruptedException {
        createExpectationForInvalidAuth();
        hitTheServerWithPostRequest();
        Thread.sleep(10000);
        verifyPostRequest();
    }

    @Test
    public void validateJsonRequest() throws InterruptedException {
        createExpectationForGet();
        Thread.sleep(10000);
        verifyGetRequest();
    }

    @AfterClass
    public static void stopServer() {
        mockServer.stop();
    }
}
