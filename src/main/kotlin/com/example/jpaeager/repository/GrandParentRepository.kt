package com.example.jpaeager.repository

import com.example.jpaeager.entity.GrandParent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GrandParentRepository: JpaRepository<GrandParent, Long>
