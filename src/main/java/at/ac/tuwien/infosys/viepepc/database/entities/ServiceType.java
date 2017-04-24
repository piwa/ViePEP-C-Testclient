package at.ac.tuwien.infosys.viepepc.database.entities;

/**
 * Created by Philipp Hoenisch on 6/16/14.
 */
public enum ServiceType {

    Service1("service1", 45, 450, 1000 * 40, 2, false),
    Service2("service2", 75, 720, 1000 * 80, 4, false),
    Service3("service3", 75, 720, 1000 * 120, 6, true),
    Service4("service4", 100, 960, 1000 * 40, 2, false),
    Service5("service5", 120, 1150, 1000 * 100, 4, false),
    Service6("service6", 125, 1150, 1000 * 20, 6, true),
    Service7("service7", 150, 1440, 1000 * 40, 2, false),
    Service8("service8", 175, 1680, 1000 * 20, 4, false),
    Service9("service9", 250, 2400, 1000 * 60, 6, true),
    Service10("service10", 333, 3200, 1000 * 30, 2, false),
    Service11("service11", 333, 3200, 1000 * 30, 2, false);

    ServiceType(String name, double cpuLoad, double memoryInByte, long makeSpan, double dataToTransfer, boolean onlyInternal) {
        this.name = name;
        this.cpuLoad = cpuLoad;
        this.memory = memoryInByte;
        this.makeSpan = makeSpan;
        this.dataToTransfer = dataToTransfer;
        this.onlyInternal = onlyInternal;
    }

    final double cpuLoad;
    final double memory;
    final long makeSpan;
    final String name;
    final double dataToTransfer;
    final boolean onlyInternal;

    public static ServiceType fromString(String serviceType) {
        switch (serviceType) {
            case "service1":
                return Service1;
            case "service2":
                return Service2;
            case "service3":
                return Service3;
            case "service4":
                return Service4;
            case "service5":
                return Service5;
            case "service6":
                return Service6;
            case "service7":
                return Service7;
            case "service8":
                return Service8;
            case "service9":
                return Service9;
            case "service10":
                return Service10;
            case "service11":
                return Service11;
            default:
                return Service1;
        }
    }

    public double getCpuLoad() {
        return cpuLoad;
    }
    
    public double getMemory() {
    	return memory;
    }

    public long getMakeSpan() {
        return makeSpan;
    }

    public String getName() {
        return name;
    }

    public double getDataToTransfer() {
        return dataToTransfer;
    }

    public boolean isOnlyInternal() {
        return onlyInternal;
    }
}
