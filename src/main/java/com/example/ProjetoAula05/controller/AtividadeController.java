package com.example.ProjetoAula05.controller;

import com.example.ProjetoAula05.model.dto.AtividadeDTO;
import com.example.ProjetoAula05.model.dto.MensagemDTO;
import com.example.ProjetoAula05.service.AtividadeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atividades")
@Slf4j
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    @GetMapping
    public ResponseEntity<Object> listar(){
        try{
            return ResponseEntity.ok(atividadeService.listar());
        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(atividadeService.buscarPorId(id));

        } catch (EntityNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(new MensagemDTO(ex.getMessage()));

        } catch ( Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody AtividadeDTO atividadeDTO){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(atividadeService.criar(atividadeDTO));
        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(
            @RequestBody AtividadeDTO atividadeDTO,
            @PathVariable("id") Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(atividadeService.atualizar(id, atividadeDTO));
        } catch (EntityNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));
        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") Long id
    ) {
        try {
            atividadeService.deletar(id);
            return ResponseEntity.ok(new MensagemDTO("Categoria com id " + id + " removido com sucesso!"));
        } catch (EntityNotFoundException ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MensagemDTO(ex.getMessage()));
        } catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MensagemDTO(ex.getMessage()));
        }
    }
}
