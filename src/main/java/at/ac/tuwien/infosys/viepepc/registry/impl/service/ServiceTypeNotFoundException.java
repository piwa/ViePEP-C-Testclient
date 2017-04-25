package at.ac.tuwien.infosys.viepepc.registry.impl.service;


/**
 * Created by philippwaibel on 07/12/2016.
 */
public class ServiceTypeNotFoundException extends Exception {
    private static final String DEFAULT_MESSAGE = "ServiceType not found in registry";

    public ServiceTypeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ServiceTypeNotFoundException(String message) {
        super(message);
    }

}