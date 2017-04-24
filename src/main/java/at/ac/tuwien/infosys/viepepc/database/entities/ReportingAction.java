package at.ac.tuwien.infosys.viepepc.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sauron
 * Date: 05.02.14
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Getter
@Setter
public class ReportingAction implements Serializable {

    /**
     * database id
     * important: this id is used to identify a vm in the program
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String virtualMachineID;

    @Enumerated(EnumType.STRING)
    private Action vmAction;

    private Date timestamp;

    public ReportingAction() {
    }

    public ReportingAction(Date date, String vmID, Action action) {
        this.timestamp = date;
        this.virtualMachineID = vmID;
        this.vmAction = action;
    }


}
