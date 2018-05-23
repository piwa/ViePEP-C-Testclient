package at.ac.tuwien.infosys.viepepc;

import at.ac.tuwien.infosys.viepepc.database.entities.services.ServiceType;
import at.ac.tuwien.infosys.viepepc.database.entities.workflow.*;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;


@Component
@Slf4j
public class App implements CommandLineRunner {

    @Autowired
    private ViepepCAccess viepepCAccess;

    @Value("${process.model.amount}")
    private int PROCESS_MODEL_AMOUNT = 10;

    @Value("${process.model.max}")
    private int MAX_PROCESS_MODEL = 4;
    @Value("${factor}")
    private double factor = 2.5;

    @Value("${request.pattern}")
    private String requestPattern = "pyramid";
    @Value("${request.interval}")
    private int requestInterval = 120;


    @Autowired
    private ExampleProcesses exampleProcesses;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");


    public void run(String... args) {
        try {
            if(requestPattern.equalsIgnoreCase("pyramid")) {
                testPyramid();
            }
            else {
                testConstant();
            }

//            testHeuristic();


            //testTau_T_1();
            //testSingleShot("process2");
            //toit_test_1();
        }catch(Exception ex ){
            log.error("EXCEPTION", ex);
        }

        System.exit(0);
    }


    private void testSingleShot(String processName) throws Exception {
        log.info("test client started");

        List<String> processTypes = new ArrayList<>();
        processTypes.add(processName);
        transformAndInvoke(processTypes);
    }

    private void testHeuristic() throws Exception {
        System.out.println("test client started");
        List<String> processTypes = new ArrayList<>();
//        processTypes.add("process1");
//        processTypes.add("process2");
//        processTypes.add("process3");
//        processTypes.add("process4");
//        processTypes.add("process5");
//
//        processTypes.add("process6");
//        processTypes.add("process7");
//        processTypes.add("process8");
        processTypes.add("process9");
        processTypes.add("process10");
        transformAndInvoke(processTypes);
    }


    private void testConstant() throws Exception {
        System.out.println("test client started");
        int summe = 0;
        int k = 0;
//        for (int j = 0; j < 20; j++) {
        for (int j = 0; j < 10; j++) {
            List<String> processTypes = new ArrayList<>();

            int i1 = MAX_PROCESS_MODEL;
            for (int i = 1; i <= i1; i++) {

                processTypes.add(("process" + ((k % PROCESS_MODEL_AMOUNT) + 1)));
                k++;
            }
            summe += processTypes.size();
            transformAndInvoke(processTypes);
            log.info(j + ": " + simpleDateFormat.format(new Date()) + ", sum: " + summe + ", types: " + processTypes);
            Thread.sleep(1000 * requestInterval);
        }

        System.out.println("sum: " + summe);
    }

    private void testPyramid() throws Exception {
        Map<Integer, List<ServiceType>> arrivalMap = new TreeMap<Integer, List<ServiceType>>();

        int sum = 0;
        int k = 0;
        for (int i = 0; i < 27; i++) {
            int count = 0;
            switch (i) {
                case 1: count = 1; break;
                case 2: count = 1; break;
                case 3: count = 2; break;
                case 4: count = 2; break;
                case 5: count = 3; break;
                case 6: count = 3; break;
                case 7: count = 4; break;
                case 8: count = 4; break;
                case 9: count = MAX_PROCESS_MODEL; break;
                case 10: count = 0; break;
                case 11: count = 1; break;
                case 12: count = 1; break;
                case 13: count = 1; break;
                case 14: count = 1; break;
                case 15: count = 1; break;
                case 16: count = 1; break;
                case 17: count = 1; break;
                case 18: count = 1; break;
                case 19: count = 2; break;
                case 20: count = 2; break;
                case 21: count = 2; break;
                case 22: count = 2; break;
                case 23: count = 2; break;
                case 24: count = 2; break;
                case 25: count = 2; break;
                case 26: count = 3; break;
            }

            for (int j = 0; j < 2; j++) {
                sum += count;
                List<String> serviceTypes = new ArrayList<String>();
                for (int z = 0; z < count; z++) {
                    serviceTypes.add(("process" + ((k % PROCESS_MODEL_AMOUNT) + 1)));
                    k++;
                }
                if (i > 0) {
                    transformAndInvoke(serviceTypes);
                    System.out.println(i + ": " + simpleDateFormat.format(new Date()) + ", sum: " + sum + ", types: " + serviceTypes);
                    Thread.sleep(1000 * requestInterval);
                }
            }

        }

    }

    private void transformAndInvoke(List<String> processTypes) throws Exception {

        List<WorkflowElement> requestList = new ArrayList<>();
        Date deadline = new Date();
        for (String processType : processTypes) {
            WorkflowElement workflowElement;
            switch (processType) {
                case "process1":
                    workflowElement = exampleProcesses.getProcess1(UUID.randomUUID().toString().substring(0, 8) + "pr1", deadline);
                    break;
                case "process2":
                    workflowElement = exampleProcesses.getProcess2(UUID.randomUUID().toString().substring(0, 8) + "pr2", deadline);
                    break;
                case "process3":
                    workflowElement = exampleProcesses.getProcess3(UUID.randomUUID().toString().substring(0, 8) + "pr3", deadline);
                    break;
                case "process4":
                    workflowElement = exampleProcesses.getProcess4(UUID.randomUUID().toString().substring(0, 8) + "pr4", deadline);
                    break;
                case "process5":
                    workflowElement = exampleProcesses.getProcess5(UUID.randomUUID().toString().substring(0, 8) + "pr5", deadline);
                    break;
                case "process6":
                    workflowElement = exampleProcesses.getProcess6(UUID.randomUUID().toString().substring(0, 8) + "pr6", deadline);
                    break;
                case "process7":
                    workflowElement = exampleProcesses.getProcess7(UUID.randomUUID().toString().substring(0, 8) + "pr7", deadline);
                    break;
                case "process8":
                    workflowElement = exampleProcesses.getProcess8(UUID.randomUUID().toString().substring(0, 8) + "pr8", deadline);
                    break;
                case "process9":
                    workflowElement = exampleProcesses.getProcess9(UUID.randomUUID().toString().substring(0, 8) + "pr9", deadline);
                    break;
                case "process10":
                    workflowElement = exampleProcesses.getProcess10(UUID.randomUUID().toString().substring(0, 8) + "pr10", deadline);
                    break;


                default:
                    workflowElement = exampleProcesses.getProcess1(UUID.randomUUID().toString().substring(0, 8) + "pr1", deadline);
                    break;

            }

            long execDuration = getExecDuration(workflowElement);
            workflowElement.setDeadline((long) (DateTime.now().getMillis() + execDuration * factor));
            log.debug(workflowElement.getName() + " Deadline " + simpleDateFormat.format(workflowElement.getDeadline()));

            requestList.add(workflowElement);

        }
        try {
            viepepCAccess.sendRequests(requestList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private long getExecDuration(Element currentElement) {
        if (currentElement instanceof ProcessStep) {
            return ((ProcessStep) currentElement).getServiceType().getServiceTypeResources().getMakeSpan();
        } else {
            long exec = 0;
            if (currentElement instanceof WorkflowElement) {
                for (Element element : currentElement.getElements()) {
                    exec += getExecDuration(element);
                }
            } else if (currentElement instanceof Sequence) {
                for (Element element1 : currentElement.getElements()) {
                    exec += getExecDuration(element1);
                }
            } else if (currentElement instanceof ANDConstruct || currentElement instanceof XORConstruct) {
                long max = 0;
                for (Element element1 : currentElement.getElements()) {
                    long execDuration = getExecDuration(element1);
                    if (execDuration > max) {
                        max = execDuration;
                    }
                }
                exec += max;
            } else if (currentElement instanceof LoopConstruct) {
                long max = 0;
                for (Element element1 : currentElement.getElements()) {
                    long execDuration = getExecDuration(element1);
                    if (execDuration > max) {
                        max = execDuration;
                    }
                }
                max *= 3;
                exec += max;
            }
            return exec;
        }
    }




}
