package tr.com.innova.payflex.campaignCriteria.drools.engine;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tr.com.innova.payflex.campaignCriteria.drools.engine.amqp.AMQPReceiver;
import tr.com.innova.payflex.campaignCriteria.drools.engine.service.CampaignCriteriaScenario1Service;

import java.util.UUID;

@SpringBootApplication
@Slf4j
public class CampaignCriteriaDroolsEngineApplication {

	public final static String requestQueueName = "payflex-campaignCriteria-Request";
	public final static String replyQueueName = "payflex-campaignCriteria-Reply";

	public final static String exchangeName = "payflex-campaignCriteria-Exchange";

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private CampaignCriteriaScenario1Service campaignCriteriaScenario1Service;

//	@Bean
//	RabbitTemplate rabbitTemplate() {
//		RabbitTemplate template = new RabbitTemplate(this.connectionFactory);
//		template.setExchange(exchange().getName());
//		template.setCorrelationKey(UUID.randomUUID().toString());
//		template.setEncoding("UTF-8");
//		template.setRoutingKey(requestQueueName);
//		template.setReplyAddress(replyQueueName);
//
//		return template;
//	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(requestQueue()).to(exchange()).with(requestQueueName);
	}

	@Bean
	Queue requestQueue() {
		return new Queue(requestQueueName);
	}

	@Bean
	SimpleMessageListenerContainer requestListenerContainer() {
		SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
		c.setConnectionFactory(this.connectionFactory);
		c.setQueues(requestQueue());
		c.setMessageListener(new MessageListenerAdapter(new AMQPReceiver(campaignCriteriaScenario1Service)));
		return c;
	}

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}

	public static void main(String[] args) {
		SpringApplication.run(CampaignCriteriaDroolsEngineApplication.class, args);

		// for debugging purpose

//		ApplicationContext ctx = SpringApplication.run(CampaignCriteriaDroolsEngineApplication.class, args);
//
//		String[] beanNames = ctx.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//
//		StringBuilder sb = new StringBuilder("Application beans:\n");
//		for (String beanName : beanNames) {
//			sb.append(beanName + "\n");
//		}
//		log.info(sb.toString());
	}


}
