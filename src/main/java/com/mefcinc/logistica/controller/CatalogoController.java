package com.mefcinc.logistica.controller;

import com.mefcinc.logistica.dto.ResponseDto;
import com.mefcinc.logistica.models.TcPais;
import com.mefcinc.logistica.models.TcTipoBodega;
import com.mefcinc.logistica.models.TcTipoProducto;
import com.mefcinc.logistica.models.TsBodega;
import com.mefcinc.logistica.repositories.TcPaisRepository;
import com.mefcinc.logistica.repositories.TcTipoBodegaRepository;
import com.mefcinc.logistica.repositories.TcTipoProductoRepository;
import com.mefcinc.logistica.repositories.TsBodegaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RestController
@RequestMapping("/api/v1/catalogo")
public class CatalogoController {

    private static final Logger logger = LoggerFactory.getLogger(CatalogoController.class);

    @Autowired
    private TcPaisRepository tcPaisRepository;
    @Autowired
    private TcTipoBodegaRepository tcTipoBodegaRepository;
    @Autowired
    private TcTipoProductoRepository tcTipoProductoRepository;
    @Autowired
    private TsBodegaRepository tsBodegaRepository;


    @GetMapping("pais")
    /**
     *
     * Muestra todos los registro con estado activo de los paises.
     *
     */
    public ResponseDto<List<TcPais>> getPais(){
        ResponseDto<List<TcPais>> tcPaisResponseDto = new ResponseDto<>();
        try {
            tcPaisResponseDto.setCode("OK");
            tcPaisResponseDto.setErrores(null);
            tcPaisResponseDto.setResponse(tcPaisRepository.findAllByEnableOrderByDescripcionAsc(true));
            return tcPaisResponseDto;
        }catch (Exception e){

            return tcPaisResponseDto;
        }
    }

    @GetMapping("tipobodega")
    /**
     * Devuelve el listado de Tipo de Bodegas.
     * @return ResponseDto<List<TcTipoBodega>>
     */
    public ResponseDto<List<TcTipoBodega>> getTipoBodega(){
        ResponseDto<List<TcTipoBodega>> responseDto = new ResponseDto<>();
        try {
            responseDto.setCode("OK");
            responseDto.setErrores(null);
            responseDto.setResponse(tcTipoBodegaRepository.findAllByEnableOrderByDescripcionAsc(true));
            return responseDto;
        }catch (Exception e){

            return responseDto;
        }
    }

    @GetMapping("tipoproducto")
    /**
     * Devuelve el listado de Tipo de producto.
     * @return ResponseDto<List<TcTipoProducto>>
     */
    public ResponseDto<List<TcTipoProducto>> getTipoProducto(){
        ResponseDto<List<TcTipoProducto>> responseDto = new ResponseDto<>();
        try {
            responseDto.setCode("OK");
            responseDto.setErrores(null);
            responseDto.setResponse(tcTipoProductoRepository.findAllByEnableOrderByDescripcionAsc(true));
            return responseDto;
        }catch (Exception e){

            return responseDto;
        }
    }

    /**
     * Devuelve las bodegas que estan asociada a un pais y al tipo de bodega.
     * @param pais
     * @param tipoBodega
     * @return ResponseDto<List<TsBodega>>
     */
    @GetMapping("bodegas/{pais}/{tipoBodega}")
    public ResponseDto<List<TsBodega>> getBodegas(@PathVariable Long pais, @PathVariable Long tipoBodega){
        ResponseDto<List<TsBodega>> responseDto = new ResponseDto<>();
        try {
            TcPais tcPais = new TcPais();
            tcPais.setIdTcPais(pais);

            TcTipoBodega tcTipoBodega = new TcTipoBodega();
            tcTipoBodega.setIdTcTipoBodega(tipoBodega);

            responseDto.setCode("OK");
            responseDto.setErrores(null);
            responseDto.setResponse(tsBodegaRepository.findAllByEnableAndTcPaisAndTcTipoBodegaOrderByDescripcionAsc(true, tcPais, tcTipoBodega));
            return responseDto;
        }catch (Exception e){

            return responseDto;
        }
    }
}
