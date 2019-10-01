package com.spectra.pilot.mocha.agentgroup.domain;

import com.spectra.pilot.share.util.JsonSerializable;
import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class AgentGroupKey implements JsonSerializable, Serializable {
    private String applicationId;
    private String id;

    public AgentGroupKey(AgentGroupKey agentGroupKey) {
        this.applicationId = agentGroupKey.getApplicationId();
        this.id = agentGroupKey.getId();
    }

    public AgentGroupKey(String applicationId) {
        this.applicationId = applicationId;
        this.id = UUID.randomUUID().toString();
    }

    public static AgentGroupKey sample() {
        return new AgentGroupKey("attic", "1234");
    }

    public AgentGroupKey getAgentGroupKey() {
        return this;
    }

    @Override
    public String toString() {
        return toJson();
    }
}
