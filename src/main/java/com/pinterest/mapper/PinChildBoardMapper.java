package com.pinterest.mapper;

import com.pinterest.model.ChildBoard;
import com.pinterest.model.Pin;
import com.pinterest.model.PinChildBoard;
import org.springframework.stereotype.Component;

@Component
public class PinChildBoardMapper {

    public PinChildBoard toPinChildBoard(Pin pin, ChildBoard childBoard) {
        return PinChildBoard.builder()
                .pin(pin)
                .childBoard(childBoard)
                .build();
    }

}
