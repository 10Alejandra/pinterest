package com.pinterest.repository;

import com.pinterest.model.PinBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinBoardRepository  extends JpaRepository<PinBoard, Long> {
}
