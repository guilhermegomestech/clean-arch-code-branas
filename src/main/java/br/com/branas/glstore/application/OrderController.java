package br.com.branas.glstore.application;

import br.com.branas.glstore.domain.entities.Order;
import br.com.branas.glstore.domain.services.OrderService;
import br.com.branas.glstore.infrastructure.exceptions.OrderException;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//Boundary
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private static final String REL_FOR_GET_ALL_ORDER = "Custumer order list";
    private static final String MSG_NOT_FOUND_ORDER_SERIAL_NUMBER = "Order whit number informated not found";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllOrder(){
        List<Order> listaOrders = this.orderService.getAllPedidos();
        if(listaOrders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        addHateoasInOrderList(listaOrders);
        return ResponseEntity.ok(this.orderService.getAllPedidos());
    }

    private void addHateoasInOrderList(List<Order> listaOrders){
        Link linkUri = null;
        for (Order orderHateoas:listaOrders){
            addLinkFindOrderSerialNumber(orderHateoas, linkUri);
            addLinkGetAllOrder(orderHateoas, linkUri);
        }
    }
    private void addLinkFindOrderSerialNumber(Order orderHateoas, Link linkUri){
        linkUri = linkTo(methodOn(OrderController.class).findOrderBySerialNumber(orderHateoas.getSerialNumberOrder())).withSelfRel();
        orderHateoas.add(linkUri);
    }

    private void addLinkGetAllOrder(Order orderHateoas, Link linkUri){
        linkUri = linkTo(methodOn(OrderController.class).getAllOrder()).withRel(REL_FOR_GET_ALL_ORDER);
        orderHateoas.add(linkUri);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody Order order){
        try{
            return ResponseEntity.ok(this.orderService.salvarPedido(order));
        } catch (OrderException oe){
            return ResponseEntity.unprocessableEntity().body(oe.getMessage());
        }
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<?> findOrderBySerialNumber(@PathVariable Long serialNumber){
        Order order = orderService.getOrderBySerialNumberOrder(serialNumber);
        if(order == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_NOT_FOUND_ORDER_SERIAL_NUMBER);
        }

        linkTo(OrderController.class).slash(order.getIdOrder()).withSelfRel();
        order.add(linkTo(OrderController.class).slash(order).withSelfRel());
        return ResponseEntity.ok(order);
    }

}

