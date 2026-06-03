package app.promptlang.service;

import app.promptlang.dto.GeneratedCodeResponse;
import app.promptlang.model.ChatHistory;
import app.promptlang.repository.ChatHistoryRepository;
import app.promptlang.repository.UserRepository;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.StructuredChatCompletionCreateParams;

import org.springframework.stereotype.Service;
import app.promptlang.model.User;

@Service
public class ChatService {

    private final ChatHistoryRepository chatHistoryRepository;
    private final UserRepository userRepository;

    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();

     public ChatService(ChatHistoryRepository chatHistoryRepository, UserRepository userRepository) {
        this.chatHistoryRepository = chatHistoryRepository;
        this.userRepository = userRepository;
    }


    public GeneratedCodeResponse generateResponse(String message, String username) {
           User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        StructuredChatCompletionCreateParams<GeneratedCodeResponse> params =
                ChatCompletionCreateParams.builder()
                        .addSystemMessage("""
                                You are a programming assistant.

                                Rules:
                                - Generate clean code
                                - Explain the code simply
                                - Default language is Java
                                - No markdown fences
                                """)
                        .addUserMessage(message)
                        .model(ChatModel.GPT_5_2)
                        .responseFormat(GeneratedCodeResponse.class)
                        .build();

        GeneratedCodeResponse response = client.chat()
                .completions()
                .create(params)
                .choices()
                .get(0)
                .message()
                .content()
                .get();

                ChatHistory chatHistory = new ChatHistory(
                message,
                response.getCode(),
                response.getExplanation(), user
        );

                chatHistoryRepository.save(chatHistory);

                System.out.println(response.getCode());
                System.out.println(response.getExplanation());

        return response;
    }
}