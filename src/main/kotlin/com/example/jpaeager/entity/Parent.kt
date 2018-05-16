package com.example.jpaeager.entity

import java.util.UUID
import javax.persistence.*
import javax.persistence.CascadeType.*
import javax.persistence.FetchType.EAGER
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

@Entity
data class Parent(

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
   @JoinColumn(name = "grand_parent_id", nullable = false)
   var grandParent: GrandParent? = null,

   @OneToMany(mappedBy = "parent", fetch = EAGER, cascade = [ALL])
   var children: Set<Child> = setOf(),

   @OneToMany(mappedBy = "parent", fetch = EAGER, cascade = [ALL])
   var cars: Set<Car> = setOf()
) {
   constructor(firstName: String, lastName: String, age: Int, children: Set<Child>, cars: Set<Car>) :
      this(id = null, firstName = firstName, lastName = lastName, age = age, children = children, cars = cars) {
      this.children.forEach { it.parent = this }
      this.cars.forEach { it.parent = this }
   }

   override fun equals(other: Any?): Boolean {
      return if (other is Parent) {
         this.uuid == other.uuid
      } else {
         false
      }
   }

   override fun hashCode(): Int {
      return uuid.hashCode()
   }

   override fun toString(): String {
      return "Parent(id=$id, \nuuid=$uuid, \nfirsName=$firstName, \nlastName=$lastName, \nage=$age, \nchildren=${children.joinToString(", \n")}, \ncars=${cars.joinToString(", \n")})"
   }
}
