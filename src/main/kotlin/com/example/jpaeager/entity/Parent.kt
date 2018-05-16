package com.example.jpaeager.entity

import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.persistence.GenerationType.IDENTITY

data class Parent(

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(nullable = false, columnDefinition = "BIGINT")
   val id: Long? = null,

   @Column(nullable = false)
   val firstName: String? = null,

   @Column(nullable = false)
   val lastName: String? = null,

   @Column(nullable = false)
   val age: Int? = null,

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "grand_parent_id", nullable = false)
   val grandParent: GrandParent? = null
) {
}