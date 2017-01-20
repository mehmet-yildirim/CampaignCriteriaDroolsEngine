package tr.com.innova.payflex.campaignCriteria.drools.engine;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tr.com.innova.payflex.campaignCriteria.drools.engine.amqp.Receiver;

@SpringBootApplication
public class CampaignCriteriaDroolsEngineApplication {

	final static String queueName = "spring-boot";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("spring-boot-exchange");
	}

	@Bean
	Binding binding(Queue queue, TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver){
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}


	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	public static void main(String[] args) {
		SpringApplication.run(CampaignCriteriaDroolsEngineApplication.class, args);
	}


}
