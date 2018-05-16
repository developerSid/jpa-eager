package com.example.jpaeager.entity

import java.util.UUID
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import javax.persistence.GenerationType.IDENTITY

@Entity
data class Toy(

   @Id
   @GeneratedValue(strategy = IDENTITY)
   @Column(nullable = false, columnDefinition = "BIGINT")
   var id: Long? = null,

   @Column(nullable = false)
   var uuid: String = UUID.randomUUID().toString(),

   @Column(nullable = false)
   var manufacturer: String? = null,

   @Column(nullable = false)
   var name: String? = null,

   @Column(nullable = false)
   var condition: String? = null,

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "child_id", nullable = false)
   var child: Child? = null
) {
   override fun equals(other: Any?): Boolean {
      return if (other is Toy) {
         this.uuid == other.uuid
      } else {
         false
      }
   }

   override fun hashCode(): Int {
      return uuid.hashCode()
   }

   override fun toString(): String {
      return "Toy(id=$id, uuid=$uuid, manufacturer=$manufacturer, name=$name, condition=$condition)"
   }
}
