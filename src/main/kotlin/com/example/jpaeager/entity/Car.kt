package com.example.jpaeager.entity

import java.util.UUID
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.persistence.GenerationType.IDENTITY

@Entity
data class Car(

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(nullable = false, columnDefinition = "BIGINT")
   var id: Long? = null,

   @Column(nullable = false)
   var uuid: String = UUID.randomUUID().toString(),

   @Column(nullable = false)
   var make: String? = null,

   @Column(nullable = false)
   var model: String? = null,

   @Column(nullable = false)
   var year: Int? = null,

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "parent_id", nullable = false)
   var parent: Parent? = null
) {
   override fun equals(other: Any?): Boolean {
      return if (other is Car) {
         this.uuid == other.uuid
      } else {
         false
      }
   }

   override fun hashCode(): Int {
      return uuid.hashCode()
   }

   override fun toString(): String {
      return "Toy(id=$id, \nuuid=$uuid, \nmake=$model, \nyear=$year)"
   }
}
