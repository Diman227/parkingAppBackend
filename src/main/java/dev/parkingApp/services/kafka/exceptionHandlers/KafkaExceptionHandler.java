package dev.parkingApp.services.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class KafkaExceptionHandler implements CommonErrorHandler {

    @Override
    public boolean handleOne(Exception exception, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
        handle(exception, consumer);
        return true;
    }

    @Override
    public void handleOtherException(Exception exception,
                                     Consumer<?, ?> consumer,
                                     MessageListenerContainer container,
                                     boolean batchListener) {
        handle(exception, consumer);
    }

    private void handle(Exception exception, Consumer<?, ?> consumer) {
        log.error("Exception thrown", exception);
        if (exception instanceof RecordDeserializationException ex) {
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
        } else if (exception instanceof SerializationException ex){
            log.error("SerializationException");
        } else if (exception instanceof ListenerExecutionFailedException){
            log.error("ListenerExecutionFailedException");
        }
        else {
            log.error("Exception not handled", exception);
        }
    }
}