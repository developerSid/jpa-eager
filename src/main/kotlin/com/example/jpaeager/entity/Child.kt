package com.example.jpaeager.entity

import java.util.UUID
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.FetchType.EAGER
import javax.persistence.GenerationType.IDENTITY

@Entity
data class Child(

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

   @ManyToOne(fetch = EAGER)
   @JoinColumn(name = "parent_id", nullable = false)
   var parent: Parent? = null,

   @OneToMany(mappedBy = "child", fetch = EAGER, cascade = [ALL])
   var toys: Set<Toy> = setOf()
) {
   constructor(firstName: String, lastName: String, age: Int, toys: Set<Toy>):
      this(id = null, firstName = firstName, lastName = lastName, age = age, toys = toys) {
      this.toys.forEach{ it.child = this }
   }

   override fun equals(other: Any?): Boolean {
      return if (other is Child) {
         this.uuid == other.uuid
      } else {
         false
      }
   }

   override fun hashCode(): Int {
      return uuid.hashCode()
   }

   override fun toString(): String {
      return "Child(id=$id, uuid=$uuid, firsName=$firstName, lastName=$lastName, age=$age, \ntoys=${toys.joinToString(", \n")})"
   }
}
