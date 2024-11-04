package technology.cariad.dlcm.PmAssistent;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JiraAgent {

    // Define pattern for identifying potential Jira issue keys like "PROJECT-123"
    private static final Pattern ISSUE_KEY_PATTERN = Pattern.compile("\\b[A-Z]+-\\d+\\b");

    private final JiraClient jiraClient;

    public JiraAgent(JiraClient jiraClient) {

        this.jiraClient = jiraClient;

    }

    public List<String> fetchIssues(String text) {
        
        List<String> issues = new ArrayList<String>();

        // Regular expression to match Jira issue keys (e.g., "PROJECT-123")
        Pattern pattern = ISSUE_KEY_PATTERN;
        Matcher matcher = pattern.matcher(text);
        
        // Find all matches and add them to the list
        while (matcher.find()) {
            String issueKey = matcher.group();
            // Fetch issue by key
            try {
                String issue = jiraClient.fetchIssueByKey(issueKey); // Fetch the issue details
                if (issue != null) {
                    issues.add(issue); // Add to the list if found
                }
            } catch (Exception e) {
                System.err.println("Error fetching issue for key " + issueKey + ": " + e.getMessage());
            }
        }
        
        return issues;
    }
    
}
