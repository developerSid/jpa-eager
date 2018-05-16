package com.example.jpaeager.entity

import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.FetchType.EAGER
import javax.persistence.GenerationType.IDENTITY

data class GrandParent(

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(nullable = false, columnDefinition = "BIGINT")
   val id: Long? = null,

   @Column(nullable = false)
   val firstName: String? = null,

   @Column(nullable = false)
   val age: Int? = null,

   @OneToMany(mappedBy = "parent", fetch = EAGER, cascade = [ALL])
   val parents: Set<Parent>? = null
) {
}