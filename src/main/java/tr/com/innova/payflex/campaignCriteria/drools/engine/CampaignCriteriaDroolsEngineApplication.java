package tr.com.innova.payflex.campaignCriteria.drools.engine;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import tr.com.innova.payflex.campaignCriteria.drools.engine.amqp.AMQPReceiver;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class CampaignCriteriaDroolsEngineApplication {

	public final static String requestQueueName = "payflex-campaignCriteria-Request";
	public final static String replyQueueName = "payflex-campaignCriteria-Reply";

	public final static String exchangeName = "payflex-campaignCriteria-Exchange";

	@Autowired
	private ConnectionFactory connectionFactory;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Bean
	RabbitTemplate rabbitReplyTemplate() {
		RabbitTemplate template = new RabbitTemplate(this.connectionFactory);
		template.setExchange(exchange().getName());
		template.setRoutingKey(requestQueueName);
		template.setReplyAddress(replyQueueName);

		return template;
	}

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
	Queue replyQueue() {
		return new Queue(replyQueueName);
	}

	@Bean
	SimpleMessageListenerContainer requestListenerContainer() {
		SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
		c.setConnectionFactory(this.connectionFactory);
		c.setQueues(requestQueue());
		c.setMessageListener(new MessageListenerAdapter(new AMQPReceiver()));
		return c;
	}

	@Bean
	SimpleMessageListenerContainer replyListenerContainer() {
		SimpleMessageListenerContainer c = new SimpleMessageListenerContainer();
		c.setConnectionFactory(this.connectionFactory);
		c.setQueues(replyQueue());
		c.setMessageListener(rabbitReplyTemplate());
		return c;
	}

	@Bean
	public KieContainer kieContainer() {
		return KieServices.Factory.get().getKieClasspathContainer();
	}


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CampaignCriteriaDroolsEngineApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);

		StringBuilder sb = new StringBuilder("Application beans:\n");
		for (String beanName : beanNames) {
			sb.append(beanName + "\n");
		}
		log.info(sb.toString());
	}


}
