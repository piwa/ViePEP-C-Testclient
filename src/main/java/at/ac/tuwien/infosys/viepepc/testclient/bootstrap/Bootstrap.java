package at.ac.tuwien.infosys.viepepc.testclient.bootstrap;

import at.ac.tuwien.infosys.viepepc.database.bootstrap.containers.ContainerConfigurationsReader;
import at.ac.tuwien.infosys.viepepc.database.bootstrap.vmTypes.VmTypesReaderImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by philippwaibel on 04/05/16.
 */
@Component
@Slf4j
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ContainerConfigurationsReader containerConfigurationsReader;
    @Autowired
    private VmTypesReaderImpl vmTypesReader;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        containerConfigurationsReader.readContainerConfigurations();
        vmTypesReader.readVMTypes();
    }

}
