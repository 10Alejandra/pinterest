package com.pinterest.repository;

import com.pinterest.model.ChildBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildBoardRepository extends JpaRepository<ChildBoard, Long> {
}
