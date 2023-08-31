package com.github.Katerina163.bank.dto.request;

import com.github.Katerina163.bank.validation.CreateGroup;
import com.github.Katerina163.bank.validation.ModifyGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class CreditDto {
    @NotNull(groups = CreateGroup.class)
    private UUID id;

    @Range(groups = {CreateGroup.class, ModifyGroup.class})
    private long limit;

    @Range(max = Integer.MAX_VALUE, groups = {CreateGroup.class, ModifyGroup.class})
    private int percent;
}
