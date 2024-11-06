package technology.cariad.dlcm.PmAssistent;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PmAssistentController {

    private final ChatClient pmAssistentChatClient;

    public PmAssistentController(ChatClient pmAssistenChatClient) {

        this.pmAssistentChatClient = pmAssistenChatClient;
    
    }
    
    @GetMapping("/ai/completion")
	Map<String, String> completion(@RequestParam(value = "message", defaultValue = "Hi! Introduce yourself.") String message) {
             
        return Map.of("completion",
            pmAssistentChatClient.prompt()
                .user(message)
                .call()
                .content());
	
    }
}
