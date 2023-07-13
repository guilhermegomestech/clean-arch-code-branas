package br.com.branas.glstore.api;

import br.com.branas.glstore.application.entities.Order;
import br.com.branas.glstore.application.services.OrderService;
import br.com.branas.glstore.exceptions.OrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/pedido")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<Order>> getAllPedidos(){
        List<Order> listaOrders = this.orderService.getAllPedidos();

        if(listaOrders.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(this.orderService.getAllPedidos());
    }

    @PostMapping()
    public ResponseEntity<?> criarPedido(@RequestBody Order order){
        try{
            return ResponseEntity.ok(this.orderService.salvarPedido(order));
        } catch (OrderException oe){
            return ResponseEntity.unprocessableEntity().body(oe.getMessage());
        }
    }
}

