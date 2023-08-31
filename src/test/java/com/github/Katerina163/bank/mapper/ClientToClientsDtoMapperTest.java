package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.model.Client;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClientToClientsDtoMapperTest {
    private ClientToClientsDtoMapper mapper = new ClientToClientsDtoMapper();

    @Test
    public void whenCorrect() {
        var client = new Client();
        var u = UUID.randomUUID();
        client.setId(u);
        client.setEmail("email");
        client.setFullName("name");
        client.setPhone(89157864792L);
        client.setPassport(2957624795L);
        var result = mapper.convert(client);
        assertThat(result.getId(), is(u));
        assertThat(result.getEmail(), is("email"));
        assertThat(result.getFullName(), is("name"));
        assertThat(result.getPhone(), is(89157864792L));
        assertThat(result.getPassport(), is(2957624795L));
    }
}