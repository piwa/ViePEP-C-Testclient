package at.ac.tuwien.infosys.viepepc.registry.impl.container;


/**
 * Created by philippwaibel on 07/12/2016.
 */
public class ContainerImageNotFoundException extends Exception {
    private static final String DEFAULT_MESSAGE = "Container image not found in registry";

    public ContainerImageNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ContainerImageNotFoundException(String message) {
        super(message);
    }

}