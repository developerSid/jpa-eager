package com.example.jpaeager

import com.example.jpaeager.entity.Car
import com.example.jpaeager.entity.Child
import com.example.jpaeager.entity.GrandParent
import com.example.jpaeager.entity.Parent
import com.example.jpaeager.entity.Toy
import com.example.jpaeager.service.GrandParentService
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.concurrent.thread

@SpringBootApplication
class JpaEagerApplication

fun main(args: Array<String>) {
   SpringApplication.run(JpaEagerApplication::class.java, *args).use { ac ->
      val grandParentService = ac.getBean(GrandParentService::class.java)
      val originalGrandParent = GrandParent(
         firstName = "Grand",
         lastName = "Parent",
         age = 65,
         parents = setOf(
            Parent(
               firstName = "Parent One",
               lastName = "Family One",
               age = 40,
               children = setOf(
                  Child(
                     firstName = "Child One",
                     lastName = "Family One",
                     age = 1,
                     toys = setOf(
                        Toy(
                           manufacturer = "Manufacturer One",
                           name = "Toy One",
                           condition = "New"
                        ),
                        Toy(
                           manufacturer = "Manufacturer Two",
                           name = "Toy Two",
                           condition = "Destroyed"
                        )
                     )
                  )
               ),
               cars = setOf(
                  Car(
                     make = "Make One",
                     model = "Model One",
                     year = 2015
                  )
               )
            )
         )
      )

      val savedGrandParent = grandParentService.save(originalGrandParent)

      thread {
         println("Loading entire graph")
         val threadGrandParentService = ac.getBean(GrandParentService::class.java)
         val foundGrandParent = threadGrandParentService.findById(savedGrandParent.id!!)

         println(foundGrandParent)
      }.join()

      println("Shutting down")
   }

   println("Finished")
}
