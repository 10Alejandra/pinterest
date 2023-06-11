package com.pinterest.repository;

import com.pinterest.model.PinChildBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PinChildBoardRepository extends JpaRepository<PinChildBoard, Long> {
}
