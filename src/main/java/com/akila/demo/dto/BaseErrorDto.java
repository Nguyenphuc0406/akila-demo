package com.akila.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseErrorDto
{

  @JsonProperty("timestamp")
  protected Long timestamp;

  @JsonProperty("error_message")
  protected String errorMessage;

}
