package com.ccee.back.controller;

import com.ccee.back.controller.response.MessageResponse;
import com.ccee.back.domain.Agents;
import com.ccee.back.service.AgentService;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/agents")
@CrossOrigin("http://localhost:4200")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            Agents agents = xmlMapper.readValue(file.getBytes(), Agents.class);
            this.agentService.save(agents);

            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("File uploaded successfully!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Could not process file"));
        }
    }
}
