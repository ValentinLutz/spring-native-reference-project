package science.monke.enitity;

import com.fasterxml.jackson.annotation.JsonProperty

data class Config(
    @JsonProperty("base-uri")
    val baseUri: String,

    @JsonProperty("base-path")
    val basePath: String
)
