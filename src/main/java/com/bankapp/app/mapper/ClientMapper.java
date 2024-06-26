package com.bankapp.app.mapper;

import com.bankapp.app.controller.dto.ClientDTO;
import com.bankapp.app.entity.Client;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR, uses = {AccountMapper.class})
public interface ClientMapper {

    ClientDTO toDTO(Client client);
    List<ClientDTO> listToDTO(List<Client> clientList);
}
