package com.elearningplatform.util;


import com.elearningplatform.dto.response.DailycoRes.DailyMeetingTokenResponse;
import com.elearningplatform.dto.response.DailycoRes.DailyRoomResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class DailyCoService {

    private final WebClient webClient;
    private final String apiKey;
    @Value("${dailyco.base-url}")
    private String baseUrl;

    public DailyCoService(@Value("${dailyco.api-key}") String apiKey) {
        this.apiKey = apiKey;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<DailyRoomResponse> createRoom(String roomName, Map<String, Object> properties) {
        return webClient.post()
                .uri("/rooms")
                .bodyValue(properties)
                .retrieve()
                .bodyToMono(DailyRoomResponse.class);
    }

    public Mono<DailyRoomResponse> getRoom(String roomName) {
        return webClient.get()
                .uri("/rooms/" + roomName)
                .retrieve()
                .bodyToMono(DailyRoomResponse.class);
    }

    public Mono<Void> deleteRoom(String roomName) {
        return webClient.delete()
                .uri("/rooms/" + roomName)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<DailyMeetingTokenResponse> createMeetingToken(
            String roomName, Map<String, Object> properties) {
        return webClient.post()
                .uri("/meeting-tokens")
                .bodyValue(properties)
                .retrieve()
                .bodyToMono(DailyMeetingTokenResponse.class);
    }
}
