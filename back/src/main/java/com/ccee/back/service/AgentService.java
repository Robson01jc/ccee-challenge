package com.ccee.back.service;

import com.ccee.back.domain.Agents;
import com.ccee.back.entity.Agent;
import com.ccee.back.entity.Region;
import com.ccee.back.repository.AgentRepository;
import com.ccee.back.repository.RegionRepository;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    private final RegionRepository regionRepository;

    public AgentService(AgentRepository agentRepository, RegionRepository regionRepository) {
        this.agentRepository = agentRepository;
        this.regionRepository = regionRepository;
    }

    public void save(Agents agents) {
        agents.getAgents().forEach(agent -> {
            System.out.println(agent.getCode());
            var managedAgent = this.agentRepository.save(Agent.fromDomain(agent));

            agent.getRegion().forEach(region -> {
                var transientRegion = Region.fromDomain(region);
                transientRegion.setAgent(managedAgent);

                this.regionRepository.save(transientRegion);
            });
        });
    }
}
