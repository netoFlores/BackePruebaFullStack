package com.mefcinc.logistica.service;

import com.mefcinc.logistica.dto.OrderDto;
import com.mefcinc.logistica.dto.ResponseDto;
import com.mefcinc.logistica.models.TtOrderLogistica;

public interface OrderLogisticaService {

    ResponseDto<TtOrderLogistica> savedOrderlogistica(OrderDto orderLogistica);
    ResponseDto<TtOrderLogistica> updatedOrderlogistica(OrderDto orderLogistica);
    ResponseDto<TtOrderLogistica> deletedOrderlogistica(OrderDto orderLogistica);
}
