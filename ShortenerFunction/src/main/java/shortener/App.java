package shortener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    static final Map<String, String> urls = Map.of(
            "/repo", "https://github.com/faermanj/url-shortener",
            "/gitpod", "https://gitpod/#github.com/faermanj/url-shortener"
    );

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input,
                                                      final Context context) {
        var logger = context.getLogger();
        var headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");
        var response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        var output = "";
        int statusCode = 404;
        var path = input.getPath();
        var url = urls.get(path);
        if (url != null){
            statusCode = 302;
            headers.put("Location", url);
            output = "Redirecting to " + url;
        }
        logger.log(String.format("%s => %d %s", path, statusCode, url));
        return response.withStatusCode(statusCode)
                    .withBody(output);

    }


}
