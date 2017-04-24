package at.ac.tuwien.infosys.viepepc.database.entities.docker;

import at.ac.tuwien.infosys.viepepc.database.entities.ServiceType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Gerta Sheganaku
 */
@XmlRootElement(name = "DockerImage")
@Entity
@Table(name = "DockerImage")
public class DockerImage {

    public DockerImage() {
    }

    public DockerImage(String serviceName, String repoName, String imageName, Integer externPort, Integer internPort) {
        this.serviceName = serviceName;
        this.serviceType = ServiceType.fromString(serviceName);
        this.repoName = repoName;
        this.imageName = imageName;
        this.internPort = internPort;
        this.externPort = externPort;
    }

    @Id
    private String serviceName;
    private ServiceType serviceType;
    private String imageName;
    private String repoName;
    private Integer internPort;
    private Integer externPort;


    public String getImageName() {
        return imageName;
    }

    public String getRepoName() {
        return repoName;
    }
    
    public ServiceType getServiceType() {
    	return this.serviceType;
    }

    public String getFullName() {
        return String.format("%s/%s", repoName, imageName);
    }

    public Integer getInternPort() {
        return internPort;
    }

    public void setInternPort(Integer internPort) {
        this.internPort = internPort;
    }

    public Integer getExternPort() {
        return externPort;
    }

    public void setExternPort(Integer externPort) {
        this.externPort = externPort;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((imageName == null) ? 0 : imageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DockerImage other = (DockerImage) obj;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		return true;
	}

}
