package com.pinterest.controller;

import com.pinterest.model.Reaction;
import com.pinterest.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    @GetMapping("/{reactionId}")
    public ResponseEntity<Reaction> getReactionById (@PathVariable("reactionId") Long reactionId) {
        return null;
    }

}
