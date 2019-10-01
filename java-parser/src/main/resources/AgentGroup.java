package domain;

import com.spectra.pilot.share.domain.NameValue;
import com.spectra.pilot.share.domain.NameValueList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentGroup extends AgentGroupKey {

    private String parentId;
    private String name;
    private String createdBy;
    private long createdAt;

    public AgentGroup(AgentGroupKey agentGroupKey, String parentId, String name, String createdBy) {
        super(agentGroupKey);
        this.parentId = parentId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = System.currentTimeMillis();
    }

    public AgentGroup(AgentGroupKey agentGroupKey, String parentId, String name, String createdBy, long createdAt) {
        super(agentGroupKey);
        this.parentId = parentId;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public void setValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.list()) {
            switch (nameValue.getName()) {
                case "parentId":
                    this.parentId = nameValue.getValue();
                    break;
                case "name":
                    this.name = nameValue.getValue();
                    break;
                case "createdBy":
                    this.createdBy = nameValue.getValue();
                    break;
                default:
                    throw new IllegalArgumentException("Update not allowed: " + nameValue);
            }
        }
    }

    public String toString() {
        return toJson();
    }

    public static AgentGroup sample() {
        return new AgentGroup(new AgentGroupKey("attic"), "1234", "그룹1", "rudaks");
    }

    public static void main(String[] args) {
        System.out.println(sample());
    }
}
