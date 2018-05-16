package com.example.jpaeager.entity

import java.util.UUID
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.FetchType.EAGER
import javax.persistence.GenerationType.IDENTITY

@Entity
data class GrandParent(

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(nullable = false, columnDefinition = "BIGINT")
   var id: Long? = null,

   @Column(nullable = false)
   var uuid: String = UUID.randomUUID().toString(),

   @Column(nullable = false)
   var firstName: String? = null,

   @Column(nullable = false)
   var lastName: String? = null,

   @Column(nullable = false)
   var age: Int? = null,

   @OneToMany(mappedBy = "grandParent", fetch = EAGER, cascade = [ALL])
   var parents: Set<Parent> = setOf()
) {
   constructor(firstName: String, lastName: String, age: Int, parents: Set<Parent>) :
      this(id = null, firstName = firstName, lastName = lastName, age = age, parents = parents) {
      this.parents.forEach { it.grandParent = this }
   }

   override fun equals(other: Any?): Boolean {
      return if (other is GrandParent) {
         this.uuid == other.uuid
      } else {
         false
      }
   }

   override fun hashCode(): Int {
      return uuid.hashCode()
   }

   override fun toString(): String {
      return "GrandParent(id=$id, uuid=$uuid, firsName=$firstName, lastName=$lastName, age=$age, parents=${parents.joinToString(", ")})"
   }
}
