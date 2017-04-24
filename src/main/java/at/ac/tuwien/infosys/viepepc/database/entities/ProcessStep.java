package at.ac.tuwien.infosys.viepepc.database.entities;


import at.ac.tuwien.infosys.viepepc.database.entities.docker.DockerContainer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Represents the smallest element of the workflow model.
 *
 * @author Waldemar Ankudin modified by Turgay Sahin and Mathieu Muench, Gerta Sheganaku
 */
@XmlRootElement(name = "ProcessStep")
@Entity(name = "ProcessStep")
@PrimaryKeyJoinColumn(name="id")
@Table(name="ProcessStepElement")
@DiscriminatorValue(value = "process_step")
@Getter
@Setter
public class ProcessStep extends Element {

    @XmlElement(name = "serviceType")
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private Date startDate;

    private boolean isScheduled;
    private Date scheduledStartedAt;
    private int numberOfExecutions;

    @ManyToOne//(cascade = CascadeType.ALL)	//TODO: CHECK
    private VirtualMachine scheduledAtVM;
    
    @ManyToOne//(cascade = CascadeType.ALL)
    private DockerContainer scheduledAtContainer;

    
    private String workflowName;

    @XmlElementWrapper(name="restrictedVMs")
    @XmlElement(name="restricted" )
    @ElementCollection(fetch= FetchType.EAGER)
    private List<Integer> restrictedVMs;

    /**
     * this flag is used to determine weather a step has to be executed or not, i.g. in an xor only one path has to be executed, every other not
     */
    private boolean hasToBeExecuted = true;

    /**
     * Constructor for the process. Contains:
     * <p/>
     * - name of the process step n - service list in process step n - list of
     * all ppu services (xVarList) - list of all flatrate services (yVarList) -
     * list of all hybrid services (zVarList)
     *  @param name     stepID
     * @param executed was already executed
     * @param serviceType
     * @param workflowName
     */
    public ProcessStep(String name, boolean executed, ServiceType serviceType, String workflowName) {
        this.name = name;
        if (executed) {
            finishedAt = new Date();
        }
        this.serviceType = serviceType;
        this.workflowName = workflowName;
        restrictedVMs = new ArrayList<>();
    }

    /**
     * Constructor for the process. Contains:
     * <p/>
     * - name of the process step n - service list in process step n - list of
     * all ppu services (xVarList) - list of all flatrate services (yVarList) -
     * list of all hybrid services (zVarList)
     *  @param name stepID
     * @param serviceType
     * @param workflowName
     */
    public ProcessStep(String name, ServiceType serviceType, String workflowName) {
        this.name = name;
        this.serviceType = serviceType;
        this.workflowName = workflowName;
        restrictedVMs = new ArrayList<>();
    }

    public ProcessStep() {
        restrictedVMs = new ArrayList<>();
    }

    @XmlTransient
    @Override
    public List<Element> getElements() {
        return super.getElements();
    }

    /**
     * @DS: Gibt eine Array mit der aggregierten QoS zurueck
     * [0] Execution Time, [1] Cost, [2] Reliability, [Throughput]
     */
    public long calculateQoS() {
    	return getRemainingExecutionTime(new Date());
    }

    public boolean hasBeenExecuted() {
        return this.finishedAt != null;
    }

    public long getExecutionTime() {
        return serviceType.makeSpan;
    }

    /**
     * calculated the remaining execution time
     *
     * @param date
     * @return
     */
    public long getRemainingExecutionTime(Date date) {
        long time = date.getTime();
        if (startDate != null) {
            time = startDate.getTime();
        }
        long difference = date.getTime() - time;
        long remaining = serviceType.makeSpan - difference;
        return remaining > 0 ? remaining : serviceType.makeSpan ;
    }

    public void setScheduledForExecution(boolean isScheduled, Date tau_t, VirtualMachine vm) {
        this.isScheduled = isScheduled;
        this.scheduledStartedAt = tau_t;
        this.scheduledAtVM = vm;
    }

    public void setScheduledForExecution(boolean isScheduled, Date tau_t, DockerContainer container) {
        this.isScheduled = isScheduled;
        this.scheduledStartedAt = tau_t;
        this.scheduledAtContainer = container;
    }
    @Override
    public ProcessStep getLastExecutedElement() {
        return this;
    }
    
    public void setStartDate(Date date){
    	this.startDate = date;
    	if(date != null) {
    		numberOfExecutions++;
    	}
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String startDateformat = startDate != null ? formatter.format(startDate) : null;
        String finishedAtformat = finishedAt != null ? formatter.format(finishedAt) : null;
        String vmName = scheduledAtVM != null ? scheduledAtVM.getName() : null;
        String dockerName = scheduledAtContainer != null ? scheduledAtContainer.getName() : null;
        return "ProcessStep{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", serviceType=" + serviceType +
                ", startDate=" + startDateformat +
                ", finishedAt=" + finishedAtformat +
                ", scheduledAtVM=" + vmName +
                ", scheduledAtContainer=" + dockerName +
                ", lastElement=" + isLastElement() +
                '}';
    }

    public void setHasBeenExecuted(boolean hasBeenExecuted) {
        if (hasBeenExecuted) {
            finishedAt = new Date();
        } else finishedAt = null;
    }

    public List<Integer> getRestrictedVMs() {
        List<Integer> objects = new ArrayList<>();
        objects.addAll(restrictedVMs);
        return objects;
    }

    
    
    @Deprecated
    public void reset() {
        this.setFinishedAt(null);
        this.setStartDate(null);
        this.setScheduledAtVM(null);
        this.setScheduledAtContainer(null);
        this.setHasBeenExecuted(false);
        this.setScheduled(false);
        this.setScheduledStartedAt(null);
    }
}
