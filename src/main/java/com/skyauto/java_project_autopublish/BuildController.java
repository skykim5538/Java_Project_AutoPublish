package com.skyauto.java_project_autopublish;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BuildController {
    private final ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/api/build", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> buildInfo() throws IOException {
        File f = new File("/opt/demo/build.json");
        Map<String, Object> info = new HashMap<>();
        if (f.exists()) {
            return mapper.readValue(f, Map.class);
        }
        info.put("commit", "unknown");
        info.put("builtAt", java.time.Instant.now().toString());
        return info;
    }
}
