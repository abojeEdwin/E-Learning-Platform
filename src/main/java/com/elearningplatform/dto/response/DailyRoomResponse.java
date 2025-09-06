package com.elearningplatform.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class DailyRoomResponse {

    private String id;
    private String name;
    private String url;
    private Map<String, Object> config;
    private String api_created;
    private String privacy;
    private String created_at;

}
