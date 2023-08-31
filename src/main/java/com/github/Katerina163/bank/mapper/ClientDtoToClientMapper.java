package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.request.ClientDto;
import com.github.Katerina163.bank.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoToClientMapper implements Mapper<ClientDto, Client> {
    @Override
    public Client convert(ClientDto dto) {
        var client = new Client();
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setFullName(dto.getFullName());
        client.setPhone(dto.getPhone());
        client.setPassport(dto.getPassport());
        return client;
    }
}
