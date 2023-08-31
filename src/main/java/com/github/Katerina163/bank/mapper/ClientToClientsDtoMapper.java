package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.response.ClientsDto;
import com.github.Katerina163.bank.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientsDtoMapper implements Mapper<Client, ClientsDto> {
    @Override
    public ClientsDto convert(Client client) {
        var dto = new ClientsDto();
        dto.setId(client.getId());
        dto.setEmail(client.getEmail());
        dto.setFullName(client.getFullName());
        dto.setPhone(client.getPhone());
        dto.setPassport(client.getPassport());
        return dto;
    }
}
