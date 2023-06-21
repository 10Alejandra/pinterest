package com.pinterest.mapper;

import com.pinterest.dto.reaction.ReactionRequestDTO;
import com.pinterest.dto.reaction.ReactionResponseDTO;
import com.pinterest.model.Comment;
import com.pinterest.model.Reaction;
import com.pinterest.model.User;
import org.springframework.stereotype.Component;

@Component
public class ReactionMapper {

    public ReactionResponseDTO toReactionResponseDTO(Reaction reaction) {
        return ReactionResponseDTO.builder()
                .id(reaction.getId())
                .type(reaction.getType())
                .userId(reaction.getUser().getId())
                .commentId(reaction.getComment().getId())
                .build();
    }

    public  Reaction toReaction (ReactionRequestDTO reactionRequestDTO, User user, Comment comment) {
        return Reaction.builder()
                .type(reactionRequestDTO.getType())
                .user(user)
                .comment(comment)
                .build();
    }

}
