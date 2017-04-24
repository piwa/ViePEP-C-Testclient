package at.ac.tuwien.infosys.viepepc;

import at.ac.tuwien.infosys.viepepc.database.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
@Slf4j
public class App implements CommandLineRunner {

    @Value("${viepep.url}")
    private String POST_URL = "http://localhost:8088/addWorkflowRequests";
    @Value("${process.model.max}")
    private int MAX_PROCESS_MODEL = 5;
    @Value("${factor}")
    private double factor = 1.25;

    @Autowired
    private ExampleProcesses exampleProcesses;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");


    public void run(String... args) {
        try {
            //testConstant();
            //testTau_T_1();
            //testPyramid();

        //        testSingleShot();
            toit_test_1();
        }catch(Exception ex ){
            log.error("EXCEPTION", ex);
        }
    }


    private void toit_test_1() throws Exception {
        System.out.println("test client started");

        List<String> processTypes = new ArrayList<>();
        processTypes.add("toit_process_1");
        transformAndInvoke(processTypes);

        Thread.sleep(1000 * 60);

        processTypes = new ArrayList<>();
        processTypes.add("toit_process_2");
        transformAndInvoke(processTypes);
    }

    private void testSingleShot() throws Exception {
        System.out.println("test client started");

        List<String> processTypes = new ArrayList<>();
        processTypes.add("toit_process_1");
        transformAndInvoke(processTypes);

//        Thread.sleep( 1000 * 60);

//        processTypes = new ArrayList<>();
//        processTypes.add("process3");
//        transformAndInvoke(processTypes);
    }

    private void testConstant() throws Exception {
        System.out.println("test client started");
        int summe = 0;
        int k = 0;
        for (int j = 0; j < 20; j++) {
            List<String> processTypes = new ArrayList<>();
            int i1 = 10;
            if (j == 12) {
                i1 = 4;
            }
            for (int i = 1; i <= i1; i++) {
//                processTypes.add(("process" + ((k % 10) + 1)));
                processTypes.add(("cloudPaperProcess" + ((k % MAX_PROCESS_MODEL) + 1)));
                k++;
            }
            summe += processTypes.size();
            transformAndInvoke(processTypes);
            System.out.println(j + ": " + simpleDateFormat.format(new Date()) + ", sum: " + summe + ", types: " + processTypes);
            Thread.sleep(1000 * 60);
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
                case 1:count = 1;break;
                case 2:count = 1;break;
                case 3:count = 2;break;
                case 4:count = 2;break;
                case 5:count = 3; break;
                case 6:count = 3;break;
                case 7:count = 4;break;
                case 8:count = 4;break;
                case 9:count = 5;break;
                case 10:count = 0; break;
                case 11:count = 1; break;
                case 12:count = 1;break;
                case 13:count = 1;break;
                case 14:count = 1; break;
                case 15:count = 1; break;
                case 16:count = 1; break;
                case 17:count = 1; break;
                case 18:count = 1; break;
                case 19:count = 2; break;
                case 20:count = 2; break;
                case 21:count = 2; break;
                case 22:count = 2;break;
                case 23:count = 2; break;
                case 24:count = 2; break;
                case 25:count = 2; break;
                case 26:count = 3; break;
            }

            for (int j = 0; j < 2; j++) {
                sum += count;
                List<String> serviceTypes = new ArrayList<String>();
                for (int z = 0; z < count; z++) {
                    serviceTypes.add(("process" + ((k % MAX_PROCESS_MODEL) + 1)));
                    k++;
                }
                if (i > 0) {
//                System.out.println(i+" : " + sum + " getCount " + getCount + " : " + serviceTypes.size() + " " + serviceTypes);
                    transformAndInvoke(serviceTypes);
                    System.out.println(i + ": " + simpleDateFormat.format(new Date()) + ", sum: " + sum + ", types: " + serviceTypes);
                    Thread.sleep(1000 * 60);
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
                    workflowElement = exampleProcesses.getProcess8_without_lop(UUID.randomUUID().toString().substring(0, 8) + "pr8_loop", deadline);
                    break;

                case "cloudPaperProcess1":
                    workflowElement = exampleProcesses.getCloudProcess1(UUID.randomUUID().toString().substring(0, 8) + "pr1", deadline);
                    break;
                case "cloudPaperProcess2":
                    workflowElement = exampleProcesses.getCloudProcess2(UUID.randomUUID().toString().substring(0, 8) + "pr2", deadline);
                    break;
                case "cloudPaperProcess3":
                    workflowElement = exampleProcesses.getCloudProcess3(UUID.randomUUID().toString().substring(0, 8) + "pr3", deadline);
                    break;
                case "cloudPaperProcess4":
                    workflowElement = exampleProcesses.getCloudProcess4(UUID.randomUUID().toString().substring(0, 8) + "pr4", deadline);
                    break;
                case "cloudPaperProcess5":
                    workflowElement = exampleProcesses.getCloudProcess5(UUID.randomUUID().toString().substring(0, 8) + "pr5", deadline);
                    break;

                case "tau_t_1_test":
                    workflowElement = exampleProcesses.getTauT1Test(UUID.randomUUID().toString().substring(0, 8) + "pr5", deadline);
                    break;

                case "toit_process_1":
                    workflowElement = exampleProcesses.getToitProcess1(UUID.randomUUID().toString().substring(0, 8) + "pr_toit_1", deadline);
                    break;
                case "toit_process_2":
                    workflowElement = exampleProcesses.getToitProcess1(UUID.randomUUID().toString().substring(0, 8) + "pr_toit_2", deadline);
                    break;
                default:
                    workflowElement = exampleProcesses.getProcess9(UUID.randomUUID().toString().substring(0, 8) + "pr9", deadline);
                    break;

            }
            if(processType.equals("tau_t_1_test") || processType.equals("toit_process_1")) {
                long execDuration = getExecDuration(workflowElement);
                workflowElement.setDeadline((long) ((new Date().getTime()) + execDuration * factor * 10));
                System.out.println(workflowElement.getName() + " Deadline " + simpleDateFormat.format(workflowElement.getDeadline()));

                requestList.add(workflowElement);
            }
            else {
                long execDuration = getExecDuration(workflowElement);
                workflowElement.setDeadline((long) ((new Date().getTime()) + execDuration * factor));
                System.out.println(workflowElement.getName() + " Deadline " + simpleDateFormat.format(workflowElement.getDeadline()));

                requestList.add(workflowElement);
            }
        }
        try {
            sendRequests(requestList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getServiceTypes(int size) {

        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= size; i++) {
            int task = i;
            if (i > 10) {
                task = i % 10;
            }
            if (i == 20) {
                task = 10;
            }
            list.add(("process" + task));
        }
        return list;
    }

    private long getExecDuration(Element currentElement) {
        if (currentElement instanceof ProcessStep) {
            return ((ProcessStep) currentElement).getServiceType().getMakeSpan();
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


    private void sendRequests(List<WorkflowElement> workflowElement) throws Exception {

        WorkflowElements workflowElements = new WorkflowElements();
        workflowElements.setWorkflowElements(workflowElement);

        HttpPost put = new HttpPost(POST_URL);
//        HttpPost put = new HttpPost("http://128.130.172.211:8080/cxf/workflow/addWorkflowRequest");
        //HttpPut put = new HttpPut("http://128.130.172.211:8080/cxf/workflow/addWorkflowRequest");
        HttpClient httpclient = HttpClients.createDefault();

        JAXBContext context = JAXBContext.newInstance(WorkflowElements.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();
        m.marshal(workflowElements, stringWriter);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        String string = stringWriter.toString();
        Object unmarshal = unmarshaller.unmarshal(new StringReader(string));

        StringEntity entity = new StringEntity(string);
        entity.setContentType("application/xml");
        put.setEntity(entity);
        HttpResponse response = httpclient.execute(put);
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine);

/*
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.postForEntity(POST_URL, workflowElement, String.class);

        log.info(String.valueOf(response.getStatusCodeValue()));
*/
    }

}
