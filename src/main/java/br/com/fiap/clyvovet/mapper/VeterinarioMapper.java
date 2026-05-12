package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.veterinario.VeterinarioRequest;
import br.com.fiap.clyvovet.dto.veterinario.VeterinarioResponse;
import br.com.fiap.clyvovet.model.Clinica;
import br.com.fiap.clyvovet.model.Veterinario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VeterinarioMapper {
    private final EnderecoMapper enderecoMapper;

    public VeterinarioResponse veterinarioToResponse(Veterinario veterinario) {
        return new VeterinarioResponse(
                veterinario.getId(),
                veterinario.getNome(),
                veterinario.getCpf(),
                veterinario.getTelefone(),
                veterinario.getEmail(),
                veterinario.getCrmv(),
                veterinario.getEspecialidade(),
                veterinario.getDataNascimento(),
                veterinario.getSexo(),
                enderecoMapper.enderecoToResponse(veterinario.getEndereco()),
                veterinario.getClinica().getId(),
                veterinario.getClinica().getNome()
        );
    }

    public Veterinario toEntity(VeterinarioRequest request){
        Veterinario veterinario = new Veterinario();
        veterinario.setNome(request.getNome());
        veterinario.setCpf(request.getCpf());
        veterinario.setCrmv(request.getCrmv());
        veterinario.setEspecialidade(request.getEspecialidade());
        veterinario.setTelefone(request.getTelefone());
        veterinario.setEmail(request.getEmail());
        veterinario.setDataNascimento(request.getDataNascimento());
        veterinario.setSexo(request.getSexo());
        veterinario.setEndereco(enderecoMapper.requestToEndereco(request.getEndereco()));
        return veterinario;
    }

    public void atualizar(Veterinario veterinario, VeterinarioRequest request, Clinica clinica) {
        veterinario.setNome(request.getNome());
        veterinario.setCpf(request.getCpf());
        veterinario.setCrmv(request.getCrmv());
        veterinario.setEspecialidade(request.getEspecialidade());
        veterinario.setTelefone(request.getTelefone());
        veterinario.setEmail(request.getEmail());
        veterinario.setDataNascimento(request.getDataNascimento());
        veterinario.setSexo(request.getSexo());
        veterinario.setEndereco(enderecoMapper.requestToEndereco(request.getEndereco()));
        veterinario.setClinica(clinica);
    }
}
