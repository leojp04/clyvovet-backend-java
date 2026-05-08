package br.com.fiap.clyvovet.service;

import br.com.fiap.clyvovet.model.Pagamento;
import br.com.fiap.clyvovet.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPorId(UUID id) {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));
    }

    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public void deletar(UUID id) {
        pagamentoRepository.deleteById(id);
    }
}