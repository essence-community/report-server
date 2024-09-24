package ru.tehnobear.essence.receiver.dto.admin.dictionary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.tehnobear.essence.dao.entries.TDGlobalSetting;
import ru.tehnobear.essence.share.dto.Fetch;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class DGlobalSettingFetch extends Fetch {
    @Schema(implementation = TDGlobalSettingData.class)
    private Map<String, Object> data;
    @Override
    public Map<String, Object> getData() {
        return this.data;
    }

    private static class TDGlobalSettingData extends TDGlobalSetting{}
}
