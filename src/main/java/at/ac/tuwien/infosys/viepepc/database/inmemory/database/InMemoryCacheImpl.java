package at.ac.tuwien.infosys.viepepc.database.inmemory.database;

import at.ac.tuwien.infosys.viepepc.database.entities.container.ContainerConfiguration;
import at.ac.tuwien.infosys.viepepc.database.entities.virtualmachine.VMType;
import at.ac.tuwien.infosys.viepepc.database.entities.virtualmachine.VirtualMachine;
import at.ac.tuwien.infosys.viepepc.database.entities.workflow.WorkflowElement;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Getter
public class InMemoryCacheImpl {
    private List<WorkflowElement> runningWorkflows = new ArrayList<>();
    private List<WorkflowElement> allWorkflowInstances = new ArrayList<>();
    private Map<VMType, List<VirtualMachine>> vmTypeVmMap = new HashMap<>();
    private List<ContainerConfiguration> containerConfigurations = new ArrayList<>();

    public void clear() {
        runningWorkflows = new ArrayList<>();
        allWorkflowInstances = new ArrayList<>();
        vmTypeVmMap = new HashMap<>();
        containerConfigurations = new ArrayList<>();
    }

    public void addRunningWorkflow(WorkflowElement workflowElement) {
        runningWorkflows.add(workflowElement);
    }

    public void addToAllWorkflows(WorkflowElement workflowElement) {
        allWorkflowInstances.add(workflowElement);
    }

    public void addContainerConfiguration(ContainerConfiguration containerConfiguration) {
        containerConfigurations.add(containerConfiguration);
    }

    public void addVMType(VMType vmType) {
        vmTypeVmMap.put(vmType, new ArrayList<VirtualMachine>());
    }

    public void addAllVMType(List<VMType> vmTypes) {
        for(VMType vmType : vmTypes) {
            addVMType(vmType);
        }
    }

    public void addVirtualMachine(VirtualMachine vm) {
    	if(!vmTypeVmMap.containsKey(vm.getVmType())) {
    		addVMType(vm.getVmType());
    	}
        vmTypeVmMap.get(vm.getVmType()).add(vm);
    }
    
    public void addVirtualMachines(List<VirtualMachine> virtualMachines) {
    	for(VirtualMachine vm : virtualMachines) {
    		addVirtualMachine(vm);
    	}
    }

    public void addAllContainerConfiguration(List<ContainerConfiguration> configurations) {
        for(ContainerConfiguration configuration : configurations) {
            addContainerConfiguration(configuration);
        }
    }
}
