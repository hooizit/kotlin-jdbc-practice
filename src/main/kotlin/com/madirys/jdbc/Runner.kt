package com.madirys.jdbc

import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement
import java.sql.Timestamp
import java.time.LocalDateTime

fun main() {

    val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/myca",
        "postgres",
        "postgres"
    )
    // CREATE
    fun create() {
        val createStmnt: PreparedStatement = connection.prepareStatement(
                "insert into products (code, description, price, createdat) values (?, ?, ?, ?)")
        val now: LocalDateTime = LocalDateTime.now()
        createStmnt.setInt(1, 1)
        createStmnt.setString(2, "this awesome product must be created, updated then deleted")
        createStmnt.setDouble(3, 1.11)
        createStmnt.setTimestamp(4, Timestamp.valueOf(now))
        createStmnt.executeUpdate()
        println("Product created")
    }

    // READ
    fun read() {
        val readStmnt: Statement = connection.createStatement()
        val rs = readStmnt.executeQuery("select * from products")
        while (rs.next()) {
            val result = "${rs.getInt(1)} ${rs.getString(2)} ${rs.getBigDecimal(3)}"
            println(result)
        }
    }

    // UPDATE
    fun update() {
        val updateStmnt: PreparedStatement = connection.prepareStatement(
                "update products set price = 1.44 where code = 1"
        )
        updateStmnt.executeUpdate()
        println("Product updated")
    }

    // DELETE
    fun delete() {
        val deleteStmnt: PreparedStatement = connection.prepareStatement("delete from products where code = 1")
        deleteStmnt.executeUpdate()
        println("Product deleted")
    }

    create()
    read()
    update()
    read()
    delete()
}
