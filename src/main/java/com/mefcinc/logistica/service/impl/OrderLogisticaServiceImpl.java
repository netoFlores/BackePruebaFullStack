package com.mefcinc.logistica.service.impl;

import com.mefcinc.logistica.dto.OrderDto;
import com.mefcinc.logistica.dto.ResponseDto;
import com.mefcinc.logistica.models.*;
import com.mefcinc.logistica.repositories.*;
import com.mefcinc.logistica.service.OrderLogisticaService;
import com.mefcinc.logistica.utils.Utils;
import jdk.jshell.spi.ExecutionControlProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class OrderLogisticaServiceImpl implements OrderLogisticaService {

    private static final Logger logger = LoggerFactory.getLogger(OrderLogisticaServiceImpl.class);

    private static final Double DESCUENTO_TERRESTE = 0.05D;
    private static final Double DESCUENTO_MARITIMA = 0.03D;
    @Autowired
    private TcTipoBodegaRepository tcTipoBodegaRepository;

    @Autowired
    private TcTipoProductoRepository  tcTipoProductoRepository;

    @Autowired
    private TsBodegaRepository tsBodegaRepository;

    @Autowired
    private TsUsuarioRepository tsUsuarioRepository;

    @Autowired
    private TtOrderLogisticaRepository ttOrderLogisticaRepository;


    @Override
    public ResponseDto<TtOrderLogistica> savedOrderlogistica(OrderDto orderLogistica) {
        return savedorUpdate(orderLogistica);
    }

    @Override
    public ResponseDto<TtOrderLogistica> updatedOrderlogistica(OrderDto orderLogistica) {
        return savedorUpdate(orderLogistica);
    }

    @Override
    public ResponseDto<TtOrderLogistica> deletedOrderlogistica(OrderDto orderLogistica) {
        ResponseDto<TtOrderLogistica> ttOrderLogisticaResponseDto = new ResponseDto<>();
        try {
            TtOrderLogistica ttOrderLogistica = ttOrderLogisticaRepository.getById(orderLogistica.getIdTtOrderLogistica());

            if(ttOrderLogistica != null){
                ttOrderLogisticaRepository.delete(ttOrderLogistica);
                ttOrderLogisticaResponseDto.setResponse(null);
                ttOrderLogisticaResponseDto.setCode("OK");
                ttOrderLogisticaResponseDto.setErrores(null);
            }else{
                ttOrderLogisticaResponseDto.setResponse(null);
                ttOrderLogisticaResponseDto.setCode("NOT_FOUND");
                ttOrderLogisticaResponseDto.setErrores(null);
            }
        }catch (Exception e){
            ttOrderLogisticaResponseDto.setResponse(null);
            ttOrderLogisticaResponseDto.setCode("Error");
            ttOrderLogisticaResponseDto.setErrores(null);
        }

        return ttOrderLogisticaResponseDto;
    }

    /**
     * Logica para guardar o modificar un registro
     * @param orderLogistica
     * @return ResponseDto<TtOrderLogistica>
     */
   private ResponseDto<TtOrderLogistica> savedorUpdate(OrderDto orderLogistica){
       ResponseDto<TtOrderLogistica> ttOrderLogisticaResponseDto = new ResponseDto<>();

       try{

           TtOrderLogistica ttOrderLogistica = new TtOrderLogistica();

           if(orderLogistica.getIdTtOrderLogistica() != null){
               ttOrderLogistica.setIdTtOrderLogistica(orderLogistica.getIdTtOrderLogistica());
           }

           ttOrderLogistica.setPrecioEnvio(orderLogistica.getPrecioEnvio());
           ttOrderLogistica.setPrecioDescuento(orderLogistica.getPrecioDescuento());
           ttOrderLogistica.setPorcentajeDescuento(orderLogistica.getPorcentajeDescuento());
           ttOrderLogistica.setCantidadProducto(orderLogistica.getCantidadProducto());
           ttOrderLogistica.setPlaca(orderLogistica.getPlaca());
           ttOrderLogistica.setNumeroGuia(orderLogistica.getNumeroGuia());

           TsUsuario usuario = tsUsuarioRepository.getById(orderLogistica.getTsUsuario());
           ttOrderLogistica.setTsUsuario(usuario);

           TsBodega bodega = tsBodegaRepository.getById(orderLogistica.getTsBodega());
           ttOrderLogistica.setTsBodega(bodega);

           TcTipoProducto tipoProducto = tcTipoProductoRepository.getById(orderLogistica.getTcTipoProducto());
           ttOrderLogistica.setTcTipoProducto(tipoProducto);

           //Se aplicara descuento cuando se tenga mas de 10 productos.
           if(ttOrderLogistica.getCantidadProducto() > 10L){
               if(ttOrderLogistica.getTsBodega().getTcTipoBodega().getIdTcTipoBodega().equals(1L)){
                   ttOrderLogistica.setPrecioDescuento(ttOrderLogistica.getPrecioEnvio().multiply(new BigDecimal(DESCUENTO_TERRESTE.toString())));
                   ttOrderLogistica.setPorcentajeDescuento(5);
               }else{
                   ttOrderLogistica.setPrecioDescuento(ttOrderLogistica.getPrecioEnvio().multiply(new BigDecimal(DESCUENTO_MARITIMA.toString())));
                   ttOrderLogistica.setPorcentajeDescuento(3);
               }
           }

           ttOrderLogistica.setFechaRegistro(new Date());

           //Validaciones

           try{
               ttOrderLogistica.setFechaEntrega(Utils.dateToString(orderLogistica.getFechaEntrega()));
           }catch (Exception e){
               ttOrderLogisticaResponseDto.setCode("ERROR_SEMANTICA");
               Map<String, String> map = new HashMap<>();
               map.put("error", "El formato de fecha incorrecto: dd/mm/yyyy");
               ttOrderLogisticaResponseDto.setErrores(map);

               return ttOrderLogisticaResponseDto;
           }

           if(ttOrderLogistica.getTsBodega().getTcTipoBodega().getIdTcTipoBodega().equals(1L)) {
               if (!Utils.validarPatter(Utils.VALIDAR_PLACA, ttOrderLogistica.getPlaca())) {
                   ttOrderLogisticaResponseDto.setCode("ERROR_SEMANTICA");
                   Map<String, String> map = new HashMap<>();
                   map.put("error", "El formato del numero de placa es incorrecto");
                   ttOrderLogisticaResponseDto.setErrores(map);
                   return ttOrderLogisticaResponseDto;
               }
           }else {
               if (!Utils.validarPatter(Utils.VALIDAR_NUMERO_FLOTA, ttOrderLogistica.getPlaca())) {
                   ttOrderLogisticaResponseDto.setCode("ERROR_SEMANTICA");
                   Map<String, String> map = new HashMap<>();
                   map.put("error", "El formato del numero de flota es incorrecto");
                   ttOrderLogisticaResponseDto.setErrores(map);

                   return ttOrderLogisticaResponseDto;
               }
           }

           if (!Utils.validarPatter(Utils.VALIDAR_NUMERO_GUIA, ttOrderLogistica.getNumeroGuia())) {
               ttOrderLogisticaResponseDto.setCode("ERROR_SEMANTICA");
               return ttOrderLogisticaResponseDto;
           }

           //fin de la validacion

           ttOrderLogisticaRepository.saveAndFlush(ttOrderLogistica);

           ttOrderLogisticaResponseDto.setResponse(ttOrderLogistica);
           ttOrderLogisticaResponseDto.setCode("OK");
           ttOrderLogisticaResponseDto.setErrores(null);

       }catch (Exception e){
           logger.error("Error", e);
           ttOrderLogisticaResponseDto = new ResponseDto<>();
           ttOrderLogisticaResponseDto.setCode("ERROR");
           Map<String, String> map = new HashMap<>();
           map.put("error", "No se pudo crear la order");
           ttOrderLogisticaResponseDto.setErrores(map);
       }

       return ttOrderLogisticaResponseDto;
   }
}
