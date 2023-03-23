package com.example.ProjetoAula05.model.mapper;

import com.example.ProjetoAula05.model.dto.AtividadeDTO;
import com.example.ProjetoAula05.model.entity.AtividadeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtividadeMapper {
    public AtividadeDTO update(AtividadeEntity atividade) {
        AtividadeDTO atividadeDTO = new AtividadeDTO();
        atividadeDTO.setId(atividade.getId());
        atividadeDTO.setTitulo(atividade.getTitulo());
        atividadeDTO.setFinalizado(atividade.getFinalizado());
        atividadeDTO.setDescricao(atividade.getDescricao());
        atividadeDTO.setCriadoEm(atividade.getCriadoEm());
        atividadeDTO.setAtualizadoEm(atividade.getAtualizadoEm());
        return atividadeDTO;
    }

    public AtividadeEntity update(AtividadeDTO atividade){
        AtividadeEntity atividadeEntity = new AtividadeEntity();
        atividadeEntity.setId(atividade.getId());
        atividadeEntity.setTitulo(atividade.getTitulo());
        atividadeEntity.setDescricao(atividade.getDescricao());
        atividadeEntity.setFinalizado(atividade.getFinalizado());
        //pelo intelij
        // meets rodrigo:
        https://meet.google.com/pky-ccvf-nao
        return atividadeEntity;
    }
    public List<AtividadeDTO> updateListDTO(List<AtividadeEntity> listaEntity){
        return listaEntity.stream()
                .map(this::update).toList();
    }
    public List<AtividadeEntity> updateListEntity(List<AtividadeDTO> listaDTO){
        return listaDTO.stream()
                .map(this::update).toList();
    }
}
