package technology.cariad.dlcm.PmAssistent;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PmAssistentController {

    private final ChatClient pmAssisChatClient;
    private final JiraAgent jiraAgent;

    public PmAssistentController(ChatModel pmAssistenChatModel, JiraAgent jiraAgent) {

        this.pmAssisChatClient = ChatClient.builder(pmAssistenChatModel)
            .defaultSystem("You are a friendly chat bot that helps to create Jira issues in working environment that uses Scaled Agile Framework. You're working on all levels of SAFe. Therefore, your working items are capabilities / capability enablers, features / feature enablers and user stories / user story enablers.")
            .build();
        this.jiraAgent = jiraAgent;
    
    }
    
    @GetMapping("/ai/completion")
	Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Hi! Introduce yourself.") String message) {
        
        List<String> jiraIssues = jiraAgent.fetchIssues(message);

        String issuesDetails = new String();
        // Append the Jira issues to a string representation
        for (String issue : jiraIssues) {
            issuesDetails += issue + "\n";
        }

        // Append the issues details to the original message
        String augmentedMessage = message + "\n\nRelated Jira Issues:\n" + issuesDetails;

		return Map.of("completion",
            pmAssisChatClient.prompt()
				.user(augmentedMessage)
				.call()
				.content());

	}
    
}
