package at.ac.tuwien.infosys.viepepc;

import at.ac.tuwien.infosys.viepepc.database.entities.workflow.WorkflowElement;
import at.ac.tuwien.infosys.viepepc.database.entities.workflow.WorkflowElements;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

@Component
@Slf4j
public class ViepepCAccess {

    @Value("${viepep.url}")
    private String postUrl = "http://localhost:8088/addWorkflowRequests";

    @Retryable(maxAttempts=100, backoff=@Backoff(delay=1000, maxDelay=10000))
    public void sendRequests(List<WorkflowElement> workflowElement) throws Exception {

        WorkflowElements workflowElements = new WorkflowElements();
        workflowElements.setWorkflowElements(workflowElement);

        HttpPost put = new HttpPost(postUrl);
//        HttpPost put = new HttpPost("http://128.130.172.211:8080/cxf/workflow/addWorkflowRequest");
        //HttpPut put = new HttpPut("http://128.130.172.211:8080/cxf/workflow/addWorkflowRequest");
        HttpClient httpclient = HttpClients.createDefault();

        JAXBContext context = JAXBContext.newInstance(WorkflowElements.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter stringWriter = new StringWriter();
        m.marshal(workflowElements, stringWriter);

        String string = stringWriter.toString();
//        Unmarshaller unmarshaller = context.createUnmarshaller();
        //        Object unmarshal = unmarshaller.unmarshal(new StringReader(string));
        log.debug(string);

        StringEntity entity = new StringEntity(string);
        entity.setContentType("application/xml");
        put.setEntity(entity);
        HttpResponse response = httpclient.execute(put);
        StatusLine statusLine = response.getStatusLine();
        log.debug(statusLine.toString());

/*
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity response = restTemplate.postForEntity(POST_URL, workflowElement, String.class);

        log.info(String.valueOf(response.getStatusCodeValue()));
*/
    }
}
