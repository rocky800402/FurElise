package com.furelise.ord.model;

import com.furelise.city.model.City;
import com.furelise.city.model.CityRepository;
import com.furelise.orddetail.model.OrdDetail;
import com.furelise.orddetail.model.OrdDetailRepository;
import com.furelise.product.model.Product;
import com.furelise.product.model.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public List<MemOrdDetailBO> getMemOrdDetailBO(Integer memID) {

        List<Ord> ords = ordR.findByMemID(memID);

        List<MemOrdDetailBO> memOrdDetailBOs = null;
        for (Ord ord : ords) {
            List<OrdDetail> ordDetails = ordDetailR.findByOrdID(ord.getOrdID());
            memOrdDetailBOs = new ArrayList<>();
            for (OrdDetail ordDetail : ordDetails) {
                MemOrdDetailBO memOrdDetailBO = new MemOrdDetailBO();
                Product product = productR.findById(ordDetail.getPID()).orElseThrow();

                memOrdDetailBO.setPName(product.getPName());
                memOrdDetailBO.setPPrice(product.getPPrice());
                memOrdDetailBO.setDetaQty(ordDetail.getDetaQty());
                memOrdDetailBO.setLevel(ordDetail.getLevel());
                memOrdDetailBO.setFeedback(ordDetail.getFeedback());
                memOrdDetailBO.setFbTime(ordDetail.getFbTime());

                memOrdDetailBOs.add(memOrdDetailBO);
            }

        }
        return memOrdDetailBOs;
    }

    public List<MemOrdVO> getMemOrdVO(Integer memID) {
        List<MemOrdVO> memOrdVOs = new ArrayList<>();
        List<Ord> ords = ordR.findByMemID(memID);
        for (Ord ord : ords) {
            City city = cityR.findByCityCode(ord.getCityCode());
            MemOrdVO memOrdVO =new MemOrdVO();
            memOrdVO.setOrdID(ord.getOrdID());
            memOrdVO.setCityCode(ord.getCityCode());
            memOrdVO.setCityName(city.getCityName());
            memOrdVO.setAddress(ord.getAddress());
            memOrdVO.setPayment(ord.getPayment());
            memOrdVO.setDeliver(ord.getDeliver());
            memOrdVO.setOrdDate(ord.getOrdDate());
            memOrdVO.setDeliverDate(ord.getDeliverDate());
            memOrdVO.setSum(ord.getSum());
            memOrdVO.setShipping(ord.getShipping());
            memOrdVO.setSaleID(ord.getSaleID());
            memOrdVO.setTotal(ord.getTotal());
            memOrdVO.setMemOrdDetailBOs(getMemOrdDetailBO(memID));

            memOrdVOs.add(memOrdVO);
        }
        return memOrdVOs;
    }

}