package technology.cariad.dlcm.PmAssistent;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JiraClient {
    
    private final String jiraUrl = "https://devstack.vwgroup.com/jira/rest/api/2/issue/";
    @Value("${atlassian.jira.bearer-token}")
    private String jiraBearerToken;

    public String fetchIssueByKey(String issueKey) throws Exception {
        
        URI uri = URI.create(jiraUrl + issueKey);
          
        // Create HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Create HttpRequest with URI and headers
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .header("Authorization", "Bearer " + jiraBearerToken)
            .header("Content-Type", "application/json")
            .GET()
            .build();

        // Send the request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if response is successful (status 200 OK)
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            System.err.println("Failed to fetch issue: " + issueKey + " - HTTP status " + response.statusCode());
            return null;
        }
    }
}
