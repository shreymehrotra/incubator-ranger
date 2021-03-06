package org.apache.ranger.services.kms.client.json.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonAutoDetect(getterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL )
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class KMSSchedulerResponse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private KMSScheduler scheduler = null;

    public KMSScheduler getScheduler() { return scheduler; }
    
    public List<String> getQueueNames() {
    	List<String> ret = new ArrayList<String>();

    	if(scheduler != null) {
    		scheduler.collectQueueNames(ret);
    	}

    	return ret;
    }
    

    @JsonAutoDetect(getterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY)
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL )
    @JsonIgnoreProperties(ignoreUnknown=true)
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class KMSScheduler implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        private KMSSchedulerInfo schedulerInfo = null;

        public KMSSchedulerInfo getSchedulerInfo() { return schedulerInfo; }

        public void collectQueueNames(List<String> queueNames) {
        	if(schedulerInfo != null) {
        		schedulerInfo.collectQueueNames(queueNames, null);
        	}
        }
    }

    @JsonAutoDetect(getterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY)
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL )
    @JsonIgnoreProperties(ignoreUnknown=true)
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class KMSSchedulerInfo implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        private String     queueName = null;
        private KMSQueues queues    = null;

        public String getQueueName() { return queueName; }

        public KMSQueues getQueues() { return queues; }

        public void collectQueueNames(List<String> queueNames, String parentQueueName) {
        	if(queueName != null) {
        		String queueFqdn = parentQueueName == null ? queueName : parentQueueName + "." + queueName;

        		queueNames.add(queueFqdn);

            	if(queues != null) {
            		queues.collectQueueNames(queueNames, queueFqdn);
            	}
        	}
        }
    }

    @JsonAutoDetect(getterVisibility=Visibility.NONE, setterVisibility=Visibility.NONE, fieldVisibility=Visibility.ANY)
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL )
    @JsonIgnoreProperties(ignoreUnknown=true)
    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class KMSQueues implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        private List<KMSSchedulerInfo> queue = null;

        public List<KMSSchedulerInfo> getQueue() { return queue; }

        public void collectQueueNames(List<String> queueNames, String parentQueueName) {
        	if(queue != null) {
        		for(KMSSchedulerInfo schedulerInfo : queue) {
        			schedulerInfo.collectQueueNames(queueNames, parentQueueName);
        		}
        	}
        }
    }
}
