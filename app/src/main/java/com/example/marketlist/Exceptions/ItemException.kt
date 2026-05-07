package com.example.marketlist.Exceptions

sealed class ItemException(message: String) : Exception(message) {

    class ItemNotFoundException(id: String) : ItemException("Item não encontrado")

    class EmptyNameException : ItemException("Nome do item é obrigatório")

    class QuantityLessThanOneException : ItemException("Quantidade deve ser maior que zero")

    class ItemUnknownException : ItemException("Ocorreu um erro inesperado")

}