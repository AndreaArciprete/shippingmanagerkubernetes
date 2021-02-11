package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ShippingRepository extends PagingAndSortingRepository<Shipping, ObjectId> {
    Optional<Shipping> findById(ObjectId shipping_id);
    Optional<Shipping> findByIdAndUserId(ObjectId shipping_id,String user_id);
    Page<Shipping> findByUserId(String user_id, Pageable pageable);
    Shipping findByOrderId(String order_id);
    Optional<Shipping> findByOrderIdAndUserId(String order_id, String user_id);
    List<Shipping> findAllByDDTNotNull();
    Shipping findFirstByOrderByDDTDesc();

}
