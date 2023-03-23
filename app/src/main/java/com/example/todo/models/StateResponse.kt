package com.example.todo.models

sealed class StateResponse<T>

open class StateSuccess<T>(val data: T): StateResponse<T>()
open class StateError<T> (val errorData: Error? = null): StateResponse<T>()
open class StateLoading<T> : StateResponse<T>()