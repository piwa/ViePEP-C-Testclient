package at.ac.tuwien.infosys.viepepc.registry.impl.container;

/**
 * Created by philippwaibel on 07/12/2016.
 */
public class ContainerConfigurationNotFoundException extends Exception {
    private static final String DEFAULT_MESSAGE = "Container configuration not found in registry";

    public ContainerConfigurationNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ContainerConfigurationNotFoundException(String message) {
        super(message);
    }

}