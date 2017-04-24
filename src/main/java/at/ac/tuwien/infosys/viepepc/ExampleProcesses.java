package at.ac.tuwien.infosys.viepepc;

import at.ac.tuwien.infosys.viepepc.database.entities.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Philipp Hoenisch on 6/20/14.
 */
@Component
public class ExampleProcesses {

    public WorkflowElement getCloudProcess1(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");
        ProcessStep elem1 = new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName());
        seq.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".2", ServiceType.Service2, workflow.getName());
        seq.addElement(elem2);
        ProcessStep elem = new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName());
        elem.setLastElement(true);
        seq.addElement(elem);
        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getCloudProcess2(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");

        ANDConstruct andConstruct = new ANDConstruct(name + "-1-AND");
        ProcessStep elem = new ProcessStep(name + ".1.1", ServiceType.Service1, workflow.getName());
        elem.setRestrictedVMs(Arrays.asList(4, 5, 6));
        elem.setLastElement(true);
        andConstruct.addElement(elem);
        ProcessStep elem1 = new ProcessStep(name + ".1.2", ServiceType.Service2, workflow.getName());
        elem1.setRestrictedVMs(Arrays.asList(4, 5, 6));
        elem1.setLastElement(true);
        andConstruct.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".1.3", ServiceType.Service3, workflow.getName());
        elem2.setRestrictedVMs(Arrays.asList(4, 5, 6));
        elem2.setLastElement(true);
        andConstruct.addElement(elem2);

        seq.addElement(andConstruct);

        workflow.addElement(seq);

        return workflow;
    }


    public WorkflowElement getCloudProcess3(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");

        XORConstruct xor = new XORConstruct(name + "-1-XOR");
        ProcessStep elem = new ProcessStep(name + ".1.1", ServiceType.Service1, workflow.getName());
        elem.setLastElement(true);
        xor.addElement(elem);
        ProcessStep elem1 = new ProcessStep(name + ".1.2", ServiceType.Service2, workflow.getName());
        elem1.setLastElement(true);
        xor.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".1.3", ServiceType.Service3, workflow.getName());
        elem2.setLastElement(true);
        xor.addElement(elem2);

        seq.addElement(xor);

        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getCloudProcess4(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");

        ANDConstruct andConstruct = new ANDConstruct(name + "-1-AND");
        ProcessStep elem1 = new ProcessStep(name + ".1.1", ServiceType.Service1, workflow.getName());
        andConstruct.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".1.2", ServiceType.Service2, workflow.getName());
        andConstruct.addElement(elem2);
        seq.addElement(andConstruct);

        XORConstruct xorConstruct = new XORConstruct(name + "-3-XOR");
        ProcessStep elem3 = new ProcessStep(name + ".3.1", ServiceType.Service3, workflow.getName());
        xorConstruct.addElement(elem3);
        elem3.setRestrictedVMs(Arrays.asList(4, 5, 6));
        elem3.setLastElement(true);
        ProcessStep elem4 = new ProcessStep(name + ".3.2", ServiceType.Service4, workflow.getName());
        xorConstruct.addElement(elem4);
        elem4.setRestrictedVMs(Arrays.asList(4, 5, 6));
        elem4.setLastElement(true);

        ProcessStep elem5 = new ProcessStep(name + ".3.3", ServiceType.Service5, workflow.getName());
        xorConstruct.addElement(elem5);
        elem5.setRestrictedVMs(Arrays.asList(4, 5, 6));
        elem5.setLastElement(true);
        seq.addElement(xorConstruct);


        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getCloudProcess5(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-1-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        ANDConstruct andConstruct = new ANDConstruct(name + "-2-AND");
        andConstruct.addElement(new ProcessStep(name + ".2.1", ServiceType.Service2, workflow.getName()));

        Sequence seq2 = new Sequence(name + "-3-seq");
        seq2.addElement(new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName()));
        LoopConstruct loopConstruct = new LoopConstruct(name + "-3-loop");
        ProcessStep elem = new ProcessStep(name + "-3-loop-1", ServiceType.Service4, workflow.getName());
        elem.setLastElement(true);
        loopConstruct.addElement(elem);

        seq2.addElement(loopConstruct);

        andConstruct.addElement(seq2);

        seq.addElement(andConstruct);
        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess1(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");
        ProcessStep elem1 = new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName());
        seq.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".2", ServiceType.Service2, workflow.getName());
        seq.addElement(elem2);
        ProcessStep elem = new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName());
        elem.setLastElement(true);
        seq.addElement(elem);
        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess2(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");

        XORConstruct xor = new XORConstruct(name + "-1-XOR");
        ProcessStep elem = new ProcessStep(name + ".1.1", ServiceType.Service1, workflow.getName());
        elem.setLastElement(true);
        xor.addElement(elem);
        ProcessStep elem1 = new ProcessStep(name + ".1.2", ServiceType.Service2, workflow.getName());
        elem1.setLastElement(true);
        xor.addElement(elem1);

        seq.addElement(xor);

        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess3(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");

        ANDConstruct andConstruct = new ANDConstruct(name + "-1-AND");
        ProcessStep elem = new ProcessStep(name + ".1.1", ServiceType.Service1, workflow.getName());
        elem.setLastElement(true);
        andConstruct.addElement(elem);
        ProcessStep elem1 = new ProcessStep(name + ".1.2", ServiceType.Service2, workflow.getName());
        elem.setLastElement(true);
        andConstruct.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".1.3", ServiceType.Service3, workflow.getName());
        elem2.setLastElement(true);
        andConstruct.addElement(elem2);

        seq.addElement(andConstruct);

        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess4(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        ANDConstruct andConstruct = new ANDConstruct(name + "-2-AND");
        andConstruct.addElement(new ProcessStep(name + ".2.1", ServiceType.Service2, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.2", ServiceType.Service3, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.3", ServiceType.Service4, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.4", ServiceType.Service5, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.5", ServiceType.Service6, workflow.getName()));
        seq.addElement(andConstruct);

        ANDConstruct andConstruct2 = new ANDConstruct(name + "-3-AND");
        ProcessStep elem1 = new ProcessStep(name + ".3.1", ServiceType.Service7, workflow.getName());
        elem1.setLastElement(true);
        andConstruct2.addElement(elem1);

        ProcessStep elem = new ProcessStep(name + ".3.2", ServiceType.Service8, workflow.getName());
        elem.setLastElement(true);
        andConstruct2.addElement(elem);

        seq.addElement(andConstruct2);

        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess5(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");

        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        XORConstruct xor = new XORConstruct(name + "-2-XOR");
        ProcessStep elem1 = new ProcessStep(name + ".2.1", ServiceType.Service2, workflow.getName());
        elem1.setLastElement(true);
        xor.addElement(elem1);
        ProcessStep elem = new ProcessStep(name + ".2.2", ServiceType.Service3, workflow.getName());
        elem.setLastElement(true);
        xor.addElement(elem);

        seq.addElement(xor);

        workflow.addElement(seq);


        return workflow;
    }


    public WorkflowElement getProcess6(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        ANDConstruct andConstruct = new ANDConstruct(name + "-2-AND");
        andConstruct.addElement(new ProcessStep(name + ".2.1", ServiceType.Service2, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.2", ServiceType.Service3, workflow.getName()));
        seq.addElement(andConstruct);

        XORConstruct xorConstruct = new XORConstruct(name + "-3-XOR");
        xorConstruct.addElement(new ProcessStep(name + ".3.1", ServiceType.Service4, workflow.getName()));
        xorConstruct.addElement(new ProcessStep(name + ".3.2", ServiceType.Service5, workflow.getName()));
        xorConstruct.addElement(new ProcessStep(name + ".3.3", ServiceType.Service6, workflow.getName()));
        seq.addElement(xorConstruct);


        seq.addElement(new ProcessStep(name + ".4", ServiceType.Service7, workflow.getName()));

        seq.addElement(new ProcessStep(name + ".5", ServiceType.Service8, workflow.getName()));

        ProcessStep elem = new ProcessStep(name + ".6", ServiceType.Service9, workflow.getName());
        elem.setLastElement(true);
        seq.addElement(elem);

        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess7(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-1-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));
        seq.addElement(new ProcessStep(name + ".2", ServiceType.Service2, workflow.getName()));
        seq.addElement(new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName()));
        seq.addElement(new ProcessStep(name + ".4", ServiceType.Service4, workflow.getName()));
        seq.addElement(new ProcessStep(name + ".5", ServiceType.Service5, workflow.getName()));
        seq.addElement(new ProcessStep(name + ".6", ServiceType.Service6, workflow.getName()));

        XORConstruct xorConstruct = new XORConstruct(name + "-3-XOR");
        Sequence seq2 = new Sequence(name + "-3-XOR-2-seq");
        seq2.addElement(new ProcessStep(name + "3-XOR-2-seq.1", ServiceType.Service7, workflow.getName()));
        ProcessStep elem1 = new ProcessStep(name + "3-XOR-2-seq.2", ServiceType.Service8, workflow.getName());
        elem1.setLastElement(true);
        seq2.addElement(elem1);

        xorConstruct.addElement(seq2);
        ProcessStep elem = new ProcessStep(name + ".3.1", ServiceType.Service8, workflow.getName());
        elem.setLastElement(true);
        xorConstruct.addElement(elem);

        seq.addElement(xorConstruct);

        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess8_without_lop(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-1-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        ANDConstruct andConstruct = new ANDConstruct(name + "-2-AND");
        ProcessStep elem = new ProcessStep(name + ".2.1", ServiceType.Service2, workflow.getName());
        elem.setLastElement(true);
        andConstruct.addElement(elem);
        ProcessStep elem1 = new ProcessStep(name + ".2.2", ServiceType.Service3, workflow.getName());
        elem1.setLastElement(true);
        andConstruct.addElement(elem1);

        seq.addElement(andConstruct);
        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess8(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-1-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        ANDConstruct andConstruct = new ANDConstruct(name + "-2-AND");
        andConstruct.addElement(new ProcessStep(name + ".2.1", ServiceType.Service2, workflow.getName()));

        Sequence seq2 = new Sequence(name + "-3-seq");
        seq2.addElement(new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName()));
        LoopConstruct loopConstruct = new LoopConstruct(name + "-3-loop");
        ProcessStep elem = new ProcessStep(name + "-3-loop-1", ServiceType.Service4, workflow.getName());
        elem.setLastElement(true);
        loopConstruct.addElement(elem);

        seq2.addElement(loopConstruct);

        andConstruct.addElement(seq2);

        seq.addElement(andConstruct);
        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getProcess9(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());

        Sequence seq = new Sequence(name + "-1-seq");

        ANDConstruct andConstruct = new ANDConstruct(name + "-2-AND");
        andConstruct.addElement(new ProcessStep(name + ".2.1", ServiceType.Service1, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.2", ServiceType.Service2, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.3", ServiceType.Service3, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.4", ServiceType.Service4, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.5", ServiceType.Service5, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.6", ServiceType.Service6, workflow.getName()));
        andConstruct.addElement(new ProcessStep(name + ".2.7", ServiceType.Service7, workflow.getName()));
        seq.addElement(andConstruct);


        ANDConstruct andConstruct2 = new ANDConstruct(name + "-3-AND");
        andConstruct2.addElement(new ProcessStep(name + ".3.1", ServiceType.Service7, workflow.getName()));
        andConstruct2.addElement(new ProcessStep(name + ".3.2", ServiceType.Service8, workflow.getName()));
        andConstruct2.addElement(new ProcessStep(name + ".3.3", ServiceType.Service9, workflow.getName()));
        andConstruct2.addElement(new ProcessStep(name + ".3.4", ServiceType.Service10, workflow.getName()));
        andConstruct2.addElement(new ProcessStep(name + ".3.5", ServiceType.Service1, workflow.getName()));
        andConstruct2.addElement(new ProcessStep(name + ".3.6", ServiceType.Service2, workflow.getName()));
        seq.addElement(andConstruct2);

        seq.addElement(new ProcessStep(name + ".4", ServiceType.Service3, workflow.getName()));

        ANDConstruct andConstruct3 = new ANDConstruct(name + "-5-AND");
        andConstruct3.addElement(new ProcessStep(name + ".5.1", ServiceType.Service4, workflow.getName()));
        andConstruct3.addElement(new ProcessStep(name + ".5.2", ServiceType.Service5, workflow.getName()));
        seq.addElement(andConstruct3);


        seq.addElement(new ProcessStep(name + ".6", ServiceType.Service6, workflow.getName()));
        seq.addElement(new ProcessStep(name + ".7", ServiceType.Service7, workflow.getName()));


        ANDConstruct andConstruct4 = new ANDConstruct(name + "-8-AND");
        andConstruct4.addElement(new ProcessStep(name + ".8.1", ServiceType.Service8, workflow.getName()));
        andConstruct4.addElement(new ProcessStep(name + ".8.2", ServiceType.Service9, workflow.getName()));
        seq.addElement(andConstruct4);

        ProcessStep elem = new ProcessStep(name + ".9", ServiceType.Service10, workflow.getName());
        elem.setLastElement(true);
        seq.addElement(elem);

        workflow.addElement(seq);

        return workflow;
    }


    public WorkflowElement getTauT1Test(String name, Date deadline) {
        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-seq");
        ProcessStep elem1 = new ProcessStep(name + ".11", ServiceType.Service11, workflow.getName());
        seq.addElement(elem1);
        ProcessStep elem2 = new ProcessStep(name + ".2", ServiceType.Service2, workflow.getName());
        seq.addElement(elem2);
        ProcessStep elem = new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName());
        elem.setLastElement(true);
        seq.addElement(elem);
        workflow.addElement(seq);

        return workflow;
    }

    public WorkflowElement getToitProcess1(String name, Date deadline) {

        WorkflowElement workflow = new WorkflowElement(name, deadline.getTime());
        Sequence seq = new Sequence(name + "-1-seq");
        seq.addElement(new ProcessStep(name + ".1", ServiceType.Service1, workflow.getName()));

        ANDConstruct andConstruct1 = new ANDConstruct(name + "-2-AND");
        andConstruct1.addElement(new ProcessStep(name + ".2", ServiceType.Service2, workflow.getName()));
        andConstruct1.addElement(new ProcessStep(name + ".3", ServiceType.Service3, workflow.getName()));
        seq.addElement(andConstruct1);

        seq.addElement(new ProcessStep(name + ".4", ServiceType.Service4, workflow.getName()));

        ANDConstruct andConstruct2 = new ANDConstruct(name + "-3-AND");
        ProcessStep elem5 = new ProcessStep(name + ".5", ServiceType.Service5, workflow.getName());
        elem5.setLastElement(true);
        andConstruct2.addElement(elem5);
        ProcessStep elem6 = new ProcessStep(name + ".6", ServiceType.Service6, workflow.getName());
        elem6.setLastElement(true);
        andConstruct2.addElement(elem6);
        ProcessStep elem7 = new ProcessStep(name + ".7", ServiceType.Service7, workflow.getName());
        elem7.setLastElement(true);
        andConstruct2.addElement(elem7);
        seq.addElement(andConstruct2);

        workflow.addElement(seq);

        return workflow;

    }

}
