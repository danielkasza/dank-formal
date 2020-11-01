package dank.formal.test.unreachable

import dank.formal._
import dank.formal.sby._
import org.scalatest._
import chisel3._

class ModuleWithUnreachaleStatements extends CoveredFormalModule {
    val io = IO(new Bundle {
        val in = Input(Bool())
        val out = Output(Bool())
    })

    when (io.in) {
        io.out := false.B
    }.elsewhen(!io.in) {
        io.out := true.B
    }.otherwise {
        /* Unreachable. */
        io.out := false.B
    }
}

class UnreachableTest extends FlatSpec {
    behavior of "SbyRun"

    it should "detect uncovered branches" in {
        try {
            new SbyRun(new ModuleWithUnreachaleStatements, "cover").throwErrors()
            assert(false)
        } catch {
            case e: SbyException => assert(e.message.contains("unreachable.scala"))
        }
    }
}