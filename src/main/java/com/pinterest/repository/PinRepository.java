package com.pinterest.repository;

import com.pinterest.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    @Modifying
    @Query("UPDATE Pin p SET p.relationshipKey = :relationshipKey WHERE p.id = :pinId")
    int updateRelationshipKeyField(
            @Param("relationshipKey") Long relationshipKey,
            @Param("pinId") Long pinId
    );

}
