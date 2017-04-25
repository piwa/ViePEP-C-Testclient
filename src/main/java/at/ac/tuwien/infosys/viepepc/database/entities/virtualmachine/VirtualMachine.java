package at.ac.tuwien.infosys.viepepc.database.entities.virtualmachine;

import at.ac.tuwien.infosys.viepepc.database.entities.services.ServiceType;
import at.ac.tuwien.infosys.viepepc.database.entities.container.Container;
import at.ac.tuwien.infosys.viepepc.database.inmemory.services.CacheVirtualMachineService;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: sauron
 * Date: 05.02.14
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "VirtualMachine")
@Getter
@Setter
public class VirtualMachine implements Serializable {

    @Autowired
    @Transient
    private CacheVirtualMachineService cacheVirtualMachineService;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="serviceTypeId")
    private ServiceType serviceType;

    private String name;
    private String instanceId;
    private String location;
    private boolean leased = false;
    private String ipAddress;
    private long startupTime;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startedAt;
    private boolean started;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime toBeTerminatedAt;
    private String resourcepool;

    @ManyToOne(cascade = CascadeType.ALL)
    private VMType vmType;

    @ElementCollection
    private List<String> usedPorts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy="virtualMachine")
    private Set<Container> deployedContainers = new HashSet<>();



    public VirtualMachine(String name, Integer numberCores, ServiceType serviceType, String location) {
        this.name = name;
        this.serviceType = serviceType;
        this.location = location;
        try {
            this.vmType = cacheVirtualMachineService.getVmTypeFromCore(numberCores, location);
        } catch (Exception e) {
        }
    }

    public VirtualMachine(String name, VMType vmType, ServiceType serviceType) {
        this.name = name;
        this.location = vmType.getLocation();
        this.serviceType = serviceType;
    }

    public VirtualMachine(String name, VMType vmType) {
        this.name = name;
        this.location = vmType.getLocation();
        this.vmType = vmType;
    }

    public VirtualMachine() {
    }
    
    public boolean isContainerDeployed(Container container) {
        return deployedContainers.contains(container);
    }

    @Override
    public int hashCode() {
    	if(id == null){
    		return 0;
    	}
        return Math.toIntExact(id);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        VirtualMachine other = (VirtualMachine) obj;

        return (this.id != null) && (other.id != null) && (this.id.intValue() == other.id.intValue());

    }

    @Override
    public String toString() {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        String startString = startedAt == null ? "NOT_YET" : dtfOut.print(startedAt);
        String toBeTerminatedAtString = toBeTerminatedAt == null ? "NOT_YET" : dtfOut.print(toBeTerminatedAt);
        return "VirtualMachine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instanceId='" + instanceId + '\'' +
                ", serviceType=" + serviceType +
                ", leased=" + leased +
                ", startedAt=" + startString +
                ", terminateAt=" + toBeTerminatedAtString +
                ", location=" + location +
                ", ip adress=" + ipAddress +
                '}';
    }

    public String getURI() {
        return "http://" + this.ipAddress;
    }

    public void terminate() {
        this.setLeased(false);
        this.setStarted(false);
        this.setStartedAt(null);
        this.setToBeTerminatedAt(null);
        this.serviceType = null;
    }

    public void undeployContainer(Container container) {
        this.deployedContainers.remove(container);
    }

}
