package com.gerhard.cs50x.core.mapper

interface Mapper<Input, Output> {
    fun map(input: Input): Output
}
