package technology.cariad.dlcm.PmAssistent;

import java.util.function.Function;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

@Configuration  
public class PmAssistenChatModelConfig {

    private final JiraClient jiraClient;

    public PmAssistenChatModelConfig(JiraClient jiraClient){
        this.jiraClient = jiraClient;
    }
        
    @Bean
    ChatClient chatClient(ChatModel chatModel) {
        return ChatClient
            .builder(chatModel)
            .defaultFunctions("retrieveJiraIssue")
            .defaultSystem("You are a friendly chat bot that helps to create Jira issues in working environment that uses Scaled Agile Framework. You're working on all levels of SAFe. Therefore, your working items are capabilities / capability enablers, features / feature enablers and user stories / user story enablers.")
            .build();
    }

    @Bean
	@Description("Fetch a Jira issue from Jira instance to retrieve it's content.")
	public Function<FetchJiraIssueService.Request, FetchJiraIssueService.Response> retrieveJiraIssue() {
		return new FetchJiraIssueService(jiraClient);
	}

}
