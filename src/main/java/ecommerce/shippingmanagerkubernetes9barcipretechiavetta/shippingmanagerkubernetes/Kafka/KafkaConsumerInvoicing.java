package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Kafka;

import com.google.gson.Gson;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model.Shipping;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Service.ShippingService;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.LoggingRequest;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.OrderPaidRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class KafkaConsumerInvoicing {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerInvoicing.class);

    @Autowired
    private ShippingService shipping_service;

    @Value(value ="${KAFKA_TOPIC_LOGGING}")
    private String topic_logging;

    @Autowired
    private KafkaTemplate<String,String> template;

    public void sendMessageInvoicing(String key, String value){
        template.send(topic_logging,key,value);
    }

    @KafkaListener(topics= "${KAFKA_TOPIC_INVOICING}", groupId ="${KAFKA_GROUP_ID}")
    public void listenInvoicingTopic(ConsumerRecord<String, String> record){
        LOG.info("Arrivato evento sul topic " + "${KAFKA_TOPIC_INVOICING}");
        String chiave = new Gson().fromJson(record.key(), String.class);
        if (chiave.equals("order_paid")){
            OrderPaidRequest o = new Gson().fromJson(record.value(), OrderPaidRequest.class);
          Optional<Shipping> s = shipping_service.findByOrderIdAndUserId(o.getOrderId(),o.getUserId());
          if (s.isPresent()) {
              LOG.info("Modifico lo stato della spedizione");
              s.get().setShippingState("TODO").setDDT(shipping_service.getShippingMaxDDT()+1);
              shipping_service.addShipping(s.get());
          }
          else {
              LOG.info("Invio un evento avente chiave shipping_unavailable");
              LoggingRequest lo = new LoggingRequest(o.getOrderId(), o.getUserId(), o.getAmountPaid(), Instant.now().toString());
              String message = new Gson().toJson(lo);
              sendMessageInvoicing("shipping_unavailable", message);
          }

        }

    }





}
