package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientDtoMapper implements Mapper<Client, ClientDto> {
    @Override
    public ClientDto convert(Client client) {
        var dto = new ClientDto();
        dto.setEmail(client.getEmail());
        dto.setFullName(client.getFullName());
        dto.setPhone(client.getPhone());
        dto.setPassport(client.getPassport());
        return dto;
    }
}
