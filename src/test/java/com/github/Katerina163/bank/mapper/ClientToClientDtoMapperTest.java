package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.model.Client;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClientToClientDtoMapperTest {
    private ClientToClientDtoMapper mapper = new ClientToClientDtoMapper();

    @Test
    public void whenCorrect() {
        var client = new Client();
        client.setEmail("email");
        client.setFullName("name");
        client.setPhone(89157864792L);
        client.setPassport(2957624795L);
        var result = mapper.convert(client);
        assertThat(result.getEmail(), is("email"));
        assertThat(result.getFullName(), is("name"));
        assertThat(result.getPhone(), is(89157864792L));
        assertThat(result.getPassport(), is(2957624795L));
    }
}