package com.example.marketlist.Retrofit

import android.util.Log
import com.example.marketlist.BuildConfig
import com.example.marketlist.Models.IA.GeneratedItem
import com.example.marketlist.Retrofit.api.OpenAIService
import com.example.marketlist.Retrofit.dto.ChatRequest
import com.example.marketlist.Retrofit.dto.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AIService(
    private val api: OpenAIService
) {
    private val gson = Gson()

    private val useMock = true

    suspend fun generateItems(prompt: String): List<GeneratedItem> {

        // =========================
        // MODO MOCK
        // =========================
        if (useMock) {
            Log.d("AI_MODE", "MOCK ATIVO")
            return mockResponse(prompt)
        }

        // =========================
        // MODO REAL
        // =========================
        Log.d("AI_MODE", "API REAL")

        val finalPrompt = """
            Gere uma lista de compras.

            Responda SOMENTE em JSON:
            [
              { "name": "item", "quantity": "quantidade", "category": "categoria" }
            ]

            Use categorias como:
            dairy, meat, fruits, bakery, cleaning, snacks

            Pedido: $prompt
        """.trimIndent()

        val request = ChatRequest(
            model = "gpt-4o-mini",
            messages = listOf(
                Message("user", finalPrompt)
            )
        )

        val response = api.generateItems(
            token = "Bearer ${BuildConfig.OPENAI_API_KEY}",
            request = request
        )

        val content = response.choices.first().message.content

        Log.d("API_KEY_TEST", BuildConfig.OPENAI_API_KEY)

        Log.d("AI_RAW", content ?: "null")

        val json = extractJson(content)

        return parseJson(json)
    }

    private fun mockResponse(prompt: String): List<GeneratedItem> {

        val p = prompt.lowercase()

        val pessoas = Regex("\\d+").find(prompt)?.value?.toIntOrNull() ?: 2

        return when {

            // CHURRASCO
            p.contains("churrasco") -> {

                val carneKg = pessoas * 0.4
                val linguicaKg = pessoas * 0.2
                val cerveja = pessoas * 3

                listOf(
                    GeneratedItem("Carne", "${carneKg} kg", "alimentos"),
                    GeneratedItem("Linguiça", "${linguicaKg} kg", "alimentos"),
                    GeneratedItem("Carvão", "1 pacote", "outros"),
                    GeneratedItem("Cerveja", "$cerveja latas", "bebidas"),
                    GeneratedItem("Refrigerante", "${pessoas} L", "bebidas")
                )
            }

            // CAFÉ
            p.contains("café") || p.contains("cafe") -> {
                listOf(
                    GeneratedItem("Pão", "${pessoas * 2} unidades", "alimentos"),
                    GeneratedItem("Leite", "${pessoas}L", "bebidas"),
                    GeneratedItem("Café", "1 pacote", "bebidas"),
                    GeneratedItem("Manteiga", "1 pote", "alimentos")
                )
            }

            p.contains("aniversário") || p.contains("festa") || p.contains("aniversario") -> {

                val bolo = if (pessoas <= 10) 1 else (pessoas / 10) + 1
                val doces = pessoas * 5
                val salgados = pessoas * 6
                val refrigeranteLitros = pessoas * 0.5
                val sucoLitros = pessoas * 0.3

                listOf(
                    GeneratedItem("Bolo", "$bolo unidade", "bakery"),
                    GeneratedItem("Doces", "$doces unidades", "snacks"),
                    GeneratedItem("Salgados", "$salgados unidades", "snacks"),
                    GeneratedItem("Refrigerante", "${refrigeranteLitros} L", "snacks"),
                    GeneratedItem("Suco", "${sucoLitros} L", "snacks"),
                    GeneratedItem("Pratos descartáveis", "${pessoas} unidades", "cleaning")
                )
            }


            p.contains("limpeza") -> {
                listOf(
                    GeneratedItem("Detergente", "1 unidade", "limpeza"),
                    GeneratedItem("Sabão em pó", "1 pacote", "limpeza"),
                    GeneratedItem("Desinfetante", "1 unidade", "limpeza"),
                    GeneratedItem("Esponja", "2 unidades", "limpeza")
                )
            }

            p.contains("almoço") || p.contains("almoco") -> {
                listOf(
                    GeneratedItem("Carne", "${pessoas * 0.3} kg", "alimentos"),
                    GeneratedItem("Arroz", "${pessoas * 0.2} kg", "alimentos"),
                    GeneratedItem("Feijão", "${pessoas * 0.2} kg", "alimentos"),
                    GeneratedItem("Salada", "${pessoas * 0.1} kg", "alimentos"),
                    GeneratedItem("Refrigerante", "${pessoas * 0.5} L", "bebidas"),
                    GeneratedItem("Suco", "${pessoas * 0.3} L", "bebidas")
                )
            }

            p.contains("jantar") -> {

                listOf(
                    GeneratedItem("Macarrão", "${pessoas * 0.2} kg", "snacks"),
                    GeneratedItem("Molho de tomate", "2 unidades", "snacks"),
                    GeneratedItem("Carne moída", "${pessoas * 0.25} kg", "meat"),
                    GeneratedItem("Queijo ralado", "${pessoas * 0.1} kg", "dairy"),
                    GeneratedItem("Vinho", "1 garrafa", "snacks")
                )
            }

            else -> throw Exception("Não consegui entender o pedido.")
        }
    }

    private fun extractJson(text: String): String {
        val start = text.indexOf("[")
        val end = text.lastIndexOf("]")

        if (start == -1 || end == -1) {
            throw Exception("JSON inválido retornado pela IA")
        }

        return text.substring(start, end + 1)
    }

    private fun parseJson(json: String): List<GeneratedItem> {
        return try {
            val type = object : TypeToken<List<GeneratedItem>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            Log.e("AI_PARSE_ERROR", json)
            throw Exception("Erro ao converter JSON")
        }
    }
}