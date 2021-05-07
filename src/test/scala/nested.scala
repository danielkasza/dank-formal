package dank.formal.test

import dank.formal._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3._

class A extends CoveredFormalModule {
    val io = IO(new Bundle {
        val in = Input(Bool())
        val out = Output(Bool())
    })

    assume(io.in)
    val reg = RegInit(true.B)
    reg := io.in
    io.out := reg
    assert(reg)
}

class B extends CoveredFormalModule {
    val io = IO(new Bundle {
        val in = Input(Bool())
        val out = Output(Bool())
    })

    assume(io.in)
    val reg = RegInit(true.B)
    reg := io.in
    io.out := reg
    assert(reg)
}

class Parent extends CoveredFormalModule {
    val io = IO(new Bundle {
        val in = Input(Bool())
        val out1 = Output(Bool())
        val out2 = Output(Bool())
    })

    val a = Module(new A)
    a.io.in := io.in
    io.out1 := a.io.out

    val b = Module(new B)
    b.io.in := io.in
    io.out2 := b.io.out
}

class NestedTest extends AnyFlatSpec with FormalTester {
    behavior of "Nested"

    prove(new Parent)
}
