package com.furelise.ord.model;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.orddetail.model.OrdDetail;
import com.furelise.orddetail.model.OrdDetailRepository;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductRepository;
import com.furelise.sale.model.Sale;
import com.furelise.sale.model.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MemOrdService {
    @Autowired
    OrdRepository ordR;
    @Autowired
    OrdDetailRepository ordDetailR;
    @Autowired
    ProductRepository productR;
    @Autowired
    CityRepository cityR;
    @Autowired
    SaleRepository saleRepository;

    public List<MemOrdDetailBO> getMemOrdDetailBO(Ord ord) {
        List<OrdDetail> ordDetails = ordDetailR.findByOrdID(ord.getOrdID());
        return ordDetails.stream().map((ordDetail) -> {
            Product product = productR.findById(ordDetail.getPID()).orElseThrow();
            return new MemOrdDetailBO(product, ordDetail);
        }).toList();
    }

    public List<MemOrdVO> getMemOrdList(Integer memID) {
        List<Ord> ords = ordR.findByMemID(memID);
        return ords.stream().map(this::getMemOrdVO).toList();
    }

    // TODO: 跪著的人請看這
    public MemOrdVO getMemOrdById(Integer ordID) {
        Ord ord = ordR.findById(ordID).orElseThrow();
        return this.getMemOrdVO(ord);
    }

    private MemOrdVO getMemOrdVO(Ord ord) {
        City city = cityR.findByCityCode(ord.getCityCode());
        List<MemOrdDetailBO> memOrdDetailBOList = getMemOrdDetailBO(ord);
        Sale sale = Objects.isNull(ord.getSaleID()) ? null : saleRepository.findById(ord.getSaleID()).orElse(null);
        return new MemOrdVO(city, ord, memOrdDetailBOList, sale);
    }

    public OrdDetail updateMemOrdDetail(MemOrdLevelDTO memOrdLevelDTO){
            OrdDetail ordDetail = ordDetailR.findByOrdIDAndPID(memOrdLevelDTO.getOrdID(), memOrdLevelDTO.getPID());
            ordDetail.setLevel(memOrdLevelDTO.getLevel());
            ordDetail.setFeedback(memOrdLevelDTO.getFeedback());
            ordDetailR.save(ordDetail);
        return ordDetail;
    }

    public Ord updateMemOrdStatusDTO(MemOrdStatusDTO memOrdStatusDTO){
        Ord ord = ordR.findById(memOrdStatusDTO.getOrdID()).orElseThrow();
        ord.setOrdStatus(memOrdStatusDTO.getOrdStatus());
        ordR.save(ord);
        return ord;
    }

}