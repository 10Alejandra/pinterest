package com.pinterest.mapper;

import com.pinterest.model.Board;
import com.pinterest.model.Pin;
import com.pinterest.model.PinBoard;
import org.springframework.stereotype.Component;

@Component
public class PinBoardMapper {

    public PinBoard toPinBoard(Pin pin, Board board) {
        return PinBoard.builder()
                .pin(pin)
                .board(board)
                .build();
    }

}
