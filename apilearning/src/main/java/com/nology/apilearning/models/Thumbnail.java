package com.nology.apilearning.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "path", "extension"})
public class Thumbnail {
    @JsonProperty("path")
    private String path;
    @JsonProperty("extension")
    private String extension;

}
