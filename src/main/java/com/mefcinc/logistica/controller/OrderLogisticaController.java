package com.mefcinc.logistica.controller;

import com.mefcinc.logistica.dto.OrderDto;
import com.mefcinc.logistica.dto.ResponseDto;
import com.mefcinc.logistica.models.TtOrderLogistica;
import com.mefcinc.logistica.repositories.TtOrderLogisticaRepository;
import com.mefcinc.logistica.service.OrderLogisticaService;
import com.mefcinc.logistica.utils.Utils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/orderlogistica")
public class OrderLogisticaController {

    private static final Logger logger = LoggerFactory.getLogger(OrderLogisticaController.class);
    @Autowired
    private OrderLogisticaService orderLogisticaService;

    @Autowired
    private TtOrderLogisticaRepository ttOrderLogisticaRepository;

    /**
     * Guarda un numevo metodo
     * @param order
     * @return
     */
    @ApiOperation("Registrar una nueva order de logistica para ser guardado en la base de datos")
    @PostMapping
    public ResponseEntity<ResponseDto<TtOrderLogistica>> savedOrder(@RequestBody OrderDto order){
        ResponseDto<TtOrderLogistica> ttOrderLogisticaResponseDto = null;

        try{
            ttOrderLogisticaResponseDto = orderLogisticaService.savedOrderlogistica(order);

            if(ttOrderLogisticaResponseDto.getCode().equals("ERROR_SEMANTICA")){
                return new ResponseEntity<>(ttOrderLogisticaResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(ttOrderLogisticaResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ttOrderLogisticaResponseDto, HttpStatus.OK);
    }

    /**
     * Guarda un numevo metodo
     * @param order
     * @return
     */
    @ApiOperation("Modifica una nueva order de logistica para ser guardado en la base de datos")
    @PutMapping
    public ResponseEntity<ResponseDto<TtOrderLogistica>> editOrder(@RequestBody OrderDto order){
        ResponseDto<TtOrderLogistica> ttOrderLogisticaResponseDto = null;

        try{
            ttOrderLogisticaResponseDto = orderLogisticaService.savedOrderlogistica(order);

            if(ttOrderLogisticaResponseDto.getCode().equals("ERROR_SEMANTICA")){
                return new ResponseEntity<>(ttOrderLogisticaResponseDto, HttpStatus.UNPROCESSABLE_ENTITY);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ttOrderLogisticaResponseDto, HttpStatus.OK);
    }

    /**
     * Guarda un numevo metodo
     * @param order
     * @return
     */
    @ApiOperation("Elimina un registro fisicamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<TtOrderLogistica>> deleteOrder(@PathVariable(name = "id") Long id){
        ResponseDto<TtOrderLogistica> ttOrderLogisticaResponseDto = null;

        try{
            OrderDto orderDto = new OrderDto();
            orderDto.setIdTtOrderLogistica(id);

            ttOrderLogisticaResponseDto = orderLogisticaService.deletedOrderlogistica(orderDto);

            if(ttOrderLogisticaResponseDto.getCode().equals("NOT_FOUND")){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            logger.error("Error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ttOrderLogisticaResponseDto, HttpStatus.OK);
    }

    @ApiOperation("Devuelve todos los registros")
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "idTtOrderLogistica,desc") String[] sort
    ){
        List<TtOrderLogistica> outCome = new ArrayList<>(0);

        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for(String s : sort){
                    String[] sortNew = s.split(",");
                    orders.add(new Sort.Order(Utils.getSortDirection(sortNew[1]), sortNew[0]));
                }
            }else{
                orders.add(new Sort.Order(Utils.getSortDirection(sort[1]), sort[0]));
            }

            Pageable pageable = PageRequest.of(page,size, Sort.by(orders));

            Page<TtOrderLogistica> pageOrder;

            pageOrder = ttOrderLogisticaRepository.findAll(pageable);

            outCome = pageOrder.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("lstordertt", outCome);
            response.put("currentPage", pageOrder.getNumber());
            response.put("totalItems", pageOrder.getTotalElements());
            response.put("totalPages", pageOrder.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            logger.error("Error ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
