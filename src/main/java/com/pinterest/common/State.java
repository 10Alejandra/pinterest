package com.pinterest.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum State {

    ACTIVE("true"),
    INACTIVE("false");

    private final String value;

}
