package ec.edu.uteq.microservicios.msusuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@FeignClient(name = "ms-facturacion", url = "${api.facturacion.url}")
public interface FacturacionClient {

    @GetMapping
    List<Map<String, Object>> getFacturas();
}
