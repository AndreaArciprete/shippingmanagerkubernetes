package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Kafka;


import com.google.gson.Gson;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model.Shipping;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Service.ShippingService;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.OrderCompletedRequest;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.OrderValidationRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KakfaConsumerOrder {
    @Autowired
    private ShippingService shipping_service;

    private final Logger LOG = LoggerFactory.getLogger(KakfaConsumerOrder.class);

    @KafkaListener(topics= "${KAFKA_TOPIC_ORDER}", groupId ="${KAFKA_GROUP_ID}")
    public void listenOrderTopic(ConsumerRecord<String, String> record){
        String chiave = new Gson().fromJson(record.key(), String.class);
        if (chiave.equals("order_completed")){
            LOG.info("Ricevuto evento avente chiave order_completed");
            OrderCompletedRequest order_completed = new Gson().fromJson(record.value(), OrderCompletedRequest.class);
            Shipping s = new Shipping(order_completed.getOrderId(),order_completed.getUserId(),
                    order_completed.getShippingAddress(),
                    order_completed.getProducts(),"Pending");
            shipping_service.addShipping(s);
        }
        if (chiave.equals("order_validation")){
            LOG.info("Ricevuto evento avente chiave order_validation");
            OrderValidationRequest order_validation = new Gson().fromJson(record.value(), OrderValidationRequest.class);
            if (order_validation.getStatus() != 0){
                LOG.info("Setto la spedizione nello stato Abort");
                Shipping s1 = shipping_service.getShippingByOrderId(order_validation.getOrderId());
                s1.setShippingState("Abort");
                shipping_service.addShipping(s1);
            }
        }
    }


}
