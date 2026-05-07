package com.example.marketlist.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marketlist.Database.ItemRepository
import com.example.marketlist.Models.CategoryEnum.CategoryEnum
import com.example.marketlist.Models.Item.ItemModel
import com.example.marketlist.Models.UI_State.IAItemUiState
import com.example.marketlist.Retrofit.AIService
import com.example.marketlist.Retrofit.api.RetrofitInstance
import com.example.marketlist.UI_Event.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ItemViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    val itemList: Flow<List<ItemModel>> = repository.getAll
    val totalItems = itemList.map { it.size }
    val checkedItems = itemList.map { it.count { it.checked } }

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event

    private val aiService = AIService(RetrofitInstance.api)

    private val _state = MutableStateFlow(IAItemUiState())
    val state: StateFlow<IAItemUiState> = _state

    var aiPrompt by mutableStateOf("")
        private set

    fun onAiPromptChange(value: String) {
        aiPrompt = value
    }

    fun resetAIState() {
        aiPrompt = ""

        _state.value = _state.value.copy(
            aiGeneratedItems = emptyList(),
            aiError = null,
            aiLoading = false
        )
    }

    private fun extractPeopleCount(prompt: String): Int {
        val number = prompt.filter { it.isDigit() }.toIntOrNull()
        return number ?: 1
    }

    private fun parseQuantity(quantity: String): Int {
        val text = quantity.lowercase()

        val number = text.filter { it.isDigit() }.toIntOrNull() ?: 1

        return when {
            text.contains("kg") -> number * 5
            text.contains("g") -> (number / 200).coerceAtLeast(1)
            text.contains("ml") || text.contains("l") -> number
            else -> number
        }
    }

    private fun normalizeQuantity(name: String, quantity: Int, people: Int): Int {

        val text = name.lowercase()

        return when {

            text.contains("refrigerante") || text.contains("refri") ->
                (people / 2).coerceAtLeast(1)

            text.contains("suco") ->
                (people / 2).coerceAtLeast(1)

            text.contains("água") ->
                people.coerceAtLeast(1)

            text.contains("cerveja") ->
                (people * 2).coerceAtLeast(2)

            text.contains("carne") || text.contains("churrasco") ->
                (people * 400 / 1000).coerceAtLeast(1)

            text.contains("frango") ->
                (people / 2).coerceAtLeast(1)

            text.contains("lingui") ->
                people.coerceAtLeast(1)

            text.contains("hamburg") ->
                people.coerceAtLeast(1)

            text.contains("salsicha") ->
                (people * 2).coerceAtLeast(2)

            text.contains("pão") ->
                (people * 2).coerceAtLeast(1)

            text.contains("bolo") ->
                (people / 4).coerceAtLeast(1)

            text.contains("pizza") ->
                (people / 2).coerceAtLeast(1)

            text.contains("macarrão") ->
                (people / 2).coerceAtLeast(1)

            text.contains("arroz") ->
                (people / 2).coerceAtLeast(1)

            text.contains("feijão") ->
                (people / 2).coerceAtLeast(1)

            text.contains("queijo") ->
                (people / 2).coerceAtLeast(1)

            text.contains("leite") ->
                (people / 3).coerceAtLeast(1)

            text.contains("ovo") ->
                (people * 2).coerceAtLeast(2)

            text.contains("banana") ->
                people.coerceAtLeast(1)

            text.contains("maçã") ->
                people.coerceAtLeast(1)

            text.contains("batata") ->
                people.coerceAtLeast(1)

            text.contains("tomate") ->
                (people / 2).coerceAtLeast(1)

            text.contains("alface") ->
                (people / 3).coerceAtLeast(1)

            text.contains("salgadinho") ->
                (people / 2).coerceAtLeast(1)

            text.contains("chocolate") ->
                (people / 2).coerceAtLeast(1)

            text.contains("bala") ->
                (people * 3).coerceAtLeast(3)

            text.contains("sorvete") ->
                (people / 2).coerceAtLeast(1)

            text.contains("pipoca") ->
                (people / 2).coerceAtLeast(1)

            text.contains("detergente") ||
                    text.contains("sabão") ||
                    text.contains("amaciante") ||
                    text.contains("desinfetante") ->
                1

            else ->
                quantity.coerceAtMost(people * 2).coerceAtLeast(1)
        }
    }

    private fun mapCategory(category: String, name: String): CategoryEnum {

        val text = (category + " " + name).lowercase()

        return when {

            text.contains("leite") ||
                    text.contains("queijo") ||
                    text.contains("manteiga") ||
                    text.contains("requeijão") ||
                    text.contains("iogurte") ||
                    text.contains("cream cheese") ||
                    text.contains("nata") ||
                    text.contains("leite condensado") ||
                    text.contains("creme de leite") ->
                CategoryEnum.DAIRY

            text.contains("carne") ||
                    text.contains("frango") ||
                    text.contains("bife") ||
                    text.contains("picanha") ||
                    text.contains("lingui") ||
                    text.contains("salsicha") ||
                    text.contains("hamburg") ||
                    text.contains("peixe") ||
                    text.contains("atum") ||
                    text.contains("ovo") ||
                    text.contains("costela") ->
                CategoryEnum.MEAT

            text.contains("fruta") ||
                    text.contains("maçã") ||
                    text.contains("banana") ||
                    text.contains("laranja") ||
                    text.contains("uva") ||
                    text.contains("abacaxi") ||
                    text.contains("manga") ||
                    text.contains("melancia") ||
                    text.contains("limão") ||
                    text.contains("batata") ||
                    text.contains("cenoura") ||
                    text.contains("tomate") ||
                    text.contains("alface") ||
                    text.contains("cebola") ||
                    text.contains("alho") ||
                    text.contains("verdura") ||
                    text.contains("legume") ->
                CategoryEnum.FRUITS

            text.contains("pão") ||
                    text.contains("bolo") ||
                    text.contains("biscoito") ||
                    text.contains("bolacha") ||
                    text.contains("torrada") ||
                    text.contains("croissant") ||
                    text.contains("massa") ||
                    text.contains("macarrão") ||
                    text.contains("espaguete") ||
                    text.contains("lasanha") ||
                    text.contains("pizza") ->
                CategoryEnum.BAKERY

            text.contains("detergente") ||
                    text.contains("sabão") ||
                    text.contains("amaciante") ||
                    text.contains("desinfetante") ||
                    text.contains("água sanitária") ||
                    text.contains("cloro") ||
                    text.contains("limpeza") ||
                    text.contains("esponja") ||
                    text.contains("palha de aço") ||
                    text.contains("veja") ||
                    text.contains("multiuso") ->
                CategoryEnum.CLEANING

            text.contains("refrigerante") ||
                    text.contains("refri") ||
                    text.contains("suco") ||
                    text.contains("cerveja") ||
                    text.contains("água") ||
                    text.contains("salgadinho") ||
                    text.contains("chips") ||
                    text.contains("batata frita") ||
                    text.contains("chocolate") ||
                    text.contains("doce") ||
                    text.contains("bala") ||
                    text.contains("sorvete") ||
                    text.contains("pipoca") ||
                    text.contains("amendoim") ->
                CategoryEnum.SNACKS

            else -> CategoryEnum.SNACKS
        }
    }

    fun toggleDone(id: String) {
        viewModelScope.launch {
            repository.toggleDone(id)
        }
    }

    fun generateListWithAI() {
        viewModelScope.launch {

            _state.value = _state.value.copy(
                aiLoading = true,
                aiError = null
            )

            try {
                val generated = aiService.generateItems(aiPrompt)

                val people = extractPeopleCount(aiPrompt)

                val mapped = generated.map {

                    val parsed = parseQuantity(it.quantity)

                    val normalized = normalizeQuantity(
                        name = it.name,
                        quantity = parsed,
                        people = people
                    )

                    ItemModel(
                        name = it.name,
                        quantity = normalized,
                        category = mapCategory(it.category, it.name)
                    )
                }

                _state.value = _state.value.copy(
                    aiGeneratedItems = mapped,
                    aiLoading = false
                )

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    aiError = "Erro ao gerar lista",
                    aiLoading = false
                )
            }
        }
    }

    fun confirmGeneratedItems() {
        viewModelScope.launch {

            state.value.aiGeneratedItems.forEach {
                repository.create(it)
            }

            _state.value = _state.value.copy(
                aiGeneratedItems = emptyList()
            )
        }
    }

    fun clearGeneratedItems() {
        _state.value = _state.value.copy(
            aiGeneratedItems = emptyList()
        )
    }

    fun delete(id: String) {
        viewModelScope.launch {
            repository.delete(id)
            _event.emit(UiEvent.ShowSnackbar("Item deletado"))
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            repository.clearAll()
            _event.emit(UiEvent.ShowSnackbar("Lista limpa"))
        }
    }
}