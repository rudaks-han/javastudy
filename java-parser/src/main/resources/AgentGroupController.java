package com.spectra.pilot.mocha.agentgroup.controller;

import com.spectra.pilot.mocha.agentgroup.service.AgentGroupService;
import com.spectra.pilot.mocha.agentgroup.service.sdo.AgentGroupCdo;
import com.spectra.pilot.mocha.agentgroup.service.sdo.AgentGroupRdo;
import com.spectra.pilot.mocha.agentgroup.service.sdo.AgentGroupUdo;
import com.spectra.pilot.mocha.agentgroup.service.sdo.AgentGroupsRdo;
import com.spectra.pilot.share.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("agentGroups")
public class AgentGroupController {

    @Autowired
    private AgentGroupService agentGroupService;

    @GetMapping
    public AgentGroupsRdo findTickets(
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit
    ) {

        return agentGroupService.findAgentGroups(new Page(offset, limit));
    }

    @GetMapping("{id}")
    public AgentGroupRdo findAgentGroup(
            @PathVariable String id
    ) {
        return agentGroupService.findAgentGroup(id);
    }

    @PostMapping
    public AgentGroupRdo registerAgentGroup(
            @RequestBody AgentGroupCdo agentGroupCdo
    ) {
        return agentGroupService.registerAgentGroup(agentGroupCdo);
    }

    @PutMapping("{id}")
    public AgentGroupRdo modifyAgentGroup(
            @PathVariable String id,
            @RequestBody AgentGroupUdo agentGroupUdo
    ) {
        return agentGroupService.modifyAgentGroup(id, agentGroupUdo.getNameValueList());
    }

    @DeleteMapping("{id}")
    public void removeAgentGroup(
            @PathVariable String id
    ) {
        agentGroupService.removeAgentGroup(id);
    }
}
