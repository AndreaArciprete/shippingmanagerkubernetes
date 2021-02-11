package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Service;
import com.mongodb.client.MongoClients;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model.Shipping;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model.ShippingRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShippingService {
    @Autowired
    private ShippingRepository shipping_repository;

    private final Environment env;

    private final String dbname;
    public ShippingService(Environment env) {
        this.env = env;
        this.dbname = env.getRequiredProperty("MONGO_INITDB_DATABASE");
    }

    public Optional<Shipping> getShipping(String shipping_id, String user_id){
        return shipping_repository.findByIdAndUserId(new ObjectId(shipping_id), user_id);
    }

    public Optional<Shipping> getShipping(String shipping_id){
        return shipping_repository.findById(new ObjectId(shipping_id));
    }

    public Page<Shipping> getShippingByUserId(String user_id, int per_page, int page){
        return shipping_repository.findByUserId(user_id, PageRequest.of(page,per_page));
    }

    public Page<Shipping> getAllShippings(int per_page, int page){
        return shipping_repository.findAll(PageRequest.of(page,per_page));
    }

    public Shipping getShippingByOrderId(String order_id){
        return shipping_repository.findByOrderId(order_id);
    }

    public Shipping addShipping(Shipping s){
        return shipping_repository.save(s);
        /*Il metodo save inserisce una nuova spedizione se non esiste, altrimenti aggiorna semplicemente la spedizione*/
    }

    public Optional<Shipping> findByOrderIdAndUserId(String order_id, String user_id){
        return shipping_repository.findByOrderIdAndUserId(order_id, user_id);
    }

    public Integer getShippingMaxDDT(){
        List<Shipping> s = shipping_repository.findAllByDDTNotNull();
        if (!s.isEmpty()) {
            return shipping_repository.findFirstByOrderByDDTDesc().getDDT();
        }
        else {
            return 0;
        }
    }


    public String getDatabaseStatus() {
        PrintStream out = System.out;
        ByteArrayOutputStream file=new ByteArrayOutputStream();
        System.setOut(new PrintStream(file));
        final String host = env.getRequiredProperty("MONGODB_HOSTNAME");
        final String port = env.getProperty("MONGODB_PORT", "27017");
        final String user = env.getRequiredProperty("MONGO_INITDB_ROOT_USERNAME");
        final String pass = env.getRequiredProperty("MONGO_INITDB_ROOT_PASSWORD");
        String s = String.format("mongodb://%s:%s@%s:%s/%s", user, pass, host, port, dbname);
        MongoClients.create(s);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String text=new String(file.toByteArray());
        System.setOut(out);
        if(text.contains("MongoSocketException")){
            return "down";
        }
        else {
            return "up";
        }
    }

}
