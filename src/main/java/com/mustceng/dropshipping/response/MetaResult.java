package com.mustceng.dropshipping.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaResult implements Serializable {

    @JsonProperty("return_code")
    private Integer code;

    @JsonProperty("info_list")
    private List<MessageItem> infoList;

    @JsonProperty("warn_list")
    private List<MessageItem> warnList;

    @JsonProperty("error_list")
    private List<MessageItem> errorList;

    @JsonIgnore
    public List<MessageItem> getInfoListSafe() {
        if (infoList == null) {
            infoList = new ArrayList<>();
        }
        return infoList;
    }

    @JsonIgnore
    public List<MessageItem> getWarnListSafe() {
        if (warnList == null) {
            warnList = new ArrayList<>();
        }
        return warnList;
    }

    @JsonIgnore
    public List<MessageItem> getErrorListSafe() {
        if (errorList == null) {
            errorList = new ArrayList<>();
        }
        return errorList;
    }
}

