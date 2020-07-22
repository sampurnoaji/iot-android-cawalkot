package iot.android.cawalkot.external.base

abstract class BaseMapper<in I, out O> {
    abstract fun map(input: I): O
}
