package com.pinterest.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CommentListResponseDTO implements Serializable {

    private List<CommentResponseDTO> comments;
    private int currentPage;
    private long totalItems;
    private int totalPages;

}
