package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Controller;

import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Exception.PageException;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Exception.ShippingNotFoundException;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model.Shipping;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Service.ShippingService;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.PingAck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ShippingController {
    @Autowired
    private ShippingService shipping_service;


    @GetMapping(path="/shipping/{shipping_id}")
    public @ResponseBody Shipping getShipping(@RequestHeader("X-User-ID") String user_id, @PathVariable String shipping_id) throws ShippingNotFoundException {
        if(user_id.equals(String.valueOf(0))){
            Optional<Shipping> s1 = shipping_service.getShipping(shipping_id);
            if (s1.isPresent()) return s1.get();
            throw new ShippingNotFoundException("Spedizione non esistente");
        }
        else {
            Optional<Shipping> s = shipping_service.getShipping(shipping_id, user_id);
            if (s.isPresent()) return s.get();
            throw new ShippingNotFoundException("Spedizione non esistente o non associata allo user id fornito");
        }
    }


    @GetMapping(value ="/shippings", params={"per_page","page"})
    public @ResponseBody Page<Shipping> getShippingsByUserIdOrGetAllShippings(@RequestHeader("X-User-ID") String user_id,@RequestParam int per_page, @RequestParam int page) throws PageException {
        page = page -1;
        if (page >= 0) {
            if (!user_id.equals(String.valueOf(0))) {
                return shipping_service.getShippingByUserId(user_id, per_page, page);
            } else {
                return shipping_service.getAllShippings(per_page, page);
            }
        }
        throw new PageException("Il parametro page deve essere maggiore di 0");
    }


    @GetMapping(path="/ping")
    public @ResponseBody PingAck getStatusDb(){
        return new PingAck("up",
                shipping_service.getDatabaseStatus());
    }

    @GetMapping(path="/ping1")
    public @ResponseBody String getPing1(){
        return "Pong";
    }

}
