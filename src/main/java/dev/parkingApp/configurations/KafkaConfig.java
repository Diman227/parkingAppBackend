package dev.parkingApp.configurations;

import dev.parkingApp.dtos.auth.SignInRequest;
import dev.parkingApp.dtos.request.SpotRequest;
import dev.parkingApp.services.kafka.KafkaExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListenerConfigurer;
import org.springframework.kafka.config.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableKafka
public class KafkaConfig implements KafkaListenerConfigurer {

    @Value("${kafka.topics.spots}")
    private String topicSpotsName;

    @Value("${kafka.topics.users}")
    private String topicUsersName;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Override
    public void configureKafkaListeners(KafkaListenerEndpointRegistrar registrar) {
        registrar.setValidator(this.validator);
    }

    @Bean
    public NewTopic createSpotTopic() {
        log.info("Kafka's partition is creating");
        return TopicBuilder
                .name(topicSpotsName)
                .build();
    }

    @Bean NewTopic createUserTopic() {
        return TopicBuilder
                .name(topicUsersName)
                .build();
    }

    @Bean
    public ConsumerFactory<String, SpotRequest> consumerSpotsFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "parking-app-bookings-spots");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, SpotRequest.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        config.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
        log.info("ConsumerSpotsFactory created");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConsumerFactory<String, SignInRequest> consumerUsersFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "parking-app-bookings-users");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, SignInRequest.class.getName());
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        config.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
        log.info("ConsumerUsersFactory created");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public CommonErrorHandler commonErrorHandler() {
        return new KafkaExceptionHandler();
    }

    @Bean(name = "kafkaListenerSpotsContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SpotRequest>> kafkaListenerSpotsContainerFactory (
            ConsumerFactory<String, SpotRequest> consumerFactory,
            CommonErrorHandler commonErrorHandler
    ) {
        ConcurrentKafkaListenerContainerFactory<String, SpotRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.setCommonErrorHandler(commonErrorHandler);

        return factory;
    }

    @Bean(name = "kafkaListenerUsersContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, SignInRequest>> kafkaListenerUsersContainerFactory (
            ConsumerFactory<String, SignInRequest> consumerFactory,
            CommonErrorHandler commonErrorHandler
    ) {
        ConcurrentKafkaListenerContainerFactory<String, SignInRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(1);
        factory.setCommonErrorHandler(commonErrorHandler);

        return factory;
    }




}
