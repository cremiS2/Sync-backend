package com.projeto.tcc.service;

import com.projeto.tcc.dto.entrada.FuncionarioDTO;
import com.projeto.tcc.dto.mappers.FuncionarioMapper;
import com.projeto.tcc.dto.pesquisa.FuncionarioResultadoDTO;
import com.projeto.tcc.entities.Funcionario;
import com.projeto.tcc.exceptions.NaoRegistradoExcpetion;
import com.projeto.tcc.repository.FuncionarioRepository;
import com.projeto.tcc.service.validation.FuncionarioValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.projeto.tcc.repository.specs.FuncionarioSpecs.*;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;
    private final FuncionarioValidation validation;

    public Funcionario criarFuncionario(FuncionarioDTO dto){
        Funcionario funcionario = mapper.toEntity(dto);
        validation.validarEntidade(funcionario, dto);
        return repository.save(funcionario);
    }

    public FuncionarioResultadoDTO getFuncionarioId(Long idFuncionario){
        return mapper.toDTO(repository.findById(idFuncionario).orElseThrow(
                () -> new NaoRegistradoExcpetion("Funcionário com o id " + idFuncionario + " não existe")));
    }

    public Funcionario getFuncionarioEntity(Long id){
        return repository.findById(id).orElse(null);
    }

    public void upadateFuncionario(Long id, FuncionarioDTO funcionarioDTO){
        Funcionario funcionario = getFuncionarioEntity(id);
        mapper.updateFuncionario(funcionarioDTO, funcionario);
        validation.validarEntidade(funcionario, funcionarioDTO);
        repository.save(funcionario);
    }

    public Page<FuncionarioResultadoDTO> pesquisa(
            String nome,
            Integer matricula,
            String role,
            Long idEscala,
            String setor,
            String maquina,
            Integer numeroPagina,
            Integer tamanhoPagina
    ){

        Specification<Funcionario> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(nome != null){
            specs = specs.and(nomeLike(nome));
        }
        if(matricula != null){
            specs = specs.and(matriculaEqual(matricula));
        }
        if(role != null){
            specs = specs.and(escalaEqual(idEscala));
        }
        if(setor != null){
            specs =  specs.and(setorLike(setor));
        }
        if(maquina != null){
            specs = specs.and(maquinaLike(maquina));
        }

        //Não está funcionando muito bem
        if(role != null){
            specs = specs.and(roleLike(role));
        }

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);

        return repository.findAll(specs,pageable)
                .map(mapper::toDTO);

    }

    public void deletarFuncionario(Long idFuncionario){
        repository.delete(getFuncionarioEntity(idFuncionario));
    }
}
