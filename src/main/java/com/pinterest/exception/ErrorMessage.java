package com.pinterest.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ErrorMessage<T> {

    private int status;
    private Date timestamp;
    private String title;
    private List<T> errors;

}
