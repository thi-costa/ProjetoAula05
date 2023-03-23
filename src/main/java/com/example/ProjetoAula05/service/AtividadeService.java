package com.example.ProjetoAula05.service;

import com.example.ProjetoAula05.model.dto.AtividadeDTO;
import com.example.ProjetoAula05.model.entity.AtividadeEntity;
import com.example.ProjetoAula05.model.mapper.AtividadeMapper;
import com.example.ProjetoAula05.repository.AtividadeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository repository;
    @Autowired
    private AtividadeMapper mapper;

    public List<AtividadeDTO> listar(){
        List<AtividadeEntity> listaEntities = repository.findAll();
        return mapper.updateListDTO(listaEntities);
    }

    public AtividadeDTO buscarPorId(Long id){
        Optional<AtividadeEntity> atividadeEntityOp = repository.findById(id);

        if(atividadeEntityOp.isPresent()){
            AtividadeEntity atividadeEntity = atividadeEntityOp.get();
            return mapper.update(atividadeEntity);
        }

        throw new EntityNotFoundException("Atividade não encontrada");
    }

    public AtividadeDTO criar(AtividadeDTO atividadeDTO){
        AtividadeEntity atividade = mapper.update(atividadeDTO);
        atividade = repository.save(atividade);

        return mapper.update(atividade);
    }

    public AtividadeDTO atualizar(Long id, AtividadeDTO atividadeDTO){
        if(repository.existsById(id)){
            AtividadeEntity atividadeEntityActual = mapper.update(buscarPorId(id)) ;
            AtividadeEntity atividadeEntityToAdd = mapper.update(atividadeDTO);
            if(atividadeDTO.getFinalizado() == null){
                atividadeEntityToAdd.setFinalizado(atividadeEntityActual.getFinalizado());
            }
            if(atividadeDTO.getDescricao() == null){
                atividadeEntityToAdd.setDescricao(atividadeEntityActual.getDescricao());
            }

            atividadeEntityToAdd.setCriadoEm(atividadeEntityActual.getCriadoEm());

            atividadeEntityToAdd.setId(id);
            atividadeEntityToAdd = repository.save(atividadeEntityToAdd);

            return mapper.update(atividadeEntityToAdd);
        }

        throw new EntityNotFoundException("Atividade não encontrada!");
    }

    public void deletar(Long id){
        Optional<AtividadeEntity> atividadeEntityOp = repository.findById(id);

        if(atividadeEntityOp.isPresent()){
            AtividadeEntity atividadeEntity = atividadeEntityOp.get();
            repository.delete(atividadeEntity);

            return ;
        }

        throw new EntityNotFoundException("Atividade não encontrada!");
    }

}
