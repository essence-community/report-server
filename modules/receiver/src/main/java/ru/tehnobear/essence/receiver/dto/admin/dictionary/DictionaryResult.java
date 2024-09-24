package ru.tehnobear.essence.receiver.dto.admin.dictionary;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;
import ru.tehnobear.essence.share.dto.Dictionary;
import ru.tehnobear.essence.share.dto.Result;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class DictionaryResult extends Result {
    @NonNull
    private List<Dictionary> data;
}
