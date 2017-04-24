package at.ac.tuwien.infosys.viepepc.database.entities.docker;

/**
 */
public enum DockerConfiguration {

    MICRO_CORE(0.5, 512, 30),
    SINGLE_CORE(1, 1024, 30),
    //SINGLE_CORE(1, 300, 30),
    DUAL_CORE(2, 2 * 1024, 30),
    QUAD_CORE(4, 4 * 1024, 30);
//    HEXA_CORE(8, 8 * 1024, 30);

    public final String id;
    public final double cores; //amount of needed VCores
    public final double ram; //amount of needed memory in mb
    public final double disc; //amount of needed disc space in mb

    DockerConfiguration(double cores, double ram, double disc) {
        this.id = "c" + String.valueOf(cores);
        this.cores = cores;
        this.ram = ram;
        this.disc = disc;
    }
    
    public double getCPUPoints(){
    	return (cores*100)*0.9;
    }

	public double getRAM() {
		return ram;
	}
}
