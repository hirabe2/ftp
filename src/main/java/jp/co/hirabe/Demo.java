package jp.co.hirabe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonPropertyOrder({"ID", "概要", "名前", "更新日時"})
@JsonIgnoreProperties(ignoreUnknown=true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Demo {

    @JsonProperty("ID")
    private Long id;

    @JsonProperty("名前")
    private String name;

    @JsonProperty("概要")
    private String desc;

    @JsonProperty("更新日時")
    private String modified;
}
