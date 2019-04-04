package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.ArticuloC;
import com.sistemas.facturacion.model.ArticuloP;
import com.sistemas.facturacion.repository.ArticuloCRepository;
import com.sistemas.facturacion.repository.ArticuloPRepository;
import com.sistemas.facturacion.service.ArticuloCService;
import com.sistemas.facturacion.service.dto.ArticuloDTO;
import com.sistemas.facturacion.service.dto.ArticuloFacturaDTO;
import com.sistemas.facturacion.service.dto.FacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticuloCServiceImpl implements ArticuloCService {

    @Autowired
    private ArticuloCRepository articuloCRepository;

    @Autowired
    private ArticuloPRepository articuloPRepository;

    @Override
    public List<ArticuloDTO> obtenerTodos(String fecha) {
        String anio = fecha.substring(6,10);
        String mes = fecha.substring(3,5);
        String dia = fecha.substring(0,2);
        List<ArticuloC> articuloCList = articuloCRepository.findByFecha(anio+mes+dia);
        List<ArticuloDTO> articuloDTOList = new ArrayList<>();
        for (ArticuloC articuloC : articuloCList){
            if (articuloC.getStock()>0) {
                ArticuloP articuloP = articuloPRepository.findByRubroArticuloAndFecha(articuloC.getRubroArticulo(),anio+mes+dia);
                ArticuloDTO articuloDTO = new ArticuloDTO();
                articuloDTO.setValue(articuloC.getRubroArticulo());
                articuloDTO.setName(articuloC.getDescripcion());
                articuloDTO.setStock(articuloC.getStock());
                articuloDTO.setPrecioA(articuloP.getImporteA());
                articuloDTO.setPrecioB(articuloP.getImporteB());
                articuloDTO.setPrecioC(articuloP.getImporteC());
                articuloDTOList.add(articuloDTO);
            }
        }
        return articuloDTOList;
    }

    @Override
    public ArticuloDTO obtenerPorId(Long id, String fecha) {
        String anio = fecha.substring(6,10);
        String mes = fecha.substring(3,5);
        String dia = fecha.substring(0,2);
        String formattedNumber = String.format("%06d", id);
        ArticuloC articulo = articuloCRepository.findByRubroArticuloAndFecha(formattedNumber,anio+mes+dia);
        ArticuloDTO articuloDTO = new ArticuloDTO();
        if (articulo != null){
            ArticuloP articuloP = articuloPRepository.findByRubroArticuloAndFecha(articulo.getRubroArticulo(),anio+mes+dia);
            articuloDTO.setValue(articulo.getRubroArticulo());
            articuloDTO.setName(articulo.getDescripcion());
            articuloDTO.setStock(articulo.getStock());
            articuloDTO.setPrecioA(articuloP.getImporteA());
            articuloDTO.setPrecioB(articuloP.getImporteB());
            articuloDTO.setPrecioC(articuloP.getImporteC());
        } else {
            articuloDTO.setName("NO EXISTE");
        }
        return articuloDTO;
    }

    @Override
    public void descontarStock(FacturaDTO facturaDTO){
        for (ArticuloFacturaDTO articulo : facturaDTO.getArticulos()){
            ArticuloC articuloC = articuloCRepository.findByRubroArticulo(articulo.getCodigo());
            articuloC.setStock(articuloC.getStock()-articulo.getCantidad());
            articuloCRepository.save(articuloC);
        }
    }
}
