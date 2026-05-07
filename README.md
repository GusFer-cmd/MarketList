<div align="center">

# 🛒 MarketList

### Organize suas compras de forma inteligente, moderna e intuitiva.

Projeto desenvolvido como entrega final da fase intermediária do  
**IRED CAPACITA BRASIL — Formação Android** 📱

</div>

---

# ✨ Sobre o Projeto

O **MarketList** é um aplicativo Android desenvolvido com foco em produtividade, organização e experiência do usuário.  
A aplicação permite gerenciar listas de compras de forma prática, moderna e eficiente.

O projeto foi inicialmente baseado em um protótipo Figma contendo apenas as telas de **Dashboard** e **Add Item**, porém toda a arquitetura, fluxo de navegação, regras de negócio, organização estrutural e melhorias de UX/UI foram idealizadas e implementadas por mim.

Além de atender aos requisitos propostos, o objetivo foi desenvolver uma aplicação com estrutura próxima de projetos reais de mercado, aplicando boas práticas modernas do ecossistema Android.

---

# 🎯 Objetivos do Projeto

- Aplicar conceitos modernos de desenvolvimento Android
- Construir uma arquitetura escalável e desacoplada
- Trabalhar com persistência local de dados
- Melhorar experiência do usuário com UI responsiva e intuitiva
- Simular integrações inteligentes utilizando arquitetura preparada para APIs
- Utilizar padrões e metodologias modernas de mercado

---

# 🧠 Principais Conceitos Aplicados

---

## 🏗️ Arquitetura MVVM

O projeto foi estruturado utilizando o padrão **MVVM (Model - View - ViewModel)**.

### Benefícios da abordagem:
- Separação de responsabilidades
- Melhor organização do código
- Facilidade de manutenção
- Escalabilidade
- Reatividade da interface
- Melhor reutilização de lógica

---

## ⚡ Jetpack Compose

Toda a interface foi construída utilizando **Jetpack Compose**, a toolkit moderna declarativa da Google para Android.

### Recursos utilizados:
- Componentização de UI
- Estados reativos
- Layouts declarativos
- Reutilização de componentes
- Organização visual moderna

### Benefícios:
- Código mais limpo
- Maior produtividade
- Facilidade de manutenção
- Interfaces mais fluidas

---

## 🗄️ Persistência Local com Room Database

O projeto utiliza **Room Database** para persistência local dos dados, permitindo armazenamento offline dos itens cadastrados.

### Estrutura aplicada:
- `Entity`
- `DAO Pattern`
- `Database Instance`
- Queries organizadas
- Integração com ViewModel

### Objetivo:
Garantir persistência dos itens da lista mesmo após fechamento do aplicativo.

> ⚠️ O projeto não utiliza migrations atualmente, pois a estrutura do banco ainda está em fase inicial e controlada durante o desenvolvimento acadêmico.

---

# 🌐 Integração com APIs e Retrofit

A aplicação foi estruturada pensando em futura integração com APIs externas utilizando **Retrofit**.

Mesmo sem uma API real integrada atualmente, a arquitetura foi preparada para suportar:
- Consumo de APIs REST
- Serviços externos
- Funcionalidades inteligentes
- Escalabilidade futura

---

## 🤖 SmartInput (Mockado)

Uma das funcionalidades idealizadas para o projeto foi o **SmartInput**, um sistema pensado para auxiliar o usuário na inserção inteligente de itens.

### Objetivos da funcionalidade:
- Sugestão automática de categorias
- Organização inteligente dos produtos
- Melhor experiência de uso
- Automatização parcial do preenchimento

---

## 🔬 Implementação Atual

Devido à indisponibilidade de créditos para utilização da API oficial da OpenAI/GPT durante o desenvolvimento, a funcionalidade foi implementada utilizando um sistema **mockado**, simulando o comportamento de uma IA através de regras locais e tratamento inteligente de strings.

Mesmo mockado, o sistema foi estruturado seguindo uma arquitetura preparada para futura integração real com IA.

---

## 🧠 Conceitos Aplicados no SmartInput

- Tratamento de texto
- Mapeamento inteligente de categorias
- Simulação de respostas inteligentes
- Organização por camada de serviço
- Estrutura preparada para Retrofit
- Arquitetura escalável

---

# 🔄 Gerenciamento de Estado

A aplicação utiliza gerenciamento de estado reativo através de:
- `mutableStateOf`
- `State`
- `ViewModel`

### Benefícios:
- Atualização automática da interface
- Melhor sincronização entre UI e dados
- Redução de inconsistências
- Maior previsibilidade do comportamento da aplicação

---

# 🧭 Navegação Moderna

Implementação de navegação utilizando:
- `Navigation Compose`
- Rotas desacopladas
- Navegação escalável

---

# 🎨 UI/UX Moderna

O aplicativo foi desenvolvido buscando uma experiência moderna e fluida.

### Características aplicadas:
- Layout responsivo
- Componentes reutilizáveis
- Hierarquia visual
- Feedbacks visuais
- Dialogs personalizados
- Melhor aproveitamento de espaço
- Organização visual consistente

---

# 🛠️ Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Material Design 3
- Room Database
- Retrofit
- Navigation Compose
- ViewModel
- State Management
- Android Studio

---

# 📂 Estrutura do Projeto

```bash
📦 MarketList
 ┣ 📂 Components
 ┣ 📂 Screens
 ┣ 📂 ViewModels
 ┣ 📂 Database
 ┣ 📂 Models
 ┣ 📂 Navigation
 ┣ 📂 Services
 ┗ 📂 Utils
```

---

# 🌟 Funcionalidades

✅ Adição de itens  
✅ Remoção de itens  
✅ Dashboard dinâmica  
✅ Persistência local  
✅ Categorias inteligentes  
✅ Interface moderna  
✅ Confirmação de ações  
✅ Navegação entre telas  
✅ Estados reativos  
✅ Organização inteligente de componentes  
✅ SmartInput mockado  
✅ Estrutura preparada para IA  

---

# 🚧 Melhorias Idealizadas Além do Figma

Embora o Figma inicial fornecesse apenas duas telas base, diversas melhorias foram projetadas e implementadas durante o desenvolvimento:

- Estrutura arquitetural completa
- Sistema inteligente de categorias
- Melhorias de UX/UI
- Componentização da interface
- Organização de navegação
- Estrutura escalável
- Fluxo completo da aplicação
- Persistência inteligente de dados
- Simulação de IA
- Estrutura preparada para APIs futuras

---

# 📚 Aprendizados

Durante o desenvolvimento deste projeto foram aprofundados conhecimentos em:

- Arquitetura Android
- MVVM
- Compose
- Persistência de dados
- Room Database
- Retrofit
- Gerenciamento de estado
- Estruturação de projetos
- Clean Code
- Componentização
- UX/UI
- Organização de aplicações escaláveis

---

# 👨‍💻 Autor

## Gustavo Fernandes

Projeto desenvolvido para fins acadêmicos e evolução profissional no ecossistema Android.

---

# 📌 Status do Projeto

🚀 Em evolução contínua.

Novas funcionalidades, melhorias visuais e integrações inteligentes ainda serão adicionadas futuramente.

---

<div align="center">

### ⭐ Se gostou do projeto, considere deixar uma estrela no repositório!

</div>
