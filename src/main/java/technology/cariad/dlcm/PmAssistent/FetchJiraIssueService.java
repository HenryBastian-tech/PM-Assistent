package technology.cariad.dlcm.PmAssistent;

import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonClassDescription;

public class FetchJiraIssueService implements Function<
    technology.cariad.dlcm.PmAssistent.FetchJiraIssueService.Request, 
    technology.cariad.dlcm.PmAssistent.FetchJiraIssueService.Response
>{
    
    private final JiraClient jiraClient;

    @JsonClassDescription("The string that contains an unique identifier for a Jira issue which is also called issue key. The format can be described as the following, it starts with capital letters is followed by a hyphen and ends with a decimal number, e.g. PROJECT-1234. Here is a regex for that pattern [A-Z]+-\\d+")
    public record Request(String issueKey) {}
    @JsonClassDescription("The string represents a Jira issue in JSON format.")
    public record Response(String issue) {}
    
    public FetchJiraIssueService(JiraClient jiraClient) {
        this.jiraClient = jiraClient;
    }

    public Response apply(Request request) {
        try {
            return new Response(jiraClient.fetchIssueByKey(request.issueKey));
        } catch (Exception e) {
            System.err.println("Error fetching issue for key " + request.issueKey + ": " + e.getMessage());
            return null;
        }
    }
}
