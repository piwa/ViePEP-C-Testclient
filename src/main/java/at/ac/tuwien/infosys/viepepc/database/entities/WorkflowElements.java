package at.ac.tuwien.infosys.viepepc.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class WorkflowElements {

    @XmlElementWrapper(name="workflowElements")
    @XmlElement(name="workflowElement")
    private List<WorkflowElement> workflowElements = new ArrayList<>();

}
