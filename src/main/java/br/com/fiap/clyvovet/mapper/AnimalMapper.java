package br.com.fiap.clyvovet.mapper;

import br.com.fiap.clyvovet.dto.animal.AnimalRequest;
import br.com.fiap.clyvovet.dto.animal.AnimalResponse;
import br.com.fiap.clyvovet.model.Animal;
import br.com.fiap.clyvovet.model.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimalMapper {
    public AnimalResponse animalToResponse(Animal animal) {
        return new AnimalResponse(animal.getId(), animal.getNome(),animal.getRaca(), animal.getEspecie(), animal.getPorte(), animal.getCor(), animal.getSexo(), animal.getDataNascimento(), animal.getObservacao(), animal.getTutor().getId(), animal.getTutor().getNome());
    }

    public Animal toEntity(AnimalRequest request, Tutor tutor){
        Animal animal = new Animal();
        animal.setNome(request.getNome());
        animal.setRaca(request.getRaca());
        animal.setEspecie(request.getEspecie());
        animal.setPorte(request.getPorte());
        animal.setCor(request.getCor());
        animal.setSexo(request.getSexo());
        animal.setDataNascimento(request.getDataNascimento());
        animal.setObservacao(request.getObservacao());
        animal.setTutor(tutor);
        return animal;
    }
}
