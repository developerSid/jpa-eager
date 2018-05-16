package com.example.jpaeager.service

import com.example.jpaeager.entity.GrandParent
import com.example.jpaeager.repository.GrandParentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GrandParentService(
   private val grandParentRepository: GrandParentRepository
) {
   @Transactional
   fun save(grandParent: GrandParent): GrandParent {
      return grandParentRepository.save(grandParent)
   }

   fun findById(id: Long): GrandParent? {
      return grandParentRepository.getOne(id)
   }
}
