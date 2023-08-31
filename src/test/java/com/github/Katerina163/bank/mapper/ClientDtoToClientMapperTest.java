package com.github.Katerina163.bank.mapper;

import com.github.Katerina163.bank.dto.request.ClientDto;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ClientDtoToClientMapperTest {
    private ClientDtoToClientMapper mapper = new ClientDtoToClientMapper();

    @Test
    public void whenCorrect() {
        var dto = new ClientDto();
        dto.setEmail("email");
        dto.setPassword("password");
        dto.setFullName("name");
        dto.setPhone(89157864792L);
        dto.setPassport(2957624795L);
        var result = mapper.convert(dto);
        assertThat(result.getEmail(), is("email"));
        assertThat(result.getPassword(), is("password"));
        assertThat(result.getFullName(), is("name"));
        assertThat(result.getPhone(), is(89157864792L));
        assertThat(result.getPassport(), is(2957624795L));
    }

}