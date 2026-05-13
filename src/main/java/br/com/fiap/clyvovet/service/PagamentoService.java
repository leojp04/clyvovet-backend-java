package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.dto.pagamento.PagamentoRequest;
import br.com.fiap.clyvovet.dto.pagamento.PagamentoResponse;
import br.com.fiap.clyvovet.mapper.PagamentoMapper;
import br.com.fiap.clyvovet.model.EventoClinico;
import br.com.fiap.clyvovet.model.Pagamento;
import br.com.fiap.clyvovet.repository.EventoClinicoRepository;
import br.com.fiap.clyvovet.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final EventoClinicoRepository eventoClinicoRepository;
    private final PagamentoMapper pagamentoMapper;

    @CacheEvict(value = "pagamentos", allEntries = true)
    public PagamentoResponse criar(PagamentoRequest request) {
        EventoClinico eventoClinico = eventoClinicoRepository
                .findById(request.getEventoClinicoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "EventoClinico não encontrado com ID: " + request.getEventoClinicoId()
                ));
        Pagamento salvo = pagamentoRepository.save(
                pagamentoMapper.toEntity(request, eventoClinico)
        );
        return pagamentoMapper.toResponse(salvo);
    }

    @Cacheable("pagamentos")
    public Page<PagamentoResponse> listarTodos(Pageable pageable) {
        return pagamentoRepository.findAll(pageable)
                .map(pagamentoMapper::toResponse);
    }

    public PagamentoResponse buscarPorId(UUID id) {
        return pagamentoMapper.toResponse(
                pagamentoRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Pagamento não encontrado com ID: " + id
                        ))
        );
    }

    @CacheEvict(value = "pagamentos", allEntries = true)
    public PagamentoResponse atualizar(UUID id, PagamentoRequest request) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pagamento não encontrado com ID: " + id
                ));
        EventoClinico eventoClinico = eventoClinicoRepository
                .findById(request.getEventoClinicoId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "EventoClinico não encontrado com ID: " + request.getEventoClinicoId()
                ));
        pagamento.setFormaPagamento(request.getFormaPagamento());
        pagamento.setValor(request.getValor());
        pagamento.setDataPagamento(request.getDataPagamento());
        pagamento.setDescricao(request.getDescricao());
        pagamento.setObservacao(request.getObservacao());
        pagamento.setStatusPagamento(request.getStatusPagamento());
        pagamento.setEventoClinico(eventoClinico);
        return pagamentoMapper.toResponse(pagamentoRepository.save(pagamento));
    }

    @CacheEvict(value = "pagamentos", allEntries = true)
    public void deletar(UUID id) {
        if (!pagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Pagamento não encontrado com ID: " + id);
        }
        pagamentoRepository.deleteById(id);
    }
}