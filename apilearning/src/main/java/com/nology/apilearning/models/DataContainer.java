package com.nology.apilearning.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataContainer {
    @JsonProperty("results")
    private List<MarvelCharacter> results;

    @JsonProperty("count")
    public long count;

    @JsonProperty("total")
    public long total;
}
