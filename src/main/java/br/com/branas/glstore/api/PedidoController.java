package br.com.branas.glstore.api;

import br.com.branas.glstore.domain.entities.Pedido;
import br.com.branas.glstore.domain.entities.Produto;
import br.com.branas.glstore.domain.services.PedidoService;
import br.com.branas.glstore.infrastructure.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("api/v1/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        List<Pedido> listaPedidos = this.pedidoService.getAllPedidos();

        if(listaPedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(this.pedidoService.getAllPedidos());
    }

    @PostMapping()
    public ResponseEntity<?> criarPedido(@RequestBody Pedido pedido){

        if(pedidoService.validarCpf(pedido.getCpfCliente())){
            return ResponseEntity.badRequest().body("cpf is invalid");
        }

        pedido.setQuantidade(pedido.getListaProdutos().size());
        pedido.setValorTotalPedido(pedido.getListaProdutos().stream().map(Produto::getPrecoProduto).reduce(BigDecimal.ZERO, BigDecimal::add));

        if(pedido.isPossuiCupom()){
            pedido.setValorTotalPedido(pedidoService.calcularDescontoPedido(pedido.getValorTotalPedido(), pedido.getPorcentagemDesconto()));
        }

        return ResponseEntity.ok(this.pedidoService.salvarPedido(pedido));
    }
}

